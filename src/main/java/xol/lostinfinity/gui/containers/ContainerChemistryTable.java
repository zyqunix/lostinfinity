package xol.lostinfinity.gui.containers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityChemistryTable;
public class ContainerChemistryTable extends Container {
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final InventoryPlayer invPlayer;
    private final TileEntityChemistryTable tileEntity;
    private final Slot inputSlot0;
    private final Slot inputSlot1;
    private final Slot inputSlot2;
    private final Slot outputSlot;
    private int mix0;
    private int mix1;
    private int mix2;
    private int mix3;
    private int mix4;
    private int mix5;
    private int mix6;
    private int mix7;
    private int mix8;
    private int currentMixLevel = 0;
    public ContainerChemistryTable(InventoryPlayer invPlayer, TileEntityChemistryTable tileEntity) {
        this.invPlayer = invPlayer;
        this.tileEntity = tileEntity;
        this.inputSlot0 = new Slot(tileEntity, 0, 18, 12);
        this.inputSlot1 = new Slot(tileEntity, 1, 18, 34);
        this.inputSlot2 = new Slot(tileEntity, 2, 18, 56);
        this.outputSlot = new Slot(tileEntity, PLAYER_INVENTORY_ROW_COUNT, 142, 35);
        func_75146_a(this.inputSlot0);
        func_75146_a(this.inputSlot1);
        func_75146_a(this.inputSlot2);
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
    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        return ItemStack.field_190927_a;
    }
    public void func_75132_a(IContainerListener listener) {
        super.func_75132_a(listener);
        listener.func_175173_a(this, this.tileEntity);
    }
    @SideOnly(Side.CLIENT)
    public void func_75137_b(int id, int data) {
        this.tileEntity.func_174885_b(id, data);
    }
    public boolean func_75145_c(EntityPlayer playerIn) {
        return true;
    }
    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            IContainerListener listener = (IContainerListener) this.field_75149_d.get(i);
            if (this.mix0 != this.tileEntity.func_174887_a_(0)) {
                listener.func_71112_a(this, 0, this.tileEntity.func_174887_a_(0));
            }
            if (this.mix1 != this.tileEntity.func_174887_a_(1)) {
                listener.func_71112_a(this, 1, this.tileEntity.func_174887_a_(1));
            }
            if (this.mix2 != this.tileEntity.func_174887_a_(2)) {
                listener.func_71112_a(this, 2, this.tileEntity.func_174887_a_(2));
            }
            if (this.mix3 != this.tileEntity.func_174887_a_(PLAYER_INVENTORY_ROW_COUNT)) {
                listener.func_71112_a(this, PLAYER_INVENTORY_ROW_COUNT, this.tileEntity.func_174887_a_(PLAYER_INVENTORY_ROW_COUNT));
            }
            if (this.mix4 != this.tileEntity.func_174887_a_(4)) {
                listener.func_71112_a(this, 4, this.tileEntity.func_174887_a_(4));
            }
            if (this.mix5 != this.tileEntity.func_174887_a_(5)) {
                listener.func_71112_a(this, 5, this.tileEntity.func_174887_a_(5));
            }
            if (this.mix6 != this.tileEntity.func_174887_a_(6)) {
                listener.func_71112_a(this, 6, this.tileEntity.func_174887_a_(6));
            }
            if (this.mix7 != this.tileEntity.func_174887_a_(7)) {
                listener.func_71112_a(this, 7, this.tileEntity.func_174887_a_(7));
            }
            if (this.mix8 != this.tileEntity.func_174887_a_(8)) {
                listener.func_71112_a(this, 8, this.tileEntity.func_174887_a_(8));
            }
            if (this.currentMixLevel != this.tileEntity.func_174887_a_(PLAYER_INVENTORY_COLUMN_COUNT)) {
                listener.func_71112_a(this, PLAYER_INVENTORY_COLUMN_COUNT, this.tileEntity.func_174887_a_(PLAYER_INVENTORY_COLUMN_COUNT));
            }
        }
        this.mix0 = this.tileEntity.func_174887_a_(0);
        this.mix1 = this.tileEntity.func_174887_a_(1);
        this.mix2 = this.tileEntity.func_174887_a_(2);
        this.mix3 = this.tileEntity.func_174887_a_(PLAYER_INVENTORY_ROW_COUNT);
        this.mix4 = this.tileEntity.func_174887_a_(4);
        this.mix5 = this.tileEntity.func_174887_a_(5);
        this.mix6 = this.tileEntity.func_174887_a_(6);
        this.mix7 = this.tileEntity.func_174887_a_(7);
        this.mix8 = this.tileEntity.func_174887_a_(8);
        this.currentMixLevel = this.tileEntity.func_174887_a_(PLAYER_INVENTORY_COLUMN_COUNT);
    }
}
