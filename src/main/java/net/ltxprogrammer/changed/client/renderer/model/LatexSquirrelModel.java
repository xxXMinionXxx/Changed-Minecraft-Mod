package net.ltxprogrammer.changed.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ltxprogrammer.changed.Changed;
import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.entity.beast.LatexSquirrel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LatexSquirrelModel extends AdvancedHumanoidModel<LatexSquirrel> implements AdvancedHumanoidModelInterface<LatexSquirrel, LatexSquirrelModel> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Changed.modResource("latex_squirrel"), "main");
    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart Head;
    private final ModelPart Torso;
    private final ModelPart Tail;
    private final HumanoidAnimator<LatexSquirrel, LatexSquirrelModel> animator;

    public LatexSquirrelModel(ModelPart root) {
        super(root);
        this.RightLeg = root.getChild("RightLeg");
        this.LeftLeg = root.getChild("LeftLeg");
        this.Head = root.getChild("Head");
        this.Torso = root.getChild("Torso");
        this.Tail = Torso.getChild("Tail");
        this.RightArm = root.getChild("RightArm");
        this.LeftArm = root.getChild("LeftArm");

        var tailPrimary = Tail.getChild("TailPrimary");
        var tailSecondary = tailPrimary.getChild("TailSecondary");
        var tailTertiary = tailSecondary.getChild("TailTertiary");
        var tailQuaternary = tailTertiary.getChild("TailQuaternary");
        /*var tailQuinary = tailQuaternary.getChild("TailQuinary");
        var tailSenary = tailQuinary.getChild("TailSenary");
        var tailSeptenary = tailSenary.getChild("TailSeptenary");
        var tailOctonary = tailSeptenary.getChild("TailOctonary");
        var tailNonary = tailOctonary.getChild("TailNonary");*/

        var leftLowerLeg = LeftLeg.getChild("LeftLowerLeg");
        var leftFoot = leftLowerLeg.getChild("LeftFoot");
        var rightLowerLeg = RightLeg.getChild("RightLowerLeg");
        var rightFoot = rightLowerLeg.getChild("RightFoot");

        animator = HumanoidAnimator.of(this).hipOffset(-1.5f)
                .addPreset(AnimatorPresets.catLike(
                        Head, Head.getChild("LeftEar"), Head.getChild("RightEar"),
                        Torso, LeftArm, RightArm,
                        Tail, List.of(tailPrimary, tailSecondary, tailTertiary, tailQuaternary/*, tailQuinary, tailSenary, tailSeptenary, tailOctonary, tailNonary*/),
                        LeftLeg, leftLowerLeg, leftFoot, leftFoot.getChild("LeftPad"), RightLeg, rightLowerLeg, rightFoot, rightFoot.getChild("RightPad")));
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.5F, 10.5F, 0.0F));

        PartDefinition RightThigh_r1 = RightLeg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

        PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(48, 22).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

        PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

        PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(30, 55).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

        PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(47, 39).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 4.325F, -4.425F));

        PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.5F, 10.5F, 0.0F));

        PartDefinition LeftThigh_r1 = LeftLeg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(32, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

        PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(14, 56).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

        PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

        PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(48, 46).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

        PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(24, 1).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 4.325F, -4.425F));

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, CubeDeformation.NONE)
                .texOffs(12, 32).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, CubeDeformation.NONE)
                .texOffs(0, 5).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 1.0F, 1.0F, CubeDeformation.NONE), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition Nose_r1 = Head.addOrReplaceChild("Nose_r1", CubeListBuilder.create().texOffs(24, 2).addBox(-1.0F, -29.625F, -0.95F, 1.0F, 1.0F, 1.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.5F, 26.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create(), PartPose.offset(-2.7F, -6.8F, 0.0F));

        PartDefinition RightEarPivot = RightEar.addOrReplaceChild("RightEarPivot", CubeListBuilder.create().texOffs(0, 16).addBox(-1.9F, -3.2F, -1.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.05F))
                .texOffs(32, 22).addBox(-0.9F, -3.6F, -0.4F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.04F))
                .texOffs(0, 21).addBox(-0.9F, -4.3F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.05F))
                .texOffs(24, 0).addBox(0.1F, -5.1F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.5F, -1.25F, 0.0F, -0.1309F, 0.48F, -0.3054F));

        PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create(), PartPose.offset(2.7F, -6.8F, 0.0F));

        PartDefinition LeftEarPivot = LeftEar.addOrReplaceChild("LeftEarPivot", CubeListBuilder.create().texOffs(0, 0).addBox(-1.1F, -3.2F, -1.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.05F))
                .texOffs(38, 22).addBox(-1.1F, -3.6F, -0.4F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.04F))
                .texOffs(24, 22).addBox(-1.1F, -4.3F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.05F))
                .texOffs(0, 32).addBox(-1.1F, -5.1F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(-0.5F, -1.25F, 0.0F, -0.1309F, -0.48F, 0.3054F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F))
                .texOffs(24, 8).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(28, 28).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 10.5F, 0.0F));

        PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

        PartDefinition Base_r1 = TailPrimary.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(37, 0).addBox(-2.0F, 2.4F, 0.6F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 2.7F, 0.2F, 1.3526F, 0.0F, 0.0F));

        PartDefinition Base_r2 = TailPrimary.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(0, 59).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 4.0F, 3.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 0.1F, 0.0F, 1.0908F, 0.0F, 0.0F));

        PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.6F, 4.6F));

        PartDefinition Base_r3 = TailSecondary.addOrReplaceChild("Base_r3", CubeListBuilder.create().texOffs(65, 39).addBox(-2.5F, -0.8F, -0.3F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 1.8F, 1.0F, 1.5272F, 0.0F, 0.0F));

        PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.6F, 2.9F));

        PartDefinition Base_r4 = TailTertiary.addOrReplaceChild("Base_r4", CubeListBuilder.create().texOffs(56, 10).addBox(-3.5F, 3.0F, -2.5F, 7.0F, 5.0F, 5.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.0F, 0.25F, -2.7F, 1.7017F, 0.0F, 0.0F));

        PartDefinition TailQuaternary = TailTertiary.addOrReplaceChild("TailQuaternary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 3.4F));

        PartDefinition Base_r5 = TailQuaternary.addOrReplaceChild("Base_r5", CubeListBuilder.create().texOffs(57, 26).addBox(-4.5F, -1.0F, -2.5F, 9.0F, 6.0F, 7.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.0F, 0.55F, 1.15F, 1.8762F, 0.0F, 0.0F));

        PartDefinition TailQuinary = TailQuaternary.addOrReplaceChild("TailQuinary", CubeListBuilder.create(), PartPose.offset(0.0F, -1.2F, 3.9F));

        PartDefinition Base_r6 = TailQuinary.addOrReplaceChild("Base_r6", CubeListBuilder.create().texOffs(29, 74).addBox(-4.5F, 1.0F, -2.5F, 9.0F, 5.0F, 7.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, -1.6F, 4.9F, 1.8762F, 0.0F, 0.0F));

        PartDefinition Base_r7 = TailQuinary.addOrReplaceChild("Base_r7", CubeListBuilder.create().texOffs(0, 66).addBox(-5.0F, 0.0F, -2.5F, 10.0F, 6.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.95F, 0.9F, 2.0071F, 0.0F, 0.0F));

        PartDefinition TailSenary = TailQuinary.addOrReplaceChild("TailSenary", CubeListBuilder.create(), PartPose.offset(0.0F, -4.3F, 9.6F));

        PartDefinition Base_r8 = TailSenary.addOrReplaceChild("Base_r8", CubeListBuilder.create().texOffs(0, 80).addBox(-4.5F, 2.0F, -2.5F, 9.0F, 4.0F, 7.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 1.25F, -1.7F, 1.6581F, 0.0F, 0.0F));

        PartDefinition TailSeptenary = TailSenary.addOrReplaceChild("TailSeptenary", CubeListBuilder.create(), PartPose.offset(0.0F, -0.7F, 2.5F));

        PartDefinition Base_r9 = TailSeptenary.addOrReplaceChild("Base_r9", CubeListBuilder.create().texOffs(28, 64).addBox(-4.0F, 3.95F, -2.5F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -1.0F, -3.6F, 1.1781F, 0.0F, 0.0F));

        PartDefinition TailOctonary = TailSeptenary.addOrReplaceChild("TailOctonary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.6F, 3.1F));

        PartDefinition Base_r10 = TailOctonary.addOrReplaceChild("Base_r10", CubeListBuilder.create().texOffs(48, 0).addBox(-4.0F, 3.0F, -2.5F, 8.0F, 4.0F, 6.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, -2.1F, -2.5F, 0.6981F, 0.0F, 0.0F));

        PartDefinition TailNonary = TailOctonary.addOrReplaceChild("TailNonary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.7F, 2.1F));

        PartDefinition Base_r11 = TailNonary.addOrReplaceChild("Base_r11", CubeListBuilder.create().texOffs(50, 55).addBox(-3.5F, 2.0F, -2.5F, 7.0F, 5.0F, 5.0F, new CubeDeformation(-0.35F)), PartPose.offsetAndRotation(0.0F, -2.2F, 0.05F, -0.0873F, 0.0F, 0.0F));

        PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(-5.0F, 1.5F, 0.0F));

        PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(16, 40).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(5.0F, 1.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 96, 96);
    }

    @Override
    public void prepareMobModel(LatexSquirrel p_102861_, float p_102862_, float p_102863_, float p_102864_) {
        this.prepareMobModel(animator, p_102861_, p_102862_, p_102863_, p_102864_);
    }

    public void setupHand(LatexSquirrel entity) {
        animator.setupHand();
    }

    @Override
    public void setupAnim(@NotNull LatexSquirrel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    public ModelPart getArm(HumanoidArm p_102852_) {
        return p_102852_ == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
    }

    public ModelPart getLeg(HumanoidArm p_102852_) {
        return p_102852_ == HumanoidArm.LEFT ? this.LeftLeg : this.RightLeg;
    }

    public ModelPart getHead() {
        return this.Head;
    }

    public ModelPart getTorso() {
        return Torso;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        RightLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Torso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public HumanoidAnimator<LatexSquirrel, LatexSquirrelModel> getAnimator(LatexSquirrel entity) {
        return animator;
    }
}
