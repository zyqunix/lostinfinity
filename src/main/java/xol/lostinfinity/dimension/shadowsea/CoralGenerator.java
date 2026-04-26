package xol.lostinfinity.dimension.shadowsea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/shadowsea/CoralGenerator.class */
public class CoralGenerator {
    private World world;
    private IBlockState topBlock = BlockInit.seastone.func_176223_P();

    public CoralGenerator() {
    }

    public void setWorld(World world, IBlockState state) {
        this.world = world;
        this.topBlock = state;
    }

    public CoralGenerator(World world) {
        this.world = world;
    }

    public void genRandomCoral(BlockPos pos) {
        if (!this.world.field_72995_K) {
            int randCoral = this.world.field_73012_v.nextInt(8);
            switch (randCoral) {
                case 0:
                    genTreeCoral(pos);
                    break;
                case 1:
                    genTubeCoral(pos, new Integer[0]);
                    break;
                case 2:
                    genFunnelCoral(pos);
                    break;
                case 3:
                    genBranchCoral(pos);
                    break;
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    genPlateCoral(pos, new Integer[0]);
                    break;
                case 5:
                    genPoleCoral(pos, new Integer[0]);
                    break;
                case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                    genBrainCoral(pos);
                    break;
            }
        }
    }

    public void genVolcanicCoral(BlockPos pos) {
        if (!this.world.field_72995_K) {
            if (this.world.field_73012_v.nextInt(4) == 0) {
                int randCoral = this.world.field_73012_v.nextInt(3);
                switch (randCoral) {
                    case 0:
                        genSproutCoral(pos);
                        break;
                    case 1:
                        GenFlatTopCoral(pos);
                        break;
                    case 2:
                        GenBulbCoral(pos);
                        break;
                }
            }
            int randCoral2 = this.world.field_73012_v.nextInt(3);
            switch (randCoral2) {
                case 0:
                    genTubeCoral(pos, 6, 7, 8, 9);
                    break;
                case 1:
                    genPlateCoral(pos, 6, 7, 8, 9);
                    break;
                case 2:
                    genPoleCoral(pos, 6, 7, 8, 9);
                    break;
            }
        }
    }

    public void genRandomExtras(BlockPos pos) {
        genRadionTower(pos);
    }

    private void genRadionTower(BlockPos pos) {
        for (int j = 0; j < 30; j++) {
            for (int i = -2; i <= 2; i++) {
                for (int k = -2; k <= 2; k++) {
                    if (Math.abs(i) != 2 || Math.abs(k) != 2) {
                        if (Math.abs(i) == 2 || Math.abs(k) == 2) {
                            if (this.world.field_73012_v.nextInt(6) == 0) {
                                this.world.func_175656_a(pos.func_177982_a(i, j, k), BlockInit.seastone.func_176223_P());
                            }
                        } else {
                            this.world.func_175656_a(pos.func_177982_a(i, j, k), BlockInit.seastone.func_176223_P());
                        }
                    }
                }
            }
        }
        for (int i2 = -7; i2 <= 7; i2++) {
            for (int k2 = -7; k2 <= 7; k2++) {
                if ((i2 * i2) + (k2 * k2) <= 7 * 7) {
                    if ((i2 * i2) + (k2 * k2) < (7 - 1) * (7 - 1)) {
                        if ((i2 * i2) + (k2 * k2) >= (7 - 2) * (7 - 2)) {
                            for (int j2 = 1; j2 <= 7; j2++) {
                                if (this.world.field_73012_v.nextInt(4) == 0) {
                                    this.world.func_175656_a(pos.func_177982_a(i2, 30 + j2, k2), BlockInit.radionOre.func_176223_P());
                                } else {
                                    this.world.func_175656_a(pos.func_177982_a(i2, 30 + j2, k2), BlockInit.seastone.func_176223_P());
                                }
                            }
                        } else if (i2 == 0 && k2 == 0) {
                            for (int j3 = 1; j3 <= 7; j3++) {
                                this.world.func_175656_a(pos.func_177982_a(i2, 30 + j3, k2), BlockInit.radionPillar.func_176223_P());
                            }
                        }
                    } else {
                        for (int j4 = 1; j4 <= 7; j4++) {
                            this.world.func_175656_a(pos.func_177982_a(i2, 30 + j4, k2), BlockInit.seastone.func_176223_P());
                        }
                    }
                    this.world.func_175656_a(pos.func_177982_a(i2, 30, k2), BlockInit.seastone.func_176223_P());
                }
            }
        }
    }

