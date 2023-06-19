package net.isaacj.tfw.world.feature;

import net.isaacj.tfw.TFWmod;
import net.isaacj.tfw.block.ModBlocks;
import net.isaacj.tfw.world.feature.tree.placer.foliage.ModBurntTadaciaFoliagePlacer;
import net.isaacj.tfw.world.feature.tree.placer.foliage.ModLargeTadaciaFoliagePlacer;
import net.isaacj.tfw.world.feature.tree.placer.foliage.ModTadaciaFoliagePlacer;
import net.isaacj.tfw.world.feature.tree.placer.trunk.ModBranchingTrunkPlacer;
import net.isaacj.tfw.world.feature.tree.placer.foliage.ModSyphFoliagePlacer;
import net.isaacj.tfw.world.feature.tree.placer.trunk.ModLargeTadaciaTrunkPlacer;
import net.isaacj.tfw.world.feature.tree.placer.trunk.ModTadaciaTrunkPlacer;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures {


    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SYPH_TREE =
            ConfiguredFeatures.register("syph_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.SYPH_LOG),
                    new ModBranchingTrunkPlacer(11, 0, 6),
                    BlockStateProvider.of(ModBlocks.SYPH_LEAVES),
                    new ModSyphFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0), ConstantIntProvider.create(2)),
                    new TwoLayersFeatureSize(1, 0, 2)).build());



    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> LARGE_TADACIA_TREE =
            ConfiguredFeatures.register("large_tadacia_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.TADACIA_LOG),
                    new ModLargeTadaciaTrunkPlacer(20, 0, 8),
                    BlockStateProvider.of(ModBlocks.TADACIA_LEAVES),
                    new ModLargeTadaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), ConstantIntProvider.create(12)),
                    new TwoLayersFeatureSize(1, 0, 2)).build());

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BLUE_SYPH_TREE =
            ConfiguredFeatures.register("blue_syph_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.SYPH_LOG),
                    new StraightTrunkPlacer(4, 0, 5),
                    BlockStateProvider.of(ModBlocks.BLUE_SYPH_LEAVES),
                    new ModSyphFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), ConstantIntProvider.create(2)),
                    new TwoLayersFeatureSize(1, 0, 2)).build());

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BURNT_TADACIA_TREE =
            ConfiguredFeatures.register("burnt_tadacia_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.BURNT_TADACIA_LOG),
                    new ModTadaciaTrunkPlacer(5, 0, 10),
                    BlockStateProvider.of(ModBlocks.BURNT_TADACIA_LEAVES),
                    new ModBurntTadaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), ConstantIntProvider.create(2)),
                    new TwoLayersFeatureSize(1, 0, 2)).build());

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TADACIA_TREE =
            ConfiguredFeatures.register("tadacia_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.TADACIA_LOG),
                    new StraightTrunkPlacer(7, 0, 5),
                    BlockStateProvider.of(ModBlocks.TADACIA_LEAVES),
                    new ModTadaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), ConstantIntProvider.create(7)),
                    new TwoLayersFeatureSize(1, 0, 2)).build());


    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + TFWmod.MOD_ID);
    }
}
