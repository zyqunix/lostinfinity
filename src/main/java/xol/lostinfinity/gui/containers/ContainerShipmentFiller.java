package xol.lostinfinity.gui.containers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityShipmentFiller;
import xol.lostinfinity.item.misc.ItemShipmentBox;
public class ContainerShipmentFiller extends Container {
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final InventoryPlayer player;
    private final TileEntityShipmentFiller tileEntity;
    private final Slot inputSlot;
    private final Slot boxSlot;
    private final Slot itemSlot0;
    private final Slot itemSlot1;
    private final Slot itemSlot2;
    private final Slot itemSlot3;
    private final Slot itemSlot4;
    private final Slot itemSlot5;
    private final Slot itemSlot6;
    private final Slot itemSlot7;
    private final Slot itemSlot8;
    private final Slot itemSlot9;
    private final Slot itemSlot10;
    private final Slot itemSlot11;
    private final Slot itemSlot12;
    private final Slot itemSlot13;
    private final Slot itemSlot14;
    private final Slot itemSlot15;
    private final Slot itemSlot16;
    private final Slot itemSlot17;
    private final Slot itemSlot18;
    private final Slot itemSlot19;
    private final Slot itemSlot20;
    private final Slot itemSlot21;
    private final Slot itemSlot22;
    private final Slot itemSlot23;
    private final Slot itemSlot24;
    private final Slot itemSlot25;
    private final Slot itemSlot26;
    private final Slot itemSlot27;
    public ContainerShipmentFiller(InventoryPlayer invPlayer, TileEntityShipmentFiller tileEntity) {
        this.player = invPlayer;
        this.tileEntity = tileEntity;
        this.inputSlot = new Slot(tileEntity, 0, 8, 6);
        this.boxSlot = new Slot(tileEntity, 1, 8, 63);
        this.itemSlot0 = new Slot(tileEntity, 2, 38, 6);
        this.itemSlot1 = new Slot(tileEntity, PLAYER_INVENTORY_ROW_COUNT, 57, 6);
        this.itemSlot2 = new Slot(tileEntity, 4, 76, 6);
        this.itemSlot3 = new Slot(tileEntity, 5, 95, 6);
        this.itemSlot4 = new Slot(tileEntity, 6, 114, 6);
        this.itemSlot5 = new Slot(tileEntity, 7, 133, 6);
        this.itemSlot6 = new Slot(tileEntity, 8, 152, 6);
        this.itemSlot7 = new Slot(tileEntity, PLAYER_INVENTORY_COLUMN_COUNT, 38, 25);
        this.itemSlot8 = new Slot(tileEntity, 10, 57, 25);
        this.itemSlot9 = new Slot(tileEntity, 11, 76, 25);
        this.itemSlot10 = new Slot(tileEntity, 12, 95, 25);
        this.itemSlot11 = new Slot(tileEntity, 13, 114, 25);
        this.itemSlot12 = new Slot(tileEntity, 14, 133, 25);
        this.itemSlot13 = new Slot(tileEntity, 15, 152, 25);
        this.itemSlot14 = new Slot(tileEntity, 16, 38, 44);
        this.itemSlot15 = new Slot(tileEntity, 17, 57, 44);
        this.itemSlot16 = new Slot(tileEntity, 18, 76, 44);
        this.itemSlot17 = new Slot(tileEntity, 19, 95, 44);
        this.itemSlot18 = new Slot(tileEntity, 20, 114, 44);
        this.itemSlot19 = new Slot(tileEntity, 21, 133, 44);
        this.itemSlot20 = new Slot(tileEntity, 22, 152, 44);
        this.itemSlot21 = new Slot(tileEntity, 23, 38, 63);
        this.itemSlot22 = new Slot(tileEntity, 24, 57, 63);
        this.itemSlot23 = new Slot(tileEntity, 25, 76, 63);
        this.itemSlot24 = new Slot(tileEntity, 26, 95, 63);
        this.itemSlot25 = new Slot(tileEntity, 27, 114, 63);
        this.itemSlot26 = new Slot(tileEntity, 28, 133, 63);
        this.itemSlot27 = new Slot(tileEntity, 29, 152, 63);
        func_75146_a(this.inputSlot);
        func_75146_a(this.boxSlot);
        func_75146_a(this.itemSlot0);
        func_75146_a(this.itemSlot1);
        func_75146_a(this.itemSlot2);
        func_75146_a(this.itemSlot3);
        func_75146_a(this.itemSlot4);
        func_75146_a(this.itemSlot5);
        func_75146_a(this.itemSlot6);
        func_75146_a(this.itemSlot7);
        func_75146_a(this.itemSlot8);
        func_75146_a(this.itemSlot9);
        func_75146_a(this.itemSlot10);
        func_75146_a(this.itemSlot11);
        func_75146_a(this.itemSlot12);
        func_75146_a(this.itemSlot13);
        func_75146_a(this.itemSlot14);
        func_75146_a(this.itemSlot15);
        func_75146_a(this.itemSlot16);
        func_75146_a(this.itemSlot17);
        func_75146_a(this.itemSlot18);
        func_75146_a(this.itemSlot19);
        func_75146_a(this.itemSlot20);
        func_75146_a(this.itemSlot21);
        func_75146_a(this.itemSlot22);
        func_75146_a(this.itemSlot23);
        func_75146_a(this.itemSlot24);
        func_75146_a(this.itemSlot25);
        func_75146_a(this.itemSlot26);
        func_75146_a(this.itemSlot27);
        for (int playerSlotIndexY = 0; playerSlotIndexY < PLAYER_INVENTORY_ROW_COUNT; playerSlotIndexY++) {
            for (int playerSlotIndexX = 0; playerSlotIndexX < PLAYER_INVENTORY_COLUMN_COUNT; playerSlotIndexX++) {
                func_75146_a(new Slot(this.player, playerSlotIndexX + (playerSlotIndexY * PLAYER_INVENTORY_COLUMN_COUNT) + PLAYER_INVENTORY_COLUMN_COUNT, 8 + (playerSlotIndexX * 18), 84 + (playerSlotIndexY * 18)));
            }
        }
        for (int hotbarSlotIndex = 0; hotbarSlotIndex < PLAYER_INVENTORY_COLUMN_COUNT; hotbarSlotIndex++) {
            func_75146_a(new Slot(this.player, hotbarSlotIndex, 8 + (hotbarSlotIndex * 18), 142));
        }
    }
    public ItemStack func_184996_a(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        ItemStack heldStack = player.field_71071_by.func_70445_o();
        if (slotId == 0) {
            if (this.boxSlot.func_75211_c().func_190926_b()) {
                return ItemStack.field_190927_a;
            }
            if (this.boxSlot.func_75211_c().func_77973_b() instanceof ItemShipmentBox) {
                Item boxItem = this.boxSlot.func_75211_c().func_77973_b();
                ItemStack boxStack = this.boxSlot.func_75211_c();
                if (!(boxItem instanceof ItemShipmentBox)) {
                    return ItemStack.field_190927_a;
                }
                if (!boxStack.func_77942_o()) {
                    boxStack.func_77982_d(new NBTTagCompound());
                }
                int boxWeight = boxStack.func_77978_p().func_74762_e("weight");
                if (ItemShipmentBox.getNewWeight(heldStack.func_77973_b(), boxWeight) > 100) {
                    return ItemStack.field_190927_a;
                }
                if (this.tileEntity.func_94041_b(0, heldStack)) {
                    return super.func_184996_a(slotId, dragType, clickTypeIn, player);
                }
                return ItemStack.field_190927_a;
            }
            if ((this.boxSlot.func_75211_c().func_77973_b() instanceof ItemShipmentBox) && this.tileEntity.func_94041_b(0, heldStack)) {
                return super.func_184996_a(slotId, dragType, clickTypeIn, player);
            }
            return ItemStack.field_190927_a;
        }
        if (slotId == 1) {
            if (this.tileEntity.func_94041_b(1, heldStack) && this.boxSlot.func_75211_c().func_190926_b()) {
                return super.func_184996_a(slotId, dragType, clickTypeIn, player);
            }
            if (heldStack.func_190926_b()) {
                return super.func_184996_a(slotId, dragType, clickTypeIn, player);
            }
            return ItemStack.field_190927_a;
        }
        if (slotId >= 2 && slotId <= 29) {
            if (((Slot) this.field_75151_b.get(slotId)).func_75211_c().func_190926_b() || !heldStack.func_190926_b()) {
                return ItemStack.field_190927_a;
            }
            return super.func_184996_a(slotId, dragType, clickTypeIn, player);
        }
        return super.func_184996_a(slotId, dragType, clickTypeIn, player);
    }
    public boolean func_75145_c(EntityPlayer playerIn) {
        return this.tileEntity.func_70300_a(playerIn);
    }
    public void func_75132_a(IContainerListener listener) {
        super.func_75132_a(listener);
        listener.func_175173_a(this, this.tileEntity);
    }
    @SideOnly(Side.CLIENT)
    public void func_75137_b(int id, int data) {
        this.tileEntity.func_174885_b(id, data);
    }
    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        return ItemStack.field_190927_a;
    }
}
