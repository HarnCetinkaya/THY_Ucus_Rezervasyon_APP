package Thy_;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AnaSayfa extends JFrame {
    private int kullaniciId;
    private String ad;
    private String soyad;
    private JRadioButton tekYonButonu;
    private JRadioButton gidisDonusButonu;
    private ButtonGroup ucusTuruGrubu;
    private JTable ucusTablosu;
    private DefaultTableModel ucusTabloModeli;

    public AnaSayfa(int kullaniciId, String ad, String soyad) {
        this.kullaniciId = kullaniciId;
        this.ad = ad;
        this.soyad = soyad;

        setTitle("Türk Hava Yolları - Ana Sayfa");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        // Üst panel
        JPanel ustPanel = new JPanel(new BorderLayout());
        ustPanel.setBackground(Color.DARK_GRAY);
        JLabel markaLabel = new JLabel("Türk Hava Yolları");
        markaLabel.setFont(new Font("Arial", Font.BOLD, 24));
        markaLabel.setForeground(Color.WHITE);
        ustPanel.add(markaLabel, BorderLayout.WEST);

        JPanel kullaniciPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        kullaniciPanel.setBackground(Color.DARK_GRAY);
        JLabel kullaniciLabel = new JLabel(ad + " " + soyad);
        kullaniciLabel.setForeground(Color.WHITE);
        JButton yardimButonu = new JButton("Yardım");
        yardimButonu.setBackground(Color.WHITE);
        yardimButonu.setForeground(Color.BLACK);
        JButton rezervasyonlarimButonu = new JButton("Rezervasyonlarım");
        rezervasyonlarimButonu.setBackground(Color.WHITE);
        rezervasyonlarimButonu.setForeground(Color.BLACK);
        JButton cikisButonu = new JButton("Çıkış Yap");
        cikisButonu.setBackground(Color.RED);
        cikisButonu.setForeground(Color.WHITE);

        kullaniciPanel.add(kullaniciLabel);
        kullaniciPanel.add(yardimButonu);
        kullaniciPanel.add(rezervasyonlarimButonu);
        kullaniciPanel.add(cikisButonu);

        ustPanel.add(kullaniciPanel, BorderLayout.EAST);

        panel.add(ustPanel, BorderLayout.NORTH);

        // Orta panel
        JPanel ortaPanel = new JPanel(new BorderLayout());
        ortaPanel.setBackground(Color.LIGHT_GRAY);

        JPanel aramaPaneli = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        aramaPaneli.setBackground(Color.LIGHT_GRAY);

        aramaPaneli.add(new JLabel("Kalkış:"));
        JTextField kalkisAlani = new JTextField(10);
        aramaPaneli.add(kalkisAlani);

        aramaPaneli.add(new JLabel("Varış:"));
        JTextField varisAlani = new JTextField(10);
        aramaPaneli.add(varisAlani);

        aramaPaneli.add(new JLabel("Tarih:"));
        JTextField tarihAlani = new JTextField(10);
        aramaPaneli.add(tarihAlani);

        aramaPaneli.add(new JLabel("Uçuş Tipi:"));
        JPanel ucusTuruPanel = new JPanel(new FlowLayout());
        ucusTuruPanel.setBackground(Color.LIGHT_GRAY);
        tekYonButonu = new JRadioButton("Tek Yön");
        gidisDonusButonu = new JRadioButton("Gidiş-Dönüş");
        ucusTuruGrubu = new ButtonGroup();
        ucusTuruGrubu.add(tekYonButonu);
        ucusTuruGrubu.add(gidisDonusButonu);
        ucusTuruPanel.add(tekYonButonu);
        ucusTuruPanel.add(gidisDonusButonu);
        aramaPaneli.add(ucusTuruPanel);

        JButton araButonu = new JButton("Uçuşları Ara");
        araButonu.setBackground(Color.RED);
        araButonu.setForeground(Color.WHITE);
        aramaPaneli.add(araButonu);

        ortaPanel.add(aramaPaneli, BorderLayout.NORTH);

        ucusTabloModeli = new DefaultTableModel(new String[]{"ID", "Kalkış", "Varış", "Kalkış Zamanı", "Varış Zamanı", "Mevcut Koltuk", "Uçuş Türü", "Uçuş Tipi", "Fiyat"}, 0);
        ucusTablosu = new JTable(ucusTabloModeli);
        JScrollPane ucusScrollPane = new JScrollPane(ucusTablosu);
        ortaPanel.add(ucusScrollPane, BorderLayout.CENTER);

        JButton rezervasyonYapButonu = new JButton("Rezervasyon Yap");
        rezervasyonYapButonu.setBackground(Color.BLACK);
        rezervasyonYapButonu.setForeground(Color.WHITE);
        ortaPanel.add(rezervasyonYapButonu, BorderLayout.SOUTH);

        panel.add(ortaPanel, BorderLayout.CENTER);

        araButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ucuslariAra(kalkisAlani.getText(), varisAlani.getText(), tarihAlani.getText());
            }
        });

        rezervasyonYapButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ucusTablosu.getSelectedRow();
                if (selectedRow != -1) {
                    int ucusId = (int) ucusTabloModeli.getValueAt(selectedRow, 0);
                    String kalkis = (String) ucusTabloModeli.getValueAt(selectedRow, 1);
                    String varis = (String) ucusTabloModeli.getValueAt(selectedRow, 2);
                    String kalkisZamani = (String) ucusTabloModeli.getValueAt(selectedRow, 3);
                    String varisZamani = (String) ucusTabloModeli.getValueAt(selectedRow, 4);
                    int mevcutKoltuk = (int) ucusTabloModeli.getValueAt(selectedRow, 5);
                    String ucusTuru = (String) ucusTabloModeli.getValueAt(selectedRow, 6);
                    String ucusTipi = (String) ucusTabloModeli.getValueAt(selectedRow, 7);
                    double fiyat = (double) ucusTabloModeli.getValueAt(selectedRow, 8);

                    Ucus secilenUcus = new Ucus(ucusId, kalkis, varis, kalkisZamani, varisZamani, mevcutKoltuk, ucusTuru, ucusTipi, fiyat);
                    new RezervasyonYap(secilenUcus, kullaniciId, ad, soyad).setVisible(true); 
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen bir uçuş seçin.");
                }
            }
        });

        yardimButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new YardimVeIletisim(kullaniciId, ad, soyad).setVisible(true);
                dispose(); 
            }
        });

        rezervasyonlarimButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rezervasyonlarim(kullaniciId, ad, soyad).setVisible(true); 
                dispose(); 
            }
        });

        
        cikisButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new UcusRezervasyonApp().setVisible(true); 
            }
        });

        add(panel);
    }

    private void ucuslariAra(String kalkis, String varis, String tarih) {
        String ucusTipi = tekYonButonu.isSelected() ? "Tek Yön" : "Gidiş-Dönüş";

        UcusArama ucusArama = new UcusArama();
        List<Ucus> ucuslar = ucusArama.ucuslariAra(kalkis, varis, tarih, ucusTipi);

        ucusTabloModeli.setRowCount(0); 
        for (Ucus ucus : ucuslar) {
            ucusTabloModeli.addRow(new Object[]{
                ucus.getId(),
                ucus.getKalkisYeri(),
                ucus.getVarisYeri(),
                ucus.getKalkisZamani(),
                ucus.getVarisZamani(),
                ucus.getMevcutKoltuk(),
                ucus.getUcusTuru(),
                ucus.getUcusTipi(),
                ucus.getFiyat()
            });
        }
    }

   
}
