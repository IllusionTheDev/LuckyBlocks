package me.imillusion.luckyblocks.command;

import me.imillusion.luckyblocks.LuckyBlocks;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LuckyBlockReloadCommand implements CommandExecutor {

    private LuckyBlocks main;

    public LuckyBlockReloadCommand(LuckyBlocks main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!sender.hasPermission("luckyblocks.reload")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getFileManager().getMessages().getString("no-permission")));
            return true;
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getFileManager().getMessages().getString("reloaded")));
        main.onEnable();

        return true;
    }
}
