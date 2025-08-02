package com.yourmod.client.renderer;

import com.yourmod.entities.HerobrineEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HerobrineRenderer extends MobRenderer<HerobrineEntity, HerobrineModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("yourmod", "textures/entity/herobrine.png");
    
    public HerobrineRenderer(EntityRendererProvider.Context context) {
        super(context, new HerobrineModel(context.bakeLayer(ClientModModels.HEROBRINE_LAYER)), 0.5F);
    }
    
    @Override
    public ResourceLocation getTextureLocation(HerobrineEntity entity) {
        return TEXTURE;
    }
}
