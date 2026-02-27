package net.itsyourdriver.morepotions;

import com.mojang.logging.LogUtils;
import net.itsyourdriver.morepotions.config.MorePotionsCommonConfigs;
import net.itsyourdriver.morepotions.potion.ModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import org.slf4j.Logger;

@Mod(MorePotions.MOD_ID)
public class MorePotions {
    public static final String MOD_ID = "morepotions";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MorePotions(IEventBus modEventBus, ModContainer modContainer) {
        ModPotions.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerBrewingRecipes);
        modContainer.registerConfig(ModConfig.Type.COMMON, MorePotionsCommonConfigs.SPEC, "morepotions-common.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    private void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        var builder = event.getBuilder();

        if (MorePotionsCommonConfigs.LEVITATION_POTION_ENABLED.get()) {
            builder.addMix(Potions.AWKWARD, Items.SHULKER_SHELL, ModPotions.LEVITATION_POTION);
            registerStandardModifiers(builder, ModPotions.LEVITATION_POTION, ModPotions.LONG_LEVITATION_POTION, ModPotions.STRONG_LEVITATION_POTION, true);
        }

        if (MorePotionsCommonConfigs.DECAY_POTION_ENABLED.get()) {
            builder.addMix(Potions.AWKWARD, Items.WITHER_ROSE, ModPotions.DECAY_POTION);
            registerStandardModifiers(builder, ModPotions.DECAY_POTION, ModPotions.LONG_DECAY_POTION, ModPotions.STRONG_DECAY_POTION, true);
        }

        if (MorePotionsCommonConfigs.NAUSEA_POTION_ENABLED.get()) {
            builder.addMix(Potions.AWKWARD, Items.POISONOUS_POTATO, ModPotions.NAUSEA_POTION);
            registerStandardModifiers(builder, ModPotions.NAUSEA_POTION, ModPotions.LONG_NAUSEA_POTION, ModPotions.STRONG_NAUSEA_POTION, true);
        }

        if (MorePotionsCommonConfigs.LUCK_POTION_ENABLED.get()) {
            builder.addMix(Potions.THICK, Items.RABBIT_FOOT, Potions.LUCK);
        }

        if (MorePotionsCommonConfigs.GLOWING_POTION_ENABLED.get()) {
            builder.addMix(Potions.AWKWARD, Items.GLOW_INK_SAC, ModPotions.GLOWING_POTION);
            registerStandardModifiers(builder, ModPotions.GLOWING_POTION, ModPotions.LONG_GLOWING_POTION, null, false);
        }

        if (MorePotionsCommonConfigs.BLINDNESS_POTION_ENABLED.get()) {
            builder.addMix(Potions.AWKWARD, Items.INK_SAC, ModPotions.BLINDNESS_POTION);
            registerStandardModifiers(builder, ModPotions.BLINDNESS_POTION, ModPotions.LONG_BLINDNESS_POTION, null, false);
        }
    }

    private static void registerStandardModifiers(net.minecraft.world.item.alchemy.PotionBrewing.Builder builder, net.minecraft.core.Holder<Potion> base, net.minecraft.core.Holder<Potion> extended, net.minecraft.core.Holder<Potion> amplified, boolean canAmplify) {
        builder.addMix(base, Items.REDSTONE, extended);
        if (canAmplify && amplified != null) {
            builder.addMix(base, Items.GLOWSTONE_DUST, amplified);
        }
    }
}
