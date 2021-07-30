package nouveau.blocks.deco;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Pile_ore extends Block
{
	public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
	protected static final VoxelShape[] SHAPES = new VoxelShape[]{VoxelShapes.empty(), Block.box(1.0D, 0.0D, 1.0D, 15.0D, 10.0D, 15.0D), 
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 11.0D, 15.0D), 
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 12.0D, 15.0D), 
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 13.0D, 15.0D), 
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D), 
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D),
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D),
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D)};

	public Pile_ore(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, Integer.valueOf(1)));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPES[state.getValue(LAYERS)];
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPES[state.getValue(LAYERS)];
	}
	
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_)
	{
		if (worldIn.isClientSide)
		{
			return ActionResultType.SUCCESS;
		}
		Integer Height = state.getValue(LAYERS);
		if (++Height > 6)
		{
			Height = 1;
		}
		worldIn.setBlockAndUpdate(pos, state.setValue(LAYERS,Height));
		return ActionResultType.SUCCESS;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(LAYERS);
	}
}
