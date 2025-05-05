package gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plugin.slowAreaTp.SlowAreaTp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GebieteGui {
    private static final String GUI_TITLE = ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Gebiete";
    private static final int GUI_SIZE = 54;
    private static Map<Integer, Location> slotToLocationMap = new HashMap<>();

    public static void openGebieteGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, GUI_SIZE, GUI_TITLE);


        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        placeholderMeta.setDisplayName(" ");
        placeholder.setItemMeta(placeholderMeta);

        for (int i = 0; i < GUI_SIZE; i++) {
            gui.setItem(i, placeholder);
        }


        SlowAreaTp plugin = SlowAreaTp.getInstance();
        ConfigurationSection gebieteSection = plugin.getConfig().getConfigurationSection("gebiete");

        if (gebieteSection != null) {
            slotToLocationMap.clear();

            for (String key : gebieteSection.getKeys(false)) {
                ConfigurationSection itemSection = gebieteSection.getConfigurationSection(key);
                if (itemSection != null) {
                    int slot = itemSection.getInt("slot");
                    String itemType = itemSection.getString("item", "DIAMOND");
                    String name = ChatColor.translateAlternateColorCodes('&',
                            itemSection.getString("name", "Gebiet"));
                    List<String> loreStrings = itemSection.getStringList("lore");
                    List<String> lore = new ArrayList<>();
                    for (String line : loreStrings) {
                        lore.add(ChatColor.translateAlternateColorCodes('&', line));
                    }

                    double x = itemSection.getDouble("x");
                    double y = itemSection.getDouble("y");
                    double z = itemSection.getDouble("z");
                    String worldName = itemSection.getString("world", player.getWorld().getName());
                    Location loc = new Location(Bukkit.getWorld(worldName), x, y, z);

                    Material material = Material.getMaterial(itemType.toUpperCase());
                    if (material == null) material = Material.DIAMOND;

                    ItemStack item = new ItemStack(material);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(name);
                    meta.setLore(lore);
                    item.setItemMeta(meta);

                    gui.setItem(slot, item);
                    slotToLocationMap.put(slot, loc);
                }
            }
        }

        player.openInventory(gui);
    }

    public static Map<Integer, Location> getSlotToLocationMap() {
        return slotToLocationMap;
    }
}