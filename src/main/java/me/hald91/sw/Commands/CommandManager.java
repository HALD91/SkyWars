package me.hald91.sw.Commands;

import me.hald91.sw.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static java.lang.String.valueOf;


public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Main main = JavaPlugin.getPlugin(Main.class);
        String prefix = "" + main.getConfig().getString("Prefix");
        String prefix1 = "" + main.getConfig().getString("HelpMenuName");

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("skywars")) {

            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " ") + ChatColor.WHITE + "This command is only player sided");
            }else {
                if (sender.hasPermission("skywars.use")) {
                    if (args.length == 0) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " ") + ChatColor.AQUA + "Skywars" + " " + "Help" + " " + ChatColor.WHITE + "To get a list of the commands");
                    }
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("Help")) {
                            sender.sendMessage(ChatColor.WHITE + "---------------------"+ " " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', prefix1 + " ") + ChatColor.WHITE + "-------------------");
                            sender.sendMessage(" ");
                            sender.sendMessage(ChatColor.AQUA + "/Skywars" + " " + "Help" + " " + ChatColor.WHITE + "To get a list of the commands");
                            sender.sendMessage(ChatColor.AQUA + "/Skywars" + " " + "Reload" + " " + ChatColor.WHITE + "To reload the config");
                            sender.sendMessage(" ");
                            sender.sendMessage(ChatColor.WHITE + "-----------------------------------------------------");
                            return true;
                        }
                        if (args[0].equalsIgnoreCase("Reload")) {
                            if (sender.hasPermission("skywars.admin.reload")) {
                                main.reloadConfig();
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "") + ChatColor.AQUA + " " + "Config" + " " + ChatColor.WHITE + "has been reload");
                                return true;
                            }
                        }

                    }
                    if (args.length == 3){
                        if (args[0].equalsIgnoreCase("set")) {
                            if (sender.hasPermission("skywars.admin.set")) {
                                if (!args[1].isEmpty()) {
                                    if (!args[2].isEmpty()) {
                                        String x = valueOf(player.getLocation().getX());
                                        String y = valueOf(player.getLocation().getY());
                                        String z = valueOf(player.getLocation().getZ());
                                        ArrayList<String> posList = (ArrayList<String>) main.getConfig().getStringList("Locations");

                                        main.getConfig();
                                        posList.add(args[1] + args[2] + ChatColor.WHITE + "X: " + x + " Y: " + y + " z: " + z);
                                        main.getConfig().options().header("###########################\n" +
                                                "#####                 #####\n" +
                                                "####                   ####\n" +
                                                "###   Author: HALD91    ###\n" +
                                                "###   Version: 1.2.7.3  ###\n" +
                                                "####                   ####\n" +
                                                "#####                 #####\n" +
                                                "###########################\n" +
                                                "\n" +
                                                "\n" +
                                                "Set your own prefix here on commands in chat or on the warp menu.");
                                        main.saveConfig();

                                        sender.sendMessage(args[2] + "is set");
                                    }
                                }
                            }
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
