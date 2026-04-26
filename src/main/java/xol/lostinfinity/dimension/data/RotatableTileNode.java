package xol.lostinfinity.dimension.data;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/RotatableTileNode.class */
public class RotatableTileNode {
    private BlockPos blockPos;
    private int xpos;
    private int zpos;
    private int rotation;
    private IBlockState state = getStateFromRot();

    public RotatableTileNode(int rotation, int gridx, int gridz) {
        this.rotation = rotation;
        this.xpos = gridx;
        this.zpos = gridz;
    }

    public void rotate(RotatableTileNode[][] tileMap, boolean propogate) {
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
        if (propogate) {
            rotateNeighbours(tileMap);
        }
    }

    private void rotateNeighbours(RotatableTileNode[][] tileMap) {
        for (RotatableTileNode node : getNeighbours(tileMap)) {
            node.rotate(tileMap, false);
        }
    }

    public IBlockState getState() {
        return this.state;
    }

    public void updateState() {
        this.state = getStateFromRot();
    }

    private IBlockState getStateFromRot() {
        EnumFacing facing;
        switch (this.rotation) {
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
        return BlockInit.rotatableTile.getStateWithFacing(facing);
    }

    public boolean compare(RotatableTileNode node) {
        int x1 = getX();
        int x2 = node.getX();
        int z1 = getZ();
        int z2 = node.getZ();
        return x1 == x2 && z1 == z2;
    }

    public int getX() {
        return this.xpos;
    }

    public int getZ() {
        return this.zpos;
    }

    public void setX(int x) {
        this.xpos = x;
    }

    public void setZ(int z) {
        this.zpos = z;
    }

    public void setBlockPos(BlockPos pos) {
        this.blockPos = new BlockPos(pos);
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public int getRotation() {
        return this.rotation;
    }

    public RotatableTileNode getNodeAtLocation(RotatableTileNode[][] tileMap, int x, int z) {
        if (x >= 0 && x < tileMap.length && z >= 0 && z < tileMap[x].length) {
            return tileMap[x][z];
        }
        return null;
    }

    public List<RotatableTileNode> getNeighbours(RotatableTileNode[][] tileMap) {
        List<RotatableTileNode> neighbourList = new ArrayList<>();
        int x = getX();
        int z = getZ();
        if (getNodeAtLocation(tileMap, x, z + 1) != null) {
            neighbourList.add(tileMap[x][z + 1]);
        }
        if (getNodeAtLocation(tileMap, x + 1, z) != null) {
            neighbourList.add(tileMap[x + 1][z]);
        }
        if (getNodeAtLocation(tileMap, x, z - 1) != null) {
            neighbourList.add(tileMap[x][z - 1]);
        }
        if (getNodeAtLocation(tileMap, x - 1, z) != null) {
            neighbourList.add(tileMap[x - 1][z]);
        }
        return neighbourList;
    }
}
