package xol.lostinfinity.gui.containers;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import xol.lostinfinity.item.misc.ItemAugmentSlide;
import xol.lostinfinity.item.misc.ItemAugmenticonBox;
public class ContainerAugmentor extends Container {
    public InventoryCrafting inputs = new InventoryCrafting(this, 2, 1);
    public InventoryCraftResult result = new InventoryCraftResult();
    public ContainerAugmentor(IInventory playerInventory) {
        addOwnSlots();
        addPlayerSlots(playerInventory);
    }
    public void onInputsChanged() {
        ItemStack boxStack = this.inputs.func_70301_a(0);
        ItemStack slideStack = this.inputs.func_70301_a(1);
        this.result.func_70299_a(0, ItemStack.field_190927_a);
        ItemStack newBoxStack = boxStack.func_77946_l();
        NBTTagList list = ItemAugmenticonBox.getAugmentList(newBoxStack);
        if (slideStack.func_77973_b() instanceof ItemAugmentSlide) {
            ItemAugmentSlide slide = (ItemAugmentSlide) slideStack.func_77973_b();
            boolean alreadyHasSlide = false;
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                NBTTagInt nBTTagInt = (NBTBase) it.next();
                if ((nBTTagInt instanceof NBTTagInt) && nBTTagInt.func_150287_d() == slide.type.ordinal()) {
                    alreadyHasSlide = true;
                    break;
                }
            }
            if (!alreadyHasSlide) {
                list.func_74742_a(new NBTTagInt(slide.type.ordinal()));
                this.result.func_70299_a(0, newBoxStack);
            }
        }
    }
    public void resultItemTaken() {
        this.inputs.func_174888_l();
    }
    private void addPlayerSlots(IInventory playerInventory) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int x = 8 + (col * 18);
                int y = (row * 18) + 84;
                func_75146_a(new Slot(playerInventory, col + (row * 9) + 10, x, y));
            }
        }
        for (int row2 = 0; row2 < 9; row2++) {
            int x2 = 8 + (row2 * 18);
            func_75146_a(new Slot(playerInventory, row2, x2, 142));
        }
    }
    private void addOwnSlots() {
        func_75146_a(new SlotBox(this.inputs, 0, 27, 47, this));
        func_75146_a(new SlotSlide(this.inputs, 1, 76, 47, this));
        func_75146_a(new SlotResult(this.result, 0, 134, 47, this));
    }
    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        ItemStack returnStack = ItemStack.field_190927_a;
        Slot slot = (Slot) this.field_75151_b.get(index);
        if (slot != null && slot.func_75216_d()) {
            ItemStack currentStack = slot.func_75211_c();
            returnStack = currentStack.func_77946_l();
            if (index == 2) {
                if (!func_75135_a(currentStack, 3, 39, true)) {
                    return ItemStack.field_190927_a;
                }
                slot.func_75220_a(currentStack, returnStack);
            } else if (index != 1 && index != 0) {
                if (currentStack.func_77973_b() instanceof ItemAugmenticonBox) {
                    if (!func_75135_a(currentStack, 0, 1, false)) {
                        return ItemStack.field_190927_a;
                    }
                } else if (currentStack.func_77973_b() instanceof ItemAugmentSlide) {
                    if (!func_75135_a(currentStack, 1, 2, false)) {
                        return ItemStack.field_190927_a;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!func_75135_a(currentStack, 30, 39, false)) {
                        return ItemStack.field_190927_a;
                    }
                } else if (index >= 30 && index < 39 && !func_75135_a(currentStack, 3, 30, false)) {
                    return ItemStack.field_190927_a;
                }
            } else if (!func_75135_a(currentStack, 3, 39, false)) {
                return ItemStack.field_190927_a;
            }
            if (currentStack.func_190926_b()) {
                slot.func_75215_d(ItemStack.field_190927_a);
            } else {
                slot.func_75218_e();
            }
            if (currentStack.func_190916_E() == returnStack.func_190916_E()) {
                return ItemStack.field_190927_a;
            }
            slot.func_190901_a(playerIn, currentStack);
        }
        return returnStack;
    }
    public boolean func_75145_c(EntityPlayer playerIn) {
        return true;
    }
    public void func_75134_a(EntityPlayer playerIn) {
        super.func_75134_a(playerIn);
        if (!playerIn.field_70170_p.field_72995_K) {
            func_193327_a(playerIn, playerIn.field_70170_p, this.inputs);
        }
    }
    private static class SlotBox extends Slot {
        private final ContainerAugmentor container;
        public SlotBox(IInventory inventoryIn, int index, int xPosition, int yPosition, ContainerAugmentor container) {
            super(inventoryIn, index, xPosition, yPosition);
            this.container = container;
        }
        public boolean func_75214_a(ItemStack stack) {
            return stack.func_77973_b() instanceof ItemAugmenticonBox;
        }
        public void func_75218_e() {
            super.func_75218_e();
            this.container.onInputsChanged();
        }
    }
    private static class SlotSlide extends Slot {
        private final ContainerAugmentor container;
        public SlotSlide(IInventory inventoryIn, int index, int xPosition, int yPosition, ContainerAugmentor container) {
            super(inventoryIn, index, xPosition, yPosition);
            this.container = container;
        }
        public boolean func_75214_a(ItemStack stack) {
            return stack.func_77973_b() instanceof ItemAugmentSlide;
        }
        public void func_75218_e() {
            super.func_75218_e();
            this.container.onInputsChanged();
        }
    }
    private static class SlotResult extends Slot {
        private final ContainerAugmentor container;
        public SlotResult(IInventory inventoryIn, int index, int xPosition, int yPosition, ContainerAugmentor container) {
            super(inventoryIn, index, xPosition, yPosition);
            this.container = container;
        }
        public boolean func_75214_a(ItemStack stack) {
            return false;
        }
        public ItemStack func_190901_a(EntityPlayer thePlayer, ItemStack stack) {
            this.container.resultItemTaken();
            return super.func_190901_a(thePlayer, stack);
        }
    }
}
