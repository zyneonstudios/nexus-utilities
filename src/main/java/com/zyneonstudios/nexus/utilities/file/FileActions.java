package com.zyneonstudios.nexus.utilities.file;

import java.io.File;

public class FileActions {

    @SuppressWarnings("all")
    public static boolean deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        return folder.delete();
    }
}
