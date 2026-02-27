package net.itsyourdriver.morepotions;

import net.fabricmc.api.ModInitializer;
import net.itsyourdriver.morepotions.config.MorePotionsConfig;
import net.itsyourdriver.morepotions.potion.ModPotions;
import net.minecraft.item.Items;
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
        }

        if (MorePotionsConfig.DECAY_POTION_ENABLED) {
            BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.WITHER_ROSE, ModPotions.DECAY_POTION);
        }

        if (MorePotionsConfig.NAUSEA_POTION_ENABLED) {
            BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.POISONOUS_POTATO, ModPotions.NAUSEA_POTION);
        }

        if (MorePotionsConfig.LUCK_POTION_ENABLED) {
            BrewingRecipeRegistry.registerPotionRecipe(Potions.THICK, Items.RABBIT_FOOT, Potions.LUCK);
        }

        if (MorePotionsConfig.GLOWING_POTION_ENABLED) {
            BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.GLOW_INK_SAC, ModPotions.GLOWING_POTION);
        }

        if (MorePotionsConfig.BLINDNESS_POTION_ENABLED) {
            BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.INK_SAC, ModPotions.BLINDNESS_POTION);
        }
    }
}