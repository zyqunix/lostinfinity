package xol.lostinfinity.projectile.entity;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityLuminousGuardianLaser.class */
public class EntityLuminousGuardianLaser extends Entity implements IMaxAttack {
    private EntityLivingBase owner;
    private static final DataParameter<Float> TARGET_X = EntityDataManager.func_187226_a(EntityLuminousGuardianLaser.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_Y = EntityDataManager.func_187226_a(EntityLuminousGuardianLaser.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_Z = EntityDataManager.func_187226_a(EntityLuminousGuardianLaser.class, DataSerializers.field_187193_c);
    private static final DataParameter<Integer> OWNER_ID = EntityDataManager.func_187226_a(EntityLuminousGuardianLaser.class, DataSerializers.field_187192_b);
    private static final int duration = 10;
    EntityPlayer playerOwner;
    private final double dist = 20.0d;
    public int counter;

    public EntityLuminousGuardianLaser(World worldIn) {
        super(worldIn);
        this.owner = null;
        this.playerOwner = null;
        this.dist = 20.0d;
        this.counter = 0;
    }

    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(TARGET_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_Z, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(OWNER_ID, 0);
    }

    public void setOwnerID(int id) {
        this.field_70180_af.func_187227_b(OWNER_ID, Integer.valueOf(id));
    }

    public int getOwnerID() {
        return ((Integer) this.field_70180_af.func_187225_a(OWNER_ID)).intValue();
    }

    public Vec3d getTargetPos() {
        double x = ((Float) this.field_70180_af.func_187225_a(TARGET_X)).floatValue();
        double y = ((Float) this.field_70180_af.func_187225_a(TARGET_Y)).floatValue();
        double z = ((Float) this.field_70180_af.func_187225_a(TARGET_Z)).floatValue();
        return new Vec3d(x, y, z);
    }

    public void setTargetPos(Vec3d pos) {
        float xpos = (float) pos.field_72450_a;
        float ypos = (float) pos.field_72448_b;
        float zpos = (float) pos.field_72449_c;
        this.field_70180_af.func_187227_b(TARGET_X, Float.valueOf(xpos));
        this.field_70180_af.func_187227_b(TARGET_Y, Float.valueOf(ypos));
        this.field_70180_af.func_187227_b(TARGET_Z, Float.valueOf(zpos));
    }

    public EntityLivingBase getOwner() {
        return this.owner;
    }

    public void setOwner(EntityLivingBase owner) {
        this.owner = owner;
    }

    public void func_70030_z() {
        super.func_70030_z();
        this.counter++;
        if (!this.field_70170_p.field_72995_K) {
            if (this.owner == null) {
                func_70106_y();
                return;
            }
            if (this.counter % 5 == 0 && this.counter < 6) {
                this.field_70170_p.func_184133_a((EntityPlayer) null, this.owner.func_180425_c(), SoundInit.MAGIC_WEAPON_22, SoundCategory.MASTER, 0.1f, 0.7f + (this.counter / 10.0f));
            }
            func_70634_a(this.owner.field_70165_t, this.owner.field_70163_u, this.owner.field_70161_v);
            if (this.counter == 6) {
                damageTarget();
                return;
            } else {
                if (this.counter >= 10) {
                    this.counter = 0;
                    func_70106_y();
                    return;
                }
                return;
            }
        }
        spawnParticles();
    }

    private void damageTarget() {
        Vec3d dir = getTargetPos().func_178788_d(this.owner.func_174791_d()).func_72432_b();
        ArrayList<EntityLivingBase> hitEntities = new ArrayList<>();
        double d = 0.0d;
        while (true) {
            double i = d;
            if (i <= 20.0d) {
                Vec3d pos = this.owner.func_174791_d().func_72441_c(dir.field_72450_a * i, dir.field_72448_b * i, dir.field_72449_c * i);
                AxisAlignedBB checkBox = new AxisAlignedBB(pos.field_72450_a - 1.8d, pos.field_72448_b - 1.8d, pos.field_72449_c - 1.8d, pos.field_72450_a + 1.8d, pos.field_72448_b + 1.8d, pos.field_72449_c + 1.8d);
                for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, checkBox)) {
                    if (!(entity instanceof EntityImmaterial) && !entity.func_110124_au().equals(this.owner.func_110124_au()) && !hitEntities.contains(entity) && (this.playerOwner == null || !this.playerOwner.func_110124_au().equals(entity.func_110124_au()))) {
                        IMaxAttack.dealTrueDamage(this.owner, entity, entity.func_110138_aP() * 0.35f);
                        hitEntities.add(entity);
                    }
                }
                d = i + 0.4d;
            } else {
                this.field_70170_p.func_184133_a((EntityPlayer) null, this.owner.func_180425_c(), SoundInit.MAGIC_WEAPON_22, SoundCategory.MASTER, 0.1f, 1.0f);
                return;
            }
        }
    }

    private void spawnParticles() {
        int id = getOwnerID();
        Entity owner = this.field_70170_p.func_73045_a(id);
        if (owner == null || this.counter >= 10) {
            return;
        }
        Vec3d dir = LMath.fastNormalize(getTargetPos().func_178788_d(owner.func_174791_d()));
        double d = 0.0d;
        while (true) {
            double i = d;
            if (i <= 20.0d) {
                if (this.field_70146_Z.nextInt(8) == 0) {
                    Vec3d pos = owner.func_174791_d().func_178787_e(dir.func_186678_a(i));
                    this.field_70170_p.func_175688_a(this.counter < 5 ? ParticleInit.GALAXY_YELLOW : ParticleInit.GALAXY_PURPLE, pos.field_72450_a, pos.field_72448_b + 0.4d, pos.field_72449_c, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, new int[0]);
                }
                d = i + 1.0d;
            } else {
                return;
            }
        }
    }

    public double getDist() {
        return 20.0d;
    }

    protected void func_70037_a(NBTTagCompound compound) {
    }

    protected void func_70014_b(NBTTagCompound compound) {
    }

    public void setPlayerOwner(EntityPlayer player) {
        this.playerOwner = player;
    }
}
