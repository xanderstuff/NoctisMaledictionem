package io.github.xanderstuff.noctismaledictionem.particle;

import io.github.xanderstuff.noctismaledictionem.MainMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
	public static final DefaultParticleType ACONITE_SMOKE = FabricParticleTypes.simple();

	public static void registerAll() {
		// Remember to register particles in ModClientParticles for client-side rendering as well!
		registerParticleType("aconite_smoke", ACONITE_SMOKE);
	}

	private static void registerParticleType(String id, DefaultParticleType particleType) {
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MainMod.MOD_ID, id), particleType);
	}
}
