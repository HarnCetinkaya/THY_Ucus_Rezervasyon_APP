package Thy_;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RezervasyonYap extends JFrame {
    private Ucus secilenUcus;
    private int kullaniciId;
    private String ad;
    private String soyad;
    private int secilenKoltuk;

    public RezervasyonYap(Ucus secilenUcus, int kullaniciId, String ad, String soyad) {
        this.secilenUcus = secilenUcus;
        this.kullaniciId = kullaniciId;
        this.ad = ad;
        this.soyad = soyad;

        setTitle("Türk Hava Yolları - Rezervasyon Yap");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Rezervasyon Bilgileri");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextArea rezervasyonBilgileriAlani = new JTextArea(10, 40);
        rezervasyonBilgileriAlani.setEditable(false);
        rezervasyonBilgileriAlani.setText(secilenUcus.toString());
        panel.add(new JScrollPane(rezervasyonBilgileriAlani), BorderLayout.CENTER);

        JPanel butonPaneli = new JPanel(new FlowLayout());

        JButton koltukSecButonu = new JButton("Koltuk Seç");
        koltukSecButonu.addActionListener(e -> {
            secilenKoltuk = koltukSec();
            if (secilenKoltuk != -1) {
                rezervasyonBilgileriAlani.append("\nSeçilen Koltuk: " + secilenKoltuk);
            }
        });
        butonPaneli.add(koltukSecButonu);

        JButton rezervasyonuOnaylaButonu = new JButton("Rezervasyonu Onayla");
        rezervasyonuOnaylaButonu.addActionListener(e -> {
            
            if (secilenKoltuk != -1) {
                boolean basarili = rezervasyonuKaydet();
                if (basarili) {
                    JOptionPane.showMessageDialog(null, "Rezervasyonunuz başarıyla oluşturuldu.\nSeçilen Koltuk: " + secilenKoltuk);
                    
                    new AnaSayfa(kullaniciId, ad, soyad).setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Rezervasyon kaydedilirken bir hata oluştu.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lütfen bir koltuk seçin.");
            }
        });
        butonPaneli.add(rezervasyonuOnaylaButonu);

        panel.add(butonPaneli, BorderLayout.SOUTH);

        add(panel);
    }

    private int koltukSec() {
        int mevcutKoltukSayisi = secilenUcus.getMevcutKoltuk();
        String[] koltuklar = new String[mevcutKoltukSayisi];
        for (int i = 0; i < mevcutKoltukSayisi; i++) {
            koltuklar[i] = "Koltuk " + (i + 1);
        }

        String secilenKoltuk = (String) JOptionPane.showInputDialog(
                this,
                "Koltuk Seçin",
                "Koltuk Seçimi",
                JOptionPane.PLAIN_MESSAGE,
                null,
                koltuklar,
                koltuklar[0]);

        if (secilenKoltuk != null) {
            return Integer.parseInt(secilenKoltuk.split(" ")[1]);
        } else {
            return -1; 
        }
    }

    private boolean rezervasyonuKaydet() {
        String kontrolSorgusu = "SELECT COUNT(*) FROM Rezervasyonlar WHERE ucus_id = ? AND koltuk_numarasi = ?";
        String eklemeSorgusu = "INSERT INTO Rezervasyonlar (kullanici_id, ucus_id, koltuk_numarasi, rezervasyon_tarihi) VALUES (?, ?, ?, NOW())";
        String koltukGuncellemeSorgusu = "UPDATE Ucuslar SET mevcut_koltuk = mevcut_koltuk - 1 WHERE ucus_id = ?";

        try (Connection conn = VeritabaniBaglantisi.getConnection()) {
            
            try (PreparedStatement kontrolPstmt = conn.prepareStatement(kontrolSorgusu)) {
                kontrolPstmt.setInt(1, secilenUcus.getId());
                kontrolPstmt.setInt(2, secilenKoltuk);
                ResultSet rs = kontrolPstmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "Bu koltuk zaten seçilmiş. Lütfen başka bir koltuk seçin.");
                    return false;
                }
            }

            
            try (PreparedStatement eklemePstmt = conn.prepareStatement(eklemeSorgusu)) {
                eklemePstmt.setInt(1, kullaniciId);
                eklemePstmt.setInt(2, secilenUcus.getId());
                eklemePstmt.setInt(3, secilenKoltuk);
                eklemePstmt.executeUpdate();
            }

            
            try (PreparedStatement koltukGuncellemePstmt = conn.prepareStatement(koltukGuncellemeSorgusu)) {
                koltukGuncellemePstmt.setInt(1, secilenUcus.getId());
                koltukGuncellemePstmt.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
