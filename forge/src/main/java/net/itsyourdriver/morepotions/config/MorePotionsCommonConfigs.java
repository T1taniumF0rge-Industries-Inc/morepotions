package net.itsyourdriver.morepotions.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MorePotionsCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> LEVITATION_POTION_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DECAY_POTION_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<Boolean> NAUSEA_POTION_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<Boolean> LUCK_POTION_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<Boolean> GLOWING_POTION_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<Boolean> BLINDNESS_POTION_ENABLED;

    static {
        BUILDER.push("Configs for More Potions (Note: These will still show up in the creative menu, but disabling them here will disable the ability to brew them.)");


        LEVITATION_POTION_ENABLED = BUILDER.comment("Should the levitation potion be enabled?")
                .define("LEVITATION_POTION_ENABLED", true);

        DECAY_POTION_ENABLED = BUILDER.comment("Should the decay (wither) potion be enabled?")
                .define("DECAY_POTION_ENABLED", true);

        NAUSEA_POTION_ENABLED = BUILDER.comment("Should the nausea potion be enabled?")
                .define("NAUSEA_POTION_ENABLED", true);

        LUCK_POTION_ENABLED = BUILDER.comment("Should the luck potion be enabled?")
                .define("LUCK_POTION_ENABLED", true);

        GLOWING_POTION_ENABLED = BUILDER.comment("Should the glowing potion be enabled?")
                .define("GLOWING_POTION_ENABLED", true);

        BLINDNESS_POTION_ENABLED = BUILDER.comment("Should the blindness potion be enabled?")
                .define("BLINDNESS_POTION_ENABLED", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}