package nouveau.blocks.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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

public class Table extends Block implements SimpleWaterloggedBlock
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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) 
	{
		return TABLE_BOUNDING_AABB;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) 
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
        		if (itype == 2)
        		{
        			itype= 0;
        		}
        		worldIn.setBlockAndUpdate(pos, state.setValue(TYPE,itype));
        	}
        }
  		return InteractionResult.SUCCESS;
  	}
}
