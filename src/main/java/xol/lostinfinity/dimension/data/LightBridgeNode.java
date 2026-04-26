package xol.lostinfinity.dimension.data;

import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/LightBridgeNode.class */
public class LightBridgeNode {
    private int x;
    private int y;
    private boolean lit;

    public LightBridgeNode(int x, int y, boolean lit) {
        this.x = x;
        this.y = y;
        this.lit = lit;
    }

    public boolean isLit() {
        return this.lit;
    }

    public void setLit(boolean lit) {
        this.lit = lit;
    }

    public void toggle() {
        if (this.lit) {
            this.lit = false;
        } else {
            this.lit = true;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<LightBridgeNode> getNeighbours(LightBridgeNode[][] bridgeMap) {
        ArrayList<LightBridgeNode> neighbours = new ArrayList<>();
        int x = getX();
        int y = getY();
        if (getNodeAtLocation(bridgeMap, x, y + 1) != null) {
            neighbours.add(getNodeAtLocation(bridgeMap, x, y + 1));
        }
        if (getNodeAtLocation(bridgeMap, x, y - 1) != null) {
            neighbours.add(getNodeAtLocation(bridgeMap, x, y - 1));
        }
        if (getNodeAtLocation(bridgeMap, x + 1, y) != null) {
            neighbours.add(getNodeAtLocation(bridgeMap, x + 1, y));
        }
        if (getNodeAtLocation(bridgeMap, x - 1, y) != null) {
            neighbours.add(getNodeAtLocation(bridgeMap, x - 1, y));
        }
        return neighbours;
    }

    public ArrayList<LightBridgeNode> getPathToNode(LightBridgeNode[][] bridgeMap, ArrayList<LightBridgeNode> visited, int y) {
        if (visited == null) {
            visited = new ArrayList<>();
        }
        visited.add(this);
        if (this.y == y) {
            return visited;
        }
        ArrayList<LightBridgeNode> neighbours = getNeighbours(bridgeMap);
        Collections.shuffle(neighbours);
        for (LightBridgeNode neighbour : neighbours) {
            boolean isLone = true;
            for (LightBridgeNode subNeighbour : neighbour.getNeighbours(bridgeMap)) {
                if (!subNeighbour.equals(this) && visited.contains(subNeighbour)) {
                    isLone = false;
                }
            }
            if (isLone) {
                return neighbour.getPathToNode(bridgeMap, visited, y);
            }
        }
        return null;
    }

    private LightBridgeNode getNodeAtLocation(LightBridgeNode[][] bridgeMap, int x, int y) {
        if (x >= 0 && x < bridgeMap.length && y >= 0 && y < bridgeMap[x].length) {
            return bridgeMap[x][y];
        }
        return null;
    }
}
