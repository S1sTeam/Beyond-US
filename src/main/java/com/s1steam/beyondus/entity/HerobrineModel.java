package com.s1steam.beyondus.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;

import net.minecraft.resources.ResourceLocation;

public class HerobrineModel extends HumanoidModel<EntityHerobrine> {
    // Используем правильный идентификатор мода и имя модели
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("beyondus", "herobrine"), "main");

    public HerobrineModel(ModelPart root) {
        super(root);
    }

    // Создаем определение слоя модели (тело, руки, ноги и голова)
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.0F), 0.0F);
        // Здесь можно дополнительно добавить детали, если надо

        // Например, если хочешь изменить какие-то части, можно здесь работать с root и его детьми
        // ModelPart root = meshdefinition.getRoot();
        // root.addOrReplaceChild("custom_part", CubeListBuilder.create()
        //    .texOffs(0,0).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4), PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(EntityHerobrine entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        // Можно добавить анимации, если хочешь
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
