package nouveau.blocks.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Pile_ore extends Block
{
	public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
	protected static final VoxelShape[] SHAPES = new VoxelShape[]{Shapes.empty(), Block.box(1.0D, 0.0D, 1.0D, 15.0D, 10.0D, 15.0D), 
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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		return SHAPES[state.getValue(LAYERS)];
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		return SHAPES[state.getValue(LAYERS)];
	}
	
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult p_225533_6_)
	{
		if (worldIn.isClientSide)
		{
			return InteractionResult.SUCCESS;
		}
		Integer Height = state.getValue(LAYERS);
		if (++Height > 6)
		{
			Height = 1;
		}
		worldIn.setBlockAndUpdate(pos, state.setValue(LAYERS,Height));
		return InteractionResult.SUCCESS;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(LAYERS);
	}
}
