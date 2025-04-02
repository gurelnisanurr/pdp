package model;

public class Zaman {
    private String tarih;

    public Zaman(String tarih) {
        this.tarih = tarih;
    }

    public void saatEkle(int saat, int gunlukSaat) {
        int gunEkle = saat / gunlukSaat;
        tarih = "Gün: " + gunEkle;
    }

    public String getTarih() {
        return tarih;
    }
}

