package millen;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("utilreload").setExecutor(this);
        getCommand("heal").setExecutor(this);
        getCommand("fix").setExecutor(this);
        getCommand("fixall").setExecutor(this);
        getCommand("broadcast").setExecutor(this);
        getCommand("serverinfo").setExecutor(this);
        getCommand("ping").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Utilities plugin disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Utilreload command
        if (command.getName().equalsIgnoreCase("utilreload")) {
            if (sender.hasPermission("utilities.reload")) {
                reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Config reloaded");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // Heal command
        if (command.getName().equalsIgnoreCase("heal")) {
            if (sender.hasPermission("utilities.heal")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.setHealth(player.getMaxHealth());
                    player.sendMessage(ChatColor.GREEN + "You have been healed");
                    return true;
                }
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // Fix command
        if (command.getName().equalsIgnoreCase("fix")) {
            if (sender.hasPermission("utilities.fix")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    org.bukkit.inventory.ItemStack item = player.getInventory().getItemInHand();
                    
                    // Check if hand is empty
                    if (item == null || item.getType() == org.bukkit.Material.AIR) {
                        player.sendMessage(ChatColor.RED + "You must be holding an item to repair something!");
                        return true;
                    }
                    
                    item.setDurability((short) 0);
                    player.getInventory().setItemInHand(item);
                    player.sendMessage(ChatColor.GREEN + "Item repaired " + ChatColor.WHITE + item.getType().name());
                    return true;
                }
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // Fixall command
        if (command.getName().equalsIgnoreCase("fixall")) {
            if (sender.hasPermission("utilities.fixall")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    
                    for (org.bukkit.inventory.ItemStack invItem : player.getInventory().getContents()) {
                        if (invItem != null) {
                            invItem.setDurability((short) 0);
                        }
                    }
                    
                    player.sendMessage(ChatColor.GREEN + "All items repaired " + ChatColor.WHITE + player.getInventory().getContents().length);
                    return true;
                }
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // Broadcast command
        if (command.getName().equalsIgnoreCase("broadcast")) {
            if (sender.hasPermission("utilities.broadcast")) {
                if (args.length > 0) {
                    String message = ChatColor.translateAlternateColorCodes('&', String.join(" ", args));
                    getServer().broadcastMessage(ChatColor.GOLD + "[Broadcast] " + ChatColor.WHITE + message);
                    return true;
                }
                sender.sendMessage(ChatColor.RED + "Usage: /broadcast <message>");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // Server info command
        if (command.getName().equalsIgnoreCase("serverinfo")) {
            if (sender.hasPermission("utilities.serverinfo")) {
                sender.sendMessage(ChatColor.GREEN + "Server IP: " + ChatColor.WHITE + getServer().getIp());
                sender.sendMessage(ChatColor.GREEN + "Server version: " + ChatColor.WHITE + getServer().getBukkitVersion());
                sender.sendMessage(ChatColor.GREEN + "Players online: " + ChatColor.WHITE + getServer().getOnlinePlayers().size() + " / " + getServer().getMaxPlayers());
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // Ping command
        if (command.getName().equalsIgnoreCase("ping")) {
            if (sender.hasPermission("utilities.ping")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.GREEN + "Pong!");
                    return true;
                }
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        return false;
    }
}
