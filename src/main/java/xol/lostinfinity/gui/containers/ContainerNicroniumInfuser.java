package xol.lostinfinity.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityNicroniumInfuser;
import xol.lostinfinity.gui.slots.SlotNicroniumInfuserFuel;
import xol.lostinfinity.gui.slots.SlotNicroniumInfuserOutput;
import xol.lostinfinity.recipes.NicroniumInfuserRecipes;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerNicroniumInfuser.class */
public class ContainerNicroniumInfuser extends Container {
    private final TileEntityNicroniumInfuser tileentity;
    private int cookTime;
    private int totalCookTime;
    private int burnTime;
    private int currentBurnTime;

    public ContainerNicroniumInfuser(InventoryPlayer player, TileEntityNicroniumInfuser tileEntityNicroniumInfuser) {
        this.tileentity = tileEntityNicroniumInfuser;
        this.tileentity.setPlacer(player.field_70458_d);
        func_75146_a(new Slot(tileEntityNicroniumInfuser, 0, 17, 21));
        func_75146_a(new SlotNicroniumInfuserFuel(tileEntityNicroniumInfuser, 1, 17, 57));
        func_75146_a(new SlotNicroniumInfuserOutput(player.field_70458_d, tileEntityNicroniumInfuser, 2, 98, 39));
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                func_75146_a(new Slot(player, x + (y * 9) + 9, 8 + (x * 18), 84 + (y * 18)));
            }
        }
        for (int x2 = 0; x2 < 9; x2++) {
            func_75146_a(new Slot(player, x2, 8 + (x2 * 18), 142));
        }
    }

    public void func_75132_a(IContainerListener listener) {
        super.func_75132_a(listener);
        listener.func_175173_a(this, this.tileentity);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            IContainerListener icontainerlistener = (IContainerListener) this.field_75149_d.get(i);
            if (this.cookTime != this.tileentity.func_174887_a_(2)) {
                icontainerlistener.func_71112_a(this, 2, this.tileentity.func_174887_a_(2));
            }
            if (this.burnTime != this.tileentity.func_174887_a_(0)) {
                icontainerlistener.func_71112_a(this, 0, this.tileentity.func_174887_a_(0));
            }
            if (this.currentBurnTime != this.tileentity.func_174887_a_(1)) {
                icontainerlistener.func_71112_a(this, 1, this.tileentity.func_174887_a_(1));
            }
            if (this.totalCookTime != this.tileentity.func_174887_a_(3)) {
                icontainerlistener.func_71112_a(this, 3, this.tileentity.func_174887_a_(3));
            }
        }
        this.cookTime = this.tileentity.func_174887_a_(2);
        this.burnTime = this.tileentity.func_174887_a_(0);
        this.currentBurnTime = this.tileentity.func_174887_a_(1);
        this.totalCookTime = this.tileentity.func_174887_a_(3);
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int id, int data) {
        this.tileentity.func_174885_b(id, data);
    }

    public boolean func_75145_c(EntityPlayer playerIn) {
        return this.tileentity.func_70300_a(playerIn);
    }

    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = (Slot) this.field_75151_b.get(index);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (index == 2) {
                if (!func_75135_a(itemstack1, 3, 39, true)) {
                    return ItemStack.field_190927_a;
                }
                slot.func_75220_a(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (NicroniumInfuserRecipes.getResult(itemstack1) != null && !NicroniumInfuserRecipes.getResult(itemstack1).func_190926_b()) {
                    if (!func_75135_a(itemstack1, 0, 1, false)) {
                        return ItemStack.field_190927_a;
                    }
                } else if (TileEntityNicroniumInfuser.isItemFuel(itemstack1)) {
                    if (!func_75135_a(itemstack1, 1, 2, false)) {
                        return ItemStack.field_190927_a;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!func_75135_a(itemstack1, 30, 39, false)) {
                        return ItemStack.field_190927_a;
                    }
                } else if (index >= 30 && index < 39 && !func_75135_a(itemstack1, 3, 30, false)) {
                    return ItemStack.field_190927_a;
                }
            } else if (!func_75135_a(itemstack1, 3, 39, false)) {
                return ItemStack.field_190927_a;
            }
            if (itemstack1.func_190926_b()) {
                slot.func_75215_d(ItemStack.field_190927_a);
            } else {
                slot.func_75218_e();
            }
            if (itemstack1.func_190916_E() == itemstack.func_190916_E()) {
                return ItemStack.field_190927_a;
            }
            slot.func_190901_a(playerIn, itemstack1);
        }
        return itemstack;
    }
}
