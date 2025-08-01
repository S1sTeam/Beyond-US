package com.s1steam.beyondus.entity;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class HerobrineModel extends HumanoidModel<EntityHerobrine> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("beyondus", "herobrine"), "main");

    public HerobrineModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Копируем стандартные части HumanoidModel с их размерами и позициями
        partdefinition.addOrReplaceChild("head",
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8),
            PartPose.offset(0.0F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("hat",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("body",
            CubeListBuilder.create()
                .texOffs(16, 16)
                .addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4),
            PartPose.offset(0.0F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("right_arm",
            CubeListBuilder.create()
                .texOffs(40, 16)
                .addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4),
            PartPose.offset(-5.0F, 2.0F, 0.0F));

        partdefinition.addOrReplaceChild("left_arm",
            CubeListBuilder.create()
                .texOffs(40, 32)
                .addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4),
            PartPose.offset(5.0F, 2.0F, 0.0F));

        partdefinition.addOrReplaceChild("right_leg",
            CubeListBuilder.create()
                .texOffs(0, 16)
                .addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4),
            PartPose.offset(-1.9F, 12.0F, 0.0F));

        partdefinition.addOrReplaceChild("left_leg",
            CubeListBuilder.create()
                .texOffs(0, 32)
                .addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4),
            PartPose.offset(1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
