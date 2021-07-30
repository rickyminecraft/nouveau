package nouveau.blocks;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Groupe_block extends ItemGroup
{
	public Groupe_block() 
	{
		super("blocs");
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
		return new ItemStack(BlocksNames.B1);
	}

}