    public void genPoleCoral(BlockPos pos, Integer... ints) {
        int pick;
        int secondPick;
        if (ints.length == 0) {
            pick = this.world.field_73012_v.nextInt(16);
            secondPick = (pick - 1) + this.world.field_73012_v.nextInt(3);
            if (secondPick < 0) {
                secondPick = 15;
            } else if (secondPick > 15) {
                secondPick = 0;
            }
        } else {
            pick = ints[this.world.field_73012_v.nextInt(ints.length)].intValue();
            secondPick = pick;
        }
        ArrayList<BlockPos> startingPositions = new ArrayList<>();
        IBlockState poleState = BlockInit.superRoughCoral.func_176203_a(pick);
        IBlockState poleTop = BlockInit.roughCoral.func_176203_a(secondPick);
        Random rand = new Random();
        int height = rand.nextInt((15 - 3) + 1) + 3;
        int radius = rand.nextInt((8 - 4) + 1) + 4;
        startingPositions.add(pos);
        for (int i = 0; i < height; i++) {
            if (i == height - 1) {
                this.world.func_175656_a(pos.func_177982_a(0, i, 0), poleTop);
            } else {
                this.world.func_175656_a(pos.func_177982_a(0, i, 0), poleState);
            }
        }
        for (int i2 = -radius; i2 <= radius; i2++) {
            for (int j = -radius; j <= radius; j++) {
                BlockPos check = pos.func_177982_a(i2, 0, j);
                if (isTopBlock(check.func_177977_b())) {
                    boolean tooClose = false;
                    Iterator<BlockPos> it = startingPositions.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        BlockPos position = it.next();
                        if (check.func_185332_f(position.func_177958_n(), position.func_177956_o(), position.func_177952_p()) < 2.0d) {
                            tooClose = true;
                            break;
                        }
                    }
                    if (!tooClose && rand.nextInt(4) == 0) {
                        int height2 = rand.nextInt((15 - 3) + 1) + 3;
                        startingPositions.add(check);
                        for (int k = 0; k < height2; k++) {
                            if (k == height2 - 1) {
                                this.world.func_175656_a(check.func_177982_a(0, k, 0), poleTop);
                            } else {
                                this.world.func_175656_a(check.func_177982_a(0, k, 0), poleState);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isTopBlock(BlockPos pos) {
        return this.world.func_180495_p(pos).equals(this.topBlock);
    }

    public void genFunnelCoral(BlockPos pos) {
        if (!isTopBlock(pos.func_177977_b())) {
            return;
        }
        int pick = 9 + this.world.field_73012_v.nextInt(4);
        IBlockState funnelState = BlockInit.roughCoral.func_176203_a(pick);
        int height = this.world.field_73012_v.nextInt((25 - 10) + 1) + 10;
        int curRadius = 0;
        for (int i = 0; i < height; i++) {
            if (i > height - 2) {
                curRadius += 3;
            } else if (i % 5 == 4) {
                curRadius++;
            }
            for (int j = -curRadius; j <= curRadius; j++) {
                for (int k = -curRadius; k <= curRadius; k++) {
                    if ((j * j) + (k * k) <= curRadius * curRadius) {
                        if (i == height - 1 && (j * j) + (k * k) < (curRadius * curRadius) - curRadius) {
                            int roll = this.world.field_73012_v.nextInt(2);
                            if (roll == 0) {
                                this.world.func_175656_a(pos.func_177982_a(j, i, k), funnelState);
                            } else if (roll == 1) {
                                this.world.func_175656_a(pos.func_177982_a(j, i + 1, k), funnelState);
                                this.world.func_175656_a(pos.func_177982_a(j, i, k), funnelState);
                            }
                        } else {
                            this.world.func_175656_a(pos.func_177982_a(j, i, k), funnelState);
                        }
                    }
                }
            }
        }
    }

    public void genPlateCoral(BlockPos pos, Integer... ints) {
        if (!isTopBlock(pos.func_177977_b())) {
            return;
        }
        genPlateCoral(pos, null, null, ints);
    }

    public void genPlateCoral(BlockPos pos, IBlockState prevPlate, IBlockState prevInner, Integer... ints) {
        int pick;
        IBlockState plateState = prevPlate;
        IBlockState innerState = prevInner;
        if (plateState == null || innerState == null) {
            if (ints.length == 0) {
                pick = this.world.field_73012_v.nextInt(16);
            } else {
                pick = ints[this.world.field_73012_v.nextInt(ints.length)].intValue();
            }
            plateState = BlockInit.smoothCoral.func_176203_a(pick);
            innerState = BlockInit.lightSmoothCoral.func_176203_a(pick);
        }
        int radius = this.world.field_73012_v.nextInt((10 - 3) + 1) + 3;
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                double curRadius = Math.sqrt((i * i) + (j * j));
                if (curRadius <= radius + 1) {
                    int x = pos.func_177958_n() + i;
                    int z = pos.func_177952_p() + j;
                    int y = (int) (((double) pos.func_177956_o()) + ((((double) radius) / 5.2d) * Math.cos(((curRadius * 3.141592653589793d) / ((double) radius)) + 3.141592653589793d)));
                    if (curRadius <= radius * 0.85f) {
                        this.world.func_175656_a(new BlockPos(x, y, z), innerState);
                    } else {
                        this.world.func_175656_a(new BlockPos(x, y, z), plateState);
                    }
                }
            }
        }
        if (this.world.field_73012_v.nextBoolean()) {
            genPlateCoral(pos.func_177971_a(new Vec3i(this.world.field_73012_v.nextInt(5) - 2, 2, this.world.field_73012_v.nextInt(5) - 2)), plateState, innerState, new Integer[0]);
        }
    }

    public void genBranchCoral(BlockPos pos) {
        if (!isTopBlock(pos.func_177977_b())) {
            return;
        }
        int pick = this.world.field_73012_v.nextInt(16);
        IBlockState branchState = BlockInit.roughCoral.func_176203_a(pick);
        genBranchCoral(branchState, (CoralNode[][][]) null, null, null, null, 0, pos);
    }

    public int genBranchCoral(IBlockState branchState, CoralNode[][][] map, CoralNode node, Vec3i dir, ArrayList<CoralNode> visited, int count, BlockPos pos) {
        CoralNode newNode;
        CoralNode check;
        Random rand = new Random();
        int count2 = count + 1;
        if (map == null) {
            int xSize = rand.nextInt((7 - 5) + 1) + 5;
            int zSize = rand.nextInt((7 - 5) + 1) + 5;
            int ySize = rand.nextInt((40 - 8) + 1) + 8;
            CoralNode[][][] map2 = new CoralNode[xSize][ySize][zSize];
            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    for (int k = 0; k < zSize; k++) {
                        map2[i][j][k] = new CoralNode(i, j, k);
                    }
                }
            }
            CoralNode node2 = getNodeAtLocation(map2, xSize / 2, 0, zSize / 2);
            ArrayList<CoralNode> visited2 = new ArrayList<>();
            Vec3i dir2 = new Vec3i(0, 1, 0);
            CoralNode next = getNodeAtLocation(map2, node2.x + dir2.func_177958_n(), node2.y + dir2.func_177956_o(), node2.z + dir2.func_177952_p());
            node2.visited = true;
            next.prev.add(node2);
            genBranchCoral(branchState, map2, next, dir2, visited2, count2, pos);
            int totalCount = 0;
            boolean finished = false;
            for (int i2 = 0; i2 < xSize; i2++) {
                for (int j2 = 0; j2 < ySize; j2++) {
                    int k2 = 0;
                    while (true) {
                        if (k2 >= zSize) {
                            break;
                        }
                        if (getNodeAtLocation(map2, i2, j2, k2).visited) {
                            totalCount++;
                        }
                        if (totalCount <= 24) {
                            k2++;
                        } else {
                            finished = true;
                            break;
                        }
                    }
                }
            }
            if (!finished) {
                genBranchCoral(branchState, (CoralNode[][][]) null, null, null, null, 0, pos);
            } else {
                for (int i3 = 0; i3 < xSize; i3++) {
                    for (int j3 = 0; j3 < ySize; j3++) {
                        for (int k3 = 0; k3 < zSize; k3++) {
                            CoralNode countNode = getNodeAtLocation(map2, i3, j3, k3);
                            if (countNode.visited) {
                                this.world.func_175656_a(pos.func_177982_a(countNode.x - (xSize / 2), countNode.y, countNode.z - (zSize / 2)), branchState);
                            }
                        }
                    }
                }
            }
            return count2;
        }
        double xDist = Math.abs(Math.min(map.length - node.x, node.x));
        double yDist = Math.abs(Math.min(map[0].length - node.y, node.y));
        double zDist = Math.abs(Math.min(map[0][0].length - node.z, node.z));
        if (xDist > 0.0d && yDist > 0.0d && zDist > 0.0d && !node.visited) {
            int neighbourCount = 0;
            for (int i4 = -1; i4 <= 1; i4++) {
                for (int j4 = -1; j4 <= 1; j4++) {
                    for (int k4 = -1; k4 <= 1; k4++) {
                        if ((i4 != 0 || j4 != 0 || k4 != 0) && (check = getNodeAtLocation(map, node.x + i4, node.y + j4, node.z + k4)) != null && check.visited) {
                            neighbourCount++;
                            if (neighbourCount > 2) {
                                return count2;
                            }
                        }
                    }
                }
            }
            node.visited = true;
            int roll = rand.nextInt(11);
            if (roll > 3) {
                ArrayList<Vec3i> upDirs = upDirs();
                Collections.shuffle(upDirs);
                ArrayList<Vec3i> used = new ArrayList<>();
                for (int i5 = 0; i5 < 2; i5++) {
                    int iterations = 0;
                    boolean found = false;
                    while (!found && iterations < 10) {
                        iterations++;
                        Collections.shuffle(upDirs);
                        Vec3i newDir = upDirs.get(0);
                        if (!used.contains(newDir) && (newNode = getNodeAtLocation(map, node.x + newDir.func_177958_n(), node.y + newDir.func_177956_o(), node.z + newDir.func_177952_p())) != null && !newNode.equals(node) && !node.prev.contains(newNode)) {
                            for (CoralNode prevNode : node.prev) {
                                if (prevNode != null && !prevNode.equals(node) && !prevNode.equals(newNode)) {
                                    newNode.prev.add(node);
                                }
                            }
                            found = true;
                            newNode.prev.add(node);
                            count2 += genBranchCoral(branchState, map, newNode, newDir, visited, count2, pos);
                            used.add(newDir);
                        }
                    }
                }
                return count2;
            }
            if (!dir.equals(new Vec3i(0, 1, 0)) && rand.nextInt(3) == 0) {
                dir = new Vec3i(0, 1, 0);
            }
            CoralNode next2 = getNodeAtLocation(map, node.x + dir.func_177958_n(), node.y + dir.func_177956_o(), node.z + dir.func_177952_p());
            if (node.prev.contains(next2) || next2 == null) {
                return count2;
            }
            if (node.equals(next2)) {
                return count2;
            }
            for (CoralNode prevNode2 : node.prev) {
                if (prevNode2 != null && !prevNode2.equals(node) && !prevNode2.equals(next2)) {
                    next2.prev.add(node);
                }
            }
            next2.prev.add(node);
            count2 += genBranchCoral(branchState, map, next2, dir, visited, count2, pos);
        }
        return count2;
    }

    private static ArrayList<Vec3i> upDirs() {
        ArrayList<Vec3i> neighbours = new ArrayList<>();
        neighbours.add(new Vec3i(0, 1, 0));
        neighbours.add(new Vec3i(1, 0, 0));
        neighbours.add(new Vec3i(-1, 0, 0));
        neighbours.add(new Vec3i(0, 0, 1));
        neighbours.add(new Vec3i(0, 0, -1));
        return neighbours;
    }

    private static ArrayList<Vec3i> getDirs() {
        ArrayList<Vec3i> neighbours = new ArrayList<>();
        neighbours.add(new Vec3i(0, 1, 0));
        neighbours.add(new Vec3i(1, 0, 0));
        neighbours.add(new Vec3i(-1, 0, 0));
        neighbours.add(new Vec3i(0, 0, 1));
        neighbours.add(new Vec3i(0, 0, -1));
        neighbours.add(new Vec3i(0, -1, 0));
        return neighbours;
    }

    private static Vec3d randomlyRotate(Vec3d dir, double xRot, double yRot, double zRot) {
        Random rand = new Random();
        double angleX = ((rand.nextDouble() * xRot) * 2.0d) - xRot;
        double angleY = ((rand.nextDouble() * yRot) * 2.0d) - yRot;
        double angleZ = ((rand.nextDouble() * zRot) * 2.0d) - zRot;
        double newX = dir.field_72450_a;
        double newY = (dir.field_72448_b * Math.cos(angleX)) - (dir.field_72449_c * Math.sin(angleX));
        double newZ = (dir.field_72448_b * Math.sin(angleX)) + (dir.field_72449_c * Math.cos(angleX));
        double newX2 = (newX * Math.cos(angleY)) + (newZ * Math.sin(angleY));
        double newZ2 = ((-newX2) * Math.sin(angleY)) + (newZ * Math.cos(angleY));
        double newX3 = (newX2 * Math.cos(angleZ)) - (newY * Math.sin(angleZ));
        return new Vec3d(newX3, (newX3 * Math.sin(angleZ)) + (newY * Math.cos(angleZ)), newZ2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CoralNode getNodeAtLocation(CoralNode[][][] map, int x, int y, int z) {
        if (map != null && x >= 0 && x < map.length && y >= 0 && y < map[0].length && z >= 0 && z < map[0][0].length) {
            return map[x][y][z];
        }
        return null;
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/shadowsea/CoralGenerator$CoralNode.class */
    private class CoralNode {
        private int x;
        private int y;
        private int z;
        private ArrayList<CoralNode> prev;
        private boolean visited;

        private CoralNode(int x, int y, int z) {
            this.x = 0;
            this.y = 0;
            this.z = 0;
            this.prev = new ArrayList<>();
            this.visited = false;
            this.x = x;
            this.y = y;
            this.z = z;
            this.prev.clear();
        }
    }

    public boolean isZigZag(Vec3i first, Vec3i second) {
        return (Math.abs(first.func_177958_n() - second.func_177958_n()) + Math.abs(first.func_177952_p() - second.func_177952_p()) == 1 && (first.func_177958_n() == second.func_177958_n() || second.func_177952_p() == first.func_177952_p())) ? false : true;
    }

    public void genTubeCoral(BlockPos pos, Integer... ints) {
        int pick;
        if (!isTopBlock(pos.func_177977_b())) {
            return;
        }
        if (ints.length == 0) {
            pick = this.world.field_73012_v.nextInt(16);
        } else {
            pick = ints[this.world.field_73012_v.nextInt(ints.length)].intValue();
        }
        IBlockState outerTube = BlockInit.darkRoughCoral.func_176203_a(pick);
        IBlockState innerTube = BlockInit.smoothCoral.func_176203_a(pick);
        int radius = this.world.field_73012_v.nextInt((8 - 4) + 1) + 4;
        ArrayList<BlockPos> startingPositions = new ArrayList<>();
        startingPositions.add(pos);
        ArrayList<Vec3i> outerRingVecs = new ArrayList<>();
        ArrayList<Vec3i> innerRingVecs = new ArrayList<>();
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                int currentRad = (i * i) + (j * j);
                if (currentRad >= 2.0d && currentRad <= 5.0d) {
                    outerRingVecs.add(new Vec3i(i, 0, j));
                } else if (currentRad > 0 && currentRad < 2.0d) {
                    innerRingVecs.add(new Vec3i(i, 0, j));
                }
            }
        }
        int height = this.world.field_73012_v.nextInt((24 - 6) + 1) + 6;
        this.world.func_175656_a(pos.func_177982_a(0, -1, 1), outerTube);
        this.world.func_175656_a(pos.func_177982_a(1, -1, 1), outerTube);
        this.world.func_175656_a(pos.func_177982_a(-1, -1, 1), outerTube);
        this.world.func_175656_a(pos.func_177982_a(0, -1, -1), outerTube);
        this.world.func_175656_a(pos.func_177982_a(1, -1, 0), outerTube);
        this.world.func_175656_a(pos.func_177982_a(1, -1, -1), outerTube);
        this.world.func_175656_a(pos.func_177982_a(-1, -1, 0), outerTube);
        this.world.func_175656_a(pos.func_177982_a(-1, -1, -1), outerTube);
        this.world.func_175656_a(pos.func_177982_a(0, -1, 0), outerTube);
        this.world.func_175656_a(pos.func_177982_a(0, -2, 1), outerTube);
        this.world.func_175656_a(pos.func_177982_a(1, -2, 0), outerTube);
        this.world.func_175656_a(pos.func_177982_a(0, -2, -1), outerTube);
        this.world.func_175656_a(pos.func_177982_a(-1, -2, 0), outerTube);
        this.world.func_175656_a(pos.func_177982_a(0, -2, 0), outerTube);
        Vec3i offset = new Vec3i(0, 0, 0);
        Vec3i prevOffset = new Vec3i(0, 0, 0);
        for (int i2 = 0; i2 < height; i2++) {
            if (i2 % 5 == 2) {
                while (isZigZag(prevOffset, offset)) {
                    offset = new Vec3i(this.world.field_73012_v.nextInt(3) - 1, 0, this.world.field_73012_v.nextInt(3) - 1);
                }
                prevOffset = offset;
            }
            if (i2 < height - 1) {
                for (Vec3i vec : innerRingVecs) {
                    this.world.func_175656_a(pos.func_177971_a(offset).func_177971_a(vec).func_177982_a(0, i2, 0), innerTube);
                }
            }
            for (Vec3i vec2 : outerRingVecs) {
                this.world.func_175656_a(pos.func_177971_a(offset).func_177971_a(vec2).func_177982_a(0, i2, 0), outerTube);
            }
        }
        for (int i3 = -radius; i3 <= radius; i3++) {
            for (int j2 = -radius; j2 <= radius; j2++) {
                BlockPos check = pos.func_177982_a(i3, 0, j2);
                if (isTopBlock(check.func_177977_b())) {
                    boolean tooClose = false;
                    Iterator<BlockPos> it = startingPositions.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        BlockPos position = it.next();
                        double dist = check.func_185332_f(position.func_177958_n(), position.func_177956_o(), position.func_177952_p());
                        if (dist < 5.0d) {
                            tooClose = true;
                            break;
                        }
                    }
                    if (!tooClose && this.world.field_73012_v.nextInt(4) == 0) {
                        startingPositions.add(check);
                        for (int k = -2; k <= 2; k++) {
                            for (int n = -2; n <= 2; n++) {
                                int currentRad2 = (k * k) + (n * n);
                                if (currentRad2 >= 2.0d && currentRad2 <= 5.0d) {
                                    outerRingVecs.add(new Vec3i(k, 0, n));
                                } else if (currentRad2 > 0 && currentRad2 < 2.0d) {
                                    innerRingVecs.add(new Vec3i(k, 0, n));
                                }
                            }
                        }
                        int height2 = this.world.field_73012_v.nextInt((24 - 6) + 1) + 6;
                        Vec3i offset2 = new Vec3i(0, 0, 0);
                        this.world.func_175656_a(check.func_177982_a(0, -1, 1), outerTube);
                        this.world.func_175656_a(check.func_177982_a(1, -1, 1), outerTube);
                        this.world.func_175656_a(check.func_177982_a(-1, -1, 1), outerTube);
                        this.world.func_175656_a(check.func_177982_a(0, -1, -1), outerTube);
                        this.world.func_175656_a(check.func_177982_a(1, -1, 0), outerTube);
                        this.world.func_175656_a(check.func_177982_a(1, -1, -1), outerTube);
                        this.world.func_175656_a(check.func_177982_a(-1, -1, 0), outerTube);
                        this.world.func_175656_a(check.func_177982_a(-1, -1, -1), outerTube);
                        this.world.func_175656_a(check.func_177982_a(0, -1, 0), outerTube);
                        this.world.func_175656_a(check.func_177982_a(0, -2, 1), outerTube);
                        this.world.func_175656_a(check.func_177982_a(1, -2, 0), outerTube);
                        this.world.func_175656_a(check.func_177982_a(0, -2, -1), outerTube);
                        this.world.func_175656_a(check.func_177982_a(-1, -2, 0), outerTube);
                        this.world.func_175656_a(check.func_177982_a(0, -2, 0), outerTube);
                        Vec3i prevOffset2 = new Vec3i(0, 0, 0);
                        for (int h = 0; h < height2; h++) {
                            if (h % 5 == 2) {
                                while (true) {
                                    if (!isZigZag(prevOffset2, offset2) && !prevOffset2.equals(offset2)) {
                                        break;
                                    } else {
                                        offset2 = new Vec3i(this.world.field_73012_v.nextInt(3) - 1, 0, this.world.field_73012_v.nextInt(3) - 1);
                                    }
                                }
                                prevOffset2 = offset2;
                            }
                            if (h < height2 - 1) {
                                for (Vec3i vec3 : innerRingVecs) {
                                    this.world.func_175656_a(check.func_177971_a(offset2).func_177971_a(vec3).func_177982_a(0, h, 0), innerTube);
                                }
                            }
                            for (Vec3i vec4 : outerRingVecs) {
                                this.world.func_175656_a(check.func_177971_a(offset2).func_177971_a(vec4).func_177982_a(0, h, 0), outerTube);
                            }
                        }
                    }
                }
            }
        }
    }

    private static ArrayList<BlockPos> getNeighbours(BlockPos pos, boolean hasDiag) {
        ArrayList<BlockPos> neighbours = new ArrayList<>();
        neighbours.add(pos.func_177974_f());
        neighbours.add(pos.func_177968_d());
        neighbours.add(pos.func_177976_e());
        neighbours.add(pos.func_177978_c());
        if (hasDiag) {
            neighbours.add(pos.func_177974_f().func_177978_c());
            neighbours.add(pos.func_177974_f().func_177968_d());
            neighbours.add(pos.func_177976_e().func_177978_c());
            neighbours.add(pos.func_177976_e().func_177968_d());
        }
        return neighbours;
    }

    public void genTreeCoral(BlockPos pos) {
        if (!isTopBlock(pos.func_177977_b())) {
            return;
        }
        int pick = this.world.field_73012_v.nextInt(16);
        IBlockState branchState = BlockInit.roughCoral.func_176203_a(pick);
        genTreeCoral(branchState, (CoralNode[][][]) null, null, null, null, pos);
    }

    public void genTreeCoral(IBlockState branchState, CoralNode[][][] map, CoralTrunk trunk, Vec3i dir, ArrayList<CoralNode> visited, BlockPos pos) {
        int radius;
        int radius2 = 3;
        Random rand = new Random();
        if (map == null) {
            int xSize = rand.nextInt((30 - 20) + 1) + 20;
            int zSize = rand.nextInt((30 - 20) + 1) + 20;
            int ySize = rand.nextInt((35 - 25) + 1) + 25;
            CoralNode[][][] map2 = new CoralNode[xSize][ySize][zSize];
            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    for (int k = 0; k < zSize; k++) {
                        map2[i][j][k] = new CoralNode(i, j, k);
                    }
                }
            }
            CoralNode node = getNodeAtLocation(map2, xSize / 2, 0, zSize / 2);
            node.prev.clear();
            ArrayList<CoralNode> visited2 = new ArrayList<>();
            Vec3i dir2 = new Vec3i(0, 1, 0);
            CoralTrunk next = new CoralTrunk(map2, node, radius2, dir2);
            next.visit();
            visited2.addAll(next.nodes);
            ArrayList<CoralTrunk> queue = new ArrayList<>();
            queue.add(next);
            int iterations = 0;
            while (!queue.isEmpty() && iterations < 1000) {
                iterations++;
                CoralTrunk curTrunk = queue.get(0);
                if (curTrunk.centre.prev.size() > 40) {
                    radius = 1;
                } else {
                    radius = Math.max(1, 2 - (curTrunk.centre.prev.size() / 4));
                }
                queue.remove(curTrunk);
                int roll = rand.nextInt(6);
                if (roll >= 4 && curTrunk.centre.prev.size() >= radius) {
                    ArrayList<Vec3i> dirs = upDirs();
                    dirs.remove(curTrunk.dir);
                    Collections.shuffle(dirs);
                    for (int i2 = 0; i2 < 2; i2++) {
                        Vec3i newDir = dirs.get(i2);
                        int mult = 1;
                        if (radius == 0) {
                            mult = 1;
                        }
                        int iFunc_177952_p = curTrunk.centre.z + (newDir.func_177952_p() * mult);
                        CoralNode newCentre = getNodeAtLocation(map2, curTrunk.centre.x + (newDir.func_177958_n() * mult), curTrunk.centre.y + (newDir.func_177956_o() * mult), curTrunk.centre.z + (newDir.func_177952_p() * mult));
                        if (newCentre != null && !curTrunk.centre.prev.contains(newCentre)) {
                            newCentre.prev.clear();
                            newCentre.prev.addAll(curTrunk.centre.prev);
                            newCentre.prev.add(curTrunk.centre);
                            CoralTrunk newTrunk = new CoralTrunk(map2, newCentre, radius, newDir);
                            int visitedCount = 0;
                            ArrayList<CoralNode> checked = new ArrayList<>();
                            for (CoralNode trunkNode : newTrunk.nodes) {
                                if (trunkNode.visited) {
                                    visitedCount++;
                                }
                                if (radius == 0) {
                                    for (CoralNode neighbour : getCoralNeighbours(map2, trunkNode)) {
                                        if (neighbour != null && neighbour.visited && !checked.contains(neighbour)) {
                                            visitedCount++;
                                            checked.add(neighbour);
                                        }
                                    }
                                }
                            }
                            if ((visitedCount <= 2 && radius > 0) || (radius == 0 && visitedCount <= 5)) {
                                newTrunk.visit();
                                visited2.addAll(newTrunk.nodes);
                                queue.add(newTrunk);
                            }
                        }
                    }
                } else {
                    Vec3i newDir2 = curTrunk.dir;
                    if (!newDir2.equals(new Vec3i(0, 1, 0)) && rand.nextInt(3) == 0) {
                        newDir2 = new Vec3i(0, 1, 0);
                    }
                    CoralNode newCentre2 = getNodeAtLocation(map2, curTrunk.centre.x + newDir2.func_177958_n(), curTrunk.centre.y + newDir2.func_177956_o(), curTrunk.centre.z + newDir2.func_177952_p());
                    if (newCentre2 != null && !curTrunk.centre.prev.contains(newCentre2)) {
                        CoralTrunk newTrunk2 = new CoralTrunk(map2, newCentre2, radius, newDir2);
                        int visitedCount2 = 0;
                        new ArrayList();
                        Iterator it = newTrunk2.nodes.iterator();
                        while (it.hasNext()) {
                            if (((CoralNode) it.next()).visited) {
                                visitedCount2++;
                            }
                        }
                        if ((visitedCount2 <= 2 && radius > 0) || (radius == 0 && visitedCount2 <= 5)) {
                            newCentre2.prev.clear();
                            newCentre2.prev.addAll(curTrunk.centre.prev);
                            newCentre2.prev.add(curTrunk.centre);
                            newTrunk2.visit();
                            visited2.addAll(newTrunk2.nodes);
                            queue.add(newTrunk2);
                        }
                    }
                }
            }
            int count = 0;
            boolean done = false;
            int i3 = 0;
            loop8: while (true) {
                if (i3 >= xSize) {
                    break;
                }
                for (int j2 = 0; j2 < ySize; j2++) {
                    for (int k2 = 0; k2 < zSize; k2++) {
                        if (getNodeAtLocation(map2, i3, j2, k2).visited) {
                            count++;
                            if (count > 20) {
                                done = true;
                                break loop8;
                            }
                        }
                    }
                }
                i3++;
            }
            if (!done) {
                genTreeCoral(branchState, (CoralNode[][][]) null, null, null, null, pos);
                return;
            }
            for (int i4 = 0; i4 < xSize; i4++) {
                for (int j3 = 0; j3 < ySize; j3++) {
                    for (int k3 = 0; k3 < zSize; k3++) {
                        CoralNode countNode = getNodeAtLocation(map2, i4, j3, k3);
                        if (countNode.visited) {
                            this.world.func_175656_a(pos.func_177982_a(countNode.x - (xSize / 2), countNode.y, countNode.z - (zSize / 2)), branchState);
                        }
                    }
                }
            }
        }
    }

