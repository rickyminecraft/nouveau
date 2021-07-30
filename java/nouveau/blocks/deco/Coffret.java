package nouveau.blocks.deco;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import nouveau.blocks.entitées.CoffretEntity;

public class Coffret extends ContainerBlock implements IWaterLoggable 
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
	protected static final VoxelShape field_196315_B = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 5.0D, 11.0D);

	public Coffret(Properties builder) 
	{
		super(builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(ROTATION, Integer.valueOf(0)).setValue(OPEN, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return field_196315_B;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.below()).getMaterial().isSolid();
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(ROTATION, Integer.valueOf(MathHelper.floor((180.0F + context.getRotation()) * 16.0F / 360.0F + 0.5D) & 15)).setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
	}

	/**
	 * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
	 * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
	 * returns its solidified counterpart.
	 * Note that this method should ideally consider only the specific face passed in.
	 */
	@Override
	@Deprecated
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.DOWN && !this.canSurvive(stateIn, worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever possible. Implementing/overriding is
	 * fine.
	 */
	@Deprecated
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(ROTATION, Integer.valueOf(rot.rotate(state.getValue(ROTATION), 16)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever possible. Implementing/overriding is fine.
	 */
	@Deprecated
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.setValue(ROTATION, Integer.valueOf(mirrorIn.mirror(state.getValue(ROTATION), 16)));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(ROTATION, OPEN, WATERLOGGED);
	}

	@OnlyIn(Dist.CLIENT)
	public static TileEntityMerger.ICallback<CoffretEntity, Float2FloatFunction> opennessCombiner(final IChestLid p_226917_0_) {
		return new TileEntityMerger.ICallback<CoffretEntity, Float2FloatFunction>() {
			@Override
			public Float2FloatFunction acceptDouble(CoffretEntity p_225539_1_, CoffretEntity p_225539_2_) {
				return (p_226921_2_) -> {
					return Math.max(p_225539_1_.getOpenNess(p_226921_2_), p_225539_2_.getOpenNess(p_226921_2_));
				};
			}

			@Override
			public Float2FloatFunction acceptSingle(CoffretEntity p_225538_1_) {
				return p_225538_1_::getOpenNess;
			}

			@Override
			public Float2FloatFunction acceptNone() {
				return p_226917_0_::getOpenNess;
			}
		};
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader worldIn) {
		return new CoffretEntity();
	}

	@Override
	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}

	/**
	 * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	 * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	 * @deprecated call via {@link IBlockState#getRenderType()} whenever possible. Implementing/overriding is fine.
	 */
	@Deprecated
	@Override
	public BlockRenderType getRenderShape(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (worldIn.isClientSide) {
			return ActionResultType.SUCCESS;
		} else {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof CoffretEntity) {
				player.openMenu((CoffretEntity)tileentity);
				player.awardStat(Stats.OPEN_CHEST);
			}
			return ActionResultType.SUCCESS;
		}
	}

	@Override
	@Deprecated
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof IInventory) {
				InventoryHelper.dropContents(worldIn, pos, (IInventory)tileentity);
				worldIn.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}

	/**
	 * Called by BlockItems after a block is set in the world, to allow post-place logic
	 */
	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if (stack.hasCustomHoverName()) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof CoffretEntity) {
				((CoffretEntity)tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}
	
}
