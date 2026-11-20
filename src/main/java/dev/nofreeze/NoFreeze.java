package dev.nofreeze;

import dev.nofreeze.config.NoFreezeConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoFreeze implements ModInitializer {
	public static final String MOD_ID = "nofreeze";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Identifier RULE_ID = Identifier.fromNamespaceAndPath(MOD_ID, "water_cannot_freeze");

	private static GameRule<Boolean> waterCannotFreeze;

	@Override
	public void onInitialize() {
		NoFreezeConfig.load();

		waterCannotFreeze = GameRuleBuilder.forBoolean(true)
			.category(GameRuleCategory.UPDATES)
			.buildAndRegister(RULE_ID);
	}

	public static GameRule<Boolean> rule() {
		return waterCannotFreeze;
	}

	public static boolean isOurRule(final GameRule<?> rule) {
		return rule == waterCannotFreeze;
	}
}
