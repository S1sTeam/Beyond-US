package com.s1steam.beyondus.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.s1steam.beyondus.client.model.HerobrineModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;

public class EntityHerobrine extends Monster {

    // Регистрация типа сущности через DeferredRegister
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "beyondus");

    public static final RegistryObject<EntityType<EntityHerobrine>> HEROBRINE = ENTITY_TYPES.register("herobrine",
            () -> EntityType.Builder.of(EntityHerobrine::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)  // Размеры сущности (ширина, высота)
                    .build("herobrine"));

    // Определение ModelLayerLocation для модели
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("beyondus", "herobrine"), "main");

    // Конструктор сущности
    public EntityHerobrine(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setNoAi(false); // Включаем ИИ (если нужно)
    }

    // Регистрация целей (AI)
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2, new net.minecraft.world.entity.ai.goal.RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new net.minecraft.world.entity.ai.goal.LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    // Пакет для синхронизации сущности с клиентом
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    // Регистрация DeferredRegister
    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }

    // Класс рендера сущности (клиентская часть)
    @OnlyIn(Dist.CLIENT)
    public static class Render extends MobRenderer<EntityHerobrine, HerobrineModel> {

        private static final ResourceLocation TEXTURE = new ResourceLocation("beyondus:textures/entity/herobrine.png");

        public Render(EntityRendererProvider.Context context) {
            super(context, new HerobrineModel(context.bakeLayer(LAYER_LOCATION)), 0.5f);
        }

        @Override
        public ResourceLocation getTextureLocation(EntityHerobrine entity) {
            return TEXTURE;
        }
    }

    // Метод для регистрации рендера
    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HEROBRINE.get(), Render::new);
    }
}
