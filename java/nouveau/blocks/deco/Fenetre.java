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

public class Fenetre extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock
{
	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 4);
	private static final VoxelShape FENETRE_A_AABB = Block.box(12.5D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape FENETRE_B_AABB = Block.box(0.0D, 0.0D, 0.0D, 3.5D, 16.0D, 16.0D);
	private static final VoxelShape FENETRE_C_AABB = Block.box(0.0D, 0.0D, 12.5D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape FENETRE_D_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.5D);
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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) 
	{
		Direction direction = state.getValue(FACING);
		int Type = state.getValue(TYPE);
		switch (Type)
		{
		case 0:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = FENETRE_HAUT_AABB;
				VoxelShape voxelshape2 = Shapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = FENETRE_HAUT_AABB;
				VoxelShape voxelshape2 = Shapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
		case 1:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				return voxelshape;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				return voxelshape;
			}
		case 2:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_ROND_BAS_AABB, FENETRE_ROND_GAUCHE_AABB);
				VoxelShape voxelshape2 = Shapes.or(FENETRE_ROND_DROITE_AABB, FENETRE_ROND_HAUT_AABB);
				VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_ROND_BAS_AABB, FENETRE_ROND_GAUCHE1_AABB);
				VoxelShape voxelshape2 = Shapes.or(FENETRE_ROND_DROITE1_AABB, FENETRE_ROND_HAUT_AABB);
				VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
		case 3:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				return voxelshape;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				return voxelshape;
			}
		case 4:
		}
		return Shapes.block();
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		Direction direction = state.getValue(FACING);
		int Type = state.getValue(TYPE);
		switch (Type)
		{
		case 0:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = FENETRE_HAUT_AABB;
				VoxelShape voxelshape2 = Shapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = FENETRE_HAUT_AABB;
				VoxelShape voxelshape2 = Shapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
		case 1:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				return voxelshape;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				return voxelshape;
			}
		case 2:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_ROND_BAS_AABB, FENETRE_ROND_GAUCHE_AABB);
				VoxelShape voxelshape2 = Shapes.or(FENETRE_ROND_DROITE_AABB, FENETRE_ROND_HAUT_AABB);
				VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_ROND_BAS_AABB, FENETRE_ROND_GAUCHE1_AABB);
				VoxelShape voxelshape2 = Shapes.or(FENETRE_ROND_DROITE1_AABB, FENETRE_ROND_HAUT_AABB);
				VoxelShape voxelshape3 = Shapes.or(voxelshape, voxelshape2);
				return voxelshape3;
			}
		case 3:
			if (direction == Direction.SOUTH || direction == Direction.NORTH)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_A_AABB, FENETRE_B_AABB);
				return voxelshape;
			}
			if (direction == Direction.EAST || direction == Direction.WEST)
			{
				VoxelShape voxelshape = Shapes.or(FENETRE_C_AABB, FENETRE_D_AABB);
				return voxelshape;
			}
		case 4:
		}
		return Shapes.block();
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
				if (itype == 5)
				{
					itype= 0;
				}
				worldIn.setBlockAndUpdate(pos, state.setValue(TYPE,itype));
			}
		}
  		return InteractionResult.SUCCESS;
  	}
}
