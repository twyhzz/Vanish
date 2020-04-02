package com.twyhzz.vanish.vanish;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

class SessionResolverImpl implements SessionResolver {

    private final SessionStorage storage;

    SessionResolverImpl(@NotNull final SessionStorage storage) {
        this.storage = storage;
    }

    @Override
    public @NotNull Optional<VanishSession> resolveByOwner(@NotNull final Player owner) {
        return storage.sessions().stream()
                .filter(session -> session.getOwner().equals(owner))
                .findFirst();
    }

}
