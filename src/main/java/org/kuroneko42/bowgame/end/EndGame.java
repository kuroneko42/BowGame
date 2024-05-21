package org.kuroneko42.bowgame.end;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kuroneko42.bowgame.items.ItemManager;
import org.kuroneko42.bowgame.target.TargetManager;
import org.kuroneko42.bowgame.timer.Timer;

public class EndGame {

    private void checkGameStatus() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            int remainingArrows = ItemManager.checkArrowCount(player);
            int remainingTargets = TargetManager.checkTarget();

            // 화살이 없거나 과녁이 모두 사라진 경우 게임 종료
            if (remainingArrows == 0 || remainingTargets == 0) {
                stopGame(player);
            }
        }
    }

    public static void stopGame(Player player) {
        Timer timer = new Timer();
        timer.stopTimer();
        int remainingArrows = ItemManager.checkArrowCount(player);
        int remainingTargets = TargetManager.checkTarget();
        if (remainingArrows == 0 || remainingTargets == 0) {
            Bukkit.broadcastMessage("게임이 종료 되었습니다.");
        } else {
            Bukkit.broadcastMessage("게임 종료: 화살 " + remainingArrows + "개, 과녁 " + remainingTargets + "개가 남았습니다.");
        }
        TargetManager.removeTarget();
        player.getInventory().clear();
    }

}
