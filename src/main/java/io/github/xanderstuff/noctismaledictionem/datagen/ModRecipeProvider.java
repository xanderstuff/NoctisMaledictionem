package io.github.xanderstuff.noctismaledictionem.datagen;

import io.github.xanderstuff.noctismaledictionem.MainMod;
import io.github.xanderstuff.noctismaledictionem.block.ModBlocks;
import io.github.xanderstuff.noctismaledictionem.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
	public ModRecipeProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generate(Consumer<RecipeJsonProvider> exporter) {
		// note: see RecipeProvider for what methods are available,
		// and see VanillaRecipeProvider for examples

		CookingRecipeJsonBuilder.createCampfireCooking( //FIXME: this actually sets the CookingRecipeCategory to FOOD, but we should use MISC. However, this may not have a visible effect in-game anyways
						Ingredient.ofItems(ModBlocks.ACONITE),
						RecipeCategory.MISC,
						Items.AIR,
						0.0f,
						600
				)
				.criterion(hasItem(ModBlocks.ACONITE), conditionsFromItem(ModBlocks.ACONITE))
				.offerTo(exporter, new Identifier(MainMod.MOD_ID, "aconite_campfire")); // Use a custom recipe id since the default is to use the item id of the resulting item (air, since this is a dummy recipe)

		// We aren't using CookingRecipeJsonBuilder#createCampfireCooking because we want to set cookingRecipeCategory to MISC instead of the default (FOOD)
//		new CookingRecipeJsonBuilder( //TODO: this needs an accessor Mixin first, since the constructor is private
//				RecipeCategory.MISC,
//				CookingRecipeCategory.MISC,
//				Items.AIR,
//				Ingredient.ofItems(ModBlocks.ACONITE),
//				0.0f,
//				600,
//				RecipeSerializer.CAMPFIRE_COOKING
//		)
//				.criterion(hasItem(ModBlocks.ACONITE), conditionsFromItem(ModBlocks.ACONITE))
//				.offerTo(exporter);


//		ShapelessRecipeJsonBuilder.create(RecipeCategory.SOMETHING, ModItems.RESULT, 1)
//				.input(ModItems.INPUT1)
//				.input(ModItems.INPUT2)
//				.input(ModItems.INPUT3)
//				.criterion(hasItem(ModItems.INPUT1), conditionsFromItem(ModItems.INPUT1))
//				.criterion(hasItem(ModItems.INPUT2), conditionsFromItem(ModItems.INPUT2))
//				.criterion(hasItem(ModItems.INPUT3), conditionsFromItem(ModItems.INPUT3))
//				.offerTo(exporter);
//
//		ShapedRecipeJsonBuilder.create(RecipeCategory.SOMETHING, ModItems.RESULT, 1)
//				.input('A', ModItems.INPUT1)
//				.input('B', ModItems.INPUT2)
//				.input('S', Items.STICK)
//				.pattern(" A ")
//				.pattern("ABA")
//				.pattern(" S ")
//				.criterion(hasItem(ModItems.INPUT1), conditionsFromItem(ModItems.INPUT1))
//				.criterion(hasItem(ModItems.INPUT2), conditionsFromItem(ModItems.INPUT2))
//				.offerTo(exporter);
	}
}
