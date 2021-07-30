package nouveau.blocks.deco;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import nouveau.blocks.TileentityTypes;
import nouveau.blocks.entitées.CoffretEntity;

public class Coffret extends BaseEntityBlock implements SimpleWaterloggedBlock 
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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return field_196315_B;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.below()).getMaterial().isSolid();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(ROTATION, Integer.valueOf(Mth.floor((double)((180.0F + context.getRotation()) * 16.0F / 360.0F) + 0.5D) & 15)).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	/**
	 * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
	 * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
	 * returns its solidified counterpart.
	 * Note that this method should ideally consider only the specific face passed in.
	 */
	@Override
	@Deprecated
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ROTATION, OPEN, WATERLOGGED);
	}

	@OnlyIn(Dist.CLIENT)
	public static DoubleBlockCombiner.Combiner<CoffretEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity p_226917_0_) {
		return new DoubleBlockCombiner.Combiner<CoffretEntity, Float2FloatFunction>() {
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
	public BlockEntity newBlockEntity(BlockPos p_153064_, BlockState p_153065_) {
		return new CoffretEntity(p_153064_, p_153065_);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153055_, BlockState p_153056_, BlockEntityType<T> p_153057_) {
		return p_153055_.isClientSide ? createTickerHelper(p_153057_, TileentityTypes.COFFRET, CoffretEntity::lidAnimateTick) : null;
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		return false;
	}

	/**
	 * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	 * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	 * @deprecated call via {@link IBlockState#getRenderType()} whenever possible. Implementing/overriding is fine.
	 */
	@Deprecated
	@Override
	public RenderShape getRenderShape(BlockState state) 
	{
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult p_225533_6_) 
	{
		if (worldIn.isClientSide) 
		{
			return InteractionResult.SUCCESS;
		} else 
		{
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof CoffretEntity) 
			{
				player.openMenu((CoffretEntity)tileentity);
				player.awardStat(Stats.OPEN_CHEST);
			}
			return InteractionResult.SUCCESS;
		}
	}

	@Override
	@Deprecated
	public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		if (state.getBlock() != newState.getBlock()) 
		{
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof Container) 
			{
				Containers.dropContents(worldIn, pos, (Container)tileentity);
				worldIn.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}

	/**
	 * Called by BlockItems after a block is set in the world, to allow post-place logic
	 */
	@Override
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) 
	{
		if (stack.hasCustomHoverName()) 
		{
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof CoffretEntity) 
			{
				((CoffretEntity)tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}
}
