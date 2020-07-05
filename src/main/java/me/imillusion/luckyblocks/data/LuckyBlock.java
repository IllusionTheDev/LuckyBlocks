package me.imillusion.luckyblocks.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
public class LuckyBlock {

    private String permission;
    private LootTable<Reward> rewards;
    private Material material;

}
