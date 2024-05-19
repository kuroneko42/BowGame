package org.kuroneko42.bowgame.commands;

import com.google.common.collect.Lists;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.kuroneko42.bowgame.items.ItemArrow;

import java.util.ArrayList;
import java.util.List;

public class GameCommand extends BukkitCommand {
    public GameCommand() {
        super("bow");
    }

    List<Location> TargetLoc = new ArrayList<>();

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;
        World world = player.getWorld();
        if (args.length == 0) {
            player.sendMessage("/bow start");
            player.sendMessage("/bow stop");
            return false;
        }
        String args0 = args[0];
        if (args0.equals("start")) {
            ItemStack bow = new ItemStack(Material.BOW);
            addTarget(player);
            player.getInventory().addItem(getArrow());
            player.getInventory().addItem(bow);
            player.sendMessage("게임 시작");
            return false;
        }

        if (args0.equals("stop")) {
            removeTarget();
            player.getInventory().clear();
            player.sendMessage("게임 종료");
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

    public void addTarget(Player player) {
        Location playerLoc = player.getLocation();
        Location blockLoc = playerLoc.add(0,3,21);

        for (int x = -3; x <=3; x++) {
            for (int y = 0; y <= 6; y++){
                Location addBlockLoc = blockLoc.clone().add(x, y, 0);
                addBlockLoc.getBlock().setType(Material.TARGET);
                TargetLoc.add(addBlockLoc);
            }
        }
    }

    public void removeTarget() {
        for (Location removeTarget : TargetLoc) {
            removeTarget.getBlock().setType(Material.AIR);
        }
        TargetLoc.clear();
    }

    private ItemStack[] getArrow() {
        ItemStack[] arrows = new ItemStack[3];
        arrows[0] = ItemArrow.createNormalArrow();
        arrows[1] = ItemArrow.createHorizontalArrow();
        arrows[2] = ItemArrow.createVerticalArrow();
        return arrows;
    }
}
