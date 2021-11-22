package com.taylorswiftcn.justwei.commands.sub;

import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashMap;

@Getter
public abstract class SubTabCompleter extends SubCommand {

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
}
