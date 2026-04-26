package xol.lostinfinity.dimension.data;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/ParkourNode.class */
public class ParkourNode {
    private int height = 0;
    private boolean visited = false;
    private int x;
    private int z;

    public ParkourNode(int x, int z) {
        this.x = 0;
        this.z = 0;
        this.x = x;
        this.z = z;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isNeighbour(ParkourNode node) {
        if (getDistance(node) < 1.5d) {
            return false;
        }
        return this.height >= node.height ? getDistance(node) <= 4.0d : this.height + 1 == node.height && getDistance(node) <= 3.0d;
    }

    public double getDistance(ParkourNode node) {
        return Math.sqrt(Math.pow(this.x - node.x, 2.0d) + Math.pow(this.z - node.z, 2.0d));
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
