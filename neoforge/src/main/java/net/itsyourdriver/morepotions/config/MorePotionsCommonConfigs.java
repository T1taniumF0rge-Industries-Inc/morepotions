package net.itsyourdriver.morepotions.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class MorePotionsCommonConfigs {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue LEVITATION_POTION_ENABLED;
    public static final ModConfigSpec.BooleanValue DECAY_POTION_ENABLED;
    public static final ModConfigSpec.BooleanValue NAUSEA_POTION_ENABLED;
    public static final ModConfigSpec.BooleanValue LUCK_POTION_ENABLED;
    public static final ModConfigSpec.BooleanValue GLOWING_POTION_ENABLED;
    public static final ModConfigSpec.BooleanValue BLINDNESS_POTION_ENABLED;

    static {
        BUILDER.push("Configs for More Potions (These still appear in creative tabs, but disabling removes brewing recipes.)");

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
