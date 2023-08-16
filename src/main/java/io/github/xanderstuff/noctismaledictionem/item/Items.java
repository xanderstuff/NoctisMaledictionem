package io.github.xanderstuff.noctismaledictionem.item;

import io.github.xanderstuff.noctismaledictionem.MainMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Items {
	public static final Item VIAL = new VialItem(new FabricItemSettings().maxCount(1));
	public static final Item VIAL_EMPTY = new VialEmptyItem(new FabricItemSettings().maxCount(64));

	public static void registerAll(){
		Registry.register(Registries.ITEM, new Identifier(MainMod.MODID, "vial"), VIAL);
		Registry.register(Registries.ITEM, new Identifier(MainMod.MODID, "vial_empty"), VIAL_EMPTY);
	}
}
