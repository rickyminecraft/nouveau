package nouveau.blocks.deco;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class Pilier extends Block
{
	private static final VoxelShape PILIER = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	
	public Pilier(Properties properties) 
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return PILIER;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return PILIER;
	}
}
