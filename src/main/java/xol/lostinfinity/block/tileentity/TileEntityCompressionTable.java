package xol.lostinfinity.block.tileentity;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.starforge.EntityDusker;
public class TileEntityCompressionTable extends TileEntity implements IInventory, ITickable {
    public NonNullList<ItemStack> compressionItemStacks;
    private int progress = -1;
    private static final byte PROGRESS = 0;
    public TileEntityCompressionTable() {
        this.compressionItemStacks = NonNullList.func_191197_a(3, ItemStack.field_190927_a);
        this.compressionItemStacks = NonNullList.func_191197_a(3, ItemStack.field_190927_a);
        func_174888_l();
    }
    public void func_145839_a(NBTTagCompound compound) {
        NBTTagList dataForSlots = compound.func_150295_c("Items", 10);
        this.compressionItemStacks = NonNullList.func_191197_a(3, ItemStack.field_190927_a);
        for (int i = PROGRESS; i < dataForSlots.func_74745_c(); i++) {
            NBTTagCompound dataForSlot = dataForSlots.func_150305_b(i);
            byte slotNumber = dataForSlot.func_74771_c("Slot");
            if (slotNumber >= 0 && slotNumber < this.compressionItemStacks.size()) {
                this.compressionItemStacks.set(slotNumber, new ItemStack(dataForSlot));
            }
        }
        this.progress = compound.func_74762_e("Progress");
        super.func_145839_a(compound);
    }
    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        NBTTagList dataForSlots = new NBTTagList();
        for (int i = PROGRESS; i < this.compressionItemStacks.size(); i++) {
            if (!((ItemStack) this.compressionItemStacks.get(i)).func_190926_b()) {
                NBTTagCompound dataForSlot = new NBTTagCompound();
                dataForSlot.func_74774_a("Slot", (byte) i);
                ((ItemStack) this.compressionItemStacks.get(i)).func_77955_b(dataForSlot);
                dataForSlots.func_74742_a(dataForSlot);
            }
        }
        compound.func_74782_a("Items", dataForSlots);
        compound.func_74768_a("Progress", this.progress);
        return super.func_189515_b(compound);
    }
    public int updateProgress(int increment) {
        this.progress = Math.min(this.progress + increment, 500);
        func_70296_d();
        return this.progress;
    }
    public void updateSlot(int index, ItemStack itemStack) {
        this.compressionItemStacks.set(index, itemStack);
        func_70296_d();
    }
    public int getProgress() {
        return this.progress;
    }
    public int setProgress(int newValue) {
        this.progress = newValue;
        func_70296_d();
        return this.progress;
    }
    public double getProgressAsFraction() {
        return MathHelper.func_151237_a(getProgress() >= 0 ? ((double) getProgress()) / 500.0d : 0.0d, 0.0d, 1.0d);
    }
    public ItemStack getResult() {
        return (ItemStack) this.compressionItemStacks.get(2);
    }
    public void func_73660_a() {
        boolean flag = this.progress > -1;
        boolean flag1 = PROGRESS;
        if (flag) {
            this.progress++;
        }
        if (!this.field_145850_b.field_72995_K) {
            ItemStack itemstack = (ItemStack) this.compressionItemStacks.get(PROGRESS);
            if ((flag || !itemstack.func_190926_b()) && canProgress()) {
                if (this.progress % 100 == 0 && this.progress < 500) {
                    EntityDusker dusker = new EntityDusker(this.field_145850_b);
                    dusker.func_70107_b(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() + 13, this.field_174879_c.func_177952_p());
                    this.field_145850_b.func_72838_d(dusker);
                }
                if (this.progress == -1 && canProgress()) {
                    this.progress = PROGRESS;
                    this.compressionItemStacks.set(2, itemstack.func_77946_l());
                    flag1 = true;
                }
                if (this.progress >= 500 && canProgress()) {
                    compressItem();
                    this.progress = -1;
                    flag1 = true;
                }
            }
            if (!canProgress()) {
                if (this.progress != -1) {
                    this.progress = -1;
                }
                flag1 = true;
            }
        }
        if (flag1) {
            func_70296_d();
        }
    }
    public ItemStack getResultFor(ItemStack iteminput1) {
        ItemStack result = ItemStack.field_190927_a;
        if (iteminput1.func_77973_b().equals(ItemInit.astralliumIngot)) {
            result = new ItemStack(ItemInit.astralliumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.crystoniumIngot)) {
            result = new ItemStack(ItemInit.crystoniumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.detheriumIngot)) {
            result = new ItemStack(ItemInit.detheriumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.emberiumIngot)) {
            result = new ItemStack(ItemInit.emberiumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.hextoriumIngot)) {
            result = new ItemStack(ItemInit.hextoriumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.incadiumIngot)) {
            result = new ItemStack(ItemInit.incadiumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.kylaxiumIngot)) {
            result = new ItemStack(ItemInit.kylaxiumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.noxeriumIngot)) {
            result = new ItemStack(ItemInit.noxeriumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.olysiumIngot)) {
            result = new ItemStack(ItemInit.olysiumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.velloriumIngot)) {
            result = new ItemStack(ItemInit.velloriumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.xeroviumIngot)) {
            result = new ItemStack(ItemInit.xeroviumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.phytrosiumIngot)) {
            result = new ItemStack(ItemInit.phytrosiumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.kyvoriumIngot)) {
            result = new ItemStack(ItemInit.kyvoriumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.biosynthiumIngot)) {
            result = new ItemStack(ItemInit.biosynthiumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.maliciumIngot)) {
            result = new ItemStack(ItemInit.maliciumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.etheriumIngot)) {
            result = new ItemStack(ItemInit.etheriumCondensed);
        } else if (iteminput1.func_77973_b().equals(ItemInit.polariumIngot)) {
            result = new ItemStack(ItemInit.polariumCondensed);
        }
        return result;
    }
    private void compressItem() {
        ItemStack result = getResultFor((ItemStack) this.compressionItemStacks.get(PROGRESS));
        this.compressionItemStacks.set(2, ItemStack.field_190927_a);
        ItemStack output = (ItemStack) this.compressionItemStacks.get(1);
        boolean yes = true;
        if (output.func_190926_b()) {
            this.compressionItemStacks.set(1, result.func_77946_l());
        } else if (output.func_77973_b() == result.func_77973_b()) {
            output.func_190917_f(1);
        } else {
            yes = PROGRESS;
        }
        if (yes) {
            ((ItemStack) this.compressionItemStacks.get(PROGRESS)).func_190918_g(25);
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_175718_b(1030, this.field_174879_c, PROGRESS);
            }
        }
    }
    public boolean canProgress() {
        return !((ItemStack) this.compressionItemStacks.get(PROGRESS)).func_190926_b() && ((ItemStack) this.compressionItemStacks.get(PROGRESS)).func_190916_E() >= 25;
    }
    @Nullable
    public SPacketUpdateTileEntity func_189518_D_() {
        NBTTagCompound updateTag = func_189517_E_();
        return new SPacketUpdateTileEntity(this.field_174879_c, PROGRESS, updateTag);
    }
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        NBTTagCompound updateTag = packet.func_148857_g();
        handleUpdateTag(updateTag);
    }
    public NBTTagCompound func_189517_E_() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        func_189515_b(nbtTagCompound);
        return nbtTagCompound;
    }
    public void handleUpdateTag(NBTTagCompound tag) {
        func_145839_a(tag);
    }
    public int func_174887_a_(int id) {
        if (id == 0) {
            return this.progress;
        }
        System.err.println("Invalid field ID in TileEntityCompressionTable#getField:" + id);
        return PROGRESS;
    }
    public void func_174885_b(int id, int value) {
        if (id == 0) {
            this.progress = value;
        } else {
            System.err.println("Invalid field ID in TileEntityCompressionTable#setField:" + id);
        }
    }
    public int func_174890_g() {
        return 1;
    }
    public void func_174888_l() {
        this.compressionItemStacks = NonNullList.func_191197_a(3, ItemStack.field_190927_a);
    }
    public boolean func_94041_b(int slotIndex, ItemStack itemstack) {
        return true;
    }
    public int func_70302_i_() {
        return this.compressionItemStacks.size();
    }
    public boolean func_191420_l() {
        return this.compressionItemStacks.stream().allMatch(it -> {
            return it.func_190926_b();
        });
    }
    public ItemStack func_70301_a(int index) {
        return (ItemStack) this.compressionItemStacks.get(index);
    }
    public ItemStack func_70298_a(int index, int count) {
        return ItemStackHelper.func_188382_a(this.compressionItemStacks, index, count);
    }
    public ItemStack func_70304_b(int index) {
        return ItemStackHelper.func_188383_a(this.compressionItemStacks, index);
    }
    public void func_70299_a(int index, ItemStack stack) {
        ItemStack itemstack = (ItemStack) this.compressionItemStacks.get(index);
        boolean flag = !stack.func_190926_b() && stack.func_77969_a(itemstack) && ItemStack.func_77970_a(stack, itemstack);
        this.compressionItemStacks.set(index, stack);
        if (stack.func_190916_E() > func_70297_j_()) {
            stack.func_190920_e(func_70297_j_());
        }
        if (index == 0 && !flag) {
            this.progress = PROGRESS;
            func_70296_d();
        }
    }
    public int func_70297_j_() {
        return 64;
    }
    public boolean func_70300_a(EntityPlayer player) {
        return this.field_145850_b.func_175625_s(this.field_174879_c) == this && player.func_70092_e(((double) this.field_174879_c.func_177958_n()) + 0.5d, ((double) this.field_174879_c.func_177956_o()) + 0.5d, ((double) this.field_174879_c.func_177952_p()) + 0.5d) < 64.0d;
    }
    public void func_174889_b(EntityPlayer player) {
    }
    public void func_174886_c(EntityPlayer player) {
    }
    public String func_70005_c_() {
        return null;
    }
    public boolean func_145818_k_() {
        return false;
    }
}
