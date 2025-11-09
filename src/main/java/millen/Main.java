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
        getCommand("announce").setExecutor(this);
        getCommand("serverinfo").setExecutor(this);
        getCommand("ping").setExecutor(this);
        getCommand("feed").setExecutor(this);
        getCommand("clearlag").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Utilities plugin disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Utilreload command
        if (command.getName().equalsIgnoreCase("utilreload")) {
            if (sender.hasPermission("util.reload")) {
                reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Config reloaded");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // Heal command
        if (command.getName().equalsIgnoreCase("heal")) {
            if (sender.hasPermission("util.heal")) {
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
            if (sender.hasPermission("util.fix")) {
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
            if (sender.hasPermission("util.fixall")) {
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

        // Announce command
        if (command.getName().equalsIgnoreCase("announce")) {
            if (sender.hasPermission("util.announce")) {
                if (args.length > 0) {
                    String message = ChatColor.translateAlternateColorCodes('&', String.join(" ", args));
                    getServer().broadcastMessage(Color.translate(message));
                    return true;
                }
                sender.sendMessage(ChatColor.RED + "Usage: /announce <message>");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // Server info command
        if (command.getName().equalsIgnoreCase("serverinfo")) {
            if (sender.hasPermission("util.serverinfo")) {
                sender.sendMessage(ChatColor.GREEN + "Server IP: " + ChatColor.WHITE + getServer().getIp() + ":" + getServer().getPort());
                sender.sendMessage(ChatColor.GREEN + "Server version: " + ChatColor.WHITE + getServer().getBukkitVersion());
                sender.sendMessage(ChatColor.GREEN + "Players online: " + ChatColor.WHITE + getServer().getOnlinePlayers().size() + " / " + getServer().getMaxPlayers());
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // Ping command
        if (command.getName().equalsIgnoreCase("ping")) {
            if (sender.hasPermission("util.ping")) {
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

        // Feed command
        if (command.getName().equalsIgnoreCase("feed")) {
            if (sender.hasPermission("util.feed")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.setFoodLevel(20);
                    player.setSaturation(20);
                    player.sendMessage(ChatColor.GREEN + "You have been fed");
                    return true;
                }
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // seen command
        if (command.getName().equalsIgnoreCase("seen")) {
            if (sender.hasPermission("util.seen")) {
                if (args.length > 0) {
                    String playerName = args[0];
                    Player onlinePlayer = getServer().getPlayer(playerName);
                    
                    // Check online player first
                    if (onlinePlayer != null) {
                        sender.sendMessage(ChatColor.GREEN + playerName + " is currently online");
                        return true;
                    }
                    
                    // Check offline player
                    org.bukkit.OfflinePlayer offlinePlayer = getServer().getOfflinePlayer(playerName);
                    if (offlinePlayer != null && offlinePlayer.hasPlayedBefore()) {
                        long lastPlayed = offlinePlayer.getLastPlayed();
                        long currentTime = System.currentTimeMillis();
                        long timeDiff = currentTime - lastPlayed;
                        
                        // Convert milliseconds to readable format
                        long days = timeDiff / (24 * 60 * 60 * 1000);
                        long hours = (timeDiff % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
                        long minutes = (timeDiff % (60 * 60 * 1000)) / (60 * 1000);
                        
                        // Build time ago string
                        StringBuilder timeAgo = new StringBuilder();
                        if (days > 0) timeAgo.append(days).append(days == 1 ? " day " : " days ");
                        if (hours > 0) timeAgo.append(hours).append(hours == 1 ? " hour " : " hours ");
                        if (minutes > 0) timeAgo.append(minutes).append(minutes == 1 ? " minute " : " minutes ");
                        
                        // Handle edge case of very recent play or no time passed
                        if (timeAgo.length() == 0) {
                            timeAgo.append("just now");
                        } else {
                            timeAgo.append("ago");
                        }
                        
                        sender.sendMessage(ChatColor.YELLOW + playerName + " was last seen " + timeAgo.toString().trim());
                        return true;
                    }
                    
                    // Player not found
                    sender.sendMessage(ChatColor.RED + "Player " + playerName + " not found");
                    return true;
                }
                sender.sendMessage(ChatColor.RED + "Usage: /seen <player>");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }
        // enderchest command
        if (command.getName().equalsIgnoreCase("enderchest")) {
            if (sender.hasPermission("util.enderchest")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.openInventory(player.getEnderChest());
                    return true;
                }
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // craft command
        if (command.getName().equalsIgnoreCase("craft")) {
            if (sender.hasPermission("util.craft")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.openWorkbench(null, true);
                    return true;
                }
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        // Clearlag command
        if (command.getName().equalsIgnoreCase("clearlag")) {
            if (sender.hasPermission("util.clearlag")) {
                getServer().getScheduler().runTaskAsynchronously(this, () -> {
                    final int[] itemsCleared = {0};
                    final int[] mobsCleared = {0};

                    for (org.bukkit.World world : getServer().getWorlds()) {
                        // Clear dropped items
                        for (org.bukkit.entity.Item item : world.getEntitiesByClass(org.bukkit.entity.Item.class)) {
                            item.remove();
                            itemsCleared[0]++;
                        }

                        // Clear hostile mobs
                        for (org.bukkit.entity.Monster mob : world.getEntitiesByClass(org.bukkit.entity.Monster.class)) {
                            // Exclude named mobs or mobs with special properties
                            if (!mob.hasMetadata("protected")) {
                                mob.remove();
                                mobsCleared[0]++;
                            }
                        }

                        // Clear other passive mobs (optional, can be configured)
                        for (org.bukkit.entity.Animals animal : world.getEntitiesByClass(org.bukkit.entity.Animals.class)) {
                            // Exclude named animals or special mobs
                            if (!animal.hasMetadata("protected")) {
                                animal.remove();
                            }
                        }
                    }

                    // Send message back to sender
                    getServer().getScheduler().runTask(this, () -> {
                        sender.sendMessage(ChatColor.GREEN + "Cleared " + itemsCleared[0] + " dropped items");
                        sender.sendMessage(ChatColor.GREEN + "Removed " + mobsCleared[0] + " hostile mobs");
                    });
                });
                return true;
            }
            sender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }

        return true;
    }
}
