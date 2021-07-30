package nouveau.blocks.deco;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import nouveau.blocks.entitées.CrateEntity;

public class Crate extends ContainerBlock implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	protected static final VoxelShape cuboid = Block.box(0.5D, 0.0D, 0.5D, 15.5D, 15.5D, 15.5D);

	public Crate(Properties builder)
	{
		super(builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return cuboid;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	@Deprecated
	public FluidState getFluidState(BlockState state) 
	{
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader worldIn) 
	{
		return new CrateEntity();
	}

	@Override
	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) 
	{
		return false;
	}

	/**
	 * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	 * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	 * @deprecated call via {@link IBlockState#getRenderType()} whenever possible. Implementing/overriding is fine.
	 */
	@Deprecated
	@Override
	public BlockRenderType getRenderShape(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever possible. Implementing/overriding is
	 * fine.
	 */
	@Deprecated
	@Override
	public BlockState rotate(BlockState state, Rotation rot) 
	{
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever possible. Implementing/overriding is fine.
	 */
	@Deprecated
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) 
	{
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_)
	{
		if (worldIn.isClientSide)
		{
			return ActionResultType.SUCCESS;
		} 
		this.interactWith(worldIn, pos, player);
		return ActionResultType.SUCCESS;
	}

	/**
	 * Interface for handling interaction with blocks that impliment AbstractFurnaceBlock. Called in onBlockActivated
	 * inside AbstractFurnaceBlock.
	 */
	protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player)
	{
		TileEntity tileentity = worldIn.getBlockEntity(pos);
		if (tileentity instanceof CrateEntity)
		{
			player.openMenu((INamedContainerProvider)tileentity);
			player.awardStat(Stats.INTERACT_WITH_FURNACE);
		}
	}
}
