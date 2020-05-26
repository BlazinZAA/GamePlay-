package net.viedantmc.GamePlay.ArmorStandFix.Listeners;

import net.viedantmc.GamePlay.ArmorStandFix.ArmorStandCrashFix;
import java.util.ArrayList;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class BlockListener implements Listener {
    private ArmorStandCrashFix plugin;

    public BlockListener(ArmorStandCrashFix plugin) {
        this.plugin = plugin;
    }

    private ArmorStandCrashFix getPlugin() {
        return this.plugin;
    }

    @EventHandler
    public void onPistonRetrackt(BlockPistonRetractEvent event) {
        if (getPlugin().getConfigfile().getBoolean("settings.block-stacking")) {
            int found = 0;
            ArrayList<Entity> remove = new ArrayList<>();
            if (event.getBlocks().size() > 0) {
                for (Block blocks : event.getBlocks()) {
                    for (Entity entity : blocks.getWorld().getNearbyEntities(blocks.getLocation(), 0.5D, 0.5D, 0.5D)) {
                        if (entity instanceof org.bukkit.entity.ArmorStand) {
                            found++;
                            remove.add(entity);
                        }
                    }
                }
            } else {
                Block pistonhead = event.getBlock().getRelative(event.getDirection());
                for (Entity entity : pistonhead.getWorld().getNearbyEntities(pistonhead.getLocation(), 0.5D, 0.5D, 0.5D)) {
                    if (entity instanceof org.bukkit.entity.ArmorStand) {
                        found++;
                        remove.add(entity);
                    }
                }
            }
            if (found > 1 && !remove.isEmpty())
                for (Entity tokill : remove)
                    tokill.remove();
        }
    }
}
