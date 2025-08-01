package com.s1steam.beyondus.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "herobrinemod")
public class HerobrineEventHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();
        // Пример: отправить игроку сообщение или запустить что-то при входе
        if (!player.level.isClientSide()) {
            player.sendSystemMessage(
                net.minecraft.network.chat.Component.literal("Welcome to the Herobrine mod! Beware..."));
        }
    }

    // Другие события можно добавлять здесь
}
