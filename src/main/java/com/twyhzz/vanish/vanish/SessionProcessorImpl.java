package com.twyhzz.vanish.vanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

class SessionProcessorImpl implements SessionProcessor {

    private final Plugin plugin;
    private final SessionStorage storage;
    private final SessionResolver resolver;

    SessionProcessorImpl(@NotNull final Plugin plugin, @NotNull final SessionStorage storage, @NotNull final SessionResolver resolver) {
        this.plugin = plugin;
        this.storage = storage;
        this.resolver = resolver;
    }

    private void processHiding(@NotNull final Player owner) {
        Bukkit.getOnlinePlayers().forEach(got -> {
            if (got.hasPermission("Vanish.bypass") || got.hasPermission("Vanish.*")) {
                return;
            }
            got.hidePlayer(plugin, owner);
        });
    }

    private void processShowing(@NotNull final Player owner) {
        Bukkit.getOnlinePlayers().forEach(got -> {
            if (got.hasPermission("Vanish.bypass") || got.hasPermission("Vanish.*")) {
                return;
            }
            got.showPlayer(plugin, owner);
        });
    }

    @Override
    public @NotNull SessionResolver resolver() {
        return resolver;
    }

    @Override
    public @NotNull VanishSession activateSession(@NotNull final Player owner) {
        final Optional<VanishSession> sessionOpt = resolver.resolveByOwner(owner);
        if (sessionOpt.isPresent()) {
            throw new IllegalArgumentException("The player has already activated session!");
        }
        final VanishSession session = new SessionImpl(owner, System.currentTimeMillis());
        processHiding(owner);
        storage.sessions().add(session);
        return session;
    }

    @Override
    public void deactivateSession(@NotNull final Player owner) {
        final Optional<VanishSession> sessionOpt = resolver.resolveByOwner(owner);
        if (!sessionOpt.isPresent()) {
            throw new IllegalArgumentException("The player has already deactivated session!");
        }
        final VanishSession session = sessionOpt.get();
        processShowing(owner);
        storage.sessions().remove(session);
    }

    @Override
    public void deactivateAll() {
        storage.sessions().forEach(got -> deactivateSession(got.getOwner()));
    }

}
