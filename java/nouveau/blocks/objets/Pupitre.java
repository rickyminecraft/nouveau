package nouveau.blocks.objets;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Pupitre extends LecternBlock
{
	public static final VoxelShape A = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D);
	public static final VoxelShape B = Block.box(4.0D, 2.0D, 4.0D, 12.0D, 14.0D, 12.0D);
	public static final VoxelShape C = Shapes.or(A, B);
	public Pupitre(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, BlockGetter worldIn, BlockPos pos)
	{
		return C;
	}
}
