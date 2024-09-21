package com.zyneonstudios.nexus.utilities.storage;

public interface Storage {

    Object get(String path);

    boolean has(String path);

    String getString(String path);

    Integer getInteger(String path);

    int getInt(String path);

    Double getDouble(String path);

    double getDoub(String path);

    Boolean getBoolean(String path);

    boolean getBool(String path);
}