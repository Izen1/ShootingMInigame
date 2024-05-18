package kr.kro.izen.shootingminigame.timer;

import kr.kro.izen.shootingminigame.ShootingMiniGame;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Timer {

    private BukkitTask timer;
    public static int time;
    public void timerStart() {
        timer = new BukkitRunnable() {
            @Override
            public void run() {
                ++time;
            }
        }.runTaskTimer(ShootingMiniGame.plugin, 0L, 1L);
    }

    public void endTimer(Player player) {
        if (timer != null && !timer.isCancelled()) {
            timer.cancel();
            timer = null;
        }
        player.sendMessage("게임이 종료 되었습니다 ! \n" + "게임 시간 : " + time / 20 + "초, 틱 : " + time);
        time = 0;
    }
}
