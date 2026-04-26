package xol.lostinfinity.dimension.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/TetrisMap.class */
public class TetrisMap {
    private TetrisNode[][] map;
    private int numColumns;
    private int numRows;
    private int complexity;
    private int[] pieceList;
    private static int[][][] linePiece = {new int[]{new int[]{0, 1, 2, 3}, new int[]{0, 0, 0, 0}}, new int[]{new int[]{0, 0, 0, 0}, new int[]{0, 1, 2, 3}}};
    private static int[][][] lPiece = {new int[]{new int[]{0, 1, 2, 2}, new int[]{0, 0, 0, 1}}, new int[]{new int[]{0, 0, 0, 1}, new int[]{0, 1, 2, 2}}, new int[]{new int[]{0, 0, 0, -1}, new int[]{0, 1, 2, 2}}, new int[]{new int[]{-1, 0, 0, 0}, new int[]{0, 0, 1, 2}}, new int[]{new int[]{1, 0, 0, 0}, new int[]{0, 0, 1, 2}}, new int[]{new int[]{0, 1, 2, 2}, new int[]{0, 0, 0, -1}}, new int[]{new int[]{0, 0, 1, 2}, new int[]{-1, 0, 0, 0}}, new int[]{new int[]{0, 0, 1, 2}, new int[]{1, 0, 0, 0}}};
    private static int[][][] tPiece = {new int[]{new int[]{0, 1, 1, 2}, new int[]{0, 0, -1, 0}}, new int[]{new int[]{0, 1, 1, 1}, new int[]{0, 1, 0, -1}}, new int[]{new int[]{0, 0, 0, 1}, new int[]{1, 0, -1, 0}}, new int[]{new int[]{0, 1, 1, 2}, new int[]{0, 0, 1, 0}}};
    private static int[][][] zigPiece = {new int[]{new int[]{0, 0, 1, 1}, new int[]{-1, 0, 0, 1}}, new int[]{new int[]{1, 1, 0, 0}, new int[]{1, 0, 0, -1}}, new int[]{new int[]{0, 1, 1, 2}, new int[]{0, 0, 1, 1}}, new int[]{new int[]{0, 1, 1, 2}, new int[]{0, 0, -1, -1}}};
    private static int[][][] squarePiece = {new int[]{new int[]{0, 0, 1, 1}, new int[]{0, 1, 0, 1}}};
    private static final Random random = new Random();
    private static int startx = 0;
    private static int startz = 0;
    private static int[][] combinationsFactor4 = {new int[]{1, 1, 2, 0, 0}, new int[]{1, 2, 0, 0, 1}, new int[]{2, 0, 0, 0, 2}, new int[]{2, 2, 0, 0, 0}, new int[]{0, 2, 0, 0, 2}, new int[]{0, 1, 2, 1, 0}, new int[]{1, 2, 0, 1, 0}};
    private Random rand = new Random();
    private List<int[][][]> pieceCollection = new ArrayList();
    private List<TetrisNode> activeNodes = new ArrayList();

    public TetrisMap(int n, int m) {
        this.pieceList = null;
        this.pieceCollection.add(linePiece);
        this.pieceCollection.add(lPiece);
        this.pieceCollection.add(tPiece);
        this.pieceCollection.add(zigPiece);
        this.pieceCollection.add(squarePiece);
        this.map = new TetrisNode[n][m];
        this.numColumns = n;
        this.numRows = m;
        for (int cnum = 0; cnum < n; cnum++) {
            for (int rnum = 0; rnum < m; rnum++) {
                TetrisNode node = new TetrisNode(cnum, rnum);
                this.map[cnum][rnum] = node;
            }
        }
        if (n % 4 == 0 && m % 4 == 0) {
            this.pieceList = generate4FactorPieceList(n, m);
        }
    }

    public int[] getPieceList() {
        return this.pieceList;
    }

    public static void main(String[] args) {
        new TetrisMap(4, 8);
    }

    private int[] generate4FactorPieceList(int n, int m) {
        int[] pieceList = new int[5];
        ArrayList<int[]> combinationList = new ArrayList<>();
        for (int i = 0; i < combinationsFactor4.length; i++) {
            combinationList.add(combinationsFactor4[i]);
        }
        Collections.shuffle(combinationList);
        int picksLeft = (n / 4) * (m / 4);
        for (int i2 = 0; i2 < combinationList.size(); i2++) {
            int picks = this.rand.nextInt(picksLeft + 1);
            picksLeft -= picks;
            int[] comb = combinationList.get(i2);
            for (int j = 0; j < comb.length; j++) {
                int i3 = j;
                pieceList[i3] = pieceList[i3] + (comb[j] * picks);
            }
            if (picksLeft <= 0) {
                break;
            }
        }
        return pieceList;
    }

    public TetrisNode[][] getGrid() {
        return this.map;
    }

    public int getCol() {
        return this.numColumns;
    }

    public int getRow() {
        return this.numRows;
    }

    public List<TetrisNode> getNodesAtOrientation(int i, int j, int[][] orientation) {
        List<TetrisNode> nodes = new ArrayList<>();
        for (int k = 0; k < 4; k++) {
            int x = orientation[0][k];
            int z = orientation[1][k];
            TetrisNode node = getNodeAtLocation(i + x, j + z);
            nodes.add(node);
        }
        return nodes;
    }

    public TetrisNode getNodeAtLocation(int col, int row) {
        if (col < 0 || row < 0 || col >= this.numColumns || row >= this.numRows) {
            return null;
        }
        return this.map[col][row];
    }
}
