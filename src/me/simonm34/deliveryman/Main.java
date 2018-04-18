package me.simonm34.deliveryman;

import me.simonm34.deliveryman.commands.DeliveryManCMD;
import me.simonm34.deliveryman.delay.Delays;
import me.simonm34.deliveryman.events.InventoryClick;
import me.simonm34.deliveryman.reward.Rewards;
import me.simonm34.deliveryman.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private Rewards rewards;
    private Delays delays;
    private Utils utils;

    public void onEnable() {
        loadDepends();
        loadConfigs();
        loadCommands();
        loadEvents();

        getRewards().loadRewards(this);
        getDelays().loadDelays(this);
    }
    public void onDisable() {
        getDelays().saveDelays(this);
    }

    private void loadDepends() {
        rewards = new Rewards();
        delays = new Delays();
        utils = new Utils();
    }
    private void loadConfigs() {
        saveDefaultConfig();
    }
    private void loadCommands() {
        new DeliveryManCMD(this);
    }
    private void loadEvents() {
        new InventoryClick(this);
    }

    public Rewards getRewards() {
        return rewards;
    }
    public Delays getDelays() {
        return delays;
    }
    public Utils getUtils() {
        return utils;
    }
}
