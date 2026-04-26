package xol.lostinfinity.mob.entity.contest.operator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerLightBridge;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/operator/EntityOperatorLightBridge.class */
public class EntityOperatorLightBridge extends EntityOperatorBase {
    private List<BlockPos> spawnPositions;
    private BlockPos ref;

    public EntityOperatorLightBridge(World worldIn) {
        super(worldIn);
        this.spawnPositions = new ArrayList();
        this.ref = null;
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void startGame() {
        EntityControllerLightBridge gamehologram = new EntityControllerLightBridge(this.field_70170_p);
        BlockPos pos = ContestCoordinates.lightBridgeControllerPos();
        gamehologram.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        gamehologram.setPlayerCount(this.contenders.size());
        HashMap<UUID, BlockPos> respawnPositions = new HashMap<>();
        int curSpawn = 0;
        for (UUID pl_id : this.contenders) {
            EntityPlayer pl = this.field_70170_p.func_152378_a(pl_id);
            if (pl != null) {
                gamehologram.addPlayerToList(pl);
                pl.field_71071_by.func_70436_m();
                BlockPos spawnPos = this.spawnPositions.get(curSpawn);
                pl.func_70634_a(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                respawnPositions.put(pl_id, new BlockPos(spawnPos));
                pl.func_145747_a(new TextComponentString(TextFmt.Aqua + "Grandmaster: Alright contenders, lets see who can cross to the other side first!"));
                curSpawn++;
            }
        }
        int col = (int) Math.abs(getBoardAABB().field_72334_f - getBoardAABB().field_72339_c);
        int row = (int) Math.abs(getBoardAABB().field_72336_d - getBoardAABB().field_72340_a);
        gamehologram.setRespawns(respawnPositions);
        gamehologram.setBridgePositions(this.ref, col, row, this.field_70170_p);
        this.field_70170_p.func_72838_d(gamehologram);
        this.spawnPositions.clear();
        this.contenders.clear();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.lightBridgeArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.lightBridgeLobbyAABB();
    }

    protected AxisAlignedBB getBoardAABB() {
        return ContestCoordinates.lightBridgeBoardAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void generateArena() {
        BlockPos arenaPos = ContestCoordinates.lightBridgeArenaPos();
        this.spawnPositions.clear();
        int col = (int) Math.abs(getBoardAABB().field_72334_f - getBoardAABB().field_72339_c);
        for (int i = 0; i < col; i++) {
            this.spawnPositions.add(arenaPos.func_177982_a(0, 1, i));
        }
        Collections.shuffle(this.spawnPositions);
        this.ref = arenaPos.func_177982_a(1, 0, 0);
    }
}
