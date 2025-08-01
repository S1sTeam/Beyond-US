package com.s1steam.beyondus.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.minecraftforge.fmllegacy.network.NetworkHooks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }

    public static void registerRenderers() {
        // Здесь регистрируем рендер (на клиенте)
        // Для простоты пока пропустим
    }
}
