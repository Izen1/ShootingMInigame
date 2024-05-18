package kr.kro.izen.shootingminigame.shooting;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ProjectileBuilder {
    void givingBow(Player player);
    void arrowNormal(Player player);
    void arrowWidth(Player player);
    void arrowHeight(Player player);
    void removeArrow(Player player);
    int getArrowCount(Player player, Material arrowType, String name);
    void returnArrowAmount(Player player);
    boolean allArrowAmount(Player player);
}
