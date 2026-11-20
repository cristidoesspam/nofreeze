package dev.nofreeze.client;

import dev.nofreeze.config.NoFreezeConfig;
import dev.nofreeze.config.NoFreezeSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class NoFreezeScreen extends OptionsSubScreen {
	private static final String KEY = "nofreeze.option.";

	private final NoFreezeSettings s = NoFreezeConfig.get();

	public NoFreezeScreen(Screen parent) {
		super(parent, Minecraft.getInstance().options, Component.translatable("nofreeze.screen.title"));
	}

	@Override
	protected void addOptions() {
		list.addBig(toggle("permanent", s.permanent, v -> s.permanent = v));
		list.addBig(toggle("block_frost_walker", s.blockFrostWalker, v -> s.blockFrostWalker = v));

		list.addHeader(Component.translatable("nofreeze.screen.note.hosted"));
		list.addHeader(Component.translatable("nofreeze.screen.note.restart"));
		list.addHeader(Component.translatable("nofreeze.screen.note.server"));
	}

	@Override
	public void removed() {
		NoFreezeConfig.save();
		super.removed();
	}

	@Override
	public void onClose() {
		super.onClose();
		NoFreezeConfig.save();
	}

	private static OptionInstance<Boolean> toggle(String name, boolean value, Consumer<Boolean> setter) {
		Tooltip tip = Tooltip.create(Component.translatable(KEY + name + ".tooltip"));
		return OptionInstance.createBoolean(KEY + name, v -> tip, value, setter::accept);
	}
}
