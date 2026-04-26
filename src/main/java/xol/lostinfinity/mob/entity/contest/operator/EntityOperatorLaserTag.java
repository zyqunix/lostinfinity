package xol.lostinfinity.mob.entity.contest.operator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.data.LaserNode;
import xol.lostinfinity.dimension.data.LaserTagMap;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerLaserTag;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/operator/EntityOperatorLaserTag.class */
public class EntityOperatorLaserTag extends EntityOperatorBase {
    private List<BlockPos> spawnPositions;

    public EntityOperatorLaserTag(World worldIn) {
        super(worldIn);
        this.spawnPositions = new ArrayList();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void startGame() {
        for (EntityControllerLaserTag entity : this.field_70170_p.func_72872_a(EntityControllerLaserTag.class, getArenaAABB())) {
            entity.func_70106_y();
        }
        EntityControllerLaserTag gamehologram = new EntityControllerLaserTag(this.field_70170_p);
        BlockPos pos = ContestCoordinates.laserTagControllerPos();
        gamehologram.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        gamehologram.setPlayerCount(this.contenders.size());
        int curSpawn = 0;
        ArrayList<UUID> toRemove = new ArrayList<>();
        for (UUID pl_id : this.contenders) {
            if (this.contenders.indexOf(pl_id) > 10) {
                toRemove.add(pl_id);
            }
        }
        for (UUID pl_id2 : toRemove) {
            this.contenders.remove(pl_id2);
            this.field_70170_p.func_152378_a(pl_id2).func_145747_a(new TextComponentString(TextFmt.Aqua + "Too many players, please join the next game"));
        }
        Iterator<UUID> it = this.contenders.iterator();
        while (it.hasNext()) {
            EntityPlayer pl = this.field_70170_p.func_152378_a(it.next());
            if (pl != null) {
                gamehologram.addPlayerToList(pl);
                pl.field_71071_by.func_70436_m();
                pl.func_191521_c(new ItemStack(ItemInit.laserZapper, 1));
                BlockPos spawnPos = this.spawnPositions.get(curSpawn);
                pl.func_70634_a(spawnPos.func_177958_n(), spawnPos.func_177956_o(), spawnPos.func_177952_p());
                pl.func_145747_a(new TextComponentString(TextFmt.Aqua + "Grandmaster: Alright contenders, zap the other players to get the highest score!"));
                curSpawn++;
            }
        }
        this.field_70170_p.func_72838_d(gamehologram);
        gamehologram.initLaserTag(this.spawnPositions);
        this.spawnPositions.clear();
        this.contenders.clear();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected void generateArena() {
        IBlockState wallState;
        IBlockState wallState2;
        BlockPos pos = new BlockPos(getArenaAABB().field_72340_a, getArenaAABB().field_72338_b + 1.0d, getArenaAABB().field_72339_c);
        LaserTagMap map = new LaserTagMap(36, 36);
        LaserNode[][] floor1 = map.getFloor1();
        LaserNode[][] floor2 = map.getFloor2();
        for (int i = 0; i < floor1.length; i++) {
            for (int j = 0; j < floor1[0].length; j++) {
                if ((i < floor1.length - 4 || j < floor1[0].length - 4) && (i >= 4 || j >= 4)) {
                    this.field_70170_p.func_175698_g(pos.func_177982_a(i, 0, j));
                    this.field_70170_p.func_175698_g(pos.func_177982_a(i, 1, j));
                    this.field_70170_p.func_175698_g(pos.func_177982_a(i, 2, j));
                    this.field_70170_p.func_175698_g(pos.func_177982_a(i, 3, j));
                    this.field_70170_p.func_175656_a(pos.func_177982_a(i, 3, j), BlockInit.championDungeonPlating.func_176223_P());
                    if (floor1[i][j].visited()) {
                        BlockInit.bomberWallHard.func_176223_P();
                        if (i < floor1.length / 2) {
                            if (j < floor1[0].length / 2) {
                                wallState2 = BlockInit.neonBricksBlue.func_176223_P();
                            } else {
                                wallState2 = BlockInit.neonBricksYellow.func_176223_P();
                            }
                        } else if (j < floor1[0].length / 2) {
                            wallState2 = BlockInit.neonBricksRed.func_176223_P();
                        } else {
                            wallState2 = BlockInit.neonBricksGreen.func_176223_P();
                        }
                        this.field_70170_p.func_175656_a(pos.func_177982_a(i, 0, j), BlockInit.championWall.func_176223_P());
                        if (this.field_70170_p.field_73012_v.nextInt(10) == 0) {
                            this.field_70170_p.func_175656_a(pos.func_177982_a(i, 1, j), BlockInit.championGlassDark.func_176223_P());
                        } else {
                            this.field_70170_p.func_175656_a(pos.func_177982_a(i, 1, j), wallState2);
                        }
                        this.field_70170_p.func_175656_a(pos.func_177982_a(i, 2, j), wallState2);
                    } else {
                        this.spawnPositions.add(pos.func_177982_a(i, 1, j));
                        if (this.field_70170_p.field_73012_v.nextInt(40) == 0) {
                            this.field_70170_p.func_175656_a(pos.func_177982_a(i, 3, j), Blocks.field_150415_aT.func_176223_P());
                        }
                    }
                }
            }
        }
        Collections.shuffle(this.spawnPositions);
        for (int i2 = 0; i2 < floor2.length; i2++) {
            for (int j2 = 0; j2 < floor2[0].length; j2++) {
                if ((i2 < floor2.length - 4 || j2 < floor2[0].length - 4) && (i2 >= 4 || j2 >= 4)) {
                    this.field_70170_p.func_175698_g(pos.func_177982_a(i2, 4, j2));
                    this.field_70170_p.func_175698_g(pos.func_177982_a(i2, 5, j2));
                    this.field_70170_p.func_175698_g(pos.func_177982_a(i2, 6, j2));
                    if (floor2[i2][j2].visited()) {
                        BlockInit.championWall.func_176223_P();
                        if (i2 < floor1.length / 2) {
                            if (j2 < floor1[0].length / 2) {
                                wallState = BlockInit.neonBricksAqua.func_176223_P();
                            } else {
                                wallState = BlockInit.neonBricksOrange.func_176223_P();
                            }
                        } else if (j2 < floor1[0].length / 2) {
                            wallState = BlockInit.neonBricksPurple.func_176223_P();
                        } else {
                            wallState = BlockInit.neonBricksPink.func_176223_P();
                        }
                        this.field_70170_p.func_175656_a(pos.func_177982_a(i2, 4, j2), BlockInit.championWall.func_176223_P());
                        if (this.field_70170_p.field_73012_v.nextInt(15) == 0) {
                            this.field_70170_p.func_175656_a(pos.func_177982_a(i2, 5, j2), BlockInit.championGlassDark.func_176223_P());
                        } else {
                            this.field_70170_p.func_175656_a(pos.func_177982_a(i2, 5, j2), wallState);
                        }
                        this.field_70170_p.func_175656_a(pos.func_177982_a(i2, 6, j2), wallState);
                    }
                }
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.laserTagArenaAABB();
    }

    @Override // xol.lostinfinity.mob.entity.contest.operator.EntityOperatorBase
    protected AxisAlignedBB getLobbyAABB() {
        return ContestCoordinates.laserTagLobbyAABB();
    }
}
