package world.ntdi.mapcleanup;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public final class MapCleanup extends JavaPlugin implements Listener {
    List<Block> blocks = new ArrayList<Block>();

    int Delay = 5 * 60 * 20;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getLogger().info("MapCleanup enabled");

        new BukkitRunnable() {
            @Override
            public void run() {
                cleanup();
            }
        }.runTaskTimer(this, Delay, Delay);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("MapCleanup disabled");
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Block b = e.getBlock();
        blocks.add(b);
    }

    public void cleanup() {
        for (Block b : blocks) {
            b.setType(Material.AIR);
        }
        blocks.clear();
    }
}
