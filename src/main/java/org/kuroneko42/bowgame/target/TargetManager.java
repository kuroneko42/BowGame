package org.kuroneko42.bowgame.target;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class TargetManager {

    private static final Set<Block> targetList = new HashSet<>();


    public static void createTarget(@NotNull Player player) {
        Location playerLoc = player.getLocation();
        Location targetLoc = playerLoc.add(0,3,21);

        for (int x = -3; x <= 3; x++) {
            for (int y = 0; y <= 6; y++) {
                Location addTargetLoc = targetLoc.clone().add(x, y,0);
                Block addTarget = addTargetLoc.getBlock();
                addTarget.setType(Material.TARGET);
                targetList.add(addTarget);
            }

        }
    }

    public static void removeTarget() {
        for (Block removeTarget : targetList) {
            removeTarget.setType(Material.AIR);
        }
        targetList.clear();
    }

    public static boolean distanceTarget(@NotNull Location playerLoc, Location blockLoc) {
        if (playerLoc.distance(blockLoc) < 20) {
            Bukkit.broadcastMessage("20칸 거리를 유지하세요");
            return false;
        }
        return true;
    }

    public static void brokenTarget(Block hitblock, String arrowType) {
        if (targetList.contains(hitblock) && arrowType.equals("NORMAL")) {
            removeTarget(hitblock);
        } else if (targetList.contains(hitblock) && arrowType.equals("HORIZONTAL")) {
            removeTarget(hitblock);
            removeTarget(hitblock.getRelative(1,0,0));
            removeTarget(hitblock.getRelative(-1,0,0));
        } else if (targetList.contains(hitblock) && arrowType.equals("VERTICAL")) {
            removeTarget(hitblock);
            removeTarget(hitblock.getRelative(0,1,0));
            removeTarget(hitblock.getRelative(0,-1,0));
        }
    }

    public static void removeTarget(Block hitBlock) {
        if(targetList.contains(hitBlock)) {
            targetList.remove(hitBlock);
            hitBlock.setType(Material.AIR);
        }
    }

    public static int checkTarget() {
        return targetList.size();
    }

}
