package nouveau.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import nouveau.blocks.deco.*;
import nouveau.blocks.objets.BlockGlass_stone;
import nouveau.blocks.objets.Blockdeco;
import nouveau.blocks.objets.Door;
import nouveau.blocks.objets.NewLights;
import nouveau.blocks.objets.Pupitre;

public class Makeblock
{
	public static void makeblocks()
	{
		BlocksNames.Door1 = new Door(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(5.0F)).setRegistryName("porte_diamant");
		BlocksNames.Door2 = new Door(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(3.0F)).setRegistryName("porte_or");
		BlocksNames.Door4 = new Door(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(5.0F)).setRegistryName("porte_fer_0");
		BlocksNames.Door13 = new Door(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(5.0F)).setRegistryName("porte_fer_1");
		BlocksNames.Door14 = new Door(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(5.0F)).setRegistryName("porte_fer_2");
		BlocksNames.Door15 = new Door(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(5.0F)).setRegistryName("porte_fer_3");
		BlocksNames.Door3 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_0");
		BlocksNames.Door5 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_1");
		BlocksNames.Door6 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_2");
		BlocksNames.Door7 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_3");
		BlocksNames.Door9 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_5");
		BlocksNames.Door8 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_4");
		BlocksNames.Door10 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_6");
		BlocksNames.Door11 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_7");
		BlocksNames.Door12 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_8");
		BlocksNames.Door16 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_9");
		BlocksNames.Door17 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_10");
		BlocksNames.Door18 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_11");
		BlocksNames.Door19 = new Door(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F)).setRegistryName("porte_bois_12");
		
		BlocksNames.Newlight1 = new NewLights(Block.Properties.of(Material.GLASS, MaterialColor.QUARTZ).strength(0.3F).sound(SoundType.GLASS).lightLevel((p_235464_0_) -> {return 15;})).setRegistryName("lightblock_01");
		BlocksNames.Newlight2 = new NewLights(Block.Properties.of(Material.GLASS, MaterialColor.QUARTZ).strength(0.3F).sound(SoundType.GLASS).lightLevel((p_235464_0_) -> {return 15;})).setRegistryName("lightblock_02");

