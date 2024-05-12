package org.kuroneko42.bowgame;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.kuroneko42.bowgame.commands.GameCommand;
import org.kuroneko42.bowgame.game.GameListener;

public final class BowGame extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getCommandMap().register("", new GameCommand());

        Bukkit.getPluginManager().registerEvents(new GameListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
