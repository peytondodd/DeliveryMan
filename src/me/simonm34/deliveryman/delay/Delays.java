package me.simonm34.deliveryman.delay;

import me.simonm34.deliveryman.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class Delays {
    private Map<String, Map<String, Long>> delays = new HashMap<>();

    public Long getDelay(String name, String reward) {
        if (delays.get(name) == null)
            return null;
        return delays.get(name).get(reward);
    }
public void addDelay(String name, String reward, Long delay) {
    delays.putIfAbsent(name, new HashMap<>());
    delays.get(name).put(reward, delay + System.currentTimeMillis());
}

    public void loadDelays(Main main) {
        FileConfiguration data = main.getConfig();
        data.getConfigurationSection("delays").getKeys(false).forEach(name -> {
            Map<String, Long> rewardDelays = new HashMap<>();
            data.getConfigurationSection("delays." + name).getKeys(false).forEach(rewardName -> {
               Long rewardTime = data.getLong("delays." + name + "." + rewardName);
               if (rewardTime - System.currentTimeMillis() > 0)
                   rewardDelays.put(rewardName, rewardTime);
            });
            if (!rewardDelays.isEmpty())
                delays.put(name, rewardDelays);
        });
    }
    public void saveDelays(Main main) {
        FileConfiguration data = main.getConfig();

        data.set("delays", null);
        delays.forEach((name, delay) -> {
            delay.forEach((rewardName, delayLong) -> {
                if (delayLong - System.currentTimeMillis() <= 0)
                    return;
                data.set("delays." + name + "." + rewardName, delayLong);
            });
        });
        main.saveConfig();
    }
}
