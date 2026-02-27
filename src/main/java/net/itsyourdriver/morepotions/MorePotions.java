package net.itsyourdriver.morepotions;

import net.fabricmc.api.ModInitializer;
import net.itsyourdriver.morepotions.config.MorePotionsConfig;
import net.itsyourdriver.morepotions.potion.ModPotions;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;

public class MorePotions implements ModInitializer {
    public static final String MOD_ID = "morepotions";

    @Override
    public void onInitialize() {
        MorePotionsConfig.load();
        ModPotions.init();

        if (MorePotionsConfig.LEVITATION_POTION_ENABLED) {
            BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.SHULKER_SHELL, ModPotions.LEVITATION_POTION);
            registerStandardModifiers(ModPotions.LEVITATION_POTION, ModPotions.LONG_LEVITATION_POTION, ModPotions.STRONG_LEVITATION_POTION, true);
        }

        if (MorePotionsConfig.DECAY_POTION_ENABLED) {
            BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.WITHER_ROSE, ModPotions.DECAY_POTION);
            registerStandardModifiers(ModPotions.DECAY_POTION, ModPotions.LONG_DECAY_POTION, ModPotions.STRONG_DECAY_POTION, true);
        }

        if (MorePotionsConfig.NAUSEA_POTION_ENABLED) {
            BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.POISONOUS_POTATO, ModPotions.NAUSEA_POTION);
            registerStandardModifiers(ModPotions.NAUSEA_POTION, ModPotions.LONG_NAUSEA_POTION, ModPotions.STRONG_NAUSEA_POTION, true);
        }

        if (MorePotionsConfig.LUCK_POTION_ENABLED) {
            BrewingRecipeRegistry.registerPotionRecipe(Potions.THICK, Items.RABBIT_FOOT, Potions.LUCK);
            registerLuckModifiers();
        }

        if (MorePotionsConfig.GLOWING_POTION_ENABLED) {
            BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.GLOW_INK_SAC, ModPotions.GLOWING_POTION);
            registerStandardModifiers(ModPotions.GLOWING_POTION, ModPotions.LONG_GLOWING_POTION, null, false);
        }

        if (MorePotionsConfig.BLINDNESS_POTION_ENABLED) {
            BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.INK_SAC, ModPotions.BLINDNESS_POTION);
            registerStandardModifiers(ModPotions.BLINDNESS_POTION, ModPotions.LONG_BLINDNESS_POTION, null, false);
        }
    }

    private static void registerStandardModifiers(Potion base, Potion extended, Potion amplified, boolean canAmplify) {
        BrewingRecipeRegistry.registerPotionRecipe(base, Items.REDSTONE, extended);

        if (canAmplify && amplified != null) {
            BrewingRecipeRegistry.registerPotionRecipe(base, Items.GLOWSTONE_DUST, amplified);
        }
    }

    private static void registerLuckModifiers() {
        for (int level = 1; level <= 5; level++) {
            for (int extensionTier = 0; extensionTier < 3; extensionTier++) {
                BrewingRecipeRegistry.registerPotionRecipe(
                        ModPotions.getLuckPotion(level, extensionTier),
                        Items.REDSTONE,
                        ModPotions.getLuckPotion(level, extensionTier + 1)
                );
            }
        }

        for (int extensionTier = 0; extensionTier <= 3; extensionTier++) {
            for (int level = 1; level < 5; level++) {
                BrewingRecipeRegistry.registerPotionRecipe(
                        ModPotions.getLuckPotion(level, extensionTier),
                        Items.GLOWSTONE_DUST,
                        ModPotions.getLuckPotion(level + 1, extensionTier)
                );
            }
        }
    }
}
