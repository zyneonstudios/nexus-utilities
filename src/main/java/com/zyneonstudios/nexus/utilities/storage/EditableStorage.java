package com.zyneonstudios.nexus.utilities.storage;

public interface EditableStorage extends Storage {

    boolean set(String key, Object value);
    boolean ensure(String key, Object defaultValue);
    boolean delete(String key);
}