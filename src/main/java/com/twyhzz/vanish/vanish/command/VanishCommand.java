package com.twyhzz.vanish.vanish.command;

import com.twyhzz.command.ChatCommand;
import com.twyhzz.vanish.config.MessageConfig;
import com.twyhzz.vanish.config.path.Messages;
import com.twyhzz.vanish.vanish.SessionProcessor;
import com.twyhzz.vanish.vanish.SessionResolver;
import com.twyhzz.vanish.vanish.VanishState;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class VanishCommand extends ChatCommand {

    private final MessageConfig messageConfig;
    private final SessionResolver resolver;
    private final SessionProcessor processor;

    public VanishCommand(@NotNull final MessageConfig messageConfig, @NotNull final SessionProcessor processor) {
        super("vanish");
        this.messageConfig = messageConfig;
        this.resolver = processor.resolver();
        this.processor = processor;
    }

    private @NotNull VanishState processSession(@NotNull final Player player) {
        if (resolver.resolveByOwner(player).isPresent()) {
            processor.deactivateSession(player);
            return VanishState.INACTIVE;
        }
        processor.activateSession(player);
        return VanishState.ACTIVE;
    }

    @Override
    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command,
                             @NotNull final String name, @NotNull final String[] args) {
        if (!hasPermission(sender, "Vanish", "use")) {
            messageConfig.getMessage(Messages.NO_PERMISSION).send(sender);
            return true;
        }
        if (!isArgMinMax(args, 0, 1)) {
            messageConfig.getMessage(Messages.USAGE).send(sender);
            return true;
        }
        if (isArgLength(args, 0)) {
            if (!checkSender(sender, true, false)) {
                messageConfig.getMessage(Messages.ONLY_PLAYER).send(sender);
                return true;
            }
            if (!hasPermission(sender, "Vanish", "myself")) {
                messageConfig.getMessage(Messages.NO_PERMISSION).send(sender);
                return true;
            }
            final VanishState state = processSession(castPlayer(sender));
            if (state == VanishState.ACTIVE) {
                messageConfig.getMessage(Messages.ACTIVATED).send(sender);
                return true;
            }
            messageConfig.getMessage(Messages.DEACTIVATED).send(sender);
            return true;
        }
        if (!hasPermission(sender, "Vanish", "other")) {
            messageConfig.getMessage(Messages.NO_PERMISSION).send(sender);
            return true;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            messageConfig.getMessage(Messages.PLAYER_NOT_ACTIVE).send(sender);
            return true;
        }
        final VanishState state = processSession(player);
        if (state == VanishState.ACTIVE) {
            messageConfig.getMessage(Messages.ACTIVATED).send(sender, player);
            return true;
        }
        messageConfig.getMessage(Messages.DEACTIVATED).send(player, sender);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull final CommandSender sender, @NotNull final Command command,
                                                @NotNull final String name, @NotNull final String[] args) {
        return super.onTabComplete(sender, command, name, args);
    }

}
