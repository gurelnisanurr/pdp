package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Zaman implements Comparable<Zaman> {
    private LocalDateTime tarih;
    private int saat;

    public Zaman(String tarihStr, int saat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        this.tarih = LocalDateTime.parse(tarihStr + " 00:00", DateTimeFormatter.ofPattern("d.M.yyyy HH:mm"));
        this.tarih = this.tarih.plusHours(saat);
    }

    public void birSaatIlerle() {
        this.tarih = this.tarih.plusHours(1);
    }

    public String getTarihStr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        return tarih.format(formatter);
    }

    public int getSaat() {
        return tarih.getHour();
    }

    public LocalDateTime getTarih() {
        return tarih;
    }

    @Override
    public int compareTo(Zaman other) {
        return this.tarih.compareTo(other.tarih);
    }

    @Override
    public String toString() {
        return getTarihStr() + " " + getSaat() + ". saat";
    }
}
