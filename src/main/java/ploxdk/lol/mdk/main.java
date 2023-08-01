package ploxdk.lol.mdk;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ploxdk.lol.mdk.commands.mdkadmin;
import ploxdk.lol.mdk.misc.Color;

public final class main extends JavaPlugin {
    public static ConsoleCommandSender log;

    @Override
    public void onEnable() {
        log = Bukkit.getConsoleSender();
        log.sendMessage(Color.getColored(new String[] { "&8&m---------------------------------&r", "", "  &2Enabling &aWeatherTool.jar &fv" + getDescription().getVersion() , "    &aAuthors: &f" + getDescription().getAuthors(), "", "&8&m---------------------------------&r" }));

        this.getConfig();
        this.saveDefaultConfig();
        this.getCommand("weather").setExecutor((CommandExecutor)new mdkadmin(this));

    }

    @Override
    public void onDisable() {
        log.sendMessage(Color.getColored(new String[] { "&8&m---------------------------------&r", "", "  &4Disabling WeatherTool.jar &fv" + getDescription().getVersion(), "    &cAuthors: &f" + getDescription().getAuthors(), "", "&8&m---------------------------------&r" }));
    }
}
