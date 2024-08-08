package dk.noxitech.weathertool;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import dk.noxitech.weathertool.commands.admin;
import dk.noxitech.weathertool.misc.Color;
import java.util.Objects;

public final class main extends JavaPlugin {
    public static ConsoleCommandSender log;

    @Override
    public void onEnable() {
        log = Bukkit.getConsoleSender();
        log.sendMessage(Color.getColored(new String[]{
                "&8&m---------------------------------&r",
                "",
                "  &2Enabling &aWeatherTool.jar &fv" + getDescription().getVersion(),
                "    &aAuthors: &f" + String.join(", ", getDescription().getAuthors()),
                "",
                "&8&m---------------------------------&r"
        }));
        saveDefaultConfig();
        admin adminCommand = new admin(this);
        Objects.requireNonNull(this.getCommand("weather")).setExecutor(adminCommand);
        Objects.requireNonNull(this.getCommand("weather")).setTabCompleter(adminCommand);
    }

    @Override
    public void onDisable() {
        log.sendMessage(Color.getColored(new String[]{
                "&8&m---------------------------------&r",
                "",
                "  &4Disabling WeatherTool.jar &fv" + getDescription().getVersion(),
                "    &cAuthors: &f" + String.join(", ", getDescription().getAuthors()),
                "",
                "&8&m---------------------------------&r"
        }));
    }
}