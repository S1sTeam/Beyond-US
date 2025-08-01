package com.s1steam.beyondus.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HerobrineRenderer extends MobRenderer<EntityHerobrine, HerobrineModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("beyondus", "textures/entity/herobrine.png");

    public HerobrineRenderer(EntityRendererProvider.Context context) {
        super(context, new HerobrineModel(context.bakeLayer(HerobrineModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityHerobrine entity) {
        return TEXTURE;
    }
}
