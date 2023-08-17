package io.github.xanderstuff.noctismaledictionem.entity.client;

import io.github.xanderstuff.noctismaledictionem.entity.Entities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EntityRenderers {
	public static void registerAll() {
		EntityRendererRegistry.register(Entities.CRONE, CroneEntityRenderer::new);
	}
}
