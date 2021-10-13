package com.taylorswiftcn.justwei.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public abstract class MegumiSQL {
    public abstract void openConnection();

    public abstract boolean checkConnection();

    public abstract Connection getConnection();

    public abstract void closeConnection();

    public abstract ResultSet querySQL(String query);

    public abstract boolean updateSQL(String data);

    public abstract boolean updateSQL(String sql, List<String[]> data);
}
