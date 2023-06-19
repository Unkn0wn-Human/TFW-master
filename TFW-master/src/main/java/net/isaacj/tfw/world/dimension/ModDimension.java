package net.isaacj.tfw.world.dimension;

import net.isaacj.tfw.TFWmod;
import net.isaacj.tfw.block.ModBlocks;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

import javax.swing.text.AbstractDocument;

public class ModDimension {

        private static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(Registry.DIMENSION_KEY,
                new Identifier(TFWmod.MOD_ID, "frozen"));
        public static RegistryKey<World> FROZEN_KEY = RegistryKey.of(Registry.WORLD_KEY, DIMENSION_KEY.getValue());
        private static final RegistryKey<DimensionType> DIMENSION_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
                new Identifier(TFWmod.MOD_ID, "frozen_type"));

        public static void register() {
            System.out.println("Registering ModDimensions for " + TFWmod.MOD_ID);
        }
    }


