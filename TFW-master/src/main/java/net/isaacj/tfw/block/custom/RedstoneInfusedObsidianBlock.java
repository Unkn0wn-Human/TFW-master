package net.isaacj.tfw.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class RedstoneInfusedObsidianBlock extends Block {

    public static final BooleanProperty ON = BooleanProperty.of("on");



    public RedstoneInfusedObsidianBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(ON, false ));
    }
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {

        if (!world.isClient) {
            boolean bl = state.get(ON);
            if (bl != world.isReceivingRedstonePower(pos)) {
                if (bl) {
                    world.createAndScheduleBlockTick(pos, this, 4);
                }else {
                    world.setBlockState(pos, state.cycle(ON), 2);
                }
            }

        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(ON) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(ON), 2);
        }

    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(ON, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }





    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ON);
    }
}
