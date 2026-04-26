package xol.lostinfinity.gui.containers;

import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerCompressionTable.class */
public class ContainerCompressionTable extends Container {
    private int progress;
    private final Block compTable;
    private final IInventory inv;
    private final BlockPos blockPos;
    private final World world;
    public CompressionTableStatus status = CompressionTableStatus.AWAITING_INPUT;

    public ContainerCompressionTable(InventoryPlayer player, World worldIn, BlockPos blockPosIn, Block compTable, IInventory inv) {
        this.compTable = compTable;
        this.blockPos = blockPosIn;
        this.world = worldIn;
        this.inv = inv;
        func_75146_a(new Slot(inv, 0, 39, 35) { // from class: xol.lostinfinity.gui.containers.ContainerCompressionTable.1
            public boolean func_75214_a(ItemStack stack) {
                Item[] validItems = {ItemInit.astralliumIngot, ItemInit.crystoniumIngot, ItemInit.detheriumIngot, ItemInit.emberiumIngot, ItemInit.incadiumIngot, ItemInit.hextoriumIngot, ItemInit.kylaxiumIngot, ItemInit.noxeriumIngot, ItemInit.olysiumIngot, ItemInit.velloriumIngot, ItemInit.xeroviumIngot, ItemInit.phytrosiumIngot, ItemInit.kyvoriumIngot, ItemInit.biosynthiumIngot, ItemInit.maliciumIngot, ItemInit.etheriumIngot, ItemInit.polariumIngot};
                return Arrays.stream(validItems).anyMatch(it -> {
                    return stack.func_77973_b().equals(it);
                });
            }
        });
        func_75146_a(new Slot(inv, 1, 116, 35) { // from class: xol.lostinfinity.gui.containers.ContainerCompressionTable.2
            public boolean func_75214_a(ItemStack stack) {
                return false;
            }

            public boolean func_82869_a(EntityPlayer playerIn) {
                return func_75216_d();
            }
        });
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                func_75146_a(new Slot(player, j + (i * 9) + 9, 8 + (j * 18), 84 + (i * 18)));
            }
        }
        for (int k = 0; k < 9; k++) {
            func_75146_a(new Slot(player, k, 8 + (k * 18), 142));
        }
    }

    public boolean func_75145_c(EntityPlayer playerIn) {
        return this.world.func_180495_p(this.blockPos).func_177230_c().equals(this.compTable) && playerIn.func_70092_e(((double) this.blockPos.func_177958_n()) + 0.5d, ((double) this.blockPos.func_177956_o()) + 0.5d, ((double) this.blockPos.func_177952_p()) + 0.5d) <= 64.0d;
    }

    public void func_75132_a(IContainerListener listener) {
        super.func_75132_a(listener);
        listener.func_175173_a(this, this.inv);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            IContainerListener icontainerlistener = (IContainerListener) this.field_75149_d.get(i);
            if (this.progress != this.inv.func_174887_a_(0)) {
                icontainerlistener.func_71112_a(this, 0, this.inv.func_174887_a_(0));
            }
        }
        this.progress = this.inv.func_174887_a_(0);
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int id, int data) {
        this.inv.func_174885_b(id, data);
    }

    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = (Slot) this.field_75151_b.get(index);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (index == 2) {
                if (!func_75135_a(itemstack1, 3, 38, true)) {
                    return ItemStack.field_190927_a;
                }
                slot.func_75220_a(itemstack1, itemstack);
            } else if (index != 0) {
                if (index >= 2 && index < 38 && !func_75135_a(itemstack1, 0, 1, false)) {
                    return ItemStack.field_190927_a;
                }
            } else if (!func_75135_a(itemstack1, 2, 38, false)) {
                return ItemStack.field_190927_a;
            }
            if (itemstack1.func_190926_b()) {
                slot.func_75215_d(ItemStack.field_190927_a);
            } else {
                slot.func_75218_e();
            }
            if (itemstack1.func_190916_E() == itemstack.func_190916_E()) {
                return ItemStack.field_190927_a;
            }
            slot.func_190901_a(playerIn, itemstack1);
        }
        return itemstack;
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerCompressionTable$CompressionTableStatus.class */
    public enum CompressionTableStatus {
        AWAITING_INPUT("Awaiting valid compression input..."),
        IN_PROGRESS("Compression in progress..."),
        COMPLETE("Compression has been completed."),
        VALID("Valid compression input detected, proceed.");

        final String descriptor;

        CompressionTableStatus(String descriptor) {
            this.descriptor = descriptor;
        }

        public int getColor() {
            switch (AnonymousClass3.$SwitchMap$xol$lostinfinity$gui$containers$ContainerCompressionTable$CompressionTableStatus[ordinal()]) {
                case 1:
                case 2:
                    return Reference.getDecimalColorFromRGB(250, 250, 100);
                case 3:
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    return Reference.getDecimalColorFromRGB(100, 220, 100);
                default:
                    return Reference.getDecimalColorFromRGB(255, 0, 0);
            }
        }
    }

    /* JADX INFO: renamed from: xol.lostinfinity.gui.containers.ContainerCompressionTable$3, reason: invalid class name */
    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/containers/ContainerCompressionTable$3.class */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$xol$lostinfinity$gui$containers$ContainerCompressionTable$CompressionTableStatus = new int[CompressionTableStatus.values().length];

        static {
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerCompressionTable$CompressionTableStatus[CompressionTableStatus.AWAITING_INPUT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerCompressionTable$CompressionTableStatus[CompressionTableStatus.IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerCompressionTable$CompressionTableStatus[CompressionTableStatus.VALID.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerCompressionTable$CompressionTableStatus[CompressionTableStatus.COMPLETE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
}
