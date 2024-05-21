package org.kuroneko42.bowgame.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class ItemManager {

    private static final NamespacedKey KEY = new NamespacedKey("arrow", "type");

    public static ItemStack createArrow(int amount, String type, String name, String lore1) {
        ItemStack customArrow = new ItemStack(Material.ARROW, amount);
        ItemMeta meta = customArrow.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore1));

        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(KEY, PersistentDataType.STRING, type);

        customArrow.setItemMeta(meta);
        return customArrow;
    }

    public static void getArrow(Player player, int amount1, int amount2, int amount3) {
        player.getInventory().addItem(createArrow(amount1, "NORMAL", "일반 화살", "한칸 제거"));
        player.getInventory().addItem(createArrow(amount2, "HORIZONTAL", "가로 화살", "좌우 제거"));
        player.getInventory().addItem(createArrow(amount3, "VERTICAL", "세로 화살", "상하 제거"));

    }

    public static int checkArrowCount(Player player) {
        int arrowCount = 0;

        ItemStack[] contents = player.getInventory().getContents();
        for (ItemStack item : contents) {
            if (item != null && item.getType() == Material.ARROW) {
                arrowCount += item.getAmount();
            }
        }

        return arrowCount;
    }

    public static void getBow(Player player) {
        player.getInventory().addItem(new ItemStack(Material.BOW));
    }

    public static String readArrow(ItemStack item) {
        if (item == null || item.getType() != Material.ARROW) {
            return "";
        }

        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return "";
        }
        PersistentDataContainer data = meta.getPersistentDataContainer();
        return data.getOrDefault(KEY, PersistentDataType.STRING, "");
    }
}
