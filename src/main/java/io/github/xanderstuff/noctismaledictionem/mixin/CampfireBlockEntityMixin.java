package io.github.xanderstuff.noctismaledictionem.mixin;

import io.github.xanderstuff.noctismaledictionem.block.AconiteBlock;
import io.github.xanderstuff.noctismaledictionem.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {
	@Inject(method = "litServerTick", at = @At(value = "HEAD"))
	private static void noctismaledictionem$checkForAconiteInCampfires(World world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CallbackInfo ci) {
		if (world instanceof ServerWorld serverWorld) {
			for (ItemStack itemStack : campfire.getItemsBeingCooked()) {
				if (itemStack.isOf(ModBlocks.ACONITE.asItem())) {
					AconiteBlock.onAconiteInCampfireTick(serverWorld, pos, state, campfire);
					break;
				}
			}
		}
	}

	//TODO: this could be moved to a separate Mixin class within the client sources instead
	@Inject(method = "clientTick", at = @At(value = "HEAD"))
	private static void noctismaledictionem$checkForAconiteInCampfiresClientside(World world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CallbackInfo ci) {
//		if (world instanceof ClientWorld clientWorld) {
		for (ItemStack itemStack : campfire.getItemsBeingCooked()) {
			if (itemStack.isOf(ModBlocks.ACONITE.asItem())) {
				AconiteBlock.onAconiteInCampfireClientTick(world, pos, state, campfire);
				break;
			}
		}
//		}
	}
}
