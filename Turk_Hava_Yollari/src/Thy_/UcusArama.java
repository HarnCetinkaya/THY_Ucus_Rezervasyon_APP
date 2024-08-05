package Thy_;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UcusArama {
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
                Ucus ucus = new Ucus(
                    rs.getInt("ucus_id"),
                    rs.getString("kalkis_yeri"),
                    rs.getString("varis_yeri"),
                    rs.getString("kalkis_zamani"),
                    rs.getString("varis_zamani"),
                    rs.getInt("mevcut_koltuk"),
                    rs.getString("ucus_turu"),
                    rs.getString("ucus_tipi"),
                    rs.getDouble("fiyat")
                );
                ucuslar.add(ucus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ucuslar;
    }
}
