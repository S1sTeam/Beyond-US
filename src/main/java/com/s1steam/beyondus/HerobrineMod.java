package com.s1steam.beyondus;

import com.s1steam.beyondus.entity.EntityHerobrine;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(HerobrineMod.MODID)
public class HerobrineMod {
    public static final String MODID = "beyondus";

    public HerobrineMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Регистрируем сущность
        EntityHerobrine.register(modEventBus);

        // Регистрируем рендеры и слои только на клиенте
        if (FMLEnvironment.dist == Dist.CLIENT) {
            // Клиентские события обрабатываются через ClientModEvents
        }

        MinecraftForge.EVENT_BUS.register(this);
    }
}
