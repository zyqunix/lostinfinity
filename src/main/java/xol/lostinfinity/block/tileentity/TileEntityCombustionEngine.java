package xol.lostinfinity.block.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.misc.BlockCombustionEngine;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityCombustionEngine.class */
public class TileEntityCombustionEngine extends TileEntity implements IMachine, ITickable, IInventory {
    private NonNullList<ItemStack> inventory = NonNullList.func_191197_a(1, ItemStack.field_190927_a);
    private int burnTime;
    private int currentBurnTime;

    public String func_70005_c_() {
        return "tile.combustion_engine";
    }

    public boolean func_145818_k_() {
        return false;
    }

    private double getROD(int multi) {
        return ((-0.5d) + this.field_145850_b.field_73012_v.nextDouble()) * ((double) multi);
    }

    public void func_73660_a() {
        boolean burning = isBurning();
        boolean sync = false;
        if (isBurning()) {
            this.burnTime--;
        }
        if (!this.field_145850_b.field_72995_K) {
            ItemStack fuel = (ItemStack) this.inventory.get(0);
            if ((isBurning() || !fuel.func_190926_b()) && !isBurning() && canSmelt()) {
                this.burnTime = getItemBurnTime(fuel);
                this.currentBurnTime = this.burnTime;
                if (isBurning()) {
                    sync = true;
                    if (!fuel.func_190926_b()) {
                        Item item = fuel.func_77973_b();
                        fuel.func_190918_g(1);
                        if (fuel.func_190926_b()) {
                            ItemStack itemContainer = item.getContainerItem(fuel);
                            this.inventory.set(0, itemContainer);
                        }
                    }
                }
            }
            if (burning != isBurning()) {
                sync = true;
                BlockCombustionEngine.setState(isBurning(), this.field_145850_b, this.field_174879_c);
            }
        }
        if (sync) {
            func_70296_d();
        }
    }

    public int func_70302_i_() {
        return this.inventory.size();
    }

    public boolean func_191420_l() {
        for (ItemStack stack : this.inventory) {
            if (!stack.func_190926_b()) {
                return false;
            }
        }
        return true;
    }

    public ItemStack func_70301_a(int index) {
        return (ItemStack) this.inventory.get(index);
    }

    public ItemStack func_70298_a(int index, int count) {
        return ItemStackHelper.func_188382_a(this.inventory, index, count);
    }

    public ItemStack func_70304_b(int index) {
        return ItemStackHelper.func_188383_a(this.inventory, index);
    }

    public void func_70299_a(int index, ItemStack stack) {
        ItemStack itemstack = (ItemStack) this.inventory.get(index);
        this.inventory.set(index, stack);
        int limit = func_70297_j_();
        if (stack.func_190916_E() > limit) {
            stack.func_190920_e(limit);
        }
        boolean same = (!stack.func_190926_b() && stack.func_77969_a(itemstack) && ItemStack.func_77970_a(stack, itemstack)) ? false : true;
        if (index == 0 && same) {
            func_70296_d();
        }
    }

    private boolean canSmelt() {
        ItemStack fuel = (ItemStack) this.inventory.get(0);
        if (!fuel.func_190926_b()) {
            return true;
        }
        return false;
    }

    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        super.func_189515_b(compound);
        compound.func_74768_a("BurnTime", (short) this.burnTime);
        ItemStackHelper.func_191282_a(compound, this.inventory);
        return compound;
    }

    @Override // xol.lostinfinity.block.tileentity.IMachine
    public boolean getPowered() {
        return isBurning();
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.inventory = NonNullList.func_191197_a(func_70302_i_(), ItemStack.field_190927_a);
        ItemStackHelper.func_191283_b(compound, this.inventory);
        this.burnTime = compound.func_74762_e("BurnTime");
        this.currentBurnTime = getItemBurnTime((ItemStack) this.inventory.get(0));
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory) {
        return inventory.func_174887_a_(0) > 0;
    }

    private static int getItemBurnTime(ItemStack fuel) {
        if (!fuel.func_190926_b() && fuel.func_77973_b() == ItemInit.sparkPlug) {
            return 200;
        }
        return 0;
    }

    public static boolean isItemFuel(ItemStack fuel) {
        return getItemBurnTime(fuel) > 0;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer player) {
        return this.field_145850_b.func_175625_s(this.field_174879_c) == this && player.func_70092_e(((double) this.field_174879_c.func_177958_n()) + 0.5d, ((double) this.field_174879_c.func_177956_o()) + 0.5d, ((double) this.field_174879_c.func_177952_p()) + 0.5d) <= 64.0d;
    }

    public void func_174889_b(EntityPlayer player) {
    }

    public void func_174886_c(EntityPlayer player) {
    }

    public boolean func_94041_b(int index, ItemStack stack) {
        if (index >= 1) {
            return false;
        }
        if (index == 0) {
            return isItemFuel(stack);
        }
        return true;
    }

    public int getGuiID() {
        return GuiHandler.RegisteredGuis.COMBUSTION_ENGINE.getId();
    }

    public int func_174887_a_(int id) {
        switch (id) {
            case 0:
                return this.burnTime;
            case 1:
                return this.currentBurnTime;
            default:
                return 0;
        }
    }

    public void func_174885_b(int id, int value) {
        switch (id) {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.currentBurnTime = value;
                break;
        }
    }

    public int func_174890_g() {
        return 2;
    }

    public void func_174888_l() {
        this.inventory.clear();
    }
}
