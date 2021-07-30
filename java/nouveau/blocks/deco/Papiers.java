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
import net.minecraft.world.IBlockReader;

public class Papiers extends HorizontalBlock implements IWaterLoggable
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
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
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
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
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
