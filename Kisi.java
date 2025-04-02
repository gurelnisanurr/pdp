package model;

public class Kisi {
    private String isim;
    private int yas;
    private int kalanOmur;
    private String uzayAraciAdi;

    public Kisi(String isim, int yas, int kalanOmur, String uzayAraciAdi) {
        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.uzayAraciAdi = uzayAraciAdi;
    }

    public void azaltOmur(int miktar) {
        this.kalanOmur -= miktar;
    }

    public int getKalanOmur() {
        return kalanOmur;
    }

    public String getUzayAraciAdi() {
        return uzayAraciAdi;
    }

    @Override
    public String toString() {
        return isim + " (Yaş: " + yas + ", Kalan Ömür: " + kalanOmur + ", Araç: " + uzayAraciAdi + ")";
    }
}
