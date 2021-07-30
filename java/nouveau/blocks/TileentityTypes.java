package nouveau.blocks;

import net.minecraft.tileentity.TileEntityType;
import nouveau.blocks.entitées.*;

public class TileentityTypes 
{
	public static TileEntityType<CoffretEntity> COFFRET = null;
	public static TileEntityType<CrateEntity> CRATE = null;
	public static void reg()
	{
		COFFRET = TileEntityType.Builder.of(CoffretEntity::new, BlocksNames.Coffret).build(null);
		COFFRET.setRegistryName("nouveau:coffret");
		CRATE = TileEntityType.Builder.of(CrateEntity::new, BlocksNames.acacia_crate).build(null);
		CRATE.setRegistryName("nouveau:crate");
	}
}
