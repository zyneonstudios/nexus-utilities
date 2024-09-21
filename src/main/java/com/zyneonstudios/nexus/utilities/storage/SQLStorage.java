package com.zyneonstudios.nexus.utilities.storage;

import com.zyneonstudios.nexus.utilities.NexusUtilities;
import com.zyneonstudios.nexus.utilities.sql.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLStorage implements Storage{

    private final SQL sql;
    private final String table;

    public SQLStorage(String tableName, SQL sql) {
        this.sql = sql;
        this.table = tableName;
        sql.execute("CREATE TABLE IF NOT EXISTS "+table+" ( `key` VARCHAR(255) NOT NULL PRIMARY KEY, `value` TEXT );");
    }

    @Override
    public boolean has(String path) {
        return get(path) != null;
    }

    @Override
    public Object get(String path) {
        sql.disconnect();
        sql.connect();
        String query = "SELECT `value` FROM `"+table+"` WHERE `key` = ?";
        try (Connection connection = sql.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, path);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getObject("value");
                }
            }
        } catch (SQLException e) {
            NexusUtilities.getLogger().err("[UTILITIES] (SQLStorage) Couldn't read from database: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String getString(String path) {
        return get(path).toString();
    }

    @Override
    public Integer getInteger(String path) {
        return Integer.parseInt(getString(path));
    }

    @Override
    public int getInt(String path) {
        return getInteger(path);
    }

    @Override
    public Double getDouble(String path) {
        return Double.parseDouble(getString(path));
    }

    @Override
    public double getDoub(String path) {
        return getDouble(path);
    }

    @Override
    public Boolean getBoolean(String path) {
        return Boolean.parseBoolean(getString(path).toLowerCase());
    }

    @Override
    public boolean getBool(String path) {
        return getBoolean(path);
    }

    public boolean ensure(String key, String value) {
        if(!has(key)) {
            return set(key, value);
        }
        return true;
    }

    public boolean set(String key, String value) {
        sql.disconnect();
        sql.connect();
        String query = "INSERT INTO `" + table + "` (`key`, `value`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `value` = ?";
        try (Connection connection = sql.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, key);
            statement.setString(2, value);
            statement.setString(3, value);
            statement.execute();
            return true;
        } catch (SQLException e) {
            NexusUtilities.getLogger().err("[UTILITIES] (SQLStorage) Couldn't write to database: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String key) {
        sql.disconnect();
        sql.connect();
        String query = "DELETE FROM `"+table+"` WHERE `key` = ?";
        try (Connection connection = sql.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, key);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            NexusUtilities.getLogger().err("[UTILITIES] (SQLStorage) Couldn't delete database entry ("+key+"): " + e.getMessage());
            return false;
        }
    }
}
