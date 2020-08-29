package bungee.vaccum.module.adminutils.commands;

import bungee.vaccum.module.adminutils.AdminUtils;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class JumpTo extends Command {

    public JumpTo(String name) {
        super(name);
    }

    private final IPlayerManager playerManager = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) sender;
            ICloudPlayer cloudPlayer = playerManager.getOnlinePlayer(proxiedPlayer.getUniqueId());

            if(proxiedPlayer.hasPermission("vaccum.jumpto")) {
                if(args.length == 1) {
                    ICloudPlayer target = playerManager.getFirstOnlinePlayer(args[0]);

                    if(target != null) {
                        if(target.getUniqueId() != cloudPlayer.getUniqueId()) {
                            if (!cloudPlayer.getConnectedService().getServerName().equals(target.getConnectedService().getServerName())) {
                                cloudPlayer.getPlayerExecutor().connect(target.getConnectedService().getServerName());
                                proxiedPlayer.sendMessage(AdminUtils.prefix + "Du wurdest zu §e" + target.getName() + " §7teleportiert.");
                            } else
                                proxiedPlayer.sendMessage(AdminUtils.prefix + "§cDu befindest dich bereits auf diesem Server");
                        } else
                            proxiedPlayer.sendMessage(AdminUtils.prefix + "§cDu dich nicht zu dich selber teleportieren.");
                    } else
                        proxiedPlayer.sendMessage(AdminUtils.prefix + "§cDieser Spieler ist nicht online!");
                } else {
                    proxiedPlayer.sendMessage(AdminUtils.prefix + "§cFalsche Eingabe: §7§o/jumpto <Spieler>");
                }
            } else {
                proxiedPlayer.sendMessage(AdminUtils.noperms);
            }
        }
    }

}
