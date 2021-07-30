package nouveau.blocks.entitées;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import nouveau.blocks.TileentityTypes;

public class CrateEntity extends LockableLootTileEntity implements ITickableTileEntity
{
	private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
	/** The number of players currently using this chest */
	protected int openCount;
	/**
	 * A counter that is incremented once each tick. Used to determine when to recompute ; this is done every 200 ticks
	 * (but staggered between different chests). However, the new value isn't actually sent to clients when it is
	 * changed.
	 */
	private int ticksSinceSync;
	private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;
	
	protected CrateEntity(TileEntityType<?> typeIn)
	{
		super(typeIn);
	}
	
	public CrateEntity()
	{
		this(TileentityTypes.CRATE);
	}

	@Override
	public int getContainerSize() 
	{
		return 27;
	}

	@Override
	public void tick()
	{
		int i = this.worldPosition.getX();
		int j = this.worldPosition.getY();
		int k = this.worldPosition.getZ();
		++this.ticksSinceSync;
		this.openCount = calculatePlayersUsingSync(this.level, this, this.ticksSinceSync, i, j, k, this.openCount);
	}

	public static int calculatePlayersUsingSync(World p_213977_0_, LockableTileEntity p_213977_1_, int p_213977_2_, int p_213977_3_, int p_213977_4_, int p_213977_5_, int p_213977_6_) 
	{
		if (!p_213977_0_.isClientSide && p_213977_6_ != 0 && (p_213977_2_ + p_213977_3_ + p_213977_4_ + p_213977_5_) % 200 == 0) 
		{
			p_213977_6_ = getOpenCount(p_213977_0_, p_213977_1_, p_213977_3_, p_213977_4_, p_213977_5_);
		}
		return p_213977_6_;
	}

	public static int getOpenCount(World p_213976_0_, LockableTileEntity p_213976_1_, int p_213976_2_, int p_213976_3_, int p_213976_4_) 
	{
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

	/**
	 * See {@link Block#eventReceived} for more information. This must return true serverside before it is called
	 * clientside.
	 */
	@Override
	public boolean triggerEvent(int id, int type) 
	{
		if (id == 1) 
		{
			this.openCount = type;
			return true;
		} 
		else 
		{
			return super.triggerEvent(id, type);
		}
	}

	@Override
	public void startOpen(PlayerEntity player)
	{
		if (!player.isSpectator()) 
		{
			if (this.openCount < 0)
			{
				this.openCount = 0;
			}

			++this.openCount;
			this.onOpenOrClose();
		}
	}

	@Override
	public void stopOpen(PlayerEntity player)
	{
		if (!player.isSpectator()) 
		{
			--this.openCount;
			this.onOpenOrClose();
		}
	}

	protected void onOpenOrClose()
	{
		Block block = this.getBlockState().getBlock();
		if (block instanceof ChestBlock)
		{
			this.level.blockEvent(this.worldPosition, block, 1, this.openCount);
			this.level.updateNeighborsAt(this.worldPosition, block);
		}
	}

	@Override
	protected NonNullList<ItemStack> getItems()
	{
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) 
	{
		this.items = itemsIn;
	}

	@Override
	protected ITextComponent getDefaultName() 
	{
		return new TranslationTextComponent("container.crate");
	}

	@Override
	public void load(BlockState state, CompoundNBT compound)//read
	{
		super.load(state, compound);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(compound))
		{
			ItemStackHelper.loadAllItems(compound, this.items);
		}

	}

	@Override
	public CompoundNBT save(CompoundNBT compound)
	{
		super.save(compound);
		if (!this.trySaveLootTable(compound))
		{
			ItemStackHelper.saveAllItems(compound, this.items);
		}

		return compound;
	}

	public static int getPlayersUsing(IBlockReader reader, BlockPos posIn)
	{
		BlockState blockstate = reader.getBlockState(posIn);
		if (blockstate.hasTileEntity()) 
		{
			TileEntity tileentity = reader.getBlockEntity(posIn);
			if (tileentity instanceof ChestTileEntity) 
			{
				return ((CrateEntity)tileentity).openCount;
			}
		}

		return 0;
	}

	public static void swapContents(CrateEntity chest, CrateEntity otherChest)
	{
		NonNullList<ItemStack> nonnulllist = chest.getItems();
		chest.setItems(otherChest.getItems());
		otherChest.setItems(nonnulllist);
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
	protected Container createMenu(int id, PlayerInventory player) 
	{
		return ChestContainer.threeRows(id, player, this);
	}

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
		if (!this.remove && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (this.chestHandler == null)
				this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
			return this.chestHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	private net.minecraftforge.items.IItemHandlerModifiable createHandler() {
		return new net.minecraftforge.items.wrapper.InvWrapper(this);
	}

	/**
	 * invalidates a tile entity
	 */
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		if (chestHandler != null)
			chestHandler.invalidate();
	}

}
