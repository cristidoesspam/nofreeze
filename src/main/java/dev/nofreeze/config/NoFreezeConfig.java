package dev.nofreeze.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.nofreeze.NoFreeze;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class NoFreezeConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static volatile NoFreezeSettings current = new NoFreezeSettings();

	private NoFreezeConfig() {}

	public static NoFreezeSettings get() {
		return current;
	}

	private static Path file() {
		return FabricLoader.getInstance().getConfigDir().resolve("nofreeze.json");
	}

	public static void load() {
		Path file = file();
		if (Files.exists(file)) {
			try (Reader reader = Files.newBufferedReader(file)) {
				NoFreezeSettings loaded = GSON.fromJson(reader, NoFreezeSettings.class);
				if (loaded != null) {
					current = loaded;
				}
			} catch (Exception e) {
				NoFreeze.LOGGER.warn("Could not read {}, using defaults", file, e);
			}
		}
		save();
	}

	public static void save() {
		Path file = file();
		try {
			Files.createDirectories(file.getParent());
			try (Writer writer = Files.newBufferedWriter(file)) {
				GSON.toJson(current, writer);
			}
		} catch (IOException e) {
			NoFreeze.LOGGER.warn("Could not write {}", file, e);
		}
	}
}
