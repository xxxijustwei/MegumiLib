package com.taylorswiftcn.justwei;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.taylorswiftcn.justwei.file.FileManager;
import com.taylorswiftcn.justwei.storage.mysql.HikariCPPool;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MegumiLib extends JavaPlugin {

    @Getter
    private static MegumiLib instance;

    @Getter
    private FileManager fileManager;

    @Getter
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        instance = this;

        fileManager = new FileManager(this);
        fileManager.init();

        HikariCPPool.initPool();

        protocolManager = ProtocolLibrary.getProtocolManager();

        getLogger().info(String.format("当前服务器版本: %s", getVersion()));
        getLogger().info("加载成功!");
    }

    @Override
    public void onDisable() {
        HikariCPPool.closePool();
        getLogger().info("卸载成功!");
    }

    public String getVersion() {
        String packet = Bukkit.getServer().getClass().getPackage().getName();
        return packet.substring(packet.lastIndexOf('.') + 1);
    }
}
