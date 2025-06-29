package net.ltxprogrammer.changed.entity.beast;

import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.entity.variant.EntityShape;
import net.ltxprogrammer.changed.init.ChangedSounds;
import net.ltxprogrammer.changed.init.ChangedTransfurVariants;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;

public class DarkLatexWolfPup extends AbstractDarkLatexEntity {
    protected static final int MAX_AGE = 72000;
    protected int age = 0;
    protected int ticksLeftAsPuddle = 0;
    private static final EntityDataAccessor<Boolean> DATA_PUDDLE_ID = SynchedEntityData.defineId(DarkLatexWolfPup.class, EntityDataSerializers.BOOLEAN);

    public DarkLatexWolfPup(EntityType<? extends DarkLatexWolfPup> type, Level level) {
        super(type, level);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        final DarkLatexWolfPup self = this;
        return new GroundPathNavigation(this, level) {
            @Override
            protected boolean canUpdatePath() {
                return super.canUpdatePath() && !self.isPuddle();
            }
        };
    }

    @Override
    public boolean tryTransfurTarget(Entity entity) {
        return false;
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            if (!this.isPuddle())
                this.playSound(ChangedSounds.POISON, 1.0f, 1.0f);
            this.setPuddle(true);
            ticksLeftAsPuddle = 120;
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2, false, false, false)); // Slowness 2 for 5 seconds
        }
        return true;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_PUDDLE_ID, false);
    }

    public void setPuddle(boolean isPuddle) {
        this.goalSelector.setControlFlag(Goal.Flag.MOVE, !isPuddle);
        this.entityData.set(DATA_PUDDLE_ID, isPuddle);
    }

    public boolean isPuddle() {
        return this.entityData.get(DATA_PUDDLE_ID);
    }

    @Override
    public void tick() {
        super.tick();
        if (ticksLeftAsPuddle > 0) {
            this.navigation.stop();
            this.setDeltaMovement(0, Math.min(this.getDeltaMovement().y, 0), 0);
            ticksLeftAsPuddle--;
            if (ticksLeftAsPuddle <= 0)
                setPuddle(false);
        }
        this.refreshDimensions();
    }

    @Override
    public TransfurMode getTransfurMode() {
        return TransfurMode.NONE;
    }

    @Override
    public Color3 getTransfurColor(TransfurCause cause) {
        return Color3.DARK;
    }

    @Override
    public void setSharedFlag(int p_20116_, boolean p_20117_) {
        super.setSharedFlag(p_20116_, p_20117_);
    }

    @Override
    public double getMyRidingOffset() {
        return 0.2;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        age = tag.getInt("age");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("age", age);
    }

    @Override
    public float getEyeHeightMul() {
        if (this.isCrouching())
            return 0.65F;
        if (this.isPuddle())
            return 0.9F;
        else
            return 0.8F;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        EntityDimensions core = super.getDimensions(pose);
        if (this.isPuddle())
            return EntityDimensions.scalable(core.width + 0.4f, core.height - 0.5f);
        else
            return core;
    }

    @Override
    public void variantTick(Level level) {
        super.variantTick(level);

        age++;

        var underlyingPlayer = getUnderlyingPlayer();
        if (ProcessTransfur.ifPlayerTransfurred(underlyingPlayer, variant -> {
            if (variant.ageAsVariant > MAX_AGE || age > MAX_AGE) {
                var newVariant = ChangedTransfurVariants.Gendered.DARK_LATEX_WOLVES.getRandomVariant(level.random);
                ProcessTransfur.changeTransfur(underlyingPlayer, newVariant);
                ChangedSounds.broadcastSound(this, newVariant.sound, 1.0f, 1.0f);
                underlyingPlayer.heal(12.0f);
            }
        })) return;

        if (age > MAX_AGE) {
            var newVariant = ChangedTransfurVariants.Gendered.DARK_LATEX_WOLVES.getRandomVariant(level.random);
            var wolf = newVariant.getEntityType().create(level);
            if (wolf != null) {
                wolf.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                level.addFreshEntity(wolf);
                ChangedSounds.broadcastSound(this, newVariant.sound, 1.0f, 1.0f);
                applyCustomizeToAged((AbstractDarkLatexEntity)wolf);
            }
            this.discard();
        }
    }

    protected void applyCustomizeToAged(AbstractDarkLatexEntity aged) {
        aged.setTame(this.isTame());
        aged.setOwnerUUID(this.getOwnerUUID());
        aged.setFollowOwner(this.isFollowingOwner());
        aged.setCustomName(this.getCustomName());
        aged.getBasicPlayerInfo().copyFrom(this.getBasicPlayerInfo());
        aged.setUnderlyingPlayer(this.getUnderlyingPlayer());
    }

    public boolean canBeLeashed(Player player) {
        return !this.isLeashed();
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (this.level.isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || this.isTameItem(itemstack) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            if (!this.isTame() && this.isTameItem(itemstack)) {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                ProcessTransfur.ifPlayerTransfurred(player, variant -> {
                    if (variant.getLatexType() == LatexType.DARK_LATEX && this.random.nextInt(3) == 0) { // One in 3 chance
                        this.tame(player);
                        this.navigation.stop();
                        this.setTarget(null);
                        this.level.broadcastEntityEvent(this, (byte)7);
                    } else if (!variant.getLatexType().isHostileTo(LatexType.DARK_LATEX) && this.random.nextInt(10) == 0) {
                        this.tame(player);
                        this.navigation.stop();
                        this.setTarget(null);
                        this.level.broadcastEntityEvent(this, (byte)7);
                    } else {
                        this.level.broadcastEntityEvent(this, (byte)6);
                    }
                }, () -> {
                    if (this.random.nextInt(10) == 0) { // One in 10 chance
                        this.tame(player);
                        this.navigation.stop();
                        this.setTarget(null);
                        this.level.broadcastEntityEvent(this, (byte)7);
                    } else {
                        this.level.broadcastEntityEvent(this, (byte)6);
                    }
                });

                return InteractionResult.SUCCESS;
            }

            return super.mobInteract(player, hand);
        }
    }

    @Override
    protected void setAttributes(AttributeMap attributes) {
        super.setAttributes(attributes);
        attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.25);
        attributes.getInstance(ForgeMod.SWIM_SPEED.get()).setBaseValue(0.975);
        attributes.getInstance(Attributes.MAX_HEALTH).setBaseValue(12.0);
    }

    @Override
    public @NotNull EntityShape getEntityShape() {
        return EntityShape.FERAL;
    }

    @Override
    public boolean isItemAllowedInSlot(ItemStack stack, EquipmentSlot slot) {
        if (slot.getType() == EquipmentSlot.Type.ARMOR)
            return false;
        return super.isItemAllowedInSlot(stack, slot);
    }
}
