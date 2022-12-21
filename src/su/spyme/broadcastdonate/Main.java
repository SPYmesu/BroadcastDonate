package su.spyme.broadcastdonate;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "BroadcastDonate v1.1 by SPY_me enabled.");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        message = getConfig().getString("msg.MESSAGE", "&2BroadcastDonate &8» &fPlayer &6%player% &fdonated to &6%donate%").replaceAll("&", "§");
        noPermission = getConfig().getString("msg.NO_PERMISSION", "&2BroadcastDonate &8» &fYou have no permission.").replaceAll("&", "§");
        usage = getConfig().getString("msg.USAGE", "&2BroadcastDonate &8» &fUsage: &6/bcdonate <player>").replaceAll("&", "§");
        isConsole = getConfig().getBoolean("settings.COLSOLE_ONLY", true);
        permission = getConfig().getString("perm.USE_COMMAND", "broadcastdonate.use");
    }

    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "BroadcastDonate v1.1 by SPY_me disabled.");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, String[] args){
        if(!commandSender.equals(Bukkit.getConsoleSender()) && Main.isConsole){
            commandSender.sendMessage("Эта команда может использоваться только из консоли");
            return false;
        }

        if(!commandSender.hasPermission(Main.permission)){
            commandSender.sendMessage(Main.noPermission);
            return false;
        }

        if(args.length < 2){
            commandSender.sendMessage(Main.usage);
            return false;
        }

        StringBuilder builder = new StringBuilder();
        for(int i = 1; i < args.length; i++){
            builder.append(i == 1 ? args[i] : ' ' + args[i]);
        }

        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(Main.message.replace("%player%", args[0]).replace("%donate%", builder.toString()));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1){
            List<String> players = new ArrayList<>();
            for(Player p : Bukkit.getOnlinePlayers()){
                players.add(p.getName());
            }
            return players;
        }
        return new ArrayList<>();
    }
}
