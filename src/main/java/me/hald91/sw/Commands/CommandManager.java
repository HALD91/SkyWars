package me.hald91.sw.Commands;

import me.hald91.sw.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class CommandManager implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Main main = JavaPlugin.getPlugin(Main.class);
        String prefix = "" + main.getConfig().getString("Prefix");
        String prefix1 = "" + main.getConfig().getString("PrefixMenu");

        if (cmd.getName().equalsIgnoreCase("skywars")) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " ") + "This command is only player sided");
            }else {
                if (sender.hasPermission("skywars.use")) {
                    if (args.length == 0) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " ") + ChatColor.GRAY + "Skywars" + ChatColor.GREEN + "Help" + ChatColor.GRAY + "To get a list of the commands");
                    }
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("Help")) {
                            sender.sendMessage(ChatColor.GRAY + "---------------------"+ " " + ChatColor.translateAlternateColorCodes('&', prefix1 + " ") + ChatColor.GRAY + "-------------------");
                            sender.sendMessage(" ");
                            sender.sendMessage(ChatColor.GRAY + "Skywars" + ChatColor.GREEN + " " + "Help" + " " + ChatColor.GRAY + "To get a list of the commands");
                            sender.sendMessage(" ");
                            sender.sendMessage("-----------------------------------------------------");
                            return true;
                        }
                        if (args[0].equalsIgnoreCase("Reload")) {
                            main.reloadConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "") + ChatColor.RED + " " + "Config" + " " + ChatColor.GRAY + "has been reload");
                            return true;
                        }
                    }
                }else {
                    sender.sendMessage(ChatColor.RED + "You do not have access to use this command.");
                }
            }
        }
        return false;
    }
}
