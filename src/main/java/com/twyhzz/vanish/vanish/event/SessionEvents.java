package com.twyhzz.vanish.vanish.event;

import com.twyhzz.event.EventListener;
import com.twyhzz.vanish.vanish.SessionProcessor;
import com.twyhzz.vanish.vanish.SessionResolver;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SessionEvents extends EventListener {

    private final SessionResolver resolver;
    private final SessionProcessor processor;

    public SessionEvents(@NotNull final Plugin plugin, @NotNull final SessionProcessor processor) {
        super(plugin);
        this.resolver = processor.resolver();
        this.processor = processor;
    }

    private void clearSession(@NotNull final Player player) {
        if (!resolver.resolveByOwner(player).isPresent()) {
            return;
        }
        processor.deactivateSession(player);
    }

    @EventHandler
    public void onQuit(@NotNull final PlayerQuitEvent event) {
        clearSession(event.getPlayer());
    }

    @EventHandler
    public void onKick(@NotNull final PlayerKickEvent event) {
        clearSession(event.getPlayer());
    }

}
