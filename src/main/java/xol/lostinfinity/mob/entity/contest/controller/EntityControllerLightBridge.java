package xol.lostinfinity.mob.entity.contest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.data.LightBridgeNode;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/controller/EntityControllerLightBridge.class */
public class EntityControllerLightBridge extends EntityControllerBase {
    private Vec3i dirRight;
    private Vec3i dirUp;
    private LightBridgeNode[][] bridgeMap;
    private ArrayList<LightBridgeNode> previewBridge;
    private boolean game;
    private BlockPos ref;
    private HashMap<UUID, BlockPos> respawnPositions;

    public EntityControllerLightBridge(World worldIn) {
        super(worldIn);
        this.dirRight = new Vec3i(0, 0, 1);
        this.dirUp = new Vec3i(1, 0, 0);
        this.previewBridge = new ArrayList<>();
        this.game = false;
        this.ref = new BlockPos(0, 0, 0);
        func_70105_a(5.0f, 12.0f);
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    public AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.lightBridgeArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 40 == 20 && this.bridgeMap != null) {
                this.previewBridge.clear();
                Random rand = new Random();
                int randCol = rand.nextInt(this.bridgeMap.length - 8);
                int randRow = rand.nextInt(this.bridgeMap[randCol].length - 8);
                for (int c = 0; c < 8; c++) {
                    for (int r = 0; r < 8; r++) {
                        LightBridgeNode node = getNodeAtLocation(randCol + c, randRow + r);
                        if (node.isLit()) {
                            this.previewBridge.add(node);
                            this.field_70170_p.func_175656_a(getBridgeNodePos(node), BlockInit.lightBridge.func_176203_a(1));
                        }
                    }
                }
            }
            if (this.field_70173_aa % 40 == 0) {
                this.previewBridge.clear();
            }
            if (this.bridgeMap != null) {
                for (int i = 0; i < this.bridgeMap.length; i++) {
                    for (int j = 0; j < this.bridgeMap[0].length; j++) {
                        if (!this.previewBridge.contains(getNodeAtLocation(i, j))) {
                            this.field_70170_p.func_175656_a(getBridgeNodePos(getNodeAtLocation(i, j)), BlockInit.lightBridge.func_176203_a(0));
                        }
                    }
                }
            }
            List<EntityPlayer> inAABB = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB());
            for (UUID playerID : getPlayerList()) {
                if (!inAABB.contains(this.field_70170_p.func_152378_a(playerID))) {
                    respawn(playerID);
                }
            }
            for (EntityPlayer near_pl : inAABB) {
                if (near_pl.field_71075_bZ.field_75100_b) {
                    removePlayer(near_pl);
                }
                if (getPlayerList().contains(near_pl.func_110124_au())) {
                    int x = getBridgeX(near_pl);
                    int y = getBridgeY(near_pl);
                    LightBridgeNode node2 = getNodeAtLocation(x, y);
                    if (node2 != null && node2.isLit()) {
                        this.field_70170_p.func_175656_a(getBridgeNodePos(node2), BlockInit.lightBridge.func_176203_a(1));
                    }
                }
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    public void registerTouch(EntityPlayer player) {
        List<UUID> toRemove = new ArrayList<>();
        for (UUID pl_id : getPlayerList()) {
            if (!pl_id.equals(player.func_110124_au())) {
                toRemove.add(pl_id);
            }
        }
        removeMultiplePlayers(toRemove);
    }

    private int getBridgeX(EntityPlayer player) {
        return player.func_180425_c().func_177952_p() - this.ref.func_177952_p();
    }

    private int getBridgeY(EntityPlayer player) {
        return player.func_180425_c().func_177958_n() - this.ref.func_177958_n();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void playerStatusCheck() {
        boolean end_flag = false;
        if (getPlayerList().isEmpty()) {
            end_flag = true;
        }
        if (!end_flag && getPlayerList().size() == 1) {
            EntityPlayer winner = this.field_70170_p.func_152378_a(getPlayerList().get(0));
            if (winner != null) {
                rewardPlayer(winner, this.contenderCount - 1);
            }
            end_flag = true;
        }
        if (end_flag) {
            func_70106_y();
            endGame();
        }
    }

    private void respawn(UUID pl_id) {
        if (this.respawnPositions.get(pl_id) != null) {
            BlockPos respawnPos = this.respawnPositions.get(pl_id);
            this.field_70170_p.func_152378_a(pl_id).func_70634_a(respawnPos.func_177958_n(), respawnPos.func_177956_o(), respawnPos.func_177952_p());
            messageContenders(TextFmt.Red, String.format("%s has fallen", this.field_70170_p.func_152378_a(pl_id).func_70005_c_()));
        }
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.lightBridgeControllerPos();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        BlockPos teleTo = ContestCoordinates.lightBridgeLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        int reward_count = placement == this.contenderCount - 1 ? 20 * this.contenderCount : 0;
        player.func_191521_c(new ItemStack(ItemInit.zirconiaOyster, reward_count));
    }

    public void setBridgePositions(BlockPos ref, int columns, int rows, World worldIn) {
        ArrayList<LightBridgeNode> bridge;
        this.bridgeMap = new LightBridgeNode[columns][rows];
        this.ref = ref;
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                BlockPos pos = ref.func_177982_a((this.dirRight.func_177958_n() * c) + (this.dirUp.func_177958_n() * r), 0, (this.dirRight.func_177952_p() * c) + (this.dirUp.func_177952_p() * r));
                worldIn.func_175656_a(pos, BlockInit.lightBridge.func_176203_a(0));
                this.bridgeMap[c][r] = new LightBridgeNode(c, r, false);
            }
        }
        Random rand = new Random();
        int randCol = rand.nextInt(this.bridgeMap.length);
        ArrayList<LightBridgeNode> pathToNode = getNodeAtLocation(randCol, 0).getPathToNode(this.bridgeMap, null, rows - 1);
        while (true) {
            bridge = pathToNode;
            if (bridge != null) {
                break;
            }
            int randCol2 = rand.nextInt(this.bridgeMap.length);
            pathToNode = getNodeAtLocation(randCol2, 0).getPathToNode(this.bridgeMap, null, rows - 1);
        }
        if (bridge != null) {
            for (LightBridgeNode bridgeNode : bridge) {
                bridgeNode.setLit(true);
            }
        }
    }

    private BlockPos getBridgeNodePos(LightBridgeNode bridgeNode) {
        return this.ref.func_177982_a((this.dirRight.func_177958_n() * bridgeNode.getX()) + (this.dirUp.func_177958_n() * bridgeNode.getY()), 0, (this.dirRight.func_177952_p() * bridgeNode.getX()) + (this.dirUp.func_177952_p() * bridgeNode.getY()));
    }

    private LightBridgeNode getNodeAtLocation(int x, int y) {
        if (x >= 0 && x < this.bridgeMap.length && y >= 0 && y < this.bridgeMap[x].length) {
            return this.bridgeMap[x][y];
        }
        return null;
    }

    public void setRespawns(HashMap<UUID, BlockPos> respawnPositions) {
        this.respawnPositions = respawnPositions;
    }
}
