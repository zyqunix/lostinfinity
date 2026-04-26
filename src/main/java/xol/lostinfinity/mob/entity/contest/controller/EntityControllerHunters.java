package xol.lostinfinity.mob.entity.contest.controller;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.contest.EntityBloodhunter;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityControllerHunters extends EntityControllerBase {
    private int stageTimer;
    public EntityControllerHunters(World worldIn) {
        super(worldIn);
        this.stageTimer = 200;
        func_70105_a(3.0f, 6.0f);
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.grandmasterOutpost) {
            if (this.stageTimer == 0) {
                upStage();
                return;
            }
            if (this.stageTimer == 5 && this.touchInProgress) {
                removeFailedCheckIn();
            }
            this.stageTimer--;
        }
    }
    private void upStage() {
        this.stage++;
        boolean summonStage = this.field_70146_Z.nextBoolean();
        if (this.stage == 1 || this.stage == 5) {
            summonStage = true;
            if (this.stage == 5) {
                for (EntityBloodhunter hunters : this.field_70170_p.func_72872_a(EntityBloodhunter.class, getArenaAABB())) {
                    hunters.setRanged();
                    messageContenders(TextFmt.Red, "Okay contenders, the hunters now attack with spells!");
                }
            }
        }
        if (summonStage) {
            EntityBloodhunter hunter = new EntityBloodhunter(this.field_70170_p);
            BlockPos spawnPos = ContestCoordinates.huntersArenaCenterPos().func_177984_a();
            hunter.func_70107_b(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
            if (this.stage >= 5) {
                hunter.setRanged();
            }
            this.field_70170_p.func_72838_d(hunter);
            this.stageTimer = 400;
            messageContenders(TextFmt.Gold, "A new hunter has been released!");
            return;
        }
        this.stageTimer = 300;
        this.touchInProgress = true;
        messageContenders(TextFmt.Dark_Aqua, "Time to come out of hiding! You have 15 seconds to touch a sensor block.");
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.huntersArenaAABB();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.huntersControllerPos();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        BlockPos teleTo = ContestCoordinates.huntersLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        int reward_count = Math.min(1 + (placement * this.stage) + (placement == this.contenderCount - 1 ? this.contenderCount * 2 : 0), 50);
        player.func_191521_c(new ItemStack(ItemInit.zirconiaCrimson, reward_count));
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void endGame() {
        for (EntityBloodhunter hunter : this.field_70170_p.func_72872_a(EntityBloodhunter.class, getArenaAABB())) {
            hunter.func_70106_y();
        }
    }
}
