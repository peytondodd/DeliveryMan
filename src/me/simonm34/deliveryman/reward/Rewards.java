package me.simonm34.deliveryman.reward;

import me.simonm34.deliveryman.Main;
import me.simonm34.deliveryman.utils.InventoryBuilder;
import me.simonm34.deliveryman.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rewards {
    private Collection<Reward> rewards = new ArrayList<>();
    private Inventory inventory;

    public Reward getReward(ItemStack item) {
        return rewards.stream()
                .filter(reward -> item.equals(reward.getItem()))
                .findAny()
                .orElse(null);
    }
    public Inventory getInventory() {
        return inventory;
    }

    public void loadRewards(Main main) {
        FileConfiguration data = main.getConfig();
        data.getConfigurationSection("rewards").getKeys(false).forEach(key -> {
            String name = key;
            List<String> command = data.getStringList("rewards." + key + ".run commands");
            Long delay = data.getLong("rewards." + key + ".delay") * 1000;
            Integer slot = data.getInt("rewards." + key + ".item.slot") - 1;

            Material material = main.getUtils().parseMaterial(data.getString("rewards." + key + ".item.material"));
            Integer amount = data.getInt("rewards." + key + ".item.amount");
            String itemName = main.getUtils().getColor(data.getString("rewards." + key + ".item.name"));
            List<String> lore = data.getStringList("rewards." + key + ".item.lore");

            ItemBuilder item = new ItemBuilder()
                    .setMaterial(material)
                    .setAmount(amount)
                    .setName(itemName);
            lore.forEach(item::addLore);

            Reward reward = new Reward(name, command, delay, item.build(), slot);
            rewards.add(reward);
        });

        InventoryBuilder inv = new InventoryBuilder();
        inv.setName(main.getUtils().getColor(data.getString("inventory.name")));
        inv.setSize(data.getInt("inventory.rows") * 9);
        rewards.forEach(reward ->
                inv.setItem(reward.getSlot(), reward.getItem())
        );
        inventory = inv.build();
    }
}
