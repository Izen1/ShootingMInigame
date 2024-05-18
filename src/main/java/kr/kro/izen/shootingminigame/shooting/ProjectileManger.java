package kr.kro.izen.shootingminigame.shooting;

import kr.kro.izen.shootingminigame.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ProjectileManger implements ProjectileBuilder{

    @Override
    public void givingBow(Player player) {
        player.getInventory().addItem(ItemBuilder.addEnchantment(Material.BOW, ChatColor.LIGHT_PURPLE + "사격용 활", Enchantment.ARROW_INFINITE, 1));
    }

    @Override
    public void arrowNormal(Player player) {
        ItemStack arrow = ItemBuilder.createItem(Material.ARROW, "§f일반 화살", 30);
        player.getInventory().addItem(arrow);
    }

    @Override
    public void removeArrow(Player player) {
        player.getInventory().remove(Material.ARROW);
        player.getInventory().remove(Material.BOW);
        player.getInventory().getItemInOffHand().setAmount(0);
    }

    @Override
    public int getArrowCount(Player player, Material material, String name) {
        Inventory inventory = player.getInventory();
        int count = 0;

        // 오른손에 있는 아이템 갯수 포함
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == material && item.getItemMeta().getDisplayName().equals(name)) {
                count += item.getAmount();
            }
        }
        return count;
    }

    @Override
    public void returnArrowAmount(Player player) {
        int normalArrow = getArrowCount(player, Material.ARROW, "§f일반 화살");
        int widthArrow = getArrowCount(player, Material.ARROW, "§6가로 화살");
        int heightArrow = getArrowCount(player, Material.ARROW, "§b세로 화살");

        player.sendMessage(String.format("일반 화살 : %d , 가로 화살 : %d, 세로 화살 : %d 개 남았습니다.", normalArrow, widthArrow, heightArrow));
    }

    @Override
    public void arrowWidth(Player player) {
        ItemStack arrow = ItemBuilder.createItem(Material.ARROW, "§6가로 화살", 10);
        player.getInventory().addItem(arrow);
    }

    @Override
    public boolean allArrowAmount(Player player) {
        int normalArrow = getArrowCount(player, Material.ARROW, "§f일반 화살");
        int widthArrow = getArrowCount(player, Material.ARROW, "§6가로 화살");
        int heightArrow = getArrowCount(player, Material.ARROW, "§b세로 화살");
        return normalArrow + widthArrow + heightArrow == 0;
    }

    @Override
    public void arrowHeight(Player player) {
        ItemStack arrow = ItemBuilder.createItem(Material.ARROW, "§b세로 화살", 10);
        player.getInventory().addItem(arrow);
    }
}
