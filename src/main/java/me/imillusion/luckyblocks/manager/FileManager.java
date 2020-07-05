package me.imillusion.luckyblocks.manager;

import lombok.Getter;
import me.imillusion.luckyblocks.LuckyBlocks;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private LuckyBlocks main;

    @Getter
    private FileConfiguration luckyBlocks;
    @Getter
    private FileConfiguration messages;

    public FileManager(LuckyBlocks main)
    {
        this.main = main;

        luckyBlocks = getYML(new File(main.getDataFolder(), "luckyblocks.yml"));
        messages = getYML(new File(main.getDataFolder(), "messages.yml"));
    }

    private FileConfiguration getYML(File file) {
        FileConfiguration cfg = new YamlConfiguration();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                main.saveResource(file.getName(), false);
            }

            cfg.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return cfg;
    }

}
