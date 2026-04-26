package xol.lostinfinity.dimension.data;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.util.math.Vec3i;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/LaserTagMap.class */
public class LaserTagMap {
    private LaserNode[][] floor1;
    private LaserNode[][] floor2;
    private static final int iterations = 10000;
    private int columns;
    private int rows;
    private static final Random rand = new Random();
    private static ArrayList<Vec3i> dirs = null;

    public static void main(String[] args) {
        LaserTagMap map = new LaserTagMap(20, 20);
        LaserNode[][] floor1 = map.getFloor1();
        LaserNode[][] floor2 = map.getFloor2();
        for (int i = 0; i < floor1.length; i++) {
            for (int j = 0; j < floor1[0].length; j++) {
                if (floor1[i][j].isStair()) {
                    System.out.print("||");
                } else if (floor1[i][j].visited()) {
                    System.out.print("[]");
                } else {
                    System.out.print("{}");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        for (int i2 = 0; i2 < floor2.length; i2++) {
            for (int j2 = 0; j2 < floor2[0].length; j2++) {
                if (floor2[i2][j2].isStair()) {
                    System.out.print("||");
                } else if (floor2[i2][j2].visited()) {
                    System.out.print("[]");
                } else {
                    System.out.print("{}");
                }
            }
            System.out.println();
        }
    }

    public LaserTagMap(int columns, int rows) {
        this.floor1 = (LaserNode[][]) null;
        this.floor2 = (LaserNode[][]) null;
        this.columns = 0;
        this.rows = 0;
        dirs = new ArrayList<>();
        dirs.add(new Vec3i(1, 0, 0));
        dirs.add(new Vec3i(-1, 0, 0));
        dirs.add(new Vec3i(1, 0, 1));
        dirs.add(new Vec3i(1, 0, -1));
        dirs.add(new Vec3i(-1, 0, 1));
        dirs.add(new Vec3i(-1, 0, -1));
        dirs.add(new Vec3i(0, 0, 1));
        dirs.add(new Vec3i(0, 0, -1));
        this.columns = columns;
        this.rows = rows;
        this.floor1 = new LaserNode[columns][rows];
        this.floor2 = new LaserNode[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                this.floor1[i][j] = new LaserNode(i, j);
                this.floor2[i][j] = new LaserNode(i, j);
            }
        }
        generateFloor(true);
        generateFloor(false);
    }

    private void generateFloor(boolean firstFloor) {
        LaserNode[][] floor;
        if (firstFloor) {
            floor = getFloor1();
        } else {
            floor = getFloor2();
        }
        if (floor == null) {
            return;
        }
        for (int i = 0; i < iterations; i++) {
            int nodeX = rand.nextInt(this.columns);
            int nodeZ = rand.nextInt(this.rows);
            if (isValidPlacement(nodeX, nodeZ, firstFloor, true)) {
                int randDir = rand.nextInt(dirs.size());
                Vec3i dir = dirs.get(randDir);
                ArrayList<LaserNode> toVisit = traverse(nodeX, nodeZ, dir, firstFloor, true, null);
                for (LaserNode node : toVisit) {
                    node.setVisited();
                }
            }
        }
    }

    private ArrayList<LaserNode> traverse(int nodeX, int nodeZ, Vec3i dir, boolean firstFloor, boolean repeat, ArrayList<LaserNode> placed) {
        Vec3i newDir;
        if (placed == null) {
            placed = new ArrayList<>();
        }
        ArrayList<LaserNode> toPlace = new ArrayList<>();
        toPlace.add(getNodeAtLocation(nodeX, nodeZ, firstFloor));
        int length = rand.nextInt(4) + 2;
        for (int i = 1; i < length; i++) {
            if (!isValidPlacement(nodeX + (i * dir.func_177958_n()), nodeZ + (i * dir.func_177952_p()), firstFloor, false) || placed.contains(getNodeAtLocation(nodeX + (i * dir.func_177958_n()), nodeZ + (i * dir.func_177952_p()), firstFloor))) {
                toPlace.clear();
                return toPlace;
            }
            toPlace.add(getNodeAtLocation(nodeX + (i * dir.func_177958_n()), nodeZ + (i * dir.func_177952_p()), firstFloor));
        }
        if (repeat) {
            placed.addAll(toPlace);
            int lastX = nodeX + ((length - 1) * dir.func_177958_n());
            int lastZ = nodeZ + ((length - 1) * dir.func_177952_p());
            int numSplits = rand.nextInt(3);
            ArrayList<Vec3i> chosenDirs = new ArrayList<>();
            chosenDirs.add(dir);
            for (int i2 = 0; i2 <= numSplits; i2++) {
                int randDir = rand.nextInt(dirs.size());
                Vec3i vec3i = dirs.get(randDir);
                while (true) {
                    newDir = vec3i;
                    if (chosenDirs.contains(newDir)) {
                        int randDir2 = rand.nextInt(dirs.size());
                        vec3i = dirs.get(randDir2);
                    }
                }
                int startX = lastX + newDir.func_177958_n();
                int startZ = lastZ + newDir.func_177952_p();
                toPlace.addAll(traverse(startX, startZ, newDir, firstFloor, false, placed));
            }
        }
        return toPlace;
    }

    private boolean isValidPlacement(int nodeX, int nodeZ, boolean firstFloor, boolean canPlaceByWall) {
        if (nodeX >= this.columns - 4 && nodeZ >= this.rows - 4) {
            return false;
        }
        if (nodeX < 4 && nodeZ < 4) {
            return false;
        }
        boolean valid = true;
        if (getNodeAtLocation(nodeX, nodeZ, firstFloor) == null) {
            return false;
        }
        int i = -1;
        loop0: while (true) {
            if (i > 1) {
                break;
            }
            for (int j = -1; j <= 1; j++) {
                LaserNode node = getNodeAtLocation(nodeX + i, nodeZ + j, firstFloor);
                if (canPlaceByWall) {
                    if (node != null && node.visited()) {
                        valid = false;
                        break loop0;
                    }
                } else {
                    if (node == null || node.visited()) {
                        break loop0;
                    }
                }
            }
            i++;
        }
        valid = false;
        return valid;
    }

    public LaserNode[][] getFloor1() {
        return this.floor1;
    }

    public LaserNode[][] getFloor2() {
        return this.floor2;
    }

    public LaserNode getNodeAtLocation(int x, int z, boolean firstFloor) {
        LaserNode[][] floor;
        if (firstFloor) {
            floor = getFloor1();
        } else {
            floor = getFloor2();
        }
        if (floor != null && x >= 0 && x < floor.length && z >= 0 && z < floor[x].length) {
            return floor[x][z];
        }
        return null;
    }
}
