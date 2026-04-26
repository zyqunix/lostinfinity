package xol.lostinfinity.gui.containers;
import java.util.Arrays;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.Reference;
public class ContainerItemCharger extends Container {
    public final ItemStack charger;
    private final World world;
    public ItemChargerStatus status = ItemChargerStatus.AWAITING_INPUT;
    private final IInventory inputSlots = new InventoryBasic("Item Charger", true, 4) { // from class: xol.lostinfinity.gui.containers.ContainerItemCharger.1
        public void func_70296_d() {
            super.func_70296_d();
            ContainerItemCharger.this.func_75130_a(this);
        }
    };
    public boolean isValidInput(ItemStack input) {
        boolean result = false;
        if (this.charger.func_77973_b().equals(ItemInit.headCollector)) {
            result = input.func_77973_b().equals(ItemInit.powerDrive);
        }
        return result;
    }
    public int getMaxChargeForItem() {
        int maxCharge = 0;
        if (this.charger.func_77973_b().equals(ItemInit.headCollector)) {
            maxCharge = 10;
        }
        return maxCharge;
    }
    public int getChargeLimitForItem() {
        if (!this.charger.func_77942_o()) {
            this.charger.func_77982_d(new NBTTagCompound());
        }
        int limit = getMaxChargeForItem();
        if (this.charger.func_77973_b().equals(ItemInit.headCollector)) {
            int curCharge = this.charger.func_77978_p().func_74762_e("Charge");
            limit -= curCharge;
        }
        return Math.max(limit, 0);
    }
    public ContainerItemCharger(InventoryPlayer player, World worldIn, ItemStack charger) {
        this.charger = charger;
        this.world = worldIn;
        func_75146_a(new Slot(this.inputSlots, 0, 66, 13) { // from class: xol.lostinfinity.gui.containers.ContainerItemCharger.2
            public int func_178170_b(ItemStack stack) {
                return ContainerItemCharger.this.getChargeLimitForItem();
            }
            public boolean func_75214_a(ItemStack stack) {
                return ContainerItemCharger.this.isValidInput(stack);
            }
        });
        func_75146_a(new Slot(this.inputSlots, 1, 94, 13) { // from class: xol.lostinfinity.gui.containers.ContainerItemCharger.3
            public boolean func_75214_a(ItemStack stack) {
                return ContainerItemCharger.this.isValidInput(stack);
            }
            public int func_178170_b(ItemStack stack) {
                return ContainerItemCharger.this.getChargeLimitForItem();
            }
        });
        func_75146_a(new Slot(this.inputSlots, 2, 66, 42) { // from class: xol.lostinfinity.gui.containers.ContainerItemCharger.4
            public boolean func_75214_a(ItemStack stack) {
                return ContainerItemCharger.this.isValidInput(stack);
            }
            public int func_178170_b(ItemStack stack) {
                return ContainerItemCharger.this.getChargeLimitForItem();
            }
        });
        func_75146_a(new Slot(this.inputSlots, 3, 94, 42) { // from class: xol.lostinfinity.gui.containers.ContainerItemCharger.5
            public boolean func_75214_a(ItemStack stack) {
                return ContainerItemCharger.this.isValidInput(stack);
            }
            public int func_178170_b(ItemStack stack) {
                return ContainerItemCharger.this.getChargeLimitForItem();
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
            updateChargingGui();
        }
    }
    public void updateChargingGui() {
        ItemStack iteminput1 = this.inputSlots.func_70301_a(0).func_77946_l();
        ItemStack iteminput2 = this.inputSlots.func_70301_a(1).func_77946_l();
        ItemStack iteminput3 = this.inputSlots.func_70301_a(2).func_77946_l();
        ItemStack iteminput4 = this.inputSlots.func_70301_a(3).func_77946_l();
        if (iteminput1.func_190926_b() || iteminput2.func_190926_b() || iteminput3.func_190926_b() || iteminput4.func_190926_b()) {
            if (iteminput1.func_190926_b() && iteminput2.func_190926_b() && iteminput3.func_190926_b() && iteminput4.func_190926_b()) {
                this.status = ItemChargerStatus.AWAITING_INPUT;
                return;
            } else {
                this.status = ItemChargerStatus.PARTIAL_INPUT;
                return;
            }
        }
        this.status = ItemChargerStatus.VALID;
        int[] counts = {iteminput1.func_190916_E(), iteminput2.func_190916_E(), iteminput3.func_190916_E(), iteminput4.func_190916_E()};
        Arrays.sort(counts);
        for (int i = 0; i < 4; i++) {
            this.inputSlots.func_70301_a(i).func_190918_g(counts[0]);
        }
        this.charger.func_77978_p().func_74768_a("Charge", Math.min(getMaxChargeForItem(), this.charger.func_77978_p().func_74762_e("Charge") + counts[0]));
        func_75142_b();
    }
    public void func_75134_a(EntityPlayer playerIn) {
        super.func_75134_a(playerIn);
        if (!this.world.field_72995_K) {
            func_193327_a(playerIn, this.world, this.inputSlots);
        }
    }
    public boolean func_75145_c(EntityPlayer playerIn) {
        return true;
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
                if (index >= 8 && index < 44 && !func_75135_a(itemstack1, 0, 3, false)) {
                    return ItemStack.field_190927_a;
                }
            } else if (!func_75135_a(itemstack1, 4, 40, false)) {
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
    public enum ItemChargerStatus {
        AWAITING_INPUT("Awaiting valid charger input..."),
        FULLY_CHARGED("Item has already been fully charged."),
        PARTIAL_INPUT("Partial charger input detected."),
        VALID("Valid and complete charger input detected, processing.");
        final String descriptor;
        ItemChargerStatus(String descriptor) {
            this.descriptor = descriptor;
        }
        public int getColor() {
            switch (AnonymousClass6.$SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus[ordinal()]) {
                case 1:
                    return Reference.getDecimalColorFromRGB(69, 175, 69);
                case 2:
                    return Reference.getDecimalColorFromRGB(250, 250, 100);
                case 3:
                    return Reference.getDecimalColorFromRGB(255, 191, 0);
                case TileEntityFusionTable.BOARD_ROWS :
                    return Reference.getDecimalColorFromRGB(100, 220, 100);
                default:
                    return Reference.getDecimalColorFromRGB(255, 0, 0);
            }
        }
    }
    static  class AnonymousClass6 {
        static final  int[] $SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus = new int[ItemChargerStatus.values().length];
        static {
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus[ItemChargerStatus.FULLY_CHARGED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus[ItemChargerStatus.AWAITING_INPUT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus[ItemChargerStatus.PARTIAL_INPUT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus[ItemChargerStatus.VALID.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
}
