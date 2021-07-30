package nouveau.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import nouveau.blocks.rendu.CoffretRendu;

@Mod("nouveau")
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class Blocks 
{
	// The instance of your mod that Forge uses.
	public static Blocks instance;

	public static final ItemGroup blocksTab1 = new Groupe_block();
	public static final ItemGroup blocksTab2 = new Groupe_portes();
	public static final ItemGroup blocksTab3 = new Groupe_decoration();
	
	public Blocks()
	{
		instance = this;
		// Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);

        MinecraftForge.EVENT_BUS.register(this);
	}

	public void preInit(final FMLClientSetupEvent  event) 
	{
		TileEntityRendererDispatcher.instance.setSpecialRendererInternal(TileentityTypes.COFFRET, new CoffretRendu(TileEntityRendererDispatcher.instance));
	}
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> Reg)
	{
		Makeblock.makeblocks();
		Names.registerBlocks(Reg);
		
		//pour attribuer le bon rendu au bloc
		//plus exactement active la transparence de la texture
		RenderType Type = RenderType.cutoutMipped();
		RenderTypeLookup.setRenderLayer(BlocksNames.B47, Type);
		RenderTypeLookup.setRenderLayer(BlocksNames.oak_table, Type);
		RenderTypeLookup.setRenderLayer(BlocksNames.dark_oak_table, Type);
		RenderTypeLookup.setRenderLayer(BlocksNames.jungle_table, Type);
		RenderTypeLookup.setRenderLayer(BlocksNames.spruce_table, Type);
		RenderTypeLookup.setRenderLayer(BlocksNames.birch_table, Type);
		RenderTypeLookup.setRenderLayer(BlocksNames.acacia_table, Type);
		RenderTypeLookup.setRenderLayer(BlocksNames.Couverts, Type);
		RenderTypeLookup.setRenderLayer(BlocksNames.Door18, Type);
	}

	@SubscribeEvent
	public static void registerItem(final RegistryEvent.Register<Item> Reg)
	{
		Makeblock.makeitem();
		Names.registerItems(Reg);
	}
	
	@SubscribeEvent
	public static void registerEntity(final RegistryEvent.Register<TileEntityType<?>> Reg)
	{
		TileentityTypes.reg();
		Reg.getRegistry().register(TileentityTypes.COFFRET);
		Reg.getRegistry().register(TileentityTypes.CRATE);
	}

}
