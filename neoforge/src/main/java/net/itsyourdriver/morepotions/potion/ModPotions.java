package net.itsyourdriver.morepotions.potion;

import net.itsyourdriver.morepotions.MorePotions;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, MorePotions.MOD_ID);

    public static final DeferredHolder<Potion, Potion> LEVITATION_POTION = POTIONS.register("levitation_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.LEVITATION, 600, 0)));
    public static final DeferredHolder<Potion, Potion> LONG_LEVITATION_POTION = POTIONS.register("long_levitation_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.LEVITATION, 1800, 0)));
    public static final DeferredHolder<Potion, Potion> STRONG_LEVITATION_POTION = POTIONS.register("strong_levitation_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.LEVITATION, 600, 1)));

    public static final DeferredHolder<Potion, Potion> DECAY_POTION = POTIONS.register("decay_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.WITHER, 900, 0)));
    public static final DeferredHolder<Potion, Potion> LONG_DECAY_POTION = POTIONS.register("long_decay_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.WITHER, 1800, 0)));
    public static final DeferredHolder<Potion, Potion> STRONG_DECAY_POTION = POTIONS.register("strong_decay_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.WITHER, 900, 1)));

    public static final DeferredHolder<Potion, Potion> NAUSEA_POTION = POTIONS.register("nausea_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.CONFUSION, 300, 0)));
    public static final DeferredHolder<Potion, Potion> LONG_NAUSEA_POTION = POTIONS.register("long_nausea_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.CONFUSION, 900, 0)));
    public static final DeferredHolder<Potion, Potion> STRONG_NAUSEA_POTION = POTIONS.register("strong_nausea_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.CONFUSION, 300, 1)));

    public static final DeferredHolder<Potion, Potion> GLOWING_POTION = POTIONS.register("glowing_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.GLOWING, 3600, 0)));
    public static final DeferredHolder<Potion, Potion> LONG_GLOWING_POTION = POTIONS.register("long_glowing_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.GLOWING, 9600, 0)));

    public static final DeferredHolder<Potion, Potion> BLINDNESS_POTION = POTIONS.register("blindness_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.BLINDNESS, 300, 0)));
    public static final DeferredHolder<Potion, Potion> LONG_BLINDNESS_POTION = POTIONS.register("long_blindness_potion",
            () -> createPotion(new MobEffectInstance(MobEffects.BLINDNESS, 900, 0)));

    private static final int[] LUCK_DURATIONS = {6000, 18000, 36000, 72000};
    private static final Holder<Potion>[][] LUCK_VARIANTS = createLuckVariants();

    private ModPotions() {}

    private static Holder<Potion>[][] createLuckVariants() {
        @SuppressWarnings("unchecked")
        Holder<Potion>[][] variants = (Holder<Potion>[][]) new Holder[5][4];
        variants[0][0] = Potions.LUCK;

        for (int levelIndex = 0; levelIndex < variants.length; levelIndex++) {
            for (int extensionIndex = 0; extensionIndex < variants[levelIndex].length; extensionIndex++) {
                if (levelIndex == 0 && extensionIndex == 0) {
                    continue;
                }

                String id = "luck_l" + (levelIndex + 1) + "_e" + extensionIndex;
                int duration = LUCK_DURATIONS[extensionIndex];
                int amplifier = levelIndex;
                variants[levelIndex][extensionIndex] = POTIONS.register(id,
                        () -> createPotion(new MobEffectInstance(MobEffects.LUCK, duration, amplifier)));
            }
        }

        return variants;
    }

    public static Holder<Potion> getLuckPotion(int level, int extensionTier) {
        if (level < 1 || level > 5) {
            throw new IllegalArgumentException("Luck level must be in [1..5]");
        }
        if (extensionTier < 0 || extensionTier > 3) {
            throw new IllegalArgumentException("Luck extension tier must be in [0..3]");
        }

        return LUCK_VARIANTS[level - 1][extensionTier];
    }

    private static Potion createPotion(MobEffectInstance effect) {
        MobEffectInstance[] singleEffect = new MobEffectInstance[]{effect};

        Potion potion = tryConstructPotion(new Class<?>[]{String.class, MobEffectInstance[].class}, "", singleEffect);
        if (potion != null) {
            return potion;
        }

        potion = tryConstructPotion(new Class<?>[]{MobEffectInstance.class}, effect);
        if (potion != null) {
            return potion;
        }

        potion = tryConstructPotion(new Class<?>[]{MobEffectInstance[].class}, (Object) singleEffect);
        if (potion != null) {
            return potion;
        }

        for (Constructor<?> constructor : Potion.class.getConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            try {
                if (parameterTypes.length == 2
                        && parameterTypes[0] == String.class
                        && parameterTypes[1] == MobEffectInstance[].class) {
                    return (Potion) constructor.newInstance("", singleEffect);
                }
                if (parameterTypes.length == 1 && parameterTypes[0] == MobEffectInstance.class) {
                    return (Potion) constructor.newInstance(effect);
                }
                if (parameterTypes.length == 1 && parameterTypes[0] == MobEffectInstance[].class) {
                    return (Potion) constructor.newInstance((Object) singleEffect);
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
            }
        }

        throw new IllegalStateException("Unable to construct Potion instance for current Minecraft runtime");
    }

    private static Potion tryConstructPotion(Class<?>[] parameterTypes, Object... args) {
        try {
            Constructor<Potion> constructor = Potion.class.getConstructor(parameterTypes);
            return constructor.newInstance(args);
        } catch (ReflectiveOperationException ignored) {
            return null;
        }
    }

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
