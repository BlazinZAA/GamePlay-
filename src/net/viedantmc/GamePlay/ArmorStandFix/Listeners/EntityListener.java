package net.viedantmc.GamePlay.ArmorStandFix.Listeners;


import net.viedantmc.GamePlay.ArmorStandFix.ArmorStandCrashFix;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.Plugin;

public class EntityListener implements Listener {
    private ArmorStandCrashFix plugin;

    public EntityListener(ArmorStandCrashFix plugin) {
        this.plugin = plugin;
    }

    private ArmorStandCrashFix getPlugin() {
        return this.plugin;
    } // hi

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ARMOR_STAND) {
            ArmorStand stand = (ArmorStand)entity;
            if (getPlugin().getConfigfile().getBoolean("settings.betterchairs")) {
                getPlugin().getServer().getScheduler().runTaskLater((Plugin)this.plugin, () -> {
                    if (paramArmorStand.isVisible())
                        paramEntity.setGravity(false);
                }1L)
            } else {
                entity.setGravity(false);
            }
        }
    }
}
