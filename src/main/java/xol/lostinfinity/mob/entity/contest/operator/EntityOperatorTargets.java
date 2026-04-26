package xol.lostinfinity.mob.entity.contest.operator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerTargets;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityOperatorTargets extends EntityOperatorBase {
    private List<BlockPos> spawnPositions;
    public EntityOperatorTargets(World worldIn) {
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
        this.spawnPositions.add(ContestCoordinates.targetsSpawnPos());
        EntityControllerTargets gamehologram = new EntityControllerTargets(this.field_70170_p);
        BlockPos pos = ContestCoordinates.targetsControllerPos();
        gamehologram.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        gamehologram.setPlayerCount(1);
        int curSpawn = 0;
        for (UUID pl_id : this.contenders) {
            EntityPlayer pl = this.field_70170_p.func_152378_a(pl_id);
            if (pl != null) {
                gamehologram.addPlayerToList(pl);
                pl.field_71071_by.func_70436_m();
                pl.func_191521_c(new ItemStack(ItemInit.spaceZapper, 1));
                BlockPos spawnPos = this.spawnPositions.get(curSpawn);
                pl.func_70634_a(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                gamehologram.setTargetPositions(this.field_70170_p);
                curSpawn++;
            }
        }
        this.field_70170_p.func_72838_d(gamehologram);
        this.spawnPositions.clear();
        this.contenders.clear();
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.targetsArenaAABB();
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.targetsLobbyAABB();
    }
}
