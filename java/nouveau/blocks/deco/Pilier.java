package nouveau.blocks.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Pilier extends Block
{
	private static final VoxelShape PILIER = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	
	public Pilier(Properties properties) 
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) 
	{
		return PILIER;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		return PILIER;
	}
}
