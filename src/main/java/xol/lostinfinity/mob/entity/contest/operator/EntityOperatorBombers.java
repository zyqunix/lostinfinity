package xol.lostinfinity.mob.entity.contest.operator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.data.BomberMap;
import xol.lostinfinity.dimension.data.BomberNode;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerBombers;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityOperatorBombers extends EntityOperatorBase {
    private List<BlockPos> spawnPositions;
    private List<BlockPos> powerupPositions;
    public EntityOperatorBombers(World worldIn) {
        super(worldIn);
        this.spawnPositions = new ArrayList();
        this.powerupPositions = new ArrayList();
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void startGame() {
        EntityControllerBombers gamehologram = new EntityControllerBombers(this.field_70170_p);
        BlockPos holopos = ContestCoordinates.bombersControllerPos();
        gamehologram.func_70107_b(holopos.func_177958_n(), holopos.func_177956_o(), holopos.func_177952_p());
        gamehologram.setPlayerCount(this.contenders.size());
        for (BlockPos pos : this.powerupPositions) {
            gamehologram.addPowerupLocation(pos);
        }
        int curSpawn = 0;
        for (UUID pl_id : this.contenders) {
            EntityPlayer pl = this.field_70170_p.func_152378_a(pl_id);
            if (pl != null) {
                gamehologram.addPlayerToList(pl);
                pl.field_71071_by.func_70436_m();
                BlockPos spawnPos = this.spawnPositions.get(curSpawn);
                pl.func_70634_a(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                pl.func_145747_a(new TextComponentString(TextFmt.Aqua + "Grandmaster: Alright contenders, time to blow eachother up for our entertainment!"));
                pl.func_191521_c(new ItemStack(ItemInit.bomberGameDeployer));
                curSpawn++;
            }
        }
        this.field_70170_p.func_72838_d(gamehologram);
        this.spawnPositions.clear();
        this.powerupPositions.clear();
        this.contenders.clear();
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.bombersArenaAABB();
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.bombersLobbyAABB();
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void generateArena() {
        BlockPos arenaPos = ContestCoordinates.bombersArenaPos();
        this.spawnPositions.clear();
        BomberMap game_map = new BomberMap(45, 45, 20);
        game_map.setSpawns(10);
        for (int col = 0; col < 45; col++) {
            for (int row = 0; row < 45; row++) {
                BomberNode node = game_map.getNodeAtLocation(col, row);
                String type = node.getType();
                if (type.contains("spawn")) {
                    this.field_70170_p.func_175698_g(arenaPos.func_177982_a(col, 0, row));
                    this.field_70170_p.func_175698_g(arenaPos.func_177982_a(col, 1, row));
                    this.spawnPositions.add(arenaPos.func_177982_a(col, 0, row));
                } else {
                    switch (type) {
                        case "air":
                            this.field_70170_p.func_175698_g(arenaPos.func_177982_a(col, 0, row));
                            this.field_70170_p.func_175698_g(arenaPos.func_177982_a(col, 1, row));
                            break;
                        case "hard":
                            this.field_70170_p.func_175656_a(arenaPos.func_177982_a(col, 0, row), BlockInit.bomberWallHard.func_176223_P());
                            this.field_70170_p.func_175656_a(arenaPos.func_177982_a(col, 1, row), BlockInit.bomberWallHard.func_176223_P());
                            break;
                        case "soft":
                            this.field_70170_p.func_175656_a(arenaPos.func_177982_a(col, 0, row), BlockInit.bomberWallSoft.func_176223_P());
                            this.field_70170_p.func_175656_a(arenaPos.func_177982_a(col, 1, row), BlockInit.bomberWallSoft.func_176223_P());
                            break;
                        case "powerup":
                            this.powerupPositions.add(arenaPos.func_177982_a(col, 1, row));
                            this.field_70170_p.func_175656_a(arenaPos.func_177982_a(col, 0, row), BlockInit.bomberWallHard.func_176223_P());
                            this.field_70170_p.func_175656_a(arenaPos.func_177982_a(col, 1, row), BlockInit.bombersPowerupClosed.func_176223_P());
                            break;
                    }
                }
            }
        }
        Collections.shuffle(this.spawnPositions);
    }
}
