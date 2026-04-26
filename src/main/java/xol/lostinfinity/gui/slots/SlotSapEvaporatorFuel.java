package xol.lostinfinity.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.block.tileentity.TileEntitySapEvaporator;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/slots/SlotSapEvaporatorFuel.class */
public class SlotSapEvaporatorFuel extends Slot {
    public SlotSapEvaporatorFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public boolean func_75214_a(ItemStack stack) {
        return TileEntitySapEvaporator.isItemFuel(stack);
    }

    public int func_178170_b(ItemStack stack) {
        return super.func_178170_b(stack);
    }
}
