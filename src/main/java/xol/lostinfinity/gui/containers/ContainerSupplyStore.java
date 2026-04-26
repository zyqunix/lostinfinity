package xol.lostinfinity.gui.containers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.mob.entity.misc.EntitySupplyTrader;
public class ContainerSupplyStore extends Container {
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final InventoryPlayer invPlayer;
    private final EntitySupplyTrader trader;
    private final InventoryBasic inventory = new InventoryBasic("Supply Store", false, 1);
    private final Slot outputSlot = new Slot(this.inventory, 0, 120, 33);
    public ContainerSupplyStore(InventoryPlayer invPlayer) {
        this.invPlayer = invPlayer;
        this.trader = (EntitySupplyTrader) invPlayer.field_70458_d.field_70170_p.func_72872_a(EntitySupplyTrader.class, invPlayer.field_70458_d.func_174813_aQ().func_186662_g(10.0d)).get(0);
        func_75146_a(this.outputSlot);
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
        if (slotId == this.outputSlot.field_75222_d) {
            if (!this.outputSlot.func_75211_c().func_190926_b() && player.field_71071_by.func_70445_o().func_77973_b() != this.outputSlot.func_75211_c().func_77973_b()) {
                return ItemStack.field_190927_a;
            }
            if (this.outputSlot.func_75211_c().func_190926_b()) {
                return ItemStack.field_190927_a;
            }
        }
        return super.func_184996_a(slotId, dragType, clickTypeIn, player);
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
