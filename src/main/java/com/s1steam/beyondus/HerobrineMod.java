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
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HerobrineMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HerobrineModel.LAYER_LOCATION, HerobrineModel::createBodyLayer);
    }
}


@Mod(HerobrineMod.MODID)
public class HerobrineMod {
    public static final String MODID = "beyondus";

    public HerobrineMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Регистрация сущностей и звуков
        EntityHerobrine.register(modEventBus);
        MySounds.register(modEventBus);

        // Только на клиенте: рендер и модель
        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(this::registerEntityRenderers);
            modEventBus.addListener(EntityHerobrine::registerLayerDefinition); // ✅ Регистрируем модельный слой
        }

        // Регистрируем события
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void registerEntityRenderers(final net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityHerobrine.HEROBRINE.get(), context -> new EntityHerobrine.Render(context));
    }
}
