package io.github.xanderstuff.noctismaledictionem.datagen;

import io.github.xanderstuff.noctismaledictionem.entity.ModEntities;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider {
	public ModEntityTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup arg) {
//		getOrCreateTagBuilder(EntityTypeTags.SKELETONS)
//				.add(EntityType.COW)
//				.add(ModEntities.SOMETHING)
//				.add(ModEntities.SOMETHING_ELSE);
//
//		getOrCreateTagBuilder(ModEntityTags.SOMETHING)
//				.add(ModEntities.SOMETHING)
//				.add(ModEntities.SOMETHING_ELSE);
	}
}
