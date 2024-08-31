package com.zyneonstudios.nexus.utilities.file;

import com.zyneonstudios.nexus.utilities.NexusUtilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileGetter {

    @SuppressWarnings("all")
    public static File downloadFile(String urlString, String path) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                File outputFile = new File(path);
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
                return outputFile;
            }
        } catch (Exception e) {
            e.printStackTrace();
            NexusUtilities.getLogger().err("[UTILITIES] (FileGetter) Couldn't download file: "+e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("all")
    public static File getResourceFile(String resourceString, Class class_) {
        try {
            return new File(class_.getClassLoader().getResource(resourceString).getFile());
        } catch (Exception e) {
            e.printStackTrace();
            NexusUtilities.getLogger().err("[UTILITIES] (FileGetter) Couldn't get file from resources: "+e.getMessage());
            return null;
        }
    }
}
