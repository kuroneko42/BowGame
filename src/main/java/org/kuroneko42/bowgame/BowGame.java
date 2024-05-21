package org.kuroneko42.bowgame;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.kuroneko42.bowgame.commands.GameCommand;
import org.kuroneko42.bowgame.event.GameListener;

public final class BowGame extends JavaPlugin {


    private static BowGame instance;

    @Override
    public void onEnable() {
        Bukkit.getLogger().warning("플러그인 활성화");
        instance = this;
        Bukkit.getCommandMap().register("", new GameCommand());

        Bukkit.getPluginManager().registerEvents(new GameListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().warning("플러그인 비활성화");
    }

    public static BowGame getInstance() {
        return instance;
    }
}
