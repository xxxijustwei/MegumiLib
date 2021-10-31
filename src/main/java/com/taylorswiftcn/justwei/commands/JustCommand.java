package com.taylorswiftcn.justwei.commands;

import com.taylorswiftcn.justwei.commands.sub.SubCommand;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class JustCommand implements TabExecutor {

    private final SubCommand help;
    private final HashMap<String, SubCommand> commands;

    public JustCommand(SubCommand help) {
        this.help = help;
        this.commands = new HashMap<>();
    }

    public void register(SubCommand command) {
        commands.put(command.getIdentifier().toLowerCase(), command);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        SubCommand cmd = help;
        if (strings.length >= 1 && commands.containsKey(strings[0].toLowerCase())) {
            cmd = commands.get(strings[0].toLowerCase());
        }
        cmd.execute(commandSender, Arrays.copyOfRange(strings, 1, strings.length));
        return false;
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