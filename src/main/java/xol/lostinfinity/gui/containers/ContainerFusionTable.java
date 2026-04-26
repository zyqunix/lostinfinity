package xol.lostinfinity.gui.containers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ItemInit;
public class ContainerFusionTable extends Container {
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int INPUT_SLOT_BOTTOM_INDEX = 0;
    private static final int INPUT_SLOT_TOP_INDEX = 1;
    private static final int OUTPUT_SLOT_INDEX = 2;
    private final InventoryPlayer invPlayer;
    private final TileEntityFusionTable tileEntity;
    private final Slot inputSlotTop;
    private final Slot inputSlotBottom;
    private final Slot outputSlot;
    private int boardSlot0;
    private int boardSlot1;
    private int boardSlot2;
    private int boardSlot3;
    private int boardSlot4;
    private int boardSlot5;
    private int boardSlot6;
    private int boardSlot7;
    private int boardSlot8;
    private int boardSlot9;
    private int boardSlot10;
    private int boardSlot11;
    private int boardSlot12;
    private int boardSlot13;
    private int boardSlot14;
    private int boardSlot15;
    private int boardSlot16;
    private int boardSlot17;
    private int boardSlot18;
    private int boardSlot19;
    private int boardSlot20;
    private int boardSlot21;
    private int boardSlot22;
    private int boardSlot23;
    private int upcomingSlot0;
    private int upcomingSlot1;
    private int progress;
    public ContainerFusionTable(InventoryPlayer invPlayer, TileEntityFusionTable tileEntity) {
        this.invPlayer = invPlayer;
        this.tileEntity = tileEntity;
        this.inputSlotTop = new Slot(tileEntity, INPUT_SLOT_BOTTOM_INDEX, 19, 10);
        this.inputSlotBottom = new Slot(tileEntity, INPUT_SLOT_TOP_INDEX, 19, 30);
        this.outputSlot = new Slot(tileEntity, OUTPUT_SLOT_INDEX, 142, 15);
        func_75146_a(this.inputSlotTop);
        func_75146_a(this.inputSlotBottom);
        func_75146_a(this.outputSlot);
        MinecraftForge.EVENT_BUS.register(this);
        for (int playerSlotRow = INPUT_SLOT_BOTTOM_INDEX; playerSlotRow < PLAYER_INVENTORY_ROW_COUNT; playerSlotRow += INPUT_SLOT_TOP_INDEX) {
            for (int playerSlotColumn = INPUT_SLOT_BOTTOM_INDEX; playerSlotColumn < PLAYER_INVENTORY_COLUMN_COUNT; playerSlotColumn += INPUT_SLOT_TOP_INDEX) {
                func_75146_a(new Slot(this.invPlayer, playerSlotColumn + (playerSlotRow * PLAYER_INVENTORY_COLUMN_COUNT) + PLAYER_INVENTORY_COLUMN_COUNT, 8 + (playerSlotColumn * 18), 84 + (playerSlotRow * 18)));
            }
        }
        for (int hotbarColumn = INPUT_SLOT_BOTTOM_INDEX; hotbarColumn < PLAYER_INVENTORY_COLUMN_COUNT; hotbarColumn += INPUT_SLOT_TOP_INDEX) {
            func_75146_a(new Slot(this.invPlayer, hotbarColumn, 8 + (hotbarColumn * 18), 142));
        }
    }
    public ItemStack func_184996_a(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        ItemStack heldStack = player.field_71071_by.func_70445_o();
        this.inputSlotTop.func_75211_c();
        this.inputSlotBottom.func_75211_c();
        this.outputSlot.func_75211_c();
        switch (slotId) {
            case INPUT_SLOT_BOTTOM_INDEX :
            case INPUT_SLOT_TOP_INDEX :
                if (heldStack.func_190926_b()) {
                    return super.func_184996_a(slotId, dragType, clickTypeIn, player);
                }
                if (!heldStack.func_77973_b().equals(ItemInit.inverseMagnecronite) && !heldStack.func_77973_b().equals(ItemInit.ioniteBar)) {
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
        int totalCount;
        Slot depositSlot;
        Slot clickedSlot = (Slot) this.field_75151_b.get(index);
        Slot inputSlot = (Slot) this.field_75151_b.get(INPUT_SLOT_TOP_INDEX);
        Slot inputSlot2 = (Slot) this.field_75151_b.get(INPUT_SLOT_BOTTOM_INDEX);
        Slot outputSlot = (Slot) this.field_75151_b.get(OUTPUT_SLOT_INDEX);
        if (clickedSlot != null && clickedSlot.func_75216_d()) {
            if (clickedSlot.equals(inputSlot) || clickedSlot.equals(inputSlot2) || clickedSlot.equals(outputSlot)) {
                if (!this.invPlayer.func_70441_a(clickedSlot.func_75211_c())) {
                    return ItemStack.field_190927_a;
                }
                clickedSlot.func_75215_d(ItemStack.field_190927_a);
                clickedSlot.func_75218_e();
            } else if (clickedSlot.field_75224_c.equals(this.invPlayer) && ((clickedSlot.func_75211_c().func_77973_b().equals(ItemInit.inverseMagnecronite) || clickedSlot.func_75211_c().func_77973_b().equals(ItemInit.ioniteBar)) && (clickedSlot.func_75211_c().func_77973_b().equals(ItemInit.inverseMagnecronite) || clickedSlot.func_75211_c().func_77973_b().equals(ItemInit.ioniteBar) || !inputSlot.func_75216_d() || !inputSlot2.func_75216_d()))) {
                int clickedCount = clickedSlot.func_75211_c().func_190916_E();
                if (inputSlot.func_75211_c().func_77973_b().equals(clickedSlot.func_75211_c().func_77973_b()) || !inputSlot.func_75216_d()) {
                    int inputCount = inputSlot.func_75211_c().func_190916_E();
                    totalCount = inputCount + clickedCount;
                    depositSlot = inputSlot;
                } else if (inputSlot2.func_75211_c().func_77973_b().equals(clickedSlot.func_75211_c().func_77973_b()) || !inputSlot2.func_75216_d()) {
                    int inputCount2 = inputSlot2.func_75211_c().func_190916_E();
                    totalCount = inputCount2 + clickedCount;
                    depositSlot = inputSlot2;
                } else {
                    return ItemStack.field_190927_a;
                }
                if (totalCount > depositSlot.func_75219_a()) {
                    depositSlot.func_75215_d(new ItemStack(clickedSlot.func_75211_c().func_77973_b(), inputSlot.func_75219_a()));
                    depositSlot.func_75218_e();
                    clickedSlot.func_75215_d(new ItemStack(clickedSlot.func_75211_c().func_77973_b(), totalCount - inputSlot.func_75219_a()));
                    clickedSlot.func_75218_e();
                    return ItemStack.field_190927_a;
                }
                depositSlot.func_75215_d(new ItemStack(clickedSlot.func_75211_c().func_77973_b(), totalCount));
                depositSlot.func_75218_e();
                clickedSlot.func_75215_d(ItemStack.field_190927_a);
                clickedSlot.func_75218_e();
                return ItemStack.field_190927_a;
            }
        }
        return ItemStack.field_190927_a;
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
    public void func_75142_b() {
        super.func_75142_b();
        for (int i = INPUT_SLOT_BOTTOM_INDEX; i < this.field_75149_d.size(); i += INPUT_SLOT_TOP_INDEX) {
            IContainerListener listener = (IContainerListener) this.field_75149_d.get(i);
            if (this.boardSlot0 != this.tileEntity.func_174887_a_(INPUT_SLOT_BOTTOM_INDEX)) {
                listener.func_71112_a(this, INPUT_SLOT_BOTTOM_INDEX, this.tileEntity.func_174887_a_(INPUT_SLOT_BOTTOM_INDEX));
                this.boardSlot0 = this.tileEntity.func_174887_a_(INPUT_SLOT_BOTTOM_INDEX);
            }
            if (this.boardSlot1 != this.tileEntity.func_174887_a_(INPUT_SLOT_TOP_INDEX)) {
                listener.func_71112_a(this, INPUT_SLOT_TOP_INDEX, this.tileEntity.func_174887_a_(INPUT_SLOT_TOP_INDEX));
                this.boardSlot1 = this.tileEntity.func_174887_a_(INPUT_SLOT_TOP_INDEX);
            }
            if (this.boardSlot2 != this.tileEntity.func_174887_a_(OUTPUT_SLOT_INDEX)) {
                listener.func_71112_a(this, OUTPUT_SLOT_INDEX, this.tileEntity.func_174887_a_(OUTPUT_SLOT_INDEX));
                this.boardSlot2 = this.tileEntity.func_174887_a_(OUTPUT_SLOT_INDEX);
            }
            if (this.boardSlot3 != this.tileEntity.func_174887_a_(PLAYER_INVENTORY_ROW_COUNT)) {
                listener.func_71112_a(this, PLAYER_INVENTORY_ROW_COUNT, this.tileEntity.func_174887_a_(PLAYER_INVENTORY_ROW_COUNT));
                this.boardSlot3 = this.tileEntity.func_174887_a_(PLAYER_INVENTORY_ROW_COUNT);
            }
            if (this.boardSlot4 != this.tileEntity.func_174887_a_(4)) {
                listener.func_71112_a(this, 4, this.tileEntity.func_174887_a_(4));
                this.boardSlot4 = this.tileEntity.func_174887_a_(4);
            }
            if (this.boardSlot5 != this.tileEntity.func_174887_a_(5)) {
                listener.func_71112_a(this, 5, this.tileEntity.func_174887_a_(5));
                this.boardSlot5 = this.tileEntity.func_174887_a_(5);
            }
            if (this.boardSlot6 != this.tileEntity.func_174887_a_(6)) {
                listener.func_71112_a(this, 6, this.tileEntity.func_174887_a_(6));
                this.boardSlot6 = this.tileEntity.func_174887_a_(6);
            }
            if (this.boardSlot7 != this.tileEntity.func_174887_a_(7)) {
                listener.func_71112_a(this, 7, this.tileEntity.func_174887_a_(7));
                this.boardSlot7 = this.tileEntity.func_174887_a_(7);
            }
            if (this.boardSlot8 != this.tileEntity.func_174887_a_(8)) {
                listener.func_71112_a(this, 8, this.tileEntity.func_174887_a_(8));
                this.boardSlot8 = this.tileEntity.func_174887_a_(8);
            }
            if (this.boardSlot9 != this.tileEntity.func_174887_a_(PLAYER_INVENTORY_COLUMN_COUNT)) {
                listener.func_71112_a(this, PLAYER_INVENTORY_COLUMN_COUNT, this.tileEntity.func_174887_a_(PLAYER_INVENTORY_COLUMN_COUNT));
                this.boardSlot9 = this.tileEntity.func_174887_a_(PLAYER_INVENTORY_COLUMN_COUNT);
            }
            if (this.boardSlot10 != this.tileEntity.func_174887_a_(10)) {
                listener.func_71112_a(this, 10, this.tileEntity.func_174887_a_(10));
                this.boardSlot10 = this.tileEntity.func_174887_a_(10);
            }
            if (this.boardSlot11 != this.tileEntity.func_174887_a_(11)) {
                listener.func_71112_a(this, 11, this.tileEntity.func_174887_a_(11));
                this.boardSlot11 = this.tileEntity.func_174887_a_(11);
            }
            if (this.boardSlot12 != this.tileEntity.func_174887_a_(12)) {
                listener.func_71112_a(this, 12, this.tileEntity.func_174887_a_(12));
                this.boardSlot12 = this.tileEntity.func_174887_a_(12);
            }
            if (this.boardSlot13 != this.tileEntity.func_174887_a_(13)) {
                listener.func_71112_a(this, 13, this.tileEntity.func_174887_a_(13));
                this.boardSlot13 = this.tileEntity.func_174887_a_(13);
            }
            if (this.boardSlot14 != this.tileEntity.func_174887_a_(14)) {
                listener.func_71112_a(this, 14, this.tileEntity.func_174887_a_(14));
                this.boardSlot14 = this.tileEntity.func_174887_a_(14);
            }
            if (this.boardSlot15 != this.tileEntity.func_174887_a_(15)) {
                listener.func_71112_a(this, 15, this.tileEntity.func_174887_a_(15));
                this.boardSlot15 = this.tileEntity.func_174887_a_(15);
            }
            if (this.boardSlot16 != this.tileEntity.func_174887_a_(16)) {
                listener.func_71112_a(this, 16, this.tileEntity.func_174887_a_(16));
                this.boardSlot16 = this.tileEntity.func_174887_a_(16);
            }
            if (this.boardSlot17 != this.tileEntity.func_174887_a_(17)) {
                listener.func_71112_a(this, 17, this.tileEntity.func_174887_a_(17));
                this.boardSlot17 = this.tileEntity.func_174887_a_(17);
            }
            if (this.boardSlot18 != this.tileEntity.func_174887_a_(18)) {
                listener.func_71112_a(this, 18, this.tileEntity.func_174887_a_(18));
                this.boardSlot18 = this.tileEntity.func_174887_a_(18);
            }
            if (this.boardSlot19 != this.tileEntity.func_174887_a_(19)) {
                listener.func_71112_a(this, 19, this.tileEntity.func_174887_a_(19));
                this.boardSlot19 = this.tileEntity.func_174887_a_(19);
            }
            if (this.boardSlot20 != this.tileEntity.func_174887_a_(20)) {
                listener.func_71112_a(this, 20, this.tileEntity.func_174887_a_(20));
                this.boardSlot20 = this.tileEntity.func_174887_a_(20);
            }
            if (this.boardSlot21 != this.tileEntity.func_174887_a_(21)) {
                listener.func_71112_a(this, 21, this.tileEntity.func_174887_a_(21));
                this.boardSlot21 = this.tileEntity.func_174887_a_(21);
            }
            if (this.boardSlot22 != this.tileEntity.func_174887_a_(22)) {
                listener.func_71112_a(this, 22, this.tileEntity.func_174887_a_(22));
                this.boardSlot22 = this.tileEntity.func_174887_a_(22);
            }
            if (this.boardSlot23 != this.tileEntity.func_174887_a_(23)) {
                listener.func_71112_a(this, 23, this.tileEntity.func_174887_a_(23));
                this.boardSlot23 = this.tileEntity.func_174887_a_(23);
            }
            if (this.upcomingSlot0 != this.tileEntity.func_174887_a_(24)) {
                listener.func_71112_a(this, 24, this.tileEntity.func_174887_a_(24));
                this.upcomingSlot0 = this.tileEntity.func_174887_a_(24);
            }
            if (this.upcomingSlot1 != this.tileEntity.func_174887_a_(25)) {
                listener.func_71112_a(this, 25, this.tileEntity.func_174887_a_(25));
                this.upcomingSlot1 = this.tileEntity.func_174887_a_(25);
            }
            if (this.progress != this.tileEntity.func_174887_a_(27)) {
                listener.func_71112_a(this, 27, this.tileEntity.func_174887_a_(27));
                this.progress = this.tileEntity.func_174887_a_(27);
            }
        }
    }
}
