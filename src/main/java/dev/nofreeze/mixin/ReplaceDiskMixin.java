package dev.nofreeze.mixin;

import dev.nofreeze.FreezeRules;
import dev.nofreeze.config.NoFreezeConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.enchantment.effects.ReplaceDisk;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ReplaceDisk.class)
public abstract class ReplaceDiskMixin {
	@Redirect(
		method = "apply",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z")
	)
	private boolean nofreeze$noFrostedIce(final ServerLevel level, final BlockPos pos, final BlockState state) {
		if (state.is(Blocks.FROSTED_ICE) && NoFreezeConfig.get().blockFrostWalker && FreezeRules.blocksFreezing(level)) {
			return false;
		}
		return level.setBlockAndUpdate(pos, state);
	}
}
