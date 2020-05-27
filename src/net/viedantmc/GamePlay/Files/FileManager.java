package net.viedantmc.GamePlay.Files;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public abstract class FileManager {
    private File file;

    private YamlConfiguration config;

    public FileManager(File file) {
        this.file = file;
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        this.config = YamlConfiguration.loadConfiguration(file);
        create();
        load();
    }

    public void create() {}

    public void load() {}

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            this.config.load(this.file);
        } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return this.file;
    }

    public YamlConfiguration getConfig() {
        return this.config;
    }

    public String getString(String key) {
        if (this.config.get(key) != null)
            return this.config.getString(key);
        return key;
    }

    public String getMessage(String key) {
        if (this.config.get(key) != null)
            return this.config.getString(key).replace("&", "");
        return key;
    }

    public int getInt(String key) {
        return this.config.getInt(key);
    }

    public double getDouble(String key) {
        return this.config.getDouble(key);
    }

    public boolean getBoolean(String key) {
        return this.config.getBoolean(key);
    }

    public ItemStack getItemStack(String key) {
        return this.config.getItemStack(key);
    }
}
