package me.imillusion.luckyblocks.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
@AllArgsConstructor
public class Reward {

    private List<Command> commands;
    private List<ItemStack> items;
    private List<String> messages;

    public void apply(Location loc, Player breaker)
    {
        commands.forEach(c -> c.send(breaker));
        items.forEach(item -> loc.getWorld().dropItemNaturally(loc, item));

        for (String s : messages)
            breaker.sendMessage(ChatColor.translateAlternateColorCodes('&', s.replace("%player%", breaker.getName())));
    }

}
