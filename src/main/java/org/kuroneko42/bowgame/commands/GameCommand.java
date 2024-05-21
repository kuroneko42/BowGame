package org.kuroneko42.bowgame.commands;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kuroneko42.bowgame.items.ItemManager;
import org.kuroneko42.bowgame.target.TargetManager;
import org.kuroneko42.bowgame.timer.Timer;

import java.util.List;

public class GameCommand extends BukkitCommand {
    public GameCommand() {
        super("bow");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (args.length == 0) {
            player.sendMessage("/bow start");
            player.sendMessage("/bow stop");
            return false;
        }
        Timer timer = new Timer();
        String args0 = args[0];
        if (args0.equals("start")) {
            timer.startTimer();
            TargetManager.createTarget(player);
            ItemManager.getArrow(player, 30, 10, 10);
            ItemManager.getBow(player);
            player.sendMessage("게임 시작");
            return false;
        }

        if (args0.equals("stop")) {
            timer.stopTimer();
            Bukkit.broadcastMessage("남은 화살: " + ItemManager.checkArrowCount(player) + " 남은 과녁: " + TargetManager.checkTarget());
            TargetManager.removeTarget();
            player.getInventory().clear();
            return false;
        }

        return false;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return Lists.newArrayList("start", "stop");
        }
        return super.tabComplete(sender, alias, args);
    }
}
