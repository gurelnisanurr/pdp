package model;

public class Gezegen {
    private String ad;
    private int saatlikUzunluk;
    private Zaman zaman;
    private int nufus;

    public Gezegen(String ad, int saatlikUzunluk, Zaman zaman, int nufus) {
        this.ad = ad;
        this.saatlikUzunluk = saatlikUzunluk;
        this.zaman = zaman;
        this.nufus = nufus;
    }

    public void saatIlerle() {
        zaman.birSaatIlerle();
    }

    public String getAd() {
        return ad;
    }

    public int getNufus() {
        return nufus;
    }

    public void nufusArttir(int sayi) {
        nufus += sayi;
    }

    public void nufusAzalt(int sayi) {
        nufus -= sayi;
    }

    public Zaman getZaman() {
        return zaman;
    }
}
