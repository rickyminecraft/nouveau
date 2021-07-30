package nouveau.blocks.deco;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class Armoire extends HorizontalBlock
{
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	private static final VoxelShape MEUBLE_BOUNDING_AABB = Block.box(0.0D, 0.1D, 0.0D, 16.0D, 16.0D, 16.0D);

	public Armoire(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(FACING, Direction.NORTH));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return MEUBLE_BOUNDING_AABB;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return MEUBLE_BOUNDING_AABB;
	}

	/**
	 * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
	 * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
	 * returns its solidified counterpart.
	 * Note that this method should ideally consider only the specific face passed in.
	 */
	@Override
	@Deprecated
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) 
	{
		DoubleBlockHalf doubleblockhalf = stateIn.getValue(HALF);
		if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.getBlock() == this && facingState.getValue(HALF) != doubleblockhalf) 
		{
			return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		} else 
		{
			return Blocks.AIR.defaultBlockState();
		}
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		BlockPos blockpos = context.getClickedPos();
		return blockpos.getY() < context.getLevel().dimensionType().logicalHeight()/*getHeight()*/ - 1 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context) ? this.defaultBlockState().setValue(FACING, context.getHorizontalDirection()) : null;
	}

	/**
	 * Called by BlockItems after a block is set in the world, to allow post-place logic
	 */
	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
	{
		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(FACING, state.getValue(FACING)), 3);
	}

	@Override
	@Deprecated
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) 
	{
		if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
			return super.canSurvive(state, worldIn, pos);
		} else 
		{
			BlockState blockstate = worldIn.getBlockState(pos.below());
			if (state.getBlock() != this) return super.canSurvive(state, worldIn, pos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
			return blockstate.getBlock() == this && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
		}
	}

	public void placeAt(IWorld worldIn, BlockPos pos, int flags) 
	{
		worldIn.setBlock(pos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER), flags);
		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER), flags);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(HALF, FACING);
	}
}
