package nouveau.blocks.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.material.Fluids;

public class Chaine extends RotatedPillarBlock implements SimpleWaterloggedBlock
{
	private static final VoxelShape AABB_SMALL_X = Block.box(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
	private static final VoxelShape AABB_SMALL_Y = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private static final VoxelShape AABB_SMALL_Z = Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public Chaine(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) 
	{
		Axis Axe = state.getValue(AXIS);
		if (Axe == Direction.Axis.X)
		{

			return AABB_SMALL_X;
		}
		if (Axe == Direction.Axis.Y)
		{

			return AABB_SMALL_Y;
		}
		if (Axe == Direction.Axis.Z)
		{

			return AABB_SMALL_Z;
		}
		return Shapes.block();
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		Axis Axe = state.getValue(AXIS);
		if (Axe == Direction.Axis.X)
		{

			return AABB_SMALL_X;
		}
		if (Axe == Direction.Axis.Y)
		{

			return AABB_SMALL_Y;
		}
		if (Axe == Direction.Axis.Z)
		{

			return AABB_SMALL_Z;
		}
		return Shapes.block();
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) 
	{
		builder.add(WATERLOGGED, AXIS);
	}

	@Override
	@Deprecated
	public FluidState getFluidState(BlockState state) 
	{
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

}
