package model;

public class Gezegen {
    private String adi;
    private int gunlukSaatSayisi;
    private String tarih;

    public Gezegen(String adi, int gunlukSaatSayisi, String tarih) {
        this.adi = adi;
        this.gunlukSaatSayisi = gunlukSaatSayisi;
        this.tarih = tarih;
    }

    public int getGunlukSaatSayisi() {
        return gunlukSaatSayisi;
    }

    public String getAdi() {
        return adi;
    }

    public String getTarih() {
        return tarih;
    }
}
