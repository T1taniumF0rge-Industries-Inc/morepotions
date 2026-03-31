package net.itsyourdriver.morepotions;

import com.mojang.logging.LogUtils;
import net.itsyourdriver.morepotions.config.MorePotionsCommonConfigs;
import net.itsyourdriver.morepotions.potion.ModPotions;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
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

@Mod(MorePotions.MOD_ID)
public class MorePotions {
    public static final String MOD_ID = "morepotions";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MorePotions() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModPotions.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerBrewingRecipes);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MorePotionsCommonConfigs.SPEC, "morepotions-common.toml");

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    private void registerBrewingRecipes(BrewingRecipeRegisterEvent event) {
        var builder = event.getBuilder();

        if (MorePotionsCommonConfigs.LEVITATION_POTION_ENABLED.get()) {
            builder.addMix(Potions.AWKWARD, Items.SHULKER_SHELL, ModPotions.holderOf(ModPotions.LEVITATION_POTION));
            registerStandardModifiers(builder, ModPotions.holderOf(ModPotions.LEVITATION_POTION), ModPotions.holderOf(ModPotions.LONG_LEVITATION_POTION), ModPotions.holderOf(ModPotions.STRONG_LEVITATION_POTION), true);
        }

        if (MorePotionsCommonConfigs.DECAY_POTION_ENABLED.get()) {
            builder.addMix(Potions.AWKWARD, Items.WITHER_ROSE, ModPotions.holderOf(ModPotions.DECAY_POTION));
            registerStandardModifiers(builder, ModPotions.holderOf(ModPotions.DECAY_POTION), ModPotions.holderOf(ModPotions.LONG_DECAY_POTION), ModPotions.holderOf(ModPotions.STRONG_DECAY_POTION), true);
        }

        if (MorePotionsCommonConfigs.NAUSEA_POTION_ENABLED.get()) {
            builder.addMix(Potions.AWKWARD, Items.POISONOUS_POTATO, ModPotions.holderOf(ModPotions.NAUSEA_POTION));
            registerStandardModifiers(builder, ModPotions.holderOf(ModPotions.NAUSEA_POTION), ModPotions.holderOf(ModPotions.LONG_NAUSEA_POTION), ModPotions.holderOf(ModPotions.STRONG_NAUSEA_POTION), true);
        }

        if (MorePotionsCommonConfigs.LUCK_POTION_ENABLED.get()) {
            builder.addMix(Potions.THICK, Items.RABBIT_FOOT, Potions.LUCK);
            registerLuckModifiers(builder);
        }

        if (MorePotionsCommonConfigs.GLOWING_POTION_ENABLED.get()) {
            builder.addMix(Potions.AWKWARD, Items.GLOW_INK_SAC, ModPotions.holderOf(ModPotions.GLOWING_POTION));
            registerStandardModifiers(builder, ModPotions.holderOf(ModPotions.GLOWING_POTION), ModPotions.holderOf(ModPotions.LONG_GLOWING_POTION), null, false);
        }

        if (MorePotionsCommonConfigs.BLINDNESS_POTION_ENABLED.get()) {
            builder.addMix(Potions.AWKWARD, Items.INK_SAC, ModPotions.holderOf(ModPotions.BLINDNESS_POTION));
            registerStandardModifiers(builder, ModPotions.holderOf(ModPotions.BLINDNESS_POTION), ModPotions.holderOf(ModPotions.LONG_BLINDNESS_POTION), null, false);
        }
    }

    private static void registerStandardModifiers(net.minecraft.world.item.alchemy.PotionBrewing.Builder builder, Holder<Potion> base, Holder<Potion> extended, Holder<Potion> amplified, boolean canAmplify) {
        builder.addMix(base, Items.REDSTONE, extended);

        if (canAmplify && amplified != null) {
            builder.addMix(base, Items.GLOWSTONE_DUST, amplified);
        }
    }

    private static void registerLuckModifiers(net.minecraft.world.item.alchemy.PotionBrewing.Builder builder) {
        for (int level = 1; level <= 5; level++) {
            for (int extensionTier = 0; extensionTier < 3; extensionTier++) {
                builder.addMix(
                        ModPotions.getLuckPotion(level, extensionTier),
                        Items.REDSTONE,
                        ModPotions.getLuckPotion(level, extensionTier + 1)
                );
            }
        }

        for (int extensionTier = 0; extensionTier <= 3; extensionTier++) {
            for (int level = 1; level < 5; level++) {
                builder.addMix(
                        ModPotions.getLuckPotion(level, extensionTier),
                        Items.GLOWSTONE_DUST,
                        ModPotions.getLuckPotion(level + 1, extensionTier)
                );
            }
        }
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {}
    }
}
