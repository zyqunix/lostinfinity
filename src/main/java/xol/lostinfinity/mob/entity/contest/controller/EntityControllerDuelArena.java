package xol.lostinfinity.mob.entity.contest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.projectile.entity.EntityFountainPellet;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/controller/EntityControllerDuelArena.class */
public class EntityControllerDuelArena extends EntityControllerBase {
    private ArrayList<BlockPos> heatTraps;
    private ArrayList<BlockPos> fountainTraps;
    private HashMap<UUID, Integer> playerHits;
    private static final int maxHits = 30;

    public void initDuel() {
        this.playerHits = new HashMap<>();
        this.heatTraps = new ArrayList<>();
        this.fountainTraps = new ArrayList<>();
        for (UUID pl_id : getPlayerList()) {
            this.playerHits.put(pl_id, 0);
        }
        AxisAlignedBB arena = getArenaAABB();
        for (int i = (int) arena.field_72340_a; i <= ((int) arena.field_72336_d); i++) {
            for (int k = (int) arena.field_72339_c; k <= ((int) arena.field_72334_f); k++) {
                BlockPos check = new BlockPos(i, arena.field_72338_b, k);
                Block block = this.field_70170_p.func_180495_p(check).func_177230_c();
                if (block.equals(BlockInit.championHeatTrap)) {
                    this.heatTraps.add(check);
                } else if (block.equals(BlockInit.championFountainTrap)) {
                    this.fountainTraps.add(check);
                }
            }
        }
    }

    public void hitPlayer(EntityPlayer player, int numHits) {
        UUID pl_id = player.func_110124_au();
        int hits = 0;
        if (this.playerHits == null) {
            return;
        }
        if (this.playerHits.containsKey(pl_id)) {
            hits = this.playerHits.get(pl_id).intValue() + numHits;
            if (hits > 30) {
                hits = 30;
            }
            this.playerHits.replace(pl_id, Integer.valueOf(hits));
        }
        messageContenders(TextFmt.Red, String.format("%s is at %d hits", player.func_70005_c_(), Integer.valueOf(hits)));
    }

