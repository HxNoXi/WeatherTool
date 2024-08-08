package dk.noxitech.weathertool.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import dk.noxitech.weathertool.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import dk.noxitech.weathertool.misc.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class admin implements CommandExecutor, TabCompleter {

    private final main plugin;

    public admin(main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        String adminPerms = plugin.getConfig().getString("AdminPerm");

        if (!player.hasPermission(adminPerms)) {
            player.sendMessage(Color.getColored(String.format("&fThis server is running &bWeather &fversion &b%s", plugin.getDescription().getVersion())));
            player.sendMessage(Color.getColored("&fCreated and maintained by &bNot Found#0001"));
            return true;
        }

        if (args.length == 0) {
            sendUsageMessage(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                reloadConfig(player);
                break;
            case "clear":
                setWeather(player, false, false, plugin.getConfig().getString("Messages.clearweather"));
                break;
            case "storm":
                setWeather(player, true, false, plugin.getConfig().getString("Messages.rainweather"));
                break;
            case "thunder":
                setWeather(player, true, true, plugin.getConfig().getString("Messages.rainweather"));
                break;
            case "day":
                setTime(player, 1000L, plugin.getConfig().getString("Messages.day"));
                break;
            case "night":
                setTime(player, 18000L, plugin.getConfig().getString("Messages.night"));
                break;
            default:
                player.sendMessage(Color.getColored("&cCan't find this value!"));
                break;
        }

        return true;
    }

    private void sendUsageMessage(Player player) {
        List<String> messages = Arrays.asList(
                "&cYou need to define something!",
                "&cExample:",
                "&e/weather reload",
                "&e/weather clear",
                "&e/weather storm",
                "&e/weather thunder",
                "&e/weather day",
                "&e/weather night"
        );

        for (String message : messages) {
            player.sendMessage(Color.getColored(message));
        }
    }

    private void reloadConfig(Player player) {
        player.sendMessage(Color.getColored(plugin.getConfig().getString("Messages.reload")));
        plugin.log.sendMessage(Color.getColored("&e" + player.getName() + " Reloaded the config"));
        plugin.reloadConfig();
        plugin.saveConfig();
    }

    private void setWeather(Player player, boolean storm, boolean thunder, String message) {
        for (World world : Bukkit.getWorlds()) {
            world.setStorm(storm);
            world.setThundering(thunder);
        }
        player.sendMessage(Color.getColored(message));
    }

    private void setTime(Player player, long time, String message) {
        for (World world : Bukkit.getWorlds()) {
            world.setTime(time);
        }
        player.sendMessage(Color.getColored(message));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("reload", "clear", "storm", "thunder", "day", "night");
        }
        return new ArrayList<>();
    }
}