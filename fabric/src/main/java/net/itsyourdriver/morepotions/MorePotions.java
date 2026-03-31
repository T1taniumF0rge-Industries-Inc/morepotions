package net.itsyourdriver.morepotions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.itsyourdriver.morepotions.config.MorePotionsConfig;
import net.itsyourdriver.morepotions.potion.ModPotions;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;

public class MorePotions implements ModInitializer {
    public static final String MOD_ID = "morepotions";

    @Override
    public void onInitialize() {
        MorePotionsConfig.load();
        ModPotions.init();

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            if (MorePotionsConfig.LEVITATION_POTION_ENABLED) {
                registerPotionRecipe(builder, Potions.AWKWARD, Items.SHULKER_SHELL, ModPotions.LEVITATION_POTION);
                registerStandardModifiers(builder, ModPotions.LEVITATION_POTION, ModPotions.LONG_LEVITATION_POTION, ModPotions.STRONG_LEVITATION_POTION, true);
            }

            if (MorePotionsConfig.DECAY_POTION_ENABLED) {
                registerPotionRecipe(builder, Potions.AWKWARD, Items.WITHER_ROSE, ModPotions.DECAY_POTION);
                registerStandardModifiers(builder, ModPotions.DECAY_POTION, ModPotions.LONG_DECAY_POTION, ModPotions.STRONG_DECAY_POTION, true);
            }

            if (MorePotionsConfig.NAUSEA_POTION_ENABLED) {
                registerPotionRecipe(builder, Potions.AWKWARD, Items.POISONOUS_POTATO, ModPotions.NAUSEA_POTION);
                registerStandardModifiers(builder, ModPotions.NAUSEA_POTION, ModPotions.LONG_NAUSEA_POTION, ModPotions.STRONG_NAUSEA_POTION, true);
            }

            if (MorePotionsConfig.LUCK_POTION_ENABLED) {
                registerPotionRecipe(builder, Potions.THICK, Items.RABBIT_FOOT, Potions.LUCK);
                registerLuckModifiers(builder);
            }

            if (MorePotionsConfig.GLOWING_POTION_ENABLED) {
                registerPotionRecipe(builder, Potions.AWKWARD, Items.GLOW_INK_SAC, ModPotions.GLOWING_POTION);
                registerStandardModifiers(builder, ModPotions.GLOWING_POTION, ModPotions.LONG_GLOWING_POTION, null, false);
            }

            if (MorePotionsConfig.BLINDNESS_POTION_ENABLED) {
                registerPotionRecipe(builder, Potions.AWKWARD, Items.INK_SAC, ModPotions.BLINDNESS_POTION);
                registerStandardModifiers(builder, ModPotions.BLINDNESS_POTION, ModPotions.LONG_BLINDNESS_POTION, null, false);
            }
        });
    }

    private static void registerPotionRecipe(BrewingRecipeRegistry.Builder builder, RegistryEntry<Potion> input, Item ingredient, RegistryEntry<Potion> output) {
        builder.registerPotionRecipe(input, Ingredient.ofItems(ingredient), output);
    }

    private static void registerStandardModifiers(BrewingRecipeRegistry.Builder builder, RegistryEntry<Potion> base, RegistryEntry<Potion> extended, RegistryEntry<Potion> amplified, boolean canAmplify) {
        registerPotionRecipe(builder, base, Items.REDSTONE, extended);

        if (canAmplify && amplified != null) {
            registerPotionRecipe(builder, base, Items.GLOWSTONE_DUST, amplified);
        }
    }

    private static void registerLuckModifiers(BrewingRecipeRegistry.Builder builder) {
        for (int level = 1; level <= 5; level++) {
            for (int extensionTier = 0; extensionTier < 3; extensionTier++) {
                registerPotionRecipe(
                        builder,
                        ModPotions.getLuckPotion(level, extensionTier),
                        Items.REDSTONE,
                        ModPotions.getLuckPotion(level, extensionTier + 1)
                );
            }
        }

        for (int extensionTier = 0; extensionTier <= 3; extensionTier++) {
            for (int level = 1; level < 5; level++) {
                registerPotionRecipe(
                        builder,
                        ModPotions.getLuckPotion(level, extensionTier),
                        Items.GLOWSTONE_DUST,
                        ModPotions.getLuckPotion(level + 1, extensionTier)
                );
            }
        }
    }
}
