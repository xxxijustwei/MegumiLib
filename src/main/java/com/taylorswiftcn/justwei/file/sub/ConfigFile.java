package com.taylorswiftcn.justwei.file.sub;

import com.taylorswiftcn.justwei.MegumiLib;
import com.taylorswiftcn.justwei.util.MegumiUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class ConfigFile {
    private static YamlConfiguration config;

    public static String Prefix;

    public static class HikariCP {
        public static Long connectionTimeOut;
        public static Integer minimumIdle;
        public static Integer maximumPoolSize;
    }

    public static class SQL {
        public static Boolean Enable;
        public static String Host;
        public static String Port;
        public static String Database;
        public static String Users;
        public static String Password;
    }

    public static void init() {
        config = MegumiLib.getInstance().getFileManager().getConfig();

        Prefix = getString("Prefix");

        HikariCP.connectionTimeOut = config.getLong("HikariCP.ConnectionTimeOut");
        HikariCP.minimumIdle = config.getInt("HikariCP.MinimumIdle");
        HikariCP.maximumPoolSize = config.getInt("HikariCP.MaximumPoolSize");

        SQL.Enable = config.getBoolean("MYSQL.Enable");
        SQL.Host = getString("MYSQL.Host");
        SQL.Port = getString("MYSQL.Port");
        SQL.Database = getString("MYSQL.Database");
        SQL.Users = getString("MYSQL.Users");
        SQL.Password = getString("MYSQL.Password");
    }

    private static String getString(String path) {
        return MegumiUtil.onReplace(config.getString(path));
    }

    private static List<String> getStringList(String path) {
        return MegumiUtil.onReplace(config.getStringList(path));
    }
}