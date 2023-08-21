package io.github.xanderstuff.noctismaledictionem.util;

import net.minecraft.util.math.random.Random;

public class RandomUtil {
	@SafeVarargs
	public static <T> T pickOne(Random randomInstance, T... items) {
		int choiceIndex = randomInstance.nextInt(items.length);
		return items[choiceIndex];
	}
}
