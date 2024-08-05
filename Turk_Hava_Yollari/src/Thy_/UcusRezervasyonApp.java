package Thy_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UcusRezervasyonApp extends JFrame {
    private JTextField kalkisAlani;
    private JTextField varisAlani;
    private JTextField tarihAlani;
    private JTextArea sonucAlani;
    private boolean girisYapildi = false; 
    private int kullaniciId; 

    public UcusRezervasyonApp() {
        setTitle("Türk Hava Yolları");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel anaPanel = new JPanel();
        anaPanel.setLayout(new BorderLayout(10, 10));
        anaPanel.setBackground(Color.LIGHT_GRAY); 

        JPanel baslikPaneli = new JPanel();
        baslikPaneli.setLayout(new BorderLayout());
        baslikPaneli.setBackground(Color.DARK_GRAY); 
        JLabel baslikLabel = new JLabel("Türk Hava Yolları");
        baslikLabel.setFont(new Font("Arial", Font.BOLD, 24));
        baslikLabel.setForeground(Color.WHITE); 
        baslikPaneli.add(baslikLabel, BorderLayout.CENTER);

        JPanel girisPaneli = new JPanel();
        girisPaneli.setBackground(Color.DARK_GRAY); 
        girisPaneli.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton kayitOlButonu = new JButton("Kayıt Ol");
        kayitOlButonu.setBackground(Color.WHITE); 
        kayitOlButonu.setForeground(Color.BLACK); 
        girisPaneli.add(kayitOlButonu);

        JButton girisYapButonu = new JButton("Giriş Yap");
        girisYapButonu.setBackground(Color.WHITE); 
        girisYapButonu.setForeground(Color.BLACK); 
        girisPaneli.add(girisYapButonu);

        baslikPaneli.add(girisPaneli, BorderLayout.EAST);

        JPanel formPaneli = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        formPaneli.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPaneli.setBackground(Color.LIGHT_GRAY); 

        formPaneli.add(new JLabel("Kalkış:"));
        kalkisAlani = new JTextField(10);
        formPaneli.add(kalkisAlani);

        formPaneli.add(new JLabel("Varış:"));
        varisAlani = new JTextField(10);
        formPaneli.add(varisAlani);

        formPaneli.add(new JLabel("Tarih:"));
        tarihAlani = new JTextField(10);
        formPaneli.add(tarihAlani);

        formPaneli.add(new JLabel("Uçuş Tipi:"));
        JPanel ucusTuruPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ucusTuruPanel.setBackground(Color.LIGHT_GRAY); 
        JRadioButton tekYonButonu = new JRadioButton("Tek Yön");
        JRadioButton gidisDonusButonu = new JRadioButton("Gidiş-Dönüş");
        ButtonGroup ucusTuruGrubu = new ButtonGroup();
        ucusTuruGrubu.add(tekYonButonu);
        ucusTuruGrubu.add(gidisDonusButonu);
        ucusTuruPanel.add(tekYonButonu);
        ucusTuruPanel.add(gidisDonusButonu);
        formPaneli.add(ucusTuruPanel);

        JButton araButonu = new JButton("Uçuşları Ara");
        araButonu.setBackground(Color.RED); 
        araButonu.setForeground(Color.WHITE); 
        formPaneli.add(araButonu);

        sonucAlani = new JTextArea(10, 70);
        sonucAlani.setEditable(false);
        JScrollPane sonucScrollPane = new JScrollPane(sonucAlani);
        sonucScrollPane.setBorder(BorderFactory.createTitledBorder("Uçuş Sonuçları"));

        anaPanel.add(baslikPaneli, BorderLayout.NORTH);
        anaPanel.add(formPaneli, BorderLayout.CENTER);
        anaPanel.add(sonucScrollPane, BorderLayout.SOUTH);

        araButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (girisYapildi) {
                    String ucusTipi = tekYonButonu.isSelected() ? "Tek Yön" : "Gidiş-Dönüş";
                    ucuslariAra(ucusTipi);
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen uçuşları aramadan önce giriş yapın.");
                }
            }
        });

        kayitOlButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kayitOl();
            }
        });

        girisYapButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                girisYap();
            }
        });

        add(anaPanel);
    }

    private void ucuslariAra(String ucusTipi) {
        String kalkis = kalkisAlani.getText();
        String varis = varisAlani.getText();
        String tarih = tarihAlani.getText();

        UcusIslemleri ucusIslemleri = new UcusIslemleri();
        List<Ucus> ucuslar = ucusIslemleri.ucuslariAra(kalkis, varis, tarih, ucusTipi);

        sonucAlani.setText("");
        for (Ucus ucus : ucuslar) {
            sonucAlani.append(ucus.toString() + "\n");
        }
    }

    private void kayitOl() {
        JTextField kullaniciAdiAlani = new JTextField();
        JPasswordField sifreAlani = new JPasswordField();
        JTextField emailAlani = new JTextField();
        JTextField adAlani = new JTextField();
        JTextField soyadAlani = new JTextField();

        Object[] mesaj = {
            "Kullanıcı Adı:", kullaniciAdiAlani,
            "Şifre:", sifreAlani,
            "Email:", emailAlani,
            "Ad:", adAlani,
            "Soyad:", soyadAlani
        };

        int secenek = JOptionPane.showConfirmDialog(null, mesaj, "Kayıt Ol", JOptionPane.OK_CANCEL_OPTION);
        if (secenek == JOptionPane.OK_OPTION) {
            String kullaniciAdi = kullaniciAdiAlani.getText();
            String sifre = new String(sifreAlani.getPassword());
            String email = emailAlani.getText();
            String ad = adAlani.getText();
            String soyad = soyadAlani.getText();

            KullaniciIslemleri kullaniciIslemleri = new KullaniciIslemleri();
            boolean basarili = kullaniciIslemleri.kayitOl(kullaniciAdi, sifre, email, ad, soyad);

            if (basarili) {
                JOptionPane.showMessageDialog(null, "Kayıt başarılı!");
                girisYapildi = true; 
                kullaniciId = kullaniciIslemleri.getKullaniciId(kullaniciAdi); 
                dispose(); 
                new AnaSayfa(kullaniciId, ad, soyad).setVisible(true); 
            } else {
                JOptionPane.showMessageDialog(null, "Kayıt başarısız. Lütfen tekrar deneyin.");
            }
        }
    }

    private void girisYap() {
        JTextField kullaniciAdiAlani = new JTextField();
        JPasswordField sifreAlani = new JPasswordField();

        Object[] mesaj = {
            "Kullanıcı Adı:", kullaniciAdiAlani,
            "Şifre:", sifreAlani
        };

        int secenek = JOptionPane.showConfirmDialog(null, mesaj, "Giriş Yap", JOptionPane.OK_CANCEL_OPTION);
        if (secenek == JOptionPane.OK_OPTION) {
            String kullaniciAdi = kullaniciAdiAlani.getText();
            String sifre = new String(sifreAlani.getPassword());

            KullaniciIslemleri kullaniciIslemleri = new KullaniciIslemleri();
            boolean basarili = kullaniciIslemleri.girisYap(kullaniciAdi, sifre);

            if (basarili) {
                JOptionPane.showMessageDialog(null, "Giriş başarılı!");
                girisYapildi = true; 
                kullaniciId = kullaniciIslemleri.getKullaniciId(kullaniciAdi); 
                dispose(); 
                String[] kullaniciBilgileri = kullaniciIslemleri.kullaniciBilgileriniGetir(kullaniciAdi);
                new AnaSayfa(kullaniciId, kullaniciBilgileri[0], kullaniciBilgileri[1]).setVisible(true); 
            } else {
                JOptionPane.showMessageDialog(null, "Giriş başarısız. Lütfen tekrar deneyin.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UcusRezervasyonApp().setVisible(true);
            }
        });
    }
}
