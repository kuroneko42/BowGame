package org.kuroneko42.bowgame.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(@NotNull Material type) {
        item = new ItemStack(type);
        meta = item.getItemMeta();
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setDisplayName(@NotNull String name) {
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(@NotNull List<String> lore) {
        meta.setLore(lore);
        return this;
    }

    public <T, Z>ItemBuilder setTag(@NotNull NamespacedKey key, @NotNull PersistentDataType<T, Z> type, @NotNull Z value) {
        meta.getPersistentDataContainer().set(key, type, value);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item.clone();
    }
}
