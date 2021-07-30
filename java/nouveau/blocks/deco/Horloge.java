package nouveau.blocks.deco;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class Horloge extends HorizontalBlock implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	private static final VoxelShape HORLOGE_SELECTION_AABB = Block.box(5.0D, 10.0D, 0.0D, 11.0D, 16.0D, 6.0D);
	private static final VoxelShape HORLOGE_01_AABB = Block.box(5.0D, 10.0D, 10.0D, 11.0D, 16.0D, 16.0D);
	private static final VoxelShape HORLOGE_11_AABB = Block.box(7.5D, 1.0D, 12.5D, 8.5D, 10.0D, 13.5D);
	private static final VoxelShape HORLOGE_02_AABB = Block.box(5.0D, 10.0D, 0.0D, 11.0D, 16.0D, 6.0D);
	private static final VoxelShape HORLOGE_12_AABB = Block.box(7.5D, 1.0D, 2.5D, 8.5D, 10.0D, 3.5D);
	private static final VoxelShape HORLOGE_03_AABB = Block.box(10.0D, 10.0D, 5.0D, 16.0D, 16.0D, 11.0D);
	private static final VoxelShape HORLOGE_13_AABB = Block.box(12.5D, 1.0D, 7.5D, 13.5D, 10.0D, 8.5D);
	private static final VoxelShape HORLOGE_04_AABB = Block.box(0.0D, 10.0D, 5.0D, 6.0D, 16.0D, 11.0D);
	private static final VoxelShape HORLOGE_14_AABB = Block.box(2.5D, 1.0D, 7.5D, 3.5D, 10.0D, 8.5D);

	public Horloge(Properties builder)
	{
		super(builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH)
		{
			VoxelShape voxelshape = VoxelShapes.or(HORLOGE_01_AABB, HORLOGE_11_AABB);
			return voxelshape;
		}
		if (direction == Direction.NORTH)
		{
			VoxelShape voxelshape = VoxelShapes.or(HORLOGE_02_AABB, HORLOGE_12_AABB);
			return voxelshape;
		}
		if (direction == Direction.EAST)
		{
			VoxelShape voxelshape = VoxelShapes.or(HORLOGE_03_AABB, HORLOGE_13_AABB);
			return voxelshape;
		}
		if (direction == Direction.WEST)
		{
			VoxelShape voxelshape = VoxelShapes.or(HORLOGE_04_AABB, HORLOGE_14_AABB);
			return voxelshape;
		}
		return HORLOGE_SELECTION_AABB;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH)
		{
			VoxelShape voxelshape = VoxelShapes.or(HORLOGE_01_AABB, HORLOGE_11_AABB);
			return voxelshape;
		}
		if (direction == Direction.NORTH)
		{
			VoxelShape voxelshape = VoxelShapes.or(HORLOGE_02_AABB, HORLOGE_12_AABB);
			return voxelshape;
		}
		if (direction == Direction.EAST)
		{
			VoxelShape voxelshape = VoxelShapes.or(HORLOGE_03_AABB, HORLOGE_13_AABB);
			return voxelshape;
		}
		if (direction == Direction.WEST)
		{
			VoxelShape voxelshape = VoxelShapes.or(HORLOGE_04_AABB, HORLOGE_14_AABB);
			return voxelshape;
		}
		return HORLOGE_SELECTION_AABB;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
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
