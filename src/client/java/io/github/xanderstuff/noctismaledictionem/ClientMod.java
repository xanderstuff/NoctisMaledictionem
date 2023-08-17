package io.github.xanderstuff.noctismaledictionem;

import io.github.xanderstuff.noctismaledictionem.entity.client.EntityRenderers;
import net.fabricmc.api.ClientModInitializer;

public class ClientMod implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRenderers.registerAll();
	}
}