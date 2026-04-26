package xol.lostinfinity.gui.containers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.recipes.FossilCombinerRecipes;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerFossilCombiner.class */
public class ContainerFossilCombiner extends Container {
    private final Block combiner;
    private final BlockPos blockPos;
    private final World world;
    public CombinerStatus status = CombinerStatus.AWAITING_INPUT;
    public int materialCost = 1;
    private final IInventory outputSlot = new InventoryCraftResult();
    private final IInventory inputSlots = new InventoryBasic("Combiner", true, 15) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.1
        public void func_70296_d() {
            super.func_70296_d();
            ContainerFossilCombiner.this.func_75130_a(this);
        }
    };

    public ContainerFossilCombiner(InventoryPlayer player, final World worldIn, final BlockPos blockPosIn, Block combiner) {
        this.combiner = combiner;
        this.world = worldIn;
        this.blockPos = blockPosIn;
        func_75146_a(new Slot(this.inputSlots, 0, 9, 14) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.2
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 1, 28, 14) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.3
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 2, 47, 14) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.4
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 3, 66, 14) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.5
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 4, 85, 14) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.6
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 5, 9, 35) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.7
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 6, 28, 35) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.8
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 7, 47, 35) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.9
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 8, 66, 35) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.10
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 9, 85, 35) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.11
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 10, 9, 56) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.12
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 11, 28, 56) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.13
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 12, 47, 56) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.14
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 13, 66, 56) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.15
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 14, 85, 56) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.16
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.outputSlot, 15, 134, 35) { // from class: xol.lostinfinity.gui.containers.ContainerFossilCombiner.17
            public boolean func_75214_a(ItemStack stack) {
                return false;
            }

            public boolean func_82869_a(EntityPlayer playerIn) {
                return func_75216_d();
            }

            public ItemStack func_190901_a(EntityPlayer thePlayer, ItemStack stack) {
                for (int i = 0; i < 15; i++) {
                    ItemStack input = ContainerFossilCombiner.this.inputSlots.func_70301_a(i).func_77946_l();
                    input.func_190918_g(1);
                    ContainerFossilCombiner.this.inputSlots.func_70299_a(i, input);
                }
                if (!worldIn.field_72995_K) {
                    worldIn.func_175718_b(1030, blockPosIn, 0);
                }
                return stack;
            }
        });
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                func_75146_a(new Slot(player, j + (i * 9) + 9, 8 + (j * 18), 84 + (i * 18)));
            }
        }
        for (int k = 0; k < 9; k++) {
            func_75146_a(new Slot(player, k, 8 + (k * 18), 142));
        }
    }

    public void func_75130_a(IInventory inventoryIn) {
        super.func_75130_a(inventoryIn);
        if (inventoryIn == this.inputSlots) {
            updateOutput();
        }
    }

    public void updateOutput() {
        ItemStack[] inputs = new ItemStack[15];
        for (int i = 0; i < 15; i++) {
            inputs[i] = this.inputSlots.func_70301_a(i).func_77946_l();
        }
        if (inputs.length == 0) {
            this.outputSlot.func_70299_a(0, ItemStack.field_190927_a);
            this.status = CombinerStatus.AWAITING_INPUT;
            return;
        }
        ItemStack result = FossilCombinerRecipes.getResult(inputs);
        if (result != null) {
            this.status = CombinerStatus.VALID;
            this.outputSlot.func_70299_a(0, result);
            func_75142_b();
        } else {
            this.outputSlot.func_70299_a(0, ItemStack.field_190927_a);
            this.status = CombinerStatus.AWAITING_INPUT;
        }
    }

    public void func_75134_a(EntityPlayer playerIn) {
        super.func_75134_a(playerIn);
        if (!this.world.field_72995_K) {
            func_193327_a(playerIn, this.world, this.inputSlots);
        }
    }

    public boolean func_75145_c(EntityPlayer playerIn) {
        return this.world.func_180495_p(this.blockPos).func_177230_c().equals(this.combiner) && playerIn.func_70092_e(((double) this.blockPos.func_177958_n()) + 0.5d, ((double) this.blockPos.func_177956_o()) + 0.5d, ((double) this.blockPos.func_177952_p()) + 0.5d) <= 64.0d;
    }

    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = (Slot) this.field_75151_b.get(index);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (index != 0 && index != 1 && index >= 18 && index < 55 && !func_75135_a(itemstack1, 0, 15, false)) {
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

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerFossilCombiner$CombinerStatus.class */
    public enum CombinerStatus {
        AWAITING_INPUT("Awaiting valid module creation input..."),
        VALID("Valid module creation detected, proceed.");

        final String descriptor;

        CombinerStatus(String descriptor) {
            this.descriptor = descriptor;
        }

        public int getColor() {
            switch (this) {
                case AWAITING_INPUT:
                    return Reference.getDecimalColorFromRGB(250, 250, 100);
                case VALID:
                    return Reference.getDecimalColorFromRGB(100, 220, 100);
                default:
                    return Reference.getDecimalColorFromRGB(255, 0, 0);
            }
        }
    }
}
