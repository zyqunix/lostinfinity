package xol.lostinfinity.mob.entity.contest.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityControllerTrampolineDodgeball extends EntityControllerBase {
    private HashMap<UUID, Integer> playerScores;
    private ArrayList<UUID> playersInRound;
    private ArrayList<BlockPos> spawnPositions;
    private static final int maxScore = 3;
    public void setSpawnPositions(List<BlockPos> spawnPositions) {
        this.spawnPositions.clear();
        this.spawnPositions.addAll(spawnPositions);
    }
    public void initGame() {
        this.playersInRound.clear();
        this.playerScores.clear();
        for (UUID pl_id : getPlayerList()) {
            this.playerScores.put(pl_id, 0);
            this.playersInRound.add(pl_id);
        }
    }
    public void eliminatePlayerFromRound(EntityPlayer player) {
        UUID pl_id = player.func_110124_au();
        this.playersInRound.remove(pl_id);
        soundContenders(SoundInit.GAME_BUZZER, 1.0f, 1.0f);
        BlockPos teleTo = ContestCoordinates.dodgeballWaitingRoomPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        if (this.playersInRound.size() == 1) {
            roundUp(this.playersInRound.get(0));
        }
    }
    private void roundUp(UUID uuid) {
        int score;
        this.playersInRound.clear();
        this.playersInRound.addAll(getPlayerList());
        int oldScore = this.playerScores.get(uuid).intValue();
        this.playerScores.replace(uuid, Integer.valueOf(oldScore + 1));
        if (oldScore >= 2) {
            ArrayList<UUID> removeList = new ArrayList<>();
            int iterations = getPlayerList().size();
            for (int i = 0; i < iterations; i++) {
                int minScore = 99;
                UUID minPlayer = null;
                for (UUID pl_id : getPlayerList()) {
                    if (!removeList.contains(pl_id) && this.playerScores.containsKey(pl_id) && (score = this.playerScores.get(pl_id).intValue()) < minScore) {
                        minScore = score;
                        minPlayer = pl_id;
                    }
                }
                if (minPlayer != null) {
                    removeList.add(minPlayer);
                }
            }
            Iterator<UUID> it = removeList.iterator();
            while (it.hasNext()) {
                removePlayerByUUID(it.next());
            }
            func_70106_y();
            return;
        }
        messageContenders(TextFmt.Green, "SCOREBOARD");
        int i2 = 0;
        for (UUID pl_id2 : getPlayerList()) {
            if (this.playerScores.containsKey(pl_id2)) {
                EntityPlayer playerEntity = this.field_70170_p.func_152378_a(pl_id2);
                if (playerEntity != null) {
                    messageContenders(TextFmt.Green, String.format("%s: %d", playerEntity.func_70005_c_(), this.playerScores.get(pl_id2)));
                }
                BlockPos teleTo = this.spawnPositions.get(i2);
                i2++;
                playerEntity.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
            }
        }
        soundContenders(SoundInit.MINIGAME_POWERUP, 1.0f, 1.0f);
        messageContenders(TextFmt.Green, "Next round, begin!");
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void playerStatusCheck() {
    }
    public EntityControllerTrampolineDodgeball(World worldIn) {
        super(worldIn);
        this.playerScores = new HashMap<>();
        this.playersInRound = new ArrayList<>();
        this.spawnPositions = new ArrayList<>();
        func_70105_a(5.0f, 12.0f);
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    public AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.dodgeballArenaAABB();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        EntityPlayer lastPlayer;
        ItemStack stack;
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa > 2 && this.contenderCount < 2) {
                func_70106_y();
            }
            List<EntityPlayer> inAABB = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB());
            if (this.field_70173_aa % 500 == 0) {
                int numSpecial = this.field_70170_p.field_73012_v.nextInt(maxScore) + 1;
                int xMin = ((int) getArenaAABB().field_72340_a) + 5;
                int xMax = ((int) getArenaAABB().field_72336_d) - 5;
                int y = ((int) getArenaAABB().field_72338_b) + 6;
                int zMin = ((int) getArenaAABB().field_72339_c) + 5;
                int zMax = ((int) getArenaAABB().field_72334_f) - 5;
                for (int i = 0; i < numSpecial; i++) {
                    int x = this.field_70170_p.field_73012_v.nextInt((xMax - xMin) + 1) + xMin;
                    int z = this.field_70170_p.field_73012_v.nextInt((zMax - zMin) + 1) + zMin;
                    if (this.field_70170_p.field_73012_v.nextBoolean()) {
                        stack = new ItemStack(ItemInit.championDodgeball, 1);
                        if (!stack.func_77942_o()) {
                            stack.func_77982_d(new NBTTagCompound());
                        }
                        stack.func_77978_p().func_74768_a("balltype_data", 1);
                    } else {
                        stack = new ItemStack(ItemInit.championDodgeball, 1);
                        if (!stack.func_77942_o()) {
                            stack.func_77982_d(new NBTTagCompound());
                        }
                        stack.func_77978_p().func_74768_a("balltype_data", 2);
                    }
                    EntityItem item = new EntityItem(this.field_70170_p, x, y, z, stack);
                    item.field_70159_w = 0.0d;
                    item.field_70181_x = 0.0d;
                    item.field_70179_y = 0.0d;
                    this.field_70170_p.func_72838_d(item);
                }
                soundContenders(SoundInit.GAME_DING, 1.0f, 1.0f);
                messageContenders(TextFmt.Green, "Some special dodgeballs have been added to the arena!");
            }
            for (EntityPlayer near_pl : inAABB) {
                near_pl.field_70143_R = -1.0f;
                if (near_pl.field_71075_bZ.field_75100_b) {
                    eliminatePlayerFromRound(near_pl);
                    removePlayer(near_pl);
                    messageContenders(TextFmt.Dark_Red, String.format("%s was removed for trying to fly away", near_pl.func_70005_c_()));
                }
                BlockPos playerPos = near_pl.func_180425_c().func_177977_b();
                IBlockState state = this.field_70170_p.func_180495_p(playerPos);
                if (state.equals(BlockInit.parkourPlatform.func_176203_a(0))) {
                    near_pl.func_70024_g(0.0d, 0.6d, 0.0d);
                    near_pl.field_70133_I = true;
                } else if (state.equals(BlockInit.championLauncher.func_176223_P())) {
                    int xDiff = 208 - playerPos.func_177958_n();
                    int zDiff = (-49) - playerPos.func_177952_p();
                    if (zDiff > 10) {
                        near_pl.func_70024_g(0.0d, 0.5d, 2.0d);
                    } else if (zDiff < -10) {
                        near_pl.func_70024_g(0.0d, 0.5d, -2.0d);
                    } else if (xDiff > 10) {
                        near_pl.func_70024_g(2.0d, 0.5d, 0.0d);
                    } else if (xDiff < -10) {
                        near_pl.func_70024_g(-2.0d, 0.5d, 0.0d);
                    }
                    near_pl.field_70133_I = true;
                }
            }
            if (getPlayerList().size() == 1 && (lastPlayer = this.field_70170_p.func_152378_a(getPlayerList().get(0))) != null) {
                rewardPlayer(lastPlayer, this.contenderCount);
                func_70106_y();
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.dodgeballControllerPos();
    }
    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
            if (player.field_71071_by.func_70301_a(i).func_77973_b() == ItemInit.championDodgeball) {
                player.field_71071_by.func_70299_a(i, ItemStack.field_190927_a);
            }
        }
        BlockPos teleTo = ContestCoordinates.dodgeballLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        int reward_count = Math.min(10 + (20 * placement) + (placement == this.contenderCount - 1 ? this.contenderCount * 10 : 0), 50);
        player.func_191521_c(new ItemStack(ItemInit.zirconiaAubergine, reward_count));
    }
}
