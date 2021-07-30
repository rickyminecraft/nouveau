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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.material.Fluids;

public class Chaise extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock
{
	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 3);
	protected static final VoxelShape CHAISE_SELECTION_AABB = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	private static final VoxelShape CHAISE_B01_AABB = Block.box(5.0D, 8.0D, 11.0D, 11.0D, 16.0D, 13.0D);
	private static final VoxelShape CHAISE_B02_AABB = Block.box(3.0D, 6.5D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape CHAISE_B11_AABB = Block.box(5.0D, 8.0D, 3.0D, 11.0D, 16.0D, 5.0D);
	private static final VoxelShape CHAISE_B12_AABB = Block.box(3.0D, 6.5D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape CHAISE_B21_AABB = Block.box(11.0D, 8.0D, 5.0D, 13.0D, 16.0D, 11.0D);
	private static final VoxelShape CHAISE_B22_AABB = Block.box(3.0D, 6.5D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape CHAISE_B31_AABB = Block.box(3.0D, 8.0D, 5.0D, 5.0D, 16.0D, 11.0D);
	private static final VoxelShape CHAISE_B32_AABB = Block.box(3.0D, 6.5D, 3.0D, 13.0D, 8.0D, 13.0D);
	
	public Chaise(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, 0).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) 
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH)
		{
			VoxelShape voxelshape = Shapes.or(CHAISE_B01_AABB, CHAISE_B02_AABB);
			return voxelshape;
		}
		if (direction == Direction.NORTH)
		{
			VoxelShape voxelshape = Shapes.or(CHAISE_B11_AABB, CHAISE_B12_AABB);
			return voxelshape;
		}
		if (direction == Direction.EAST)
		{
			VoxelShape voxelshape = Shapes.or(CHAISE_B21_AABB, CHAISE_B22_AABB);
			return voxelshape;
		}
		if (direction == Direction.WEST)
		{
			VoxelShape voxelshape = Shapes.or(CHAISE_B31_AABB, CHAISE_B32_AABB);
			return voxelshape;
		}
		return CHAISE_SELECTION_AABB;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH)
		{
			VoxelShape voxelshape = Shapes.or(CHAISE_B01_AABB, CHAISE_B02_AABB);
			return voxelshape;
		}
		if (direction == Direction.NORTH)
		{
			VoxelShape voxelshape = Shapes.or(CHAISE_B11_AABB, CHAISE_B12_AABB);
			return voxelshape;
		}
		if (direction == Direction.EAST)
		{
			VoxelShape voxelshape = Shapes.or(CHAISE_B21_AABB, CHAISE_B22_AABB);
			return voxelshape;
		}
		if (direction == Direction.WEST)
		{
			VoxelShape voxelshape = Shapes.or(CHAISE_B31_AABB, CHAISE_B32_AABB);
			return voxelshape;
		}
		return CHAISE_SELECTION_AABB;
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
				if (itype == 4)
				{
					itype = 0;
				}
				worldIn.setBlockAndUpdate(pos, state.setValue(TYPE,itype));
			}
		}
		return InteractionResult.SUCCESS;
	}
}
