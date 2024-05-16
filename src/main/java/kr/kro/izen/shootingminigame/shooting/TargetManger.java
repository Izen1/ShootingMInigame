package kr.kro.izen.shootingminigame.shooting;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class TargetManger implements TargetBuilder{
    @Override
    public void creatTarget(@NotNull Location location, Player player) {
        World world = Bukkit.getWorld("shooter");

        int startX = location.getBlockX();
        int startY = location.getBlockY() + 1;
        int startZ = location.getBlockZ();

        // Block dimensions
        int xSize = 7;
        int ySize = 7;
        int zSize = 1;

        switch (player.getFacing()) {
            case NORTH -> {
                startZ = startZ - 20;
            }
            case EAST -> {
                startX = startX + 20;
                xSize = 1;
                zSize = 7;
            }
            case SOUTH -> {
                startZ = startZ + 20;
            }
            case WEST -> {
                startX = startX - 20;
                xSize = 1;
                zSize = 7;
            }
        }

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                for (int z = 0; z < zSize; z++) {
                    Location blockLocation = new Location(world, startX - xSize / 2 + x, startY + y, startZ - zSize / 2 + z);
                    if (world != null) {
                        world.getBlockAt(blockLocation).setType(Material.TARGET);
                    }
                }
            }
        }
    }

    @Override
    public void removeTarget(@NotNull Block block) {

    }

    @Override
    public void removeTargetWidth(@NotNull Block block) {

    }

    @Override
    public void removeTargetHeight(@NotNull Block block) {

    }
}
