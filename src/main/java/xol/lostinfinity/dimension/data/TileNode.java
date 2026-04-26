package xol.lostinfinity.dimension.data;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/TileNode.class */
public class TileNode {
    private boolean is_winner;
    private boolean is_dead = false;
    private int xpos;
    private int ypos;
    private int zpos;

    public TileNode(boolean winner, int gridx, int gridy, int gridz) {
        this.is_winner = false;
        this.is_winner = winner;
        this.xpos = gridx;
        this.ypos = gridy;
        this.zpos = gridz;
    }

    public boolean nodeHasPath(List<TileNode> checked_nodes, TileMap tile_map) {
        if (this.is_winner) {
            return true;
        }
        if (isDead()) {
            return false;
        }
        for (TileNode node : checked_nodes) {
            if (this.xpos == node.getX() && this.zpos == node.getZ()) {
                return false;
            }
        }
        checked_nodes.add(this);
        for (TileNode neighbour : getNeighbours(tile_map)) {
            if (neighbour != null && neighbour.nodeHasPath(checked_nodes, tile_map)) {
                return true;
            }
        }
        return false;
    }

    public List<TileNode> getNeighbours(TileMap tilemap) {
        List<TileNode> neighbours = new ArrayList<>();
        neighbours.add(tilemap.getNodeAtLocation(this.xpos + 1, this.zpos + 1));
        neighbours.add(tilemap.getNodeAtLocation(this.xpos - 1, this.zpos + 1));
        neighbours.add(tilemap.getNodeAtLocation(this.xpos, this.zpos + 1));
        neighbours.add(tilemap.getNodeAtLocation(this.xpos, this.zpos - 1));
        neighbours.add(tilemap.getNodeAtLocation(this.xpos + 1, this.zpos - 1));
        neighbours.add(tilemap.getNodeAtLocation(this.xpos - 1, this.zpos - 1));
        neighbours.add(tilemap.getNodeAtLocation(this.xpos + 1, this.zpos));
        neighbours.add(tilemap.getNodeAtLocation(this.xpos - 1, this.zpos));
        return neighbours;
    }

    public boolean isWinnerNode() {
        return this.is_winner;
    }

    public boolean isDead() {
        return this.is_dead;
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

    public void setDead() {
        this.is_dead = true;
    }
}
