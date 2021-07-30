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
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Fenetre extends HorizontalBlock implements IWaterLoggable
{
	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 4);
	private static final VoxelShape FENETRE_A_AABB = Block.box(12.5D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape FENETRE_B_AABB = Block.box(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
	private static final VoxelShape FENETRE_C_AABB = Block.box(0.0D, 0.0D, 12.5D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape FENETRE_D_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
	private static final VoxelShape FENETRE_HAUT_AABB = Block.box(0.0D, 11.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape FENETRE_ROND_BAS_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.5D, 16.0D);
	private static final VoxelShape FENETRE_ROND_GAUCHE_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.5D, 16.0D, 16.0D);
	private static final VoxelShape FENETRE_ROND_DROITE_AABB = Block.box(14.5D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape FENETRE_ROND_GAUCHE1_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.5D);
	private static final VoxelShape FENETRE_ROND_DROITE1_AABB = Block.box(0.0D, 0.0D, 14.5D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape FENETRE_ROND_HAUT_AABB = Block.box(0.0D, 14.5D, 0.0D, 16.0D, 16.0D, 16.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public Fenetre(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, 0).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		Direction direction = state.getValue(FACING);
		int Type = state.getValue(TYPE);
		switch (Type)
		{
		case 0:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = FENETRE_HAUT_AABB;
				VoxelShape voxelshape2 = VoxelShapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = FENETRE_HAUT_AABB;
				VoxelShape voxelshape2 = VoxelShapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
		case 1:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				return voxelshape;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				return voxelshape;
			}
		case 2:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_ROND_BAS_AABB, FENETRE_ROND_GAUCHE_AABB);
				VoxelShape voxelshape2 = VoxelShapes.or(FENETRE_ROND_DROITE_AABB, FENETRE_ROND_HAUT_AABB);
				VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_ROND_BAS_AABB, FENETRE_ROND_GAUCHE1_AABB);
				VoxelShape voxelshape2 = VoxelShapes.or(FENETRE_ROND_DROITE1_AABB, FENETRE_ROND_HAUT_AABB);
				VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
		case 3:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				return voxelshape;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				return voxelshape;
			}
		case 4:
		}
		return VoxelShapes.block();
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		Direction direction = state.getValue(FACING);
		int Type = state.getValue(TYPE);
		switch (Type)
		{
		case 0:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = FENETRE_HAUT_AABB;
				VoxelShape voxelshape2 = VoxelShapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = FENETRE_HAUT_AABB;
				VoxelShape voxelshape2 = VoxelShapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
		case 1:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				return voxelshape;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				return voxelshape;
			}
		case 2:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_ROND_BAS_AABB, FENETRE_ROND_GAUCHE_AABB);
				VoxelShape voxelshape2 = VoxelShapes.or(FENETRE_ROND_DROITE_AABB, FENETRE_ROND_HAUT_AABB);
				VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_ROND_BAS_AABB, FENETRE_ROND_GAUCHE1_AABB);
				VoxelShape voxelshape2 = VoxelShapes.or(FENETRE_ROND_DROITE1_AABB, FENETRE_ROND_HAUT_AABB);
				VoxelShape voxelshape3 = VoxelShapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
		case 3:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				return voxelshape;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = VoxelShapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				return voxelshape;
			}
		case 4:
		}
		return VoxelShapes.block();
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
				if (itype == 5)
				{
					itype= 0;
				}
				worldIn.setBlockAndUpdate(pos, state.setValue(TYPE,itype));
			}
		}
  		return ActionResultType.SUCCESS;
  	}
}
