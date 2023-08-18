package io.github.xanderstuff.noctismaledictionem.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static io.github.xanderstuff.noctismaledictionem.MainMod.MOD_ID;

public class ModItemGroups {
	public static final RegistryKey<ItemGroup> MAIN = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "main"));

	public static void registerAll() {
		Registry.register(Registries.ITEM_GROUP, MAIN, FabricItemGroup.builder()
				.displayName(Text.translatable("itemgroup." + MOD_ID + ".main"))
				.icon(() -> new ItemStack(ModItems.VIAL)) //FIXME: replace ItemGroup icon with a more interesting item from this mod
				.build()
		);
	}
}
