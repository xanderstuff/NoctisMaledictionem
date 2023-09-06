package io.github.xanderstuff.noctismaledictionem.util;

import net.minecraft.util.math.random.Random;

public class RandomUtil {
	@SafeVarargs
	public static <T> T pickOne(Random randomInstance, T... items) {
		int choiceIndex = randomInstance.nextInt(items.length);
		return items[choiceIndex];
	}

	public static double range(Random randomInstance, double min, double max) {
		return min + randomInstance.nextDouble() * (max - min);
	}

	public static double plusOrMinus(Random randomInstance, double distanceFromZero) {
		return -distanceFromZero + randomInstance.nextDouble() * distanceFromZero * 2;
	}
}