    public EntityControllerDuelArena(World worldIn) {
        super(worldIn);
        this.heatTraps = null;
        this.fountainTraps = null;
        this.playerHits = null;
        func_70105_a(5.0f, 12.0f);
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    public AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.duelArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa == 1200) {
                messageContenders(TextFmt.Red, "A more powerful sword awaits at your spawn point!");
                BlockPos spawn = new BlockPos(99, 33, 256);
                EntityItem sword = new EntityItem(this.field_70170_p, spawn.func_177958_n(), ((double) spawn.func_177956_o()) + 0.4d, spawn.func_177952_p(), new ItemStack(ItemInit.duelingSwordSharp));
                sword.field_70159_w = 0.0d;
                sword.field_70181_x = 0.0d;
                sword.field_70179_y = 0.0d;
                this.field_70170_p.func_72838_d(sword);
            }
            if (this.field_70173_aa == 2400) {
                messageContenders(TextFmt.Dark_Red, "THE MOST POWERFUL DUELING WEAPONS HAVE ARRIVED AT YOUR SPAWNS!");
                BlockPos spawn2 = new BlockPos(155, 33, 256);
                EntityItem sword2 = new EntityItem(this.field_70170_p, spawn2.func_177958_n(), ((double) spawn2.func_177956_o()) + 0.4d, spawn2.func_177952_p(), new ItemStack(ItemInit.duelingSwordRazorEdged));
                sword2.field_70159_w = 0.0d;
                sword2.field_70181_x = 0.0d;
                sword2.field_70179_y = 0.0d;
                this.field_70170_p.func_72838_d(sword2);
            }
            if ((this.field_70173_aa > 2 && this.playerHits == null) || this.contenderCount < 2) {
                func_70106_y();
            }
            List<EntityPlayer> inAABB = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB());
            List<UUID> toRemove = new ArrayList<>();
            for (UUID pl_id : getPlayerList()) {
                if (this.playerHits.containsKey(pl_id) && this.playerHits.get(pl_id).intValue() >= 30) {
                    toRemove.add(pl_id);
                }
            }
            removeMultiplePlayers(toRemove);
            for (EntityPlayer near_pl : inAABB) {
                near_pl.field_70143_R = -1.0f;
                near_pl.func_70691_i(2.0f);
                if (near_pl.field_71075_bZ.field_75100_b) {
                    removePlayer(near_pl);
                }
                BlockPos playerPos = near_pl.func_180425_c().func_177977_b();
                IBlockState state = this.field_70170_p.func_180495_p(playerPos);
                Block block = state.func_177230_c();
                if (block.equals(BlockInit.championLauncher)) {
                    if (playerPos.func_177958_n() < (getArenaAABB().field_72336_d + getArenaAABB().field_72340_a) / 2.0d) {
                        near_pl.func_70024_g(200.0d, 1.0d, 0.0d);
                    } else {
                        near_pl.func_70024_g(-200.0d, 1.0d, 0.0d);
                    }
                    near_pl.field_70133_I = true;
                } else if (block.equals(BlockInit.championHeatTrap) && this.field_70173_aa % 20 == 0 && state.equals(BlockInit.championHeatTrap.func_176203_a(1))) {
                    hitPlayer(near_pl, 2);
                }
            }
            if (this.field_70173_aa % 200 >= 100 && this.field_70173_aa % 3 == 0) {
                for (BlockPos pos : this.fountainTraps) {
                    EntityFountainPellet shot = new EntityFountainPellet(this.field_70170_p);
                    shot.func_70107_b(((double) pos.func_177958_n()) + 0.5d, pos.func_177956_o(), ((double) pos.func_177952_p()) + 0.5d);
                    shot.func_70186_c((-0.15d) + (this.field_70170_p.field_73012_v.nextDouble() * 0.3d), this.field_70170_p.field_73012_v.nextDouble() + 2.0d, (-0.15d) + (this.field_70170_p.field_73012_v.nextDouble() * 0.3d), 1.5f, 2.0f);
                    this.field_70170_p.func_72838_d(shot);
                }
            }
            if (this.field_70173_aa % 200 < 100 && this.field_70173_aa % 20 == 0) {
                for (BlockPos pos2 : this.heatTraps) {
                    if (this.field_70170_p.field_73012_v.nextBoolean()) {
                        this.field_70170_p.func_175656_a(pos2, BlockInit.championHeatTrap.func_176203_a(1));
                    } else {
                        this.field_70170_p.func_175656_a(pos2, BlockInit.championHeatTrap.func_176203_a(0));
                    }
                }
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.duelControllerPos();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        this.playerHits = null;
        BlockPos teleTo = ContestCoordinates.duelLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
            if (player.field_71071_by.func_70301_a(i).func_77973_b() == ItemInit.duelingSwordDull || player.field_71071_by.func_70301_a(i).func_77973_b() == ItemInit.duelingSwordSharp || player.field_71071_by.func_70301_a(i).func_77973_b() == ItemInit.duelingSwordRazorEdged) {
                player.field_71071_by.func_70299_a(i, ItemStack.field_190927_a);
            }
        }
        int reward_count = placement == this.contenderCount - 1 ? 1 : 0;
        if (reward_count > 0) {
            ItemStack stack1 = null;
            ItemStack stack2 = null;
            Iterator it = this.field_70170_p.func_72872_a(EntityItemFrame.class, new AxisAlignedBB(ContestCoordinates.duelFrame1Pos())).iterator();
            if (it.hasNext()) {
                EntityItemFrame frames = (EntityItemFrame) it.next();
                stack1 = frames.func_82335_i();
            }
            Iterator it2 = this.field_70170_p.func_72872_a(EntityItemFrame.class, new AxisAlignedBB(ContestCoordinates.duelFrame2Pos())).iterator();
            if (it2.hasNext()) {
                EntityItemFrame frames2 = (EntityItemFrame) it2.next();
                stack2 = frames2.func_82335_i();
            }
            if (stack1 != null && stack2 != null) {
                player.func_191521_c(stack1.func_77946_l());
                player.func_191521_c(stack2.func_77946_l());
                Iterator it3 = this.field_70170_p.func_72872_a(EntityItemFrame.class, new AxisAlignedBB(ContestCoordinates.duelFrame1Pos())).iterator();
                if (it3.hasNext()) {
                    EntityItemFrame frames3 = (EntityItemFrame) it3.next();
                    frames3.func_82334_a(ItemStack.field_190927_a);
                }
                Iterator it4 = this.field_70170_p.func_72872_a(EntityItemFrame.class, new AxisAlignedBB(ContestCoordinates.duelFrame2Pos())).iterator();
                if (it4.hasNext()) {
                    EntityItemFrame frames4 = (EntityItemFrame) it4.next();
                    frames4.func_82334_a(ItemStack.field_190927_a);
                }
            }
        }
    }
}
