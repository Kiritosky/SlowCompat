package commands;

import gui.rechtegui;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.slowRights.SlowRights;

public class rechtecommand implements CommandExecutor {

    private final SlowRights plugin;

    public rechtecommand(SlowRights plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission("slowrights.rechte")) {
                sender.sendMessage("&6 You do not have permission to use this command.");
                return true;
            }
            Player player = (Player) sender;
            player.playSound(player.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 1.0f, 1.0f);
            new rechtegui(plugin).openInventory(player);
        }
        return true;
    }
}