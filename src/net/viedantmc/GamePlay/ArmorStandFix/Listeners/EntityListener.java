package net.viedantmc.GamePlay.ArmorStandFix.Listeners;


import net.viedantmc.GamePlay.Main;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntityListener implements Listener {
    public Main plugin;

    public EntityListener(Main plugin) {
        this.plugin = plugin;
    }

    public Main getPlugin() {
        return this.plugin;
    } // hi

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ARMOR_STAND) {
            ArmorStand stand = (ArmorStand)entity;
            if (getPlugin().getConfig().getBoolean("ArmorStandSettings.betterchairs")) {
                getPlugin().getServer().getScheduler().runTaskLater(plugin, () -> {
                    if (stand.isVisible())
                        stand.setGravity(false);
                },2L);
            } else {
                entity.setGravity(false);
            }
        }
    }
}
