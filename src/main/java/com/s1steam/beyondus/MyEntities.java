public class MyEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = 
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "modid");
    
    public static final RegistryObject<EntityType<MyEntity>> MY_ENTITY = 
        ENTITIES.register("my_entity", () -> EntityType.Builder.of(MyEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.8F).build("my_entity"));
}
