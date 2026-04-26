package xol.lostinfinity.mob.entity.contest.operator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.item.EntityItemFrame;
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
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerDuelArena;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityOperatorDuelArena extends EntityOperatorBase {
    private List<BlockPos> spawnPositions;
    public EntityOperatorDuelArena(World worldIn) {
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
            Item held = player.func_184586_b(hand).func_77973_b();
            ItemStack stack = player.func_184586_b(hand);
            ItemStack temp1 = null;
            ItemStack temp2 = null;
            Iterator it = this.field_70170_p.func_72872_a(EntityItemFrame.class, new AxisAlignedBB(ContestCoordinates.duelFrame1Pos())).iterator();
            if (it.hasNext()) {
                EntityItemFrame frames = (EntityItemFrame) it.next();
                temp1 = frames.func_82335_i();
            }
            Iterator it2 = this.field_70170_p.func_72872_a(EntityItemFrame.class, new AxisAlignedBB(ContestCoordinates.duelFrame2Pos())).iterator();
            if (it2.hasNext()) {
                EntityItemFrame frames2 = (EntityItemFrame) it2.next();
                temp2 = frames2.func_82335_i();
            }
            if (held.equals(ItemInit.contenderPass)) {
                if (!temp1.func_190926_b() && !temp2.func_190926_b()) {
                    this.gameStartCountdown = 200;
                    arenaClear();
                } else {
                    return true;
                }
            } else if (temp1.func_190926_b()) {
                Iterator it3 = this.field_70170_p.func_72872_a(EntityItemFrame.class, new AxisAlignedBB(ContestCoordinates.duelFrame1Pos())).iterator();
                if (it3.hasNext()) {
                    EntityItemFrame frames3 = (EntityItemFrame) it3.next();
                    frames3.func_82334_a(stack.func_77946_l());
                    player.func_145747_a(new TextComponentString(TextFmt.Aqua + "THANK YOU FOR YOU ITEM!"));
                }
            } else if (temp2.func_190926_b()) {
                Iterator it4 = this.field_70170_p.func_72872_a(EntityItemFrame.class, new AxisAlignedBB(ContestCoordinates.duelFrame2Pos())).iterator();
                if (it4.hasNext()) {
                    EntityItemFrame frames4 = (EntityItemFrame) it4.next();
                    frames4.func_82334_a(stack.func_77946_l());
                    player.func_145747_a(new TextComponentString(TextFmt.Aqua + "THANK YOU FOR YOU ITEM!"));
                }
            } else {
                return true;
            }
        }
        player.func_184586_b(hand).func_190918_g(1);
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
                if (!near_pl.field_71071_by.func_70301_a(i).func_77973_b().equals(ItemInit.contenderPass)) {
                    i++;
                } else {
                    pl_count++;
                    this.contenders.add(near_pl.func_110124_au());
                    near_pl.field_71071_by.func_70299_a(i, ItemStack.field_190927_a);
                    break;
                }
            }
        }
        return pl_count == 2;
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void startGame() {
        if (this.contenders.size() != 2) {
            return;
        }
        this.spawnPositions.add(new BlockPos(99, 33, 256));
        this.spawnPositions.add(new BlockPos(155, 33, 256));
        EntityControllerDuelArena gamehologram = new EntityControllerDuelArena(this.field_70170_p);
        BlockPos pos = ContestCoordinates.duelControllerPos();
        gamehologram.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        gamehologram.setPlayerCount(this.contenders.size());
        int curSpawn = 0;
        for (UUID pl_id : this.contenders) {
            EntityPlayer pl = this.field_70170_p.func_152378_a(pl_id);
            if (pl != null) {
                gamehologram.addPlayerToList(pl);
                pl.field_71071_by.func_70436_m();
                pl.func_191521_c(new ItemStack(ItemInit.duelingSwordDull, 1));
                BlockPos spawnPos = this.spawnPositions.get(curSpawn);
                pl.func_70634_a(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                pl.func_145747_a(new TextComponentString(TextFmt.Aqua + "Grandmaster: Alright contenders, last one standing wins!"));
                curSpawn++;
            }
        }
        gamehologram.initDuel();
        this.field_70170_p.func_72838_d(gamehologram);
        this.spawnPositions.clear();
        this.contenders.clear();
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.duelArenaAABB();
    }
    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.duelLobbyAABB();
    }
}
