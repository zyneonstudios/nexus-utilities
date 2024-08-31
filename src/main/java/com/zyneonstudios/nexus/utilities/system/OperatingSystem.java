package com.zyneonstudios.nexus.utilities.system;

import org.apache.commons.lang3.SystemUtils;

public class OperatingSystem {

    private static Type type = null;
    private static Architecture architecture = null;
    private static final String name = System.getProperty("os.name");
    private static final String version = System.getProperty("os.version");

    public static Type getType() {
        if(type==null) {
            if(SystemUtils.IS_OS_LINUX) {
                type = Type.Linux;
            } else if(SystemUtils.IS_OS_MAC) {
                type = Type.macOS;
            } else if(SystemUtils.IS_OS_WINDOWS) {
                type = Type.Windows;
            } else {
                type = Type.unknown;
            }
            System.gc();
        }
        return type;
    }

    public static Architecture getArchitecture() {
        if(architecture==null) {
            String os = System.getProperty("os.arch").toLowerCase();
            String dm = System.getProperty("sun.arch.data.model");
            if(os.contains("arm")||os.contains("aarch")) {
                if(dm.equals("64")) {
                    architecture = Architecture.arm64;
                } else {
                    architecture = Architecture.arm32;
                }
            } else {
                if(dm.equals("64")) {
                    architecture = Architecture.x64;
                } else {
                    architecture = Architecture.x86;
                }
            }
            System.gc();
        }
        return architecture;
    }

    public static String getName() {
        return name;
    }

    public static String getVersion() {
        return version;
    }

    public enum Type {
        Linux,
        macOS,
        Windows,
        unknown
    }

    public enum Architecture {
        arm32,
        arm64,
        x64,
        x86
    }
}