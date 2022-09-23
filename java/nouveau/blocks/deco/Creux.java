package nouveau.blocks.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
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

public class Creux extends DirectionalBlock implements SimpleWaterloggedBlock
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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) 
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH || direction == Direction.NORTH)
		{
			VoxelShape voxelshape = Shapes.or(CREUX_A_AABB, CREUX_B_AABB);
			VoxelShape voxelshape2 = Shapes.or(CREUX_C_AABB, CREUX_D_AABB);
			VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		if (direction == Direction.UP || direction == Direction.DOWN)
		{
			VoxelShape voxelshape = Shapes.or(CREUX_C_AABB, CREUX_D_AABB);
			VoxelShape voxelshape2 = Shapes.or(CREUX_E_AABB, CREUX_F_AABB);
			VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		if (direction == Direction.EAST || direction == Direction.WEST)
		{
			VoxelShape voxelshape = Shapes.or(CREUX_A_AABB, CREUX_B_AABB);
			VoxelShape voxelshape2 = Shapes.or(CREUX_E_AABB, CREUX_F_AABB);
			VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		return Shapes.block();
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH || direction == Direction.NORTH)
		{
			VoxelShape voxelshape = Shapes.or(CREUX_A_AABB, CREUX_B_AABB);
			VoxelShape voxelshape2 = Shapes.or(CREUX_C_AABB, CREUX_D_AABB);
			VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		if (direction == Direction.UP || direction == Direction.DOWN)
		{
			VoxelShape voxelshape = Shapes.or(CREUX_C_AABB, CREUX_D_AABB);
			VoxelShape voxelshape2 = Shapes.or(CREUX_E_AABB, CREUX_F_AABB);
			VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		if (direction == Direction.EAST || direction == Direction.WEST)
		{
			VoxelShape voxelshape = Shapes.or(CREUX_A_AABB, CREUX_B_AABB);
			VoxelShape voxelshape2 = Shapes.or(CREUX_E_AABB, CREUX_F_AABB);
			VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
			return voxelshape3;
		}
		return Shapes.block();
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
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) 
	{
		if (stateIn.getValue(WATERLOGGED)) 
		{
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) 
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
