package kr.kro.izen.shootingminigame.world;

import kr.kro.izen.shootingminigame.shooting.TargetManger;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiniGame extends BukkitCommand {
    public MiniGame(String name) {
        super(name);
    }

    public static Map<Player, Location> playerMap = new HashMap<>();

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String command, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length == 0) {
            player.sendMessage("/미니게임 <이동, 귀환>");
            return true;
        }
        if (args.length == 1) {
            switch (args[0]) {
                case "이동" -> {
                    playerMap.put(player, player.getLocation());
                    World world = worldMake("shooter");
                    player.teleport(new Location(world, 0.5, world.getHighestBlockYAt(0, 0) + 1, 0.5));
                    player.sendMessage("미니게임 월드로 이동합니다 !!");
                }
                case "귀환" -> {
                    player.teleport(playerMap.get(player));
                    playerMap.remove(player);
                    player.sendMessage("플레이어의 원위치로 이동합니다!");
                }
                case "생성" -> {
                    TargetManger targetManger = new TargetManger();
                    targetManger.creatTarget(player.getLocation(), player);
                }
            }
        }
        return false;
    }

    private World worldMake(String worldName) {
        WorldCreator worldCreator = new WorldCreator(worldName);
        worldCreator.type(WorldType.FLAT);
        worldCreator.generateStructures(false);
        return worldCreator.createWorld();
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) return Arrays.asList("이동", "귀환");
        return null;
    }
}
