package nouveau.blocks.deco;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Lumieres_mural extends DirectionalBlock implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 2);
	private static final VoxelShape PLATEAU_BAS = Block.box(6.0D, 15.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private static final VoxelShape PLATEAU_HAUT = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 1.0D, 10.0D);
	private static final VoxelShape PLATEAU_EST = Block.box(0.0D, 6.0D, 6.0D, 1.0D, 10.0D, 10.0D);
	private static final VoxelShape PLATEAU_OUEST = Block.box(15.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
	private static final VoxelShape PLATEAU_SUD = Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 1.0D);
	private static final VoxelShape PLATEAU_NORD = Block.box(6.0D, 6.0D, 15.0D, 10.0D, 10.0D, 16.0D);

	private static final VoxelShape PLATEAU_BAS_1 = Block.box(7.0D, 15.0D, 0.0D, 9.0D, 16.0D, 16.0D);
	private static final VoxelShape PLATEAU_HAUT_1 = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 1.0D, 16.0D);
	private static final VoxelShape PLATEAU_EST_1 = Block.box(0.0D, 0.0D, 7.0D, 1.0D, 16.0D, 9.0D);
	private static final VoxelShape PLATEAU_OUEST_1 = Block.box(15.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
	private static final VoxelShape PLATEAU_SUD_1 = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 1.0D);
	private static final VoxelShape PLATEAU_NORD_1 = Block.box(7.0D, 0.0D, 15.0D, 9.0D, 16.0D, 16.0D);

	private static final VoxelShape PLATEAU_BAS_2 = Block.box(0.0D, 15.0D, 7.0D, 16.0D, 16.0D, 9.0D);
	private static final VoxelShape PLATEAU_HAUT_2 = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 1.0D, 9.0D);
	private static final VoxelShape PLATEAU_EST_2 = Block.box(0.0D, 7.0D, 0.0D, 1.0D, 9.0D, 16.0D);
	private static final VoxelShape PLATEAU_OUEST_2 = Block.box(15.0D, 7.0D, 0.0D, 16.0D, 9.0D, 16.0D);
	private static final VoxelShape PLATEAU_SUD_2 = Block.box(0.0D, 7.0D, 0.0D, 16.0D, 9.0D, 1.0D);
	private static final VoxelShape PLATEAU_NORD_2 = Block.box(0.0D, 7.0D, 15.0D, 16.0D, 9.0D, 16.0D);

	public Lumieres_mural(Properties builder)
	{
		super(builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(TYPE, 0).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		Direction direction = state.getValue(FACING);
		int Type = state.getValue(TYPE);
		switch (Type)
		{
		case 0:
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
		case 1:
			if (direction == Direction.UP)
			{
				return PLATEAU_HAUT_1;
			}
			if (direction == Direction.DOWN)
			{
				return PLATEAU_BAS_1;
			}
			if (direction == Direction.SOUTH)
			{
				return PLATEAU_SUD_1;
			}
			if (direction == Direction.NORTH)
			{
				return PLATEAU_NORD_1;
			}
			if (direction == Direction.WEST)
			{
				return PLATEAU_OUEST_1;
			}
			if (direction == Direction.EAST)
			{
				return PLATEAU_EST_1;
			}
		case 2:
			if (direction == Direction.UP)
			{
				return PLATEAU_HAUT_2;
			}
			if (direction == Direction.DOWN)
			{
				return PLATEAU_BAS_2;
			}
			if (direction == Direction.SOUTH)
			{
				return PLATEAU_SUD_2;
			}
			if (direction == Direction.NORTH)
			{
				return PLATEAU_NORD_2;
			}
			if (direction == Direction.WEST)
			{
				return PLATEAU_OUEST_2;
			}
			if (direction == Direction.EAST)
			{
				return PLATEAU_EST_2;
			}
		}
		return PLATEAU_HAUT;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		Direction direction = state.getValue(FACING);
		int Type = state.getValue(TYPE);
		switch (Type)
		{
		case 0:
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
		case 1:
			if (direction == Direction.UP)
			{
				return PLATEAU_HAUT_1;
			}
			if (direction == Direction.DOWN)
			{
				return PLATEAU_BAS_1;
			}
			if (direction == Direction.SOUTH)
			{
				return PLATEAU_SUD_1;
			}
			if (direction == Direction.NORTH)
			{
				return PLATEAU_NORD_1;
			}
			if (direction == Direction.WEST)
			{
				return PLATEAU_OUEST_1;
			}
			if (direction == Direction.EAST)
			{
				return PLATEAU_EST_1;
			}
		case 2:
			if (direction == Direction.UP)
			{
				return PLATEAU_HAUT_2;
			}
			if (direction == Direction.DOWN)
			{
				return PLATEAU_BAS_2;
			}
			if (direction == Direction.SOUTH)
			{
				return PLATEAU_SUD_2;
			}
			if (direction == Direction.NORTH)
			{
				return PLATEAU_NORD_2;
			}
			if (direction == Direction.WEST)
			{
				return PLATEAU_OUEST_2;
			}
			if (direction == Direction.EAST)
			{
				return PLATEAU_EST_2;
			}
		}
		return PLATEAU_HAUT;
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
		builder.add(FACING, TYPE, WATERLOGGED);
	}

	@Override
	@Deprecated
	public FluidState getFluidState(BlockState state) 
	{
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	//onBlockActivated
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult Ray)
	{
		if (worldIn.isClientSide)
		{
			return ActionResultType.SUCCESS;
		}
		else
		{
			ItemStack is = player.getMainHandItem();
			Item it = is.getItem();
			String nom = it.getDescriptionId();
			if ("item.minecraft.stick".equalsIgnoreCase(nom) && player.isCreative())
			{
				int itype = state.getValue(TYPE).intValue();
				itype++;
				if (itype == 3)
				{
					itype = 0;
				}
				worldIn.setBlockAndUpdate(pos, state.setValue(TYPE,itype));
			}
		}
		return ActionResultType.SUCCESS;
	}
}
