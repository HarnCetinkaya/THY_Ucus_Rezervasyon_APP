package Thy_;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UcusIslemleri {
    public boolean ucusEkle(String kalkisYeri, String varisYeri, String kalkisZamani, String varisZamani, int mevcutKoltuk, String ucusTuru, String ucusTipi, double fiyat) {
        String sorgu = "INSERT INTO Ucuslar (kalkis_yeri, varis_yeri, kalkis_zamani, varis_zamani, mevcut_koltuk, ucus_turu, ucus_tipi, fiyat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sorgu)) {
            pstmt.setString(1, kalkisYeri);
            pstmt.setString(2, varisYeri);
            pstmt.setString(3, kalkisZamani);
            pstmt.setString(4, varisZamani);
            pstmt.setInt(5, mevcutKoltuk);
            pstmt.setString(6, ucusTuru);
            pstmt.setString(7, ucusTipi);
            pstmt.setDouble(8, fiyat);

            int etkilenenSatir = pstmt.executeUpdate();
            return etkilenenSatir > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ucus> ucuslariAra(String kalkis, String varis, String tarih, String ucusTipi) {
        List<Ucus> ucuslar = new ArrayList<>();
        String sorgu = "SELECT * FROM Ucuslar WHERE kalkis_yeri = ? AND varis_yeri = ? AND kalkis_zamani LIKE ? AND ucus_tipi = ?";

        try (Connection conn = VeritabaniBaglantisi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sorgu)) {
            pstmt.setString(1, kalkis);
            pstmt.setString(2, varis);
            pstmt.setString(3, tarih + "%");
            pstmt.setString(4, ucusTipi);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Ucus ucus = new Ucus();
                ucus.setId(rs.getInt("ucus_id"));
                ucus.setKalkisYeri(rs.getString("kalkis_yeri"));
                ucus.setVarisYeri(rs.getString("varis_yeri"));
                ucus.setKalkisZamani(rs.getString("kalkis_zamani"));
                ucus.setVarisZamani(rs.getString("varis_zamani"));
                ucus.setMevcutKoltuk(rs.getInt("mevcut_koltuk"));
                ucus.setUcusTuru(rs.getString("ucus_turu"));
                ucus.setUcusTipi(rs.getString("ucus_tipi"));
                ucus.setFiyat(rs.getDouble("fiyat"));
                ucuslar.add(ucus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ucuslar;
    }
}
