package net.isaacj.tfw.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.isaacj.tfw.TFWmod;
import net.isaacj.tfw.block.ModBlocks;
import net.isaacj.tfw.item.custom.ModAxeItem;
import net.isaacj.tfw.item.custom.ModHoeItem;
import net.isaacj.tfw.item.custom.ModPickaxeItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item SYPH_NUT = registerItem("syph_nut",
            new Item(new FabricItemSettings().food(ModFoodComponent.SYPH_NUT).group(ModItemGroups.TFW)));


    public static final Item BLUE_MUMBADE_SEEDS = registerItem("blue_mumbade_seeds",
            new AliasedBlockItem(ModBlocks.BLUE_MUMBADE_CROP,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item PURPLE_MUMBADE_SEEDS = registerItem("purple_mumbade_seeds",
            new AliasedBlockItem(ModBlocks.PURPLE_MUMBADE_CROP,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item GOLD_MUMBADE_SEEDS = registerItem("gold_mumbade_seeds",
            new AliasedBlockItem(ModBlocks.GOLD_MUMBADE_CROP,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item RAW_MICENTEAN = registerItem("raw_micentean",
            new Item(new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item MICENTEAN_INGOT = registerItem("micentean_ingot",
            new Item(new FabricItemSettings().group(ModItemGroups.TFW)));



    public static final Item MICENTEAN_PICKAXE = registerItem("micentean_pickaxe",
            new ModPickaxeItem(ModToolMaterial.MICENTEAN, 2, -2.8f,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item MICENTEAN_AXE = registerItem("micentean_axe",
            new ModAxeItem(ModToolMaterial.MICENTEAN, 6, -3.0f,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item MICENTEAN_HOE = registerItem("micentean_hoe",
            new ModHoeItem(ModToolMaterial.MICENTEAN, -2, -1.0f,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item MICENTEAN_SHOVEL = registerItem("micentean_shovel",
            new ShovelItem(ModToolMaterial.MICENTEAN, 2, -3.0f,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item MICENTEAN_SWORD = registerItem("micentean_sword",
            new SwordItem(ModToolMaterial.MICENTEAN, 3, -2.4f,
                    new FabricItemSettings().group(ModItemGroups.TFW)));


    public static final Item MICENTEAN_HELMET = registerItem("micentean_helmet",
            new ArmorItem(ModArmorMaterial.MICENTEAN, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item MICENTEAN_CHESTPLATE = registerItem("micentean_chestplate",
            new ArmorItem(ModArmorMaterial.MICENTEAN, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item MICENTEAN_LEGGINGS = registerItem("micentean_leggings",
            new ArmorItem(ModArmorMaterial.MICENTEAN, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item MICENTEAN_BOOTS = registerItem("micentean_boots",
            new ArmorItem(ModArmorMaterial.MICENTEAN, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroups.TFW)));


    public static final Item HEAVY_LEATHER_HELMET = registerItem("heavy_leather_helmet",
            new ArmorItem(ModArmorMaterial.HEAVY_LEATHER, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item HEAVY_LEATHER_CHESTPLATE = registerItem("heavy_leather_chestplate",
            new ArmorItem(ModArmorMaterial.HEAVY_LEATHER, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item HEAVY_LEATHER_LEGGINGS = registerItem("heavy_leather_leggings",
            new ArmorItem(ModArmorMaterial.HEAVY_LEATHER, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroups.TFW)));

    public static final Item HEAVY_LEATHER_BOOTS = registerItem("heavy_leather_boots",
            new ArmorItem(ModArmorMaterial.HEAVY_LEATHER, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroups.TFW)));







    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(TFWmod.MOD_ID, name), item);

    }


    public static void registerModItems(){
        System.out.println("Registering Mod Items for" + TFWmod.MOD_ID);

    }
}
