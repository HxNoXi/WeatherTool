package ploxdk.lol.mdk.commands;

import org.bukkit.World;
import ploxdk.lol.mdk.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ploxdk.lol.mdk.misc.Color;

public class mdkadmin implements CommandExecutor {

    private final main plugin;
    private org.bukkit.Bukkit Bukkit;

    public mdkadmin(main plugin) {this.plugin = plugin;}

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {

            String clearweather = plugin.getConfig().getString("Messages.clearweather");
            String rainweather = plugin.getConfig().getString("Messages.rainweather");
            String day = plugin.getConfig().getString("Messages.day");
            String night = plugin.getConfig().getString("Messages.night");
            String reloadmessage = plugin.getConfig().getString("Messages.reload");

            final Player player = (Player) sender;
            String adminperms = plugin.getConfig().getString("AdminPerm");
            if (player.hasPermission(adminperms)) {
                if (args.length <= 0){
                    player.sendMessage(Color.getColored("&cYou need to define something!"));
                    player.sendMessage(Color.getColored("&cExample:"));
                    player.sendMessage(Color.getColored("&e/weather reload"));
                    player.sendMessage(Color.getColored("&e/weather clear"));
                    player.sendMessage(Color.getColored("&e/weather storm"));
                    player.sendMessage(Color.getColored("&e/weather thunder"));
                    player.sendMessage(Color.getColored("&e/weather day"));
                    player.sendMessage(Color.getColored("&e/weather night"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    player.sendMessage(Color.getColored(reloadmessage));
                    plugin.log.sendMessage(Color.getColored(new String[] { "&8&m---------------------------------&r", "", "&e" + player.getName() + " Reloaded the config", "", "&8&m---------------------------------&r" }));
                    plugin.reloadConfig();
                    plugin.saveConfig();
                }else if (args[0].equalsIgnoreCase("clear")) {
                    for (final World world : Bukkit.getWorlds()) {
                        world.setStorm(false);
                        world.setThundering(false);

                    }
                    player.sendMessage(Color.getColored(clearweather));
                }else if (args[0].equalsIgnoreCase("storm")) {
                    for (final World world : Bukkit.getWorlds()) {
                        world.setStorm(true);
                    }
                    player.sendMessage(Color.getColored(rainweather));
                }else if (args[0].equalsIgnoreCase("thunder")) {
                    for (final World world : Bukkit.getWorlds()) {
                        world.setThundering(true);
                    }
                    player.sendMessage(Color.getColored(rainweather));
                }else if (args[0].equalsIgnoreCase("day")) {
                    for (final World world : Bukkit.getWorlds()) {
                        world.setTime(1000L);
                    }
                    player.sendMessage(Color.getColored(day));
                }else if (args[0].equalsIgnoreCase("night")) {
                    for (final World world : Bukkit.getWorlds()) {
                        world.setTime(18000L);
                    }
                    player.sendMessage(Color.getColored(night));
                }else{
                    player.sendMessage(Color.getColored("&cCan't find this value!"));
                }
            }else{
                player.sendMessage(Color.getColored(String.format("&fThis server is running &bWeather &fversion &b" + plugin.getDescription().getVersion())));
                player.sendMessage(Color.getColored("&fCreated and maintained by &bNot Found#0001"));
                return true;
            }
        }
        return true;
    }
}
