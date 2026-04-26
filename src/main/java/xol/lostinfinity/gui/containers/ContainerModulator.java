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
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.item.armor.ItemLostArmor;
import xol.lostinfinity.item.misc.ItemModule;
import xol.lostinfinity.recipes.ModulatorRecipes;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerModulator.class */
public class ContainerModulator extends Container {
    private final Block modulator;
    private final BlockPos blockPos;
    private final World world;
    public ModulatorStatus status = ModulatorStatus.AWAITING_INPUT;
    public int materialCost = 1;
    private final IInventory outputSlot = new InventoryCraftResult();
    private final IInventory inputSlots = new InventoryBasic("Modulator", true, 6) { // from class: xol.lostinfinity.gui.containers.ContainerModulator.1
        public void func_70296_d() {
            super.func_70296_d();
            ContainerModulator.this.func_75130_a(this);
        }
    };

    public ContainerModulator(InventoryPlayer player, final World worldIn, final BlockPos blockPosIn, Block modulator) {
        this.modulator = modulator;
        this.world = worldIn;
        this.blockPos = blockPosIn;
        func_75146_a(new Slot(this.inputSlots, 0, 13, 25) { // from class: xol.lostinfinity.gui.containers.ContainerModulator.2
            public boolean func_75214_a(ItemStack stack) {
                return stack.func_77973_b() instanceof ItemModule;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 1, 13, 45) { // from class: xol.lostinfinity.gui.containers.ContainerModulator.3
            public boolean func_75214_a(ItemStack stack) {
                return stack.func_77973_b() instanceof ItemModule;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 2, 46, 35) { // from class: xol.lostinfinity.gui.containers.ContainerModulator.4
            public boolean func_75214_a(ItemStack stack) {
                return stack.func_77973_b() instanceof ItemLostArmor;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }

            public int func_75219_a() {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 3, 79, 14) { // from class: xol.lostinfinity.gui.containers.ContainerModulator.5
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
        func_75146_a(new Slot(this.inputSlots, 4, 79, 35) { // from class: xol.lostinfinity.gui.containers.ContainerModulator.6
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_75219_a() {
                return 1;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }
        });
        func_75146_a(new Slot(this.inputSlots, 5, 79, 56) { // from class: xol.lostinfinity.gui.containers.ContainerModulator.7
            public boolean func_75214_a(ItemStack stack) {
                return true;
            }

            public int func_75219_a() {
                return 1;
            }

            public int func_178170_b(ItemStack stack) {
                return 1;
            }
        });
        func_75146_a(new Slot(this.outputSlot, 6, 134, 35) { // from class: xol.lostinfinity.gui.containers.ContainerModulator.8
            public boolean func_75214_a(ItemStack stack) {
                return false;
            }

            public boolean func_82869_a(EntityPlayer playerIn) {
                return func_75216_d();
            }

            public ItemStack func_190901_a(EntityPlayer thePlayer, ItemStack stack) {
                for (int i = 0; i < 6; i++) {
                    ItemStack input = ContainerModulator.this.inputSlots.func_70301_a(i).func_77946_l();
                    input.func_190918_g(1);
                    ContainerModulator.this.inputSlots.func_70299_a(i, input);
                }
                BlockPos pos = ContainerModulator.this.blockPos;
                BlockPos[] positions = {pos.func_177977_b().func_177978_c(), pos.func_177977_b().func_177976_e(), pos.func_177977_b().func_177974_f(), pos.func_177977_b().func_177968_d()};
                for (BlockPos position : positions) {
                    ContainerModulator.this.world.func_175656_a(position, BlockInit.unpoweredModulationBattery.func_176223_P());
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
        ItemStack module1 = this.inputSlots.func_70301_a(0).func_77946_l();
        ItemStack module2 = this.inputSlots.func_70301_a(1).func_77946_l();
        ItemStack armor = this.inputSlots.func_70301_a(2).func_77946_l();
        ItemStack ingot1 = this.inputSlots.func_70301_a(3).func_77946_l();
        ItemStack ingot2 = this.inputSlots.func_70301_a(4).func_77946_l();
        ItemStack ingot3 = this.inputSlots.func_70301_a(5).func_77946_l();
        if (module1.func_190926_b() || module2.func_190926_b() || armor.func_190926_b() || ingot1.func_190926_b() || ingot2.func_190926_b() || ingot3.func_190926_b()) {
            this.outputSlot.func_70299_a(0, ItemStack.field_190927_a);
            this.status = ModulatorStatus.AWAITING_INPUT;
            return;
        }
        ItemStack result = ModulatorRecipes.getResult(module1, module2, armor, ingot1, ingot2, ingot3);
        if (result != null) {
            this.status = ModulatorStatus.VALID;
            this.outputSlot.func_70299_a(0, result);
            func_75142_b();
        } else {
            this.outputSlot.func_70299_a(0, ItemStack.field_190927_a);
            this.status = ModulatorStatus.AWAITING_INPUT;
        }
    }

    public void func_75134_a(EntityPlayer playerIn) {
        super.func_75134_a(playerIn);
        if (!this.world.field_72995_K) {
            func_193327_a(playerIn, this.world, this.inputSlots);
        }
    }

    public boolean func_75145_c(EntityPlayer playerIn) {
        return this.world.func_180495_p(this.blockPos).func_177230_c().equals(this.modulator) && playerIn.func_70092_e(((double) this.blockPos.func_177958_n()) + 0.5d, ((double) this.blockPos.func_177956_o()) + 0.5d, ((double) this.blockPos.func_177952_p()) + 0.5d) <= 64.0d;
    }

    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = (Slot) this.field_75151_b.get(index);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (index != 0 && index != 1 && index >= 7 && index < 44 && !func_75135_a(itemstack1, 0, 6, false)) {
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

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerModulator$ModulatorStatus.class */
    public enum ModulatorStatus {
        AWAITING_INPUT("Awaiting valid armor modulation input..."),
        VALID("Valid modulation configuration detected, proceed.");

        final String descriptor;

        ModulatorStatus(String descriptor) {
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
