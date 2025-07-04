package net.ltxprogrammer.changed.client.renderer.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ltxprogrammer.changed.Changed;
import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.renderer.model.LowerTorsoedModel;
import net.ltxprogrammer.changed.entity.beast.HeadlessKnight;
import net.ltxprogrammer.changed.item.Shorts;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ArmorHeadlessKnightModel extends LatexHumanoidArmorModel<HeadlessKnight, ArmorHeadlessKnightModel> implements LowerTorsoedModel {
    public static final ArmorModelSet<HeadlessKnight, ArmorHeadlessKnightModel> MODEL_SET =
            ArmorModelSet.of(Changed.modResource("armor_headless_knight"), ArmorHeadlessKnightModel::createArmorLayer, ArmorHeadlessKnightModel::new);

    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;
    private final ModelPart RightLeg2;
    private final ModelPart LeftLeg2;
    private final ModelPart LowerTorso;
    private final ModelPart Tail;
    private final HumanoidAnimator<HeadlessKnight, ArmorHeadlessKnightModel> animator;

    public ArmorHeadlessKnightModel(ModelPart modelPart, ArmorModel model) {
        super(modelPart, model);
        this.LowerTorso = modelPart.getChild("LowerTorso");

        this.RightLeg = LowerTorso.getChild("RightLeg");
        this.LeftLeg = LowerTorso.getChild("LeftLeg");
        this.RightLeg2 = LowerTorso.getChild("RightLeg2");
        this.LeftLeg2 = LowerTorso.getChild("LeftLeg2");
        this.Tail = LowerTorso.getChild("Tail");
        animator = HumanoidAnimator.of(this).addPreset(AnimatorPresets.taurLegsOld(Tail, List.of(), LeftLeg, RightLeg, LowerTorso, LeftLeg2, RightLeg2))
                .forwardOffset(-7.0f);
    }

    public static LayerDefinition createArmorLayer(ArmorModel layer) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition LowerTorso = partdefinition.addOrReplaceChild("LowerTorso", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, -7.0F));

        PartDefinition Tail = LowerTorso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 15.25F));

        PartDefinition Base_r1 = Tail.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(2, 17).addBox(-1.5F, 2.1914F, -2.1983F, 3.0F, 7.0F, 3.0F, layer.altDeformation), PartPose.offsetAndRotation(0.0F, 2.0F, 5.0F, 1.4835F, 0.0F, 0.0F));

        PartDefinition Base_r2 = Tail.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(2, 17).addBox(-1.5F, 2.0F, -1.0F, 3.0F, 7.0F, 3.0F, layer.altDeformation), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 1.1781F, 0.0F, 0.0F));

        PartDefinition LeftLeg = LowerTorso.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(-1.75F, 12.0F, -1.8F, 4.0F, 2.0F, 4.0F, layer.deformation).mirror(false)
                .texOffs(3, 29).mirror().addBox(-1.75F, 12.0F, -2.8F, 4.0F, 2.0F, 1.0F, layer.deformation).mirror(false), PartPose.offset(3.25F, 0.0F, 0.0F));

        PartDefinition LeftLowerLeg_r1 = LeftLeg.addOrReplaceChild("LeftLowerLeg_r1", CubeListBuilder.create().texOffs(16, 38).mirror().addBox(-2.0F, 9.8638F, 2.7342F, 4.0F, 1.0F, 4.0F, layer.slightAltDeformation).mirror(false)
                .texOffs(0, 16).mirror().addBox(-2.0F, 2.8638F, 2.7342F, 4.0F, 7.0F, 4.0F, layer.slightAltDeformation).mirror(false), PartPose.offsetAndRotation(0.25F, 1.0348F, -2.0472F, -0.2182F, 0.0F, 0.0F));

        PartDefinition LeftUpperLeg_r1 = LeftLeg.addOrReplaceChild("LeftUpperLeg_r1", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -0.3172F, -0.0274F, 4.0F, 6.0F, 4.0F, layer.inverseDeformation).mirror(false), PartPose.offsetAndRotation(0.25F, 1.0348F, -2.0472F, 0.3054F, 0.0F, 0.0F));

        PartDefinition RightLeg = LowerTorso.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 26).addBox(-1.75F, 12.0F, -1.8F, 4.0F, 2.0F, 4.0F, layer.deformation)
                .texOffs(3, 29).addBox(-1.75F, 12.0F, -2.8F, 4.0F, 2.0F, 1.0F, layer.deformation), PartPose.offset(-3.75F, 0.0F, 0.0F));

        PartDefinition RightLowerLeg_r1 = RightLeg.addOrReplaceChild("RightLowerLeg_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 9.8638F, 2.7342F, 4.0F, 1.0F, 4.0F, layer.slightAltDeformation)
                .texOffs(0, 16).addBox(-2.0F, 2.8638F, 2.7342F, 4.0F, 7.0F, 4.0F, layer.slightAltDeformation), PartPose.offsetAndRotation(0.25F, 1.0348F, -2.0472F, -0.2182F, 0.0F, 0.0F));

        PartDefinition RightUpperLeg_r1 = RightLeg.addOrReplaceChild("RightUpperLeg_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -0.3172F, -0.0274F, 4.0F, 6.0F, 4.0F, layer.inverseDeformation), PartPose.offsetAndRotation(0.25F, 1.0348F, -2.0472F, 0.3054F, 0.0F, 0.0F));

        PartDefinition LeftLeg2 = LowerTorso.addOrReplaceChild("LeftLeg2", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(-2.0F, 12.0F, 0.25F, 4.0F, 2.0F, 4.0F, layer.deformation).mirror(false), PartPose.offset(3.5F, 0.0F, 15.0F));

        PartDefinition LeftLowerLeg_r2 = LeftLeg2.addOrReplaceChild("LeftLowerLeg_r2", CubeListBuilder.create().texOffs(1, 17).mirror().addBox(-2.0F, 3.5131F, 2.8162F, 4.0F, 6.0F, 3.0F, layer.altDeformation).mirror(false), PartPose.offsetAndRotation(0.0F, 2.1238F, 0.5226F, -0.2182F, 0.0F, 0.0F));

        PartDefinition LeftMidLeg_r1 = LeftLeg2.addOrReplaceChild("LeftMidLeg_r1", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -0.098F, -4.8445F, 4.0F, 7.0F, 4.0F, layer.slightAltDeformation).mirror(false), PartPose.offsetAndRotation(0.0F, 2.1238F, 0.5226F, 0.8727F, 0.0F, 0.0F));

        PartDefinition LeftUpperLeg_r2 = LeftLeg2.addOrReplaceChild("LeftUpperLeg_r2", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -2.0208F, -2.9291F, 4.0F, 6.0F, 4.0F, layer.altDeformation).mirror(false), PartPose.offsetAndRotation(0.0F, 2.1238F, 0.5226F, -0.0873F, 0.0F, 0.0F));

        PartDefinition RightLeg2 = LowerTorso.addOrReplaceChild("RightLeg2", CubeListBuilder.create().texOffs(0, 26).addBox(-2.0F, 12.0F, 0.25F, 4.0F, 2.0F, 4.0F, layer.deformation), PartPose.offset(-3.5F, 0.0F, 15.0F));

        PartDefinition RightLowerLeg_r2 = RightLeg2.addOrReplaceChild("RightLowerLeg_r2", CubeListBuilder.create().texOffs(1, 17).addBox(-2.0F, 3.5131F, 2.8162F, 4.0F, 6.0F, 3.0F, layer.altDeformation), PartPose.offsetAndRotation(0.0F, 2.1238F, 0.5226F, -0.2182F, 0.0F, 0.0F));

        PartDefinition RightMidLeg_r1 = RightLeg2.addOrReplaceChild("RightMidLeg_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -0.098F, -4.8445F, 4.0F, 7.0F, 4.0F, layer.slightAltDeformation), PartPose.offsetAndRotation(0.0F, 2.1238F, 0.5226F, 0.8727F, 0.0F, 0.0F));

        PartDefinition RightUpperLeg_r2 = RightLeg2.addOrReplaceChild("RightUpperLeg_r2", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -2.0208F, -2.9291F, 4.0F, 6.0F, 4.0F, layer.altDeformation), PartPose.offsetAndRotation(0.0F, 2.1238F, 0.5226F, -0.0873F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public HumanoidAnimator<HeadlessKnight, ArmorHeadlessKnightModel> getAnimator(HeadlessKnight entity) {
        return animator;
    }

    @Override
    public void prepareVisibility(EquipmentSlot armorSlot, ItemStack item) {
        super.prepareVisibility(armorSlot, item);
        if (armorSlot == EquipmentSlot.LEGS) {
            prepareUnifiedLegsForArmor(item, LeftLeg, RightLeg);

            if (item.getItem() instanceof Shorts) {
                setAllPartsVisibility(LowerTorso, false);
                LeftLeg2.getChild("LeftUpperLeg_r2").visible = true;
                RightLeg2.getChild("RightUpperLeg_r2").visible = true;
            }
        }
    }

    @Override
    public void unprepareVisibility(EquipmentSlot armorSlot, ItemStack item) {
        super.unprepareVisibility(armorSlot, item);
        if (armorSlot == EquipmentSlot.LEGS) {
            prepareUnifiedLegsForArmor(item, LeftLeg, RightLeg);

            if (item.getItem() instanceof Shorts) {
                setAllPartsVisibility(LowerTorso, true);
            }
        }
    }

    @Override
    public void renderForSlot(HeadlessKnight entity, RenderLayerParent<? super HeadlessKnight, ?> parent, ItemStack stack, EquipmentSlot slot, PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();
        this.scaleForSlot(parent, slot, poseStack);

        switch (slot) {
            case LEGS, FEET -> LowerTorso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        }

        poseStack.popPose();
    }

    public ModelPart getArm(HumanoidArm arm) {
        return null;
    }

    public ModelPart getLeg(HumanoidArm leg) {
        return null;
    }

    public ModelPart getHead() {
        return NULL_PART;
    }

    public ModelPart getTorso() {
        return null;
    }

    @Override
    public ModelPart getLowerTorso() {
        return LowerTorso;
    }
}
