package xol.lostinfinity.dimension.data;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/BomberMap.class */
public class BomberMap {
    private int col;
    private int row;
    private static int softWallRatio = 50;
    private BomberNode[][] bomberGrid;

    public static void main(String[] args) {
        BomberMap bombMap = new BomberMap(31, 31, 10);
        bombMap.setSpawns(3);
        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 31; j++) {
                BomberNode node = bombMap.getNodeAtLocation(i, j);
                String type = node.getType();
                switch (type) {
                    case "hard":
                        System.out.print("[]");
                        break;
                    case "soft":
                        System.out.print("{}");
                        break;
                    case "spawn1":
                        System.out.print("^!");
                        break;
                    case "spawn2":
                        System.out.print("^|");
                        break;
                    case "spawn3":
                        System.out.print("!^");
                        break;
                    case "air":
                        System.out.print("||");
                        break;
                }
            }
            System.out.print("\r\n");
        }
    }

    public BomberMap(int columns, int rows, int numPowerups) {
        BomberNode node;
        this.col = columns;
        this.row = rows;
        this.bomberGrid = new BomberNode[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (i % 3 == 2 && j % 3 == 2) {
                    node = new BomberNode(i, 0, j, "hard");
                } else {
                    int randInt = (int) (Math.random() * 100.0d);
                    if (randInt < softWallRatio) {
                        node = new BomberNode(i, 0, j, "soft");
                    } else {
                        node = new BomberNode(i, 0, j, "air");
                    }
                }
                this.bomberGrid[i][j] = node;
            }
        }
        int powerUpsLeft = numPowerups;
        while (powerUpsLeft > 0) {
            int randCol = (int) (Math.random() * ((double) columns));
            int randRow = (int) (Math.random() * ((double) rows));
            BomberNode node2 = getNodeAtLocation(randCol, randRow);
            if (node2.getType().equals("soft")) {
                node2.setType("powerup");
                powerUpsLeft--;
            }
        }
    }

    public void setSpawns(int numPlayers) {
        int c = this.col;
        int r = this.row;
        int perimeter = (2 * c) + (2 * r);
        int distPoint = perimeter / numPlayers;
        BomberNode startNode = getNodeAtLocation(0, 0);
        startNode.setType("spawn1");
        List<BomberNode> spawns = findSpawnPointsFromPerimeterDist(startNode, distPoint, numPlayers);
        for (BomberNode spawn : spawns) {
            List<BomberNode> neighbours = spawn.getNeighbours(this);
            for (BomberNode neighbour : neighbours) {
                if (neighbour != null) {
                    neighbour.setType("air");
                }
            }
        }
    }

    public List<BomberNode> findSpawnPointsFromPerimeterDist(BomberNode startNode, int pointDist, int n) {
        List<BomberNode> spawnList = new ArrayList<>();
        spawnList.add(startNode);
        String side = "";
        int c = this.col - 1;
        int r = this.row - 1;
        int x = startNode.getX();
        int z = startNode.getZ();
        for (int i = 1; i < n; i++) {
            String type = "spawn" + Integer.toString(i + 1);
            System.out.println(type);
            int dist = pointDist;
            if (x == 0 && z >= 0) {
                side = "left";
            } else if (x > 0 && z == 0) {
                side = "bottom";
            } else if (x >= 0 && z == r) {
                side = "top";
            } else if (x == c && z >= 0) {
                side = "right";
            } else {
                System.out.println("bad things happened");
            }
            while (dist > 0) {
                switch (side) {
                    case "left":
                        if (dist <= r - z) {
                            z += dist;
                            dist = 0;
                            break;
                        } else {
                            dist -= r - z;
                            z = r;
                            side = "top";
                            break;
                        }
                        break;
                    case "top":
                        if (dist <= c - x) {
                            x += dist;
                            dist = 0;
                            break;
                        } else {
                            dist -= c - x;
                            x = c;
                            side = "right";
                            break;
                        }
                        break;
                    case "right":
                        if (dist <= z) {
                            z -= dist;
                            dist = 0;
                            break;
                        } else {
                            dist -= z;
                            z = 0;
                            side = "bottom";
                            break;
                        }
                        break;
                    case "bottom":
                        if (dist <= x) {
                            x -= dist;
                            dist = 0;
                            break;
                        } else {
                            dist -= x;
                            x = 0;
                            side = "left";
                            break;
                        }
                        break;
                }
            }
            BomberNode spawn = getNodeAtLocation(x, z);
            spawn.setType(type);
            spawnList.add(spawn);
        }
        return spawnList;
    }

    public BomberNode getNodeAtLocation(int col, int row) {
        if (col < 0 || row < 0 || col >= this.col || row >= this.row) {
            return null;
        }
        return this.bomberGrid[col][row];
    }
}
