package gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plugin.slowRights.SlowRights;

import java.util.List;

public class rechtegui {

    private final SlowRights plugin;

    public rechtegui(SlowRights plugin) {
        this.plugin = plugin;
    }

    public void openInventory(Player player) {
        Inventory gui = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', "&4&lRANG Rechte"));

        ItemStack blackPane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta paneMeta = blackPane.getItemMeta();
        if (paneMeta != null) {
            paneMeta.setDisplayName(" ");
            blackPane.setItemMeta(paneMeta);
        }
        for (int i = 0; i < gui.getSize(); i++) {
            gui.setItem(i, blackPane);
        }

        for (String key : plugin.getConfig().getConfigurationSection("items").getKeys(false)) {
            int slot = plugin.getConfig().getInt("items." + key + ".slot");
            Material material = Material.valueOf(plugin.getConfig().getString("items." + key + ".item").toUpperCase());
            String name = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("items." + key + ".name"));
            List<String> lore = plugin.getConfig().getStringList("items." + key + ".lore");

            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(name);
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
            gui.setItem(slot, item);
        }

        player.openInventory(gui);
    }
}