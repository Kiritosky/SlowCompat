package plugin.slowCompat;

import commands.GebieteCommand;
import commands.WerbungCommand;
import commands.rechtecommand;
import listener.GebieteListener;
import listener.GuiClickListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SlowCompat extends JavaPlugin {
    private static SlowCompat instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("SlowAreaTp plugin has been enabled!");


        getCommand("gebiete").setExecutor(new GebieteCommand());
        getCommand("werbung").setExecutor(new WerbungCommand());
        getCommand("rechte").setExecutor(new rechtecommand());


        getServer().getPluginManager().registerEvents(new GebieteListener(), this);
        getServer().getPluginManager().registerEvents(new GuiClickListener(), this);


        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SlowCompat getInstance() {
        return instance;
    }
}
