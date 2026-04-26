package xol.lostinfinity.gui.slots;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
public class SlotNicroniumInfuserOutput extends Slot {
    private final EntityPlayer player;
    private int removeCount;
    public SlotNicroniumInfuserOutput(EntityPlayer player, IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
        this.player = player;
    }
    public boolean func_75214_a(ItemStack stack) {
        return false;
    }
    public ItemStack func_190901_a(EntityPlayer player, ItemStack stack) {
        func_75208_c(stack);
        super.func_190901_a(player, stack);
        return stack;
    }
    public ItemStack func_75209_a(int amount) {
        if (func_75216_d()) {
            this.removeCount += Math.min(amount, func_75211_c().func_190916_E());
        }
        return super.func_75209_a(amount);
    }
}
