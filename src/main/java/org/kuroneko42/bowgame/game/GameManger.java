package org.kuroneko42.bowgame.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GameManger {
    public static boolean distanceCheck (@NotNull Location location1, Location location2) {
        int loc1 = location1.getBlockZ();
        int loc2 = location2.getBlockZ();

        int distanceZ = Math.abs(loc1 - loc2);

        return distanceZ >= 20;
    }
}
