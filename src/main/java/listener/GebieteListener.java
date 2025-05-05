package listener;

import gui.GebieteGui;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.ChatColor;

import java.util.Map;

public class GebieteListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Gebiete")) {
            event.setCancelled(true);

            int slot = event.getRawSlot();
            Map<Integer, Location> locations = GebieteGui.getSlotToLocationMap();

            if (locations.containsKey(slot)) {
                Location destination = locations.get(slot);
                player.closeInventory();
                player.teleport(destination);
                player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 1.0f);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f &8&l[&c&lTeleport&8&l] &eDu wurdest zu " + destination.getWorld().getName() + " teleportiert!"));
            }
        }
    }
}