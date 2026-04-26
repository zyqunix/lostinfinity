package xol.lostinfinity.block.tileentity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.crafting.BlockSapEvaporator;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.starforge.EntityGrubber;
import xol.lostinfinity.recipes.SapEvaporatorRecipes;
public class TileEntitySapEvaporator extends TileEntity implements IInventory, ITickable {
    private NonNullList<ItemStack> inventory = NonNullList.func_191197_a(3, ItemStack.field_190927_a);
    private int burnTime;
    private int currentBurnTime;
    private int cookTime;
    private int totalCookTime;
    private boolean setBurning;
    public String func_70005_c_() {
        return "tile.sap_evaporator";
    }
    public boolean func_145818_k_() {
        return false;
    }
    public void func_73660_a() {
        boolean burning = isBurning();
        boolean sync = false;
        if (isBurning()) {
            this.burnTime--;
            if (!this.field_145850_b.field_72995_K) {
                if (this.burnTime % 4 == 0) {
                    AxisAlignedBB aabb = new AxisAlignedBB(this.field_174879_c.func_177982_a(-25, 0, -25), this.field_174879_c.func_177982_a(25, 15, 25));
                    for (EntityGrubber grubber : this.field_145850_b.func_72872_a(EntityGrubber.class, aabb)) {
                        grubber.setAwake(true);
                    }
                }
                int burnOffset = this.burnTime % 40;
                if (burnOffset <= 5) {
                    this.field_145850_b.func_175739_a(EnumParticleTypes.SMOKE_LARGE, ((double) this.field_174879_c.func_177958_n()) + 0.5d, ((this.field_174879_c.func_177956_o() + 1) + 5) - burnOffset, ((double) this.field_174879_c.func_177952_p()) + 0.5d, 5, 0.0d, 0.0d, 0.0d, 0.05000000596046448d, new int[0]);
                    if (burnOffset == 5) {
                        this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.ELECTRIC_WOOSH, SoundCategory.BLOCKS, 1.0f, 0.8f + (0.4f * this.field_145850_b.field_73012_v.nextFloat()));
                    }
                }
            }
        }
        if (!this.field_145850_b.field_72995_K) {
            ItemStack input = (ItemStack) this.inventory.get(0);
            ItemStack fuel = (ItemStack) this.inventory.get(1);
            if (isBurning() || (!fuel.func_190926_b() && !input.func_190926_b())) {
                if (!isBurning() && canSmelt()) {
                    this.burnTime = getItemBurnTime(fuel);
                    this.currentBurnTime = this.burnTime;
                    if (isBurning()) {
                        sync = true;
                        if (!fuel.func_190926_b()) {
                            Item item = fuel.func_77973_b();
                            fuel.func_190918_g(1);
                            if (fuel.func_190926_b()) {
                                ItemStack itemContainer = item.getContainerItem(fuel);
                                this.inventory.set(1, itemContainer);
                            }
                        }
                    }
                }
                if (isBurning() && canSmelt()) {
                    this.cookTime++;
                    if (this.cookTime >= this.totalCookTime) {
                        this.cookTime = 0;
                        this.totalCookTime = getCookTime((ItemStack) this.inventory.get(0));
                        smeltItem();
                        sync = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.func_76125_a(this.cookTime - 2, 0, this.totalCookTime);
            }
            if (burning != isBurning()) {
                sync = true;
                BlockSapEvaporator.setState(isBurning(), this.field_145850_b, this.field_174879_c);
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
            this.totalCookTime = getCookTime(stack);
            this.cookTime = 0;
            func_70296_d();
        }
    }
    private boolean canSmelt() {
        ItemStack result;
        int count;
        ItemStack input = (ItemStack) this.inventory.get(0);
        ItemStack fuel = (ItemStack) this.inventory.get(1);
        if (input.func_190926_b() || fuel.func_190926_b() || (result = SapEvaporatorRecipes.getResult(input)) == null || result.func_190926_b()) {
            return false;
        }
        ItemStack output = (ItemStack) this.inventory.get(2);
        if (output.func_190926_b()) {
            return true;
        }
        return output.func_77969_a(result) && (count = output.func_190916_E() + result.func_190916_E()) <= func_70297_j_() && count <= output.func_77976_d();
    }
    public void smeltItem() {
        if (canSmelt()) {
            ItemStack input = (ItemStack) this.inventory.get(0);
            ItemStack result = SapEvaporatorRecipes.getResult(input);
            ItemStack output = (ItemStack) this.inventory.get(2);
            if (output.func_190926_b()) {
                this.inventory.set(2, result.func_77946_l());
            } else if (output.func_77973_b() == result.func_77973_b()) {
                output.func_190917_f(result.func_190916_E());
            }
            input.func_190918_g(1);
        }
    }
    private int getCookTime(ItemStack stack) {
        return 100;
    }
    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        super.func_189515_b(compound);
        compound.func_74768_a("BurnTime", (short) this.burnTime);
        compound.func_74768_a("CookTime", (short) this.cookTime);
        compound.func_74768_a("TotalCookTime", (short) this.totalCookTime);
        ItemStackHelper.func_191282_a(compound, this.inventory);
        return compound;
    }
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.inventory = NonNullList.func_191197_a(func_70302_i_(), ItemStack.field_190927_a);
        ItemStackHelper.func_191283_b(compound, this.inventory);
        this.burnTime = compound.func_74762_e("BurnTime");
        this.cookTime = compound.func_74762_e("CookTime");
        this.totalCookTime = compound.func_74762_e("TotalCookTime");
        this.currentBurnTime = getItemBurnTime((ItemStack) this.inventory.get(1));
        this.setBurning = true;
    }
    public boolean isBurning() {
        return this.burnTime > 0;
    }
    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory) {
        return inventory.func_174887_a_(0) > 0;
    }
    private static int getItemBurnTime(ItemStack fuel) {
        if (!fuel.func_190926_b() && fuel.func_77973_b() == ItemInit.pyreLog) {
            return 500;
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
        if (index >= 2) {
            return false;
        }
        if (index == 2) {
            return isItemFuel(stack);
        }
        return true;
    }
    public int getGuiID() {
        return GuiHandler.RegisteredGuis.SAP_EVAPORATOR.getId();
    }
    public int func_174887_a_(int id) {
        switch (id) {
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
    public void func_174885_b(int id, int value) {
        switch (id) {
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
                break;
        }
    }
    public int func_174890_g() {
        return 4;
    }
    public void func_174888_l() {
        this.inventory.clear();
    }
}
