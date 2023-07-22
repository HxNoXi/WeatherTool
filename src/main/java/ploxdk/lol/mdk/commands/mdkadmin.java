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
            String noaccess = plugin.getConfig().getString("Messages.noaccess");
            String reloadmessage = plugin.getConfig().getString("Messages.reload");

            final Player player = (Player) sender;
            String adminperms = plugin.getConfig().getString("AdminPerm");
            if (args.length <= 0){
                if (player.hasPermission(adminperms)) {
                    player.sendMessage(Color.translate("&cYou need to define something!"));
                    player.sendMessage(Color.translate("&cExample:"));
                    player.sendMessage(Color.translate("&e/weather reload"));
                    player.sendMessage(Color.translate("&e/weather clear"));
                    player.sendMessage(Color.translate("&e/weather rain"));
                    player.sendMessage(Color.translate("&e/weather day"));
                    player.sendMessage(Color.translate("&e/weather night"));
                    return true;
                }else{
                    player.sendMessage(Color.translate(String.format("&fThis server is running &bWeather &fversion &b" + plugin.getDescription().getVersion())));
                    player.sendMessage(Color.translate("&fCreated and maintained by &bNot Found#0001"));
                    return true;
                }
            }
            if (player.hasPermission(adminperms)) {
                if (args[0].equalsIgnoreCase("reload")) {
                    player.sendMessage(Color.translate(reloadmessage));
                    Bukkit.getConsoleSender().sendMessage(Color.translate("&a[]==========[ STAFF CHAT ]==========[]"));
                    Bukkit.getConsoleSender().sendMessage(Color.translate("&e" + player.getName() + " Reloaded the config"));
                    Bukkit.getConsoleSender().sendMessage(Color.translate("&a[]==========[ STAFF CHAT ]==========[]"));
                    plugin.reloadConfig();
                    plugin.saveConfig();
                }else if (args[0].equalsIgnoreCase("clear")) {
                    for (final World world : Bukkit.getWorlds()) {
                        world.setStorm(false);
                        world.setThundering(false);

                    }
                    player.sendMessage(Color.translate(clearweather));
                }else if (args[0].equalsIgnoreCase("rain")) {
                    for (final World world : Bukkit.getWorlds()) {
                        world.setStorm(true);
                    }
                    player.sendMessage(Color.translate(rainweather));
                }else if (args[0].equalsIgnoreCase("day")) {
                    for (final World world : Bukkit.getWorlds()) {
                        world.setTime(1000L);
                    }
                    player.sendMessage(Color.translate(day));
                }else if (args[0].equalsIgnoreCase("night")) {
                    for (final World world : Bukkit.getWorlds()) {
                        world.setTime(18000L);
                    }
                    player.sendMessage(Color.translate(night));
                }
            }else{
                player.sendMessage(Color.translate(noaccess));
            }
        }
        return true;
    }
}
