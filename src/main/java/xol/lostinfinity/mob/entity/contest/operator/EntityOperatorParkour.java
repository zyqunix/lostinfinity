package xol.lostinfinity.mob.entity.contest.operator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.data.ParkourNode;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerParkour;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/operator/EntityOperatorParkour.class */
public class EntityOperatorParkour extends EntityOperatorBase {
    private List<BlockPos> spawnPositions;
    private static final int normalHeight = 6;
    private static final int minHeight = 3;
    private static final int maxHeight = 9;
    private static final int pillarStartHeight = 22;
    private static final int pillarHeight = 37;

    public EntityOperatorParkour(World worldIn) {
        super(worldIn);
        this.spawnPositions = new ArrayList();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (!this.field_70170_p.field_72995_K) {
            if (isGameInProgress() || this.gameStartCountdown >= 0) {
                player.func_145747_a(new TextComponentString(TextFmt.Red + "A match is currently in progress."));
                return true;
            }
            if (this.timeSinceSwitch == 0) {
                Item held = player.func_184586_b(hand).func_77973_b();
                if (held.equals(ItemInit.eliteContenderPass)) {
                    this.gameStartCountdown = 1;
                    arenaClear();
                    generateArena();
                    return true;
                }
                return true;
            }
            player.func_145747_a(new TextComponentString(TextFmt.Red + "Operator: The arena was just changed. Be patient mortal."));
            return true;
        }
        return true;
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected boolean canStartGame() {
        int pl_count = 0;
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getLobbyAABB())) {
            int i = 0;
            while (true) {
                if (i >= near_pl.field_71071_by.func_70302_i_()) {
                    break;
                }
                if (!near_pl.field_71071_by.func_70301_a(i).func_77973_b().equals(ItemInit.eliteContenderPass)) {
                    i++;
                } else {
                    pl_count++;
                    this.contenders.add(near_pl.func_110124_au());
                    near_pl.field_71071_by.func_70299_a(i, ItemStack.field_190927_a);
                    break;
                }
            }
        }
        return pl_count == 1;
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void startGame() {
        this.spawnPositions.add(ContestCoordinates.parkourSpawnPos());
        EntityControllerParkour gamehologram = new EntityControllerParkour(this.field_70170_p);
        BlockPos pos = ContestCoordinates.parkourControllerPos();
        gamehologram.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        gamehologram.setPlayerCount(1);
        int curSpawn = 0;
        for (UUID pl_id : this.contenders) {
            EntityPlayer pl = this.field_70170_p.func_152378_a(pl_id);
            if (pl != null) {
                gamehologram.addPlayerToList(pl);
                pl.field_71071_by.func_70436_m();
                BlockPos spawnPos = this.spawnPositions.get(curSpawn);
                pl.func_70634_a(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                curSpawn++;
            }
        }
        this.field_70170_p.func_72838_d(gamehologram);
        gamehologram.startGame();
        this.spawnPositions.clear();
        this.contenders.clear();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.parkourArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.parkourLobbyAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void generateArena() {
        if (!this.field_70170_p.field_72995_K) {
            genParkour(this.field_70170_p, 1);
        }
    }

    public static void genParkour(World world, int round) {
        int columns;
        int rows;
        AxisAlignedBB parkourGrid = ContestCoordinates.parkourGridAABB();
        Random rand = new Random();
        Vec3i upDir = new Vec3i(0, 0, 1);
        Vec3i leftDir = new Vec3i(1, 0, 0);
        int diffX = ((int) Math.abs(Math.round(parkourGrid.field_72336_d) - Math.round(parkourGrid.field_72340_a))) + 1;
        int diffZ = ((int) Math.abs(Math.round(parkourGrid.field_72334_f) - Math.round(parkourGrid.field_72339_c))) + 1;
        if (0 != 0) {
            columns = diffZ;
            rows = diffX;
        } else {
            columns = diffX;
            rows = diffZ;
        }
        ArrayList<ParkourNode> nodes = new ArrayList<>();
        ParkourNode[][] grid = new ParkourNode[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new ParkourNode(i, j);
            }
        }
        ParkourNode end = getNodeAtLocation(grid, columns - 1, rows / 2);
        ParkourNode start = getNodeAtLocation(grid, 0, rows / 2);
        start.setVisited(true);
        start.setHeight(6);
        end.setHeight(6);
        if (end == null || start == null) {
            return;
        }
        while (true) {
            if (0 != 0) {
                break;
            }
            nodes.clear();
            for (int i2 = 0; i2 < columns; i2++) {
                for (int j2 = 0; j2 < rows; j2++) {
                    nodes.add(getNodeAtLocation(grid, i2, j2));
                }
            }
            ArrayList<ParkourNode> path = findPath(grid, start, end, new ArrayList());
            if (path != null) {
                for (ParkourNode pathNode : path) {
                    pathNode.setVisited(true);
                }
            }
        }
        for (BlockPos pos : BlockPos.func_191532_a((int) Math.round(parkourGrid.field_72340_a), ((int) Math.round(parkourGrid.field_72338_b)) - 20, (int) Math.round(parkourGrid.field_72339_c), (int) Math.round(parkourGrid.field_72336_d), (int) Math.round(parkourGrid.field_72337_e), (int) Math.round(parkourGrid.field_72334_f))) {
            if (world.func_180495_p(pos).func_177230_c().equals(BlockInit.championGlowingTile) || world.func_180495_p(pos).func_177230_c().equals(BlockInit.arenaBrickBlack) || world.func_180495_p(pos).func_177230_c().equals(BlockInit.championDungeonSelector) || world.func_180495_p(pos).func_177230_c().equals(BlockInit.parkourPlatform) || world.func_180495_p(pos).func_177230_c().equals(BlockInit.parkourPlatformSlippery) || world.func_180495_p(pos).equals(BlockInit.championWall.func_176223_P())) {
                world.func_175698_g(pos);
            }
        }
        ArrayList<ParkourNode> visitedList = new ArrayList<>();
        for (ParkourNode node : nodes) {
            if (node.isVisited()) {
                visitedList.add(node);
            }
        }
        ArrayList<ParkourNode> pillarNodes = new ArrayList<>();
        ArrayList<ParkourNode> pillarsToPlace = new ArrayList<>();
        ArrayList<ParkourNode> adjacents = new ArrayList<>();
        boolean lastPlaced = false;
        for (int i3 = 4; i3 < columns - minHeight; i3++) {
            for (int j3 = 4; j3 < rows - minHeight; j3++) {
                pillarNodes.clear();
                adjacents.clear();
                pillarNodes.add(getNodeAtLocation(grid, i3, j3));
                pillarNodes.add(getNodeAtLocation(grid, i3 + 1, j3));
                pillarNodes.add(getNodeAtLocation(grid, i3, j3 + 1));
                pillarNodes.add(getNodeAtLocation(grid, i3 + 1, j3 + 1));
                boolean visited = false;
                Iterator<ParkourNode> it = pillarNodes.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ParkourNode pillarNode = it.next();
                    if (pillarNode.isVisited()) {
                        visited = true;
                        break;
                    }
                }
                for (ParkourNode adjacent : getAdjacents(grid, pillarNodes, visitedList, pillarsToPlace)) {
                    if (!adjacents.contains(adjacent)) {
                        adjacents.add(adjacent);
                    }
                }
                if (!visited && adjacents.size() == 2) {
                    boolean canPut = true;
                    ParkourNode adj1 = adjacents.get(0);
                    ParkourNode adj2 = adjacents.get(1);
                    if (adj1.getX() == adj2.getX() || adj1.getZ() == adj2.getZ()) {
                        canPut = false;
                    }
                    double dist = adj1.getDistance(adj2);
                    if (dist > 2.5d) {
                        canPut = false;
                    }
                    if (canPut) {
                        if (lastPlaced) {
                            lastPlaced = false;
                        } else {
                            pillarsToPlace.addAll(pillarNodes);
                            lastPlaced = true;
                        }
                    }
                }
            }
        }
        for (ParkourNode pillar : pillarsToPlace) {
            int realX = (leftDir.func_177958_n() * pillar.getX()) + (upDir.func_177958_n() * pillar.getZ()) + ((int) Math.round(parkourGrid.field_72340_a));
            int realZ = (leftDir.func_177952_p() * pillar.getX()) + (upDir.func_177952_p() * pillar.getZ()) + ((int) Math.round(parkourGrid.field_72339_c));
            for (int i4 = 0; i4 < pillarHeight; i4++) {
                world.func_175656_a(new BlockPos(realX, pillarStartHeight + i4, realZ), BlockInit.championWall.func_176223_P());
            }
        }
        boolean last = false;
        for (ParkourNode node2 : visitedList) {
            int realX2 = (leftDir.func_177958_n() * node2.getX()) + (upDir.func_177958_n() * node2.getZ()) + ((int) Math.round(parkourGrid.field_72340_a));
            int realZ2 = (leftDir.func_177952_p() * node2.getX()) + (upDir.func_177952_p() * node2.getZ()) + ((int) Math.round(parkourGrid.field_72339_c));
            int realY = node2.getHeight() + 40;
            if (node2.equals(end)) {
                world.func_175656_a(new BlockPos(realX2 + upDir.func_177958_n() + 1, 46, realZ2 + upDir.func_177952_p()), BlockInit.championDungeonSelector.func_176223_P());
            }
            switch (round) {
                case 1:
                    int randMeta = rand.nextInt(5);
                    world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.championGlowingTile.func_176203_a(randMeta));
                    break;
                case 2:
                    if (rand.nextInt(minHeight) == 0) {
                        world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.parkourPlatformSlippery.func_176223_P());
                    } else {
                        int randMeta2 = rand.nextInt(5);
                        world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.championGlowingTile.func_176203_a(randMeta2));
                    }
                    break;
                case minHeight /* 3 */:
                    if (rand.nextInt(minHeight) == 0) {
                        world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.parkourPlatform.func_176203_a(0));
                    } else {
                        int randMeta3 = rand.nextInt(5);
                        world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.championGlowingTile.func_176203_a(randMeta3));
                    }
                    break;
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    if (rand.nextInt(minHeight) == 0) {
                        if (last) {
                            world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.parkourPlatform.func_176203_a(1));
                            last = false;
                        } else {
                            world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.parkourPlatform.func_176203_a(2));
                            last = true;
                        }
                    } else {
                        int randMeta4 = rand.nextInt(5);
                        world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.championGlowingTile.func_176203_a(randMeta4));
                    }
                    break;
                case 5:
                    int randChoice = rand.nextInt(minHeight);
                    switch (randChoice) {
                        case 0:
                            world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.parkourPlatformSlippery.func_176223_P());
                            break;
                        case 1:
                            world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.parkourPlatform.func_176203_a(0));
                            break;
                        case 2:
                            if (last) {
                                world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.parkourPlatform.func_176203_a(1));
                                last = false;
                            } else {
                                world.func_175656_a(new BlockPos(realX2, realY, realZ2), BlockInit.parkourPlatform.func_176203_a(2));
                                last = true;
                            }
                            break;
                    }
                    break;
            }
        }
    }

    public static ArrayList<ParkourNode> findPath(ParkourNode[][] grid, ParkourNode cur, ParkourNode end, ArrayList<ParkourNode> path) {
        int height;
        ArrayList<ParkourNode> nodePath;
        Random rand = new Random();
        if (path == null) {
            path = new ArrayList<>();
        }
        if (path.contains(cur) || hasAdjacents(grid, cur, path)) {
            return null;
        }
        if (cur.equals(end)) {
            end.setVisited(true);
            return path;
        }
        path.add(cur);
        int height2 = cur.getHeight();
        int roll = rand.nextInt(minHeight);
        if (roll < 2 && cur.getHeight() < maxHeight) {
            height = height2 + 1;
        } else {
            height = height2 - rand.nextInt((cur.getHeight() - minHeight) + 1);
        }
        int dist = 4;
        if (height > cur.getHeight()) {
            dist = minHeight;
        }
        ArrayList<Vec3i> dirs = getDirs(dist);
        Collections.shuffle(dirs);
        for (Vec3i dir : dirs) {
            ParkourNode node = getNodeAtLocation(grid, cur.getX() + dir.func_177958_n(), cur.getZ() + dir.func_177952_p());
            if (node != null && !node.isVisited() && !path.contains(node)) {
                node.setHeight(height);
                if (cur.isNeighbour(node) && !hasAdjacents(grid, node, path) && (nodePath = findPath(grid, node, end, path)) != null) {
                    return nodePath;
                }
            }
        }
        return null;
    }

    public static boolean hasAdjacents(ParkourNode[][] grid, ParkourNode node, ArrayList<ParkourNode> path) {
        ParkourNode adjacent;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if ((i != 0 || j != 0) && (adjacent = getNodeAtLocation(grid, node.getX() + i, node.getZ() + j)) != null && path.contains(adjacent)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static ArrayList<ParkourNode> getAdjacents(ParkourNode[][] grid, ArrayList<ParkourNode> nodes, ArrayList<ParkourNode> path, ArrayList<ParkourNode> exclude) {
        ArrayList<ParkourNode> adjacents = new ArrayList<>();
        for (ParkourNode node : nodes) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        if (i != 0 || j != 0) {
                            ParkourNode adjacent = getNodeAtLocation(grid, node.getX() + i, node.getZ() + j);
                            if (exclude.contains(adjacent)) {
                                adjacents.clear();
                                return adjacents;
                            }
                            if (adjacent != null && path.contains(adjacent) && !adjacents.contains(adjacent)) {
                                adjacents.add(adjacent);
                            }
                        }
                    }
                }
            }
        }
        return adjacents;
    }

    public static ArrayList<Vec3i> getDirs(int dist) {
        ArrayList<Vec3i> dirs = new ArrayList<>();
        for (int i = 0; i <= dist; i++) {
            for (int j = -dist; j <= dist; j++) {
                if ((i != 0 || j != 0) && i != 0) {
                    double radius = Math.sqrt((i * i) + (j * j));
                    if (radius <= dist && radius >= 1.0d) {
                        dirs.add(new Vec3i(i, 0, j));
                    }
                }
            }
        }
        return dirs;
    }

    public static ParkourNode getNodeAtLocation(ParkourNode[][] grid, int x, int z) {
        if (grid != null && x >= 0 && x < grid.length && z >= 0 && z < grid[x].length) {
            return grid[x][z];
        }
        return null;
    }
}
