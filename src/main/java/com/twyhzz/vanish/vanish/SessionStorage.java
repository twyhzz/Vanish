package com.twyhzz.vanish.vanish;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

final class SessionStorage {

    private final Set<VanishSession> sessions = new HashSet<>();

    SessionStorage() {
    }

    @NotNull Set<VanishSession> sessions() {
        return sessions;
    }

}
