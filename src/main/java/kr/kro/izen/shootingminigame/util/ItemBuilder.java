package kr.kro.izen.shootingminigame.util;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class ItemBuilder {

    private static NamespacedKey KEY_TEST = new NamespacedKey("minigame", "test");

    public static ItemStack createItem(Material material, String name, int amount) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta mata = itemStack.getItemMeta();
        mata.setDisplayName(name);

        PersistentDataContainer data = mata.getPersistentDataContainer();
        data.set(KEY_TEST, PersistentDataType.INTEGER, 100);

        itemStack.setItemMeta(mata);
        return itemStack;
    }
    public static ItemStack createItem(Material material, String name) {
        return createItem(material, name, 1);
    }
    public static ItemStack addEnchantment(Material material, String name, Enchantment enchantment, int lev) {
        ItemStack itemStack = createItem(material, name);
        ItemMeta mata = itemStack.getItemMeta();
        mata.setUnbreakable(true);
        mata.addEnchant(enchantment, lev, true);
        itemStack.setItemMeta(mata);
        return itemStack;
    }

    public static int readTestData(@NotNull ItemStack itemStack) {
        ItemMeta mata = itemStack.getItemMeta();
        PersistentDataContainer data = mata.getPersistentDataContainer();
        return data.getOrDefault(KEY_TEST, PersistentDataType.INTEGER, -1);
    }
}
