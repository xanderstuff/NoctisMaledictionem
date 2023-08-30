package io.github.xanderstuff.noctismaledictionem;

import io.github.xanderstuff.noctismaledictionem.entity.ModEntities;
import io.github.xanderstuff.noctismaledictionem.item.ModItemGroups;
import io.github.xanderstuff.noctismaledictionem.item.ModItems;
import io.github.xanderstuff.noctismaledictionem.sound.ModSoundEvents;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMod implements ModInitializer {
	public static final String MOD_ID = "noctis-maledictionem";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModSoundEvents.registerAll();
		ModItemGroups.registerAll();
		ModItems.registerAll();
		ModEntities.registerAll();
	}
}