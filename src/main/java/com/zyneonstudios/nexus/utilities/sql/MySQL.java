package com.zyneonstudios.nexus.utilities.sql;

import com.zyneonstudios.nexus.utilities.NexusUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MySQL implements SQL {

    private Connection connection = null;
    private final String url;
    private final String path;

    private final String hostname;
    private final String username;
    private final String database;
    private final int port;
    private final boolean ssl;

    public MySQL(String hostname, String username, String password, String database, int port, boolean ssl) {
        this.hostname = hostname;
        this.username = username;
        this.database = database;
        this.port = port;
        this.ssl = ssl;
        url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?user="+username+"&password="+password+"&useSSL="+ssl;
        path = username+"@"+hostname+":"+port;
    }

    @Override
    public boolean connect() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(url);
            }
            return connection != null;
        } catch (Exception e) {
            NexusUtilities.getLogger().err("[UTILITIES] (MySQL) Can't connect to database: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean reconnect() {
        try {
            connection = DriverManager.getConnection(url);
            return connection != null;
        } catch (Exception e) {
            NexusUtilities.getLogger().err("[UTILITIES] (MySQL) Can't reconnect to database: "+e.getMessage());
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
            NexusUtilities.getLogger().err("[UTILITIES] (MySQL) Can't disconnect from database: "+e.getMessage());
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

    public String getUrl() {
        return "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?user="+username+"&password=***&useSSL="+ssl;
    }

    public String getHostname() {
        return hostname;
    }

    public String getUsername() {
        return username;
    }

    public String getDatabase() {
        return database;
    }

    public int getPort() {
        return port;
    }

    public boolean isSSL() {
        return ssl;
    }

    public String getPath() {
        return path;
    }
}