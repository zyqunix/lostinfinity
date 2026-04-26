package xol.lostinfinity.dimension.data;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/PowerColliderNode.class */
public class PowerColliderNode {
    private int x;
    private int z;
    boolean isEnd = false;

    public PowerColliderNode(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
}
