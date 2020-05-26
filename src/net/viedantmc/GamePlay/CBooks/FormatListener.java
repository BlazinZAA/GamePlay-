package net.viedantmc.GamePlay.CBooks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

public class FormatListener implements Listener {
    @EventHandler
    public void onCloseBook(PlayerEditBookEvent event) {
        if (!event.isSigning())
            return;
        BookMeta book = event.getNewBookMeta();
        book = Main.formatBook(book);
        event.setNewBookMeta(book);
    }
}
