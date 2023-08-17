package io.github.xanderstuff.noctismaledictionem.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

//TODO: perhaps the Crone should extend MerchantEntity for trading?
public class CroneEntity extends PathAwareEntity implements GeoEntity {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public CroneEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder setAttributes() {
		return PathAwareEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0) //FIXME: adjust settings
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0) //FIXME: adjust settings
				.add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0) //FIXME: adjust settings
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3); //FIXME: adjust settings
	}

	@Override
	protected void initGoals() {
		goalSelector.add(1, new SwimGoal(this));
		goalSelector.add(2, new EscapeDangerGoal(this, 0.6));

		//		this.goalSelector.add(3, new TemptGoal(this, 1.25, Ingredient.ofItems(Items.WHEAT), false));
		//for reference, the WanderAroundFarGoal "probability" parameter is set to 0.001f for a cow, probably so that it walks around less often
		goalSelector.add(3, new WanderAroundFarGoal(this, 0.3, 1.0f));
		goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
		goalSelector.add(5, new LookAroundGoal(this));

//		targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
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
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}
}
