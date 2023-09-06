package io.github.xanderstuff.noctismaledictionem.particle.client;

import io.github.xanderstuff.noctismaledictionem.particle.ModParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

@Environment(EnvType.CLIENT)
public class ModClientParticles {
	public static void registerAll() {
		registerParticle(ModParticles.ACONITE_SMOKE, CampfireSmokeParticle.CosySmokeFactory::new);
	}

	private static <T extends ParticleEffect> void registerParticle(ParticleType<T> particleType, ParticleFactoryRegistry.PendingParticleFactory<T> particleFactory) {
		ParticleFactoryRegistry.getInstance().register(particleType, particleFactory);
	}
}
