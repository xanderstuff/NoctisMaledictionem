package io.github.xanderstuff.noctismaledictionem.datagen;

import io.github.xanderstuff.noctismaledictionem.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
	protected ModLootTableProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {
		// note: see BlockLootTableGenerator for what methods are available,
		// and see VanillaBlockLootTableGenerator for examples

		addDrop(ModBlocks.ACONITE);
//		addPottedPlantDrops(ModBlocks.POTTED_ACONITE); //TODO
	}
}
