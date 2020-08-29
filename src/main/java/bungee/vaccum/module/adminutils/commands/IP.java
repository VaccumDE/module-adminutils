package bungee.vaccum.module.adminutils.commands;

import bungee.vaccum.module.adminutils.AdminUtils;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.player.CloudPlayer;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class IP extends Command {

    IPlayerManager playerManager = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);

    public IP(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission("vaccum.see.ip")) {
            if(args.length == 1) {
                CloudPlayer target = (CloudPlayer) playerManager.getFirstOnlinePlayer(args[0]);
                sender.sendMessage(AdminUtils.prefix + "Die IP von §e" + target.getName() + " §2lautet: §c" + target.getNetworkConnectionInfo().getAddress().getHost());
            } else {
                sender.sendMessage(AdminUtils.prefix + "§cFalsche Befehl-Eingabe: §7§o/ip <player>");
            }
        } else
            sender.sendMessage(AdminUtils.noperms);
    }

}
