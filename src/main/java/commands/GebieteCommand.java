package command;

import gui.GebieteGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Sound;

public class GebieteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cDieser Befehl kann nur von Spielern ausgeführt werden!");
            return true;
        }
        if (!sender.hasPermission("gebiete.open")) {
            sender.sendMessage("§cDu hast keine Berechtigung, um diesen command auszuführen!");
            return true;
        }

        Player player = (Player) sender;
        player.playSound(player.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 1.0f, 1.0f);
        GebieteGui.openGebieteGUI(player);
        return true;
    }
}