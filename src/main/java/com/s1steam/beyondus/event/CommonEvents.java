package com.yourmod.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.yourmod.YourMod;
import com.yourmod.entities.EntityInit;
import com.yourmod.entities.HerobrineEntity;

@Mod.EventBusSubscriber(modid = YourMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {
    
    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.HEROBRINE.get(), HerobrineEntity.createAttributes().build());
    }
}
