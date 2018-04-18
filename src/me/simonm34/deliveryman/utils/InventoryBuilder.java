package me.simonm34.deliveryman.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class InventoryBuilder {
    private String name;
    private Integer size;
    private Map<Integer, ItemStack> items = new HashMap<>();

    public InventoryBuilder setName(String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        return this;
    }

    public InventoryBuilder setSize(Integer size) {
        this.size = size;
        return this;
    }

    public InventoryBuilder addItem(ItemStack item) {
        setItem(items.size(), item);
        return this;
    }

    public InventoryBuilder setItem(Integer slot, ItemStack item) {
        this.items.put(slot, item);
        return this;
    }

    public Integer getSize() {
        return items.size();
    }

    public Inventory build() {
        Inventory inv = Bukkit.createInventory(null, size, name);
        for (Integer slot : items.keySet()) {
            inv.setItem(slot, items.get(slot));
        }
        return inv;
    }
}
