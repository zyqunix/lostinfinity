package xol.lostinfinity.dimension.data;
import java.util.Random;
import net.minecraft.util.Rotation;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
public class GridNode {
    private String pieceType;
    private Rotation rotation;
    private int heightOffset;
    private Random rand;
    public GridNode() {
        this.rand = new Random();
        this.pieceType = "None";
        this.rotation = Rotation.NONE;
        this.heightOffset = 0;
    }
    public GridNode(String pieceType, int rotation, int yOff) {
        this.rand = new Random();
        this.pieceType = pieceType;
        this.rotation = rotationFromInt(rotation);
        this.heightOffset = yOff;
    }
    private Rotation rotationFromInt(int rotation) {
        switch (rotation) {
            case -1:
                return Rotation.COUNTERCLOCKWISE_90;
            case 0:
                return Rotation.NONE;
            case 1:
                return Rotation.CLOCKWISE_90;
            case 2:
                return Rotation.CLOCKWISE_180;
            case 3:
            case TileEntityFusionTable.BOARD_ROWS :
            case 5:
            case TileEntityFusionTable.BOARD_COLUMNS :
            default:
                return Rotation.NONE;
            case 7:
                return this.rand.nextBoolean() ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90;
            case 8:
                return this.rand.nextBoolean() ? Rotation.NONE : Rotation.CLOCKWISE_180;
            case 9:
                return rotationFromInt(this.rand.nextInt(4) - 1);
        }
    }
    public String getPieceType() {
        return this.pieceType;
    }
    public Rotation getRotation() {
        return this.rotation;
    }
    public int getHeightDiff() {
        return this.heightOffset;
    }
}
