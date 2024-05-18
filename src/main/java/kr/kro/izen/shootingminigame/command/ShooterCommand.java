package kr.kro.izen.shootingminigame.command;

import kr.kro.izen.shootingminigame.shooting.ShootingEnd;
import kr.kro.izen.shootingminigame.timer.Timer;
import kr.kro.izen.shootingminigame.shooting.ProjectileManger;
import kr.kro.izen.shootingminigame.shooting.TargetManger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShooterCommand extends BukkitCommand {
    public ShooterCommand(@NotNull String name) {
        super(name);
    }

    public static boolean starting = false;
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String command, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length == 0) {
            player.sendMessage("/사격 시작, 종료");
            return true;
        }
        TargetManger targetManger = new TargetManger();
        ProjectileManger projectileManger = new ProjectileManger();
        Timer timer = new Timer();
        if (args.length == 1) {
            switch (args[0]) {
                case "시작" -> {
                    World world = Bukkit.getWorld("shooter");
                    if (!player.getWorld().equals(world)) {
                        player.sendMessage("올바른 월드에서 입력해주세요 !! \n/미니게임 이동");
                        return false;
                    }
                    if (starting) {
                        player.sendMessage("게임이 이미 시작되었습니다 !");
                        return false;
                    }
                    starting = true;
                    targetManger.creatTarget(player.getLocation(), player);
                    projectileManger.givingBow(player);
                    projectileManger.arrowNormal(player);
                    projectileManger.arrowWidth(player);
                    projectileManger.arrowHeight(player);
                    timer.timerStart();
                }
                case "종료" -> {
                    ShootingEnd.end(player);
                }
            }
        }
        return false;
    }
}
