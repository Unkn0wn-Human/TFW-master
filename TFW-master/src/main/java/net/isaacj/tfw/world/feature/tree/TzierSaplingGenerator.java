package net.isaacj.tfw.world.feature.tree;

import net.isaacj.tfw.world.feature.ModConfiguredFeatures;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

public class TzierSaplingGenerator extends LargeTreeSaplingGenerator {

    public TzierSaplingGenerator() {
    }

    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return ModConfiguredFeatures.TZIER_TREE;
    }

    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random) {
        return ModConfiguredFeatures.TZIER_MONARCH_TREE;
    }
}
