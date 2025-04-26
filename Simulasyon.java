package main;

import model.*;
import utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class Simulasyon {
    public static void main(String[] args) throws Exception {
        List<Kisi> kisiler = DosyaOkuma.kisileriOku("veri/Kisiler.txt");
        Map<String, Gezegen> gezegenler = DosyaOkuma.gezegenleriOku("veri/Gezegenler.txt");
        List<UzayAraci> araclar = DosyaOkuma.araclariOku("veri/Araclar.txt");

        Map<String, UzayAraci> aracMap = araclar.stream().collect(Collectors.toMap(UzayAraci::getAd, a -> a));
        for (Kisi k : kisiler) {
            UzayAraci arac = aracMap.get(k.getAracAdi());
            if (arac != null) arac.kisiEkle(k);
        }

        for (int saat = 0; saat < 9999; saat++) {
            EkranTemizleyici.temizle();
            System.out.println("=== SAAT: " + saat + " ===");

            // Gezegen zamanı ilerletme
            for (Gezegen g : gezegenler.values()) {
                g.saatIlerle();
            }

            // Araç işlemleri
            for (UzayAraci a : araclar) {
                Gezegen cikisG = gezegenler.get(a.getCikis());
                if (cikisG == null) {
                    System.err.println("UYARI: " + a.getCikis() + " gezegeni bulunamadı. Araç atlandı: " + a.getAd());
                    continue;
                }

                if (!a.isYolda() && !a.isImhaEdildi() && cikisG.getZaman().compareTo(a.getHareketZamani()) >= 0) {
                    a.yolaCik();
                    cikisG.nufusAzalt(a.getYolcular().size());
                }

                if (a.isYolda()) {
                    a.birSaatIlerle();
                    for (Kisi k : a.getYolcular()) k.omurAzalt();
                    a.getYolcular().removeIf(Kisi::olduMu);
                    if (a.getYolcular().isEmpty()) a.imhaEt();
                    else if (a.hedefeVardiMi()) {
                        Gezegen varisG = gezegenler.get(a.getVaris());
                        if (varisG != null)
                            varisG.nufusArttir(a.getYolcular().size());
                    }
                }
            }

            // Çıktı
            System.out.println("\nGezegenler:");

         // Gezegen adları
         for (Gezegen g : gezegenler.values()) {
             System.out.printf("%-8s", g.getAd());
         }
         System.out.println();

         // Gezegen tarihleri
         for (Gezegen g : gezegenler.values()) {
             System.out.printf("%-8s", g.getZaman());
         }
         System.out.println();

         // Gezegen nüfusları
         for (Gezegen g : gezegenler.values()) {
             System.out.printf("%-8d", g.getNufus());
         }
            System.out.println();

            System.out.println("\nUzay Araçları:");
            for (UzayAraci a : araclar) {
                String durum = a.isImhaEdildi() ? "IMHA" : a.hedefeVardiMi() ? "VARDI" : a.isYolda() ? "YOLDA" : "BEKLEME";
                String kalan = a.isYolda() ? String.valueOf(a.getKalanSaat()) : "--";
                System.out.printf("%-5s %-8s %-12s\n", a.getAd(), durum, kalan);
            }

            Thread.sleep(100);
        }
    }
}
