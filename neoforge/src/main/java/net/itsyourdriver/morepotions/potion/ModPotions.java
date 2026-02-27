package net.itsyourdriver.morepotions.potion;

import net.itsyourdriver.morepotions.MorePotions;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, MorePotions.MOD_ID);

    public static final DeferredHolder<Potion, Potion> LEVITATION_POTION = POTIONS.register("levitation_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 600, 0)));
    public static final DeferredHolder<Potion, Potion> LONG_LEVITATION_POTION = POTIONS.register("long_levitation_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 1800, 0)));
    public static final DeferredHolder<Potion, Potion> STRONG_LEVITATION_POTION = POTIONS.register("strong_levitation_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 600, 1)));

    public static final DeferredHolder<Potion, Potion> DECAY_POTION = POTIONS.register("decay_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 900, 0)));
    public static final DeferredHolder<Potion, Potion> LONG_DECAY_POTION = POTIONS.register("long_decay_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 1800, 0)));
    public static final DeferredHolder<Potion, Potion> STRONG_DECAY_POTION = POTIONS.register("strong_decay_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 900, 1)));

    public static final DeferredHolder<Potion, Potion> NAUSEA_POTION = POTIONS.register("nausea_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 300, 0)));
    public static final DeferredHolder<Potion, Potion> LONG_NAUSEA_POTION = POTIONS.register("long_nausea_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 900, 0)));
    public static final DeferredHolder<Potion, Potion> STRONG_NAUSEA_POTION = POTIONS.register("strong_nausea_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 300, 1)));

    public static final DeferredHolder<Potion, Potion> GLOWING_POTION = POTIONS.register("glowing_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 3600, 0)));
    public static final DeferredHolder<Potion, Potion> LONG_GLOWING_POTION = POTIONS.register("long_glowing_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 9600, 0)));

    public static final DeferredHolder<Potion, Potion> BLINDNESS_POTION = POTIONS.register("blindness_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 300, 0)));
    public static final DeferredHolder<Potion, Potion> LONG_BLINDNESS_POTION = POTIONS.register("long_blindness_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 900, 0)));

    private ModPotions() {}

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
