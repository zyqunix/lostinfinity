package xol.lostinfinity.gui.containers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.block.tileentity.TileEntityGearbox;
public class ContainerGearbox extends Container {
    private final TileEntityGearbox tileentity;
    public ContainerGearbox(InventoryPlayer player, TileEntityGearbox tileentity) {
        this.tileentity = tileentity;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                func_75146_a(new Slot(player, x + (y * 9) + 9, 8 + (x * 18), 84 + (y * 18)));
            }
        }
        for (int x2 = 0; x2 < 9; x2++) {
            func_75146_a(new Slot(player, x2, 8 + (x2 * 18), 142));
        }
    }
    public boolean func_75145_c(EntityPlayer playerIn) {
        return true;
    }
    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        return ItemStack.field_190927_a;
    }
}
