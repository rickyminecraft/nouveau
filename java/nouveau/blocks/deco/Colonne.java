package nouveau.blocks.deco;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Colonne extends HorizontalBlock implements IWaterLoggable
{
	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 2);
	protected static final VoxelShape COLONNE_SELECTION_AABB = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	private static final VoxelShape COLONNE_N01_AABB = Block.box(4.0D, 0.0D, 0.0D, 12.0D, 16.0D, 8.0D);//M
	private static final VoxelShape COLONNE_N02_AABB = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 8.0D);//G
	private static final VoxelShape COLONNE_N03_AABB = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);//D
	
	private static final VoxelShape COLONNE_S01_AABB = Block.box(4.0D, 0.0D, 8.0D, 12.0D, 16.0D, 16.0D);//M
	private static final VoxelShape COLONNE_S02_AABB = Block.box(8.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);//G
	private static final VoxelShape COLONNE_S03_AABB = Block.box(0.0D, 0.0D, 8.0D, 8.0D, 16.0D, 16.0D);//D
	
	private static final VoxelShape COLONNE_E01_AABB = Block.box(8.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);//M
	private static final VoxelShape COLONNE_E02_AABB = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);//G
	private static final VoxelShape COLONNE_E03_AABB = Block.box(8.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);//D
	
	private static final VoxelShape COLONNE_W01_AABB = Block.box(0.0D, 0.0D, 4.0D, 8.0D, 16.0D, 12.0D);//M
	private static final VoxelShape COLONNE_W02_AABB = Block.box(0.0D, 0.0D, 8.0D, 8.0D, 16.0D, 16.0D);//G
	private static final VoxelShape COLONNE_W03_AABB = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 8.0D);//D
	
	public Colonne(Properties builder) 
	{
		super(builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, 0).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		Direction direction = state.getValue(FACING);
		int type = state.getValue(TYPE);
		if (direction == Direction.SOUTH)
		{
			switch (type)
			{
			case 0:
				return COLONNE_S01_AABB;
			case 1:
				return COLONNE_S02_AABB;
			case 2:
				return COLONNE_S03_AABB;
			default:
				break;
			}
			return COLONNE_S01_AABB;
		}
		if (direction == Direction.NORTH)
		{
			switch (type)
			{
			case 0:
				return COLONNE_N01_AABB;
			case 1:
				return COLONNE_N02_AABB;
			case 2:
				return COLONNE_N03_AABB;
			default:
				break;
			}
			return COLONNE_N01_AABB;
		}
		if (direction == Direction.EAST)
		{
			switch (type)
			{
			case 0:
				return COLONNE_E01_AABB;
			case 1:
				return COLONNE_E02_AABB;
			case 2:
				return COLONNE_E03_AABB;
			default:
				break;
			}
			return COLONNE_E01_AABB;
		}
		if (direction == Direction.WEST)
		{
			switch (type)
			{
			case 0:
				return COLONNE_W01_AABB;
			case 1:
				return COLONNE_W02_AABB;
			case 2:
				return COLONNE_W03_AABB;
			default:
				break;
			}
			return COLONNE_W01_AABB;
		}
		return COLONNE_SELECTION_AABB;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		Direction direction = state.getValue(FACING);
		int type = state.getValue(TYPE);
		if (direction == Direction.SOUTH)
		{
			switch (type)
			{
			case 0:
				return COLONNE_S01_AABB;
			case 1:
				return COLONNE_S02_AABB;
			case 2:
				return COLONNE_S03_AABB;
			default:
				break;
			}
			return COLONNE_S01_AABB;
		}
		if (direction == Direction.NORTH)
		{
			switch (type)
			{
			case 0:
				return COLONNE_N01_AABB;
			case 1:
				return COLONNE_N02_AABB;
			case 2:
				return COLONNE_N03_AABB;
			default:
				break;
			}
			return COLONNE_N01_AABB;
		}
		if (direction == Direction.EAST)
		{
			switch (type)
			{
			case 0:
				return COLONNE_E01_AABB;
			case 1:
				return COLONNE_E02_AABB;
			case 2:
				return COLONNE_E03_AABB;
			default:
				break;
			}
			return COLONNE_E01_AABB;
		}
		if (direction == Direction.WEST)
		{
			switch (type)
			{
			case 0:
				return COLONNE_W01_AABB;
			case 1:
				return COLONNE_W02_AABB;
			case 2:
				return COLONNE_W03_AABB;
			default:
				break;
			}
			return COLONNE_W01_AABB;
		}
		return COLONNE_SELECTION_AABB;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(TYPE, FACING, WATERLOGGED);
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
