package net.itsyourdriver.morepotions.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MorePotionsConfig {
    public static boolean LEVITATION_POTION_ENABLED = true;
    public static boolean DECAY_POTION_ENABLED = true;
    public static boolean NAUSEA_POTION_ENABLED = true;
    public static boolean LUCK_POTION_ENABLED = true;
    public static boolean GLOWING_POTION_ENABLED = true;
    public static boolean BLINDNESS_POTION_ENABLED = true;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static class Data {
        boolean LEVITATION_POTION_ENABLED = true;
        boolean DECAY_POTION_ENABLED = true;
        boolean NAUSEA_POTION_ENABLED = true;
        boolean LUCK_POTION_ENABLED = true;
        boolean GLOWING_POTION_ENABLED = true;
        boolean BLINDNESS_POTION_ENABLED = true;
    }

    public static void load() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("morepotions-common.json");

        try {
            if (Files.notExists(path)) {
                saveDefaults(path);
            }

            Data data = GSON.fromJson(Files.readString(path), Data.class);
            if (data == null) data = new Data();

            LEVITATION_POTION_ENABLED = data.LEVITATION_POTION_ENABLED;
            DECAY_POTION_ENABLED = data.DECAY_POTION_ENABLED;
            NAUSEA_POTION_ENABLED = data.NAUSEA_POTION_ENABLED;
            LUCK_POTION_ENABLED = data.LUCK_POTION_ENABLED;
            GLOWING_POTION_ENABLED = data.GLOWING_POTION_ENABLED;
            BLINDNESS_POTION_ENABLED = data.BLINDNESS_POTION_ENABLED;
        } catch (IOException ignored) {
        }
    }

    private static void saveDefaults(Path path) throws IOException {
        Files.createDirectories(path.getParent());
        Files.writeString(path, GSON.toJson(new Data()));
    }
}