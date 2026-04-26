package xol.lostinfinity.block.tileentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketFusionTable;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityFusionTable.class */
public class TileEntityFusionTable extends TileEntity implements IInventory, ITickable {
    private static final boolean DEBUG = false;
    public static final int BOARD_ROWS = 4;
    public static final int BOARD_COLUMNS = 6;
    public static final int BOARD_SIZE = 24;
    private NonNullList<ItemStack> inventory = NonNullList.func_191197_a(3, ItemStack.field_190927_a);
    private List<BoardPiece> board = new ArrayList();
    private List<Integer> upcomingPieces = new ArrayList();
    private int progress = DEBUG;

    public void func_73660_a() {
        int weight = DEBUG;
        for (BoardPiece piece : this.board) {
            weight += piece.getShapeId();
        }
        if (weight > 0) {
            if (this.field_145850_b.field_72995_K) {
                checkMatches();
            }
            if (this.progress == 6) {
                fuseCraft();
                return;
            }
        }
        if ((((ItemStack) this.inventory.get(DEBUG)).func_77973_b().equals(ItemInit.ioniteBar) || ((ItemStack) this.inventory.get(DEBUG)).func_77973_b().equals(ItemInit.inverseMagnecronite)) && ((((ItemStack) this.inventory.get(1)).func_77973_b().equals(ItemInit.ioniteBar) || ((ItemStack) this.inventory.get(1)).func_77973_b().equals(ItemInit.inverseMagnecronite)) && weight <= 0)) {
            if (this.field_145850_b.field_72995_K) {
                fillBoard();
                fillUpcomingPieces();
                return;
            }
            return;
        }
        if ((((ItemStack) this.inventory.get(DEBUG)).func_190926_b() || (((ItemStack) this.inventory.get(1)).func_190926_b() && weight > 0)) && this.field_145850_b.field_72995_K) {
            resetBoard();
        }
    }

    public int func_174887_a_(int id) {
        if (this.upcomingPieces.isEmpty() || this.board.isEmpty()) {
            return DEBUG;
        }
        if (id >= 0 && id < 24) {
            return this.board.get(id).getShapeId();
        }
        if (id == 24) {
            return this.upcomingPieces.get(DEBUG).intValue();
        }
        if (id == 25) {
            return this.upcomingPieces.get(1).intValue();
        }
        if (id == 27) {
            return this.progress;
        }
        return DEBUG;
    }

    public void func_174885_b(int id, int value) {
        if (!this.field_145850_b.field_72995_K && this.board.isEmpty()) {
            for (int i = DEBUG; i < 24; i++) {
                this.board.add(new BoardPiece(DEBUG, DEBUG, DEBUG));
            }
            if (this.upcomingPieces.isEmpty()) {
                this.upcomingPieces.add(Integer.valueOf(DEBUG));
                this.upcomingPieces.add(Integer.valueOf(DEBUG));
            }
        }
        if (id >= 0 && id < 24) {
            this.board.get(id).setShapeId(value);
            return;
        }
        if (id == 24) {
            this.upcomingPieces.set(DEBUG, Integer.valueOf(value));
        } else if (id == 25) {
            this.upcomingPieces.set(1, Integer.valueOf(value));
        } else if (id == 27) {
            this.progress = value;
        }
    }

