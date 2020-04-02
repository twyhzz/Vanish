package com.twyhzz.vanish;

import com.twyhzz.vanish.config.MessageConfig;
import com.twyhzz.vanish.vanish.SessionProcessor;
import com.twyhzz.vanish.vanish.command.VanishCommand;
import com.twyhzz.vanish.vanish.event.SessionEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanishPlugin extends JavaPlugin {

    private SessionProcessor processor;

    @Override
    public void onEnable() {
        final MessageConfig messageConfig = new MessageConfig(this);
        processor = SessionProcessor.simpleProcess(this);
        new SessionEvents(this, processor);
        new VanishCommand(messageConfig, processor);
    }

    @Override
    public void onDisable() {
        processor.deactivateAll();
    }

}
