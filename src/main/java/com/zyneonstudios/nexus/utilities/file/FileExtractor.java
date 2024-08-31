package com.zyneonstudios.nexus.utilities.file;

import com.zyneonstudios.nexus.utilities.NexusUtilities;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileExtractor {

    @SuppressWarnings("all")
    public static boolean unzipFile(String filePath, String outputPathString) {
        Path outputPath = Paths.get(outputPathString);
        try (ZipArchiveInputStream zipInput = new ZipArchiveInputStream(new FileInputStream(filePath))) {
            ArchiveEntry entry;
            while ((entry = zipInput.getNextEntry()) != null) {
                Path path = Paths.get(outputPath.toString(), entry.getName());
                if (entry.isDirectory()) {
                    path.toFile().mkdirs();
                } else {
                    try (FileOutputStream outputStream = new FileOutputStream(path.toFile())) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipInput.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            NexusUtilities.getLogger().err("[UTILITIES] (FileExtractor) Couldn't unzip file: "+e.getMessage());
            return false;
        }
    }

    @SuppressWarnings("all")
    public static void extractResourceFile(String sourceFileName, String destinationFileName, Class class_) {
        new File(new File(destinationFileName).getParent()).mkdirs();
        try {
            ClassLoader classLoader = class_.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(sourceFileName);
            if (inputStream == null) {
                return;
            }
            OutputStream outputStream = new FileOutputStream(destinationFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            NexusUtilities.getLogger().err("[UTILITIES] (FileExtractor) Couldn't extract file(s) from resources: "+e.getMessage());
        }
    }
}
