package net.viedantmc.GamePlay.CBooks;
import net.md_5.bungee.api.ChatColor;
import net.viedantmc.GamePlay.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class PreviewCommand implements CommandExecutor {
    private Main plugin = (Main)JavaPlugin.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0)
            return false;
        if (args[0].equals("info")) {
            sender.sendMessage(ChatColor.YELLOW + "[ColoredBooks]" + ChatColor.GRAY + " Version " + this.plugin.getDescription().getVersion());
            return true;
        }
        if (args[0].equals("preview")) {
            if (!sender.hasPermission("coloredbooks.preview")) {
                sender.sendMessage(ChatColor.YELLOW + "[ColoredBooks]" + ChatColor.RED + " You do not have permission to run this command.");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.YELLOW + "[ColoredBooks]" + ChatColor.RED + " This command cannot be run from the console.");
                return true;
            }
            Player ply = (Player)sender;
            ItemStack item = ply.getInventory().getItemInMainHand();
            if (!item.getType().equals(Material.WRITABLE_BOOK)) {
                sender.sendMessage(ChatColor.YELLOW + "[ColoredBooks]" + ChatColor.RED + " Hold a book and quill in your main hand to view a preview of it.");
                return true;
            }
            BookMeta meta = (BookMeta)item.getItemMeta();
            meta.setGeneration(BookMeta.Generation.TATTERED);
            meta.setTitle("Preview");
            meta.setAuthor("");
            meta = Main.formatBook(meta);
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            book.setItemMeta((ItemMeta)meta);
            ply.openBook(book);
            return true;
        }
        if (args[0].equals("copy")) {
            if (!sender.hasPermission("coloredbooks.copy")) {
                sender.sendMessage(ChatColor.YELLOW + "[ColoredBooks]" + ChatColor.RED + " You do not have permission to run this command.");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.YELLOW + "[ColoredBooks]" + ChatColor.RED + " This command cannot be run from the console.");
                return true;
            }
            String title = "Preview Copy";
            Player ply = (Player)sender;
            ItemStack item = ply.getInventory().getItemInMainHand();
            if (!item.getType().equals(Material.WRITABLE_BOOK)) {
                sender.sendMessage(ChatColor.YELLOW + "[ColoredBooks]" + ChatColor.RED + " Hold a book and quill in your main hand to make a copy of it.");
                return true;
            }
            if (args.length > 1) {
                title = "";
                for (int i = 1; i < args.length; i++)
                    title = String.valueOf(title) + args[i] + " ";
                title = title.trim();
                if (title.length() > 16) {
                    sender.sendMessage(ChatColor.YELLOW + "[ColoredBooks]" + ChatColor.RED + " Title cannot be more than 16 letters long.");
                    return true;
                }
            }
            BookMeta meta = (BookMeta)item.getItemMeta();
            meta.setGeneration(BookMeta.Generation.COPY_OF_COPY);
            meta.setTitle(title);
            meta.setAuthor(ply.getName());
            meta = Main.formatBook(meta);
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            book.setItemMeta((ItemMeta)meta);
            ply.getInventory().addItem(new ItemStack[] { book });
            return true;
        }
        return false;
    }
}
