package utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
import java.util.Collections;

public class DosyaOkuma {
    public static List<String> dosyaOku(String dosyaAdi) {
        try {
            return Files.readAllLines(Paths.get(dosyaAdi));
        } catch (IOException e) {
            System.out.println("❌ Dosya okunamadı: " + dosyaAdi);
            return Collections.emptyList();
        }
    }
}
