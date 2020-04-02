package com.twyhzz.vanish.vanish;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface SessionProcessor {

    static @NotNull SessionProcessor simpleProcess(@NotNull final Plugin plugin) {
        final SessionStorage storage = new SessionStorage();
        return new SessionProcessorImpl(plugin, storage, new SessionResolverImpl(storage));
    }

    @NotNull SessionResolver resolver();

    @NotNull VanishSession activateSession(@NotNull final Player owner);

    void deactivateSession(@NotNull final Player owner);

    void deactivateAll();

}
