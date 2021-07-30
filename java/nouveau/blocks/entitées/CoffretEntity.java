package nouveau.blocks.entit�es;

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
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import nouveau.blocks.TileentityTypes;

public class CoffretEntity extends RandomizableContainerBlockEntity implements LidBlockEntity
{
	private static final int EVENT_SET_OPEN_COUNT = 1;
	private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() 
	{
		protected void onOpen(Level p_155357_, BlockPos p_155358_, BlockState p_155359_) 
		{
			CoffretEntity.playSound(p_155357_, p_155358_, p_155359_, SoundEvents.CHEST_OPEN);
		}

		protected void onClose(Level p_155367_, BlockPos p_155368_, BlockState p_155369_) 
		{
			CoffretEntity.playSound(p_155367_, p_155368_, p_155369_, SoundEvents.CHEST_CLOSE);
		}

		protected void openerCountChanged(Level p_155361_, BlockPos p_155362_, BlockState p_155363_, int p_155364_, int p_155365_) 
		{
			CoffretEntity.this.signalOpenCount(p_155361_, p_155362_, p_155363_, p_155364_, p_155365_);
		}

		protected boolean isOwnContainer(Player p_155355_) 
		{
			if (!(p_155355_.containerMenu instanceof ChestMenu)) 
			{
				return false;
			} else 
			{
				Container container = ((ChestMenu)p_155355_.containerMenu).getContainer();
				return container == CoffretEntity.this || container instanceof CompoundContainer && ((CompoundContainer)container).contains(CoffretEntity.this);
			}
		}
	};
	private final ChestLidController chestLidController = new ChestLidController();

	public static void lidAnimateTick(Level p_155344_, BlockPos p_155345_, BlockState p_155346_, CoffretEntity p_155347_) 
	{
		p_155347_.chestLidController.tickLid();
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
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
		if (!this.remove && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (this.chestHandler == null)
				this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
			return this.chestHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	private net.minecraftforge.items.IItemHandlerModifiable createHandler() {
		BlockState state = this.getBlockState();
		if (!(state.getBlock() instanceof ChestBlock)) {
			return new net.minecraftforge.items.wrapper.InvWrapper(this);
		}
		Container inv = ChestBlock.getContainer((ChestBlock) state.getBlock(), state, getLevel(), getBlockPos(), true);
		return new net.minecraftforge.items.wrapper.InvWrapper(inv == null ? this : inv);
	}

	private CoffretEntity(BlockEntityType<?> p_155327_, BlockPos p_155328_, BlockState p_155329_) {
		super(p_155327_, p_155328_, p_155329_);
	}

	public CoffretEntity(BlockPos p_155331_, BlockState p_155332_) {
		this(TileentityTypes.COFFRET, p_155331_, p_155332_);
	}

	@Override
	public int getContainerSize() {
		return 27;
	}

	@Override
	protected Component getDefaultName() {
		return new TranslatableComponent("container.coffret");
	}

	static void playSound(Level p_155339_, BlockPos p_155340_, BlockState p_155341_, SoundEvent p_155342_) 
	{
		double d0 = (double)p_155340_.getX() + 0.5D;
		double d1 = (double)p_155340_.getY() + 0.5D;
		double d2 = (double)p_155340_.getZ() + 0.5D;
		p_155339_.playSound((Player)null, d0, d1, d2, p_155342_, SoundSource.BLOCKS, 0.5F, p_155339_.random.nextFloat() * 0.1F + 0.9F);

	}

	@Override
	public void load(CompoundTag p_230337_2_) {
		super.load(p_230337_2_);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(p_230337_2_)) {
			ContainerHelper.loadAllItems(p_230337_2_, this.items);
		}

	}

	@Override
	public CompoundTag save(CompoundTag p_189515_1_) {
		super.save(p_189515_1_);
		if (!this.trySaveLootTable(p_189515_1_)) {
			ContainerHelper.saveAllItems(p_189515_1_, this.items);
		}

		return p_189515_1_;
	}

	public static int getOpenCount(BlockGetter p_59087_, BlockPos p_59088_) {
		BlockState blockstate = p_59087_.getBlockState(p_59088_);
		if (blockstate.hasBlockEntity()) 
		{
			BlockEntity blockentity = p_59087_.getBlockEntity(p_59088_);
			if (blockentity instanceof CoffretEntity)
			{
				return ((CoffretEntity)blockentity).openersCounter.getOpenerCount();
			}
		}
		return 0;
	}

	@Override
	public boolean triggerEvent(int p_59114_, int p_59115_) 
	{
		if (p_59114_ == 1) 
		{
			this.chestLidController.shouldBeOpen(p_59115_ > 0);
			return true;
		} else 
		{
			return super.triggerEvent(p_59114_, p_59115_);
		}
	}

	@Override
	public void startOpen(Player p_59120_) 
	{
		if (!this.remove && !p_59120_.isSpectator()) 
		{
			this.openersCounter.incrementOpeners(p_59120_, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	@Override
	public void stopOpen(Player p_59118_) 
	{
		if (!this.remove && !p_59118_.isSpectator()) 
		{
			this.openersCounter.decrementOpeners(p_59118_, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	protected void signalOpenCount(Level p_155333_, BlockPos p_155334_, BlockState p_155335_, int p_155336_, int p_155337_) 
	{
		Block block = p_155335_.getBlock();
		p_155333_.blockEvent(p_155334_, block, 1, p_155337_);
	}

	@Override
	protected NonNullList<ItemStack> getItems() 
	{
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> p_199721_1_) 
	{
		this.items = p_199721_1_;
	}

	public void recheckOpen() 
	{
		if (!this.remove) 
		{
			this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}

	}

	public static void swapContents(CoffretEntity p_199722_0_, CoffretEntity p_199722_1_) 
	{
		NonNullList<ItemStack> nonnulllist = p_199722_0_.getItems();
		p_199722_0_.setItems(p_199722_1_.getItems());
		p_199722_1_.setItems(nonnulllist);
	}

	@Override
	protected void invalidateCaps() 
	{
		super.invalidateCaps();
		if (chestHandler != null)
			chestHandler.invalidate();
	}

	@Override
	public float getOpenNess(float p_59604_) 
	{
		return this.chestLidController.getOpenness(p_59604_);
	}

	@Override
	protected AbstractContainerMenu createMenu(int p_59082_, Inventory p_59083_) 
	{
		return ChestMenu.threeRows(p_59082_, p_59083_, this);
	}
}
