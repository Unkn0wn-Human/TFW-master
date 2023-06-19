package net.isaacj.tfw.block.entity;


import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.isaacj.tfw.TFWmod;
import net.isaacj.tfw.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntity {
    public static BlockEntityType<NetheriteBlasterEntity> NETHERITE_BLASTER;

    public static void registerAllEntities() {
        NETHERITE_BLASTER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(TFWmod.MOD_ID, "netherite_blaster"),
                FabricBlockEntityTypeBuilder.create(NetheriteBlasterEntity::new,
                        ModBlocks.NETHERITE_BLASTER).build(null));
    }
}
