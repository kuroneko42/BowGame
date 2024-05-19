package org.kuroneko42.bowgame.items;

import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.kuroneko42.bowgame.BowGame;

public class ItemArrow {

    private static final NamespacedKey ARROW_KEY = new NamespacedKey(BowGame.PLUGIN_ID, "arrow");

//    private static final Map<String, String> TAG_DATA = Maps.newHashMap();
//
//    static {
//        TAG_DATA.put(key, value) // Tag.set()
//        TAG_DATA.get(key) // Tag.get()
//        TAG_DATA.containsKey(key) // Tag.has();
//    }

    public static ItemStack createNormalArrow() { // create, make, generate, new;
        return new ItemBuilder(Material.ARROW)
                .setDisplayName("일반 화살")
                .setLore(Lists.newArrayList("과녁 하나를 부순다."))
                .setTag(ARROW_KEY, PersistentDataType.STRING, ArrowType.NORMAL.toString())
                .setAmount(30)
                .build();
    }

    // 1. 아이템 태그를 가져온다
    // 2. 가져온 태그와 ArrowType을 비교한다.
    // 3. 비교 결과를 true/false로 반환한다.
    public static boolean isNormalArrow(@NotNull ItemStack item) {
        String tag = ItemTagUtil.getTag(item, ARROW_KEY, PersistentDataType.STRING); // 얘가 1번
        return ArrowType.NORMAL.toString().equals(tag); // 2, 3번
    }

    public static ItemStack createHorizontalArrow() {
        return new ItemBuilder(Material.ARROW)
                .setDisplayName("가로 화살")
                .setLore(Lists.newArrayList("과녁 양옆으로 부순다."))
                .setTag(ARROW_KEY, PersistentDataType.STRING, ArrowType.HORIZONTAL.toString())
                .setAmount(10)
                .build();
    }
    public static boolean isHorizontalArrow(@NotNull ItemStack item) {
        String tag = ItemTagUtil.getTag(item, ARROW_KEY, PersistentDataType.STRING);
        return ArrowType.HORIZONTAL.toString().equals(tag);
    }

    public static ItemStack createVerticalArrow() {
        return new ItemBuilder(Material.ARROW)
                .setDisplayName("세로 화살")
                .setLore(Lists.newArrayList("과녁을 위아래로 부순다."))
                .setTag(ARROW_KEY, PersistentDataType.STRING, ArrowType.VERTICAL.toString())
                .setAmount(10)
                .build();
    }

    public static boolean isVerticalArrow(@NotNull ItemStack item) {
        String tag = ItemTagUtil.getTag(item, ARROW_KEY, PersistentDataType.STRING);
        return ArrowType.VERTICAL.toString().equals(tag);
    }

}
