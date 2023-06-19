package net.isaacj.tfw.tag;

import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class CustomBiomeTags{

    public static final TagKey<Biome> IS_FROZEN = of("tfw:frozen_biomes");
    public static final TagKey<Biome> FULL_SNOW_LAYERS = of("tfw:full_snow_layers");
    public static final TagKey<Biome> ONE_SNOW_LAYER = of("tfw:one_snow_layers");
    public static final TagKey<Biome> Glacial = of("tfw:glacial");

    private CustomBiomeTags(){
    }

    private static TagKey<Biome> of(String id) {
        return TagKey.of(Registry.BIOME_KEY, new Identifier(id));
    }
}
