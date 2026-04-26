package xol.lostinfinity.dimension.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/PipeNode.class */
public class PipeNode {
    private BlockPos blockPos;
    private int xpos;
    private int ypos;
    private String type;
    private boolean[] neighbours;
    private int rotation;
    private IBlockState state;
    private boolean lit;

    public PipeNode(String type, int rotation, int gridx, int gridy) {
        this.type = type;
        this.rotation = rotation;
        this.state = getStateFromTypeRot(type, rotation);
        this.xpos = gridx;
        this.ypos = gridy;
        this.lit = false;
        initNeighbours();
    }

    public boolean isLit() {
        return this.lit;
    }

    public void setLit(boolean lit) {
        this.lit = lit;
    }

    public void rotate() {
        switch (this.rotation) {
            case 0:
                this.rotation = 1;
                break;
            case 1:
                this.rotation = 2;
                break;
            case 2:
                this.rotation = 3;
                break;
            case 3:
                this.rotation = 0;
                break;
            default:
                this.rotation = 0;
                break;
        }
        rotateNeighbours();
    }

    public IBlockState getState() {
        return this.state;
    }

    public void updateState() {
        this.state = getStateFromTypeRot(this.type, this.rotation);
    }

    private IBlockState getStateFromTypeRot(String name, int rot) {
        EnumFacing facing;
        switch (rot) {
            case 0:
                facing = EnumFacing.NORTH;
                break;
            case 1:
                facing = EnumFacing.EAST;
                break;
            case 2:
                facing = EnumFacing.SOUTH;
                break;
            case 3:
                facing = EnumFacing.WEST;
                break;
            default:
                facing = EnumFacing.NORTH;
                break;
        }
        switch (name) {
            case "cross":
                if (isLit()) {
                    return BlockInit.pipeCrossLit.getStateWithFacing(facing);
                }
                return BlockInit.pipeCross.getStateWithFacing(facing);
            case "elbow":
                if (isLit()) {
                    return BlockInit.pipeElbowLit.getStateWithFacing(facing);
                }
                return BlockInit.pipeElbow.getStateWithFacing(facing);
            case "t":
                if (isLit()) {
                    return BlockInit.pipeTLit.getStateWithFacing(facing);
                }
                return BlockInit.pipeT.getStateWithFacing(facing);
            case "l":
                if (isLit()) {
                    return BlockInit.pipeLLit.getStateWithFacing(facing);
                }
                return BlockInit.pipeL.getStateWithFacing(facing);
            default:
                return BlockInit.pipeNone.getStateWithFacing(facing);
        }
    }

    public PipeNode(int gridx, int gridy) {
        new Random();
        this.type = "none";
        this.rotation = 0;
        this.xpos = gridx;
        this.ypos = gridy;
        this.lit = false;
        initNeighbours();
    }

    public void initNeighbours() {
        int rot = this.rotation;
        String type = this.type;
        switch (type) {
            case "cross":
                this.neighbours = new boolean[]{true, true, true, true};
                break;
            case "elbow":
                this.neighbours = new boolean[]{true, true, false, false};
                break;
            case "t":
                this.neighbours = new boolean[]{true, true, true, false};
                break;
            case "l":
                this.neighbours = new boolean[]{true, false, true, false};
                break;
            case "none":
                this.neighbours = new boolean[]{false, false, false, false};
                break;
        }
        for (int i = 0; i < rot; i++) {
            rotateNeighbours();
        }
    }

    private void rotateNeighbours() {
        boolean[] neighbours = this.neighbours;
        boolean[] newNeighbours = {false, false, false, false};
        if (neighbours[0]) {
            newNeighbours[1] = true;
        }
        if (neighbours[1]) {
            newNeighbours[2] = true;
        }
        if (neighbours[2]) {
            newNeighbours[3] = true;
        }
        if (neighbours[3]) {
            newNeighbours[0] = true;
        }
        setNeighbours(newNeighbours);
    }

    public void setNeighbours(boolean[] neighbours) {
        this.neighbours = neighbours;
    }

    public boolean compare(PipeNode node) {
        int x1 = getX();
        int x2 = node.getX();
        int y1 = getY();
        int y2 = node.getY();
        return x1 == x2 && y1 == y2;
    }

    public int getX() {
        return this.xpos;
    }

    public int getY() {
        return this.ypos;
    }

    public void setX(int x) {
        this.xpos = x;
    }

    public void setY(int y) {
        this.ypos = y;
    }

    public void setBlockPos(BlockPos pos) {
        this.blockPos = new BlockPos(pos);
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PipeNode> getConnectedNeighbours(PipeNode[][] pipeMap) {
        List<PipeNode> connected = new ArrayList<>();
        for (PipeNode neighbour : getNeighbours(pipeMap)) {
            if (neighbour.getNeighbours(pipeMap).contains(this)) {
                connected.add(neighbour);
            }
        }
        return connected;
    }

    public void propogateLit(PipeNode[][] pipeMap, ArrayList<PipeNode> visited) {
        visited.add(this);
        setLit(true);
        for (PipeNode neighbour : getConnectedNeighbours(pipeMap)) {
            if (!visited.contains(neighbour)) {
                neighbour.propogateLit(pipeMap, visited);
            }
        }
    }

    public PipeNode getNodeAtLocation(PipeNode[][] pipeMap, int x, int y) {
        if (x >= 0 && x < pipeMap.length && y >= 0 && y < pipeMap[x].length) {
            return pipeMap[x][y];
        }
        return null;
    }

    public List<PipeNode> getNeighbours(PipeNode[][] pipeMap) {
        List<PipeNode> neighbourList = new ArrayList<>();
        int x = getX();
        int y = getY();
        boolean[] neighbours = this.neighbours;
        if (neighbours[0] && getNodeAtLocation(pipeMap, x, y + 1) != null) {
            neighbourList.add(pipeMap[x][y + 1]);
        }
        if (neighbours[1] && getNodeAtLocation(pipeMap, x + 1, y) != null) {
            neighbourList.add(pipeMap[x + 1][y]);
        }
        if (neighbours[2] && getNodeAtLocation(pipeMap, x, y - 1) != null) {
            neighbourList.add(pipeMap[x][y - 1]);
        }
        if (neighbours[3] && getNodeAtLocation(pipeMap, x - 1, y) != null) {
            neighbourList.add(pipeMap[x - 1][y]);
        }
        return neighbourList;
    }
}
