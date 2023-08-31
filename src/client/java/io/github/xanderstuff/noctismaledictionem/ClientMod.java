package io.github.xanderstuff.noctismaledictionem;

import io.github.xanderstuff.noctismaledictionem.entity.client.ModEntityRenderers;
import io.github.xanderstuff.noctismaledictionem.render.ModBlockRenderLayers;
import net.fabricmc.api.ClientModInitializer;

public class ClientMod implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModEntityRenderers.registerAll();
		ModBlockRenderLayers.registerAll();
	}
}
