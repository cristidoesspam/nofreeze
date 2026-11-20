package dev.nofreeze.mixin;

import dev.nofreeze.FreezeRules;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public abstract class BiomeMixin {
	@Inject(method = "shouldFreeze(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Z)Z", at = @At("HEAD"), cancellable = true)
	private void nofreeze$keepWaterLiquid(
		final LevelReader level, final BlockPos pos, final boolean checkNeighbors, final CallbackInfoReturnable<Boolean> cir
	) {
		if (FreezeRules.blocksFreezing(level)) {
			cir.setReturnValue(false);
		}
	}
}
