package com.taylorswiftcn.justwei.commands.sub;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SubTabCompleter extends SubCommand implements TabCompleter {

    private final SubCommand help;
    private final HashMap<String, SubCommand> commands;

    public SubTabCompleter(SubCommand help) {
        this.help = help;
        this.commands = new HashMap<>();
    }

    public void register(SubCommand command) {
        commands.put(command.getIdentifier().toLowerCase(), command);
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        SubCommand cmd = help;
        if (args.length >= 1 && commands.containsKey(args[0].toLowerCase())) {
            cmd = commands.get(args[0].toLowerCase());
            cmd.execute(sender, Arrays.copyOfRange(args, 1, args.length));
        }
        else {
            cmd.execute(sender, args);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length > 1) return null;

        List<String> keys = new ArrayList<>();
        for (String key : commands.keySet()) {
            SubCommand sub = commands.get(key);
            if (sub.getPermission() == null) continue;
            if (!sender.hasPermission(sub.getPermission())) continue;
            keys.add(key);

        }
        if (args.length == 0) return keys;

        return keys.stream().filter(s -> StringUtils.startsWithIgnoreCase(s, args[0])).collect(Collectors.toList());
    }
}
