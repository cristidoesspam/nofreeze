package dev.nofreeze;

import dev.nofreeze.config.NoFreezeConfig;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gamerules.GameRule;
import org.jspecify.annotations.Nullable;

public final class FreezeRules {
	private FreezeRules() {}

	public static boolean blocksFreezing(final @Nullable LevelReader level) {
		if (NoFreezeConfig.get().permanent) {
			return true;
		}
		if (!(level instanceof ServerLevelAccessor accessor)) {
			return false;
		}

		GameRule<Boolean> rule = NoFreeze.rule();
		try {
			return accessor.getLevel().getGameRules().get(rule);
		} catch (IllegalArgumentException e) {
			return rule.defaultValue();
		}
	}
}
