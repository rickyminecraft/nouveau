package nouveau.blocks.deco;

import net.minecraft.block.Block;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Table extends Block implements IWaterLoggable
{
	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 1);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape TABLE_BOUNDING_AABB = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	//protected static final VoxelShape TABLE_BOUNDING2_AABB = Block.makeCuboidShape(7.0D, 1.5D, 7.0D, 8.5D, 14.5D, 8.5D);
	
	public Table(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, 0).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return TABLE_BOUNDING_AABB;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		//if (state.get(TYPE).intValue() == 0)
		//{
			return TABLE_BOUNDING_AABB;
		//}
		//else
		//{
		//	return TABLE_BOUNDING2_AABB;
		//}
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(TYPE, WATERLOGGED);
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
        		if (itype == 2)
        		{
        			itype= 0;
        		}
        		worldIn.setBlockAndUpdate(pos, state.setValue(TYPE,itype));
        	}
        }
  		return ActionResultType.SUCCESS;
  	}
}
