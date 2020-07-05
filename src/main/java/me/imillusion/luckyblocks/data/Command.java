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
        if(this.sender == CommandSenderType.PLAYER)
            player.chat("/" + command.replaceAll("%player%", player.getName()));
        else
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("%player%", player.getName()));
    }
}
