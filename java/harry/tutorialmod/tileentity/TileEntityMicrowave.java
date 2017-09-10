package harry.tutorialmod.tileentity;

import harry.tutorialmod.Reference;
import harry.tutorialmod.container.ContainerMicrowave;
import harry.tutorialmod.init.blocks.CustomBlockMicrowave;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityMicrowave extends TileEntityLockable implements ITickable, ISidedInventory
{
	private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {2, 1};
    private static final int[] SLOTS_SIDES = new int[] {1};
    
    private NonNullList<ItemStack> microwaveStacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    private int burnTime;
    private int currentBurnTime;
    private int cookTime;
    private int totalCookTime;
    protected String customName;

    public String getCustomName() 
    {
		return customName;
	}
    
    public void setCustomName(String customName) 
    {
		this.customName = customName;
	}
    
    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.microwave";
    }
    
    @Override
    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }
    
    @Override
    public int getSizeInventory()
    {
        return this.microwaveStacks.size();
    }
    
    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.microwaveStacks.get(index);
    }
    
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.microwaveStacks, index, count);
    }

    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.microwaveStacks, index);
    }
    
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = this.microwaveStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.microwaveStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());

        if (index == 0 && !flag)
        {
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
    }
    
    public int getInventoryStackLimit()
    {
        return 64;
    }
    
    public boolean isUsableByPlayer(EntityPlayer player)
    {
    	return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }
    
    public void openInventory(EntityPlayer player)
    {
    }

    public void closeInventory(EntityPlayer player)
    {
    }
    
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        if (index == 2)
        {
            return false;
        }
        else if (index != 1)
        {
            return true;
        }
        else
        {
            ItemStack itemstack = this.microwaveStacks.get(1);
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && itemstack.getItem() != Items.BUCKET;
        }
    }
    
    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.burnTime;
            case 1:
                return this.currentBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.currentBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }
    
    public int getFieldCount()
    {
        return 4;
    }

    public void clear()
    {
        this.microwaveStacks.clear();
    }
    
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {	
		super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.burnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.microwaveStacks);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }
    
    public void readFromNBT(NBTTagCompound compound)
    {	
        super.readFromNBT(compound);
        this.microwaveStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.microwaveStacks);
        this.burnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentBurnTime = getItemBurnTime(this.microwaveStacks.get(1));
        
        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }
    
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.microwaveStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    public static void registerFixesFurnace(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityMicrowave.class, new String[] {"Items"}));
    }

    public boolean isBurning()
    {
        return this.burnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

    public void update()
    {	
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.burnTime;
        }

        if (!this.world.isRemote)
        {
            ItemStack itemstack = this.microwaveStacks.get(1);

            if (this.isBurning() || !itemstack.isEmpty() && !((ItemStack)this.microwaveStacks.get(0)).isEmpty())
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.burnTime = getItemBurnTime(itemstack);
                    this.currentBurnTime = this.burnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.microwaveStacks.set(1, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.microwaveStacks.get(0));
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                CustomBlockMicrowave.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    public int getCookTime(ItemStack stack)
    {
    	Item item = stack.getItem();
    	
    	if(item == Items.BEEF || item == Items.PORKCHOP || item == Items.MUTTON || item == Items.CHICKEN || item == Items.RABBIT)
    	{
    		return 50;
    	}
    	else
    	{
    		return 200;
    	}
    }

    private boolean canSmelt()
    {
        if (((ItemStack)this.microwaveStacks.get(0)).isEmpty())
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.microwaveStacks.get(0));

            if (itemstack.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack itemstack1 = this.microwaveStacks.get(2);

                if (itemstack1.isEmpty())
                {
                    return true;
                }
                else if (!itemstack1.isItemEqual(itemstack))
                {
                    return false;
                }
                else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize())  // Forge fix: make furnace respect stack sizes in furnace recipes
                {
                    return true;
                }
                else
                {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = this.microwaveStacks.get(0);
            ItemStack itemstack1 = FurnaceRecipes.instance().getSmeltingResult(itemstack);
            ItemStack itemstack2 = this.microwaveStacks.get(2);

            if (itemstack2.isEmpty())
            {
                this.microwaveStacks.set(2, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(itemstack1.getCount());
            }

            if (itemstack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && itemstack.getMetadata() == 1 && !((ItemStack)this.microwaveStacks.get(1)).isEmpty() && ((ItemStack)this.microwaveStacks.get(1)).getItem() == Items.BUCKET)
            {
                this.microwaveStacks.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
        }
    }

    public static int getItemBurnTime(ItemStack stack)
    {
    	if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();
            
            if (item == Items.BLAZE_ROD)
            {
            	return 2400;
            }
            else
            {
                return 100;
            }
        }
    }

    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        if (side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        else
        {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }

    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }

        return true;
    }

    public String getGuiID()
    {
        return Reference.MODID + ":microwave";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerMicrowave(playerInventory, this);
    }
}
