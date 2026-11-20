package dev.nofreeze.client;

import com.mojang.blaze3d.platform.InputConstants;
import dev.nofreeze.NoFreeze;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class NoFreezeClient implements ClientModInitializer {
	private static final KeyMapping.Category CATEGORY =
		KeyMapping.Category.register(Identifier.fromNamespaceAndPath(NoFreeze.MOD_ID, NoFreeze.MOD_ID));

	@Override
	public void onInitializeClient() {
		KeyMapping openSettingsKey = KeyMappingHelper.registerKeyMapping(
			new KeyMapping("key.nofreeze.settings", InputConstants.UNKNOWN.getValue(), CATEGORY));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openSettingsKey.consumeClick()) {
				if (client.player != null) {
					client.gui.setScreen(new NoFreezeScreen(client.gui.screen()));
				}
			}
		});
	}
}
