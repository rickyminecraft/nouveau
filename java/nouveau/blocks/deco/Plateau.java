package nouveau.blocks.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.material.Fluids;

public class Plateau extends DirectionalBlock implements SimpleWaterloggedBlock
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape PLATEAU_BAS = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape PLATEAU_HAUT = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	private static final VoxelShape PLATEAU_EST = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
	private static final VoxelShape PLATEAU_OUEST = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape PLATEAU_SUD = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
	private static final VoxelShape PLATEAU_NORD = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);

	public Plateau(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) 
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.UP)
		{
			return PLATEAU_HAUT;
		}
		if (direction == Direction.DOWN)
		{
			return PLATEAU_BAS;
		}
		if (direction == Direction.SOUTH)
		{
			return PLATEAU_SUD;
		}
		if (direction == Direction.NORTH)
		{
			return PLATEAU_NORD;
		}
		if (direction == Direction.WEST)
		{
			return PLATEAU_OUEST;
		}
		if (direction == Direction.EAST)
		{
			return PLATEAU_EST;
		}
		return PLATEAU_HAUT;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.UP)
		{
			return PLATEAU_HAUT;
		}
		if (direction == Direction.DOWN)
		{
			return PLATEAU_BAS;
		}
		if (direction == Direction.SOUTH)
		{
			return PLATEAU_SUD;
		}
		if (direction == Direction.NORTH)
		{
			return PLATEAU_NORD;
		}
		if (direction == Direction.WEST)
		{
			return PLATEAU_OUEST;
		}
		if (direction == Direction.EAST)
		{
			return PLATEAU_EST;
		}
		return PLATEAU_HAUT;
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
