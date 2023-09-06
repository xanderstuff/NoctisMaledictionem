package io.github.xanderstuff.noctismaledictionem.datagen;

import io.github.xanderstuff.noctismaledictionem.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup arg) {
//		getOrCreateTagBuilder(ItemTags.SOMETHING)
//				.add(ModItems.SOMETHING)
//				.add(ModItems.SOMETHING_ELSE);
//
//		getOrCreateTagBuilder(ModItemTags.SOMETHING)
//				.add(ModItems.SOMETHING)
//				.add(ModItems.SOMETHING_ELSE);
	}
}
