package io.github.xanderstuff.noctismaledictionem.block;

import io.github.xanderstuff.noctismaledictionem.MainMod;
import io.github.xanderstuff.noctismaledictionem.item.ModItems;
import io.github.xanderstuff.noctismaledictionem.mixin.BlocksAccessorMixin;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBlocks {
	public static final Block ACONITE = new AconiteBlock(FabricBlockSettings.copyOf(Blocks.ALLIUM));
	public static final Block POTTED_ACONITE = BlocksAccessorMixin.callCreateFlowerPotBlock(ACONITE);

	public static void registerAll() {
		registerBlockAndDefaultItem("aconite", ACONITE, ItemGroups.NATURAL);
		registerBlock("potted_aconite", POTTED_ACONITE);
	}

	//note: since there's 4 different block registration methods already,
	// if more still need to be added, it may be worth considering the builder pattern instead
	private static void registerBlockAndDefaultItem(String id, Block block) {
		registerBlockAndItem(id, block, new FabricItemSettings());
	}

	private static void registerBlockAndDefaultItem(String id, Block block, RegistryKey<ItemGroup> itemGroup) {
		registerBlockAndItem(id, block, new FabricItemSettings(), itemGroup);
	}

	private static void registerBlockAndItem(String id, Block block, Item.Settings itemSettings) {
		registerBlock(id, block);
		//Registry.register(Registries.ITEM, new Identifier(MainMod.MOD_ID, id), new BlockItem(block, itemSettings));
		ModItems.registerItem(id, new BlockItem(block, itemSettings));
	}

	private static void registerBlockAndItem(String id, Block block, Item.Settings itemSettings, RegistryKey<ItemGroup> itemGroup) {
		registerBlock(id, block);
		//Registry.register(Registries.ITEM, new Identifier(MainMod.MOD_ID, id), new BlockItem(block, itemSettings));
		ModItems.registerItem(id, new BlockItem(block, itemSettings), itemGroup);
	}

	private static void registerBlock(String id, Block block) {
		Registry.register(Registries.BLOCK, new Identifier(MainMod.MOD_ID, id), block);
	}
}
