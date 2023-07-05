package net.isaacj.tfw.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.isaacj.tfw.TFWmod;
import net.isaacj.tfw.block.custom.*;
import net.isaacj.tfw.block.custom.crop.BluePearlBlock;
import net.isaacj.tfw.block.custom.crop.ModBlueMumbadeBlock;
import net.isaacj.tfw.block.custom.crop.PinkPearlBlock;
import net.isaacj.tfw.block.custom.crop.PurpleCarrotsBlock;
import net.isaacj.tfw.item.ModItemGroups;
import net.isaacj.tfw.world.feature.tree.*;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {


    public static final Block REDSTONE_INFUSED_OBSIDIAN = registerBlock("redstone_infused_obsidian",
            new RedstoneInfusedObsidianBlock(FabricBlockSettings.of(Material.STONE).strength(50f, 6000f ).requiresTool()
            .luminance((state) -> state.get(RedstoneInfusedObsidianBlock.ON) ? 10 : 0 )),
                    ModItemGroups.TFW);

    public static final Block NETHERITE_BLASTER = registerBlock("netherite_blaster",
            new NetheriteBlasterBlock(FabricBlockSettings.of(Material.STONE).nonOpaque()),
            ModItemGroups.TFW);

    public static final Block COMPACT_SNOW = registerBlock("compact_snow",
            new Block(FabricBlockSettings.of(Material.SNOW_BLOCK).sounds(BlockSoundGroup.SNOW).strength(0.4f, 2f ).requiresTool()),
            ModItemGroups.TFW);

    public static final Block POINTED_ICE = registerBlock("pointed_ice",
            new PointedIceBlock(FabricBlockSettings.of(Material.ICE).sounds(BlockSoundGroup.GLASS).dynamicBounds().strength(0.1f, 1f )),
            ModItemGroups.TFW);


    public static final Block COMPACT_SNOW_SYPH = registerBlock("compact_snow_syph",
            new Block(FabricBlockSettings.of(Material.SNOW_BLOCK).sounds(BlockSoundGroup.SNOW).strength(0.4f, 2f )),
            ModItemGroups.TFW);

    public static final Block COMPACT_SNOW_TZIER = registerBlock("compact_snow_tzier",
            new Block(FabricBlockSettings.of(Material.SNOW_BLOCK).sounds(BlockSoundGroup.SNOW).strength(0.4f, 2f )),
            ModItemGroups.TFW);
    public static final Block COMPACT_SNOW_BURNT_TADACIA = registerBlock("compact_snow_burnt_tadacia",
            new Block(FabricBlockSettings.of(Material.SNOW_BLOCK).sounds(BlockSoundGroup.SNOW).strength(0.4f, 2f )),
            ModItemGroups.TFW);

    public static final Block COMPACT_SNOW_TADACIA = registerBlock("compact_snow_tadacia",
            new Block(FabricBlockSettings.of(Material.SNOW_BLOCK).sounds(BlockSoundGroup.SNOW).strength(0.4f, 2f )),
            ModItemGroups.TFW);


    public static final Block TZIER_LOG = registerBlock("tzier_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)), ModItemGroups.TFW);

    public static final Block TZIER_LEAVES = registerBlock("tzier_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES)), ModItemGroups.TFW);

    public static final Block TZIER_SAPLING = registerBlock("tzier_sapling",
            new ModSaplingBlock(new TzierSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ModItemGroups.TFW);

    public static final Block TADACIA_LOG = registerBlock("tadacia_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)), ModItemGroups.TFW);


    public static final Block BURNT_TADACIA_LOG = registerBlock("burnt_tadacia_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)), ModItemGroups.TFW);

    public static final Block TADACIA_PLANKS = registerBlock("tadacia_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)),ModItemGroups.TFW);

    public static final Block TADACIA_FENCE = registerBlock("tadacia_fence",
            new FenceBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).
                    strength(0.5f, 2.5f )),
            ModItemGroups.TFW);

    public static final Block TADACIA_FENCE_GATE = registerBlock("tadacia_fence_gate",
            new FenceGateBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).
                    strength(0.5f, 2.5f )),
            ModItemGroups.TFW);

    public static final Block BURNT_TADACIA_SAPLING = registerBlock("burnt_tadacia_sapling",
            new ModSaplingBlock(new BurntTadaciaSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ModItemGroups.TFW);

    public static final Block TADACIA_SAPLING = registerBlock("tadacia_sapling",
            new ModSaplingBlock(new TadaciaSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ModItemGroups.TFW);

    public static final Block BURNT_TADACIA_LEAVES = registerBlock("burnt_tadacia_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES)), ModItemGroups.TFW);

    public static final Block TADACIA_LEAVES = registerBlock("tadacia_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES)), ModItemGroups.TFW);

    public static final Block TADACIA_STAIRS = registerBlock("tadacia_stairs",
            new ModStairsBlock(ModBlocks.TADACIA_PLANKS.getDefaultState(),FabricBlockSettings.of(Material.WOOD).
                    sounds(BlockSoundGroup.WOOD).strength(2f, 15f )),
            ModItemGroups.TFW);

    public static final Block TADACIA_SLAB = registerBlock("tadacia_slab",
            new SlabBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).
                    strength(2f, 15f )),
            ModItemGroups.TFW);

    public static final Block TADACIA_PRESSURE_PLATE = registerBlock("tadacia_pressure_plate",
            new ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING ,FabricBlockSettings.of(Material.WOOD)
                    .sounds(BlockSoundGroup.WOOD).
                            strength(0.5f, 2.5f )),
            ModItemGroups.TFW);


    public static final Block MICENTEAN_ORE = registerBlock("micentean_ore",
            new Block(FabricBlockSettings.copy(Blocks.IRON_ORE).requiresTool()), ModItemGroups.TFW);

    public static final Block DEEPSLATE_MICENTEAN_ORE = registerBlock("deepslate_micentean_ore",
            new Block(FabricBlockSettings.copy(Blocks.DEEPSLATE_IRON_ORE)), ModItemGroups.TFW);

    public static final Block MICENTEAN_BLOCK = registerBlock("micentean_block",
            new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK)), ModItemGroups.TFW);

    public static final Block PURPLE_SAND = registerBlock("purple_sand" ,
            new SandBlock(11968731, FabricBlockSettings.copy(Blocks.SAND)),ModItemGroups.TFW);



    public static final Block FROST_BLOOM = registerBlock("frost_bloom",
            new FrostBloomBlock(FabricBlockSettings.copy(Blocks.KELP).luminance((state) -> 7)), ModItemGroups.TFW);


    public static final Block RUBBERMOSS = registerBlock("rubbermoss",
            new RubbermossBlock(FabricBlockSettings.copy(Blocks.AZALEA).nonOpaque()), ModItemGroups.TFW);


    public static final Block FROST_BLOOM_PLANT = registerBlock("frost_bloom_plant",
           new ModFrostBloomBlockTop(FabricBlockSettings.copy(Blocks.KELP_PLANT)), ModItemGroups.TFW);

    public static final Block VENTIAN = registerBlock("ventian",
            new SeagrassBlock(FabricBlockSettings.copy(Blocks.KELP_PLANT)),ModItemGroups.TFW);

    public static final Block BLUELEAF_THETTLE = registerBlock("blueleaf_thettle",
            new ThettleBlock(FabricBlockSettings.copy(Blocks.AZALEA)), ModItemGroups.TFW);

    public static final Block SMALL_BLUELEAF_THETTLE = registerBlock("small_blueleaf_thettle",
            new SmallThettleBlock(FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ModItemGroups.TFW);



    public static final Block SYPH_LOG = registerBlock("syph_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)), ModItemGroups.TFW);

    public static final Block SYPH_PLANKS = registerBlock("syph_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)),ModItemGroups.TFW);

    public static final Block SYPH_WOOD = registerBlock("syph_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)),ModItemGroups.TFW);

    public static final Block STRIPPED_SYPH_LOG = registerBlock("stripped_syph_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG)),ModItemGroups.TFW);

    public static final Block STRIPPED_SYPH_WOOD = registerBlock("stripped_syph_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD)),ModItemGroups.TFW);

    public static final Block SYPH_LEAVES = registerBlock("syph_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES)), ModItemGroups.TFW);

    public static final Block BLUE_SYPH_LEAVES = registerBlock("blue_syph_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES)), ModItemGroups.TFW);

    public static final Block SYPH_SAPLING = registerBlock("syph_sapling",
            new ModSaplingBlock(new SyphSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ModItemGroups.TFW);

    public static final Block BLUE_SYPH_SAPLING = registerBlock("blue_syph_sapling",
            new ModSaplingBlock(new BlueSyphSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ModItemGroups.TFW);

    public static final Block SYPH_STAIRS = registerBlock("syph_stairs",
            new ModStairsBlock(ModBlocks.SYPH_PLANKS.getDefaultState(),FabricBlockSettings.of(Material.WOOD).
                    sounds(BlockSoundGroup.WOOD).strength(2f, 15f )),
            ModItemGroups.TFW);

    public static final Block SYPH_SLAB = registerBlock("syph_slab",
            new SlabBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).
                    strength(2f, 15f )),
            ModItemGroups.TFW);

    public static final Block SYPH_PRESSURE_PLATE = registerBlock("syph_pressure_plate",
            new ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING ,FabricBlockSettings.of(Material.WOOD)
                    .sounds(BlockSoundGroup.WOOD).
                    strength(0.5f, 2.5f )),
            ModItemGroups.TFW);

    public static final Block SYPH_FENCE = registerBlock("syph_fence",
            new FenceBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).
                            strength(0.5f, 2.5f )),
            ModItemGroups.TFW);

    public static final Block SYPH_FENCE_GATE = registerBlock("syph_fence_gate",
            new FenceGateBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).
                            strength(0.5f, 2.5f )),
            ModItemGroups.TFW);

    public static final Block SYPH_DOOR = registerBlock("syph_door",
            new ModDoorBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).nonOpaque()
                    .strength(2f, 15f )),
            ModItemGroups.TFW);

    public static final Block PINK_PEARL = registerBlock("pink_pearl",
            new FlowerBlock(StatusEffects.GLOWING, 8, FabricBlockSettings.copy(Blocks.POPPY)),
            ModItemGroups.TFW);

    public static final Block BLUE_PEARL = registerBlock("blue_pearl",
            new FlowerBlock(StatusEffects.GLOWING, 8, FabricBlockSettings.copy(Blocks.POPPY)),
            ModItemGroups.TFW);

    public static final Block BLUE_MUMBADE = registerBlock("blue_mumbade",
            new FlowerBlock(StatusEffects.GLOWING, 8, FabricBlockSettings.copy(Blocks.POPPY)),
            ModItemGroups.TFW);

    public static final Block PURPLE_MUMBADE = registerBlock("purple_mumbade",
            new FlowerBlock(StatusEffects.GLOWING, 8, FabricBlockSettings.copy(Blocks.POPPY)),
            ModItemGroups.TFW);

    public static final Block GOLD_MUMBADE = registerBlock("gold_mumbade",
            new FlowerBlock(StatusEffects.GLOWING, 8, FabricBlockSettings.copy(Blocks.POPPY)),
            ModItemGroups.TFW);


    public static final Block POTTED_BLUE_MUMBADE = registerBlockWithoutBlockItem("potted_blue_mumbade",
            new FlowerPotBlock(ModBlocks.BLUE_MUMBADE, FabricBlockSettings.copy(Blocks.POTTED_POPPY)));

    public static final Block POTTED_GOLD_MUMBADE = registerBlockWithoutBlockItem("potted_gold_mumbade",
            new FlowerPotBlock(ModBlocks.GOLD_MUMBADE, FabricBlockSettings.copy(Blocks.POTTED_POPPY)));

    public static final Block POTTED_PURPLE_MUMBADE = registerBlockWithoutBlockItem("potted_purple_mumbade",
            new FlowerPotBlock(ModBlocks.PURPLE_MUMBADE, FabricBlockSettings.copy(Blocks.POTTED_POPPY)));

    public static final Block POTTED_PINK_PEARL = registerBlockWithoutBlockItem("potted_pink_pearl",
            new FlowerPotBlock(ModBlocks.PINK_PEARL, FabricBlockSettings.copy(Blocks.POTTED_POPPY)));

    public static final Block POTTED_BLUE_PEARL = registerBlockWithoutBlockItem("potted_blue_pearl",
            new FlowerPotBlock(ModBlocks.BLUE_PEARL, FabricBlockSettings.copy(Blocks.POTTED_POPPY)));

    public static final Block UNLIT_LANTERN = registerBlock("unlit_lantern",
            new LanternBlock(FabricBlockSettings.of(Material.METAL)
    .sounds(BlockSoundGroup.LANTERN).strength(3.5f,3.5f).nonOpaque()), ModItemGroups.TFW);

    public static final Block GLIMMERLACE = registerBlock("glimmerlace",
            new VineBlock(FabricBlockSettings.copy(Blocks.VINE).luminance((state) -> 5)), ModItemGroups.TFW);


    public static final Block PURPLE_CARROT_CROP = registerBlockWithoutBlockItem("purple_carrot",
            new PurpleCarrotsBlock(FabricBlockSettings.copy(Blocks.CARROTS)));

//mumbade crop
    public static final Block BLUE_MUMBADE_CROP = registerBlockWithoutBlockItem("blue_mumbade_crop",
            new ModBlueMumbadeBlock(FabricBlockSettings.copy(Blocks.BEETROOTS)));

    public static final Block PURPLE_MUMBADE_CROP = registerBlockWithoutBlockItem("purple_mumbade_crop",
            new ModBlueMumbadeBlock(FabricBlockSettings.copy(Blocks.BEETROOTS)));

    public static final Block GOLD_MUMBADE_CROP = registerBlockWithoutBlockItem("gold_mumbade_crop",
            new ModBlueMumbadeBlock(FabricBlockSettings.copy(Blocks.BEETROOTS)));

//pearl flower

    public static final Block PINK_PEARL_CROP = registerBlockWithoutBlockItem("pink_pearl_crop",
            new PinkPearlBlock(FabricBlockSettings.copy(Blocks.BEETROOTS)));

    public static final Block BLUE_PEARL_CROP = registerBlockWithoutBlockItem("blue_pearl_crop",
            new BluePearlBlock(FabricBlockSettings.copy(Blocks.BEETROOTS)));








    private static Block registerBlockWithoutBlockItem(String name, Block block){
        return Registry.register(Registry.BLOCK, new Identifier(TFWmod.MOD_ID, name), block);
    }


    private static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(TFWmod.MOD_ID, name), block);
    }


    private static Item registerBlockItem(String name, Block Block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(TFWmod.MOD_ID, name),
                new BlockItem(Block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks(){
        System.out.println("Registering Mod Blocks for " + TFWmod.MOD_ID);

    }
}
