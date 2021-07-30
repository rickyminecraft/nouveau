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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.material.Fluids;

public class Banc extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock
{
	protected static final VoxelShape BANC_SELECTION_AABB = Block.box(0.0D, 0.1D, 0.0D, 16.0D, 8.0D, 16.0D);
	private static final VoxelShape BANC_01_AABB = Block.box(5.0D, 0.0D, 6.0D, 6.0D, 7.0D, 11.0D);
	private static final VoxelShape BANC_11_AABB = Block.box(2.0D, 7.0D, 4.0D, 16.0D, 8.0D, 12.0D);
	private static final VoxelShape BANC_02_AABB = Block.box(10.0D, 0.1D, 6.0D, 11.0D, 7.0D, 11.0D);
	private static final VoxelShape BANC_12_AABB = Block.box(0.0D, 7.0D, 4.0D, 14.0D, 8.0D, 12.0D);
	private static final VoxelShape BANC_03_AABB = Block.box(5.0D, 0.1D, 10.0D, 10.0D, 7.0D, 11.0D);
	private static final VoxelShape BANC_13_AABB = Block.box(4.0D, 7.0D, 0.0D, 12.0D, 8.0D, 14.0D);
	private static final VoxelShape BANC_04_AABB = Block.box(5.0D, 0.1D, 5.0D, 10.0D, 7.0D, 6.0D);
	private static final VoxelShape BANC_14_AABB = Block.box(4.0D, 7.0D, 2.0D, 12.0D, 8.0D, 16.0D);
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public Banc(Properties builder)
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
			VoxelShape voxelshape = Shapes.or(BANC_01_AABB, BANC_11_AABB);
			return voxelshape;
		}
		if (direction == Direction.NORTH)
		{
			VoxelShape voxelshape = Shapes.or(BANC_02_AABB, BANC_12_AABB);
			return voxelshape;
		}
		if (direction == Direction.EAST)
		{
			VoxelShape voxelshape = Shapes.or(BANC_03_AABB, BANC_13_AABB);
			return voxelshape;
		}
		if (direction == Direction.WEST)
		{
			VoxelShape voxelshape = Shapes.or(BANC_04_AABB, BANC_14_AABB);
			return voxelshape;
		}
		return BANC_SELECTION_AABB;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH)
		{
			VoxelShape voxelshape = Shapes.or(BANC_01_AABB, BANC_11_AABB);
			return voxelshape;
		}
		if (direction == Direction.NORTH)
		{
			VoxelShape voxelshape = Shapes.or(BANC_02_AABB, BANC_12_AABB);
			return voxelshape;
		}
		if (direction == Direction.EAST)
		{
			VoxelShape voxelshape = Shapes.or(BANC_03_AABB, BANC_13_AABB);
			return voxelshape;
		}
		if (direction == Direction.WEST)
		{
			VoxelShape voxelshape = Shapes.or(BANC_04_AABB, BANC_14_AABB);
			return voxelshape;
		}
		return BANC_SELECTION_AABB;
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
