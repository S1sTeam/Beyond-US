package com.s1steam.beyondus;

import com.s1steam.beyondus.entity.EntityHerobrine;
import com.s1steam.beyondus.entity.HerobrineSpawning;
import com.s1steam.beyondus.registry.MySounds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.client.event.EntityRenderersEvent;

@Mod(HerobrineMod.MODID)
public class HerobrineMod {
    public static final String MODID = "beyondus";

    public HerobrineMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Регистрация сущности
        EntityHerobrine.register(modEventBus);

        // Регистрация звуков
        MySounds.register(modEventBus);

        // Регистрация спауна
        HerobrineSpawning.registerSpawnPlacement();

        // Регистрируем рендер на клиенте
        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(this::registerEntityRenderers);
        }

        // Регистрируем события спауна
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(HerobrineSpawning.class);
    }

    private void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityHerobrine.HEROBRINE.get(), context -> new EntityHerobrine.Render(context));
    }
}
