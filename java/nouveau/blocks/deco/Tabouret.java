package nouveau.blocks.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
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

public class Tabouret extends Block implements SimpleWaterloggedBlock
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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) 
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
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
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
	public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState().setValue(TYPE, 0);
    }
    
    /**
     * Called by BlockItems after a block is set in the world, to allow post-place logic
     */
    @Override
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        worldIn.setBlock(pos, state.setValue(TYPE, 0), 2);
    }
    
    /**
     * The type of render function called. 3 for standard block models, 2 for TESR's, 1 for liquids, -1 is no render
     */
    @Override
	public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
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
