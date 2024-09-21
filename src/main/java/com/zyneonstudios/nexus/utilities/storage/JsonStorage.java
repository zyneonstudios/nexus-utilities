package com.zyneonstudios.nexus.utilities.storage;

import com.google.gson.*;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class JsonStorage implements Storage {

    private Gson gson;
    private File jsonFile;
    private String path;

    public JsonStorage(File file) {
        init(file);
    }

    public JsonStorage(String path) {
        File file = new File(path);
        init(file);
    }

    private void createEmptyJsonFile() {
        try (FileWriter writer = new FileWriter(jsonFile)) {
            JsonObject rootNode = new JsonObject();
            writer.write(gson.toJson(rootNode));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void init(File file) {
        new File(file.getParent()).mkdirs();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.jsonFile = file;
        if (!jsonFile.exists()) {
            createEmptyJsonFile();
        }
        this.path = URLDecoder.decode(file.getAbsolutePath(), StandardCharsets.UTF_8);
    }

    public File getJsonFile() {
        return jsonFile;
    }

    public String getPath() {
        return path;
    }

    public Gson getGson() {
        return gson;
    }

    @Override
    public String getString(String path) {
        if (get(path) != null) {
            return get(path).toString();
        }
        return null;
    }

    @Override
    public int getInt(String path) {
        return getInteger(path);
    }

    @Override
    public Integer getInteger(String path) {
        if (get(path) != null) {
            try {
                return (int)(double)getDouble(path);
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    @Override
    public double getDoub(String path) {
        return getDouble(path);
    }

    @Override
    public Double getDouble(String path) {
        if (get(path) != null) {
            try {
                return (Double) get(path);
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    @Override
    public boolean getBool(String path) {
        return getBoolean(path);
    }

    @Override
    public Boolean getBoolean(String path) {
        if (get(path) != null) {
            try {
                return (Boolean) get(path);
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    public boolean ensure(String path, Object value) {
        if (get(path) == null) {
            set(path, value);
            return false;
        }
        return true;
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean set(String path, Object value) {
        try {
            JsonObject rootNode = JsonParser.parseReader(jsonFileReader()).getAsJsonObject();
            String[] parts = path.split("\\.");
            JsonObject currentNode = rootNode;

            for (int i = 0; i < parts.length - 1; i++) {
                if (!currentNode.has(parts[i]) || !currentNode.get(parts[i]).isJsonObject()) {
                    currentNode.add(parts[i], new JsonObject());
                }
                currentNode = currentNode.getAsJsonObject(parts[i]);
            }

            JsonElement valueElement = gson.toJsonTree(value);
            currentNode.add(parts[parts.length - 1], valueElement);

            try (FileWriter writer = new FileWriter(jsonFile)) {
                writer.write(gson.toJson(rootNode));
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Object get(String path) {
        try {
            JsonObject rootNode = JsonParser.parseReader(jsonFileReader()).getAsJsonObject();
            String[] parts = path.split("\\.");
            JsonElement currentNode = rootNode;

            for (String part : parts) {
                if (!currentNode.isJsonObject() || !currentNode.getAsJsonObject().has(part)) {
                    return null;
                }
                currentNode = currentNode.getAsJsonObject().get(part);
            }

            return gson.fromJson(currentNode, Object.class);
        } catch (IOException e) {
            return null;
        }
    }

    public boolean delete(String path) {
        try {
            JsonObject rootNode = JsonParser.parseReader(jsonFileReader()).getAsJsonObject();
            String[] parts = path.split("\\.");
            JsonObject currentNode = rootNode;

            for (int i = 0; i < parts.length - 1; i++) {
                if (!currentNode.isJsonObject() || !currentNode.getAsJsonObject().has(parts[i])) {
                    return true;
                }
                currentNode = currentNode.getAsJsonObject(parts[i]);
            }

            currentNode.getAsJsonObject().remove(parts[parts.length - 1]);

            try (FileWriter writer = new FileWriter(jsonFile)) {
                writer.write(gson.toJson(rootNode));
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean has(String path) {
        return get(path) != null;
    }

    private FileReader jsonFileReader() throws FileNotFoundException {
        return new FileReader(jsonFile);
    }
}