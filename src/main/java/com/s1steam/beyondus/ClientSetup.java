@Mod.EventBusSubscriber(modid = "modid", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(MyEntities.MY_ENTITY.get(), MyEntityRenderer::new);
    }
}
