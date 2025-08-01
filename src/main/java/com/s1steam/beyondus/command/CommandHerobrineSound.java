package com.s1steam.beyondus.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class CommandHerobrineSound {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("herobrinesound")
            .then(Commands.argument("player", EntityArgument.player())
                .then(Commands.argument("sound", StringArgumentType.word())
                    .executes(context -> {
                        ServerPlayer target = EntityArgument.getPlayer(context, "player");
                        String sound = StringArgumentType.getString(context, "sound");

                        playSound(target, sound);
                        return 1;
                    })
                )
            )
        );
    }

    private static void playSound(ServerPlayer player, String sound) {
        // Пока проигрываем звук Wither Spawn, можно расширить под свои звуки
        player.playNotifySound(SoundEvents.WITHER_SPAWN, SoundSource.HOSTILE, 1.0F, 1.0F);
        player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Проигрывается звук Херобрина: " + sound));
    }
}
