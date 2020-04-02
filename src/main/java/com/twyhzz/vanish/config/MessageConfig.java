package com.twyhzz.vanish.config;

import com.twyhzz.config.MessageYamlConfig;
import com.twyhzz.message.Message;
import com.twyhzz.message.MessageAdapter;
import com.twyhzz.vanish.config.path.Messages;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class MessageConfig extends MessageYamlConfig<Messages> {

    public MessageConfig(@NotNull final Plugin plugin) {
        super(plugin, "language", Messages.values());
    }

    @Override
    public void reinit() {
        super.reinit();
    }

    @Override
    public @NotNull Message getMessage(@NotNull final Messages path) {
        return super.getMessage(path);
    }

    @Override
    public @NotNull MessageAdapter getMessageAdapter(@NotNull final Messages path) {
        return super.getMessageAdapter(path);
    }

}
