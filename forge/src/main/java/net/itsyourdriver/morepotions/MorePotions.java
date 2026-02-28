package net.itsyourdriver.morepotions;

import com.mojang.logging.LogUtils;
import net.itsyourdriver.morepotions.potion.ModPotions;
import net.itsyourdriver.morepotions.util.BetterBrewingRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.itsyourdriver.morepotions.config.MorePotionsCommonConfigs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(MorePotions.MOD_ID)
public class MorePotions {
    public static final String MOD_ID = "morepotions";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MorePotions() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModPotions.register(modEventBus);
        modEventBus.addListener(this::commonSetup);

        // Config Loading
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MorePotionsCommonConfigs.SPEC, "morepotions-common.toml");


        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            // Levitation
            if (MorePotionsCommonConfigs.LEVITATION_POTION_ENABLED.get() == true) {
                BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                        Items.SHULKER_SHELL, ModPotions.LEVITATION_POTION.get()));
                registerStandardModifiers(
                        ModPotions.LEVITATION_POTION.get(),
                        ModPotions.LONG_LEVITATION_POTION.get(),
                        ModPotions.STRONG_LEVITATION_POTION.get(),
                        true
                );
            }

            // Decay Potion
            if (MorePotionsCommonConfigs.DECAY_POTION_ENABLED.get() == true) {
                BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                        Items.WITHER_ROSE, ModPotions.DECAY_POTION.get()));
                registerStandardModifiers(
                        ModPotions.DECAY_POTION.get(),
                        ModPotions.LONG_DECAY_POTION.get(),
                        ModPotions.STRONG_DECAY_POTION.get(),
                        true
                );
            }

            // Nausea Potion
            if (MorePotionsCommonConfigs.NAUSEA_POTION_ENABLED.get() == true) {
                BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                        Items.POISONOUS_POTATO, ModPotions.NAUSEA_POTION.get()));
                registerStandardModifiers(
                        ModPotions.NAUSEA_POTION.get(),
                        ModPotions.LONG_NAUSEA_POTION.get(),
                        ModPotions.STRONG_NAUSEA_POTION.get(),
                        true
                );
            }

            // Luck Potion
            if (MorePotionsCommonConfigs.LUCK_POTION_ENABLED.get() == true) {
                BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.THICK,
                        Items.RABBIT_FOOT, Potions.LUCK));
                registerLuckModifiers();
            }

            // Glowing Potion
            if (MorePotionsCommonConfigs.GLOWING_POTION_ENABLED.get() == true) {
                BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                        Items.GLOW_INK_SAC, ModPotions.GLOWING_POTION.get()));
                registerStandardModifiers(
                        ModPotions.GLOWING_POTION.get(),
                        ModPotions.LONG_GLOWING_POTION.get(),
                        null,
                        false
                );
            }

            // Blindness Potion
            if (MorePotionsCommonConfigs.BLINDNESS_POTION_ENABLED.get() == true) {
                BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                        Items.INK_SAC, ModPotions.BLINDNESS_POTION.get()));
                registerStandardModifiers(
                        ModPotions.BLINDNESS_POTION.get(),
                        ModPotions.LONG_BLINDNESS_POTION.get(),
                        null,
                        false
                );
            }


        });
    }

    private static void registerStandardModifiers(Potion base, Potion extended, Potion amplified, boolean canAmplify) {
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(base, Items.REDSTONE, extended));

        if (canAmplify && amplified != null) {
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(base, Items.GLOWSTONE_DUST, amplified));
        }
    }

    private static void registerLuckModifiers() {
        for (int level = 1; level <= 5; level++) {
            for (int extensionTier = 0; extensionTier < 3; extensionTier++) {
                BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                        ModPotions.getLuckPotion(level, extensionTier),
                        Items.REDSTONE,
                        ModPotions.getLuckPotion(level, extensionTier + 1)
                ));
            }
        }

        for (int extensionTier = 0; extensionTier <= 3; extensionTier++) {
            for (int level = 1; level < 5; level++) {
                BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                        ModPotions.getLuckPotion(level, extensionTier),
                        Items.GLOWSTONE_DUST,
                        ModPotions.getLuckPotion(level + 1, extensionTier)
                ));
            }
        }
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
