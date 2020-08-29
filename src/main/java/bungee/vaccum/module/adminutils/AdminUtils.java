package bungee.vaccum.module.adminutils;

import bungee.vaccum.api.cloud.CloudHandler;
import bungee.vaccum.api.vBungeeAPI;
import bungee.vaccum.module.adminutils.commands.*;
import net.md_5.bungee.api.plugin.Plugin;

public final class AdminUtils extends Plugin {

    private static AdminUtils instance;
    private vBungeeAPI bungeeAPI;
    public static String prefix = "§7» §bAdminUtils §7| §2";
    public static String noperms = prefix + "§cDazu hast du keine Berechtigung";

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getProxy().getConsole().sendMessage("§7[" + getDescription().getName() + "] §aLoading...");

        getProxy().getPluginManager().registerCommand(this, new BungeePlugins("bungeeplugins"));
        getProxy().getPluginManager().registerCommand(this, new FindPlayer("find"));
        getProxy().getPluginManager().registerCommand(this, new AdminChat("ac"));
        getProxy().getPluginManager().registerCommand(this, new AdminChat("adminchat"));
        getProxy().getPluginManager().registerCommand(this, new IP("ip"));
        getProxy().getPluginManager().registerCommand(this, new Glist("glist"));
        getProxy().getPluginManager().registerCommand(this, new Glist("list"));
        getProxy().getPluginManager().registerCommand(this, new JumpTo("jumpto"));

        getProxy().getConsole().sendMessage("§7[" + getDescription().getName() + "] §aSucessfully loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AdminUtils getInstance() {
        return instance;
    }

    public CloudHandler getCloudHandler() { return  vBungeeAPI.getInstance().getCloudHandler(); }

}
