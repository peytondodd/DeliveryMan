package me.simonm34.deliveryman.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {
    private Material material;
    private Integer data;
    private Integer amount;
    private String name;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments;
    private List<ItemFlag> flags;

    public ItemBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }
    public ItemBuilder setData(Integer data) {
        this.data = data;
        return this;
    }
    public ItemBuilder setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }
    public ItemBuilder setName(String string) {
        this.name = ChatColor.translateAlternateColorCodes('&', string);
        return this;
    }
    public ItemBuilder addLore(String name) {
        if (this.lore == null) this.lore = new ArrayList<>();
        this.lore.add(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }
    public ItemBuilder addEnchantment(Enchantment enchantment, Integer integer) {
        if (this.enchantments == null) enchantments = new HashMap<>();
        this.enchantments.put(enchantment, integer);
        return this;
    }
    public ItemBuilder addFlag(ItemFlag itemFlag) {
        if (this.flags == null) flags = new ArrayList<>();
        flags.add(itemFlag);
        return this;
    }

    public ItemStack build() {
        ItemStack item;
        if (data != null)
            item = new ItemStack(material, 1, (short)(int) data);
        else
            item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();
        if (amount != null) item.setAmount(amount);
        if (name != null) meta.setDisplayName(name);
        if (lore != null) meta.setLore(lore);
        if (enchantments != null)
            for (Enchantment enchant : enchantments.keySet())
                meta.addEnchant(enchant, enchantments.get(enchant), true);
        if (flags != null)
            for (ItemFlag flag : flags)
                meta.addItemFlags(flag);

        item.setItemMeta(meta);
        return item;
    }
}
