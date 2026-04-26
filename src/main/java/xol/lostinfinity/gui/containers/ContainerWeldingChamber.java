package xol.lostinfinity.gui.containers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityWeldingChamber;
public class ContainerWeldingChamber extends Container {
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int INPUT_SLOT_INDEX = 0;
    private static final int OUTPUT_SLOT_INDEX = 1;
    private final InventoryPlayer invPlayer;
    private final TileEntityWeldingChamber tileEntity;
    private final Slot inputSlot;
    private final Slot outputSlot;
    private int currentSmeltTime;
    private int heat;
    private int acetylene;
    public ContainerWeldingChamber(InventoryPlayer invPlayer, TileEntityWeldingChamber tileEntity) {
        this.invPlayer = invPlayer;
        this.tileEntity = tileEntity;
        this.inputSlot = new Slot(tileEntity, INPUT_SLOT_INDEX, 17, 27);
        this.outputSlot = new Slot(tileEntity, OUTPUT_SLOT_INDEX, 143, 27);
        func_75146_a(this.inputSlot);
        func_75146_a(this.outputSlot);
        for (int playerSlotIndexY = INPUT_SLOT_INDEX; playerSlotIndexY < PLAYER_INVENTORY_ROW_COUNT; playerSlotIndexY += OUTPUT_SLOT_INDEX) {
            for (int playerSlotIndexX = INPUT_SLOT_INDEX; playerSlotIndexX < PLAYER_INVENTORY_COLUMN_COUNT; playerSlotIndexX += OUTPUT_SLOT_INDEX) {
                func_75146_a(new Slot(this.invPlayer, playerSlotIndexX + (playerSlotIndexY * PLAYER_INVENTORY_COLUMN_COUNT) + PLAYER_INVENTORY_COLUMN_COUNT, 8 + (playerSlotIndexX * 18), 84 + (playerSlotIndexY * 18)));
            }
        }
        for (int hotbarSlotIndex = INPUT_SLOT_INDEX; hotbarSlotIndex < PLAYER_INVENTORY_COLUMN_COUNT; hotbarSlotIndex += OUTPUT_SLOT_INDEX) {
            func_75146_a(new Slot(this.invPlayer, hotbarSlotIndex, 8 + (hotbarSlotIndex * 18), 142));
        }
    }
    public ItemStack func_184996_a(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        ItemStack heldStack = player.field_71071_by.func_70445_o();
        this.inputSlot.func_75211_c();
        this.outputSlot.func_75211_c();
        switch (slotId) {
            case INPUT_SLOT_INDEX :
                if (heldStack.func_190926_b()) {
                    return super.func_184996_a(slotId, dragType, clickTypeIn, player);
                }
                if (!heldStack.func_77973_b().func_77658_a().equalsIgnoreCase("item.atomite_fragments")) {
                    return ItemStack.field_190927_a;
                }
                break;
            case OUTPUT_SLOT_INDEX :
                if (!heldStack.func_190926_b()) {
                    return ItemStack.field_190927_a;
                }
                break;
            default:
                return super.func_184996_a(slotId, dragType, clickTypeIn, player);
        }
        return super.func_184996_a(slotId, dragType, clickTypeIn, player);
    }
    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        Slot clickedSlot = (Slot) this.field_75151_b.get(index);
        Slot inputSlot = (Slot) this.field_75151_b.get(INPUT_SLOT_INDEX);
        Slot outputSlot = (Slot) this.field_75151_b.get(OUTPUT_SLOT_INDEX);
        if (clickedSlot != null && clickedSlot.func_75216_d()) {
            if (clickedSlot.equals(inputSlot) || clickedSlot.equals(outputSlot)) {
                if (!this.invPlayer.func_70441_a(clickedSlot.func_75211_c())) {
                    return ItemStack.field_190927_a;
                }
                clickedSlot.func_75215_d(ItemStack.field_190927_a);
                clickedSlot.func_75218_e();
            } else if (clickedSlot.field_75224_c.equals(this.invPlayer) && clickedSlot.func_75211_c().func_77973_b().func_77658_a().equalsIgnoreCase("item.atomite_fragments") && (inputSlot.func_75211_c().func_77973_b().func_77658_a().equalsIgnoreCase("item.atomite_fragments") || !inputSlot.func_75216_d())) {
                int clickedCount = clickedSlot.func_75211_c().func_190916_E();
                int inputCount = inputSlot.func_75211_c().func_190916_E();
                int totalCount = clickedCount + inputCount;
                if (totalCount > inputSlot.func_75219_a()) {
                    inputSlot.func_75215_d(new ItemStack(clickedSlot.func_75211_c().func_77973_b(), inputSlot.func_75219_a()));
                    inputSlot.func_75218_e();
                    clickedSlot.func_75215_d(new ItemStack(clickedSlot.func_75211_c().func_77973_b(), totalCount - inputSlot.func_75219_a()));
                    clickedSlot.func_75218_e();
                    return ItemStack.field_190927_a;
                }
                inputSlot.func_75215_d(new ItemStack(clickedSlot.func_75211_c().func_77973_b(), totalCount));
                inputSlot.func_75218_e();
                clickedSlot.func_75215_d(ItemStack.field_190927_a);
                clickedSlot.func_75218_e();
                return ItemStack.field_190927_a;
            }
        }
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
        return this.tileEntity.func_70300_a(playerIn);
    }
    public void func_75142_b() {
        super.func_75142_b();
        for (int i = INPUT_SLOT_INDEX; i < this.field_75149_d.size(); i += OUTPUT_SLOT_INDEX) {
            IContainerListener listener = (IContainerListener) this.field_75149_d.get(i);
            if (this.currentSmeltTime != this.tileEntity.func_174887_a_(INPUT_SLOT_INDEX)) {
                listener.func_71112_a(this, INPUT_SLOT_INDEX, this.tileEntity.func_174887_a_(INPUT_SLOT_INDEX));
            }
            if (this.heat != this.tileEntity.func_174887_a_(OUTPUT_SLOT_INDEX)) {
                listener.func_71112_a(this, OUTPUT_SLOT_INDEX, this.tileEntity.func_174887_a_(OUTPUT_SLOT_INDEX));
            }
            if (this.acetylene != this.tileEntity.func_174887_a_(2)) {
                listener.func_71112_a(this, 2, this.tileEntity.func_174887_a_(2));
            }
        }
        this.currentSmeltTime = this.tileEntity.func_174887_a_(INPUT_SLOT_INDEX);
        this.heat = this.tileEntity.func_174887_a_(OUTPUT_SLOT_INDEX);
        this.acetylene = this.tileEntity.func_174887_a_(2);
    }
}
