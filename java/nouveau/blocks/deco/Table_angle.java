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
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class Table_angle extends HorizontalBlock implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape TABLE_BOUNDING_AABB = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	public Table_angle(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return TABLE_BOUNDING_AABB;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return TABLE_BOUNDING_AABB;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
	}
	
	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever possible. Implementing/overriding is
	 * fine.
	 */
	@Deprecated
	@Override
	public BlockState rotate(BlockState state, Rotation rot) 
	{
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}
	
	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever possible. Implementing/overriding is fine.
	 */
	@Deprecated
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) 
	{
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
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
