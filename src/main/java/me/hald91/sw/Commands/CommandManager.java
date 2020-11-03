package me.hald91.sw.Commands;

import me.hald91.sw.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
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
            } else {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " ") + ChatColor.AQUA + "Skywars" + " " + "Help" + " " + ChatColor.WHITE + "To get a list of the commands");
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("List")) {
                        sender.sendMessage(org.bukkit.ChatColor.AQUA + "Getting list.");
                        if (main.getConfig().getStringList("Locations").isEmpty()) {
                            sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + org.bukkit.ChatColor.WHITE + "no Locations exists"));
                        }
                        for (String messages : main.getConfig().getStringList("Locations")) {
                            sender.sendMessage(org.bukkit.ChatColor.WHITE + messages);
                        }
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("Help")) {
                        sender.sendMessage(ChatColor.WHITE + "---------------------" + " " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', prefix + " ") + ChatColor.WHITE + "-------------------");
                        sender.sendMessage(" ");
                        sender.sendMessage(ChatColor.AQUA + "/Skywars" + " " + "Help" + " " + ChatColor.WHITE + "To get a list of the commands.");
                        sender.sendMessage(ChatColor.AQUA + "/Skywars" + " " + "Reload" + " " + ChatColor.WHITE + "To reload the config.");
                        sender.sendMessage(ChatColor.AQUA + "/Skywars" + " " + "List" + " " + ChatColor.WHITE + "To get the list of Locations.");
                        sender.sendMessage(ChatColor.AQUA + "/Skywars" + " " + "<Location>" + " " + ChatColor.WHITE + "To teleport to locations.");
                        sender.sendMessage(ChatColor.AQUA + "/Skywars" + " " + "Set" + "<Location>" + " " + ChatColor.WHITE + "To set a location.");
                        sender.sendMessage(ChatColor.AQUA + "/Skywars" + " " + "Del" + "<Location>" + " " + ChatColor.WHITE + "To del a location.");
                        sender.sendMessage(ChatColor.AQUA + "/Skywars" + " " + "SetPrefix" + "<Prefix>" + " " + ChatColor.WHITE + "To set a new prefix.");
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
                    if (!args[0].isEmpty()) {
                        ArrayList<String> list2 = (ArrayList<String>) main.getConfig().getStringList("Locations");
                        for (String messages1 : list2) {
                            if (messages1.contains(args[0])) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp" + " " + sender.getName() + "" + String.valueOf(messages1.toString()).replace(",", "").replace("Name: " + args[0], "").replace(" X: ", " ").replace(" Y: ", " ").replace(" Z: ", " ").replace("[", "").replace("]", "").replace("&", ""));
                                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + org.bukkit.ChatColor.WHITE + " " + "You have been teleportet to" + " " + org.bukkit.ChatColor.AQUA + args[0]));
                            }
                        }
                    }

                }
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (sender.hasPermission("skywars.admin.set")) {
                            if (!args[1].isEmpty()) {
                                    if (args[1].contains("&") && (args[1].contains("1") || (args[1].contains("2") || (args[1].contains("3") || (args[1].contains("4") || (args[1].contains("5") || (args[1].contains("6") || (args[1].contains("7") || (args[1].contains("8") || (args[1].contains("9") || (args[1].contains("a") || (args[1].contains("b") || (args[1].contains("c") || (args[1].contains("d") || (args[1].contains("e") || (args[1].contains("f"))))))))))))))))) {
                                        sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + org.bukkit.ChatColor.WHITE + "using color codes on the warp name arent allow."));
                                    } else {
                                        String x = valueOf(player.getLocation().getX());
                                        String y = valueOf(player.getLocation().getY());
                                        String z = valueOf(player.getLocation().getZ());
                                        ArrayList<String> posList = (ArrayList<String>) main.getConfig().getStringList("Locations");

                                        main.getConfig();
                                        posList.add("Name: " + args[1] + " X: " + x + " Y: " + y + " Z: " + z);
                                        main.getConfig().set("Locations", posList);
                                        main.getConfig().options().header("###########################\n" +
                                                "#####                 #####\n" +
                                                "####                   ####\n" +
                                                "###   Author: HALD91    ###\n" +
                                                "###   Version: 1.0.2.1  ###\n" +
                                                "####                   ####\n" +
                                                "#####                 #####\n" +
                                                "###########################\n" +
                                                "\n" +
                                                "\n" +
                                                "Set your own prefix here on commands in chat or on the warp menu.");
                                        main.saveConfig();

                                        sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + org.bukkit.ChatColor.AQUA + args[1] + org.bukkit.ChatColor.WHITE + " " + "set at:" + " " + "X:" + " " + org.bukkit.ChatColor.GRAY + x + " " + org.bukkit.ChatColor.WHITE + "Y:" + " " + org.bukkit.ChatColor.GRAY + y + " " + org.bukkit.ChatColor.WHITE + "Z:" + " " + org.bukkit.ChatColor.GRAY + z));                                    }
                                }
                            }
                        }
                        if (sender.hasPermission("skywars.admin.del")) {
                            if (args[0].equalsIgnoreCase("Del")) {
                                if (!args[1].isEmpty()) {
                                    if (main.getConfig().getStringList("Locations").isEmpty()) {
                                        sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + org.bukkit.ChatColor.GRAY + "no warps exists"));
                                    } else {
                                        ArrayList<String> list3 = (ArrayList<String>) main.getConfig().getStringList("Locations");
                                        for (String messages2 : list3) {
                                            if (messages2.contains(args[1])) {
                                                list3.remove(messages2);
                                                main.getConfig().set("Locations", list3);
                                                main.saveConfig();
                                                sender.sendMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString() + " " + org.bukkit.ChatColor.AQUA + args[1] + org.bukkit.ChatColor.WHITE + " " + "has been deleted"));
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (args[0].equalsIgnoreCase("SetPrefix")) {
                            if (sender.hasPermission("skywars.admin.setprefix")) {
                                if (!args[1].isEmpty()) {
                                    main.getConfig().set("Prefix", args[1]);
                                    main.saveConfig();
                                    sender.sendMessage(org.bukkit.ChatColor.WHITE + "Your new prefix" + org.bukkit.ChatColor.RESET + " " + org.bukkit.ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Prefix").toString()));
                                }
                                return true;
                            }
                        }
                        return true;
                        }
                    }
                }return false;
        }
    }
