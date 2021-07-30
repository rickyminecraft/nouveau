package nouveau.blocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import nouveau.blocks.entitées.*;

public class TileentityTypes 
{
	public static BlockEntityType<CoffretEntity> COFFRET = null;
	public static BlockEntityType<CrateEntity> CRATE = null;
	public static void reg()
	{
		COFFRET = BlockEntityType.Builder.of(CoffretEntity::new, BlocksNames.Coffret).build(null);
		COFFRET.setRegistryName("nouveau:coffret");
		CRATE = BlockEntityType.Builder.of(CrateEntity::new, BlocksNames.acacia_crate).build(null);
		CRATE.setRegistryName("nouveau:crate");
	}
}
