public class MyEvents {
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getClimateSettings().precipitation() != Biome.Precipitation.NONE) {
            event.getSpawns().addSpawn(MobCategory.MONSTER, 
                new MobSpawnSettings.SpawnerData(MyEntities.MY_ENTITY.get(), 10, 1, 3));
        }
    }
}
