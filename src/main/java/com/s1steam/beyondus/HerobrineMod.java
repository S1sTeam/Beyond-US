package com.s1steam.beyondus;

import com.s1steam.beyondus.entity.EntityHerobrine;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;

@Mod(HerobrineMod.MODID)
@EventBusSubscriber(modid = HerobrineMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class HerobrineMod {
    public static final String MODID = "herobrinemod";

    public HerobrineMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        EntityHerobrine.register(modBus);
        modBus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRenderersEvent.RegisterRenderers.register(registry -> {
                registry.registerEntityRenderer(EntityHerobrine.HEROBRINE.get(), EntityHerobrine.Render::new);
            });
        });
    }
}
