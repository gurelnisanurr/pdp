package main;

import utils.DosyaOkuma;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulasyon {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\uD83D\uDE80 Uzay Simülasyonu Başlatılıyor...\n");

        // Dosyalardan verileri oku
        List<String> kisilerVeri = DosyaOkuma.dosyaOku("Kisiler.txt");
        List<String> araclarVeri = DosyaOkuma.dosyaOku("Araclar.txt");
        List<String> gezegenlerVeri = DosyaOkuma.dosyaOku("Gezegenler.txt");

        if (kisilerVeri.isEmpty() || araclarVeri.isEmpty() || gezegenlerVeri.isEmpty()) {
            System.out.println("❌ Hata: Gerekli veriler yüklenemedi. Lütfen dosyaları kontrol edin.");
            return;
        }
        
        System.out.println("✅ Veriler başarıyla yüklendi!\n");

        // Nesne listelerini oluştur
        List<Kisi> kisiler = kisiListesiOlustur(kisilerVeri);
        List<UzayAraci> araclar = uzayAraciListesiOlustur(araclarVeri);
        List<Gezegen> gezegenler = gezegenListesiOlustur(gezegenlerVeri);

        List<Zaman> gezegenZamanlari = new ArrayList<>();
        for (Gezegen gezegen : gezegenler) {
            gezegenZamanlari.add(new Zaman(gezegen.getTarih()));
        }

        System.out.println("⏳ Simülasyon başlatıldı. Zaman ilerliyor...\n");

        int saatIlerlemesi = 1;  // HER DÖNGÜ 1 SAAT ARTACAK
        int simülasyonSaati = 0;
        while (!araclar.isEmpty() && simülasyonSaati < 5000) {
            for (int i = 0; i < gezegenler.size(); i++) {
                gezegenZamanlari.get(i).saatEkle(saatIlerlemesi, gezegenler.get(i).getGunlukSaatSayisi());
            }

            kisiler.removeIf(kisi -> {
                kisi.azaltOmur(1);
                return kisi.getKalanOmur() <= 0;
            });

            List<UzayAraci> hareketEdecekAraclar = new ArrayList<>();
            for (UzayAraci arac : araclar) {
                gezegenler.stream()
                    .filter(gezegen -> gezegen.getAdi().equals(arac.getCikisGezegeni()))
                    .findFirst()
                    .ifPresent(gezegen -> {
                        if (arac.hareketeGec(gezegenZamanlari.get(gezegenler.indexOf(gezegen)))) {
                            hareketEdecekAraclar.add(arac);
                            System.out.println("\uD83D\uDE80 " + arac.getAdi() + " ÇIKIŞ YAPTI!");
                        } else if (arac.getMesafeSaat() > 0 && arac.isHareketEtti()) {
                            arac.azaltMesafe(1);
                            System.out.println("\uD83D\uDE80 " + arac.getAdi() + " ilerledi! Kalan mesafe: " + arac.getMesafeSaat());
                        }
                    });
            }
            
            araclar.removeIf(arac -> {
                boolean imha = kisiler.stream().noneMatch(k -> k.getUzayAraciAdi().equals(arac.getAdi()));
                if (imha) System.out.println("\uD83D\uDCA5 " + arac.getAdi() + " İMHA OLDU!");
                return imha;
            });
            
            temizleKonsol();
            System.out.println("\n⏳ Simülasyon Zamanı: " + simülasyonSaati + " saat geçti");
            kisiler.forEach(System.out::println);
            araclar.forEach(System.out::println);
            gezegenler.forEach(gezegen -> {
                System.out.println(gezegen.getAdi() + " → " + gezegenZamanlari.get(gezegenler.indexOf(gezegen)).getTarih());
            });
            
            simülasyonSaati += saatIlerlemesi;
            Thread.sleep(1000);
        }
        System.out.println("\n✅ Simülasyon sona erdi.");
    }

    private static List<Kisi> kisiListesiOlustur(List<String> veri) {
        List<Kisi> kisiler = new ArrayList<>();
        for (String satir : veri) {
            String[] parcalar = satir.split("#");
            if (parcalar.length == 4) {
                kisiler.add(new Kisi(parcalar[0], Integer.parseInt(parcalar[1]), Integer.parseInt(parcalar[2]), parcalar[3]));
            }
        }
        return kisiler;
    }

    private static List<UzayAraci> uzayAraciListesiOlustur(List<String> veri) {
        List<UzayAraci> araclar = new ArrayList<>();
        for (String satir : veri) {
            String[] parcalar = satir.split("#");
            if (parcalar.length == 5) {
                araclar.add(new UzayAraci(parcalar[0], parcalar[1], parcalar[2], parcalar[3], Integer.parseInt(parcalar[4])));
            }
        }
        return araclar;
    }

    private static List<Gezegen> gezegenListesiOlustur(List<String> veri) {
        List<Gezegen> gezegenler = new ArrayList<>();
        for (String satir : veri) {
            String[] parcalar = satir.split("#");
            if (parcalar.length == 3) {
                gezegenler.add(new Gezegen(parcalar[0], Integer.parseInt(parcalar[1]), parcalar[2]));
            }
        }
        return gezegenler;
    }

    public static void temizleKonsol() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
