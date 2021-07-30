package nouveau.blocks.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.material.Fluids;

public class Colonne extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock
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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) 
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
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
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
	public BlockState getStateForPlacement(BlockPlaceContext context) 
	{
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) 
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
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult Ray)
	{
		if (worldIn.isClientSide)
		{
			return InteractionResult.SUCCESS;
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
		return InteractionResult.SUCCESS;
	}
}
