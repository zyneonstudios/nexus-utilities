package com.zyneonstudios.nexus.utilities.storage;

import java.util.HashMap;

public class LocalStorage implements EditableStorage {

    private final HashMap<String, Object> storage = new HashMap<>();

    @Override
    public boolean has(String path) {
        return get(path) != null;
    }

    @Override
    public Object get(String path) {
        if (storage.containsKey(path)) {
            return storage.get(path);
        }
        return null;
    }

    @Override
    public String getString(String path) {
        if(storage.containsKey(path)) {
            return storage.get(path).toString();
        }
        return null;
    }

    @Override
    public Integer getInteger(String path) {
        if(storage.containsKey(path)) {
            return (Integer)storage.get(path);
        }
        return null;
    }

    @Override
    public int getInt(String path) {
        if(storage.containsKey(path)) {
            return (int)storage.get(path);
        }
        return -1;
    }

    @Override
    public Double getDouble(String path) {
        if(storage.containsKey(path)) {
            return (Double)storage.get(path);
        }
        return null;
    }

    @Override
    public double getDoub(String path) {
        if(storage.containsKey(path)) {
            return (double)storage.get(path);
        }
        return -1;
    }

    @Override
    public Boolean getBoolean(String path) {
        if(storage.containsKey(path)) {
            return (Boolean)storage.get(path);
        }
        return null;
    }

    @Override
    public boolean getBool(String path) {
        if(storage.containsKey(path)) {
            return (boolean)storage.get(path);
        }
        return false;
    }

    @Override
    public boolean ensure(String key, Object defaultValue) {
        if(get(key) == null) {
            return set(key, defaultValue);
        }
        return true;
    }

    @Override
    public boolean set(String path, Object content) {
        try {
            storage.remove(path);
            storage.put(path,content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String path) {
        try {
            storage.remove(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}