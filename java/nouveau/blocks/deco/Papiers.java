package nouveau.blocks.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.material.Fluids;

public class Papiers extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape PAPIER_SELECTION_AABB = Block.box(5.0D, 0.0D, 3.0D, 11.0D, 2.0D, 11.0D);
	private static final VoxelShape PAPIER_01_AABB = Block.box(5.0D, 0.0D, 3.0D, 11.0D, 2.0D, 11.0D);
	private static final VoxelShape PAPIER_02_AABB = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 2.0D, 13.0D);
	private static final VoxelShape PAPIER_03_AABB = Block.box(3.0D, 0.0D, 5.0D, 11.0D, 2.0D, 11.0D);
	private static final VoxelShape PAPIER_04_AABB = Block.box(5.0D, 0.0D, 5.0D, 13.0D, 2.0D, 11.0D);

	public Papiers(Properties builder) 
	{
		super(builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) 
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH)
		{
			return PAPIER_01_AABB;
		}
		if (direction == Direction.NORTH)
		{
			return PAPIER_02_AABB;
		}
		if (direction == Direction.EAST)
		{
			return PAPIER_03_AABB;
		}
		if (direction == Direction.WEST)
		{
			return PAPIER_04_AABB;
		}
		return PAPIER_SELECTION_AABB;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH)
		{
			return PAPIER_01_AABB;
		}
		if (direction == Direction.NORTH)
		{
			return PAPIER_02_AABB;
		}
		if (direction == Direction.EAST)
		{
			return PAPIER_03_AABB;
		}
		if (direction == Direction.WEST)
		{
			return PAPIER_04_AABB;
		}
		return PAPIER_SELECTION_AABB;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) 
	{
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) 
	{
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	@Deprecated
	public FluidState getFluidState(BlockState state) 
	{
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
}
