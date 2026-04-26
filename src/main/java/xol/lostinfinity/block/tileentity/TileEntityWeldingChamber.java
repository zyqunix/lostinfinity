package xol.lostinfinity.block.tileentity;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import xol.lostinfinity.block.crafting.BlockWeldingChamber;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class TileEntityWeldingChamber extends TileEntity implements IInventory, ITickable {
    private int currentSmeltTime;
    private NonNullList<ItemStack> inventory = NonNullList.func_191197_a(2, ItemStack.field_190927_a);
    private final int smeltTime = 300;
    private final int maxHeat = 6000;
    private int heat = 3000;
    private int acetylene = 4;
    public void func_73660_a() {
        ItemStack input = (ItemStack) this.inventory.get(0);
        int inputCount = input.func_190916_E();
        if (input.func_77973_b().func_77658_a().equalsIgnoreCase("item.atomite_fragments") && inputCount >= 5 && ((ItemStack) this.inventory.get(1)).func_190916_E() <= 64) {
            BlockWeldingChamber.setState(true, this.field_145850_b, this.field_174879_c);
            this.currentSmeltTime = func_174887_a_(0) + 1;
            if (calculateHeat(input, inputCount) && this.currentSmeltTime == 300) {
                fuseAtomite(input, inputCount);
                return;
            }
            return;
        }
        BlockWeldingChamber.setState(false, this.field_145850_b, this.field_174879_c);
        this.currentSmeltTime = 0;
        if (this.heat != 3000) {
            cool();
        }
    }
    private boolean calculateHeat(ItemStack input, int inputCount) {
        int iNextInt;
        int targetAcetyleneLevel = 0;
        int multiplier = 0;
        if (ThreadLocalRandom.current().nextInt(2) == 0) {
            iNextInt = -(ThreadLocalRandom.current().nextInt(50) + 1);
        } else {
            iNextInt = ThreadLocalRandom.current().nextInt(50) + 1;
        }
        int offset = iNextInt;
        int i = this.heat;
        getClass();
        if (i >= 6000 || this.heat <= 0) {
            failFuse(input, inputCount);
            return false;
        }
        if (this.currentSmeltTime / 300.0f <= 0.2d) {
            targetAcetyleneLevel = 1;
            multiplier = 20;
        } else if (this.currentSmeltTime / 300.0f <= 0.4d) {
            targetAcetyleneLevel = 4;
            multiplier = 30;
        } else if (this.currentSmeltTime / 300.0f <= 0.6d) {
            targetAcetyleneLevel = 2;
            multiplier = 30;
        } else if (this.currentSmeltTime / 300.0f <= 0.8d) {
            targetAcetyleneLevel = 6;
            multiplier = 25;
        } else if (this.currentSmeltTime / 300.0f <= 1.0d) {
            targetAcetyleneLevel = 2;
            multiplier = 25;
        }
        int diff = this.acetylene - targetAcetyleneLevel;
        this.heat += (diff * multiplier) + offset;
        return true;
    }
    private void failFuse(ItemStack input, int inputCount) {
        this.currentSmeltTime = 0;
        this.heat = 3000;
        this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.GENERIC_UI_4, SoundCategory.BLOCKS, 1.0f, 1.0f);
        input.func_190920_e(inputCount - 5);
        func_70296_d();
    }
    private void cool() {
        if (this.heat == 2999) {
            this.heat++;
            return;
        }
        if (this.heat == 3001) {
            this.heat--;
        } else if (this.heat > 3000) {
            this.heat -= 4;
        } else if (this.heat < 3000) {
            this.heat += 4;
        }
    }
    private void fuseAtomite(ItemStack input, int inputCount) {
        ItemStack output;
        func_174885_b(0, 0);
        if (((ItemStack) this.inventory.get(1)).func_190926_b()) {
            output = new ItemStack(ItemInit.weldedAtomite, 1);
        } else {
            output = (ItemStack) this.inventory.get(1);
            output.func_190920_e(output.func_190916_E() + 1);
        }
        this.inventory.set(1, output);
        input.func_190920_e(inputCount - 5);
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
    public int func_174887_a_(int id) {
        switch (id) {
            case 0:
                return this.currentSmeltTime;
            case 1:
                return this.heat;
            case 2:
                return this.acetylene;
            default:
                return 0;
        }
    }
    public void func_174885_b(int id, int value) {
        switch (id) {
            case 0:
                this.currentSmeltTime = value;
                break;
            case 1:
                this.heat = value;
                break;
            case 2:
                if (value < 0) {
                    value = 0;
                } else if (value > 7) {
                    value = 7;
                }
                this.acetylene = value;
                break;
        }
    }
    public int func_174890_g() {
        return 3;
    }
    public void func_174888_l() {
        this.inventory.clear();
    }
    public String func_70005_c_() {
        return "tile.welding_chamber";
    }
    public boolean func_145818_k_() {
        return false;
    }
    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        super.func_189515_b(compound);
        compound.func_74768_a("currentSmeltTime", this.currentSmeltTime);
        compound.func_74768_a("heat", this.heat);
        compound.func_74768_a("acetylene", this.acetylene);
        ItemStackHelper.func_191282_a(compound, this.inventory);
        return compound;
    }
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.inventory = NonNullList.func_191197_a(func_70302_i_(), ItemStack.field_190927_a);
        ItemStackHelper.func_191283_b(compound, this.inventory);
        this.currentSmeltTime = compound.func_74762_e("currentSmeltTime");
        this.heat = compound.func_74762_e("heat");
        this.acetylene = compound.func_74762_e("acetylene");
    }
    public int getSmeltTime() {
        getClass();
        return 300;
    }
    public int getMaxHeat() {
        getClass();
        return 6000;
    }
    public int getGuiID() {
        return GuiHandler.RegisteredGuis.WELDING_CHAMBER.getId();
    }
}
