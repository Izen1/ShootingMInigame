package kr.kro.izen.shootingminigame.shooting;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public interface TargetBuilder {
    void creatTarget(@NotNull Location location, Player player);
    void removeTarget(@NotNull Player player, Material material);
    int countTarget(@NotNull Player player);
    void removeTargetNormal(@NotNull Location location);
    void removeTargetWidth(@NotNull Location location, Player player);
    void removeTargetHeight(@NotNull Location location, Player player);
    boolean enoughDistance(@NotNull Location location, Location location1, Player player);

}
