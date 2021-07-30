package nouveau.blocks.deco;

import nouveau.blocks.BlocksNames;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BrumeFX extends TextureSheetParticle
{
	ParticleRenderType TERRAIN_SHEET_PERSO = new ParticleRenderType() {
		@Override
		public void begin(BufferBuilder p_217600_1_, TextureManager p_217600_2_) {
			RenderSystem.enableBlend();
			//RenderSystem.blendFuncSeparate(bCol, rCol, gCol, alpha);
			RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			//blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderSystem.depthMask(true);
			p_217600_2_.bindForSetup(ModelBakery.MISSING_MODEL_LOCATION);
			p_217600_1_.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
		}

		@Override
		public void end(Tesselator p_217599_1_) {
			p_217599_1_.end();
		}

		@Override
		public String toString() {
			return "TERRAIN_SHEET";
		}

	};

	public BrumeFX(ClientLevel worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
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
	public ParticleRenderType getRenderType() 
	{
		return TERRAIN_SHEET_PERSO;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> 
	{

		@Override
		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) 
		{
			return new BrumeFX(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}
}
