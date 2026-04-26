package xol.lostinfinity.mob.entity.contest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockInkable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/controller/EntityControllerInkBattle.class */
public class EntityControllerInkBattle extends EntityControllerBase {
    private int time;
    private static final int duration = 5000;
    private List<BlockPos> spawnPositions;
    private HashMap<UUID, Integer> inks;

    public void initInkBattle(List<BlockPos> spawnPositions) {
        this.spawnPositions.clear();
        this.spawnPositions.addAll(spawnPositions);
        this.time = 0;
        AxisAlignedBB arena = getArenaAABB();
        for (BlockPos arenaPos : BlockPos.func_191532_a((int) arena.field_72340_a, (int) arena.field_72338_b, (int) arena.field_72339_c, (int) arena.field_72336_d, (int) arena.field_72337_e, (int) arena.field_72334_f)) {
            if (!this.field_70170_p.func_175623_d(arenaPos)) {
                IBlockState state = this.field_70170_p.func_180495_p(arenaPos);
                Block block = state.func_177230_c();
                if (block instanceof BlockInkable) {
                    this.field_70170_p.func_175656_a(arenaPos, block.func_176203_a(0));
                }
            }
        }
    }

    public void inkBlock(UUID id, BlockPos pos) {
        if (this.inks != null && this.inks.containsKey(id)) {
            int ink = this.inks.get(id).intValue();
            IBlockState state = this.field_70170_p.func_180495_p(pos);
            Block block = state.func_177230_c();
            if (block instanceof BlockInkable) {
                this.field_70170_p.func_175656_a(pos, block.func_176203_a(ink));
            }
        }
    }

    public EntityControllerInkBattle(World worldIn) {
        super(worldIn);
        this.time = 0;
        this.spawnPositions = new ArrayList();
        this.inks = new HashMap<>();
        func_70105_a(5.0f, 12.0f);
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    public AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.inkBattleArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa > 2 && this.inks == null) {
                func_70106_y();
            }
            AxisAlignedBB arena = getArenaAABB();
            if (this.field_70173_aa % 400 == 0) {
                for (BlockPos pos : getPowerupPostions()) {
                    if (this.field_70146_Z.nextInt(4) == 0) {
                        if (this.field_70146_Z.nextBoolean()) {
                            EntityItem item = new EntityItem(this.field_70170_p, pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p(), new ItemStack(ItemInit.inkBomb));
                            item.field_70159_w = 0.0d;
                            item.field_70181_x = 0.0d;
                            item.field_70179_y = 0.0d;
                            this.field_70170_p.func_72838_d(item);
                        } else {
                            EntityItem item2 = new EntityItem(this.field_70170_p, pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p(), new ItemStack(ItemInit.inkShotgun));
                            item2.field_70159_w = 0.0d;
                            item2.field_70181_x = 0.0d;
                            item2.field_70179_y = 0.0d;
                            this.field_70170_p.func_72838_d(item2);
                        }
                    }
                }
                messageContenders(TextFmt.Green, "Powerful Items Have Spawned Around The Arena!");
            }
            this.time++;
            if (this.time >= duration) {
                int numToRemove = getPlayerList().size() - 1;
                HashMap<UUID, Integer> playerScores = new HashMap<>();
                ArrayList<BlockPos> inkables = new ArrayList<>();
                for (BlockPos arenaPos : BlockPos.func_191532_a((int) arena.field_72340_a, (int) arena.field_72338_b, (int) arena.field_72339_c, (int) arena.field_72336_d, (int) arena.field_72337_e, (int) arena.field_72334_f)) {
                    if (!this.field_70170_p.func_175623_d(arenaPos)) {
                        Block block = this.field_70170_p.func_180495_p(arenaPos).func_177230_c();
                        if (block instanceof BlockInkable) {
                            inkables.add(arenaPos);
                        }
                    }
                }
                for (UUID pl_id : getPlayerList()) {
                    int score = 0;
                    if (this.inks != null && this.inks.containsKey(pl_id)) {
                        int ink = this.inks.get(pl_id).intValue();
                        for (BlockPos inkable : inkables) {
                            IBlockState state = this.field_70170_p.func_180495_p(inkable);
                            Block block2 = state.func_177230_c();
                            int meta = block2.func_176201_c(state);
                            if (meta == ink) {
                                score++;
                            }
                        }
                    }
                    playerScores.put(pl_id, Integer.valueOf(score));
                }
                for (int i = 0; i < numToRemove; i++) {
                    int min = 1000;
                    UUID remove = null;
                    for (UUID pl_id2 : getPlayerList()) {
                        if (playerScores.get(pl_id2).intValue() < min) {
                            min = playerScores.get(pl_id2).intValue();
                            remove = pl_id2;
                        }
                    }
                    if (remove != null) {
                        removePlayerByUUID(remove);
                    }
                }
            }
        }
    }

    private ArrayList<BlockPos> getPowerupPostions() {
        return ContestCoordinates.inkBattlePowerUpPositions();
    }

    public void inkPlayer(EntityPlayer target) {
        if (this.spawnPositions != null && !this.spawnPositions.isEmpty()) {
            int randSpawn = this.field_70146_Z.nextInt(this.spawnPositions.size());
            BlockPos spawn = this.spawnPositions.get(randSpawn);
            target.func_70634_a(spawn.func_177958_n(), spawn.func_177956_o(), spawn.func_177952_p());
        }
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.inkBattleControllerPos();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        BlockPos teleTo = ContestCoordinates.inkBattleLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
            if (player.field_71071_by.func_70301_a(i).func_77973_b() == ItemInit.inkGun || player.field_71071_by.func_70301_a(i).func_77973_b() == ItemInit.inkShotgun || player.field_71071_by.func_70301_a(i).func_77973_b() == ItemInit.inkBomb) {
                player.field_71071_by.func_70299_a(i, ItemStack.field_190927_a);
            }
        }
        int reward_count = Math.min(10 + (20 * placement) + (placement == this.contenderCount - 1 ? this.contenderCount * 10 : 0), 50);
        player.func_191521_c(new ItemStack(ItemInit.zirconiaIvory, reward_count));
    }

    public void setInk(UUID pl_id, int i) {
        if (this.inks != null) {
            this.inks.put(pl_id, Integer.valueOf(i));
        }
    }
}
