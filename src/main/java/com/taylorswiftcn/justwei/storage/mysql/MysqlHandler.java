package com.taylorswiftcn.justwei.storage.mysql;

import com.taylorswiftcn.justwei.storage.MegumiSQL;
import com.taylorswiftcn.justwei.file.sub.ConfigFile;
import com.taylorswiftcn.justwei.util.MegumiUtil;
import org.bukkit.plugin.Plugin;

import java.sql.*;
import java.util.List;

public class MysqlHandler extends MegumiSQL {

    private Plugin plugin;
    private Connection connection;

    public MysqlHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void openConnection() {
        this.connection = HikariCPPool.getConnection();
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
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException e) {
                plugin.getLogger().info("关闭数据库连接失败");
                e.printStackTrace();
            }
        }
    }

    public ResultSet querySQL(String query) {
        try {
            Statement stat = connection.createStatement();
            if (stat == null) return null;
            return stat.executeQuery(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateSQL(String data) {
        try {
            Statement stat = connection.createStatement();
            stat.executeUpdate(data);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized boolean updateSQL(String sql, List<String[]> data) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
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
