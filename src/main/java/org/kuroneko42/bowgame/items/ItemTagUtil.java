package org.kuroneko42.bowgame.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemTagUtil {
    public static <T, Z> boolean hasTag(
            @Nullable ItemStack item, @NotNull NamespacedKey key,
            @NotNull PersistentDataType<T, Z> type) {
        if (item == null) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return false;
        }

        return meta.getPersistentDataContainer().has(key, type);
    }

    public static <T, Z> Z getTag(@NotNull ItemStack item, @NotNull NamespacedKey key, @NotNull PersistentDataType<T, Z> type) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return null;
        }

        return meta.getPersistentDataContainer().get(key, type); // nullable
    }
}
