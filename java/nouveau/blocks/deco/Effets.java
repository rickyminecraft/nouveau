package nouveau.blocks.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;

public class Effets extends Block
{
	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 4);
	protected static final VoxelShape EFFETS_AABB = Block.box(0.0D, 0.001D, 0.0D, 16.0D, 1.5D, 16.0D);
	protected static final VoxelShape NULL_AABB = Block.box(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	private static int Compteur;
	//private static int Tickrate;
	private static int loopsound = 0;

	public Effets(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, 0));
		Compteur = 0;
		//Tickrate = 15;
	}
	
	/**
     * Called by BlockItems just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.defaultBlockState().setValue(TYPE, 0);
    }
    
    /**
     * Called by BlockItems after a block is set in the world, to allow post-place logic
     */
    @Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        worldIn.setBlock(pos, state.setValue(TYPE, 0), 2);
    }
    
    /**
     * The type of render function called. 3 for standard block models, 2 for TESR's, 1 for liquids, -1 is no render
     */
    @Override
	public BlockRenderType getRenderShape(BlockState state)
    {
        return BlockRenderType.INVISIBLE;
    }
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return EFFETS_AABB;
    }
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return NULL_AABB;
    }
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(TYPE);
	}
    
    //onBlockActivated
  	@Override
  	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult Ray)
  	{
  		if (worldIn.isClientSide)
        {
            return ActionResultType.SUCCESS;
        }
        else
        {
        	ItemStack is = player.getMainHandItem();
        	Item it = is.getItem();
        	String nom = it.getDescriptionId();
        	if ("item.minecraft.stick".equalsIgnoreCase(nom) && player.isCreative())
        	{
        		int itype = state.getValue(TYPE).intValue();
        		itype++;
        		if (itype == 5)
        		{
        			itype= 0;
        		}
        		worldIn.setBlockAndUpdate(pos, state.setValue(TYPE,itype));
        	}
        }
  		return ActionResultType.SUCCESS;
  	}
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) 
    {
		int itype = state.getValue(TYPE).intValue();
		if (itype == 0)
		{
			double x = 0;
			double y = 0;
			double z = 0;
			for (int l = 0; l < 2; ++l)
			{
				x = pos.getX() + rand.nextFloat();
				y = pos.getY();
				z = pos.getZ() + rand.nextFloat();
				worldIn.addParticle(RedstoneParticleData.REDSTONE, x, y, z, 0.0D, 0.0D, 0.0D);
				worldIn.addParticle(ParticleTypes.WITCH, x, y, z, 0.0D, 0.0D, 0.0D);
			}
			x = pos.getX();
			y = pos.getY();
			z = pos.getZ();
			loopsound++;
			if (loopsound == 19)
			{
				worldIn.playLocalSound(x, y, z, SoundEvents.PORTAL_AMBIENT, SoundCategory.AMBIENT, 0.2F, 0.2F + rand.nextFloat() * 0.2F, true);
				loopsound = 0;
			}
		}
		if (itype == 1)
		{
			if (Compteur >= 5)
			{
				double x;
				double y;
				double z;
				Particle entityfx = null;
				final Minecraft mc = Minecraft.getInstance();
				for (int l = 0; l < 1; ++l)
				{
					x = pos.getX() + rand.nextFloat();
					y = pos.getY() + rand.nextFloat();
					z = pos.getZ() + rand.nextFloat();
					entityfx = new BrumeFX((ClientWorld)worldIn, x, y, z, 0.0D, 0.0D, 0.0D);
					mc.particleEngine.add(entityfx);
				}
				x = pos.getX();
				y = pos.getY();
				z = pos.getZ();
				loopsound++;
				if (loopsound == 19)
				{
					worldIn.playLocalSound(x, y, z, SoundEvents.AMBIENT_CAVE, SoundCategory.AMBIENT, 0.2F, 0.2F + rand.nextFloat() * 0.2F, true);
					loopsound = 0;
				}
				Compteur = -1;
			}
			Compteur ++;
		}
		if (itype == 2)
		{
			double x;
			double y;
			double z;
			for (int l = 0; l < 3; ++l)
			{
				x = pos.getX() + rand.nextFloat();
				y = pos.getY() + rand.nextFloat();
				z = pos.getZ() + rand.nextFloat();
				worldIn.addParticle(ParticleTypes.LARGE_SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			}
			x = pos.getX();
			y = pos.getY();
			z = pos.getZ();
			loopsound++;
			if (loopsound == 19)
			{
				worldIn.playLocalSound(x, y, z, SoundEvents.FIRE_AMBIENT, SoundCategory.AMBIENT, 0.2F, 0.2F + rand.nextFloat() * 0.2F, true);
				loopsound = 0;
			}
		}
		if (itype == 3)
		{
			double x;
			double y;
			double z;
			for (int l = 0; l < 5; ++l)
			{
				x = pos.getX() + rand.nextFloat();
				y = pos.getY() + rand.nextFloat();
				z = pos.getZ() + rand.nextFloat();
				worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, x, y, z, 0.0D, 0.0D, 0.0D);
			}
			x = pos.getX();
			y = pos.getY();
			z = pos.getZ();
			loopsound++;
			if (loopsound == 19)
			{
				worldIn.playLocalSound(x, y, z, SoundEvents.PORTAL_AMBIENT, SoundCategory.AMBIENT, 0.2F, 0.2F + rand.nextFloat() * 0.2F, true);
				loopsound = 0;
			}
		}
		if (itype == 4)
		{
			double x;
			double y;
			double z;
			for (int l = 0; l < 3; ++l)
			{
				x = pos.getX() + rand.nextFloat();
				y = pos.getY() + rand.nextFloat();
				z = pos.getZ() + rand.nextFloat();
				worldIn.addParticle(ParticleTypes.TOTEM_OF_UNDYING, x, y, z, 0.0D, 0.0D, 0.0D);
			}
			x = pos.getX();
			y = pos.getY();
			z = pos.getZ();
			loopsound++;
			if (loopsound == 19)
			{
				worldIn.playLocalSound(x, y, z, SoundEvents.WOOL_FALL, SoundCategory.AMBIENT, 0.2F, 0.2F + rand.nextFloat() * 0.2F, true);
				loopsound = 0;
			}
		}
    }
}
