package nouveau.blocks.deco;

import net.minecraft.block.Block;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Chaise extends HorizontalBlock implements IWaterLoggable
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
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH)
		{
			VoxelShape voxelshape = VoxelShapes.or(CHAISE_B01_AABB, CHAISE_B02_AABB);
			return voxelshape;
		}
		if (direction == Direction.NORTH)
		{
			VoxelShape voxelshape = VoxelShapes.or(CHAISE_B11_AABB, CHAISE_B12_AABB);
			return voxelshape;
		}
		if (direction == Direction.EAST)
		{
			VoxelShape voxelshape = VoxelShapes.or(CHAISE_B21_AABB, CHAISE_B22_AABB);
			return voxelshape;
		}
		if (direction == Direction.WEST)
		{
			VoxelShape voxelshape = VoxelShapes.or(CHAISE_B31_AABB, CHAISE_B32_AABB);
			return voxelshape;
		}
		return CHAISE_SELECTION_AABB;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		Direction direction = state.getValue(FACING);
		if (direction == Direction.SOUTH)
		{
			VoxelShape voxelshape = VoxelShapes.or(CHAISE_B01_AABB, CHAISE_B02_AABB);
			return voxelshape;
		}
		if (direction == Direction.NORTH)
		{
			VoxelShape voxelshape = VoxelShapes.or(CHAISE_B11_AABB, CHAISE_B12_AABB);
			return voxelshape;
		}
		if (direction == Direction.EAST)
		{
			VoxelShape voxelshape = VoxelShapes.or(CHAISE_B21_AABB, CHAISE_B22_AABB);
			return voxelshape;
		}
		if (direction == Direction.WEST)
		{
			VoxelShape voxelshape = VoxelShapes.or(CHAISE_B31_AABB, CHAISE_B32_AABB);
			return voxelshape;
		}
		return CHAISE_SELECTION_AABB;
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
				if (itype == 4)
				{
					itype = 0;
				}
				worldIn.setBlockAndUpdate(pos, state.setValue(TYPE,itype));
			}
		}
		return ActionResultType.SUCCESS;
	}
}
