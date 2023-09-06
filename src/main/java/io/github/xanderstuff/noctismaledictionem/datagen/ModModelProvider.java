package io.github.xanderstuff.noctismaledictionem.datagen;

import io.github.xanderstuff.noctismaledictionem.block.ModBlocks;
import io.github.xanderstuff.noctismaledictionem.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		// note: see BlockStateModelGenerator.register() for examples

		// registerFlowerPotPlant() (called createPlant in Mojang mappings) will run registerTintableCross() for us.
		blockStateModelGenerator.registerTintableCross(ModBlocks.ACONITE, BlockStateModelGenerator.TintType.NOT_TINTED);
//		blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.ACONITE, ModBlocks.POTTED_ACONITE, BlockStateModelGenerator.TintType.NOT_TINTED); //TODO

		// Spawn eggs have to be generated in generateBlockStateModels() instead of generateItemModels() for some unknown reason. Minecraft is weird.
		blockStateModelGenerator.registerParentedItemModel(ModItems.CRONE_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg")); // this has to be done in generateBlockStateModels() instead for some unknown reason
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		// note: see ItemModelGenerator.register() for examples

		itemModelGenerator.register(ModItems.VIAL, Models.GENERATED);
		itemModelGenerator.register(ModItems.VIAL_EMPTY, Models.GENERATED);
		// Spawn eggs have to be generated in generateBlockStateModels() instead of generateItemModels() for some unknown reason. Minecraft is weird.
//		itemModelGenerator.register(ModItems.CRONE_SPAWN_EGG, Models.GENERATED);
	}
}
