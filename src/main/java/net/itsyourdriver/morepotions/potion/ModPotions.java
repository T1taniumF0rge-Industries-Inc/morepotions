package net.itsyourdriver.morepotions.potion;

import net.itsyourdriver.morepotions.MorePotions;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModPotions {
    public static final Potion LEVITATION_POTION = registerPotion("levitation_potion",
            new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 600, 0)));
    public static final Potion LONG_LEVITATION_POTION = registerPotion("long_levitation_potion",
            new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 1800, 0)));
    public static final Potion STRONG_LEVITATION_POTION = registerPotion("strong_levitation_potion",
            new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 600, 1)));

    public static final Potion DECAY_POTION = registerPotion("decay_potion",
            new Potion(new StatusEffectInstance(StatusEffects.WITHER, 900, 0)));
    public static final Potion LONG_DECAY_POTION = registerPotion("long_decay_potion",
            new Potion(new StatusEffectInstance(StatusEffects.WITHER, 1800, 0)));
    public static final Potion STRONG_DECAY_POTION = registerPotion("strong_decay_potion",
            new Potion(new StatusEffectInstance(StatusEffects.WITHER, 900, 1)));

    public static final Potion NAUSEA_POTION = registerPotion("nausea_potion",
            new Potion(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0)));
    public static final Potion LONG_NAUSEA_POTION = registerPotion("long_nausea_potion",
            new Potion(new StatusEffectInstance(StatusEffects.NAUSEA, 900, 0)));
    public static final Potion STRONG_NAUSEA_POTION = registerPotion("strong_nausea_potion",
            new Potion(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 1)));

    public static final Potion GLOWING_POTION = registerPotion("glowing_potion",
            new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 3600, 0)));
    public static final Potion LONG_GLOWING_POTION = registerPotion("long_glowing_potion",
            new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 9600, 0)));

    public static final Potion BLINDNESS_POTION = registerPotion("blindness_potion",
            new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 300, 0)));
    public static final Potion LONG_BLINDNESS_POTION = registerPotion("long_blindness_potion",
            new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 900, 0)));

    private static final int[] LUCK_DURATIONS = {6000, 18000, 36000, 72000};
    private static final Potion[][] LUCK_VARIANTS = createLuckVariants();

    private ModPotions() {
    }

    private static Potion[][] createLuckVariants() {
        Potion[][] variants = new Potion[5][4];
        variants[0][0] = Potions.LUCK;

        for (int levelIndex = 0; levelIndex < variants.length; levelIndex++) {
            for (int extensionIndex = 0; extensionIndex < variants[levelIndex].length; extensionIndex++) {
                if (levelIndex == 0 && extensionIndex == 0) {
                    continue;
                }

                String id = "luck_l" + (levelIndex + 1) + "_e" + extensionIndex;
                variants[levelIndex][extensionIndex] = registerPotion(id,
                        new Potion(new StatusEffectInstance(StatusEffects.LUCK, LUCK_DURATIONS[extensionIndex], levelIndex)));
            }
        }

        return variants;
    }

    public static Potion getLuckPotion(int level, int extensionTier) {
        if (level < 1 || level > 5) {
            throw new IllegalArgumentException("Luck level must be in [1..5]");
        }
        if (extensionTier < 0 || extensionTier > 3) {
            throw new IllegalArgumentException("Luck extension tier must be in [0..3]");
        }

        return LUCK_VARIANTS[level - 1][extensionTier];
    }

    private static Potion registerPotion(String id, Potion potion) {
        return Registry.register(Registries.POTION, new Identifier(MorePotions.MOD_ID, id), potion);
    }

    public static void init() {
    }
}
