package xol.lostinfinity.dimension.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/PowerColliderGrid.class */
public class PowerColliderGrid {
    private ArrayList<ArrayList<PowerColliderNode>> paths = null;
    private PowerColliderNode[][] map;
    private int col;
    private int row;
    private int minLength;
    private int numPaths;

    public PowerColliderGrid(int col, int row, int minLength, int numPaths) {
        this.map = (PowerColliderNode[][]) null;
        this.col = 0;
        this.row = 0;
        this.minLength = 0;
        this.numPaths = 0;
        this.numPaths = numPaths;
        this.col = col;
        this.row = row;
        this.minLength = minLength;
        this.map = new PowerColliderNode[this.col][this.row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                this.map[i][j] = new PowerColliderNode(i, j);
            }
        }
        for (int iterations = 0; !findPaths() && iterations < 10000; iterations++) {
        }
    }

    public static void main(String[] args) {
        new PowerColliderGrid(10, 10, 10, 3);
    }

    public ArrayList<ArrayList<PowerColliderNode>> getPaths() {
        return this.paths;
    }

    private ArrayList<PowerColliderNode> getPath(int i) {
        return this.paths.get(i);
    }

    private boolean findPaths() {
        this.paths = new ArrayList<>();
        ArrayList<PowerColliderNode> visited = new ArrayList<>();
        Random rand = new Random();
        int endX = rand.nextInt(this.col);
        int endZ = rand.nextInt(this.row);
        PowerColliderNode endNode = getNodeAtLocation(endX, endZ);
        endNode.setEnd(true);
        visited.add(endNode);
        for (int i = 0; i < this.numPaths; i++) {
            ArrayList<PowerColliderNode> path = new ArrayList<>();
            if (endNode == null) {
                return false;
            }
            path.add(endNode);
            int tries = 0;
            while (true) {
                if (tries >= 100) {
                    break;
                }
                tries++;
                ArrayList<PowerColliderNode> neighbours1 = getNeighbours(path.get(path.size() - 1));
                Collections.shuffle(neighbours1);
                Iterator<PowerColliderNode> it = neighbours1.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    PowerColliderNode neighbour = it.next();
                    if (neighbour != null) {
                        int count = 0;
                        for (PowerColliderNode subNeighbour : getNeighbours(neighbour)) {
                            if (path.contains(subNeighbour)) {
                                count++;
                            }
                            for (ArrayList<PowerColliderNode> otherPath : this.paths) {
                                if (otherPath.contains(subNeighbour) && !path.contains(subNeighbour)) {
                                    count++;
                                }
                            }
                        }
                        boolean intersect = false;
                        for (ArrayList<PowerColliderNode> otherPath2 : this.paths) {
                            if (otherPath2.contains(neighbour)) {
                                intersect = true;
                            }
                        }
                        if (count <= 1 && !path.contains(neighbour) && !intersect) {
                            visited.add(neighbour);
                            path.add(neighbour);
                            break;
                        }
                    }
                }
                if (path.size() > this.minLength && rand.nextInt(20) == 0) {
                    this.paths.add(path);
                    break;
                }
            }
            if (tries == 100) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<PowerColliderNode> getNeighbours(PowerColliderNode node) {
        ArrayList<PowerColliderNode> neighbours = new ArrayList<>();
        neighbours.add(getNodeAtLocation(node.getX() + 1, node.getZ()));
        neighbours.add(getNodeAtLocation(node.getX() - 1, node.getZ()));
        neighbours.add(getNodeAtLocation(node.getX(), node.getZ() + 1));
        neighbours.add(getNodeAtLocation(node.getX(), node.getZ() - 1));
        return neighbours;
    }

    public PowerColliderNode getNodeAtLocation(int x, int z) {
        if (this.map != null && x >= 0 && x < this.map.length && z >= 0 && z < this.map[x].length) {
            return this.map[x][z];
        }
        return null;
    }
}
