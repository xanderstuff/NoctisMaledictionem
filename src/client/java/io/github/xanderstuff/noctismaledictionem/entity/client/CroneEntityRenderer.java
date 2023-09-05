package io.github.xanderstuff.noctismaledictionem.entity.client;

import io.github.xanderstuff.noctismaledictionem.entity.CroneEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class CroneEntityRenderer extends GeoEntityRenderer<CroneEntity> {

	public CroneEntityRenderer(EntityRendererFactory.Context renderManager) {
		super(renderManager, new CroneEntityModel());
	}

//	@Override
//	public Identifier getTextureLocation(CroneEntity animatable) {
//		return new Identifier(MainMod.MODID, "textures/entity/crone.png");
//	}
}
