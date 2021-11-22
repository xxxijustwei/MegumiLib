package com.taylorswiftcn.justwei.commands;

import com.taylorswiftcn.justwei.commands.sub.SubCommand;
import com.taylorswiftcn.justwei.commands.sub.SubTabCompleter;
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
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        SubCommand cmd = help;
        if (args.length >= 1 && commands.containsKey(args[0].toLowerCase())) {
            cmd = commands.get(args[0].toLowerCase());
            cmd.execute(sender, Arrays.copyOfRange(args, 1, args.length));
        }
        else {
            cmd.execute(sender, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0) return new ArrayList<>(commands.keySet());

        if (args.length == 1) {
            List<String> keys = getKeys(sender, commands);

            return keys.stream().filter(s -> StringUtils.startsWithIgnoreCase(s, args[0])).collect(Collectors.toList());
        }

        if (args.length == 2 && commands.containsKey(args[0])) {
            String goal = args[0];
            if (commands.get(goal) instanceof SubTabCompleter) {
                SubTabCompleter completer = (SubTabCompleter) commands.get(goal);
                List<String> keys = getKeys(sender, completer.getCommands());

                return keys.stream().filter(s -> StringUtils.startsWithIgnoreCase(s, args[1])).collect(Collectors.toList());
            }
        }

        return null;
    }

    private List<String> getKeys(CommandSender sender, HashMap<String, SubCommand> commands) {
        List<String> keys = new ArrayList<>();
        for (String key : commands.keySet()) {
            SubCommand sub = commands.get(key);
            if (sub.getPermission() != null && !sender.hasPermission(sub.getPermission())) continue;
            keys.add(key);
        }

        return keys;
    }
}