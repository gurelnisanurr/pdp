package model;

import java.util.ArrayList;
import java.util.List;

public class UzayAraci {
    private String adi;
    private String cikisGezegeni;
    private String varisGezegeni;
    private String cikisTarihi;
    private int mesafeSaat;
    private boolean hareketEtti;
    private List<Kisi> yolcular;

    public UzayAraci(String adi, String cikisGezegeni, String varisGezegeni, String cikisTarihi, int mesafeSaat) {
        this.adi = adi;
        this.cikisGezegeni = cikisGezegeni;
        this.varisGezegeni = varisGezegeni;
        this.cikisTarihi = cikisTarihi;
        this.mesafeSaat = mesafeSaat;
        this.hareketEtti = false;
        this.yolcular = new ArrayList<>();
    }

    public String getAdi() {
        return adi;
    }

    public String getCikisGezegeni() {
        return cikisGezegeni;
    }

    public String getVarisGezegeni() {
        return varisGezegeni;
    }

    public String getCikisTarihi() {
        return cikisTarihi;
    }

    public int getMesafeSaat() {
        return mesafeSaat;
    }

    public boolean isHareketEtti() {
        return hareketEtti;
    }

    public void setHareketEtti(boolean hareketEtti) {
        this.hareketEtti = hareketEtti;
    }

    public void azaltMesafe(int saat) {
        if (mesafeSaat > 0) {
            mesafeSaat -= saat;
        }
    }

    public boolean hareketeGec(Zaman gezegenZamani) {
        if (!hareketEtti && gezegenZamani.getTarih().equals(cikisTarihi)) {
            hareketEtti = true;
            return true;
        }
        return false;
    }

    public void yolcuEkle(Kisi kisi) {
        yolcular.add(kisi);
    }

    public boolean isImha() {
        return yolcular.stream().allMatch(kisi -> kisi.getKalanOmur() <= 0);
    }

    @Override
    public String toString() {
        return String.format("%s (Çıkış: %s, Varış: %s, Mesafe: %d, Yolcu Sayısı: %d)", 
            adi, cikisGezegeni, varisGezegeni, mesafeSaat, yolcular.size());
    }
}
