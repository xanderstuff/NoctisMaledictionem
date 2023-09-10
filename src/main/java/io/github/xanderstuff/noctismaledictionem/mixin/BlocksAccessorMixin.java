package io.github.xanderstuff.noctismaledictionem.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Blocks.class)
public interface BlocksAccessorMixin {
	@Invoker
	static FlowerPotBlock callCreateFlowerPotBlock(Block flower, FeatureFlag... requiredFeatures) {
		throw new IllegalStateException();
	}
}
