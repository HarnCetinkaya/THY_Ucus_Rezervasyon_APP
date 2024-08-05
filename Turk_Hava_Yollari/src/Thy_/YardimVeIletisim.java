package Thy_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YardimVeIletisim extends JFrame {
    private int kullaniciId;
    private String ad;
    private String soyad;

    public YardimVeIletisim(int kullaniciId, String ad, String soyad) {
        this.kullaniciId = kullaniciId;
        this.ad = ad;
        this.soyad = soyad;

        setTitle("Yardım ve İletişim");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel baslikLabel = new JLabel("Yardım ve İletişim", SwingConstants.CENTER);
        baslikLabel.setFont(new Font("Arial", Font.BOLD, 24));
        baslikLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(baslikLabel);

        JTextArea yardimMetni = new JTextArea();
        yardimMetni.setText("Herhangi bir sorun veya soru için bize ulaşabilirsiniz.\n\n" +
                "Telefon: +90 551 105 99 71\n" +
                "Email: destek@thy.com\n\n" +
                "Müşteri hizmetleri temsilcimiz size yardımcı olmaktan memnuniyet duyacaktır.");
        yardimMetni.setEditable(false);
        yardimMetni.setFont(new Font("Arial", Font.PLAIN, 16));
        yardimMetni.setBackground(panel.getBackground());
        yardimMetni.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(yardimMetni);

        JTextArea mesajAlani = new JTextArea(5, 30);
        mesajAlani.setLineWrap(true);
        mesajAlani.setWrapStyleWord(true);
        mesajAlani.setMaximumSize(new Dimension(Integer.MAX_VALUE, mesajAlani.getPreferredSize().height));
        panel.add(new JScrollPane(mesajAlani));

        JButton mesajGonderButonu = new JButton("Mesaj Gönder");
        mesajGonderButonu.setAlignmentX(Component.CENTER_ALIGNMENT);
        mesajGonderButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mesaj = mesajAlani.getText();
                if (!mesaj.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mesajınız başarıyla gönderildi.");
                    mesajAlani.setText(""); 
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen bir mesaj yazın.");
                }
            }
        });
        panel.add(mesajGonderButonu);

        JButton anaSayfayaDonButonu = new JButton("Ana Sayfaya Dön");
        anaSayfayaDonButonu.setAlignmentX(Component.CENTER_ALIGNMENT);
        anaSayfayaDonButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AnaSayfa(kullaniciId, ad, soyad).setVisible(true);
                dispose();
            }
        });
        panel.add(anaSayfayaDonButonu);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new YardimVeIletisim(1, "Ad", "Soyad").setVisible(true); 
        });
    }
}
