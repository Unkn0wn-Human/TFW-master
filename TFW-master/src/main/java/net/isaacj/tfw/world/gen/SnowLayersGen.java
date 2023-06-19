package net.isaacj.tfw.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.isaacj.tfw.TFWmod;
import net.isaacj.tfw.world.feature.ModPlacedFeatures;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;

public class SnowLayersGen {

    public static void generateLayers() {

        //BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.NONE),
        //GenerationStep.Feature.TOP_LAYER_MODIFICATION, ModPlacedFeatures.SNOW_LAYERS_PLACED.getKey().get());
    }
}
