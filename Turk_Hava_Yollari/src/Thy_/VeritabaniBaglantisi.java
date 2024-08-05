package Thy_;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VeritabaniBaglantisi {
    private static final String URL = "jdbc:mysql://localhost:3306/ucus_rezervasyon";
    private static final String KULLANICI = "root";
    private static final String SIFRE = "harun123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, KULLANICI, SIFRE);
    }
}
