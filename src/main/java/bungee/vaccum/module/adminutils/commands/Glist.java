package bungee.vaccum.module.adminutils.commands;

import bungee.vaccum.module.adminutils.AdminUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class Glist extends Command {

    public Glist(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        int playerCount = AdminUtils.getInstance().getCloudHandler().getTotalPlayerCount();

        if(playerCount > 1)
            sender.sendMessage("§aEs sind aktuell §e" + playerCount + " §aSpieler online.");
        else
            sender.sendMessage("§aEs ist aktuell §e" + playerCount + " §aSpieler online.");
    }

}
