package nouveau.blocks.deco;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Tabouret extends Block implements IWaterLoggable
{
	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 1);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape TABOURE_AABB_BAS = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape TABOURET_AABB_HAUT = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 13.0D, 13.0D);

	public Tabouret(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, 0).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		if (state.getValue(TYPE).intValue() == 0)
		{
			return TABOURE_AABB_BAS;
		}
		else
		{
			return TABOURET_AABB_HAUT;
		}
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		if (state.getValue(TYPE).intValue() == 0)
		{
			return TABOURE_AABB_BAS;
		}
		else
		{
			return TABOURET_AABB_HAUT;
		}
	}

	/**
     * Called by BlockItems just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.defaultBlockState().setValue(TYPE, 0);
    }
    
    /**
     * Called by BlockItems after a block is set in the world, to allow post-place logic
     */
    @Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        worldIn.setBlock(pos, state.setValue(TYPE, 0), 2);
    }
    
    /**
     * The type of render function called. 3 for standard block models, 2 for TESR's, 1 for liquids, -1 is no render
     */
    @Override
	public BlockRenderType getRenderShape(BlockState state)
    {
        return BlockRenderType.MODEL;
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