    private static ArrayList<CoralNode> getCoralNeighbours(CoralNode[][][] map, CoralNode node) {
        ArrayList<CoralNode> neighbours = new ArrayList<>();
        ArrayList<Vec3i> dirs = new ArrayList<>();
        dirs.add(new Vec3i(0, 0, 1));
        dirs.add(new Vec3i(1, 0, 1));
        dirs.add(new Vec3i(-1, 0, 1));
        dirs.add(new Vec3i(1, 0, 0));
        dirs.add(new Vec3i(-1, 0, 0));
        dirs.add(new Vec3i(1, 1, 0));
        dirs.add(new Vec3i(-1, 1, 0));
        dirs.add(new Vec3i(1, -1, 0));
        dirs.add(new Vec3i(-1, -1, 0));
        dirs.add(new Vec3i(0, 1, 0));
        dirs.add(new Vec3i(0, -1, 0));
        dirs.add(new Vec3i(1, 0, -1));
        dirs.add(new Vec3i(-1, 0, -1));
        dirs.add(new Vec3i(0, 0, -1));
        dirs.add(new Vec3i(0, 1, 1));
        dirs.add(new Vec3i(1, 1, 1));
        dirs.add(new Vec3i(-1, 1, 1));
        dirs.add(new Vec3i(1, 1, -1));
        dirs.add(new Vec3i(-1, 1, -1));
        dirs.add(new Vec3i(0, 1, -1));
        dirs.add(new Vec3i(0, -1, 1));
        dirs.add(new Vec3i(1, -1, 1));
        dirs.add(new Vec3i(-1, -1, 1));
        dirs.add(new Vec3i(1, -1, -1));
        dirs.add(new Vec3i(-1, -1, -1));
        dirs.add(new Vec3i(0, -1, -1));
        for (Vec3i dir : dirs) {
            CoralNode neighbour = getNodeAtLocation(map, node.x + dir.func_177958_n(), node.y + dir.func_177956_o(), node.z + dir.func_177952_p());
            neighbours.add(neighbour);
        }
        return neighbours;
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/shadowsea/CoralGenerator$CoralTrunk.class */
    private class CoralTrunk {
        private ArrayList<CoralNode> nodes;
        private int radius;
        private CoralNode centre;
        private Vec3i dir;

        private CoralTrunk(CoralNode[][][] map, CoralNode centre, int radius, Vec3i dir) {
            CoralNode node;
            this.nodes = new ArrayList<>();
            this.radius = 0;
            this.centre = null;
            this.dir = null;
            this.centre = centre;
            this.radius = radius;
            this.dir = dir;
            if (radius == 0) {
                CoralNode node2 = CoralGenerator.getNodeAtLocation(map, centre.x + dir.func_177958_n(), centre.y + dir.func_177956_o(), centre.z + dir.func_177952_p());
                if (node2 != null) {
                    this.nodes.add(node2);
                    return;
                }
                return;
            }
            if (dir != null) {
                Vec3i upDir = null;
                Vec3i rightDir = null;
                if (dir.func_177958_n() != 0) {
                    upDir = new Vec3i(0, 1, 0);
                    rightDir = new Vec3i(0, 0, 1);
                } else if (dir.func_177956_o() != 0) {
                    upDir = new Vec3i(1, 0, 0);
                    rightDir = new Vec3i(0, 0, 1);
                } else if (dir.func_177952_p() != 0) {
                    upDir = new Vec3i(0, 1, 0);
                    rightDir = new Vec3i(1, 0, 0);
                }
                for (int i = -radius; i <= radius; i++) {
                    for (int j = -radius; j <= radius; j++) {
                        if ((i * i) + (j * j) <= radius * radius && (node = CoralGenerator.getNodeAtLocation(map, centre.x + (upDir.func_177958_n() * i) + (rightDir.func_177958_n() * j), centre.y + (upDir.func_177956_o() * i) + (rightDir.func_177956_o() * j), centre.z + (upDir.func_177952_p() * i) + (rightDir.func_177952_p() * j))) != null) {
                            this.nodes.add(node);
                        }
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void visit() {
            for (CoralNode node : this.nodes) {
                node.visited = true;
            }
        }
    }

    public void genBrainCoral(BlockPos pos) {
        if (!isTopBlock(pos.func_177977_b())) {
            return;
        }
        int pick = this.world.field_73012_v.nextInt(16);
        int secondPick = (pick - 1) + this.world.field_73012_v.nextInt(3);
        if (secondPick < 0) {
            secondPick = 15;
        } else if (secondPick > 15) {
            secondPick = 0;
        }
        IBlockState brain1 = BlockInit.patternedSmoothCoral.func_176203_a(pick);
        IBlockState brain2 = BlockInit.patternedSmoothCoral.func_176203_a(secondPick);
        int radius = this.world.field_73012_v.nextInt((8 - 4) + 1) + 4;
        for (int i = -radius; i <= radius; i++) {
            for (int k = -radius; k <= radius; k++) {
                for (int j = (int) (-Math.round(((double) radius) / 1.5d)); j <= ((int) Math.round(((double) radius) / 1.5d)); j++) {
                    int sqDist = (((i * i) + ((int) Math.round(((double) (j * j)) * 2.25d))) + (k * k)) - (radius * radius);
                    if (sqDist <= 0) {
                        BlockPos place = pos.func_177982_a(i, j, k);
                        if (this.world.field_73012_v.nextBoolean()) {
                            this.world.func_175656_a(place, brain1);
                        } else {
                            this.world.func_175656_a(place, brain2);
                        }
                    }
                }
            }
        }
    }

    public void GenFlatTopCoral(BlockPos pos) {
        int yOff;
        if (!isTopBlock(pos.func_177977_b())) {
            return;
        }
        int pick = 6 + this.world.field_73012_v.nextInt(4);
        IBlockState stemState = BlockInit.darkRoughCoral.func_176203_a(pick);
        IBlockState topState = BlockInit.roughCoral.func_176203_a(pick);
        Random rand = new Random();
        for (int i = (-2) - 1; i <= 2 + 1; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = (-2) - 1; k <= 2 + 1; k++) {
                    if (j == 0 || j == 4 - 1) {
                        if ((i * i) + (k * k) <= (2 + 1) * (2 + 1)) {
                            this.world.func_175656_a(pos.func_177982_a(i, j, k), stemState);
                        }
                    } else if ((i * i) + (k * k) <= 2 * 2) {
                        this.world.func_175656_a(pos.func_177982_a(i, j, k), stemState);
                    }
                }
            }
        }
        int curRadius = rand.nextInt((15 - 8) + 1) + 8;
        for (int i2 = -15; i2 <= 15; i2++) {
            for (int k2 = -15; k2 <= 15; k2++) {
                if ((i2 * i2) + (k2 * k2) > (curRadius * curRadius) / 3) {
                    yOff = 2;
                } else if ((i2 * i2) + (k2 * k2) > (curRadius * curRadius) / 8) {
                    yOff = 1;
                } else {
                    yOff = 0;
                }
                if ((i2 * i2) + (k2 * k2) < ((curRadius * curRadius) - curRadius) - 2) {
                    this.world.func_175656_a(pos.func_177982_a(i2, 4 + yOff, k2), stemState);
                } else if ((i2 * i2) + (k2 * k2) <= curRadius * curRadius && (i2 * i2) + (k2 * k2) >= ((curRadius * curRadius) - curRadius) - 2 && rand.nextBoolean()) {
                    this.world.func_175656_a(pos.func_177982_a(i2, 4 + yOff, k2), topState);
                }
            }
        }
    }

    public void GenBulbCoral(BlockPos pos) {
        if (!isTopBlock(pos.func_177977_b())) {
            return;
        }
        Random rand = new Random();
        int pick = 6 + this.world.field_73012_v.nextInt(4);
        IBlockState stemState = BlockInit.smoothCoral.func_176203_a(pick);
        IBlockState bulbState = BlockInit.patternedSmoothCoral.func_176203_a(pick);
        for (int i = -10; i <= 10; i++) {
            for (int k = -10; k <= 10; k++) {
                if ((i * i) + (k * k) <= 10 * 10 && rand.nextInt(25) == 0) {
                    GenBulb(pos.func_177982_a(i, 0, k), this.world, null, stemState, bulbState);
                }
            }
        }
    }

    private void GenBulb(BlockPos pos, World world, ArrayList<BlockPos> visited, IBlockState stemState, IBlockState bulbState) {
        if (!isTopBlock(pos.func_177977_b())) {
            return;
        }
        if (visited == null) {
            visited = new ArrayList<>();
        }
        Random rand = new Random();
        int height = rand.nextInt((10 - 6) + 1) + 6;
        Vec3i offset = new Vec3i(0, 0, 0);
        for (int j = 0; j < height; j++) {
            int roll = rand.nextInt(6);
            switch (roll) {
                case 0:
                    offset = new Vec3i(offset.func_177958_n() + 1, 0, offset.func_177952_p());
                    break;
                case 1:
                    offset = new Vec3i(offset.func_177958_n() - 1, 0, offset.func_177952_p());
                    break;
                case 2:
                    offset = new Vec3i(offset.func_177958_n(), 0, offset.func_177952_p() + 1);
                    break;
                case 3:
                    offset = new Vec3i(offset.func_177958_n() + 1, 0, offset.func_177952_p() - 1);
                    break;
            }
            for (int i = -1; i <= 1; i++) {
                for (int k = -1; k <= 1; k++) {
                    world.func_175656_a(pos.func_177982_a(i, j, k).func_177971_a(offset), stemState);
                    if (j > height / 2) {
                        visited.add(pos.func_177982_a(i, j, k).func_177971_a(offset));
                    }
                }
            }
        }
        for (int i2 = -3; i2 <= 3; i2++) {
            for (int j2 = (-3) + 2; j2 <= 3; j2++) {
                for (int k2 = -3; k2 <= 3; k2++) {
                    if ((i2 * i2) + (j2 * j2) + (k2 * k2) < (3 * 3) - 3) {
                        world.func_175656_a(pos.func_177971_a(offset).func_177982_a(i2, j2 + height + 1, k2), bulbState);
                        visited.add(pos.func_177971_a(offset).func_177982_a(i2, j2 + height + 1, k2));
                    }
                }
            }
        }
    }

    public void genSproutCoral(BlockPos pos) {
        if (!isTopBlock(pos.func_177977_b())) {
            return;
        }
        int pick = 6 + this.world.field_73012_v.nextInt(4);
        IBlockState branchState = BlockInit.roughCoral.func_176203_a(pick);
        IBlockState plateState = BlockInit.superRoughCoral.func_176203_a(pick);
        genSproutCoral(branchState, plateState, (CoralNode[][][]) null, null, null, null, pos, this.world);
    }

    public void genSproutCoral(IBlockState branchState, IBlockState plateState, CoralNode[][][] map, CoralTrunk trunk, Vec3i dir, ArrayList<CoralNode> visited, BlockPos pos, World world) {
        int radius;
        int radius2 = 3;
        Random rand = new Random();
        if (map == null) {
            int xSize = rand.nextInt((30 - 20) + 1) + 20;
            int zSize = rand.nextInt((30 - 20) + 1) + 20;
            int ySize = rand.nextInt((35 - 25) + 1) + 25;
            CoralNode[][][] map2 = new CoralNode[xSize][ySize][zSize];
            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    for (int k = 0; k < zSize; k++) {
                        map2[i][j][k] = new CoralNode(i, j, k);
                    }
                }
            }
            CoralNode node = getNodeAtLocation(map2, xSize / 2, 0, zSize / 2);
            node.prev.clear();
            ArrayList<CoralNode> visited2 = new ArrayList<>();
            Vec3i dir2 = new Vec3i(0, 1, 0);
            CoralTrunk next = new CoralTrunk(map2, node, radius2, dir2);
            next.visit();
            visited2.addAll(next.nodes);
            ArrayList<CoralTrunk> queue = new ArrayList<>();
            queue.add(next);
            int iterations = 0;
            while (!queue.isEmpty() && iterations < 1000) {
                iterations++;
                CoralTrunk curTrunk = queue.get(0);
                if (rand.nextInt(4) == 0 && curTrunk.centre.y > ((double) ySize) / 1.5d) {
                    int plateRadius = rand.nextInt(3) + 2;
                    Vec3i offset = new Vec3i(rand.nextInt(3) - 1, 1, rand.nextInt(3) - 1);
                    for (int i2 = -plateRadius; i2 <= plateRadius; i2++) {
                        for (int k2 = -plateRadius; k2 <= plateRadius; k2++) {
                            if ((i2 * i2) + (k2 * k2) <= plateRadius * plateRadius) {
                                world.func_175656_a(pos.func_177982_a(((curTrunk.centre.x + offset.func_177958_n()) + i2) - (xSize / 2), curTrunk.centre.y, ((curTrunk.centre.z + offset.func_177952_p()) + k2) - (zSize / 2)), plateState);
                            }
                        }
                    }
                }
                if (curTrunk.centre.prev.size() > 40) {
                    radius = 1;
                } else {
                    radius = Math.max(1, 2 - (curTrunk.centre.prev.size() / 4));
                }
                queue.remove(curTrunk);
                int roll = rand.nextInt(6);
                if (roll < 4 || curTrunk.centre.prev.size() < radius) {
                    Vec3i newDir = curTrunk.dir;
                    if (!newDir.equals(new Vec3i(0, 1, 0)) && rand.nextInt(3) == 0) {
                        newDir = new Vec3i(0, 1, 0);
                    }
                    CoralNode newCentre = getNodeAtLocation(map2, curTrunk.centre.x + newDir.func_177958_n(), curTrunk.centre.y + newDir.func_177956_o(), curTrunk.centre.z + newDir.func_177952_p());
                    if (newCentre != null && !curTrunk.centre.prev.contains(newCentre)) {
                        CoralTrunk newTrunk = new CoralTrunk(map2, newCentre, radius, newDir);
                        int visitedCount = 0;
                        new ArrayList();
                        Iterator it = newTrunk.nodes.iterator();
                        while (it.hasNext()) {
                            if (((CoralNode) it.next()).visited) {
                                visitedCount++;
                            }
                        }
                        if ((visitedCount <= 2 && radius > 0) || (radius == 0 && visitedCount <= 5)) {
                            newCentre.prev.clear();
                            newCentre.prev.addAll(curTrunk.centre.prev);
                            newCentre.prev.add(curTrunk.centre);
                            newTrunk.visit();
                            visited2.addAll(newTrunk.nodes);
                            queue.add(newTrunk);
                        }
                    }
                } else {
                    ArrayList<Vec3i> dirs = upDirs();
                    dirs.remove(curTrunk.dir);
                    Collections.shuffle(dirs);
                    for (int i3 = 0; i3 < 2; i3++) {
                        Vec3i newDir2 = dirs.get(i3);
                        int mult = 1;
                        if (radius == 0) {
                            mult = 1;
                        }
                        int iFunc_177952_p = curTrunk.centre.z + (newDir2.func_177952_p() * mult);
                        CoralNode newCentre2 = getNodeAtLocation(map2, curTrunk.centre.x + (newDir2.func_177958_n() * mult), curTrunk.centre.y + (newDir2.func_177956_o() * mult), curTrunk.centre.z + (newDir2.func_177952_p() * mult));
                        if (newCentre2 != null && !curTrunk.centre.prev.contains(newCentre2)) {
                            newCentre2.prev.clear();
                            newCentre2.prev.addAll(curTrunk.centre.prev);
                            newCentre2.prev.add(curTrunk.centre);
                            CoralTrunk newTrunk2 = new CoralTrunk(map2, newCentre2, radius, newDir2);
                            int visitedCount2 = 0;
                            ArrayList<CoralNode> checked = new ArrayList<>();
                            for (CoralNode trunkNode : newTrunk2.nodes) {
                                if (trunkNode.visited) {
                                    visitedCount2++;
                                }
                                if (radius == 0) {
                                    for (CoralNode neighbour : getCoralNeighbours(map2, trunkNode)) {
                                        if (neighbour != null && neighbour.visited && !checked.contains(neighbour)) {
                                            visitedCount2++;
                                            checked.add(neighbour);
                                        }
                                    }
                                }
                            }
                            if ((visitedCount2 <= 2 && radius > 0) || (radius == 0 && visitedCount2 <= 5)) {
                                newTrunk2.visit();
                                visited2.addAll(newTrunk2.nodes);
                                queue.add(newTrunk2);
                            }
                        }
                    }
                }
            }
            int count = 0;
            boolean done = false;
            int i4 = 0;
            loop10: while (true) {
                if (i4 >= xSize) {
                    break;
                }
                for (int j2 = 0; j2 < ySize; j2++) {
                    for (int k3 = 0; k3 < zSize; k3++) {
                        if (getNodeAtLocation(map2, i4, j2, k3).visited) {
                            count++;
                            if (count > 85) {
                                done = true;
                                break loop10;
                            }
                        }
                    }
                }
                i4++;
            }
            if (!done) {
                genSproutCoral(branchState, plateState, (CoralNode[][][]) null, null, null, null, pos, world);
                return;
            }
            for (int i5 = 0; i5 < xSize; i5++) {
                for (int j3 = 0; j3 < ySize; j3++) {
                    for (int k4 = 0; k4 < zSize; k4++) {
                        CoralNode countNode = getNodeAtLocation(map2, i5, j3, k4);
                        if (countNode.visited) {
                            BlockPos check = pos.func_177982_a(countNode.x - (xSize / 2), countNode.y, countNode.z - (zSize / 2));
                            if (world.func_180495_p(check) != plateState) {
                                world.func_175656_a(pos.func_177982_a(countNode.x - (xSize / 2), countNode.y, countNode.z - (zSize / 2)), branchState);
                            }
                        }
                    }
                }
            }
        }
    }
}
