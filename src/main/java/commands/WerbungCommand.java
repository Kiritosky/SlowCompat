package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class WerbungCommand implements CommandExecutor {

    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Nur Spieler können diesen Befehl ausführen.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("werbung.command")) {
            player.sendMessage(ChatColor.RED + "Du hast keine Berechtigung, diesen Befehl zu verwenden.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f &7Bitte gebe &e/werbung &8[&dNachricht&8]&7 um den Befehl richtig zu benützen &fn"));
            return true;
        }

        if (player.hasPermission("werbung.bypass")) {
            String message = String.join(" ", args);
            String formattedMessage = ChatColor.translateAlternateColorCodes('&', "&f &8&l[&c&lWERBUNG&8&l] &e" + message);

            Bukkit.broadcastMessage(formattedMessage);
            Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f));
            return true;
        }

        UUID playerUUID = player.getUniqueId();
        long currentTime = System.currentTimeMillis();

        if (cooldowns.containsKey(playerUUID)) {
            long timeLeft = cooldowns.get(playerUUID) - currentTime;
            if (timeLeft > 0) {
                long minutesLeft = TimeUnit.MILLISECONDS.toMinutes(timeLeft);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f &cWarte bitte " + minutesLeft + " Minuten bis zur erneuten Benutzung. &f"));
                return true;
            }
        }

        String message = String.join(" ", args);
        String formattedMessage = ChatColor.translateAlternateColorCodes('&', "&f &8(&4&lWERBUNG&8) &e" + message);

        Bukkit.broadcastMessage(formattedMessage);
        Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f));

        cooldowns.put(playerUUID, currentTime + TimeUnit.HOURS.toMillis(1));
        return true;
    }
}

