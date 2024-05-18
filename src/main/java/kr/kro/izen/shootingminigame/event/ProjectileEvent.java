package kr.kro.izen.shootingminigame.event;

import kr.kro.izen.shootingminigame.shooting.ProjectileManger;
import kr.kro.izen.shootingminigame.shooting.ShootingEnd;
import kr.kro.izen.shootingminigame.shooting.TargetManger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class ProjectileEvent implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void arrowHit(ProjectileHitEvent event) {

        Player player = (Player) event.getEntity().getShooter();
        ItemStack offHand = player.getInventory().getItemInOffHand();
        Block block = event.getHitBlock();
        Material material = block.getType();
        Location location = block.getLocation();
        TargetManger targetManger = new TargetManger();
        ProjectileManger projectileManger = new ProjectileManger();
        if (offHand.isEmpty()) return;
        //오프핸드 화살 감소
        offHand.setAmount(offHand.getAmount() - 1);
        //화살 남아있는 문제 제거
        Entity entity = event.getEntity();
        entity.remove();

        if (!targetManger.enoughDistance(player.getLocation(), location, player)) {
            player.sendMessage("20 칸 이상의 거리에서 사격해주세요 !");
            return;
        }
        if (material != Material.TARGET) {
            player.sendMessage("타겟 적중 실패 !");
            return;
        }
        switch (offHand.getItemMeta().getDisplayName()) {
            case "§f일반 화살" -> {
                targetManger.removeTargetNormal(location);
            }
            case "§6가로 화살" -> {
                targetManger.removeTargetWidth(location, player);
            }
            case "§b세로 화살" -> {
                targetManger.removeTargetHeight(location, player);
            }
        }
        if (targetManger.countTarget(player) == 0 || projectileManger.allArrowAmount(player)) {
            ShootingEnd.end(player);
        }
    }

}
