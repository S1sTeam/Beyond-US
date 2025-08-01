package com.s1steam.beyondus.client;

import com.s1steam.beyondus.entity.EntityHerobrine;
import com.s1steam.beyondus.entity.EntityHerobrine.Render;

import net.minecraft.client.renderer.entity.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "beyondus", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityHerobrine.HEROBRINE.get(), Render::new);
    }
}
