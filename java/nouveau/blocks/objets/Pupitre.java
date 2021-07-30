package nouveau.blocks.objets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LecternBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class Pupitre extends LecternBlock
{
	public static final VoxelShape A = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D);
	public static final VoxelShape B = Block.box(4.0D, 2.0D, 4.0D, 12.0D, 14.0D, 12.0D);
	public static final VoxelShape C = VoxelShapes.or(A, B);
	public Pupitre(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return C;
	}
}
