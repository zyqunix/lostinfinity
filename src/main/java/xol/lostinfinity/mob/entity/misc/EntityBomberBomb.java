package xol.lostinfinity.mob.entity.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerBombers;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityBomberBomb.class */
public class EntityBomberBomb extends EntityImmaterial implements IMaxAttack {
    private UUID creator_UUID;
    private int deploymentTime;
    private int currentSize;
    private int maxSize;
    private boolean[] continueExploding;

    public EntityBomberBomb(World worldIn) {
        super(worldIn);
        this.deploymentTime = 60;
        this.currentSize = 0;
        this.maxSize = 1;
        this.continueExploding = new boolean[]{true, true, true, true};
        func_70105_a(0.3f, 0.3f);
    }

    public void setCreator(UUID uuid) {
        this.creator_UUID = uuid;
    }

    public void setDeploymentTime(int newDeploy) {
        this.deploymentTime = newDeploy;
    }

    public void setBombSize(int newSize) {
        this.maxSize = newSize;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70159_w = 0.0d;
        this.field_70179_y = 0.0d;
        this.field_70181_x = -1.0d;
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa > 1) {
            if (this.creator_UUID == null) {
                func_70106_y();
                return;
            }
            if (this.deploymentTime <= 0 && this.deploymentTime % 2 == 0) {
                explosionEffect();
            }
            this.deploymentTime--;
        }
    }

    public void explosionEffect() {
        BlockPos pos = func_180425_c();
        if (this.currentSize == 0) {
            explosionFX(pos);
        } else {
            if (this.continueExploding[0]) {
                BlockPos reference_pos = pos.func_177982_a(-this.currentSize, 0, 0);
                if (!tileExplosion(reference_pos)) {
                    this.continueExploding[0] = false;
                }
            }
            if (this.continueExploding[1]) {
                BlockPos reference_pos2 = pos.func_177982_a(this.currentSize, 0, 0);
                if (!tileExplosion(reference_pos2)) {
                    this.continueExploding[1] = false;
                }
            }
            if (this.continueExploding[2]) {
                BlockPos reference_pos3 = pos.func_177982_a(0, 0, -this.currentSize);
                if (!tileExplosion(reference_pos3)) {
                    this.continueExploding[2] = false;
                }
            }
            if (this.continueExploding[3]) {
                BlockPos reference_pos4 = pos.func_177982_a(0, 0, this.currentSize);
                if (!tileExplosion(reference_pos4)) {
                    this.continueExploding[3] = false;
                }
            }
        }
        if (this.currentSize == this.maxSize) {
            func_70106_y();
        } else {
            this.currentSize++;
        }
    }

    private boolean tileExplosion(BlockPos pos) {
        if (this.field_70170_p.func_175623_d(pos)) {
            explosionFX(pos);
            return true;
        }
        if (this.field_70170_p.func_180495_p(pos).func_177230_c() == BlockInit.bomberWallSoft) {
            this.field_70170_p.func_175698_g(pos);
            this.field_70170_p.func_175698_g(pos.func_177984_a());
            explosionFX(pos);
            return false;
        }
        return false;
    }

    private void explosionFX(BlockPos pos) {
        playerCheck(pos);
        CustomParticleConfig config1 = new CustomParticleConfig();
        config1.createInstance().setParticle(ParticleInit.BOMBER_EXPLOSION).setIgnoreRange(true);
        IParticleSpawner.spawnParticle(this.field_70170_p, config1, ((double) pos.func_177958_n()) + 0.5d, ((double) pos.func_177956_o()) + 0.3d, ((double) pos.func_177952_p()) + 0.5d);
        func_184185_a(SoundInit.GENERIC_WEAPON_5, 2.0f, 1.0f);
    }

    private void playerCheck(BlockPos pos) {
        if (this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.grandmasterOutpost) {
            AxisAlignedBB arena_aabb = ContestCoordinates.bombersArenaAABB();
            if (pos.func_177958_n() >= arena_aabb.field_72340_a && pos.func_177958_n() <= arena_aabb.field_72336_d && pos.func_177952_p() >= arena_aabb.field_72339_c && pos.func_177952_p() <= arena_aabb.field_72334_f) {
                AxisAlignedBB aabb = new AxisAlignedBB(pos);
                List<EntityPlayer> caughtInExplosion = new ArrayList<>();
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb)) {
                    caughtInExplosion.add(near_pl);
                }
                for (EntityControllerBombers search_cont : this.field_70170_p.func_72872_a(EntityControllerBombers.class, ContestCoordinates.bombersControllerAABB())) {
                    for (EntityPlayer player : caughtInExplosion) {
                        if (!player.func_70644_a(PotionInit.PROTECTED)) {
                            search_cont.removePlayer(player);
                        }
                    }
                }
                return;
            }
            return;
        }
        AxisAlignedBB aabb2 = new AxisAlignedBB(pos);
        new ArrayList();
        for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb2)) {
            near_pl2.func_70606_j(0.0f);
        }
    }
}
