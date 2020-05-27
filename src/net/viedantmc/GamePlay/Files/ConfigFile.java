package net.viedantmc.GamePlay.Files;

import java.io.File;

public class ConfigFile extends FileManager {
    private boolean generated = false;

    public ConfigFile() {
        super(new File("plugins/GamePlayPlus", "config.yml"));
    }

    public void create() {
        if (getConfig().get("settings") == null) {
            getConfig().set("ArmorStandSettings.betterchairs", Boolean.valueOf(false));
            getConfig().set("ArmorStandSettings.block-stacking", Boolean.valueOf(false));
            this.generated = true;
        }
        if (this.generated)
            save();
    }
}