		BlocksNames.B1 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc1");
		BlocksNames.B2 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc2");
		BlocksNames.B3 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc3");
		BlocksNames.B4 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc4");
		BlocksNames.B5 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc5");
		BlocksNames.B6 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc6");
		BlocksNames.B7 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc7");
		BlocksNames.B8 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc8");
		BlocksNames.B9 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc9");
		BlocksNames.B10 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc10");
		BlocksNames.B11 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc11");
		BlocksNames.B12 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc12");
		BlocksNames.B13 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc13");
		BlocksNames.B14 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc14");
		BlocksNames.B15 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc15");
		BlocksNames.B16 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc16");
		BlocksNames.B17 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc17");
		BlocksNames.B18 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc18");
		BlocksNames.B19 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc19");
		BlocksNames.B20 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc20");
		BlocksNames.B21 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc21");
		BlocksNames.B22 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc22");
		BlocksNames.B23 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc23");
		BlocksNames.B24 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc24");
		BlocksNames.B25 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc25");
		BlocksNames.B26 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc26");
		BlocksNames.B27 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc27");
		BlocksNames.B28 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc28");
		BlocksNames.B29 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc29");
		BlocksNames.B30 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc30");
		BlocksNames.B31 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc31");
		BlocksNames.B32 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc32");
		BlocksNames.B33 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc33");
		BlocksNames.B34 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc34");
		BlocksNames.B35 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc35");
		BlocksNames.B36 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc36");
		BlocksNames.B37 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc37");
		BlocksNames.B38 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc38");
		BlocksNames.B39 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc39");
		BlocksNames.B40 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc40");
		BlocksNames.B41 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc41");
		BlocksNames.B42 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc42");
		BlocksNames.B43 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc43");
		BlocksNames.B44 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc44");
		BlocksNames.B45 = new Blockdeco(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F).sound(SoundType.STONE)).setRegistryName("bloc45");
		BlocksNames.B46 = new Blockdeco(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(1.5F).sound(SoundType.METAL)).setRegistryName("bloc46");
		BlocksNames.B47 = new BlockGlass_stone(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(1.5F).sound(SoundType.METAL).noCollission()).setRegistryName("bloc47");
		
		BlocksNames.lumiere_mural_0 = new Lumieres_mural(Block.Properties.of(Material.GLASS, MaterialColor.QUARTZ).strength(0.3F).sound(SoundType.GLASS).lightLevel((p_235464_0_) -> {return 15;})).setRegistryName("lumiere_mural_0");
		BlocksNames.lumiere_mural_1 = new Lumieres_mural(Block.Properties.of(Material.GLASS, MaterialColor.QUARTZ).strength(0.3F).sound(SoundType.GLASS).lightLevel((p_235464_0_) -> {return 15;})).setRegistryName("lumiere_mural_1");
		
		BlocksNames.oak_Chaise =  new Chaise(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("oak_chaise");
		BlocksNames.dark_oak_Chaise =  new Chaise(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("dark_oak_chaise");
		BlocksNames.spruce_Chaise =  new Chaise(Block.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("spruce_chaise");
		BlocksNames.jungle_Chaise =  new Chaise(Block.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("jungle_chaise");
		BlocksNames.birch_Chaise =  new Chaise(Block.Properties.of(Material.WOOD, MaterialColor.SAND).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("birch_chaise");
		BlocksNames.acacia_Chaise =  new Chaise(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("acacia_chaise");
		
		BlocksNames.oak_escalier =  new Escalier(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("oak_escalier");
		BlocksNames.dark_oak_escalier =  new Escalier(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("dark_oak_escalier");
		BlocksNames.spruce_escalier =  new Escalier(Block.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("spruce_escalier");
		BlocksNames.jungle_escalier =  new Escalier(Block.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("jungle_escalier");
		BlocksNames.birch_escalier =  new Escalier(Block.Properties.of(Material.WOOD, MaterialColor.SAND).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("birch_escalier");
		BlocksNames.acacia_escalier =  new Escalier(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("acacia_escalier");
		
		BlocksNames.oak_tabouret =  new Tabouret(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("oak_tabouret");
		BlocksNames.dark_oak_tabouret =  new Tabouret(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("dark_oak_tabouret");
		BlocksNames.spruce_tabouret =  new Tabouret(Block.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("spruce_tabouret");
		BlocksNames.jungle_tabouret =  new Tabouret(Block.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("jungle_tabouret");
		BlocksNames.birch_tabouret =  new Tabouret(Block.Properties.of(Material.WOOD, MaterialColor.SAND).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("birch_tabouret");
		BlocksNames.acacia_tabouret =  new Tabouret(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("acacia_tabouret");
		
		BlocksNames.oak_table =  new Table(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("oak_table");
		BlocksNames.dark_oak_table =  new Table(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("dark_oak_table");
		BlocksNames.spruce_table =  new Table(Block.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("spruce_table");
		BlocksNames.jungle_table =  new Table(Block.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("jungle_table");
		BlocksNames.birch_table =  new Table(Block.Properties.of(Material.WOOD, MaterialColor.SAND).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("birch_table");
		BlocksNames.acacia_table =  new Table(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("acacia_table");
		
		BlocksNames.oak_table_angle =  new Table_angle(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("oak_table_angle");
		BlocksNames.dark_oak_table_angle =  new Table_angle(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("dark_oak_table_angle");
		BlocksNames.spruce_table_angle =  new Table_angle(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("spruce_table_angle");
		BlocksNames.jungle_table_angle =  new Table_angle(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("jungle_table_angle");
		BlocksNames.birch_table_angle =  new Table_angle(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("birch_table_angle");
		BlocksNames.acacia_table_angle =  new Table_angle(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("acacia_table_angle");
		
		BlocksNames.oak_fenetre =  new Fenetre(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("oak_fenetre");
		BlocksNames.dark_oak_fenetre =  new Fenetre(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("dark_oak_fenetre");
		BlocksNames.spruce_fenetre =  new Fenetre(Block.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("spruce_fenetre");
		BlocksNames.jungle_fenetre =  new Fenetre(Block.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("jungle_fenetre");
		BlocksNames.birch_fenetre =  new Fenetre(Block.Properties.of(Material.WOOD, MaterialColor.SAND).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("birch_fenetre");
		BlocksNames.acacia_fenetre =  new Fenetre(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).setRegistryName("acacia_fenetre");

		BlocksNames.stone_fenetre =  new Fenetre(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F, 6.0F).sound(SoundType.STONE)).setRegistryName("stone_fenetre");
		BlocksNames.cobblestone_fenetre =  new Fenetre(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F)).setRegistryName("cobblestone_fenetre");
		BlocksNames.brick_fenetre =  new Fenetre(Block.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(2.0F, 6.0F)).setRegistryName("brick_fenetre");
		BlocksNames.quartz_fenetre =  new Fenetre(Block.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)).setRegistryName("quartz_fenetre");
		BlocksNames.nether_brick_fenetre =  new Fenetre(Block.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0F, 65.0F)).setRegistryName("nether_brick_fenetre");
		BlocksNames.prismarine_fenetre =  new Fenetre(Block.Properties.of(Material.STONE, MaterialColor.COLOR_CYAN).strength(1.5F, 6.0F)).setRegistryName("prismarine_fenetre");
		BlocksNames.stone_brick_fenetre =  new Fenetre(Block.Properties.of(Material.STONE).strength(1.5F, 6.0F)).setRegistryName("stone_brick_fenetre");
		BlocksNames.sandstone_fenetre =  new Fenetre(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("sandstone_fenetre");

		BlocksNames.effets =  new Effets(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.2F).randomTicks()).setRegistryName("effets");
		BlocksNames.pupitre =  new Pupitre(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.2F).randomTicks()).setRegistryName("pupitre");
		
		BlocksNames.oak_creux =  new Creux(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F)).setRegistryName("oak_creux");
		BlocksNames.dark_oak_creux =  new Creux(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F)).setRegistryName("dark_oak_creux");
		BlocksNames.spruce_creux =  new Creux(Block.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F, 3.0F)).setRegistryName("spruce_creux");
		BlocksNames.jungle_creux =  new Creux(Block.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.0F, 3.0F)).setRegistryName("jungle_creux");
		BlocksNames.birch_creux =  new Creux(Block.Properties.of(Material.WOOD, MaterialColor.SAND).strength(2.0F, 3.0F)).setRegistryName("birch_creux");
		BlocksNames.acacia_creux =  new Creux(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F)).setRegistryName("acacia_creux");
		
		BlocksNames.oak_plateau =  new Plateau(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F)).setRegistryName("oak_plateau");
		BlocksNames.dark_oak_plateau =  new Plateau(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F, 3.0F)).setRegistryName("dark_oak_plateau");
		BlocksNames.spruce_plateau =  new Plateau(Block.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F, 3.0F)).setRegistryName("spruce_plateau");
		BlocksNames.jungle_plateau =  new Plateau(Block.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.0F, 3.0F)).setRegistryName("jungle_plateau");
		BlocksNames.birch_plateau =  new Plateau(Block.Properties.of(Material.WOOD, MaterialColor.SAND).strength(2.0F, 3.0F)).setRegistryName("birch_plateau");
		BlocksNames.acacia_plateau =  new Plateau(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F)).setRegistryName("acacia_plateau");
		BlocksNames.stone_brick_plateau =  new Plateau(Block.Properties.of(Material.STONE).strength(2.0F, 3.0F)).setRegistryName("stone_brick_plateau");
		BlocksNames.sandstone_plateau =  new Plateau(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("sandstone_plateau");
		
		BlocksNames.sandstone_pilier =  new Pilier(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("sandstone_pilier");
		BlocksNames.stone_pilier =  new Pilier(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("stone_pilier");
		BlocksNames.cobblestone_pilier =  new Pilier(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("cobblestone_pilier");
		BlocksNames.stone_brick_pilier =  new Pilier(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("stone_brick_pilier");
		BlocksNames.prismarine_pilier =  new Pilier(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("prismarine_pilier");
		BlocksNames.brick_pilier =  new Pilier(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("brick_pilier");
		BlocksNames.quartz_pilier =  new Pilier(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("quartz_pilier");
		
		BlocksNames.oak_meuble_01 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("oak_meuble_01");
		BlocksNames.dark_oak_meuble_01 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).strength(0.8F)).setRegistryName("dark_oak_meuble_01");
		BlocksNames.spruce_meuble_01 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.PODZOL).strength(0.8F)).setRegistryName("spruce_meuble_01");
		BlocksNames.jungle_meuble_01 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.DIRT).strength(0.8F)).setRegistryName("jungle_meuble_01");
		BlocksNames.birch_meuble_01 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("birch_meuble_01");
		BlocksNames.acacia_meuble_01 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)).setRegistryName("acacia_meuble_01");
		
		BlocksNames.oak_meuble_02 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("oak_meuble_02");
		BlocksNames.dark_oak_meuble_02 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).strength(0.8F)).setRegistryName("dark_oak_meuble_02");
		BlocksNames.spruce_meuble_02 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.PODZOL).strength(0.8F)).setRegistryName("spruce_meuble_02");
		BlocksNames.jungle_meuble_02 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.DIRT).strength(0.8F)).setRegistryName("jungle_meuble_02");
		BlocksNames.birch_meuble_02 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("birch_meuble_02");
		BlocksNames.acacia_meuble_02 =  new Table_chevet(Block.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)).setRegistryName("acacia_meuble_02");
		
		BlocksNames.oak_armoire_01 =  new Armoire(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("oak_armoire_01");
		BlocksNames.dark_oak_armoire_01 =  new Armoire(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).strength(0.8F)).setRegistryName("dark_oak_armoire_01");
		BlocksNames.spruce_armoire_01 =  new Armoire(Block.Properties.of(Material.STONE, MaterialColor.PODZOL).strength(0.8F)).setRegistryName("spruce_armoire_01");
		BlocksNames.jungle_armoire_01 =  new Armoire(Block.Properties.of(Material.STONE, MaterialColor.DIRT).strength(0.8F)).setRegistryName("jungle_armoire_01");
		BlocksNames.birch_armoire_01 =  new Armoire(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("birch_armoire_01");
		BlocksNames.acacia_armoire_01 =  new Armoire(Block.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)).setRegistryName("acacia_armoire_01");
		
		BlocksNames.oak_colonne =  new Colonne(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("oak_colonne");
		BlocksNames.dark_oak_colonne =  new Colonne(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).strength(0.8F)).setRegistryName("dark_oak_colonne");
		BlocksNames.spruce_colonne =  new Colonne(Block.Properties.of(Material.STONE, MaterialColor.PODZOL).strength(0.8F)).setRegistryName("spruce_colonne");
		BlocksNames.jungle_colonne =  new Colonne(Block.Properties.of(Material.STONE, MaterialColor.DIRT).strength(0.8F)).setRegistryName("jungle_colonne");
		BlocksNames.birch_colonne =  new Colonne(Block.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.8F)).setRegistryName("birch_colonne");
		BlocksNames.acacia_colonne =  new Colonne(Block.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)).setRegistryName("acacia_colonne");
		
		BlocksNames.oak_banc =  new Banc(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("oak_banc");
		BlocksNames.dark_oak_banc =  new Banc(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("dark_oak_banc");
		BlocksNames.spruce_banc =  new Banc(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("spruce_banc");
		BlocksNames.jungle_banc =  new Banc(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("jungle_banc");
		BlocksNames.birch_banc =  new Banc(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("birch_banc");
		BlocksNames.acacia_banc =  new Banc(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("acacia_banc");
		
		BlocksNames.horloge =  new Horloge(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F)).setRegistryName("horloge");
		BlocksNames.Coffret =  new Coffret(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F)).setRegistryName("coffret");
		BlocksNames.pile_papiers =  new Papiers(Block.Properties.of(Material.CLOTH_DECORATION, MaterialColor.SNOW).strength(2.0F, 3.0F)).setRegistryName("pile_papiers");
		BlocksNames.Couverts =  new Couverts(Block.Properties.of(Material.SAND, MaterialColor.SNOW).strength(2.0F, 3.0F)).setRegistryName("couverts");
		
		BlocksNames.chaine_petite_or =  new Chaine(Block.Properties.of(Material.METAL, MaterialColor.GOLD).strength(3.0F, 3.0F)).setRegistryName("chaine_petite_or");
		BlocksNames.chaine_petite_fer =  new Chaine(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(3.0F, 3.0F)).setRegistryName("chaine_petite_fer");
		BlocksNames.chaine_large_or =  new Grande_chaine(Block.Properties.of(Material.METAL, MaterialColor.GOLD).strength(3.0F, 3.0F)).setRegistryName("chaine_large_or");
		BlocksNames.chaine_large_fer =  new Grande_chaine(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(3.0F, 3.0F)).setRegistryName("chaine_large_fer");
		
		BlocksNames.pile_or =  new Pile_ore(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(3.0F, 3.0F)).setRegistryName("pile_or");
		BlocksNames.pile_fer =  new Pile_ore(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(3.0F, 3.0F)).setRegistryName("pile_fer");
		BlocksNames.pile_diamant =  new Pile_ore(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(3.0F, 3.0F)).setRegistryName("pile_diamant");
		BlocksNames.pile_redstone =  new Pile_ore(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(3.0F, 3.0F)).setRegistryName("pile_redstone");
		BlocksNames.pile_emeraude =  new Pile_ore(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(3.0F, 3.0F)).setRegistryName("pile_emeraude");
		
		BlocksNames.oak_crate =  new Crate(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("oak_crate");
		BlocksNames.dark_oak_crate =  new Crate(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("dark_oak_crate");
		BlocksNames.spruce_crate =  new Crate(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("spruce_crate");
		BlocksNames.jungle_crate =  new Crate(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("jungle_crate");
		BlocksNames.birch_crate =  new Crate(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(0.8F)).setRegistryName("birch_crate");
		BlocksNames.acacia_crate =  new Crate(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(3.0F, 3.0F)).setRegistryName("acacia_crate");
	}
	
	public static void makeitem()
	{
		BlocksNames.ItemDoor1 = new DoubleHighBlockItem(BlocksNames.Door1, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_diamant");
		BlocksNames.ItemDoor2 = new DoubleHighBlockItem(BlocksNames.Door2, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_or");
		BlocksNames.ItemDoor3 = new DoubleHighBlockItem(BlocksNames.Door3, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_0");
		BlocksNames.ItemDoor4 = new DoubleHighBlockItem(BlocksNames.Door4, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_fer_0");
		BlocksNames.ItemDoor5 = new DoubleHighBlockItem(BlocksNames.Door5, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_1");
		BlocksNames.ItemDoor6 = new DoubleHighBlockItem(BlocksNames.Door6, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_2");
		BlocksNames.ItemDoor7 = new DoubleHighBlockItem(BlocksNames.Door7, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_3");
		BlocksNames.ItemDoor8 = new DoubleHighBlockItem(BlocksNames.Door8, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_4");
		BlocksNames.ItemDoor9 = new DoubleHighBlockItem(BlocksNames.Door9, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_5");
		BlocksNames.ItemDoor10 = new DoubleHighBlockItem(BlocksNames.Door10, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_6");
		BlocksNames.ItemDoor11 = new DoubleHighBlockItem(BlocksNames.Door11, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_7");
		BlocksNames.ItemDoor12 = new DoubleHighBlockItem(BlocksNames.Door12, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_8");
		BlocksNames.ItemDoor13 = new DoubleHighBlockItem(BlocksNames.Door13, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_fer_1");
		BlocksNames.ItemDoor14 = new DoubleHighBlockItem(BlocksNames.Door14, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_fer_2");
		BlocksNames.ItemDoor15 = new DoubleHighBlockItem(BlocksNames.Door15, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_fer_3");
		BlocksNames.ItemDoor16 = new DoubleHighBlockItem(BlocksNames.Door16, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_9");
		BlocksNames.ItemDoor17 = new DoubleHighBlockItem(BlocksNames.Door17, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_10");
		BlocksNames.ItemDoor18 = new DoubleHighBlockItem(BlocksNames.Door18, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_11");
		BlocksNames.ItemDoor19 = new DoubleHighBlockItem(BlocksNames.Door19, (new Item.Properties()).tab(Blocks.blocksTab2)).setRegistryName("porte_bois_12");
	}
}