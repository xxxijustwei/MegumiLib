package com.taylorswiftcn.justwei.file;

import com.taylorswiftcn.justwei.util.MegumiUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public abstract class IConfiguration {

    private Plugin plugin;

    public IConfiguration(Plugin plugin) {
        this.plugin = plugin;
    }

    public abstract void init();

    public YamlConfiguration initFile(String name) {
        File file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            MegumiUtil.copyFile(plugin.getResource(name), file);
            plugin.getLogger().info(String.format("File: 已生成 %s 文件", name));
        }
        else plugin.getLogger().info(String.format("File: 已加载 %s 文件", name));
        return YamlConfiguration.loadConfiguration(file);
    }
}
