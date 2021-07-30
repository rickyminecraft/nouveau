package nouveau.blocks;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Groupe_portes extends CreativeModeTab
{
	public Groupe_portes() 
	{
		super("portes");
	}
	
	@Override
	public boolean hasSearchBar() 
	{
		return false;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack makeIcon() 
	{
		return new ItemStack(BlocksNames.ItemDoor1);
	}
}
