package nouveau.blocks.deco;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class Chaine extends RotatedPillarBlock implements IWaterLoggable
{
	private static final VoxelShape AABB_SMALL_X = Block.box(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
	private static final VoxelShape AABB_SMALL_Y = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private static final VoxelShape AABB_SMALL_Z = Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public Chaine(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		Axis Axe = state.getValue(AXIS);
		if (Axe == Direction.Axis.X)
		{

			return AABB_SMALL_X;
		}
		if (Axe == Direction.Axis.Y)
		{

			return AABB_SMALL_Y;
		}
		if (Axe == Direction.Axis.Z)
		{

			return AABB_SMALL_Z;
		}
		return VoxelShapes.block();
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		Axis Axe = state.getValue(AXIS);
		if (Axe == Direction.Axis.X)
		{

			return AABB_SMALL_X;
		}
		if (Axe == Direction.Axis.Y)
		{

			return AABB_SMALL_Y;
		}
		if (Axe == Direction.Axis.Z)
		{

			return AABB_SMALL_Z;
		}
		return VoxelShapes.block();
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(WATERLOGGED, AXIS);
	}

	@Override
	@Deprecated
	public FluidState getFluidState(BlockState state) 
	{
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

}
