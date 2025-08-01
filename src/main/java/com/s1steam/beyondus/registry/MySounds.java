package com.s1steam.beyondus.registry;

import com.s1steam.beyondus.HerobrineMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class MySounds {

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HerobrineMod.MODID);

    public static final RegistryObject<SoundEvent> HEROBRINE_SOUND =
            SOUNDS.register("herobrine_sound",
                    () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(HerobrineMod.MODID, "herobrine_sound")));

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
