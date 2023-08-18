package io.github.xanderstuff.noctismaledictionem.item;

import io.github.xanderstuff.noctismaledictionem.MainMod;
import io.github.xanderstuff.noctismaledictionem.entity.Entities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class Items {
	public static final Item VIAL = new VialItem(new FabricItemSettings().maxCount(1));
	public static final Item VIAL_EMPTY = new VialEmptyItem(new FabricItemSettings().maxCount(64));
	public static final Item CRONE_SPAWN_EGG = new SpawnEggItem(Entities.CRONE, 0xFFFFFF, 0x000000, new FabricItemSettings());

	public static void registerAll() {
		registerItem("vial", VIAL);
		registerItem("vial_empty", VIAL_EMPTY);
		registerItem("crone_spawn_egg", CRONE_SPAWN_EGG, ItemGroups.SPAWN_EGGS);
	}

	private static void registerItem(String id, Item item) {
		Registry.register(Registries.ITEM, new Identifier(MainMod.MOD_ID, id), item);
		addToItemGroup(io.github.xanderstuff.noctismaledictionem.item.ItemGroups.MAIN, item);
	}

	private static void registerItem(String id, Item item, RegistryKey<ItemGroup> itemGroup) {
		Registry.register(Registries.ITEM, new Identifier(MainMod.MOD_ID, id), item);
		addToItemGroup(io.github.xanderstuff.noctismaledictionem.item.ItemGroups.MAIN, item);
		addToItemGroup(itemGroup, item);
	}

	private static void addToItemGroup(RegistryKey<ItemGroup> itemGroup, Item item) {
		ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> content.add(item));
	}
}
