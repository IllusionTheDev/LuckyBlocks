package me.imillusion.luckyblocks.manager;

import lombok.Getter;
import me.imillusion.luckyblocks.LuckyBlocks;
import me.imillusion.luckyblocks.data.Command;
import me.imillusion.luckyblocks.data.LootTable;
import me.imillusion.luckyblocks.data.LuckyBlock;
import me.imillusion.luckyblocks.data.Reward;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuckyBlockManager {

    @Getter
    private Map<String, List<LuckyBlock>> luckyblocks = new HashMap<>();

    private LuckyBlocks main;

    public LuckyBlockManager(LuckyBlocks main) {
        this.main = main;

        load();
    }

    private void load()
    {
        FileConfiguration config = main.getFileManager().getLuckyBlocks();

        ConfigurationSection section = config.getConfigurationSection("luckyblocks");

        for (String world : section.getKeys(false)) {
            List<LuckyBlock> list = new ArrayList<>();

            for (String id : section.getConfigurationSection(world).getKeys(false)) {
                String permission = section.getString(world + "." + id + ".permission");
                Material material = Material.valueOf(section.getString(world + "." + id + ".type"));

                ConfigurationSection items = section.getConfigurationSection(world + "." + id + ".drops.items");
                ConfigurationSection commands = section.getConfigurationSection(world + "." + id + ".drops.commands");

                LootTable<Reward> rewards = new LootTable<>();

                if (items != null)
                    for (String item : items.getKeys(false)) {
                        List<String> messages = items.getStringList(item + ".messages");
                        int weight = items.getInt(item + ".weight");
                        List<ItemStack> itemsList = new ArrayList<>();

                        for (String i : items.getConfigurationSection(item + ".items").getKeys(false))
                            itemsList.add(main.getItemUtil().fromSection(items.getConfigurationSection(item + ".items." + i)));

                        rewards.register(new Reward(new ArrayList<>(), itemsList, messages), weight);
                    }

                if (commands != null)
                    for (String cmd : commands.getKeys(false)) {
                        List<String> messages = commands.getStringList(cmd + ".messages");
                        int weight = commands.getInt(cmd + ".weight");
                        List<String> commandList = commands.getStringList(cmd + ".commands");

                        List<Command> l = new ArrayList<>();

                        commandList.forEach(s -> l.add(main.getCommandManager().load(s)));

                        rewards.register(new Reward(l, new ArrayList<>(), messages), weight);
                    }

                list.add(new LuckyBlock(permission, rewards, material));
            }


            luckyblocks.put(world, list);
        }
    }
}
