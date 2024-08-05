package Thy_;

public class Ucus {
    private int id;
    private String kalkisYeri;
    private String varisYeri;
    private String kalkisZamani;
    private String varisZamani;
    private int mevcutKoltuk;
    private String ucusTuru;
    private String ucusTipi;
    private double fiyat;

    public Ucus(int id, String kalkisYeri, String varisYeri, String kalkisZamani, String varisZamani, int mevcutKoltuk, String ucusTuru, String ucusTipi, double fiyat) {
        this.id = id;
        this.kalkisYeri = kalkisYeri;
        this.varisYeri = varisYeri;
        this.kalkisZamani = kalkisZamani;
        this.varisZamani = varisZamani;
        this.mevcutKoltuk = mevcutKoltuk;
        this.ucusTuru = ucusTuru;
        this.ucusTipi = ucusTipi;
        this.fiyat = fiyat;
    }

    // Default constructor
    public Ucus() {}

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKalkisYeri() {
        return kalkisYeri;
    }

    public void setKalkisYeri(String kalkisYeri) {
        this.kalkisYeri = kalkisYeri;
    }

    public String getVarisYeri() {
        return varisYeri;
    }

    public void setVarisYeri(String varisYeri) {
        this.varisYeri = varisYeri;
    }

    public String getKalkisZamani() {
        return kalkisZamani;
    }

    public void setKalkisZamani(String kalkisZamani) {
        this.kalkisZamani = kalkisZamani;
    }

    public String getVarisZamani() {
        return varisZamani;
    }

    public void setVarisZamani(String varisZamani) {
        this.varisZamani = varisZamani;
    }

    public int getMevcutKoltuk() {
        return mevcutKoltuk;
    }

    public void setMevcutKoltuk(int mevcutKoltuk) {
        this.mevcutKoltuk = mevcutKoltuk;
    }

    public String getUcusTuru() {
        return ucusTuru;
    }

    public void setUcusTuru(String ucusTuru) {
        this.ucusTuru = ucusTuru;
    }

    public String getUcusTipi() {
        return ucusTipi;
    }

    public void setUcusTipi(String ucusTipi) {
        this.ucusTipi = ucusTipi;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    @Override
    public String toString() {
        return "Uçuş{" +
                "id=" + id +
                ", kalkış yeri='" + kalkisYeri + '\'' +
                ", varış yeri='" + varisYeri + '\'' +
                ", kalkış zamanı='" + kalkisZamani + '\'' +
                ", varış zamanı='" + varisZamani + '\'' +
                ", mevcut koltuk=" + mevcutKoltuk +
                ", uçuş türü='" + ucusTuru + '\'' +
                ", uçuş tipi='" + ucusTipi + '\'' +
                ", fiyat=" + fiyat +
                '}';
    }
}
