package com.zyneonstudios.nexus.utilities.sql;

import com.zyneonstudios.nexus.utilities.NexusUtilities;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SQLite implements SQL {

    private Connection connection = null;
    private final String url;
    private final String path;

    public SQLite(String path) {
        this.path = path.replace("\\","/");
        url = "jdbc:sqlite:"+this.path;

        File sqlFile = new File(path);
        File folder = sqlFile.getParentFile();
        if(!folder.exists()) {
            NexusUtilities.getLogger().deb("[UTILITIES] (SQLite) Created sql file path: "+folder.mkdirs());
        }
        if(!sqlFile.exists()) {
            try {
                NexusUtilities.getLogger().deb("[UTILITIES] (SQLite) Created sql file: " + sqlFile.createNewFile());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean connect() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(url);
            }
            if(connection != null) {
                return true;
            } else {
                throw new RuntimeException("Cannot connect to SQLite database!");
            }
        } catch (Exception e) {
            NexusUtilities.getLogger().err("[UTILITIES] (SQLite) Can't connect to database: "+e.getMessage());
        } finally {
            disconnect();
        }
        return false;
    }

    @Override
    public boolean reconnect() {
        try {
            connection = DriverManager.getConnection(url);
            return connection != null;
        } catch (Exception e) {
            NexusUtilities.getLogger().err("[UTILITIES] (SQLite) Can't reconnect to database: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        try {
            connection.close();
            if(connection.isClosed()) {
                connection = null;
            } else {
                throw new RuntimeException("SQL connection ("+path+") is not closed!");
            }
        } catch (Exception e) {
            NexusUtilities.getLogger().err("[UTILITIES] (SQLite) Can't disconnect from database: "+e.getMessage());
        }
        return false;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean execute(String statement) {
        if(connect()) {
            try {
                PreparedStatement prepStatement = getConnection().prepareStatement(statement);
                prepStatement.execute();
                return true;
            } catch (Exception ignore) {}
        }
        return false;
    }

    public String getPath() {
        return path;
    }

    public String getUrl() {
        return url;
    }
}