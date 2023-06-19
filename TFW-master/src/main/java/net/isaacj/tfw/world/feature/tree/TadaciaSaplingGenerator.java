package net.isaacj.tfw.world.feature.tree;

import net.isaacj.tfw.block.ModBlocks;
import net.isaacj.tfw.world.feature.ModConfiguredFeatures;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

import java.util.Random;


    public class TadaciaSaplingGenerator extends LargeTreeSaplingGenerator {
        public TadaciaSaplingGenerator() {
        }

        protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
            return ModConfiguredFeatures.TADACIA_TREE;
        }

        protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random) {
            return ModConfiguredFeatures.LARGE_TADACIA_TREE;
        }
    }
