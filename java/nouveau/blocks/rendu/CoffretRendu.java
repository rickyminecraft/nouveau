package nouveau.blocks.rendu;

import java.util.Calendar;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import nouveau.blocks.BlocksNames;
import nouveau.blocks.deco.Coffret;
import nouveau.blocks.entitées.CoffretEntity;

public class CoffretRendu extends TileEntityRenderer<CoffretEntity>
{

	private final ModelRenderer up;
	private final ModelRenderer down;
	private final ModelRenderer lock;

	private boolean isChristmas;

	public CoffretRendu(TileEntityRendererDispatcher rendererDispatcherIn) 
	{
		super(rendererDispatcherIn);
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
			this.isChristmas = true;
		}

		this.down = new ModelRenderer(64, 64, 0, 19);
		this.down.addBox(1.0F, 0.0F, 1.0F, 14.0F, 5.0F, 14.0F, 0.0F);
		this.up = new ModelRenderer(64, 64, 0, 0);
		this.up.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
		this.up.y = 5.0F;
		this.up.z = 1.0F;
		this.lock = new ModelRenderer(64, 64, 0, 0);
		this.lock.addBox(7.0F, 0.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
		this.lock.y = 4.0F;
	}

	@Override
	public void render(CoffretEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

		World world = tileEntityIn.getLevel();
		
		boolean flag = world != null;
		BlockState blockstate = flag ? tileEntityIn.getBlockState(): BlocksNames.Coffret.defaultBlockState().setValue(Coffret.ROTATION, 0);
		Block block = blockstate.getBlock();
		if (block instanceof Coffret)
		{
			TileEntityMerger.ICallbackWrapper<? extends CoffretEntity> icallbackwrapper;

	        icallbackwrapper = TileEntityMerger.ICallback::acceptNone;
			
			int i = icallbackwrapper.apply(new DualBrightnessCallback<>()).applyAsInt(combinedLightIn);
			
			matrixStackIn.pushPose();
			
			//on tourne comme les panneaux, 16 positions
			float f1 = -(blockstate.getValue(Coffret.ROTATION) * 360 / 16.0F);
			
			matrixStackIn.translate(0.5D, 0.5D, 0.5D);
			matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f1));
			matrixStackIn.translate(-0.25D, -0.5D, -0.25D);
			float f2 = icallbackwrapper.apply(Coffret.opennessCombiner(tileEntityIn)).get(partialTicks);
			f2 = 1.0F - f2;
	        f2 = 1.0F - f2 * f2 * f2;
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
			
			RenderMaterial material = getMaterial(tileEntityIn ,ChestType.SINGLE);
			
			IVertexBuilder ivertexbuilder = material.buffer(bufferIn, RenderType::entityCutoutNoCull);
			
			this.func_228871_a_(matrixStackIn, ivertexbuilder, this.up, this.lock, this.down, f2, i, combinedOverlayIn);
			matrixStackIn.popPose();
		}
		
	}

	private void func_228871_a_(MatrixStack matrixStackIn, IVertexBuilder ivertexbuilder, ModelRenderer up, ModelRenderer down, ModelRenderer lock, float f1, int combinedLightIn, int combinedOverlayIn) {
		up.xRot = -(f1 * ((float)Math.PI / 2F));
		down.xRot = up.xRot;
		up.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
		down.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
		lock.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
	}

	protected RenderMaterial getMaterial(CoffretEntity tileEntity, ChestType chestType) {
		return Atlases.chooseMaterial(tileEntity, chestType, this.isChristmas);
	}
}
