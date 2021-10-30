package com.taylorswiftcn.justwei.file;

import com.taylorswiftcn.justwei.file.sub.ConfigFile;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class FileManager extends JustConfiguration {

    @Getter
    private YamlConfiguration config;

    public FileManager(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void init() {
        config = initFile("config.yml");

        ConfigFile.init();
    }
}
