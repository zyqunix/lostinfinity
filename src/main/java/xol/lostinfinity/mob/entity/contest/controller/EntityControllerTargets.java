package xol.lostinfinity.mob.entity.contest.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockTarget;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.data.TargetNode;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityControllerTargets extends EntityControllerBase {
    private int stageTimer;
    private int betweenTimer;
    private ArrayList<BlockPos> targetPositions;
    private ArrayList<TargetNode> roundTargets;
    private ArrayList<TargetNode> activeTargets;
    private boolean game;
    private int score;
    private static int stageLength = 400;
    private static int betweenRoundLength = 80;
    public EntityControllerTargets(World worldIn) {
        super(worldIn);
        this.stageTimer = 0;
        this.betweenTimer = betweenRoundLength;
        this.targetPositions = null;
        this.roundTargets = null;
        this.activeTargets = null;
        this.game = false;
        this.score = 0;
        func_70105_a(5.0f, 12.0f);
        this.roundTargets = new ArrayList<>();
        this.targetPositions = new ArrayList<>();
        this.activeTargets = new ArrayList<>();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.targetsArenaAABB();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.targetsControllerPos();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 3 == 0 && this.targetPositions == null) {
                func_70106_y();
                return;
            }
            if (this.stage == 0 && !this.game && !this.targetPositions.isEmpty()) {
                setUpRoundTargets();
                this.game = true;
                this.score = 0;
                return;
            }
            if (this.stageTimer == 0) {
                if (this.betweenTimer != 0 && this.stage != 4) {
                    this.betweenTimer--;
                    betweenStage();
                } else {
                    this.betweenTimer = betweenRoundLength;
                    upStage();
                }
                if (this.stage == 5 && this.game) {
                    endGame();
                    return;
                } else {
                    setUpRoundTargets();
                    return;
                }
            }
            this.stageTimer--;
            toggleTargets();
        }
    }
    private void betweenStage() {
        if (this.betweenTimer == betweenRoundLength - 1) {
            resetTargets();
            messageContenders(TextFmt.Green, String.format("ROUND %d STARTING IN", Integer.valueOf(this.stage + 1)));
        } else if (this.betweenTimer % 20 == 0) {
            messageContenders(TextFmt.Green, String.format("%d", Integer.valueOf(this.betweenTimer / 20)));
        }
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void endGame() {
        this.game = false;
        for (BlockPos target : this.targetPositions) {
            this.field_70170_p.func_175656_a(target, ((BlockTarget) BlockInit.target).getInactiveState());
        }
        if (this.score > 90) {
            messageContenders(TextFmt.Green, "You have reached a high enough score!");
            EntityPlayer playerEntity = null;
            func_180425_c();
            Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
            if (it.hasNext()) {
                EntityPlayer player = (EntityPlayer) it.next();
                playerEntity = player;
            }
            int placement = (((this.score * 10) - 900) / 100) + 1;
            if (playerEntity != null) {
                rewardPlayer(playerEntity, placement);
            }
        } else {
            EntityPlayer playerEntity2 = this.field_70170_p.func_152378_a(getPlayerList().get(0));
            rewardPlayer(playerEntity2, 0);
            messageContenders(TextFmt.Green, "You have failed to reach a high enough score.");
        }
        func_70106_y();
    }
    public void scoreTarget(BlockPos pos) {
        int timeDiff;
        if (this.stageTimer == 0) {
            return;
        }
        ArrayList<TargetNode> scored = new ArrayList<>();
        int scoreToAdd = 0;
        Iterator<TargetNode> it = this.activeTargets.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TargetNode activeTarget = it.next();
            if (activeTarget.getPos().equals(pos) && (timeDiff = activeTarget.getTiming() - this.stageTimer) > 0) {
                double ratio = ((double) timeDiff) / ((double) activeTarget.getDuration());
                if (ratio < 0.4d) {
                    scoreToAdd = 3;
                } else if (ratio < 0.7d) {
                    scoreToAdd = 2;
                } else {
                    scoreToAdd = 1;
                }
                scored.add(activeTarget);
                soundContenders(SoundInit.MINIGAME_SCORE, 1.0f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
            }
        }
        this.field_70170_p.func_175656_a(pos, ((BlockTarget) BlockInit.target).getInactiveState());
        this.score += scoreToAdd;
        for (TargetNode scoredTarget : scored) {
            this.activeTargets.remove(scoredTarget);
            setGraceTimer(scoredTarget);
        }
        if (scoreToAdd > 0) {
            scoreMessage();
        }
    }
    private void setGraceTimer(TargetNode activeTarget) {
        ArrayList<TargetNode> toRemove = new ArrayList<>();
        BlockPos pos = activeTarget.getPos();
        for (TargetNode node : this.activeTargets) {
            if (node.getPos().equals(pos) && !node.equals(activeTarget)) {
                if (this.stageTimer - 30 > 0) {
                    node.setTiming(this.stageTimer - 30);
                } else {
                    this.roundTargets.remove(node);
                }
                toRemove.add(node);
            }
        }
        Iterator<TargetNode> it = toRemove.iterator();
        while (it.hasNext()) {
            this.activeTargets.remove(it.next());
        }
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void playerStatusCheck() {
    }
    private void toggleTargets() {
        if (this.roundTargets != null && !this.roundTargets.isEmpty()) {
            for (TargetNode roundTarget : this.roundTargets) {
                if (this.stageTimer == roundTarget.getTiming()) {
                    this.activeTargets.add(roundTarget);
                    this.field_70170_p.func_175656_a(roundTarget.getPos(), ((BlockTarget) BlockInit.target).getActiveState());
                }
            }
        }
        if (this.activeTargets != null && !this.activeTargets.isEmpty()) {
            ArrayList<TargetNode> inactive = new ArrayList<>();
            for (TargetNode activeTarget : this.activeTargets) {
                if (this.stageTimer <= activeTarget.getEndTime()) {
                    inactive.add(activeTarget);
                }
            }
            for (TargetNode inactiveTarget : inactive) {
                this.activeTargets.remove(inactiveTarget);
                this.field_70170_p.func_175656_a(inactiveTarget.getPos(), ((BlockTarget) BlockInit.target).getInactiveState());
            }
        }
    }
    private void scoreMessage() {
        messageContenders(TextFmt.Green, String.format("SCORE : %d", Integer.valueOf(this.score)));
    }
    private void upStage() {
        this.stage++;
        this.stageTimer = stageLength;
        resetTargets();
    }
    private void resetTargets() {
        for (BlockPos pos : this.targetPositions) {
            this.field_70170_p.func_175656_a(pos, ((BlockTarget) BlockInit.target).getInactiveState());
        }
    }
    public void setTargetPositions(World worldIn) {
        AxisAlignedBB arena = ContestCoordinates.targetsArenaAABB();
        for (int i = (int) arena.field_72340_a; i <= ((int) arena.field_72336_d); i++) {
            for (int j = (int) arena.field_72338_b; j <= ((int) arena.field_72337_e); j++) {
                for (int k = (int) arena.field_72339_c; k <= ((int) arena.field_72334_f); k++) {
                    BlockPos ref = new BlockPos(i, j, k);
                    if (this.field_70170_p.func_180495_p(ref).func_177230_c() instanceof BlockTarget) {
                        this.targetPositions.add(ref);
                        this.field_70170_p.func_175656_a(ref, ((BlockTarget) BlockInit.target).getInactiveState());
                    }
                }
            }
        }
    }
    public void setUpRoundTargets() {
        if (this.targetPositions.isEmpty()) {
            return;
        }
        int roundTargets = 0;
        int minDuration = 0;
        int maxDuration = 0;
        switch (this.stage) {
            case TileEntityFusionTable.BOARD_ROWS :
                roundTargets = 40;
                minDuration = 45;
                maxDuration = 55;
                break;
        }
        Random rand = new Random();
        this.roundTargets.clear();
        for (int i = 0; i < roundTargets; i++) {
            int randTarget = rand.nextInt(this.targetPositions.size());
            int randDuration = rand.nextInt(maxDuration - minDuration) + minDuration;
            int randTiming = rand.nextInt(stageLength - randDuration) + randDuration;
            TargetNode node = new TargetNode(this.targetPositions.get(randTarget), randTiming, randDuration);
            this.roundTargets.add(node);
        }
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
            if (player.field_71071_by.func_70301_a(i).func_77973_b() == ItemInit.spaceZapper) {
                player.field_71071_by.func_70299_a(i, ItemStack.field_190927_a);
            }
        }
        BlockPos teleTo = ContestCoordinates.targetsLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        int reward_count = placement * 10;
        player.func_191521_c(new ItemStack(ItemInit.zirconiaRosewood, reward_count));
    }
}
