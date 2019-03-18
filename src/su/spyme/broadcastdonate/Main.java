package su.spyme.broadcastdonate;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

    static String message;
    static String noPermission;
    static String usage;
    static Boolean isConsole;
    static String permission;

    public void onEnable(){
        boolean enable = getConfig().getBoolean("main.ENABLE");
        if(!enable){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "BroadcastDonate disabled in config.");
            return;
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "BroadcastDonate v1.0 by SPY_me enabled.");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getCommandMap().register("bcdonate", new CommandBCDonate());
        saveDefaultConfig();
        message = getConfig().getString("msg.MESSAGE").replaceAll("&", "§");
        noPermission = getConfig().getString("msg.NO_PERMISSION").replaceAll("&", "§");
        usage = getConfig().getString("msg.USAGE").replaceAll("&", "§");
        isConsole = getConfig().getBoolean("settings.COLSOLE_ONLY");
        permission = getConfig().getString("perm.USE_COMMAND");
    }

    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "BroadcastDonate v1.0 by SPY_me disabled.");
    }
}
