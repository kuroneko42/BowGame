package org.kuroneko42.bowgame.timer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.kuroneko42.bowgame.BowGame;

public class Timer {

    private BukkitTask timer;
    public static int time;

    public void startTimer() {
        timer = new BukkitRunnable() {
            @Override
            public void run() {
                ++time;
            }
        }.runTaskTimer(BowGame.getInstance(), 0L, 1L);
    }

    public void stopTimer() {
        if (timer != null && !timer.isCancelled()) {
            timer.cancel();
            timer = null;
        }
        Bukkit.broadcastMessage("게임종료 \n" + "게임 시간: " + time / 20 +"초");
        time = 0;
    }

}
