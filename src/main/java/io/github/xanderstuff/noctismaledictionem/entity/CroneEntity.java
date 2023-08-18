package io.github.xanderstuff.noctismaledictionem.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.UUID;

//TODO: perhaps the Crone should extend MerchantEntity for trading?
public class CroneEntity extends PathAwareEntity implements GeoEntity, Angerable, RangedAttackMob {
	private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
	@Nullable
	private UUID angryAt;
	private int angerTime;
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public CroneEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder setAttributes() {
		return PathAwareEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0) // note: this is unused due to not having a melee attack
				.add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0) // note: this is unused due to not having a melee attack
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
	}

	@Override
	protected void initGoals() {
		goalSelector.add(1, new SwimGoal(this));

		goalSelector.add(2, new ProjectileAttackGoal(this, 0.8, 60, 10.0F));
		goalSelector.add(3, new WanderAroundFarGoal(this, 0.6, 1.0f));
		//		this.goalSelector.add(4, new TemptGoal(this, 1.25, Ingredient.ofItems(Items.WHEAT), false));
		goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
		goalSelector.add(5, new LookAroundGoal(this)); //FIXME: what actually happens when a goal's priority is the same as another one?

//		targetSelector.add(1, new RevengeGoal(this, WereWolf.class)); // the 2nd and subsequent parameters are for which mobs to ignore attacks from (typically accidental attacks)
		targetSelector.add(1, new RevengeGoal(this)); // RevengeGoal will target the mob which attacked this mob, ie like how neutral mobs aggro
//		targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
		controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
	}

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> animationState) {
		var animationController = animationState.getController();

		if (animationState.isMoving()) {
			animationController.setAnimation(RawAnimation.begin().thenLoop("animation.crone.move"));
			return PlayState.CONTINUE;
		}

		//.then("animation.crone.idle", Animation.LoopType.LOOP)
		animationController.setAnimation(RawAnimation.begin().thenLoop("animation.crone.idle"));

		return PlayState.CONTINUE;
	}

	@Override
	public void attack(LivingEntity target, float pullProgress) {
		// this method is based off of vanilla's WitchEntity#attack
		Vec3d targetVelocity = target.getVelocity();
		double deltaX = target.getX() + targetVelocity.x - this.getX();
		double deltaY = target.getEyeY() - this.getY() - 1.1f; // magic number: probably as a way to guess where the target's feet are
		double deltaZ = target.getZ() + targetVelocity.z - this.getZ();
		double targetDistance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

		// choose a potion to throw
		var choiceIndex = random.nextInt(8); // 8: pick 0..7 inclusive
		StatusEffectInstance statusEffect = switch (choiceIndex) {
			// yes, I could just put these in a list and pick from the list instead, but I'm avoiding creating a lot of unused objects each time or overkill abstraction with a helper method
			case 0 -> new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 0, 0);
			case 1 -> new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 0, 0); // double chance for instant damage
			case 2 -> new StatusEffectInstance(StatusEffects.SLOWNESS, 90 * 20, 0);
			case 3 -> new StatusEffectInstance(StatusEffects.WEAKNESS, 90 * 20, 0);
			case 4 -> new StatusEffectInstance(StatusEffects.BLINDNESS, 60 * 20, 0);
			case 5 -> new StatusEffectInstance(StatusEffects.HUNGER, 60 * 20, 0);
			case 6 -> new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 30 * 20, 0);
			case 7 -> new StatusEffectInstance(StatusEffects.WITHER, 30 * 20, 0);
			default -> throw new IllegalStateException("Unexpected value: " + choiceIndex + ". Please report this bug");
		};

		// then throw the potion
		var potionEntity = new PotionEntity(getWorld(), this);
		potionEntity.setItem(PotionUtil.setCustomPotionEffects(new ItemStack(Items.SPLASH_POTION), List.of(statusEffect)));
		potionEntity.setPitch(potionEntity.getPitch() + 20.0f);
		potionEntity.setVelocity(deltaX, deltaY + targetDistance * 0.2f, deltaZ, 0.75f, 8.0f);
		if (!isSilent()) {
			//TODO: should there be a custom ENTITY_CRONE_THROW sound event? (mainly for proper subtitles?)
			getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_WITCH_THROW, this.getSoundCategory(), 1.0f, 0.8f + random.nextFloat() * 0.4f);
		}

		getWorld().spawnEntity(potionEntity);
	}

	@Override
	public boolean canImmediatelyDespawn(double distanceSquared) {
		return false;
	}

	@Override
	public int getXpToDrop() {
		return 0;
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	@Override
	public boolean canTarget(LivingEntity target) {
		return super.canTarget(target);
	}

	@Override
	public int getAngerTime() {
		return angerTime;
	}

	@Override
	public void setAngerTime(int angerTime) {
		this.angerTime = angerTime;
	}

	@Nullable
	@Override
	public UUID getAngryAt() {
		return angryAt;
	}

	@Override
	public void setAngryAt(@Nullable UUID angryAt) {
		this.angryAt = angryAt;
	}

	@Override
	public void chooseRandomAngerTime() {
		setAngerTime(ANGER_TIME_RANGE.get(random));
	}
}
