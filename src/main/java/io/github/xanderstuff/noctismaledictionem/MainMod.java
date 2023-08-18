package io.github.xanderstuff.noctismaledictionem;

import io.github.xanderstuff.noctismaledictionem.entity.Entities;
import io.github.xanderstuff.noctismaledictionem.item.ItemGroups;
import io.github.xanderstuff.noctismaledictionem.item.Items;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMod implements ModInitializer {
	public static final String MOD_ID = "noctis-maledictionem";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ItemGroups.registerAll();
		Items.registerAll();
		Entities.registerAll();
	}
}