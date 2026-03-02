package utils;

import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    public static void zipReport(String sourceDir, String zipFilePath) {
        try (ZipOutputStream zos = new ZipOutputStream(
                new FileOutputStream(zipFilePath))) {

            Path sourcePath = Paths.get(sourceDir);

            Files.walk(sourcePath)
                 .filter(path -> !Files.isDirectory(path))
                 .forEach(path -> {
                     ZipEntry zipEntry =
                             new ZipEntry(sourcePath.relativize(path).toString());
                     try {
                         zos.putNextEntry(zipEntry);
                         Files.copy(path, zos);
                         zos.closeEntry();
                     } catch (IOException e) {
                         throw new RuntimeException(e);
                     }
                 });

        } catch (IOException e) {
            throw new RuntimeException("Failed to zip report", e);
        }
    }
}
