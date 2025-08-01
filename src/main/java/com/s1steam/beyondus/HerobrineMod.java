package com.s1steam.beyondus;

import com.s1steam.beyondus.entity.EntityHerobrine;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("beyondus")
public class HerobrineMod {

    public HerobrineMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Регистрируем нашу сущность
        EntityHerobrine.register(modEventBus);

        // Регистрируем рендер только на клиенте
        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(this::registerEntityRenderers);
        }

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void registerEntityRenderers(final net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityHerobrine.HEROBRINE.get(), context -> new EntityHerobrine.Render(context));
    }
}
