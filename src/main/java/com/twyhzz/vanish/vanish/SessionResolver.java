package com.twyhzz.vanish.vanish;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface SessionResolver {

    @NotNull Optional<VanishSession> resolveByOwner(@NotNull final Player owner);

}
