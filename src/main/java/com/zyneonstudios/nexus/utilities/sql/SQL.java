package com.zyneonstudios.nexus.utilities.sql;

import java.sql.Connection;

public interface SQL {

    boolean connect();
    boolean reconnect();
    boolean disconnect();
    Connection getConnection();
    boolean execute(String statement);
}