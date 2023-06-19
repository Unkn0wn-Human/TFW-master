package net.isaacj.tfw.world.dimension;

import net.isaacj.tfw.block.ModBlocks;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModPortals {

    public static void registerPortals() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.REDSTONE_INFUSED_OBSIDIAN)
                .destDimID(ModDimension.FROZEN_KEY.getValue())
                .tintColor(122,1,31)
                .registerPortal();

    }
}
