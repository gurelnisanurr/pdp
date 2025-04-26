package model;

public class Kisi {
    private String ad;
    private int yas;
    private int omur;
    private String aracAdi;

    public Kisi(String ad, int yas, int omur, String aracAdi) {
        this.ad = ad;
        this.yas = yas;
        this.omur = omur;
        this.aracAdi = aracAdi;
    }

    public String getAd() {
        return ad;
    }

    public int getYas() {
        return yas;
    }

    public int getOmur() {
        return omur;
    }

    public void yaslandir() {
        yas++;
    }

    public void omurAzalt() {
        omur--;
    }

    public boolean olduMu() {
        return omur <= 0;
    }

    public String getAracAdi() {
        return aracAdi;
    }

    public void setAracAdi(String aracAdi) {
        this.aracAdi = aracAdi;
    }
}
