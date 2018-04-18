package me.simonm34.deliveryman.reward;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Reward {
    private String name;
    private List<String> commands;
    private Long delay; //in ms
    private ItemStack item;
    private Integer slot;

    public Reward(String name, List<String> commands, Long delay, ItemStack item, Integer slot) {
        this.name = name;
        this.commands = commands;
        this.delay = delay;
        this.item = item;
        this.slot = slot;
    }

    public String getName() {
        return name;
    }
    public List<String> getCommand() {
        return commands;
    }
    public Long getDelay() {
        return delay;
    }
    public ItemStack getItem() {
        return item;
    }
    public Integer getSlot() {
        return slot;
    }
}
