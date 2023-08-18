package io.github.xanderstuff.noctismaledictionem.entity.client;

import io.github.xanderstuff.noctismaledictionem.entity.ModEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EntityRenderers {
	public static void registerAll() {
		EntityRendererRegistry.register(ModEntities.CRONE, CroneEntityRenderer::new);
	}
}
