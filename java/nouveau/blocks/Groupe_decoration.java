package nouveau.blocks;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Groupe_decoration extends CreativeModeTab
{
	public Groupe_decoration() 
	{
		super("decorations");
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
		return new ItemStack(BlocksNames.acacia_Chaise);
	}
}
