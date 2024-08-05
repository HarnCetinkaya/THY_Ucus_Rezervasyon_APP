package Thy_;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rezervasyonlarim extends JFrame {
    private int kullaniciId;
    private String ad;
    private String soyad;

    public Rezervasyonlarim(int kullaniciId, String ad, String soyad) {
        this.kullaniciId = kullaniciId;
        this.ad = ad;
        this.soyad = soyad;

        setTitle("Türk Hava Yolları - Rezervasyonlarım");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Rezervasyonlarım");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextArea rezervasyonAlani = new JTextArea(10, 40);
        rezervasyonAlani.setEditable(false);
        panel.add(new JScrollPane(rezervasyonAlani), BorderLayout.CENTER);

        JButton anaSayfayaDonButonu = new JButton("Ana Sayfaya Dön");
        anaSayfayaDonButonu.addActionListener(e -> {
            new AnaSayfa(kullaniciId, ad, soyad).setVisible(true);
            dispose(); 
        });
        panel.add(anaSayfayaDonButonu, BorderLayout.SOUTH);

        rezervasyonlariListele(rezervasyonAlani);

        add(panel);
    }

    private void rezervasyonlariListele(JTextArea rezervasyonAlani) {
        String sorgu = "SELECT Rezervasyonlar.rezervasyon_id, Rezervasyonlar.koltuk_numarasi, Ucuslar.kalkis_yeri, Ucuslar.varis_yeri, Ucuslar.kalkis_zamani, Ucuslar.varis_zamani " +
                       "FROM Rezervasyonlar JOIN Ucuslar ON Rezervasyonlar.ucus_id = Ucuslar.ucus_id " +
                       "WHERE Rezervasyonlar.kullanici_id = ?";

        try (Connection conn = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sorgu)) {
            pstmt.setInt(1, kullaniciId);
            ResultSet rs = pstmt.executeQuery();
            rezervasyonAlani.setText(""); 
            while (rs.next()) {
                int rezervasyonId = rs.getInt("rezervasyon_id");
                int koltukNo = rs.getInt("koltuk_numarasi");
                String kalkisYeri = rs.getString("kalkis_yeri");
                String varisYeri = rs.getString("varis_yeri");
                String kalkisZamani = rs.getString("kalkis_zamani");
                String varisZamani = rs.getString("varis_zamani");
                rezervasyonAlani.append("Rezervasyon ID: " + rezervasyonId + "\n");
                rezervasyonAlani.append("Koltuk No: " + koltukNo + "\n");
                rezervasyonAlani.append("Kalkış Yeri: " + kalkisYeri + "\n");
                rezervasyonAlani.append("Varış Yeri: " + varisYeri + "\n");
                rezervasyonAlani.append("Kalkış Zamanı: " + kalkisZamani + "\n");
                rezervasyonAlani.append("Varış Zamanı: " + varisZamani + "\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
