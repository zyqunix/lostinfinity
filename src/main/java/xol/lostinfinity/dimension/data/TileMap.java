package xol.lostinfinity.dimension.data;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
public class TileMap {
    private TileNode[][] map;
    private List<TileNode> remaining_nodes = new ArrayList();
    private Random rand = new Random();
    private int winx;
    private int winz;
    private int numColumns;
    private int numRows;
    public TileMap(int col, int row, int winnerx, int winnerz, int style) {
        this.map = new TileNode[col][row];
        this.numColumns = col;
        this.numRows = row;
        if (winnerx == -1) {
            this.winx = this.rand.nextInt(col);
        } else {
            this.winx = winnerx;
        }
        if (winnerz == -1) {
            this.winz = this.rand.nextInt(row);
        } else {
            this.winz = winnerz;
        }
        for (int cnum = 0; cnum < col; cnum++) {
            for (int rnum = 0; rnum < row; rnum++) {
                boolean is_winner = false;
                if (cnum == this.winx && rnum == this.winz) {
                    is_winner = true;
                }
                TileNode node = new TileNode(is_winner, cnum, getYfromXZStyle(cnum, rnum, style), rnum);
                this.map[cnum][rnum] = node;
                this.remaining_nodes.add(node);
            }
        }
    }
    public TileNode getNodeAtLocation(int col, int row) {
        if (col < 0 || row < 0 || col >= this.numColumns || row >= this.numRows || this.map[col][row].isDead()) {
            return null;
        }
        return this.map[col][row];
    }
    public int remainingTiles() {
        return this.remaining_nodes.size();
    }
    public TileNode randomLivingNode() {
        return this.remaining_nodes.get(this.rand.nextInt(this.remaining_nodes.size()));
    }
    public List<TileNode> removeTiles(int numRemove) {
        List<TileNode> removedNodes = new ArrayList<>();
        for (int i = 0; i < numRemove; i++) {
            boolean didRemove = false;
            TileNode node = null;
            for (int attempts = 0; !didRemove && attempts < 10; attempts++) {
                if (attempts < 10) {
                    boolean foundNode = false;
                    while (!foundNode) {
                        node = randomLivingNode();
                        if (node != null && !node.isDead() && !node.isWinnerNode()) {
                            foundNode = true;
                        }
                    }
                    boolean safeKill = true;
                    for (TileNode neighbour : node.getNeighbours(this)) {
                        if (neighbour != null) {
                            List<TileNode> checked = new ArrayList<>();
                            checked.add(node);
                            if (!neighbour.nodeHasPath(checked, this)) {
                                safeKill = false;
                            }
                        }
                    }
                    if (safeKill) {
                        node.setDead();
                        didRemove = true;
                        removedNodes.add(node);
                        this.remaining_nodes.remove(node);
                    }
                }
            }
        }
        return removedNodes;
    }
    private int getYfromXZStyle(int xline, int zline, int style) {
        int yheight = 28;
        switch (style) {
            case 1:
                if (xline % 2 == 1 || zline % 2 == 1) {
                    yheight = 28 + 1;
                }
                break;
            case 2:
                int xdiff = Math.abs(3 - xline);
                int zdiff = Math.abs(3 - zline);
                yheight = xdiff > zdiff ? 28 + xdiff : 28 + zdiff;
                break;
            case 3:
                int xdiff2 = Math.abs(3 - xline);
                int zdiff2 = Math.abs(3 - zline);
                yheight = xdiff2 > zdiff2 ? 31 - xdiff2 : 31 - zdiff2;
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                yheight = 28 + xline;
                break;
        }
        return yheight;
    }
}
