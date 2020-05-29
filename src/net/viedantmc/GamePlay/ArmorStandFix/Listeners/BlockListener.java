package net.viedantmc.GamePlay.ArmorStandFix.Listeners;

import java.util.ArrayList;

import net.viedantmc.GamePlay.Main;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class BlockListener implements Listener {
    private Main plugin;

    public BlockListener(Main plugin) {
        this.plugin = plugin;
    }

    private Main getPlugin() {
        return this.plugin;
    }

    @EventHandler
    public void onPistonRetrackt(BlockPistonRetractEvent event) {
        if (getPlugin().getConfig().getBoolean("ArmorStandSettings.block-stacking")) {
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
                    ((Damageable)tokill).damage(Double.MAX_VALUE);
        }
    }
}
