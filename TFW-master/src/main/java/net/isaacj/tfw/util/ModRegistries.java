package net.isaacj.tfw.util;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.isaacj.tfw.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class ModRegistries {



    public static void registerModStuffs(){

        registerStrippables();
        registerSeeThrough();
    }

    private static void registerStrippables() {

        StrippableBlockRegistry.register(ModBlocks.SYPH_WOOD, ModBlocks.STRIPPED_SYPH_WOOD);
        StrippableBlockRegistry.register(ModBlocks.SYPH_LOG, ModBlocks.STRIPPED_SYPH_LOG);
    }

    public static void registerSeeThrough(){

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.UNLIT_LANTERN, RenderLayer.getCutout());
    }
}
