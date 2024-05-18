package kr.kro.izen.shootingminigame.shooting;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class TargetManger implements TargetBuilder{
    @Override
    public boolean enoughDistance(@NotNull Location location, Location location1, Player player) {
        int loc1X = location.getBlockX();
        int loc2X = location1.getBlockX();

        int loc1Z = location.getBlockZ();
        int loc2Z = location1.getBlockZ();

        int diffX = Math.abs(loc1X - loc2X);
        int diffZ = Math.abs(loc1Z - loc2Z);

        switch (player.getFacing()) {
            case NORTH, SOUTH -> {
                return diffZ >= 20;
            }
            case EAST, WEST -> {
                return diffX >= 20;
            }
            default -> {
                return false;
            }
        }
    }

    public static Map<Player, Location> targetMap = new HashMap<>();

    @Override
    public void creatTarget(@NotNull Location location, Player player) {
        World world = Bukkit.getWorld("shooter");

        int xSize = 7;
        int ySize = 7;
        int zSize = 1;

        int startX = location.getBlockX();
        int startY = location.getBlockY() + 1;
        int startZ = location.getBlockZ();


        switch (player.getFacing()) {
            case NORTH -> {
                startZ -= 20;
            }
            case EAST -> {
                startX += 20;
                xSize = 1;
                zSize = 7;
            }
            case SOUTH -> {
                startZ += 20;
            }
            case WEST -> {
                startX -= 20;
                xSize = 1;
                zSize = 7;
            }
        }

        Location startLoc = new Location(world, startX, startY , startZ);
        targetMap.put(player, startLoc);

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
    public int countTarget(@NotNull Player player) {
        Location location = targetMap.get(player);
        World world = location.getWorld();

        int xSize = 7;
        int ySize = 7;
        int zSize = 1;

        int startX = location.getBlockX();
        int startY = location.getBlockY();
        int startZ = location.getBlockZ();

        switch (player.getFacing()) {
            case EAST, WEST -> {
                xSize = 1;
                zSize = 7;
            }
        }

        int countBlocks = 0;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                for (int z = 0; z < zSize; z++) {
                    Location blockLocation = new Location(world, startX - xSize / 2 + x, startY + y, startZ - zSize / 2 + z);
                    if (world != null && blockLocation.getBlock().getType() == Material.TARGET) {
                        countBlocks++;
                    }
                }
            }
        }
        return countBlocks;
    }

    @Override
    public void removeTarget(@NotNull Player player, Material material) {
        Location location = targetMap.get(player);
        World world = location.getWorld();

        int xSize = 7;
        int ySize = 7;
        int zSize = 1;

        int startX = location.getBlockX();
        int startY = location.getBlockY();
        int startZ = location.getBlockZ();

        switch (player.getFacing()) {
            case EAST, WEST -> {
                xSize = 1;
                zSize = 7;
            }
        }

        int changeBlock = 0;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                for (int z = 0; z < zSize; z++) {
                    Location blockLocation = new Location(world, startX - xSize / 2 + x, startY + y, startZ - zSize / 2 + z);
                    if (world != null && blockLocation.getBlock().getType() == Material.TARGET) {
                        world.getBlockAt(blockLocation).setType(material);
                        changeBlock++;
                    }
                }
            }
        }
        player.sendMessage(changeBlock + " 개의 블럭이 남았습니다 !!");
    }

    @Override
    public void removeTargetNormal(@NotNull Location location) {
        World world = location.getWorld();
        world.getBlockAt(location).setType(Material.AIR);
    }

    @Override
    public void removeTargetWidth(@NotNull Location location, Player player) {
        World world = location.getWorld();
        int canterX = location.getBlockX();
        int canterZ = location.getBlockZ();

        int xSize = 3;
        int zSize = 3;

        switch (player.getFacing()) {
            case NORTH, SOUTH -> {
                for (int x = 0; x < xSize; x++) {
                    Location location1 = new Location(world, canterX - xSize / 2 + x, location.getY(), canterZ);
                    world.getBlockAt(location1).setType(Material.AIR);
                }
            }
            case EAST, WEST -> {
                for (int z = 0; z < zSize; z++) {
                    Location location1 = new Location(world, canterX, location.getY(), canterZ - zSize / 2 + z);
                    world.getBlockAt(location1).setType(Material.AIR);
                }
            }
        }
    }

    @Override
    public void removeTargetHeight(@NotNull Location location, Player player) {
        World world = location.getWorld();
        int canterY = location.getBlockY();

        int ySize = 3;

        for (int y = 0; y < ySize; y++) {
            Location location1 = new Location(world, location.getX(), canterY - ySize / 2 + y, location.getZ());
            world.getBlockAt(location1).setType(Material.AIR);
        }
    }
}
