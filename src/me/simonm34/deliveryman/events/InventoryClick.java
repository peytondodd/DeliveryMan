package me.simonm34.deliveryman.events;

import me.simonm34.deliveryman.Main;
import me.simonm34.deliveryman.reward.Reward;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {
    public InventoryClick(Main main) {
        main.getServer().getPluginManager().registerEvent(InventoryClickEvent.class, new Listener(){}, EventPriority.NORMAL, ($, rawEvent) -> {
            InventoryClickEvent e = (InventoryClickEvent) rawEvent;
            Player player = (Player) e.getWhoClicked();
            Inventory inv = e.getClickedInventory();
            ItemStack item = e.getCurrentItem();

            if (item == null)
                return;
            if (!item.hasItemMeta())
                return;
            if (!item.getItemMeta().hasDisplayName())
                return;
            if (!inv.getName().equals(main.getRewards().getInventory().getName()))
                return;

            Reward reward = main.getRewards().getReward(item);
            if (reward == null)
                return;

            player.closeInventory();

            Long delay = main.getDelays().getDelay(player.getName(), reward.getName());
            if (delay != null) {
                if (delay - System.currentTimeMillis() > 0) {
                    main.getUtils().sendMsg(player, "&cYou claim this reward in &b" + main.getUtils().formatTime(delay));
                    return;
                }
            }
            main.getDelays().addDelay(player.getName(), reward.getName(), reward.getDelay());

            reward.getCommand().forEach(command ->
                    main.getServer().dispatchCommand(main.getServer().getConsoleSender(), command.replace("%player%", player.getName()))
            );
        }, main);
    }
}
