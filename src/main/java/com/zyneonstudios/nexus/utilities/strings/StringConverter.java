package com.zyneonstudios.nexus.utilities.strings;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.nio.file.Path;

public class StringConverter {

    public static String getURLString(String input) {
        if(input.startsWith("file://")) {
            return input.replace("\\","/");
        }
        return "file://" + input.replace("\\","/");
    }

    public static String getURLString(Path path) {
        return getURLString(path.toString());
    }

    public static String getURLString(File file) {
        return getURLString(file.getAbsolutePath());
    }

    public static String getUUIDString(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.insert(8, "-");
        sb.insert(13, "-");
        sb.insert(18, "-");
        sb.insert(23, "-");
        return sb.toString();
    }

    public static String toBase64(String input) {
        return Base64.encodeBase64String(input.getBytes());
    }

    public static String fromBase64(String input) {
        return new String(Base64.decodeBase64(input));
    }
}