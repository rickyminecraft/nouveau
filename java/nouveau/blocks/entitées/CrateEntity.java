package nouveau.blocks.entitées;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import nouveau.blocks.TileentityTypes;

public class CrateEntity extends RandomizableContainerBlockEntity
{
	private final int EVENT_SET_OPEN_COUNT = 1;
	private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() 
	{
		protected void onOpen(Level p_155357_, BlockPos p_155358_, BlockState p_155359_) 
		{
			CrateEntity.playSound(p_155357_, p_155358_, p_155359_, SoundEvents.CHEST_OPEN);
		}

		protected void onClose(Level p_155367_, BlockPos p_155368_, BlockState p_155369_) 
		{
			CrateEntity.playSound(p_155367_, p_155368_, p_155369_, SoundEvents.CHEST_CLOSE);
		}

		protected void openerCountChanged(Level p_155361_, BlockPos p_155362_, BlockState p_155363_, int p_155364_, int p_155365_) 
		{
			CrateEntity.this.signalOpenCount(p_155361_, p_155362_, p_155363_, p_155364_, p_155365_);
		}

		protected boolean isOwnContainer(Player p_155355_) 
		{
			if (!(p_155355_.containerMenu instanceof ChestMenu)) 
			{
				return false;
			} else 
			{
				Container container = ((ChestMenu)p_155355_.containerMenu).getContainer();
				return container == CrateEntity.this || container instanceof CompoundContainer && ((CompoundContainer)container).contains(CrateEntity.this);
			}
		}
	};

	protected CrateEntity(BlockEntityType<?> p_155327_, BlockPos p_155328_, BlockState p_155329_)
	{
		super(p_155327_, p_155328_, p_155329_);
	}

	public CrateEntity(BlockPos p_155331_, BlockState p_155332_)
	{
		this(TileentityTypes.CRATE, p_155331_, p_155332_);
	}

	@Override
	public int getContainerSize() 
	{
		return 27;
	}

	public static int getOpenCount(BlockGetter p_59087_, BlockPos p_59088_) 
	{
		BlockState blockstate = p_59087_.getBlockState(p_59088_);
		if (blockstate.hasBlockEntity()) 
		{
			BlockEntity blockentity = p_59087_.getBlockEntity(p_59088_);
			if (blockentity instanceof CrateEntity)
			{
				return ((CrateEntity)blockentity).openersCounter.getOpenerCount();
			}
		}
		return 0;
	}

	static void playSound(Level p_155339_, BlockPos p_155340_, BlockState p_155341_, SoundEvent p_155342_) 
	{
		double d0 = (double)p_155340_.getX() + 0.5D;
		double d1 = (double)p_155340_.getY() + 0.5D;
		double d2 = (double)p_155340_.getZ() + 0.5D;
		p_155339_.playSound((Player)null, d0, d1, d2, p_155342_, SoundSource.BLOCKS, 0.5F, p_155339_.random.nextFloat() * 0.1F + 0.9F);
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
	protected Component getDefaultName() 
	{
		return new TranslatableComponent("container.crate");
	}

	@Override
	public void load(CompoundTag compound)//read
	{
		super.load(compound);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(compound))
		{
			ContainerHelper.loadAllItems(compound, this.items);
		}

	}

	@Override
	public CompoundTag save(CompoundTag compound)
	{
		super.save(compound);
		if (!this.trySaveLootTable(compound))
		{
			ContainerHelper.saveAllItems(compound, this.items);
		}

		return compound;
	}

	public static void swapContents(CrateEntity chest, CrateEntity otherChest)
	{
		NonNullList<ItemStack> nonnulllist = chest.getItems();
		chest.setItems(otherChest.getItems());
		otherChest.setItems(nonnulllist);
	}

	private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;
	@Override
	public void setBlockState(BlockState p_155251_) {
		super.setBlockState(p_155251_);
		if (this.chestHandler != null) {
			net.minecraftforge.common.util.LazyOptional<?> oldHandler = this.chestHandler;
			this.chestHandler = null;
			oldHandler.invalidate();
		}
	}

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) 
	{
		if (!this.remove && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) 
		{
			if (this.chestHandler == null)
				this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
			return this.chestHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	private net.minecraftforge.items.IItemHandlerModifiable createHandler() 
	{
		return new net.minecraftforge.items.wrapper.InvWrapper(this);
	}

	/**
	 * invalidates a tile entity
	 */
	@Override
	public void invalidateCaps() 
	{
		super.invalidateCaps();
		if (chestHandler != null)
			chestHandler.invalidate();
	}

	@Override
	protected AbstractContainerMenu createMenu(int p_59082_, Inventory p_59083_) 
	{
		return ChestMenu.threeRows(p_59082_, p_59083_, this);
	}

	public void recheckOpen() 
	{
		if (!this.remove) 
		{
			this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	protected void signalOpenCount(Level p_155333_, BlockPos p_155334_, BlockState p_155335_, int p_155336_, int p_155337_) 
	{
		Block block = p_155335_.getBlock();
		p_155333_.blockEvent(p_155334_, block, 1, p_155337_);
	}

}
