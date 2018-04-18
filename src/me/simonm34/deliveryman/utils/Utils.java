package me.simonm34.deliveryman.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public Material parseMaterial(String string) {
        Material material;
        try {
            material = Material.valueOf(string.toUpperCase());
        } catch (Exception e) {
            return null;
        }
        return material;
    }
    public Long parseLong(String string) {
        Long l;
        try {
            l = Long.parseLong(string);
        } catch (Exception e) {
            return null;
        }
        return l;
    }

    public String formatTime(Long lo) {
        lo = lo - System.currentTimeMillis();

        long secondsMs = 1000;
        long minutesMs = secondsMs * 60;
        long hoursMs = minutesMs * 60;
        long daysMs = hoursMs * 24;
        long weeksMs = daysMs * 7;

        long weeks = lo / weeksMs;
        lo = lo % weeksMs;
        long days = lo / daysMs;
        lo = lo % daysMs;
        long hours = lo / hoursMs;
        lo = lo % hoursMs;
        long minutes = lo / minutesMs;
        lo = lo % minutesMs;
        long seconds = lo / secondsMs;

        List<String> s = new ArrayList<>();
        if (weeks > 0)
            s.add(weeks + " week" + (weeks > 1 ? "s" : ""));
        if (weeks > 0 || days > 0)
            s.add(days + " day" + (days == 0 || days > 1 ? "s" : ""));
        if (weeks > 0 || days > 0 || hours > 0)
            s.add(hours + " hour" + (hours == 0 || hours > 1 ? "s" : ""));
        if (weeks > 0 || days > 0 || hours > 0 ||  minutes > 0)
            s.add(minutes + " minute" + (minutes == 0 || minutes > 1 ? "s" : ""));
        if (weeks > 0 || days > 0 || hours > 0 || minutes > 0 || seconds > 0)
            s.add(seconds + " second" + (seconds == 0 || seconds > 1 ? "s" : ""));

        return String.join(", ", s);
    }

    public String getColor(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    public String getFormat(String string) {
        return getColor("&c&lDeliveryMan &8» " + string);
    }

    public void sendMsg(CommandSender sender, String msg) {
        sender.sendMessage(getFormat(msg));
    }
}
