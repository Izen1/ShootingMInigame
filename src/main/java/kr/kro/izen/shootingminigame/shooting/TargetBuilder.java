package kr.kro.izen.shootingminigame.shooting;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public interface TargetBuilder {
    void creatTarget(@NotNull Location location, Player player);
    void removeTarget(@NotNull Block block);
    void removeTargetWidth(@NotNull Block block);
    void removeTargetHeight(@NotNull Block block);

}
