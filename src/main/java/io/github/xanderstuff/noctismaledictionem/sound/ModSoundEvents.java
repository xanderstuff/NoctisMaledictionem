package io.github.xanderstuff.noctismaledictionem.sound;

import io.github.xanderstuff.noctismaledictionem.MainMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
	// --- Crone ---
	public static final SoundEvent ENTITY_CRONE_IDLE = getSoundEvent("entity.crone.idle");
	public static final SoundEvent ENTITY_CRONE_ATTACK = getSoundEvent("entity.crone.attack");
	public static final SoundEvent ENTITY_CRONE_THROW_POTION = getSoundEvent("entity.crone.throw_potion");
	public static final SoundEvent ENTITY_CRONE_HURT = getSoundEvent("entity.crone.hurt");
	public static final SoundEvent ENTITY_CRONE_DEATH = getSoundEvent("entity.crone.death");
	//public static final SoundEvent ENTITY_CRONE_TRADE_IDLE = getSoundEvent("entity.crone.trade.idle"); // not used (intentional)
	public static final SoundEvent ENTITY_CRONE_TRADE_AGREE = getSoundEvent("entity.crone.trade.agree"); // no sound (intentional)
	public static final SoundEvent ENTITY_CRONE_TRADE_DISAGREE = getSoundEvent("entity.crone.trade.disagree"); // no sound (intentional)
	public static final SoundEvent ENTITY_CRONE_TRADE_ACCEPTED = getSoundEvent("entity.crone.trade.accepted");


	public static void registerAll() {
		registerSoundEvent(ENTITY_CRONE_IDLE);
		registerSoundEvent(ENTITY_CRONE_ATTACK);
		registerSoundEvent(ENTITY_CRONE_THROW_POTION);
		registerSoundEvent(ENTITY_CRONE_HURT);
		registerSoundEvent(ENTITY_CRONE_DEATH);
//		registerSoundEvent(ENTITY_CRONE_TRADE_IDLE); // not used (intentional)
		registerSoundEvent(ENTITY_CRONE_TRADE_AGREE); // no sound (intentional)
		registerSoundEvent(ENTITY_CRONE_TRADE_DISAGREE); // no sound (intentional)
		registerSoundEvent(ENTITY_CRONE_TRADE_ACCEPTED);
	}

	private static SoundEvent getSoundEvent(String id) {
		return SoundEvent.of(new Identifier(MainMod.MOD_ID, id));
	}

	private static void registerSoundEvent(SoundEvent soundEvent) {
		Registry.register(Registries.SOUND_EVENT, soundEvent.getId(), soundEvent);
	}
}
