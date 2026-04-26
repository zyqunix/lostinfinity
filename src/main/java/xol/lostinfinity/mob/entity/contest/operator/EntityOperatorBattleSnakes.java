package xol.lostinfinity.mob.entity.contest.operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerBattleSnakes;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/operator/EntityOperatorBattleSnakes.class */
public class EntityOperatorBattleSnakes extends EntityOperatorBase {
    private List<BlockPos> spawnPositions;

    public EntityOperatorBattleSnakes(World worldIn) {
        super(worldIn);
        this.spawnPositions = new ArrayList();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void startGame() {
        for (EntityControllerBattleSnakes entity : this.field_70170_p.func_72872_a(EntityControllerBattleSnakes.class, getArenaAABB())) {
            entity.func_70106_y();
        }
        EntityControllerBattleSnakes gamehologram = new EntityControllerBattleSnakes(this.field_70170_p);
        BlockPos pos = ContestCoordinates.battleSnakesControllerPos();
        gamehologram.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        gamehologram.setPlayerCount(this.contenders.size());
        HashMap<UUID, BlockPos> controllerMap = new HashMap<>();
        ArrayList<BlockPos> controlPositions = ContestCoordinates.battleSnakeControlPositions();
        int curSpawn = 0;
        ArrayList<UUID> toRemove = new ArrayList<>();
        for (UUID pl_id : this.contenders) {
            if (this.contenders.indexOf(pl_id) > 4) {
                toRemove.add(pl_id);
            }
        }
        for (UUID pl_id2 : toRemove) {
            this.contenders.remove(pl_id2);
            this.field_70170_p.func_152378_a(pl_id2).func_145747_a(new TextComponentString(TextFmt.Aqua + "Too many players, please join the next game"));
        }
        for (UUID pl_id3 : this.contenders) {
            EntityPlayer pl = this.field_70170_p.func_152378_a(pl_id3);
            if (pl != null) {
                gamehologram.addPlayerToList(pl);
                pl.field_71071_by.func_70436_m();
                controllerMap.put(pl_id3, controlPositions.get(curSpawn));
                BlockPos spawnPos = this.spawnPositions.get(curSpawn);
                pl.func_70634_a(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                pl.func_145747_a(new TextComponentString(TextFmt.Aqua + "Grandmaster: Alright contenders, guide your snakes to victory!"));
                curSpawn++;
            }
        }
        this.field_70170_p.func_72838_d(gamehologram);
        gamehologram.setupSnakes();
        gamehologram.setControllerMap(controllerMap);
        this.spawnPositions.clear();
        this.contenders.clear();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void arenaClear() {
        BlockPos arenaPos = ContestCoordinates.battleSnakesArenaPos();
        int row = (int) Math.abs(getBoardAABB().field_72334_f - getBoardAABB().field_72339_c);
        int col = (int) Math.abs(getBoardAABB().field_72336_d - getBoardAABB().field_72340_a);
        for (int i = 0; i <= col; i++) {
            for (int j = 0; j <= row; j++) {
                this.field_70170_p.func_175698_g(arenaPos.func_177982_a(i, 2, j));
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.battleSnakesArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.battleSnakesLobbyAABB();
    }

    protected AxisAlignedBB getBoardAABB() {
        return ContestCoordinates.battleSnakesBoardAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void generateArena() {
        ContestCoordinates.battleSnakesArenaPos();
        this.spawnPositions.clear();
        int row = (int) Math.abs(getBoardAABB().field_72334_f - getBoardAABB().field_72339_c);
        int col = (int) Math.abs(getBoardAABB().field_72336_d - getBoardAABB().field_72340_a);
        for (int i = 0; i <= col; i++) {
            for (int j = 0; j <= row; j++) {
            }
        }
        this.spawnPositions = ContestCoordinates.battleSnakeSpawnPositions();
    }
}
