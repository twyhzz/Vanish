package com.twyhzz.vanish.config.path;

import com.twyhzz.api.Pathable;
import org.jetbrains.annotations.NotNull;

public enum Messages implements Pathable {

    ONLY_PLAYER("onlyPlayer"),
    NO_PERMISSION("noPermission"),
    USAGE("usage"),
    ACTIVATED("activated"),
    DEACTIVATED("deactivated"),
    PLAYER_NOT_ACTIVE("playerNotActive");

    private final String path;

    Messages(@NotNull final String path) {
        this.path = path;
    }

    @Override
    public @NotNull String getPath() {
        return path;
    }

}
