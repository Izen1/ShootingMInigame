package kr.kro.izen.shootingminigame;

import kr.kro.izen.shootingminigame.world.MiniGame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShootingMiniGame extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getCommandMap().register("미니게임", new MiniGame("미니게임"));
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
            onlinePlayer.teleport(MiniGame.playerMap.get(onlinePlayer));
            MiniGame.playerMap.clear();
        });
    }
}

// projectile
