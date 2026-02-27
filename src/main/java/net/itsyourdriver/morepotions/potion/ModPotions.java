package net.itsyourdriver.morepotions.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static final Potion LEVITATION_POTION = register("levitation_potion",
            new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 600, 0)));

    public static final Potion DECAY_POTION = register("decay_potion",
            new Potion(new StatusEffectInstance(StatusEffects.WITHER, 900, 0)));

    public static final Potion NAUSEA_POTION = register("nausea_potion",
            new Potion(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 0)));

    public static final Potion GLOWING_POTION = register("glowing_potion",
            new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 3600, 0)));

    public static final Potion BLINDNESS_POTION = register("blindness_potion",
            new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 300, 0)));

    private static Potion register(String id, Potion potion) {
        return Registry.register(Registries.POTION, new Identifier("morepotions", id), potion);
    }

    public static void init() {
        // static init trigger
    }
}
        return Registry.register(Registries.POTION, new Identifier(MorePotions.MOD_ID, id), potion);
    }

    public static void init() {
    }
}
