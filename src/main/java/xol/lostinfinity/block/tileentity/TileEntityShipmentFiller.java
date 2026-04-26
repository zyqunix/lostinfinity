package xol.lostinfinity.block.tileentity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import xol.lostinfinity.item.misc.ItemShipmentBox;
import xol.lostinfinity.mob.entity.misc.EntitySupplyTrader;
public class TileEntityShipmentFiller extends TileEntity implements IInventory, ITickable {
    private NonNullList<ItemStack> inventory = NonNullList.func_191197_a(30, ItemStack.field_190927_a);
    public void func_73660_a() {
        for (int i = 2; i < this.inventory.size(); i++) {
            if (!((ItemStack) this.inventory.get(i)).func_190926_b()) {
                this.inventory.set(i, ItemStack.field_190927_a);
            }
        }
        ItemStack inputStack = (ItemStack) this.inventory.get(0);
        ItemStack boxStack = (ItemStack) this.inventory.get(1);
        Item boxItem = boxStack.func_77973_b();
        if (!(boxItem instanceof ItemShipmentBox)) {
            return;
        }
        if (!this.field_145850_b.field_72995_K && !boxStack.func_77942_o()) {
            boxStack.func_77982_d(new NBTTagCompound());
        }
        if (!inputStack.func_190926_b()) {
            int[] curIDs = boxStack.func_77978_p().func_74759_k("itemids");
            int[] newIDs = new int[curIDs.length + 1];
            for (int j = 0; j < curIDs.length; j++) {
                newIDs[j] = curIDs[j];
            }
            ItemStack[] shipmentItems = ItemShipmentBox.getItemArray();
            boolean found = false;
            int i2 = 0;
            while (true) {
                if (i2 >= shipmentItems.length) {
                    break;
                }
                if (!shipmentItems[i2].func_77969_a(inputStack)) {
                    i2++;
                } else {
                    newIDs[curIDs.length] = i2;
                    int oldWeight = boxStack.func_77978_p().func_74762_e("weight");
                    boxStack.func_77978_p().func_74768_a("weight", ItemShipmentBox.getItemWeight(inputStack.func_77973_b()) + oldWeight);
                    found = true;
                    break;
                }
            }
            if (found) {
                boxStack.func_77978_p().func_74783_a("itemids", newIDs);
            }
            func_70304_b(0);
        }
        int[] itemIds = boxStack.func_77978_p().func_74759_k("itemids");
        for (int i3 = 0; i3 < itemIds.length; i3++) {
            ItemStack stack = ItemShipmentBox.getItemArray()[itemIds[i3]];
            if (!stack.func_190926_b() && i3 + 2 < this.inventory.size() && ((ItemStack) this.inventory.get(i3 + 2)).func_190926_b()) {
                this.inventory.set(i3 + 2, stack);
            }
        }
    }
    public int func_174887_a_(int id) {
        return 0;
    }
    public void func_174885_b(int id, int value) {
    }
    public int func_174890_g() {
        return 0;
    }
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        ItemStackHelper.func_191283_b(compound, this.inventory);
    }
    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        this.inventory.set(0, ItemStack.field_190927_a);
        for (int i = 2; i < this.inventory.size(); i++) {
            this.inventory.set(i, ItemStack.field_190927_a);
        }
        ItemStackHelper.func_191282_a(compound, this.inventory);
        return super.func_189515_b(compound);
    }
    public int func_70302_i_() {
        return this.inventory.size();
    }
    public boolean func_191420_l() {
        return this.inventory.isEmpty();
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
        return 1;
    }
    public boolean func_70300_a(EntityPlayer player) {
        return this.field_145850_b.func_175625_s(this.field_174879_c) == this && player.func_70092_e(((double) this.field_174879_c.func_177958_n()) + 0.5d, ((double) this.field_174879_c.func_177956_o()) + 0.5d, ((double) this.field_174879_c.func_177952_p()) + 0.5d) <= 64.0d;
    }
    public void func_174889_b(EntityPlayer player) {
    }
    public void func_174886_c(EntityPlayer player) {
    }
    public boolean func_94041_b(int index, ItemStack stack) {
        switch (index) {
            case 0:
                if (EntitySupplyTrader.SupplyTraderRecipe.isItemInRecipe(stack.func_77973_b())) {
                }
                break;
            case 1:
                if (stack.func_77973_b() instanceof ItemShipmentBox) {
                }
                break;
        }
        return true;
    }
    public void func_174888_l() {
    }
    public String func_70005_c_() {
        return "tile.shipment_filler";
    }
    public boolean func_145818_k_() {
        return false;
    }
    public void dropInventory() {
        this.inventory.set(0, ItemStack.field_190927_a);
        for (int i = 2; i < this.inventory.size(); i++) {
            this.inventory.set(i, ItemStack.field_190927_a);
        }
        InventoryHelper.func_180175_a(this.field_145850_b, this.field_174879_c, this);
    }
}
