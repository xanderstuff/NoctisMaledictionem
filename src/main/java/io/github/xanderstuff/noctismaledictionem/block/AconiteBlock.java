package io.github.xanderstuff.noctismaledictionem.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class AconiteBlock extends FlowerBlock {
	public AconiteBlock(Settings settings) {
		super(StatusEffects.NAUSEA, 20, settings);
	}

	public static void onAconiteInCampfireTick(ServerWorld serverWorld, BlockPos pos, BlockState blockState, CampfireBlockEntity campfire) {
		double x = pos.getX() + 0.5;
		double y = pos.getY() + 1;
		double z = pos.getZ() + 0.5;
		//FIXME: replace these testing particles with the intended effect
		serverWorld.spawnParticles(ParticleTypes.ELECTRIC_SPARK, x, y, z, 20, 0.25, 0.25, 0.25, 0.01);
	}
}
