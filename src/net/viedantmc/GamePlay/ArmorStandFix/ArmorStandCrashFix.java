package net.viedantmc.GamePlay.ArmorStandFix;


import net.viedantmc.GamePlay.ArmorStandFix.Files.ConfigFile;
import net.viedantmc.GamePlay.ArmorStandFix.Files.FileManager;
import net.viedantmc.GamePlay.ArmorStandFix.Listeners.BlockListener;
import net.viedantmc.GamePlay.ArmorStandFix.Listeners.EntityListener;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
       // pluginmanager.registerEvents((Listener)new PlayerListener(), (Plugin)this);
        pluginmanager.registerEvents((Listener)new BlockListener(this), (Plugin)this);
    }

    public FileManager getConfigfile() {
        return this.configfile;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("MultiPackageTest")) {
            sender.sendMessage("IT WORKED");
            return true;
        }
        return false;
    }
}