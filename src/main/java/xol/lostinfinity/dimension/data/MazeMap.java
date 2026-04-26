package xol.lostinfinity.dimension.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/MazeMap.class */
public class MazeMap {
    private MazeNode[][] map;
    private List<MazeNode> remaining_nodes = new ArrayList();
    private Random rand = new Random();
    private static int startx = 0;
    private static int startz = 0;
    private static int wallPercent = 25;
    private static int triggerPercent = 4;
    private int numColumns;
    private int numRows;

    public MazeMap(int col, int row) {
        MazeNode mazeNode;
        this.map = new MazeNode[col][row];
        this.numColumns = col;
        this.numRows = row;
        for (int cnum = 0; cnum < col; cnum++) {
            for (int rnum = 0; rnum < row; rnum++) {
                if (cnum % 2 == 0) {
                    if (rnum % 2 == 0) {
                        mazeNode = new MazeNode("path", cnum, 0, rnum);
                    } else {
                        mazeNode = new MazeNode("wall", cnum, 0, rnum);
                    }
                } else if (rnum % 2 == 0) {
                    mazeNode = new MazeNode("wall", cnum, 0, rnum);
                } else {
                    mazeNode = new MazeNode("path", cnum, 0, rnum);
                }
                MazeNode node = mazeNode;
                this.map[cnum][rnum] = node;
            }
        }
        drawMap();
    }

    private void drawMap() {
        MazeNode startNode = getNodeAtLocation(startx, startz);
        int width = this.numColumns;
        int height = this.numRows;
        startNode.setVisited();
        Stack<MazeNode> pathStack = new Stack<>();
        pathStack.push(startNode);
        while (!pathStack.empty()) {
            MazeNode cell = pathStack.pop();
            List<MazeNode> neighbours = cell.getNeighbours(this);
            if (!neighbours.isEmpty()) {
                int randIdx = (int) (Math.random() * ((double) neighbours.size()));
                for (int i = 0; i < neighbours.size(); i++) {
                    MazeNode neighbour = neighbours.get(i);
                    MazeNode wall = cell.connect(neighbour, this);
                    wall.setType("path");
                    wall.setVisited();
                    neighbour.setType("path");
                    neighbour.setVisited();
                    if (i != randIdx) {
                        pathStack.push(neighbour);
                    }
                }
                pathStack.push(neighbours.get(randIdx));
            }
        }
        for (int i2 = 0; i2 < width; i2++) {
            for (int j = 0; j < height; j++) {
                int randInt = (int) (Math.random() * 100.0d);
                MazeNode node = getNodeAtLocation(i2, j);
                if (randInt < 4 && node.isVisited()) {
                    node.setType("trigger");
                }
            }
        }
    }

    public MazeNode getNodeAtLocation(int col, int row) {
        if (col < 0 || row < 0 || col >= this.numColumns || row >= this.numRows) {
            return null;
        }
        return this.map[col][row];
    }
}
