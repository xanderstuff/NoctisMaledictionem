package io.github.xanderstuff.noctismaledictionem.block;

import io.github.xanderstuff.noctismaledictionem.particle.ModParticles;
import io.github.xanderstuff.noctismaledictionem.util.RandomUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AconiteBlock extends FlowerBlock {
	public AconiteBlock(Settings settings) {
		super(StatusEffects.NAUSEA, 20, settings);
	}

	public static void onAconiteInCampfireTick(ServerWorld serverWorld, BlockPos pos, BlockState blockState, CampfireBlockEntity campfire) {
//		double x = pos.getX() + 0.5;
//		double y = pos.getY() + 1;
//		double z = pos.getZ() + 0.5;
//		serverWorld.spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 20, 1, 0.25, 1, 0.01);

		// low, lingering smoke
//		serverWorld.spawnParticles(ModParticles.ACONITE_SMOKE, x, y - 0.5, z, 1, 1.5, 0.25, 1.5, 0.005);
	}

	@Environment(EnvType.CLIENT)
	public static void onAconiteInCampfireClientTick(World world, BlockPos pos, BlockState blockState, CampfireBlockEntity campfire) {
		// low, lingering smoke
		var random = world.random;
		double radius = 0.5;
		double horizontalVelocity = 0.04;
		double verticalVelocity = 0.005;
		double x = pos.getX() + 0.5 + RandomUtil.plusOrMinus(random, radius);
		double y = pos.getY() + 0.5;
		double z = pos.getZ() + 0.5 + RandomUtil.plusOrMinus(random, radius);
		double vx = RandomUtil.plusOrMinus(random, horizontalVelocity);
		double vy = RandomUtil.plusOrMinus(random, verticalVelocity);
		double vz = RandomUtil.plusOrMinus(random, horizontalVelocity);
		world.addParticle(ModParticles.ACONITE_SMOKE, x, y, z, vx, vy, vz);


		// rising smoke, similar to normal campfire smoke
		// copied from CampfireBlock#spawnSmokeParticle
//		var random = world.random;
//		if (random.nextFloat() < 0.3f) {
//			world.addImportantParticle(
//					ModParticles.ACONITE_SMOKE, true,
//					pos.getX() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1),
//					pos.getY() + random.nextDouble() + random.nextDouble(),
//					pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1),
//					0.0, 0.07, 0.0
//			);
//		}
	}
}
