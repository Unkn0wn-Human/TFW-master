package net.isaacj.tfw.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.isaacj.tfw.TFWmod;
import net.isaacj.tfw.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup TFW = FabricItemGroupBuilder.build(new Identifier(TFWmod.MOD_ID, "tfw"),
            () -> new ItemStack(ModBlocks.REDSTONE_INFUSED_OBSIDIAN));
}
