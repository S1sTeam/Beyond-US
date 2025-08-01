package com.s1steam.beyondus;

import com.s1steam.beyondus.entity.EntityHerobrine;
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

        // Регистрируем сущности и звуки
        EntityHerobrine.register(modEventBus);
        MySounds.register(modEventBus);

        // Регистрируем рендеры и модели только на клиенте
        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(this::registerEntityRenderers);
            modEventBus.addListener(this::registerLayerDefinitions);
        }

        MinecraftForge.EVENT_BUS.register(this);
    }

    // Регистрация рендера сущности
    private void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityHerobrine.HEROBRINE.get(), EntityHerobrine.Render::new);
    }

    // Регистрация слоя модели для правильной работы bakeLayer
    private void registerLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HerobrineModel.LAYER_LOCATION, HerobrineModel::createBodyLayer);
    }
}
