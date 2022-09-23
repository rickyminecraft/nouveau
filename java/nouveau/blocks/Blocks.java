package nouveau.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
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

	public static final CreativeModeTab blocksTab1 = new Groupe_block();
	public static final CreativeModeTab blocksTab2 = new Groupe_portes();
	public static final CreativeModeTab blocksTab3 = new Groupe_decoration();
	
	public Blocks()
	{
		instance = this;
		// Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);

        MinecraftForge.EVENT_BUS.register(this);
	}

	public void preInit(final FMLClientSetupEvent  event) 
	{
		BlockEntityRenderers.register(TileentityTypes.COFFRET, CoffretRendu::new);
		//pour attribuer le bon rendu au bloc
		//plus exactement active la transparence de la texture
		RenderType Type = RenderType.cutoutMipped();
		ItemBlockRenderTypes.setRenderLayer(BlocksNames.B47, Type);
		ItemBlockRenderTypes.setRenderLayer(BlocksNames.oak_table, Type);
		ItemBlockRenderTypes.setRenderLayer(BlocksNames.dark_oak_table, Type);
		ItemBlockRenderTypes.setRenderLayer(BlocksNames.jungle_table, Type);
		ItemBlockRenderTypes.setRenderLayer(BlocksNames.spruce_table, Type);
		ItemBlockRenderTypes.setRenderLayer(BlocksNames.birch_table, Type);
		ItemBlockRenderTypes.setRenderLayer(BlocksNames.acacia_table, Type);
		ItemBlockRenderTypes.setRenderLayer(BlocksNames.Couverts, Type);
		ItemBlockRenderTypes.setRenderLayer(BlocksNames.Door18, Type);
	}
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> Reg)
	{
		Makeblock.makeblocks();
		Names.registerBlocks(Reg);
	}

	@SubscribeEvent
	public static void registerItem(final RegistryEvent.Register<Item> Reg)
	{
		Makeblock.makeitem();
		Names.registerItems(Reg);
	}
	
	@SubscribeEvent
	public static void registerEntity(final RegistryEvent.Register<BlockEntityType<?>> Reg)
	{
		TileentityTypes.reg();
		Reg.getRegistry().register(TileentityTypes.COFFRET);
		Reg.getRegistry().register(TileentityTypes.CRATE);
	}

}
