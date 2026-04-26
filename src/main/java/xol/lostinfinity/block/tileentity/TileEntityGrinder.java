package xol.lostinfinity.block.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import xol.lostinfinity.block.crafting.BlockGrinder;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityGrinder.class */
public class TileEntityGrinder extends TileEntity implements IInventory, ITickable, IMachine {
    private NonNullList<ItemStack> inventory = NonNullList.func_191197_a(2, ItemStack.field_190927_a);
    private int failTimer = 0;

    public void func_73660_a() {
        ItemStack input = (ItemStack) this.inventory.get(0);
        int inputCount = input.func_190916_E();
        if (input.func_77973_b().func_77658_a().equalsIgnoreCase("item.sunstone") && inputCount >= 5 && ((ItemStack) this.inventory.get(1)).func_190916_E() <= 64 && getPowered()) {
            if (!this.field_145850_b.field_72995_K) {
                BlockGrinder.setState(true, this.field_145850_b, this.field_174879_c);
            }
        } else if (!this.field_145850_b.field_72995_K) {
            BlockGrinder.setState(false, this.field_145850_b, this.field_174879_c);
        }
    }

    @Override // xol.lostinfinity.block.tileentity.IMachine
    public boolean getPowered() {
        for (IMachine machine : getConnectedMachines(this, null)) {
            if (machine instanceof TileEntityGearbox) {
                return ((TileEntityGearbox) machine).getActive();
            }
        }
        return false;
    }

    public void failGrind() {
        if (this.failTimer > 0) {
            this.failTimer--;
            return;
        }
        this.failTimer = 30;
        ItemStack input = (ItemStack) this.inventory.get(0);
        int inputCount = input.func_190916_E();
        this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.GENERIC_UI_4, SoundCategory.BLOCKS, 1.0f, 1.0f);
        input.func_190920_e(inputCount - 1);
        func_70296_d();
    }

    public void grind() {
        ItemStack output;
        ItemStack input = (ItemStack) this.inventory.get(0);
        int inputCount = input.func_190916_E();
        func_174885_b(0, 0);
        if (((ItemStack) this.inventory.get(1)).func_190926_b()) {
            output = new ItemStack(ItemInit.sunstoneDust, 1);
        } else {
            output = (ItemStack) this.inventory.get(1);
            output.func_190920_e(output.func_190916_E() + 1);
        }
        this.inventory.set(1, output);
        input.func_190920_e(inputCount - 1);
        this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.WELDING, SoundCategory.BLOCKS, 1.0f, 3.0f);
        func_70296_d();
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
        this.inventory.set(index, stack);
        int limit = func_70297_j_();
        if (stack.func_190916_E() > limit) {
            stack.func_190920_e(limit);
        }
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
        return false;
    }

    public void func_174888_l() {
        this.inventory.clear();
    }

    public String func_70005_c_() {
        return "tile.grinder";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        super.func_189515_b(compound);
        ItemStackHelper.func_191282_a(compound, this.inventory);
        return compound;
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.inventory = NonNullList.func_191197_a(func_70302_i_(), ItemStack.field_190927_a);
        ItemStackHelper.func_191283_b(compound, this.inventory);
    }

    public int getGuiID() {
        return GuiHandler.RegisteredGuis.GRINDER.getId();
    }

    public int func_174887_a_(int id) {
        return 0;
    }

    public void func_174885_b(int id, int value) {
    }

    public int func_174890_g() {
        return 0;
    }
}
