package com.s1steam.beyondus.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EntityHerobrine extends Monster {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "beyondus");

    public static final RegistryObject<EntityType<EntityHerobrine>> HEROBRINE = ENTITY_TYPES.register("herobrine",
            () -> EntityType.Builder.of(EntityHerobrine::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build(new ResourceLocation("beyondus", "herobrine").toString()));

    public EntityHerobrine(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setNoAi(false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Render extends MobRenderer<EntityHerobrine, HerobrineModel> {
        private static final ResourceLocation TEXTURE = new ResourceLocation("beyondus", "textures/entity/herobrine.png");

        public Render(EntityRendererProvider.Context context) {
            super(context, new HerobrineModel(context.bakeLayer(HerobrineModel.LAYER_LOCATION)), 0.5f);
        }

        @Override
        public ResourceLocation getTextureLocation(EntityHerobrine entity) {
            return TEXTURE;
        }
    }

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers(net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HEROBRINE.get(), Render::new);
    }
}
