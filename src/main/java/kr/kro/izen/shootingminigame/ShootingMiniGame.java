package kr.kro.izen.shootingminigame;

import kr.kro.izen.shootingminigame.command.ShooterCommand;
import kr.kro.izen.shootingminigame.event.ProjectileEvent;
import kr.kro.izen.shootingminigame.world.MiniGame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShootingMiniGame extends JavaPlugin {

    public static ShootingMiniGame plugin;
    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getCommandMap().register("미니게임", new MiniGame("미니게임"));
        Bukkit.getCommandMap().register("사격", new ShooterCommand("사격"));
        Bukkit.getPluginManager().registerEvents(new ProjectileEvent(), this);
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
