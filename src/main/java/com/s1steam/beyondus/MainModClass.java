@Mod("modid")
public class MainModClass {
    public MainModClass() {
        MyEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        
        MinecraftForge.EVENT_BUS.addListener(MyEvents::onBiomeLoad);
    }
}
