package net.viedantmc.GamePlay.ArmorStandFix;


import de.joeyplayztv.armorstandcrashfix.files.ConfigFile;
import de.joeyplayztv.armorstandcrashfix.files.FileManager;
import de.joeyplayztv.armorstandcrashfix.listeners.BlockListener;
import de.joeyplayztv.armorstandcrashfix.listeners.EntityListener;
import net.viedantmc.GamePlay.ArmorStandFix.PlayerListener;
import java.io.File;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorStandCrashFix extends JavaPlugin {
    private FileManager configfile;

    public void onEnable() {
        setUpFiles();
        registerListener();
    }

    private void setUpFiles() {
        File dic = new File("plugins/ArmorStandCrashFix");
        if (!dic.isDirectory())
            dic.mkdir();
        this.configfile = (FileManager)new ConfigFile();
    }

    private void registerListener() {
        PluginManager pluginmanager = getServer().getPluginManager();
        pluginmanager.registerEvents((Listener)new EntityListener(this), (Plugin)this);
        pluginmanager.registerEvents((Listener)new PlayerListener(), (Plugin)this);
        pluginmanager.registerEvents((Listener)new BlockListener(this), (Plugin)this);
    }

    public FileManager getConfigfile() {
        return this.configfile;
    }
}