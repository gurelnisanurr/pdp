package utils;

import model.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DosyaOkuma {
    public static List<Kisi> kisileriOku(String dosyaYolu) throws IOException {
        List<Kisi> Kisiler = new ArrayList<>();
        for (String satir : Files.readAllLines(Paths.get(dosyaYolu))) {
            String[] parca = satir.split("#");
            Kisiler.add(new Kisi(parca[0], Integer.parseInt(parca[1]), Integer.parseInt(parca[2]), parca[3]));
        }
        return Kisiler;
    }

    public static Map<String, Gezegen> gezegenleriOku(String dosyaYolu) throws IOException {
        Map<String, Gezegen> Gezegenler = new HashMap<>();

        for (String satir : Files.readAllLines(Paths.get(dosyaYolu))) {
            String[] parca = satir.split("#");
            String ad = parca[0];
            int saatlik = Integer.parseInt(parca[1]);
            String tarih = parca[2];
            int nufus = Integer.parseInt(parca[3]);

            Gezegenler.put(ad, new Gezegen(ad, saatlik, new Zaman(tarih, 0), nufus));
        }

        return Gezegenler;
    }

    public static List<UzayAraci> araclariOku(String dosyaYolu) throws IOException {
        List<UzayAraci> Araclar = new ArrayList<>();
        for (String satir : Files.readAllLines(Paths.get(dosyaYolu))) {
            String[] parca = satir.split("#");
            String ad = parca[0];
            String cikis = parca[1];
            String varis = parca[2];
            String tarih = parca[3];
            int mesafe = Integer.parseInt(parca[4]);
            Araclar.add(new UzayAraci(ad, cikis, varis, new Zaman(tarih, 0), mesafe));
        }
        return Araclar;
    }
}
