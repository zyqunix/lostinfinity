package xol.lostinfinity.dimension.data;
import java.util.ArrayList;
import java.util.List;
public class MazeNode {
    private int xpos;
    private int ypos;
    private int zpos;
    private String type;
    private boolean end = false;
    private boolean visited = false;
    public MazeNode(String type, int gridx, int gridy, int gridz) {
        this.type = type;
        this.xpos = gridx;
        this.ypos = gridy;
        this.zpos = gridz;
    }
    public boolean compare(MazeNode node) {
        int x1 = getX();
        int x2 = node.getX();
        int z1 = getZ();
        int z2 = node.getZ();
        return x1 == x2 && z1 == z2;
    }
    public void setEnd() {
        this.end = true;
    }
    public boolean isEnd() {
        return this.end;
    }
    public int getX() {
        return this.xpos;
    }
    public int getY() {
        return this.ypos;
    }
    public int getZ() {
        return this.zpos;
    }
    public void setX(int x) {
        this.xpos = x;
    }
    public void setY(int y) {
        this.ypos = y;
    }
    public void setZ(int z) {
        this.zpos = z;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public MazeNode connect(MazeNode neighbour, MazeMap map) {
        int x1 = getX();
        int z1 = getZ();
        int x2 = neighbour.getX();
        int z2 = neighbour.getZ();
        MazeNode node = null;
        switch (x1 - x2) {
            case -2:
                node = map.getNodeAtLocation(x1 + 1, z1);
                break;
            case 0:
                switch (z1 - z2) {
                    case -2:
                        node = map.getNodeAtLocation(x1, z1 + 1);
                        break;
                    case 2:
                        node = map.getNodeAtLocation(x1, z1 - 1);
                        break;
                }
                break;
            case 2:
                node = map.getNodeAtLocation(x1 - 1, z1);
                break;
        }
        return node;
    }
    public List<MazeNode> getNeighbours(MazeMap mazeMap) {
        List<MazeNode> neighbours = new ArrayList<>();
        int x = getX();
        int z = getZ();
        if (mazeMap.getNodeAtLocation(x, z + 2) != null && !mazeMap.getNodeAtLocation(x, z + 2).isVisited()) {
            neighbours.add(mazeMap.getNodeAtLocation(x, z + 2));
        }
        if (mazeMap.getNodeAtLocation(x, z - 2) != null && !mazeMap.getNodeAtLocation(x, z - 2).isVisited()) {
            neighbours.add(mazeMap.getNodeAtLocation(x, z - 2));
        }
        if (mazeMap.getNodeAtLocation(x + 2, z) != null && !mazeMap.getNodeAtLocation(x + 2, z).isVisited()) {
            neighbours.add(mazeMap.getNodeAtLocation(x + 2, z));
        }
        if (mazeMap.getNodeAtLocation(x - 2, z) != null && !mazeMap.getNodeAtLocation(x - 2, z).isVisited()) {
            neighbours.add(mazeMap.getNodeAtLocation(x - 2, z));
        }
        return neighbours;
    }
    public boolean isVisited() {
        return this.visited;
    }
    public void setVisited() {
        this.visited = true;
    }
}
