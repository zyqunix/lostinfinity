package xol.lostinfinity.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityNebulousBeacon;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerNebulousBeacon.class */
public class ContainerNebulousBeacon extends Container {
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final InventoryPlayer invPlayer;
    private final TileEntityNebulousBeacon tileEntity;
    private int lastHealth;
    private int lastDuration;

    public ContainerNebulousBeacon(InventoryPlayer invPlayer, TileEntityNebulousBeacon tileEntity) {
        this.invPlayer = invPlayer;
        this.tileEntity = tileEntity;
        MinecraftForge.EVENT_BUS.register(this);
        for (int playerSlotRow = 0; playerSlotRow < PLAYER_INVENTORY_ROW_COUNT; playerSlotRow++) {
            for (int playerSlotColumn = 0; playerSlotColumn < PLAYER_INVENTORY_COLUMN_COUNT; playerSlotColumn++) {
                func_75146_a(new Slot(this.invPlayer, playerSlotColumn + (playerSlotRow * PLAYER_INVENTORY_COLUMN_COUNT) + PLAYER_INVENTORY_COLUMN_COUNT, 8 + (playerSlotColumn * 18), 84 + (playerSlotRow * 18)));
            }
        }
        for (int hotbarColumn = 0; hotbarColumn < PLAYER_INVENTORY_COLUMN_COUNT; hotbarColumn++) {
            func_75146_a(new Slot(this.invPlayer, hotbarColumn, 8 + (hotbarColumn * 18), 142));
        }
    }

    public boolean func_75145_c(EntityPlayer playerIn) {
        return true;
    }

    public void func_75132_a(IContainerListener listener) {
        super.func_75132_a(listener);
        listener.func_175173_a(this, this.tileEntity);
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int id, int data) {
        this.tileEntity.func_174885_b(id, data);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            IContainerListener icontainerlistener = (IContainerListener) this.field_75149_d.get(i);
            if (this.lastHealth != this.tileEntity.func_174887_a_(0)) {
                icontainerlistener.func_71112_a(this, 0, this.tileEntity.func_174887_a_(0));
            }
            if (this.lastDuration != this.tileEntity.func_174887_a_(1)) {
                icontainerlistener.func_71112_a(this, 1, this.tileEntity.func_174887_a_(1));
            }
        }
    }
}
