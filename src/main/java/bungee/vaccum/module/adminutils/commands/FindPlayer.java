package bungee.vaccum.module.adminutils.commands;

import bungee.vaccum.module.adminutils.AdminUtils;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.player.CloudPlayer;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;


public class FindPlayer extends Command {

    public FindPlayer(String name) {
        super(name);
    }

    IPlayerManager playerManager = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) sender;
            if(proxiedPlayer.hasPermission("vaccum.find.others")) {
                if (args.length == 1 && !args[0].equals("")) {
                    CloudPlayer target = (CloudPlayer) playerManager.getFirstOnlinePlayer(args[0]);
                    if (target != null) {
                        //ADD PERMISSION BASED DISPLAYTAG
                        proxiedPlayer.sendMessage(AdminUtils.prefix + "Der Spieler §e" + target.getName() + " §2befindet sich auf §3" + target.getConnectedService().getServerName() + " §7(§3" + target.getNetworkConnectionInfo().getNetworkService().getServerName() + "§7)");
                    } else {
                        proxiedPlayer.sendMessage(AdminUtils.prefix + "§cDer genannte Spieler ist nicht online!");
                    }
                } else {
                    proxiedPlayer.sendMessage(AdminUtils.prefix + "§cFalsche Befehl-Eingabe: §7§o/find <player>");
                }
            } else {
                proxiedPlayer.sendMessage(AdminUtils.noperms);
            }
        }
    }
}
