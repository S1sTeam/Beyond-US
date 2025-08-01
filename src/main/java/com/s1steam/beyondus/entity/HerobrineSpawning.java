package com.s1steam.beyondus.entity;

import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class HerobrineSpawning {

    @SubscribeEvent
    public static void onBiomeLoading(BiomeLoadingEvent event) {
        // Добавим Herobrine в список спауна для всех OVERWORLD биомов
        if (event.getCategory() != net.minecraft.world.level.biome.Biome.BiomeCategory.NETHER &&
            event.getCategory() != net.minecraft.world.level.biome.Biome.BiomeCategory.THEEND) {

            event.getSpawns().getSpawner(Monster.MobCategory.MONSTER).add(
                new net.minecraft.world.entity.MobSpawnSettings.SpawnerData(
                    EntityHerobrine.HEROBRINE.get(),
                    5, // вес появления
                    1, // минимальное количество
                    1  // максимальное количество
                )
            );
        }
    }

    public static void registerSpawnPlacement() {
        SpawnPlacements.register(
            EntityHerobrine.HEROBRINE.get(),
            SpawnPlacements.Type.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            Monster::checkMonsterSpawnRules
        );
    }
}
