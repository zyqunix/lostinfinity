package xol.lostinfinity.gui.containers;

import java.util.Arrays;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.recipes.Recipe;
import xol.lostinfinity.recipes.RecipeHandler;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerFabricationStation.class */
public class ContainerFabricationStation extends Container {
    private final Block fabStation;
    private final BlockPos blockPos;
    private final World world;
    public FabricationStatus status = FabricationStatus.AWAITING_INPUT;
    private final IInventory outputSlot = new InventoryCraftResult();
    private final IInventory inputSlots = new InventoryBasic("Starforge Fabrication Station", true, 7) { // from class: xol.lostinfinity.gui.containers.ContainerFabricationStation.1
        public void func_70296_d() {
            super.func_70296_d();
            ContainerFabricationStation.this.func_75130_a(this);
        }
    };

    public boolean isValidInput(ItemStack itemStack) {
        Item[] validItems = {ItemInit.astralliumCondensed, ItemInit.crystoniumCondensed, ItemInit.detheriumCondensed, ItemInit.emberiumCondensed, ItemInit.incadiumCondensed, ItemInit.hextoriumCondensed, ItemInit.kylaxiumCondensed, ItemInit.noxeriumCondensed, ItemInit.olysiumCondensed, ItemInit.velloriumCondensed, ItemInit.xeroviumCondensed, ItemInit.phytrosiumCondensed, ItemInit.kyvoriumCondensed, ItemInit.biosynthiumCondensed};
        return Arrays.stream(validItems).anyMatch(it -> {
            return itemStack.func_77973_b().equals(it);
        });
    }

