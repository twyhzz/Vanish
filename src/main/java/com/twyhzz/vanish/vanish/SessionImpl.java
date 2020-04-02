package com.twyhzz.vanish.vanish;

import com.twyhzz.api.AbstractOwnable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

class SessionImpl extends AbstractOwnable implements VanishSession {

    SessionImpl(@NotNull final Player owner, final long time) {
        super(owner);
    }

}
