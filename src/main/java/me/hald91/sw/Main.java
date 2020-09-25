package me.hald91.sw;

import me.hald91.sw.Commands.CommandManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {
    public FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        Logger.getLogger("starter");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("skywars").setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
    }
}
