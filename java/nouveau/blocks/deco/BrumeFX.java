package nouveau.blocks.deco;

import nouveau.blocks.BlocksNames;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BrumeFX extends SpriteTexturedParticle
{
	IParticleRenderType TERRAIN_SHEET_PERSO = new IParticleRenderType() {
	      @Override
		public void begin(BufferBuilder p_217600_1_, TextureManager p_217600_2_) {
	         RenderSystem.enableBlend();
	         RenderSystem.blendColor(bCol, rCol, gCol, alpha);
	         //blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	         RenderSystem.depthMask(true);
	         p_217600_2_.bind(PlayerContainer.BLOCK_ATLAS);
	         p_217600_1_.begin(7, DefaultVertexFormats.PARTICLE);
	      }

	      @Override
		public void end(Tessellator p_217599_1_) {
	         p_217599_1_.end();
	      }

	      @Override
		public String toString() {
	         return "TERRAIN_SHEET";
	      }
	   };
	
	public BrumeFX(ClientWorld worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn);
		xSpeedIn = -0.5d + this.random.nextDouble();
		zSpeedIn = -0.5d + this.random.nextDouble();
		if (xSpeedIn >= 0.02D)
		{
			xSpeedIn = 0.02D;
		}
		if (xSpeedIn <= -0.02D)
		{
			xSpeedIn = -0.02D;
		}
		if (zSpeedIn >= 0.02D)
		{
			zSpeedIn = 0.02D;
		}
		if (zSpeedIn <= -0.02D)
		{
			zSpeedIn = -0.02D;
		}
		this.xd = xSpeedIn;
	    this.yd = 0.0f;
	    this.zd = zSpeedIn;
		this.rCol = this.gCol = this.bCol = 1.0F - (float)(this.random.nextDouble() * 0.30000001192092896D);
		float Size_local = this.random.nextFloat() * 50.0F;
		this.scale(Size_local);
		this.lifetime = (int)(this.random.nextDouble() * 10.0D) + 80;
		this.hasPhysics = false;
		this.onGround = false;
		//on obtient la texture en utilisant un block artificiel
		//qui ne sert qu'a pointer sur la texture
		ItemStack stack = new ItemStack(BlocksNames.effets);
		this.setSprite(Minecraft.getInstance().getItemRenderer().getItemModelShaper().getParticleIcon(stack));
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void tick()
	{
		this.xo = this.x;
		this.zo = this.z;
		if (this.lifetime-- <= 0) 
		{
			this.remove();
		} 
		else 
		{
			this.move(this.xd, this.yd, this.zd);
		}
	}

	@Override
	public IParticleRenderType getRenderType() 
	{
		// TODO Auto-generated method stub
		return TERRAIN_SHEET_PERSO;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> 
	{

		@Override
		public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) 
		{
			return new BrumeFX(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}
}
