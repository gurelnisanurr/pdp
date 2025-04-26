package model;

import java.util.ArrayList;
import java.util.List;

public class UzayAraci {
    private String ad;
    private String cikis;
    private String varis;
    private Zaman hareketZamani;
    private int mesafe;
    private List<Kisi> yolcular;
    private boolean yolda;
    private boolean imhaEdildi;
    private int kalanSaat;

    public UzayAraci(String ad, String cikis, String varis, Zaman hareketZamani, int mesafe) {
        this.ad = ad;
        this.cikis = cikis;
        this.varis = varis;
        this.hareketZamani = hareketZamani;
        this.mesafe = mesafe;
        this.kalanSaat = mesafe;
        this.yolcular = new ArrayList<>();
        this.yolda = false;
        this.imhaEdildi = false;
    }

    public void yolaCik() {
        yolda = true;
    }

    public void birSaatIlerle() {
        if (yolda && kalanSaat > 0) {
            kalanSaat--;
        }
    }

    public boolean hedefeVardiMi() {
        return yolda && kalanSaat == 0;
    }

    public void imhaEt() {
        imhaEdildi = true;
    }

    public boolean isYolda() {
        return yolda;
    }

    public boolean isImhaEdildi() {
        return imhaEdildi;
    }

    public void kisiEkle(Kisi k) {
        yolcular.add(k);
    }

    public List<Kisi> getYolcular() {
        return yolcular;
    }

    public String getAd() {
        return ad;
    }

    public String getCikis() {
        return cikis;
    }

    public String getVaris() {
        return varis;
    }

    public Zaman getHareketZamani() {
        return hareketZamani;
    }

    public int getKalanSaat() {
        return kalanSaat;
    }
}
