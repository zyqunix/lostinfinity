package xol.lostinfinity.gui.containers;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.item.misc.ItemShipmentBox;
import xol.lostinfinity.mob.entity.misc.EntitySupplyTrader;
public class ContainerSupplyDeposit extends Container {
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final InventoryPlayer invPlayer;
    private final EntitySupplyTrader trader;
    private final InventoryBasic inventory = new InventoryBasic("Deposit Box", false, 1) { // from class: xol.lostinfinity.gui.containers.ContainerSupplyDeposit.1
        public void func_70296_d() {
            super.func_70296_d();
            ContainerSupplyDeposit.this.func_75130_a(this);
        }
    };
    private final Slot inputSlot = new Slot(this.inventory, 0, 80, 35);
    public ContainerSupplyDeposit(InventoryPlayer invPlayer) {
        this.invPlayer = invPlayer;
        this.trader = (EntitySupplyTrader) invPlayer.field_70458_d.field_70170_p.func_72872_a(EntitySupplyTrader.class, invPlayer.field_70458_d.func_174813_aQ().func_186662_g(10.0d)).get(0);
        func_75146_a(this.inputSlot);
        for (int playerSlotIndexY = 0; playerSlotIndexY < PLAYER_INVENTORY_ROW_COUNT; playerSlotIndexY++) {
            for (int playerSlotIndexX = 0; playerSlotIndexX < PLAYER_INVENTORY_COLUMN_COUNT; playerSlotIndexX++) {
                func_75146_a(new Slot(this.invPlayer, playerSlotIndexX + (playerSlotIndexY * PLAYER_INVENTORY_COLUMN_COUNT) + PLAYER_INVENTORY_COLUMN_COUNT, 8 + (playerSlotIndexX * 18), 84 + (playerSlotIndexY * 18)));
            }
        }
        for (int hotbarSlotIndex = 0; hotbarSlotIndex < PLAYER_INVENTORY_COLUMN_COUNT; hotbarSlotIndex++) {
            func_75146_a(new Slot(this.invPlayer, hotbarSlotIndex, 8 + (hotbarSlotIndex * 18), 142));
        }
    }
    public ItemStack func_184996_a(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        ItemStack heldStack = player.field_71071_by.func_70445_o();
        if (slotId == 0) {
            if (heldStack.func_190926_b()) {
                return super.func_184996_a(slotId, dragType, clickTypeIn, player);
            }
            if (!(heldStack.func_77973_b() instanceof ItemShipmentBox)) {
                return ItemStack.field_190927_a;
            }
            return super.func_184996_a(slotId, dragType, clickTypeIn, player);
        }
        return super.func_184996_a(slotId, dragType, clickTypeIn, player);
    }
    public void func_75130_a(IInventory inventoryIn) {
        super.func_75130_a(inventoryIn);
        if (inventoryIn == this.inventory) {
            ItemStack stack = this.inventory.func_70301_a(0);
            if ((stack.func_77973_b() instanceof ItemShipmentBox) && !this.trader.field_70170_p.field_72995_K) {
                int[] itemIDs = stack.func_77978_p().func_74759_k("itemids");
                int item1Count = this.trader.getItem1Count();
                int item2Count = this.trader.getItem2Count();
                int item3Count = this.trader.getItem3Count();
                int item4Count = this.trader.getItem4Count();
                for (int i : itemIDs) {
                    switch (i) {
                        case 1:
                            item1Count++;
                            break;
                        case 2:
                            item2Count++;
                            break;
                        case PLAYER_INVENTORY_ROW_COUNT :
                            item3Count++;
                            break;
                        case TileEntityFusionTable.BOARD_ROWS :
                            item4Count++;
                            break;
                    }
                }
                this.trader.setItem1Count(item1Count);
                this.trader.setItem2Count(item2Count);
                this.trader.setItem3Count(item3Count);
                this.trader.setItem4Count(item4Count);
                stack.func_190918_g(1);
                this.trader.updateInventoryToClient(this.invPlayer.field_70458_d);
                List<EntityPlayer> nearPlayers = this.trader.field_70170_p.func_72872_a(EntityPlayer.class, this.trader.func_174813_aQ().func_186662_g(20.0d));
                EntitySupplyTrader entitySupplyTrader = this.trader;
                entitySupplyTrader.getClass();
                nearPlayers.forEach(entitySupplyTrader::updateInventoryToClient);
            }
        }
    }
    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        return ItemStack.field_190927_a;
    }
    public boolean func_75145_c(EntityPlayer playerIn) {
        return true;
    }
    public EntitySupplyTrader getTrader() {
        return this.trader;
    }
}
