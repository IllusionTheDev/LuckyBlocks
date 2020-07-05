package me.imillusion.luckyblocks;

import lombok.Getter;
import me.imillusion.luckyblocks.command.LuckyBlockReloadCommand;
import me.imillusion.luckyblocks.listener.BlockBreakListener;
import me.imillusion.luckyblocks.manager.CommandManager;
import me.imillusion.luckyblocks.manager.FileManager;
import me.imillusion.luckyblocks.manager.LuckyBlockManager;
import me.imillusion.luckyblocks.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LuckyBlocks extends JavaPlugin {

    @Getter
    private FileManager fileManager;
    @Getter
    private LuckyBlockManager luckyBlockManager;
    @Getter
    private CommandManager commandManager;
    @Getter
    private ItemUtil itemUtil;

    private boolean enabled;

    @Override
    public void onEnable() {
        getLogger().info("Loaded");

        fileManager = new FileManager(this);
        commandManager = new CommandManager();
        itemUtil = new ItemUtil();
        luckyBlockManager = new LuckyBlockManager(this);

        if (!enabled) {
            Bukkit.getPluginManager().registerEvents(new BlockBreakListener(this), this);
            getCommand("luckyblockreload").setExecutor(new LuckyBlockReloadCommand(this));
        }

        enabled = true;
    }

}
