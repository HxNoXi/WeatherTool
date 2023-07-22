package ploxdk.lol.mdk;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import ploxdk.lol.mdk.commands.mdkadmin;
import ploxdk.lol.mdk.misc.Color;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(Color.translate("&a[]==========[ Weather Tool ]==========[]"));
        Bukkit.getConsoleSender().sendMessage(Color.translate(" "));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&eVersion &a" + this.getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&eDevelopers &aNot Found#0001 &fand &aSpoodermand#5621"));
        Bukkit.getConsoleSender().sendMessage(Color.translate(" "));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&a[]==========[ Weather Tool ]==========[]"));
        this.getConfig();
        this.saveDefaultConfig();
        this.getCommand("weather").setExecutor((CommandExecutor)new mdkadmin(this));

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Color.translate("&4[]==========[ Weather Tool ]==========[]"));
        Bukkit.getConsoleSender().sendMessage(Color.translate(" "));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&cVersion &a" + this.getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&cDevelopers &aNot Found#0001 &fand &aSpoodermand#5621"));
        Bukkit.getConsoleSender().sendMessage(Color.translate(" "));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&4[]==========[ Weather Tool ]==========[]"));
    }
}
