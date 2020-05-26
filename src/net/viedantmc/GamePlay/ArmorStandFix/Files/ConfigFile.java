package net.viedantmc.GamePlay.ArmorStandFix.Files;

import java.io.File;

public class ConfigFile extends FileManager {
    private boolean generated = false;

    public ConfigFile() {
        super(new File("plugins/ArmorStandCrashFix", "config.yml"));
    }

    public void create() {
        if (getConfig().get("settings") == null) {
            getConfig().set("settings.betterchairs", Boolean.valueOf(false));
            getConfig().set("settings.block-stacking", Boolean.valueOf(false));
            this.generated = true;
        }
        if (this.generated)
            save();
    }
}
