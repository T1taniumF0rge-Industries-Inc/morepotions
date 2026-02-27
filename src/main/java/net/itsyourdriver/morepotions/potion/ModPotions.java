package net.itsyourdriver.morepotions.potion;

import net.itsyourdriver.morepotions.MorePotions;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModPotions {
    public static final Potion LEVITATION_POTION = registerPotion("levitation_potion",
            new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 600, 0)));

    public static final Potion DECAY_POTION = registerPotion("decay_potion",
            new Potion(new StatusEffectInstance(StatusEffects.WITHER, 900, 0)));

    public static final Potion NAUSEA_POTION = registerPotion("nausea_potion",
            new Potion(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 0)));

    public static final Potion GLOWING_POTION = registerPotion("glowing_potion",
            new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 3600, 0)));

    public static final Potion BLINDNESS_POTION = registerPotion("blindness_potion",
            new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 300, 0)));

    private ModPotions() {
    }

    private static Potion registerPotion(String id, Potion potion) {
        return Registry.register(Registries.POTION, new Identifier(MorePotions.MOD_ID, id), potion);
    }

    public static void init() {
    }
}
