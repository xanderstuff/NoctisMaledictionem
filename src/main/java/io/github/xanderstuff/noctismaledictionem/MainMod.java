package io.github.xanderstuff.noctismaledictionem;

import io.github.xanderstuff.noctismaledictionem.entity.Entities;
import io.github.xanderstuff.noctismaledictionem.item.Items;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMod implements ModInitializer {
	public static final String MODID = "noctis-maledictionem";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
//		LOGGER.info("Hello Fabric world!");
		Items.registerAll();
		Entities.registerAll();
	}
}