package nouveau.blocks.entitées;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import nouveau.blocks.TileentityTypes;
import nouveau.blocks.deco.Coffret;

public class CoffretEntity extends LockableLootTileEntity implements IChestLid, ITickableTileEntity
{
	private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
	/** The current angle of the lid (between 0 and 1) */
	protected float openness;
	/** The angle of the lid last tick */
	protected float oOpenness;
	protected int openCount;

	/**
	 * A counter that is incremented once each tick. Used to determine when to recompute ; this is done every 200 ticks
	 * (but staggered between different chests). However, the new value isn't actually sent to clients when it is
	 * changed.
	 */
	private int tickInterval;
	private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;

	private CoffretEntity(TileEntityType<?> p_i49963_1_) {
		super(p_i49963_1_);
	}

	public CoffretEntity() {
		this(TileentityTypes.COFFRET);
	}

	@Override
	public int getContainerSize() {
		return 27;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.coffret");
	}

	@Override
	public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		super.load(p_230337_1_, p_230337_2_);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(p_230337_2_)) {
			ItemStackHelper.loadAllItems(p_230337_2_, this.items);
		}

	}

	@Override
	public CompoundNBT save(CompoundNBT p_189515_1_) {
		super.save(p_189515_1_);
		if (!this.trySaveLootTable(p_189515_1_)) {
			ItemStackHelper.saveAllItems(p_189515_1_, this.items);
		}

		return p_189515_1_;
	}

	@Override
	public void tick() {
		int i = this.worldPosition.getX();
		int j = this.worldPosition.getY();
		int k = this.worldPosition.getZ();
		++this.tickInterval;
		this.openCount = getOpenCount(this.level, this, this.tickInterval, i, j, k, this.openCount);
		this.oOpenness = this.openness;
		if (this.openCount > 0 && this.openness == 0.0F) {
			this.playSound(SoundEvents.CHEST_OPEN);
		}

		if (this.openCount == 0 && this.openness > 0.0F || this.openCount > 0 && this.openness < 1.0F) {
			float f1 = this.openness;
			if (this.openCount > 0) {
				this.openness += 0.1F;
			} else {
				this.openness -= 0.1F;
			}

			if (this.openness > 1.0F) {
				this.openness = 1.0F;
			}

			if (this.openness < 0.5F && f1 >= 0.5F) {
				this.playSound(SoundEvents.CHEST_CLOSE);
			}

			if (this.openness < 0.0F) {
				this.openness = 0.0F;
			}
		}

	}

	public static int getOpenCount(World p_213977_0_, LockableTileEntity p_213977_1_, int p_213977_2_, int p_213977_3_, int p_213977_4_, int p_213977_5_, int p_213977_6_) {
		if (!p_213977_0_.isClientSide && p_213977_6_ != 0 && (p_213977_2_ + p_213977_3_ + p_213977_4_ + p_213977_5_) % 200 == 0) {
			p_213977_6_ = getOpenCount(p_213977_0_, p_213977_1_, p_213977_3_, p_213977_4_, p_213977_5_);
		}

		return p_213977_6_;
	}

	public static int getOpenCount(World p_213976_0_, LockableTileEntity p_213976_1_, int p_213976_2_, int p_213976_3_, int p_213976_4_) {
		int i = 0;

		for(PlayerEntity playerentity : p_213976_0_.getEntitiesOfClass(PlayerEntity.class, new AxisAlignedBB(p_213976_2_ - 5.0F, p_213976_3_ - 5.0F, p_213976_4_ - 5.0F, p_213976_2_ + 1 + 5.0F, p_213976_3_ + 1 + 5.0F, p_213976_4_ + 1 + 5.0F))) {
			if (playerentity.containerMenu instanceof ChestContainer) {
				IInventory iinventory = ((ChestContainer)playerentity.containerMenu).getContainer();
				if (iinventory == p_213976_1_ || iinventory instanceof DoubleSidedInventory && ((DoubleSidedInventory)iinventory).contains(p_213976_1_)) {
					++i;
				}
			}
		}

		return i;
	}

	private void playSound(SoundEvent p_195483_1_) {

			double d0 = this.worldPosition.getX() + 0.5D;
			double d1 = this.worldPosition.getY() + 0.5D;
			double d2 = this.worldPosition.getZ() + 0.5D;


			this.level.playSound((PlayerEntity)null, d0, d1, d2, p_195483_1_, SoundCategory.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);

	}

	@Override
	public boolean triggerEvent(int p_145842_1_, int p_145842_2_) {
		if (p_145842_1_ == 1) {
			this.openCount = p_145842_2_;
			return true;
		} else {
			return super.triggerEvent(p_145842_1_, p_145842_2_);
		}
	}

	@Override
	public void startOpen(PlayerEntity p_174889_1_) {
		if (!p_174889_1_.isSpectator()) {
			if (this.openCount < 0) {
				this.openCount = 0;
			}

			++this.openCount;
			this.signalOpenCount();
		}

	}

	@Override
	public void stopOpen(PlayerEntity p_174886_1_) {
		if (!p_174886_1_.isSpectator()) {
			--this.openCount;
			this.signalOpenCount();
		}

	}

	protected void signalOpenCount() {
		Block block = this.getBlockState().getBlock();
		if (block instanceof Coffret) {
			this.level.blockEvent(this.worldPosition, block, 1, this.openCount);
			this.level.updateNeighborsAt(this.worldPosition, block);
		}

	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> p_199721_1_) {
		this.items = p_199721_1_;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public float getOpenNess(float p_195480_1_) {
		return MathHelper.lerp(p_195480_1_, this.oOpenness, this.openness);
	}

	public static int getOpenCount(IBlockReader p_195481_0_, BlockPos p_195481_1_) {
		BlockState blockstate = p_195481_0_.getBlockState(p_195481_1_);
		if (blockstate.hasTileEntity()) {
			TileEntity tileentity = p_195481_0_.getBlockEntity(p_195481_1_);
			if (tileentity instanceof CoffretEntity) {
				return ((CoffretEntity)tileentity).openCount;
			}
		}

		return 0;
	}

	public static void swapContents(CoffretEntity p_199722_0_, CoffretEntity p_199722_1_) {
		NonNullList<ItemStack> nonnulllist = p_199722_0_.getItems();
		p_199722_0_.setItems(p_199722_1_.getItems());
		p_199722_1_.setItems(nonnulllist);
	}

	@Override
	protected Container createMenu(int p_213906_1_, PlayerInventory p_213906_2_) {
		return ChestContainer.threeRows(p_213906_1_, p_213906_2_, this);
	}

	@Override
	public void clearCache() {
		super.clearCache();
		if (this.chestHandler != null) {
			net.minecraftforge.common.util.LazyOptional<?> oldHandler = this.chestHandler;
			this.chestHandler = null;
			oldHandler.invalidate();
		}
	}

	@Override
	protected void invalidateCaps() {
		super.invalidateCaps();
		if (chestHandler != null)
			chestHandler.invalidate();
	}

}
