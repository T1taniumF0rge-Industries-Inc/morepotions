package net.itsyourdriver.morepotions.potion;

import net.itsyourdriver.morepotions.MorePotions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, MorePotions.MOD_ID);

    // Levitation
    public static final RegistryObject<Potion> LEVITATION_POTION = POTIONS.register("levitation_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 600, 0)));
    public static final RegistryObject<Potion> LONG_LEVITATION_POTION = POTIONS.register("long_levitation_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 1800, 0)));
    public static final RegistryObject<Potion> STRONG_LEVITATION_POTION = POTIONS.register("strong_levitation_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 600, 1)));

    // Withering
    public static final RegistryObject<Potion> DECAY_POTION = POTIONS.register("decay_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 900, 0)));
    public static final RegistryObject<Potion> LONG_DECAY_POTION = POTIONS.register("long_decay_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 1800, 0)));
    public static final RegistryObject<Potion> STRONG_DECAY_POTION = POTIONS.register("strong_decay_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 900, 1)));

    // Nausea Potion
    public static final RegistryObject<Potion> NAUSEA_POTION = POTIONS.register("nausea_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 300, 0))); // IDK why nausea is named confusion but oh well
    public static final RegistryObject<Potion> LONG_NAUSEA_POTION = POTIONS.register("long_nausea_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 900, 0)));
    public static final RegistryObject<Potion> STRONG_NAUSEA_POTION = POTIONS.register("strong_nausea_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 300, 1)));

    // Glowing Potion
    public static final RegistryObject<Potion> GLOWING_POTION = POTIONS.register("glowing_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 3600, 0))); // me when the glow berry doesnt make me glow (i got scammed)
    public static final RegistryObject<Potion> LONG_GLOWING_POTION = POTIONS.register("long_glowing_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 9600, 0)));

    // Blindness Potion
    public static final RegistryObject<Potion> BLINDNESS_POTION = POTIONS.register("blindness_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 300, 0)));
    public static final RegistryObject<Potion> LONG_BLINDNESS_POTION = POTIONS.register("long_blindness_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 900, 0)));

    private static final int[] LUCK_DURATIONS = {6000, 18000, 36000, 72000};
    private static final RegistryObject<Potion>[][] LUCK_VARIANTS = createLuckVariants();

    @SuppressWarnings("unchecked")
    private static RegistryObject<Potion>[][] createLuckVariants() {
        RegistryObject<Potion>[][] variants = new RegistryObject[5][4];
        variants[0][0] = null;

        for (int levelIndex = 0; levelIndex < variants.length; levelIndex++) {
            for (int extensionIndex = 0; extensionIndex < variants[levelIndex].length; extensionIndex++) {
                if (levelIndex == 0 && extensionIndex == 0) {
                    continue;
                }

                String id = "luck_l" + (levelIndex + 1) + "_e" + extensionIndex;
                int amplifier = levelIndex;
                int duration = LUCK_DURATIONS[extensionIndex];
                variants[levelIndex][extensionIndex] = POTIONS.register(id,
                        () -> new Potion(new MobEffectInstance(MobEffects.LUCK, duration, amplifier)));
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

        if (level == 1 && extensionTier == 0) {
            return Potions.LUCK;
        }

        return LUCK_VARIANTS[level - 1][extensionTier].get();
    }

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
