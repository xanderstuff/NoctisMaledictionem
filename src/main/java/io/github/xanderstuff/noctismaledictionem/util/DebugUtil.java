package io.github.xanderstuff.noctismaledictionem.util;

import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

public class DebugUtil {
	public static void sendChat(Entity sourceEntity, String... message) {
		StringBuilder output = new StringBuilder(sourceEntity.getName().getString());
		output.append(": ");
		for (String s : message) {
			output.append(s);
			output.append(" ");
		}
		sourceEntity.getServer().getPlayerManager().broadcast(Text.of(output.toString()), false);
	}
}
