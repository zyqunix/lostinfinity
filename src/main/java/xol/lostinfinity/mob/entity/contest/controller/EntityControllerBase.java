package xol.lostinfinity.mob.entity.contest.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
public class EntityControllerBase extends EntityImmaterial {
    protected int stage;
    private List<UUID> player_list;
    protected int contenderCount;
    protected List<UUID> touched_list;
    protected boolean touchInProgress;
    public EntityControllerBase(World worldIn) {
        super(worldIn);
        this.stage = 0;
        this.player_list = new ArrayList();
        this.contenderCount = 0;
        this.touched_list = new ArrayList();
        this.touchInProgress = false;
    }
    public void setPlayerCount(int count) {
        this.contenderCount = count;
    }
    public void addPlayerToList(EntityPlayer player) {
        this.player_list.add(player.func_110124_au());
    }
    public List<UUID> getPlayerList() {
        return this.player_list;
    }
    public void removePlayer(EntityPlayer player) {
        if (this.player_list.contains(player.func_110124_au())) {
            eliminatePlayer(player.func_110124_au());
            this.player_list.remove(player.func_110124_au());
            playerStatusCheck();
        }
    }
    public void removePlayerByUUID(UUID uuid) {
        if (this.player_list.contains(uuid)) {
            eliminatePlayer(uuid);
            this.player_list.remove(uuid);
            playerStatusCheck();
        }
    }
    public void removeMultiplePlayers(List<UUID> getting_removed) {
        for (UUID rem_id : getting_removed) {
            if (this.player_list.contains(rem_id)) {
                eliminatePlayer(rem_id);
            }
        }
        this.player_list.removeAll(getting_removed);
        playerStatusCheck();
    }
    protected void eliminatePlayer(UUID playerID) {
        EntityPlayer player = this.field_70170_p.func_152378_a(playerID);
        if (player != null) {
            messageContenders(TextFmt.Green, player.func_70005_c_() + " Eliminated!");
            rewardPlayer(player, this.contenderCount - getPlayerList().size());
        }
    }
    protected void removeFailedCheckIn() {
        List<UUID> eliminated_players = new ArrayList<>();
        for (UUID pl_id : getPlayerList()) {
            if (!this.touched_list.contains(pl_id)) {
                eliminated_players.add(pl_id);
            }
        }
        removeMultiplePlayers(eliminated_players);
        this.touchInProgress = false;
        this.touched_list.clear();
    }
    protected void playerStatusCheck() {
        boolean end_flag = false;
        List<UUID> accounted_for = new ArrayList<>();
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            accounted_for.add(near_pl.func_110124_au());
        }
        accounted_for.retainAll(this.player_list);
        List<UUID> not_detected = new ArrayList<>();
        not_detected.addAll(this.player_list);
        not_detected.removeAll(accounted_for);
        if (!not_detected.isEmpty()) {
            removeMultiplePlayers(not_detected);
        }
        if (this.player_list.isEmpty()) {
            end_flag = true;
        }
        if (!end_flag && this.player_list.size() == 1) {
            EntityPlayer winner = this.field_70170_p.func_152378_a(this.player_list.get(0));
            if (winner != null) {
                rewardPlayer(winner, this.contenderCount - 1);
            }
            end_flag = true;
        }
        if (end_flag) {
            func_70106_y();
            endGame();
        }
    }
    public void registerTouch(EntityPlayer player) {
        if (this.touchInProgress) {
            UUID player_uuid = player.func_110124_au();
            if (!this.touched_list.contains(player_uuid)) {
                this.touched_list.add(player_uuid);
                player.func_145747_a(new TextComponentString(TextFmt.Gold + "You have checked in!"));
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public boolean func_70104_M() {
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.grandmasterOutpost) {
                BlockPos snapPos = getSnapPos();
                if (this.field_70173_aa % 5 == 0) {
                    func_70634_a(snapPos.func_177958_n(), snapPos.func_177956_o(), snapPos.func_177952_p());
                }
            }
            if (this.player_list.isEmpty() && this.field_70173_aa > 5) {
                func_70106_y();
            } else if (this.field_70173_aa % 20 == 0) {
                playerStatusCheck();
            }
        }
    }
    protected AxisAlignedBB getArenaAABB() {
        return null;
    }
    protected BlockPos getSnapPos() {
        return null;
    }
    protected void messageContenders(TextFmt fmt, String message) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            contender.func_145747_a(new TextComponentString(fmt + "Grandmaster: " + message));
        }
    }
    protected void messageContendersWithSound(TextFmt fmt, String message, SoundEvent sound) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            contender.func_145747_a(new TextComponentString(fmt + "Grandmaster: " + message));
            contender.field_70170_p.func_184133_a((EntityPlayer) null, contender.func_180425_c(), sound, SoundCategory.MASTER, 1.0f, 1.0f);
        }
    }
    protected void soundContenders(SoundEvent sound, float vol, float pitch) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            contender.field_70170_p.func_184133_a((EntityPlayer) null, contender.func_180425_c(), sound, SoundCategory.MASTER, vol, pitch);
        }
    }
    protected void rewardPlayer(EntityPlayer player, int placement) {
    }
    protected void endGame() {
    }
}
