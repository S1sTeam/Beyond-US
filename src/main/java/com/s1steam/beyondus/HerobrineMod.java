package com.s1steam.beyondus;

import com.s1steam.beyondus.entity.EntityHerobrine;
import com.s1steam.beyondus.registry.MySounds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(HerobrineMod.MODID)
public class HerobrineMod {
    public static final String MODID = "beyondus";

    public HerobrineMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Регистрация сущностей
        EntityHerobrine.register(modEventBus);

        // Регистрация звуков
        MySounds.register(modEventBus);

        // Клиентские события: регистрируем рендеры
        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.register(ClientEventHandler.class);
        }
    }
}
