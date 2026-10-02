package com.guia31.painelled;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PainelLedBlock extends Block {
	// FACING = lado para onde o painel aponta (a face em que voce clicou)
	public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

	private static final VoxelShape UP = Block.box(0, 0, 0, 16, 2, 16);
	private static final VoxelShape DOWN = Block.box(0, 14, 0, 16, 16, 16);
	private static final VoxelShape NORTH = Block.box(0, 0, 14, 16, 16, 16);
	private static final VoxelShape SOUTH = Block.box(0, 0, 0, 16, 16, 2);
	private static final VoxelShape EAST = Block.box(0, 0, 0, 2, 16, 16);
	private static final VoxelShape WEST = Block.box(14, 0, 0, 16, 16, 16);

	public PainelLedBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getClickedFace());
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case UP -> UP;
			case DOWN -> DOWN;
			case NORTH -> NORTH;
			case SOUTH -> SOUTH;
			case EAST -> EAST;
			case WEST -> WEST;
		};
	}
}
