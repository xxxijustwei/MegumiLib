package com.taylorswiftcn.justwei.storage.sqlite;

import com.taylorswiftcn.justwei.storage.MegumiSQL;
import org.bukkit.plugin.Plugin;

import java.sql.*;
import java.util.List;

public class SQLiteHandler extends MegumiSQL {
    private final Plugin plugin;
    private Connection connection;

    public SQLiteHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void openConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder() + "/database.db");
        }
        catch (SQLException e) {
            plugin.getLogger().info("连接数据库失败!");
            this.connection = null;
        }
        catch (ClassNotFoundException e) {
            plugin.getLogger().info("未找到JDBC驱动程序,连接数据库失败!");
            this.connection = null;
        }
    }

    @Override
    public boolean checkConnection() {
        return this.connection != null;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            }
            catch (SQLException e) {
                plugin.getLogger().info("关闭数据库连接失败");
                e.printStackTrace();
            }
        }
    }

    public ResultSet querySQL(String query) {
        try (Statement stat = connection.createStatement()) {
            if (stat == null) return null;
            return stat.executeQuery(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized boolean updateSQL(String data) {
        try (Statement stat = connection.createStatement()) {
            stat.executeUpdate(data);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized boolean updateSQL(String sql, List<String[]> data) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (String[] args : data) {
                for (int i = 1; i <= args.length; i++) {
                    ps.setString(i, args[i - 1]);
                }
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
