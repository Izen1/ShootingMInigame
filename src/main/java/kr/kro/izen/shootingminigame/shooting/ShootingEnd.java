package kr.kro.izen.shootingminigame.shooting;

import kr.kro.izen.shootingminigame.command.ShooterCommand;
import kr.kro.izen.shootingminigame.timer.Timer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ShootingEnd {
    public static void end(Player player) {
        TargetManger targetManger = new TargetManger();
        ProjectileManger projectileManger = new ProjectileManger();
        Timer timer = new Timer();
        if (!ShooterCommand.starting) {
            player.sendMessage("게임이 아직 시작하지 않았습니다.");
            return;
        }
        ShooterCommand.starting = false;
        targetManger.removeTarget(player, Material.AIR);
        projectileManger.returnArrowAmount(player);
        projectileManger.removeArrow(player);
        timer.endTimer(player);
    }
}
