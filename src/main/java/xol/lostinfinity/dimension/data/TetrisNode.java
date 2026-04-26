package xol.lostinfinity.dimension.data;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/TetrisNode.class */
public class TetrisNode {
    private int xpos;
    private int ypos;
    private boolean visited = false;

    public TetrisNode(int gridx, int gridy) {
        this.xpos = gridx;
        this.ypos = gridy;
    }

    public void setVisited() {
        this.visited = true;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public int getX() {
        return this.xpos;
    }

    public int getZ() {
        return this.ypos;
    }

    public List<TetrisNode> getNeighbours(TetrisMap map) {
        int x = getX();
        int z = getZ();
        List<TetrisNode> neighbours = new ArrayList<>();
        neighbours.add(map.getNodeAtLocation(x, z + 1));
        neighbours.add(map.getNodeAtLocation(x, z - 1));
        neighbours.add(map.getNodeAtLocation(x + 1, z));
        neighbours.add(map.getNodeAtLocation(x - 1, z));
        return neighbours;
    }
}
