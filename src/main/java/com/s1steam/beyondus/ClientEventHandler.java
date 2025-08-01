package com.s1steam.beyondus;

import com.s1steam.beyondus.entity.EntityHerobrine;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "beyondus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Регистрируем рендер для сущности Herobrine
        event.registerEntityRenderer(EntityHerobrine.HEROBRINE.get(), EntityHerobrine.Render::new);
    }
}
