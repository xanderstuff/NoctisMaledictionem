package io.github.xanderstuff.noctismaledictionem.util;

import io.github.xanderstuff.noctismaledictionem.MainMod;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class DebugUtil {
	public static void sendChat(Entity sourceEntity, String... message) {
		sendChat(sourceEntity.getWorld(), sourceEntity.getName().getString(), message);
	}

	public static void sendChat(World world, String source, String... messages) {
		StringBuilder output = new StringBuilder(source);
		output.append(": ");
		for (String s : messages) {
			output.append(s);
			output.append(" ");
		}
		if (world.isClient()) {
			//FIXME: send chat message client-side
			MainMod.LOGGER.info(output.toString());
		} else {
			world.getServer().getPlayerManager().broadcast(Text.of(output.toString()), false);
		}
	}
}
