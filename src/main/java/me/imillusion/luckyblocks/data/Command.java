package me.imillusion.luckyblocks.data;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class Command {

    private CommandSenderType sender;
    private String command;

    public void send(Player player)
    {
        CommandSender sender = this.sender == CommandSenderType.PLAYER ? player : Bukkit.getConsoleSender();

        Bukkit.dispatchCommand(sender, (this.sender == CommandSenderType.PLAYER ? "/" : "") + command.replaceAll("%player%", player.getName()));
    }
}
