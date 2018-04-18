package me.simonm34.deliveryman.commands;

import me.simonm34.deliveryman.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeliveryManCMD implements CommandExecutor {
    private Main main;
    public DeliveryManCMD(Main main) {
        this.main = main;
        main.getCommand("deliveryman").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            main.getUtils().sendMsg(sender, "&cThis can only be executed by a player!");
            return true;
        }
        Player player = (Player) sender;
        player.openInventory(main.getRewards().getInventory());
        main.getUtils().sendMsg(player, "&6The delivery man has come!");
        return false;
    }
}
