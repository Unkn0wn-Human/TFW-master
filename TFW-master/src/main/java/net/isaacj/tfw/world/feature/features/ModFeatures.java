package net.isaacj.tfw.world.feature.features;

import net.isaacj.tfw.TFWmod;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;


public class ModFeatures {


    public static final Feature<DefaultFeatureConfig> SNOW_LAYERS = register("snow_layers_feature", new
            SnowLayersFeature(DefaultFeatureConfig.CODEC));

    public static final Feature<DefaultFeatureConfig> SNOW_LAYERS_FIX = register("snow_layers_fix_feature", new
            SnowLayersFixFeature(DefaultFeatureConfig.CODEC));

    public static final Feature<DefaultFeatureConfig> FROZEN_WARM_OCEAN_SPREAD = register("frozen_warm_ocean_spread", new
            FrozenWarmOceanSpreadFeature(DefaultFeatureConfig.CODEC));

    public static final Feature<DefaultFeatureConfig> FROZEN_WARM_OCEAN_LARGE_SPREAD = register("frozen_warm_ocean_large_spread", new
            FrozenWarmOceanLargeSpreadFeature(DefaultFeatureConfig.CODEC));

    public static final Feature<DefaultFeatureConfig> ICE_MOUND_FEATURE = register("ice_mound_feature", new
            IceMoundFeature(DefaultFeatureConfig.CODEC));

    private static <T extends Feature<?>> T register(String name, T entry) {
        return Registry.register(Registry.FEATURE, TFWmod.id(name), entry);
    }


    public static void registerFeatures() {
        System.out.println("Registering Features for " + TFWmod.MOD_ID);
    }
}
