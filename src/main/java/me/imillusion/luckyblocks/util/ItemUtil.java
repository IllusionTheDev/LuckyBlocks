package me.imillusion.luckyblocks.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtil {

    public ItemStack fromSection(ConfigurationSection section)
    {
        Material material = Material.valueOf(section.getString("type"));
        String name = section.getString("name", "");
        int amount = section.getInt("amount", 1);

        List<String> lore = section.getStringList("lore");

        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        if (!lore.isEmpty()) {
            for (int i = 0; i < lore.size(); i++)
                lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
            meta.setLore(lore);
        }

        if (section.contains("enchants")) {
            for (String s : section.getConfigurationSection("enchants").getKeys(false)) {
                int level = section.getInt("enchants." + s + ".level", 1);
                Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(section.getString("enchants." + s + ".enchant").toLowerCase()));
                if (enchantment == null) {
                    Bukkit.getLogger().info("Enchantment " + section.getString("enchants." + s + ".enchant") + " could not be loaded");
                    continue;
                }
                meta.addEnchant(enchantment, level, true);
            }
        }

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        item.setItemMeta(meta);
        return item;
    }
}
