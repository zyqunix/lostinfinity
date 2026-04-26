package xol.lostinfinity.mob.entity.contest.controller;

import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.contest.operator.EntityOperatorParkour;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/controller/EntityControllerParkour.class */
public class EntityControllerParkour extends EntityControllerBase {
    private int stage;
    private static final int maxStage = 5;
    private int fails;
    private boolean game;

    public EntityControllerParkour(World worldIn) {
        super(worldIn);
        this.stage = 1;
        this.fails = 0;
        this.game = false;
        func_70105_a(5.0f, 12.0f);
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.parkourArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.parkourControllerPos();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    public void registerTouch(EntityPlayer player) {
        if (!this.field_70170_p.field_72995_K && this.game) {
            if (this.stage < maxStage) {
                BlockPos spawn = ContestCoordinates.parkourSpawnPos();
                player.func_70634_a(spawn.func_177958_n(), spawn.func_177956_o(), spawn.func_177952_p());
                stageUp();
                return;
            }
            endGame();
        }
    }

    private void stageUp() {
        this.stage++;
        soundContenders(SoundInit.GENERIC_UI_1, 1.0f, 1.0f);
        messageContenders(TextFmt.Green, String.format("Stage %d begins!", Integer.valueOf(this.stage)));
        EntityOperatorParkour.genParkour(this.field_70170_p, this.stage);
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70177_z = 90.0f;
        if (!this.field_70170_p.field_72995_K && this.game) {
            List<EntityPlayer> inAABB = this.field_70170_p.func_72872_a(EntityPlayer.class, ContestCoordinates.parkourArenaAABB());
            BlockPos spawn = ContestCoordinates.parkourSpawnPos();
            for (UUID playerID : getPlayerList()) {
                EntityPlayer player = this.field_70170_p.func_152378_a(playerID);
                if (player != null && !inAABB.contains(this.field_70170_p.func_152378_a(playerID))) {
                    player.func_70634_a(spawn.func_177958_n(), spawn.func_177956_o(), spawn.func_177952_p());
                    messageContenders(TextFmt.Green, "Stage failed! Try again.");
                    this.fails++;
                }
            }
            for (EntityPlayer near_pl : inAABB) {
                if (near_pl.field_71075_bZ.field_75100_b) {
                    messageContenders(TextFmt.Dark_Red, String.format("%s was removed for trying to fly away", near_pl.func_70005_c_()));
                    removePlayer(near_pl);
                }
                BlockPos playerPos = near_pl.func_180425_c().func_177977_b();
                IBlockState state = this.field_70170_p.func_180495_p(playerPos);
                Block block = state.func_177230_c();
                if (block.equals(BlockInit.parkourPlatform)) {
                    int meta = block.func_176201_c(state);
                    if (meta == 0) {
                        near_pl.func_70024_g(0.0d, 0.6d, 0.0d);
                        near_pl.field_70133_I = true;
                    }
                }
            }
            if (this.field_70173_aa % 40 == 10) {
                AxisAlignedBB parkourGrid = ContestCoordinates.parkourGridAABB();
                for (BlockPos pos : BlockPos.func_191532_a((int) Math.round(parkourGrid.field_72340_a), (int) Math.round(parkourGrid.field_72338_b), (int) Math.round(parkourGrid.field_72339_c), (int) Math.round(parkourGrid.field_72336_d), (int) Math.round(parkourGrid.field_72337_e), (int) Math.round(parkourGrid.field_72334_f))) {
                    if (this.field_70170_p.func_180495_p(pos).equals(BlockInit.parkourPlatform.func_176203_a(2))) {
                        this.field_70170_p.func_175656_a(pos, BlockInit.parkourPlatform.func_176203_a(1));
                    } else if (this.field_70170_p.func_180495_p(pos).equals(BlockInit.parkourPlatform.func_176203_a(1))) {
                        this.field_70170_p.func_175656_a(pos, BlockInit.parkourPlatform.func_176203_a(2));
                    }
                }
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void endGame() {
        this.game = false;
        if (getPlayerList().size() == 1) {
            EntityPlayer playerEntity = this.field_70170_p.func_152378_a(getPlayerList().get(0));
            messageContenders(TextFmt.Green, "Course completed!");
            rewardPlayer(playerEntity, 2);
        }
        func_70106_y();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void playerStatusCheck() {
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        BlockPos teleTo = ContestCoordinates.parkourLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        int reward_count = placement != 2 ? 0 : Math.max(150 - this.fails, 30);
        player.func_191521_c(new ItemStack(ItemInit.zirconiaMusky, reward_count));
    }

    public void startGame() {
        this.game = true;
        this.fails = 0;
        this.stage = 1;
        soundContenders(SoundInit.GENERIC_UI_1, 1.0f, 1.0f);
        messageContenders(TextFmt.Green, "Stage 1 begins! Leap across to the other side to prove your agility!");
    }
}
