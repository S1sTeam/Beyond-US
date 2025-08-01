package com.s1steam.beyondus.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.PartPose;
import net.minecraft.client.model.HumanoidModel;

public class HerobrineModel extends HumanoidModel<EntityHerobrine> {

    public HerobrineModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Туловище
        partdefinition.addOrReplaceChild("body",
            CubeListBuilder.create()
                .texOffs(16, 16)
                .addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4),
            PartPose.offset(0.0F, 0.0F, 0.0F));

        // Голова
        partdefinition.addOrReplaceChild("head",
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8),
            PartPose.offset(0.0F, 0.0F, 0.0F));

        // Левая рука
        partdefinition.addOrReplaceChild("left_arm",
            CubeListBuilder.create()
                .texOffs(32, 48)
                .addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4),
            PartPose.offset(5.0F, 2.0F, 0.0F));

        // Правая рука
        partdefinition.addOrReplaceChild("right_arm",
            CubeListBuilder.create()
                .texOffs(40, 16)
                .addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4),
            PartPose.offset(-5.0F, 2.0F, 0.0F));

        // Левая нога
        partdefinition.addOrReplaceChild("left_leg",
            CubeListBuilder.create()
                .texOffs(16, 48)
                .addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4),
            PartPose.offset(1.9F, 12.0F, 0.0F));

        // Правая нога
        partdefinition.addOrReplaceChild("right_leg",
            CubeListBuilder.create()
                .texOffs(0, 16)
                .addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4),
            PartPose.offset(-1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(EntityHerobrine entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        // Здесь можно добавить кастомные анимации
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
                                         }