    @SideOnly(Side.CLIENT)
    private void resetBoard() {
        this.board.clear();
        this.upcomingPieces.clear();
        for (int i = DEBUG; i < 4; i++) {
            for (int j = DEBUG; j < 6; j++) {
                this.board.add(new BoardPiece(i, j, DEBUG));
            }
        }
        this.upcomingPieces.add(Integer.valueOf(DEBUG));
        this.upcomingPieces.add(Integer.valueOf(DEBUG));
        this.progress = DEBUG;
        for (int i2 = DEBUG; i2 < 24; i2++) {
            lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(func_174877_v(), func_174887_a_(i2), i2));
        }
        lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(func_174877_v(), func_174887_a_(24), 24));
        lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(func_174877_v(), func_174887_a_(25), 25));
        lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(func_174877_v(), func_174887_a_(27), 27));
    }

    @SideOnly(Side.CLIENT)
    private void fillBoard() {
        for (int i = DEBUG; i < 4; i++) {
            for (int j = DEBUG; j < 6; j++) {
                this.board.add(new BoardPiece(i, j, DEBUG));
            }
        }
        int weight = DEBUG;
        for (int i2 = DEBUG; i2 < 4; i2++) {
            for (int j2 = DEBUG; j2 < 6; j2++) {
                int shapeId = new Random().nextInt(12) + 1;
                int boardIndex = (i2 * 6) + j2;
                weight = (shapeId > 0 && shapeId < 6) ? weight + 1 : weight - 1;
                this.board.set(boardIndex, new BoardPiece(i2, j2, shapeId));
            }
            new Random().nextInt(12);
        }
        if (weight != -4) {
            resetBoard();
            fillBoard();
        }
        for (int i3 = DEBUG; i3 < 24; i3++) {
            lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(func_174877_v(), func_174887_a_(i3), i3));
        }
    }

    @SideOnly(Side.CLIENT)
    private void fillUpcomingPieces() {
        this.upcomingPieces.add(Integer.valueOf(DEBUG));
        this.upcomingPieces.add(Integer.valueOf(DEBUG));
        for (int i = DEBUG; i < 2; i++) {
            int shapeId = new Random().nextInt(5) + 1;
            this.upcomingPieces.set(i, Integer.valueOf(shapeId));
        }
        lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(func_174877_v(), func_174887_a_(24), 24));
        lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(func_174877_v(), func_174887_a_(25), 25));
    }

    @SideOnly(Side.CLIENT)
    public void checkMatches() {
        if (this.board.isEmpty() || this.upcomingPieces.isEmpty()) {
            return;
        }
        for (int i = 1; i <= 24; i++) {
            if (i % 6 != 0) {
                if (i >= 19) {
                    break;
                }
                int currentPiece = this.board.get(i - 1).getShapeId();
                int nextPiece = this.board.get(i).getShapeId();
                int pieceBelow = this.board.get(i + 5).getShapeId();
                int pieceBelowNext = this.board.get(i + 6).getShapeId();
                if (currentPiece == nextPiece && currentPiece == pieceBelow && currentPiece == pieceBelowNext && currentPiece != 0) {
                    if (this.field_145850_b.field_72995_K) {
                        int clientProg = this.progress + 1;
                        shuffleBoard();
                        lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(func_174877_v(), clientProg, 27));
                        return;
                    }
                    return;
                }
            }
        }
        for (int i2 = DEBUG; i2 < 24 && this.board.get(i2).getShapeId() > 0 && this.board.get(i2).getShapeId() < 6; i2++) {
            if (i2 == 23 && this.field_145850_b.field_72995_K) {
                shuffleBoard();
                lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketFusionTable(func_174877_v(), DEBUG, 27));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private void shuffleBoard() {
        resetBoard();
        fillBoard();
        fillUpcomingPieces();
    }

    private void fuseCraft() {
        ItemStack output;
        if (this.field_145850_b.field_72995_K) {
            shuffleBoard();
        } else {
            ItemStack input1 = (ItemStack) this.inventory.get(DEBUG);
            ItemStack input2 = (ItemStack) this.inventory.get(1);
            this.progress = DEBUG;
            if (((ItemStack) this.inventory.get(2)).func_190926_b()) {
                output = new ItemStack(ItemInit.polyionite, 1);
            } else {
                output = (ItemStack) this.inventory.get(2);
                output.func_190920_e(output.func_190916_E() + 1);
            }
            input1.func_190920_e(input1.func_190916_E() - 1);
            input2.func_190920_e(input2.func_190916_E() - 1);
            this.inventory.set(DEBUG, input1);
            this.inventory.set(1, input2);
            this.inventory.set(2, output);
        }
        func_70296_d();
    }

    public int func_70302_i_() {
        return this.inventory.size();
    }

    public boolean func_191420_l() {
        for (ItemStack stack : this.inventory) {
            if (!stack.func_190926_b()) {
                return false;
            }
        }
        return true;
    }

    public ItemStack func_70301_a(int index) {
        return (ItemStack) this.inventory.get(index);
    }

    public ItemStack func_70298_a(int index, int count) {
        return ItemStackHelper.func_188382_a(this.inventory, index, count);
    }

    public ItemStack func_70304_b(int index) {
        return ItemStackHelper.func_188383_a(this.inventory, index);
    }

    public void func_70299_a(int index, ItemStack stack) {
        this.inventory.set(index, stack);
        int limit = func_70297_j_();
        if (stack.func_190916_E() > limit) {
            stack.func_190920_e(limit);
        }
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer player) {
        return this.field_145850_b.func_175625_s(this.field_174879_c) == this && player.func_70092_e(((double) this.field_174879_c.func_177958_n()) + 0.5d, ((double) this.field_174879_c.func_177956_o()) + 0.5d, ((double) this.field_174879_c.func_177952_p()) + 0.5d) <= 64.0d;
    }

    public void func_174889_b(EntityPlayer player) {
    }

    public void func_174886_c(EntityPlayer player) {
    }

    public boolean func_94041_b(int index, ItemStack stack) {
        return false;
    }

    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        super.func_189515_b(compound);
        compound.func_74768_a("progress", this.progress);
        ItemStackHelper.func_191282_a(compound, this.inventory);
        if (this.board.size() > 0) {
            for (int i = DEBUG; i < 24; i++) {
                compound.func_74768_a("board" + i, this.board.get(i).getShapeId());
            }
            compound.func_74768_a("upcomingPieces0", this.upcomingPieces.get(DEBUG).intValue());
            compound.func_74768_a("upcomingPieces1", this.upcomingPieces.get(1).intValue());
        }
        return compound;
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.progress = compound.func_74762_e("progress");
        ItemStackHelper.func_191283_b(compound, this.inventory);
        for (int i = DEBUG; i < 24; i++) {
            this.board.add(new BoardPiece(i / 6, i % 6, compound.func_74762_e("board" + i)));
        }
        this.upcomingPieces.add(Integer.valueOf(compound.func_74762_e("upcomingPieces0")));
        this.upcomingPieces.add(Integer.valueOf(compound.func_74762_e("upcomingPieces1")));
    }

    public int func_174890_g() {
        return 27;
    }

    public void func_174888_l() {
        this.inventory.clear();
    }

    public String func_70005_c_() {
        return "tile.fusion_table";
    }

    public boolean func_145818_k_() {
        return false;
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityFusionTable$BoardPiece.class */
    public class BoardPiece {
        private final int row;
        private final int column;
        private int shapeId;

        public BoardPiece(int row, int column, int shapeId) {
            this.row = row;
            this.column = column;
            this.shapeId = shapeId;
        }

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return this.column;
        }

        public int getShapeId() {
            return this.shapeId;
        }

        public void setShapeId(int shapeId) {
            this.shapeId = shapeId;
        }

        public String toString() {
            return "Row: " + this.row + " Column: " + this.column + " ShapeId: " + this.shapeId;
        }
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityFusionTable$Shapes.class */
    public enum Shapes {
        SQUARE(1, TileEntityFusionTable.DEBUG, 166),
        VERTICAL_RECTANGLE(2, 12, 166),
        DIAGONAL_RECTANGLE(3, 24, 166),
        TRIANGLE(4, 36, 166),
        HORIZONTAL_RECTANGLE(5, 48, 166);

        private final int id;
        private final int pixelX;
        private final int pixelY;

        Shapes(int id, int pixelX, int pixelY) {
            this.id = id;
            this.pixelX = pixelX;
            this.pixelY = pixelY;
        }

        public int getId() {
            return this.id;
        }

        public int getPixelX() {
            return this.pixelX;
        }

        public int getPixelY() {
            return this.pixelY;
        }

        public static Shapes getShapeFromId(int id) {
            Shapes[] shapesArrValues = values();
            int length = shapesArrValues.length;
            for (int i = TileEntityFusionTable.DEBUG; i < length; i++) {
                Shapes shape = shapesArrValues[i];
                if (shape.getId() == id) {
                    return shape;
                }
            }
            return null;
        }
    }
}
