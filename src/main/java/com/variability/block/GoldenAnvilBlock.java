package com.variability.block;

import com.variability.tileentity.GoldenAnvilTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.minecraft.state.properties.Half.BOTTOM;
import static net.minecraft.state.properties.Half.TOP;

public class GoldenAnvilBlock extends HorizontalBlock implements IWaterLoggable {

    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    public static final BooleanProperty ACTIVATED = BlockStateProperties.ENABLED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public GoldenAnvilBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, BOTTOM).setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(ACTIVATED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, ACTIVATED, WATERLOGGED);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GoldenAnvilTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public boolean canSurvive(BlockState state, @Nonnull IWorldReader world, @Nonnull BlockPos pos) {
        if(state.getValue(HALF) == BOTTOM){
            return true;
        }
        BlockState stateBelow = world.getBlockState(pos.below());
        return stateBelow.getBlock().is(this) && stateBelow.getValue(FACING) == state.getValue(FACING);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getClickedPos();
        if (blockpos.getY() < context.getLevel().getMaxBuildHeight() - 1 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(HALF, BOTTOM).setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
        } else {
            return null;
        }
    }

    @Override
    @Nonnull
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity entity, @Nonnull ItemStack stack) {
        world.setBlock(pos.above(), state.setValue(HALF, TOP).setValue(WATERLOGGED, world.getFluidState(pos.above()).getType() == Fluids.WATER), 3);
    }

    @Override
    @Nonnull
    public PushReaction getPistonPushReaction(@Nonnull BlockState state) {
        return PushReaction.BLOCK;
    }

    @Override
    @Nonnull
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    @Nonnull
    public BlockState mirror(@Nonnull BlockState state, @Nonnull Mirror mirror) {
        return mirror == Mirror.NONE ? state : this.rotate(state, Rotation.CLOCKWISE_180);
    }
}
