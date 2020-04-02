package com.twyhzz.vanish.vanish;

public enum VanishState { // Statable TODO

    ACTIVE(true),
    INACTIVE(false);

    private final boolean state;

    VanishState(final boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }

}
