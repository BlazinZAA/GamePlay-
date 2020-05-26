package net.viedantmc.GamePlay.CBooks;


import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void onEnable() {
        getCommand("coloredbooks").setExecutor(new PreviewCommand());
        getServer().getPluginManager().registerEvents(new FormatListener(), (Plugin)this);
        getCommand("MultiPackageTest").setExecutor(new net.viedantmc.GamePlay.ArmorStandFix.ArmorStandCrashFix());
    }

    public void onDisable() {}

    public static BookMeta formatBook(BookMeta book) {
        List<String> pages = book.getPages();
        List<String> newPages = new ArrayList<>();
        for (String page : pages)
            newPages.add(ChatColor.translateAlternateColorCodes('&', page));
        book.setPages(newPages);
        return book;
    }
}