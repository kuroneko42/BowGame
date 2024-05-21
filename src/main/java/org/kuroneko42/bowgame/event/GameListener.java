package org.kuroneko42.bowgame.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.kuroneko42.bowgame.end.EndGame;
import org.kuroneko42.bowgame.items.ItemManager;
import org.kuroneko42.bowgame.target.TargetManager;

public class GameListener implements Listener {

    @EventHandler (priority = EventPriority.LOWEST)
    public void onShooter(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (ItemManager.readArrow(player.getInventory().getItemInOffHand()).equals("")) {
                event.setCancelled(true);
                player.sendMessage("화살을 왼손에 들어주세요");
            }
        }
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void onArrowHit(ProjectileHitEvent event) {
       if (!(event.getEntity().getShooter() instanceof Player player)) return;
        Block block = event.getHitBlock();

        if (block == null) {
            return;
        }

        Location hitLoc = block.getLocation();
        String arrowType = ItemManager.readArrow(player.getInventory().getItemInOffHand());
        event.getEntity().remove();

        if (block.getType() != Material.TARGET) return;
        if (!TargetManager.distanceTarget(player.getLocation(), hitLoc)) return;
        TargetManager.brokenTarget(block, arrowType);
    }
}
