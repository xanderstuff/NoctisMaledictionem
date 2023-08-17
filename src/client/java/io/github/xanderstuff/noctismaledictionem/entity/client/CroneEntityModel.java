package io.github.xanderstuff.noctismaledictionem.entity.client;

import io.github.xanderstuff.noctismaledictionem.MainMod;
import io.github.xanderstuff.noctismaledictionem.entity.CroneEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CroneEntityModel extends GeoModel<CroneEntity> {
	@Override
	public Identifier getModelResource(CroneEntity animatable) {
		return new Identifier(MainMod.MODID, "geo/crone.geo.json");
	}

	@Override
	public Identifier getTextureResource(CroneEntity animatable) {
		return new Identifier(MainMod.MODID, "textures/entity/crone.png");
	}

	@Override
	public Identifier getAnimationResource(CroneEntity animatable) {
		return new Identifier(MainMod.MODID, "animations/crone.animation.json");
	}

	@Override
	public void setCustomAnimations(CroneEntity animatable, long instanceId, AnimationState<CroneEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");
		if (head != null) {
			EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityModelData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
			head.setRotY(entityModelData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
		}
	}
}
