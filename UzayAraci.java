package model;

public class UzayAraci {
    public String adi;
    private String cikisGezegeni;
    private String varisGezegeni;
    private String tipi;
    private int mesafeSaat;
    private boolean hareketEtti;

    public UzayAraci(String adi, String cikisGezegeni, String varisGezegeni, String tipi, int mesafeSaat) {
        this.adi = adi;
        this.cikisGezegeni = cikisGezegeni;
        this.varisGezegeni = varisGezegeni;
        this.tipi = tipi;
        this.mesafeSaat = mesafeSaat;
        this.hareketEtti = false;
    }

    public boolean hareketeGec(Zaman zaman) {
        if (!hareketEtti && zaman.getTarih().contains("2025")) {
            hareketEtti = true;
            return true;
        }
        return false;
    }

    public void azaltMesafe(int miktar) {
        this.mesafeSaat -= miktar;
    }

    public int getMesafeSaat() {
        return mesafeSaat;
    }

    public boolean isHareketEtti() {
        return hareketEtti;
    }

    public String getCikisGezegeni() {
        return cikisGezegeni;
    }
    
    public String getAdi() {
        return adi;
    }

    @Override
    public String toString() {
        return "\uD83D\uDE80 " + adi + " (Çıkış: " + cikisGezegeni + ", Varış: " + varisGezegeni + ", Mesafe: " + mesafeSaat + " saat)";
    }

}

