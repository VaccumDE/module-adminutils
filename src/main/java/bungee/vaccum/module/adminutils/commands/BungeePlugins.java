package bungee.vaccum.module.adminutils.commands;

import bungee.vaccum.module.adminutils.AdminUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.StringJoiner;

public class BungeePlugins extends Command {


    public BungeePlugins(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("vaccum.see.bungeeplugins")) {
            sender.sendMessage(AdminUtils.noperms);
        } else {
            StringJoiner stringJoiner = new StringJoiner(", ");
            for(Plugin plugin : AdminUtils.getInstance().getProxy().getPluginManager().getPlugins())
                stringJoiner.add(plugin.getDescription().getName());
            sender.sendMessage(AdminUtils.prefix + "Es sind folgende Plugins auf dem BungeeCord-Server: §7" + stringJoiner.toString());
        }
    }
}
