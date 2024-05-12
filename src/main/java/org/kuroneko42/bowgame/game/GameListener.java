package org.kuroneko42.bowgame.game;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack handItem = player.getInventory().getItemInMainHand();

        if (handItem.getType() == Material.BOW && event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            ItemStack arrowToUse = null;

            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null && item.getItemMeta().hasDisplayName()) {
                    String displayName = item.getItemMeta().getDisplayName();

                    if (displayName.equals("일반 화살")) {
                        arrowToUse = normalArrow();
                        break;
                    } else if (displayName.equals("가로 화살")) {
                        arrowToUse = horizontalArrow();
                        break;
                    } else if (displayName.equals("세로 화살")) {
                        arrowToUse = verticalArrow();
                        break;
                    }
                }
            }

            if (arrowToUse != null) {
                player.launchProjectile(Arrow.class);
            } else {
                event.setCancelled(true);
            }
        }
    }

    public static ItemStack getBow() {
        ItemStack bowItem = new ItemStack(Material.BOW);
        ItemMeta itemMeta = bowItem.getItemMeta();
        itemMeta.setDisplayName("기본 활");
        bowItem.setItemMeta(itemMeta);

        return bowItem;
    }

    public static ItemStack normalArrow() {
        ItemStack normalItem = new ItemStack(Material.ARROW, 30);
        ItemMeta itemMeta = normalItem.getItemMeta();
        itemMeta.setDisplayName("일반 화살");
        normalItem.setItemMeta(itemMeta);

        return normalItem;
    }

    public static ItemStack horizontalArrow() {
        ItemStack horizontalItem = new ItemStack(Material.ARROW, 10);
        ItemMeta itemMeta = horizontalItem.getItemMeta();
        itemMeta.setDisplayName("가로 화살");
        horizontalItem.setItemMeta(itemMeta);

        return horizontalItem;
    }

    public static ItemStack verticalArrow() {
        ItemStack verticaItem = new ItemStack(Material.ARROW, 10);
        ItemMeta itemMeta = verticaItem.getItemMeta();
        itemMeta.setDisplayName("세로 화살");
        verticaItem.setItemMeta(itemMeta);

        return verticaItem;
    }
}
