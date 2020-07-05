package me.imillusion.luckyblocks.listener;

import me.imillusion.luckyblocks.LuckyBlocks;
import me.imillusion.luckyblocks.data.LuckyBlock;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;

public class BlockBreakListener implements Listener {

    private LuckyBlocks main;

    public BlockBreakListener(LuckyBlocks main) {
        this.main = main;
    }

    @EventHandler
    private void onBreak(BlockBreakEvent e)
    {
        World world = e.getBlock().getWorld();

        if (!main.getLuckyBlockManager().getLuckyblocks().containsKey(world.getName()))
            return;

        Player p = e.getPlayer();

        List<LuckyBlock> luckyBlocks = main.getLuckyBlockManager().getLuckyblocks().get(world.getName());

        if (luckyBlocks.stream().noneMatch(l -> l.getMaterial() == e.getBlock().getType()))
            return;

        LuckyBlock luckyBlock = luckyBlocks.stream().filter(l -> l.getMaterial() == e.getBlock().getType()).findFirst().get();

        if (!p.hasPermission(luckyBlock.getPermission())) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getFileManager().getMessages().getString("no-permission-break")));
            e.setCancelled(true);
            return;
        }

        Location loc = e.getBlock().getLocation();

        e.setCancelled(true);
        e.setDropItems(false);
        e.getBlock().setType(Material.AIR);

        luckyBlock.getRewards().getRandom().apply(loc, p);
    }
}
