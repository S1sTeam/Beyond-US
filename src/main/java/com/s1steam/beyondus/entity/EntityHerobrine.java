package com.s1steam.beyondus.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.level.Level;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraftforge.network.NetworkHooks;
import net.minecraft.network.protocol.Packet;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EntityHerobrine extends Monster {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, "herobrinemod");
    public static final RegistryObject<EntityType<EntityHerobrine>> HEROBRINE = ENTITY_TYPES.register("herobrine",
            () -> EntityType.Builder.of(EntityHerobrine::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build("herobrine"));

    public EntityHerobrine(EntityType<? extends Monster> type, Level world) {
        super(type, world);
        this.setNoAi(false);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }

    // Рендерер
    public static class Render extends MobRenderer<EntityHerobrine, HerobrineModel<EntityHerobrine>> {

        private static final ResourceLocation TEXTURE = new ResourceLocation("herobrinemod", "textures/entity/herobrine.png");

        public Render(EntityRendererProvider.Context context) {
            super(context, new HerobrineModel<>(context.bakeLayer(HerobrineModel.LAYER_LOCATION)), 0.5f);
        }

        @Override
        public ResourceLocation getTextureLocation(EntityHerobrine entity) {
            return TEXTURE;
        }
    }
}
