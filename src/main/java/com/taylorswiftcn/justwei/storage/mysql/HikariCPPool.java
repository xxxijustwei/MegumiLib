package com.taylorswiftcn.justwei.storage.mysql;

import com.taylorswiftcn.justwei.file.sub.ConfigFile;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPPool {

    private static HikariDataSource pool;

    public static void initPool() {
        if (ConfigFile.SQL.Database == null || ConfigFile.SQL.Database.equals("")) return;

        String url = "jdbc:mysql://" + ConfigFile.SQL.Host + ":" + ConfigFile.SQL.Port + "/" + ConfigFile.SQL.Database + "?useSSL=false";

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setConnectionTimeout(ConfigFile.HikariCP.connectionTimeOut);
        config.setMinimumIdle(ConfigFile.HikariCP.minimumIdle);
        config.setMaximumPoolSize(ConfigFile.HikariCP.maximumPoolSize);
        config.setJdbcUrl(url);
        config.setUsername(ConfigFile.SQL.Users);
        config.setPassword(ConfigFile.SQL.Password);
        config.setAutoCommit(false);

        pool = new HikariDataSource(config);
    }

    public static void closePool() {
        if (pool == null) return;
        pool.close();
    }

    public static Connection getConnection() {
        try {
            return pool.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
