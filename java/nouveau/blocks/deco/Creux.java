package nouveau.blocks.deco;

import net.minecraft.block.Block;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class Creux extends DirectionalBlock implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape CREUX_A_AABB = Block.box(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);//haut quand horizontal
	private static final VoxelShape CREUX_B_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);//bas quand horizontal
	private static final VoxelShape CREUX_C_AABB = Block.box(0.0D, 3.0D, 0.0D, 3.0D, 16.0D, 16.0D);//gauche nord-sud
	private static final VoxelShape CREUX_D_AABB = Block.box(13.0D, 3.0D, 0.0D, 16.0D, 16.0D, 16.0D);//droit nord-sud
	private static final VoxelShape CREUX_E_AABB = Block.box(0.0D, 3.0D, 0.0D, 16.0D, 16.0D, 3.0D);//gauche est-ouest
	private static final VoxelShape CREUX_F_AABB = Block.box(0.0D, 3.0D, 13.0D, 16.0D, 16.0D, 16.0D);//droit est-ouest

	public Creux(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH || direction == Direction.NORTH)
		{
			VoxelShape voxelshape = VoxelShapes.or(CREUX_A_AABB, CREUX_B_AABB);
			VoxelShape voxelshape2 = VoxelShapes.or(CREUX_C_AABB, CREUX_D_AABB);
			VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		if (direction == Direction.UP || direction == Direction.DOWN)
		{
			VoxelShape voxelshape = VoxelShapes.or(CREUX_C_AABB, CREUX_D_AABB);
			VoxelShape voxelshape2 = VoxelShapes.or(CREUX_E_AABB, CREUX_F_AABB);
			VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		if (direction == Direction.EAST || direction == Direction.WEST)
		{
			VoxelShape voxelshape = VoxelShapes.or(CREUX_A_AABB, CREUX_B_AABB);
			VoxelShape voxelshape2 = VoxelShapes.or(CREUX_E_AABB, CREUX_F_AABB);
			VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		return VoxelShapes.block();
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH || direction == Direction.NORTH)
		{
			VoxelShape voxelshape = VoxelShapes.or(CREUX_A_AABB, CREUX_B_AABB);
			VoxelShape voxelshape2 = VoxelShapes.or(CREUX_C_AABB, CREUX_D_AABB);
			VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		if (direction == Direction.UP || direction == Direction.DOWN)
		{
			VoxelShape voxelshape = VoxelShapes.or(CREUX_C_AABB, CREUX_D_AABB);
			VoxelShape voxelshape2 = VoxelShapes.or(CREUX_E_AABB, CREUX_F_AABB);
			VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		if (direction == Direction.EAST || direction == Direction.WEST)
		{
			VoxelShape voxelshape = VoxelShapes.or(CREUX_A_AABB, CREUX_B_AABB);
			VoxelShape voxelshape2 = VoxelShapes.or(CREUX_E_AABB, CREUX_F_AABB);
			VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		return VoxelShapes.block();
	}

	/**
	 * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
	 * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
	 * returns its solidified counterpart.
	 * Note that this method should ideally consider only the specific face passed in.
	 *  
	 * @param facingState The state that is currently at the position offset of the provided face to the stateIn at
	 * currentPos
	 */
	@Override
	@Deprecated
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) 
	{
		if (stateIn.getValue(WATERLOGGED)) 
		{
			worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
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
