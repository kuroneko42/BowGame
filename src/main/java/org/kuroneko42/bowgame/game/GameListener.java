package org.kuroneko42.bowgame.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.kuroneko42.bowgame.items.ItemArrow;

public class GameListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onArrowShooter(EntityShootBowEvent event) {
        Player player = (Player) event.getEntity();
        ItemStack leftHandItem = player.getInventory().getItemInOffHand();

        if (leftHandItem.getType() == Material.AIR) {
            event.setCancelled(true);
            player.sendMessage("왼손에 화살을 들어주세요");
        }
    }



    @EventHandler (priority = EventPriority.LOWEST)
    public void onArrowHit(ProjectileHitEvent event) {

        Player player = (Player) event.getEntity().getShooter();
        ItemStack leftHand = player.getInventory().getItemInOffHand();
        ItemMeta meta = leftHand.getItemMeta();
        Block block = event.getHitBlock();
        Material material = block.getType();
        Location loc = block.getLocation();
        Arrow arrow = (Arrow) event.getEntity();
        arrow.remove();

        if (!GameManger.distanceCheck(player.getLocation(), loc)) return;
        if (material != Material.TARGET) return;

        if (ItemArrow.isNormalArrow(leftHand)) {
            block.setType(Material.AIR);
        } else if (ItemArrow.isHorizontalArrow(leftHand)) {
            block.setType(Material.AIR);
            block.getRelative(1,0,0).setType(Material.AIR);
            block.getRelative(-1,0,0).setType(Material.AIR);
        } else if (ItemArrow.isVerticalArrow(leftHand)) {
            block.setType(Material.AIR);
            block.getRelative(0,1,0).setType(Material.AIR);
            block.getRelative(0,-1,0).setType(Material.AIR);
        }


//        if (!GameManger.distanceCheck(player.getLocation(), loc)) return;
//        if (material != Material.TARGET) return;
//        if (ItemArrow.isNormalArrow(leftHand)) {
//            block.setType(Material.AIR);
//        } else if (ItemArrow.isHorizontalArrow(leftHand)) {
//            block.setType(Material.AIR);
//            block.getRelative(1,0,0).setType(Material.AIR);
//            block.getRelative(-1,0,0).setType(Material.AIR);
//        } else if (ItemArrow.isVerticalArrow(leftHand)) {
//            block.setType(Material.AIR);
//            block.getRelative(0,1,0).setType(Material.AIR);
//            block.getRelative(0,-1,0).setType(Material.AIR);
//        }



//        if (event.getEntity() instanceof Arrow) {
//            Arrow arrow = (Arrow) event.getEntity();
//            ProjectileSource shooter = arrow.getShooter();
//
//            if (shooter instanceof Player) {
//                Player player = (Player) shooter;
//                ItemStack arrowItem = player.getInventory().getItemInOffHand();
//
//                if (event.getHitBlock() != null && event.getHitBlock().getType() == Material.TARGET) {
//                    Block targetBlock = event.getHitBlock();
//                    String arrowName = arrowItem.getItemMeta().getDisplayName();
//
//                    switch (arrowName) {
//                        case "일반 화살":
//                            targetBlock.setType(Material.AIR);
//                            break;
//                        case "가로 화살":
//                            targetBlock.setType(Material.AIR);
//                            targetBlock.getRelative(1,0,0).setType(Material.AIR);
//                            targetBlock.getRelative(-1,0,0).setType(Material.AIR);
//                            break;
//                        case "세로 화살":
//                            targetBlock.setType(Material.AIR);
//                            targetBlock.getRelative(0,1,0).setType(Material.AIR);
//                            targetBlock.getRelative(0,-1,0).setType(Material.AIR);
//                            break;
//                    }
//                    arrow.remove();
//                }
//            }
//        }
    }
}
