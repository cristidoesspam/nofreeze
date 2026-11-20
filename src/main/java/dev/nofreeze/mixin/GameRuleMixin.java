package dev.nofreeze.mixin;

import dev.nofreeze.NoFreeze;
import dev.nofreeze.config.NoFreezeConfig;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameRule.class)
public abstract class GameRuleMixin {
	@Shadow
	public abstract FeatureFlagSet requiredFeatures();

	public boolean isEnabled(final FeatureFlagSet enabledFeatures) {
		if (NoFreezeConfig.get().permanent && NoFreeze.isOurRule((GameRule<?>) (Object) this)) {
			return false;
		}
		return this.requiredFeatures().isSubsetOf(enabledFeatures);
	}
}
