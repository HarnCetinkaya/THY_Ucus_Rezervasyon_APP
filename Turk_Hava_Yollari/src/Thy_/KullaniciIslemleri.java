package Thy_;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KullaniciIslemleri {
    public boolean kayitOl(String kullaniciAdi, String sifre, String email, String ad, String soyad) {
        
        String sorgu = "INSERT INTO kullanicilar (kullanici_adi, sifre, email, ad, soyad) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sorgu)) {
            pstmt.setString(1, kullaniciAdi);
            pstmt.setString(2, sifre);
            pstmt.setString(3, email);
            pstmt.setString(4, ad);
            pstmt.setString(5, soyad);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean girisYap(String kullaniciAdi, String sifre) {
        
        String sorgu = "SELECT * FROM kullanicilar WHERE kullanici_adi = ? AND sifre = ?";
        try (Connection conn = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sorgu)) {
            pstmt.setString(1, kullaniciAdi);
            pstmt.setString(2, sifre);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getKullaniciId(String kullaniciAdi) {
        
        String sorgu = "SELECT kullanici_id FROM kullanicilar WHERE kullanici_adi = ?";
        try (Connection conn = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sorgu)) {
            pstmt.setString(1, kullaniciAdi);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("kullanici_id");
            } else {
                throw new SQLException("Kullanıcı bulunamadı");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String[] kullaniciBilgileriniGetir(String kullaniciAdi) {
        
        String sorgu = "SELECT ad, soyad FROM kullanicilar WHERE kullanici_adi = ?";
        try (Connection conn = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sorgu)) {
            pstmt.setString(1, kullaniciAdi);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new String[]{rs.getString("ad"), rs.getString("soyad")};
            } else {
                throw new SQLException("Kullanıcı bulunamadı");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
