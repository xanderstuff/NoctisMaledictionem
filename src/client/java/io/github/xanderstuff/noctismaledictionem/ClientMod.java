package io.github.xanderstuff.noctismaledictionem;

import io.github.xanderstuff.noctismaledictionem.entity.client.ModEntityRenderers;
import io.github.xanderstuff.noctismaledictionem.particle.client.ModClientParticles;
import io.github.xanderstuff.noctismaledictionem.render.ModBlockRenderLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ClientMod implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModEntityRenderers.registerAll();
		ModBlockRenderLayers.registerAll();
		ModClientParticles.registerAll();
	}
}
