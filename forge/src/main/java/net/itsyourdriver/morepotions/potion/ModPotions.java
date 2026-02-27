package net.itsyourdriver.morepotions.potion;

import net.itsyourdriver.morepotions.MorePotions;
import net.itsyourdriver.morepotions.config.MorePotionsCommonConfigs;
import net.itsyourdriver.morepotions.util.BetterBrewingRecipe;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
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

    // Withering
    public static final RegistryObject<Potion> DECAY_POTION = POTIONS.register("decay_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 900, 0)));

    // Nausea Potion
    public static final RegistryObject<Potion> NAUSEA_POTION = POTIONS.register("nausea_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.CONFUSION, 600, 0))); // IDK why nausea is named confusion but oh well

    // Glowing Potion
    public static final RegistryObject<Potion> GLOWING_POTION = POTIONS.register("glowing_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.GLOWING, 3600, 0))); // me when the glow berry doesnt make me glow (i got scammed)

    // Blindness Potion
    public static final RegistryObject<Potion> BLINDNESS_POTION = POTIONS.register("blindness_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 300, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

}
