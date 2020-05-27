package net.viedantmc.GamePlay;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import net.viedantmc.GamePlay.Files.ConfigFile;
import net.viedantmc.GamePlay.Files.FileManager;
import net.viedantmc.GamePlay.ArmorStandFix.Listeners.BlockListener;
import net.viedantmc.GamePlay.ArmorStandFix.Listeners.EntityListener;
import net.viedantmc.GamePlay.CBooks.FormatListener;
import net.viedantmc.GamePlay.CBooks.PreviewCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private FileManager configfile;
    @Override
    public void onEnable() {
        setUpFiles();
        registerListener();
        getCommand("coloredbooks").setExecutor(new PreviewCommand());
        getServer().getPluginManager().registerEvents(new FormatListener(), (Plugin)this);
    }
    @Override
    public void onDisable() {}

    public static BookMeta formatBook(BookMeta book) {
        List<String> pages = book.getPages();
        List<String> newPages = new ArrayList<>();
        for (String page : pages)
            newPages.add(ChatColor.translateAlternateColorCodes('&', page));
        book.setPages(newPages);
        return book;
    }
    private void setUpFiles() {
        File dic = new File("plugins/GamePlayPlus");
        if (!dic.isDirectory())
            dic.mkdir();
        this.configfile = (FileManager)new ConfigFile();
    }
    private FileManager getPlugin() {
        return this.getPlugin();
    }

    private void registerListener() {
       // if (getPlugin().getConfig().getBoolean("ArmorStandSettings.enabled")) {
            PluginManager pluginmanager = getServer().getPluginManager();
            pluginmanager.registerEvents(new EntityListener(this), this);
            // pluginmanager.registerEvents((Listener)new PlayerListener(), (Plugin)this);
            pluginmanager.registerEvents(new BlockListener(this), this);
        }
    public FileManager getConfigfile() {
        return this.configfile;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("MultiPackageTest")){ //&& getPlugin().getConfig().getBoolean("ArmorStandSettings.multipackagetest")) {
            sender.sendMessage("IT WORKED");
            return true;
        }
        return false;
    }//Git test

}