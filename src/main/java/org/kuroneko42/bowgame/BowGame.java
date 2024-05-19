package org.kuroneko42.bowgame;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.kuroneko42.bowgame.commands.GameCommand;
import org.kuroneko42.bowgame.game.GameListener;

public final class BowGame extends JavaPlugin {

    public static final String PLUGIN_ID = "arrow";

    private static BowGame instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getCommandMap().register("", new GameCommand());

        Bukkit.getPluginManager().registerEvents(new GameListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static BowGame getInstance() {
        return instance;
    }
}
