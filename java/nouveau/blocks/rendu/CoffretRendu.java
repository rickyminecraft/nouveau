package nouveau.blocks.rendu;

import java.util.Calendar;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import nouveau.blocks.BlocksNames;
import nouveau.blocks.deco.Coffret;
import nouveau.blocks.entitées.CoffretEntity;

public class CoffretRendu<CoffretEntity extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<CoffretEntity>
{

	private final ModelPart lid;
	private final ModelPart bottom;
	private final ModelPart lock;
	private boolean xmasTextures;

	public CoffretRendu(BlockEntityRendererProvider.Context p_173607_) 
	{
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) 
		{
			this.xmasTextures = true;
		}

		LayerDefinition Layer = createSingleBodyLayer();
		ModelPart P = Layer.bakeRoot();

		this.bottom = P.getChild("bottom_Coffret");
		this.lid = P.getChild("lid_Coffret");
		this.lock = P.getChild("lock_Coffret");
	}

	public static LayerDefinition createSingleBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("bottom_Coffret", CubeListBuilder.create().texOffs(0, 19).addBox(1.0F, 0.0F, 1.0F, 14.0F, 5.0F, 14.0F), PartPose.ZERO);
		partdefinition.addOrReplaceChild("lid_Coffret", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 5.0F, 1.0F));
		partdefinition.addOrReplaceChild("lock_Coffret", CubeListBuilder.create().texOffs(0, 0).addBox(7.0F, 0.0F, 15.0F, 2.0F, 4.0F, 1.0F), PartPose.offset(0.0F, 4.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void render(CoffretEntity coffret, float partialTicks, PoseStack PoseStack, MultiBufferSource p_112366_, int p_112367_, int combinedOverlay) {
		Level level = coffret.getLevel();
		boolean flag = level != null;
		BlockState blockstate = flag ? coffret.getBlockState() : BlocksNames.Coffret.defaultBlockState().setValue(Coffret.ROTATION, 0);

		Block block = blockstate.getBlock();
		if (block instanceof Coffret) 
		{

			PoseStack.pushPose();
			float f = -(blockstate.getValue(Coffret.ROTATION) * 360 / 16.0F);
			PoseStack.translate(0.5D, 0.5D, 0.5D);
			PoseStack.mulPose(Vector3f.YP.rotationDegrees(f));
			PoseStack.translate(-0.25D, -0.5D, -0.25D);
			DoubleBlockCombiner.NeighborCombineResult<? extends CoffretEntity> neighborcombineresult;
			neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;

			float f1 = coffret.getOpenNess(partialTicks);
			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			PoseStack.scale(0.5F, 0.5F, 0.5F);
			int i = neighborcombineresult.<Int2IntFunction>apply(new BrightnessCombiner<>()).applyAsInt(p_112367_);
			Material material = this.getMaterial(coffret, ChestType.SINGLE);
			VertexConsumer vertexconsumer = material.buffer(p_112366_, RenderType::entityCutout);
			this.render(PoseStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, i, combinedOverlay);

			PoseStack.popPose();
		}
	}

	private void render(PoseStack p_112370_, VertexConsumer p_112371_, ModelPart p_112372_, ModelPart p_112373_, ModelPart p_112374_, float p_112375_, int p_112376_, int p_112377_) {
		p_112372_.xRot = -(p_112375_ * ((float)Math.PI / 2F));
		p_112373_.xRot = p_112372_.xRot;
		p_112372_.render(p_112370_, p_112371_, p_112376_, p_112377_);
		p_112373_.render(p_112370_, p_112371_, p_112376_, p_112377_);
		p_112374_.render(p_112370_, p_112371_, p_112376_, p_112377_);
	}

	protected Material getMaterial(CoffretEntity p_112363_, ChestType chestType) 
	{
		return Sheets.chooseMaterial(p_112363_, chestType, this.xmasTextures);
	}

}
