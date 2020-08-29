package bungee.vaccum.module.adminutils.commands;

import bungee.vaccum.module.adminutils.AdminUtils;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissible;
import de.dytanic.cloudnet.driver.permission.IPermissionManagement;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.dytanic.cloudnet.ext.bridge.BridgePlayerManager;
import de.dytanic.cloudnet.ext.bridge.node.CloudNetBridgeModule;
import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import de.dytanic.cloudnet.ext.cloudperms.CloudPermissionsManagement;
import de.dytanic.cloudnet.ext.syncproxy.bungee.util.LoginProxiedPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.jetbrains.annotations.NotNull;

public class AdminChat extends Command {

    public AdminChat(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) sender;
            IPlayerManager playerManager = BridgePlayerManager.getInstance();

            if(proxiedPlayer.hasPermission("vaccum.see.adminchat")) {
                if(args.length == 0) {
                    proxiedPlayer.sendMessage(AdminUtils.prefix + "§cFalsche Befehl-Eingabe: §7§o/ac <nachricht>");
                } else {
                    String msg = "";
                    for (int i = 0; i < args.length; i++)
                        msg = msg + " " + args[i];
                    for(ICloudPlayer all : playerManager.getOnlinePlayers()) {
                        if(CloudPermissionsManagement.getInstance().getUser(all.getUniqueId()).hasPermission("vaccum.see.adminchat").asBoolean()) {
                            playerManager.getPlayerExecutor(all).sendChatMessage(AdminUtils.prefix + "§e" + proxiedPlayer.getName() + " §7-> §c" + msg);
                        }
                    }
                }
            } else {
                proxiedPlayer.sendMessage(AdminUtils.noperms);
            }
        }
    }

}
