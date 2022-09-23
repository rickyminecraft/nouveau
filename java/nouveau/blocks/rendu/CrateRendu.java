package nouveau.blocks.rendu;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import nouveau.blocks.entitées.CoffretEntity;

@OnlyIn(Dist.CLIENT)
public class CrateRendu implements BlockEntityRenderer<CoffretEntity>
{
	public CrateRendu(BlockEntityRendererProvider.Context p_173613_)
	{
		p_173613_.getBlockEntityRenderDispatcher();
	}

	@Override
	public void render(CoffretEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) 
	{

	}

}
