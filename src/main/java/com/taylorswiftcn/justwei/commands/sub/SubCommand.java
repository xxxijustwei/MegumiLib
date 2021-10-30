package com.taylorswiftcn.justwei.commands.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class SubCommand {
    private boolean isPlayer;
    private Player player;

    public abstract String getIdentifier();

    public final void execute(CommandSender sender, String[] args) {
        this.isPlayer = sender instanceof Player;
        if (isPlayer) player = (Player) sender;
        if (playerOnly() && !isPlayer) {
            sender.sendMessage(" §7控制台无法使用该命令");
            return;
        }
        if (getPermission() != null && !sender.hasPermission(getPermission())) {
            sender.sendMessage(" §7你没有权限: §a" + getPermission());
            return;
        }
        perform(sender, args);
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract void perform(CommandSender sender, String[] args);

    public abstract boolean playerOnly();

    public abstract String getPermission();
}