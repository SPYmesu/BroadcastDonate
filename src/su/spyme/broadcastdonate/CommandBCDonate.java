package su.spyme.broadcastdonate;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBCDonate extends Command{
    CommandBCDonate(){
        super("bcdonate");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args){
        if(!commandSender.equals(Bukkit.getConsoleSender()) && Main.isConsole){
            commandSender.sendMessage("Эта команда может использоваться только из консоли");
            return false;
        }

        if(!commandSender.hasPermission(Main.permission)){
            commandSender.sendMessage(Main.noPermission);
            return false;
        }

        if(args.length != 2){
            commandSender.sendMessage(Main.usage);
            return false;
        }

        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(Main.message.replace("%player%", args[0]).replace("%donate%", args[1]));
        }
        return true;
    }
}