    public ContainerFabricationStation(InventoryPlayer player, final World worldIn, final BlockPos blockPosIn, Block fabStation) {
        this.fabStation = fabStation;
        this.blockPos = blockPosIn;
        this.world = worldIn;
        func_75146_a(new Slot(this.inputSlots, 0, 29, 11) { // from class: xol.lostinfinity.gui.containers.ContainerFabricationStation.2
            public boolean func_75214_a(ItemStack stack) {
                return ContainerFabricationStation.this.isValidInput(stack);
            }
        });
        func_75146_a(new Slot(this.inputSlots, 1, 57, 11) { // from class: xol.lostinfinity.gui.containers.ContainerFabricationStation.3
            public boolean func_75214_a(ItemStack stack) {
                return ContainerFabricationStation.this.isValidInput(stack);
            }
        });
        func_75146_a(new Slot(this.inputSlots, 2, 15, 35) { // from class: xol.lostinfinity.gui.containers.ContainerFabricationStation.4
            public boolean func_75214_a(ItemStack stack) {
                return ContainerFabricationStation.this.isValidInput(stack);
            }
        });
        func_75146_a(new Slot(this.inputSlots, 3, 43, 35) { // from class: xol.lostinfinity.gui.containers.ContainerFabricationStation.5
            public boolean func_75214_a(ItemStack stack) {
                return ContainerFabricationStation.this.isValidInput(stack);
            }
        });
        func_75146_a(new Slot(this.inputSlots, 4, 71, 35) { // from class: xol.lostinfinity.gui.containers.ContainerFabricationStation.6
            public boolean func_75214_a(ItemStack stack) {
                return ContainerFabricationStation.this.isValidInput(stack);
            }
        });
        func_75146_a(new Slot(this.inputSlots, 5, 29, 59) { // from class: xol.lostinfinity.gui.containers.ContainerFabricationStation.7
            public boolean func_75214_a(ItemStack stack) {
                return ContainerFabricationStation.this.isValidInput(stack);
            }
        });
        func_75146_a(new Slot(this.inputSlots, 6, 57, 59) { // from class: xol.lostinfinity.gui.containers.ContainerFabricationStation.8
            public boolean func_75214_a(ItemStack stack) {
                return ContainerFabricationStation.this.isValidInput(stack);
            }
        });
        func_75146_a(new Slot(this.outputSlot, 7, 134, 35) { // from class: xol.lostinfinity.gui.containers.ContainerFabricationStation.9
            public boolean func_75214_a(ItemStack stack) {
                return false;
            }

            public boolean func_82869_a(EntityPlayer playerIn) {
                return func_75216_d();
            }

            public ItemStack func_190901_a(EntityPlayer thePlayer, ItemStack stack) {
                for (int i = 0; i <= 6; i++) {
                    ContainerFabricationStation.this.inputSlots.func_70301_a(i).func_190918_g(1);
                }
                BlockPos pos = ContainerFabricationStation.this.blockPos;
                BlockPos[] positions = {pos.func_177977_b().func_177978_c(), pos.func_177977_b().func_177976_e(), pos.func_177977_b().func_177974_f(), pos.func_177977_b().func_177968_d()};
                for (BlockPos position : positions) {
                    ContainerFabricationStation.this.world.func_175656_a(position, BlockInit.unpoweredFabricationBattery.func_176223_P());
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
            updateFabricatorOutput();
        }
    }

    public void updateFabricatorOutput() {
        ItemStack iteminput1 = this.inputSlots.func_70301_a(0).func_77946_l();
        ItemStack iteminput2 = this.inputSlots.func_70301_a(1).func_77946_l();
        ItemStack iteminput3 = this.inputSlots.func_70301_a(2).func_77946_l();
        ItemStack iteminput4 = this.inputSlots.func_70301_a(3).func_77946_l();
        ItemStack iteminput5 = this.inputSlots.func_70301_a(4).func_77946_l();
        ItemStack iteminput6 = this.inputSlots.func_70301_a(5).func_77946_l();
        ItemStack iteminput7 = this.inputSlots.func_70301_a(6).func_77946_l();
        Optional<Recipe> recipeResult = RecipeHandler.getRecipeFor(iteminput1, iteminput2, iteminput3, iteminput4, iteminput5, iteminput6, iteminput7);
        if (!recipeResult.isPresent()) {
            this.outputSlot.func_70299_a(0, ItemStack.field_190927_a);
            this.status = FabricationStatus.AWAITING_INPUT;
        } else {
            Recipe recipe = recipeResult.get();
            this.outputSlot.func_70299_a(0, new ItemStack(recipe.result));
            this.status = FabricationStatus.VALID;
            func_75142_b();
        }
    }

    public void func_75134_a(EntityPlayer playerIn) {
        super.func_75134_a(playerIn);
        if (!this.world.field_72995_K) {
            func_193327_a(playerIn, this.world, this.inputSlots);
        }
    }

    public boolean func_75145_c(EntityPlayer playerIn) {
        if (!this.world.func_180495_p(this.blockPos).func_177230_c().equals(this.fabStation)) {
            return false;
        }
        int poweredCount = 0;
        Block poweredBlock = BlockInit.fabricationBattery;
        BlockPos pos = this.blockPos;
        BlockPos[] positions = {pos.func_177982_a(0, -1, 1), pos.func_177982_a(-1, -1, 0), pos.func_177982_a(1, -1, 0), pos.func_177982_a(0, -1, -1)};
        for (BlockPos position : positions) {
            if (this.world.func_180495_p(position).func_177230_c().equals(poweredBlock)) {
                poweredCount++;
            }
        }
        return poweredCount == 4 && playerIn.func_70092_e(((double) this.blockPos.func_177958_n()) + 0.5d, ((double) this.blockPos.func_177956_o()) + 0.5d, ((double) this.blockPos.func_177952_p()) + 0.5d) <= 64.0d;
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
            } else if (index != 0 && index != 1) {
                if (index >= 8 && index < 44 && !func_75135_a(itemstack1, 0, 7, false)) {
                    return ItemStack.field_190927_a;
                }
            } else if (!func_75135_a(itemstack1, 8, 44, false)) {
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

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerFabricationStation$FabricationStatus.class */
    public enum FabricationStatus {
        AWAITING_INPUT("Awaiting valid fabrication configuration..."),
        VALID("Valid modularisation configuration detected, proceed.");

        final String descriptor;

        FabricationStatus(String descriptor) {
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
