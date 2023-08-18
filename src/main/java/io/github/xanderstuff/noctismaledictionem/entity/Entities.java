package io.github.xanderstuff.noctismaledictionem.entity;

import io.github.xanderstuff.noctismaledictionem.MainMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Entities {
	public static final EntityType<CroneEntity> CRONE = FabricEntityTypeBuilder
			// use MISC spawngroup for no spawn cap - used if an entity is not spawned naturally
			.create(SpawnGroup.MISC, CroneEntity::new)
			.dimensions(EntityDimensions.fixed(0.9f, 1.8f))
			.build();

	public static void registerAll() {
		Registry.register(Registries.ENTITY_TYPE, new Identifier(MainMod.MOD_ID, "crone"), CRONE);
		FabricDefaultAttributeRegistry.register(CRONE, CroneEntity.setAttributes());


	}
}
