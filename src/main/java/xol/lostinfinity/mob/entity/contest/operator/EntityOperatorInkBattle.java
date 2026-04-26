package xol.lostinfinity.mob.entity.contest.operator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerInkBattle;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityOperatorInkBattle extends EntityOperatorBase {
    private List<BlockPos> spawnPositions;
    public EntityOperatorInkBattle(World worldIn) {
        super(worldIn);
        this.spawnPositions = new ArrayList();
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void startGame() {
        this.spawnPositions = ContestCoordinates.inkBattleSpawnPositions();
        for (EntityControllerInkBattle entity : this.field_70170_p.func_72872_a(EntityControllerInkBattle.class, getArenaAABB())) {
            entity.func_70106_y();
        }
        EntityControllerInkBattle gamehologram = new EntityControllerInkBattle(this.field_70170_p);
        BlockPos pos = ContestCoordinates.inkBattleControllerPos();
        gamehologram.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        gamehologram.setPlayerCount(this.contenders.size());
        int curSpawn = 0;
        ArrayList<UUID> toRemove = new ArrayList<>();
        for (UUID pl_id : this.contenders) {
            if (this.contenders.indexOf(pl_id) > 6) {
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
                gamehologram.setInk(pl_id3, curSpawn + 1);
                pl.field_71071_by.func_70436_m();
                pl.func_191521_c(new ItemStack(ItemInit.inkGun, 1));
                BlockPos spawnPos = this.spawnPositions.get(curSpawn);
                pl.func_70634_a(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                pl.func_145747_a(new TextComponentString(TextFmt.Aqua + "Grandmaster: Alright contenders, try to ink the most blocks to your colour!"));
                curSpawn++;
            }
        }
        this.field_70170_p.func_72838_d(gamehologram);
        gamehologram.initInkBattle(this.spawnPositions);
        this.spawnPositions.clear();
        this.contenders.clear();
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.inkBattleArenaAABB();
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.inkBattleLobbyAABB();
    }
}
