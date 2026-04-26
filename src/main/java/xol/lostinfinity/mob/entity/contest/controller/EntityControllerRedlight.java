package xol.lostinfinity.mob.entity.contest.controller;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.data.ChunkPairing;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityControllerRedlight extends EntityControllerBase {
    private int stageTimer;
    private int gracePeriod;
    private HashMap<UUID, BlockPos> playerPositions;
    public EntityControllerRedlight(World worldIn) {
        super(worldIn);
        this.stageTimer = 200;
        this.gracePeriod = 0;
        this.playerPositions = new HashMap<>();
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
            if (this.gracePeriod > 0) {
                this.gracePeriod--;
            } else if (this.stage % 2 == 1 && this.field_70173_aa % 2 == 0) {
                scanArenaForMoving();
            }
            this.stageTimer--;
        }
    }
    private void upStage() {
        this.stage++;
        if (this.stage % 2 == 0) {
            messageContendersWithSound(TextFmt.Green, "Green Light", SoundInit.GAME_DING);
            this.stageTimer = this.stage > 14 ? 60 + this.field_70146_Z.nextInt(60) : 100 + this.field_70146_Z.nextInt(100);
            this.playerPositions.clear();
            setLights(BlockInit.championSignalGreen);
            if (this.stage % 8 == 2) {
                newLights();
                return;
            }
            return;
        }
        messageContendersWithSound(TextFmt.Red, "RED LIGHT", SoundInit.GAME_BUZZER);
        cycleBlocks();
        setLights(BlockInit.championSignalRed);
        this.stageTimer = 140;
        this.gracePeriod = 15;
        if (this.stage > 1 && this.stage % 8 == 1) {
            handleCheckIn();
        }
    }
    private void scanArenaForMoving() {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            UUID contender_id = contender.func_110124_au();
            BlockPos contender_pos = contender.func_180425_c();
            if (this.playerPositions.containsKey(contender_id) && !this.playerPositions.get(contender_id).equals(contender_pos)) {
                removePlayerByUUID(contender_id);
            }
            this.playerPositions.put(contender_id, contender_pos);
        }
    }
    private void newLights() {
        this.touchInProgress = true;
        messageContenders(TextFmt.Gold, "You have 4 rounds to check in to a lit pillar.");
        int pick1 = this.field_70146_Z.nextInt(5);
        int iNextInt = this.field_70146_Z.nextInt(5);
        while (true) {
            int pick2 = iNextInt;
            if (pick1 == pick2) {
                iNextInt = this.field_70146_Z.nextInt(5);
            } else {
                lightPillars(pick1, pick2);
                return;
            }
        }
    }
    private void handleCheckIn() {
        removeFailedCheckIn();
        darkenPillars();
    }
    public void setLights(Block block) {
        ChunkPairing chunk = ContestCoordinates.redlightGenLoc();
        int chunkX = chunk.chunkX();
        int chunkZ = chunk.chunkZ();
        int blockX = 3 + (chunkX * 16);
        int blockZ = 3 + (chunkZ * 16);
        for (int width = 0; width < 2; width++) {
            for (int distance = 0; distance < 2; distance++) {
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 4; col++) {
                        this.field_70170_p.func_175656_a(new BlockPos(blockX + (28 * distance) + 13 + row, 28 + col, blockZ + (width * 57)), block.func_176223_P());
                    }
                }
            }
        }
        for (int width2 = 0; width2 < 2; width2++) {
            for (int distance2 = 0; distance2 < 2; distance2++) {
                for (int row2 = 0; row2 < 4; row2++) {
                    for (int col2 = 0; col2 < 4; col2++) {
                        this.field_70170_p.func_175656_a(new BlockPos(blockX + (width2 * 57), 28 + col2, blockZ + (28 * distance2) + 13 + row2), block.func_176223_P());
                    }
                }
            }
        }
    }
    private void cycleBlocks() {
        ChunkPairing chunk = ContestCoordinates.redlightGenLoc();
        int chunkX = chunk.chunkX();
        int chunkZ = chunk.chunkZ();
        int blockX = chunkX * 16;
        int blockZ = chunkZ * 16;
        for (int xAdd = 5; xAdd <= 59; xAdd++) {
            for (int zAdd = 5; zAdd <= 59; zAdd++) {
                BlockPos pos = new BlockPos(blockX + xAdd, 24, blockZ + zAdd);
                Block block = this.field_70170_p.func_180495_p(pos).func_177230_c();
                if (block.equals(BlockInit.championDoorClosed)) {
                    this.field_70170_p.func_175656_a(pos, BlockInit.championDoorAjar.func_176223_P());
                } else if (block.equals(BlockInit.championDoorAjar)) {
                    this.field_70170_p.func_175656_a(pos, BlockInit.championDoorOpen.func_176223_P());
                } else if (block.equals(BlockInit.championDoorOpen)) {
                    this.field_70170_p.func_175656_a(pos, BlockInit.championDoorClosed.func_176223_P());
                }
            }
        }
    }
    private void setPillar(int pilnum, Block blockset) {
        int blockX;
        int blockZ;
        ChunkPairing chunk = ContestCoordinates.redlightGenLoc();
        int chunkX = chunk.chunkX();
        int chunkZ = chunk.chunkZ();
        int blockX2 = 14 + (chunkX * 16);
        int blockZ2 = 14 + (chunkZ * 16);
        if (pilnum < 4) {
            blockX = blockX2 + (34 * (pilnum % 2));
            blockZ = blockZ2 + (34 * Math.floorDiv(pilnum, 2));
        } else {
            blockX = blockX2 + 17;
            blockZ = blockZ2 + 17;
        }
        for (int x = 0; x < 2; x++) {
            for (int z = 0; z < 2; z++) {
                this.field_70170_p.func_175656_a(new BlockPos(blockX + x, 26, blockZ + z), blockset.func_176223_P());
            }
        }
    }
    private void lightPillars(int pilnum1, int pilnum2) {
        setPillar(pilnum1, BlockInit.championDungeonSelector);
        setPillar(pilnum2, BlockInit.championDungeonSelector);
    }
    public void darkenPillars() {
        for (int i = 0; i < 5; i++) {
            setPillar(i, BlockInit.championDungeonBox);
        }
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.redlightArenaAABB();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.redlightControllerPos();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        BlockPos teleTo = ContestCoordinates.redlightLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        int reward_count = Math.min(1 + (placement * Math.floorDiv(this.stage, 3)) + (placement == this.contenderCount - 1 ? this.contenderCount * 2 : 0), 50);
        player.func_191521_c(new ItemStack(ItemInit.zirconiaMalachite, reward_count));
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void endGame() {
        setLights(BlockInit.championSignalGreen);
        darkenPillars();
    }
}
