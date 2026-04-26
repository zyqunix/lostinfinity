package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityDebtCollectorEffect.class */
public class EntityDebtCollectorEffect extends Entity implements IMaxAttack {
    private boolean playedExecuteSound;
    private boolean didKill;
    private static final DataParameter<Float> TARGET_X = EntityDataManager.func_187226_a(EntityDebtCollectorEffect.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_Y = EntityDataManager.func_187226_a(EntityDebtCollectorEffect.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_Z = EntityDataManager.func_187226_a(EntityDebtCollectorEffect.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_LOOK_X = EntityDataManager.func_187226_a(EntityDebtCollectorEffect.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_LOOK_Y = EntityDataManager.func_187226_a(EntityDebtCollectorEffect.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_LOOK_Z = EntityDataManager.func_187226_a(EntityDebtCollectorEffect.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_HEIGHT = EntityDataManager.func_187226_a(EntityDebtCollectorEffect.class, DataSerializers.field_187193_c);
    private EntityPlayer creator;
    private EntityLivingBase target;
    private float rotation;
    private float alpha;
    private float rotationIncreaser;

    public EntityDebtCollectorEffect(World worldIn) {
        super(worldIn);
        this.playedExecuteSound = false;
        this.didKill = false;
        this.creator = null;
        this.target = null;
        this.rotation = 0.0f;
        this.alpha = 1.0f;
        this.rotationIncreaser = 0.025f;
    }

    public Vec3d getTargetPos() {
        double x = ((Float) this.field_70180_af.func_187225_a(TARGET_X)).floatValue();
        double y = ((Float) this.field_70180_af.func_187225_a(TARGET_Y)).floatValue();
        double z = ((Float) this.field_70180_af.func_187225_a(TARGET_Z)).floatValue();
        return new Vec3d(x, y, z);
    }

    public Vec3d getTargetVec() {
        double x = ((Float) this.field_70180_af.func_187225_a(TARGET_LOOK_X)).floatValue();
        double y = ((Float) this.field_70180_af.func_187225_a(TARGET_LOOK_Y)).floatValue();
        double z = ((Float) this.field_70180_af.func_187225_a(TARGET_LOOK_Z)).floatValue();
        return new Vec3d(x, y, z);
    }

    public void setTargetVec(Vec3d vec) {
        this.field_70180_af.func_187227_b(TARGET_LOOK_X, Float.valueOf((float) vec.field_72450_a));
        this.field_70180_af.func_187227_b(TARGET_LOOK_Y, Float.valueOf((float) vec.field_72448_b));
        this.field_70180_af.func_187227_b(TARGET_LOOK_Z, Float.valueOf((float) vec.field_72449_c));
    }

    public void setTargetPos(Vec3d vec) {
        this.field_70180_af.func_187227_b(TARGET_X, Float.valueOf((float) vec.field_72450_a));
        this.field_70180_af.func_187227_b(TARGET_Y, Float.valueOf((float) vec.field_72448_b));
        this.field_70180_af.func_187227_b(TARGET_Z, Float.valueOf((float) vec.field_72449_c));
    }

    public void setTargetHeight(double height) {
        this.field_70180_af.func_187227_b(TARGET_HEIGHT, Float.valueOf((float) height));
    }

    public double getTargetHeight() {
        return ((Float) this.field_70180_af.func_187225_a(TARGET_HEIGHT)).floatValue();
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if ((this.creator == null || this.target == null) && this.field_70173_aa > 2) {
                func_70106_y();
            } else {
                if (this.target != null) {
                    setTargetPos(this.target.func_174791_d());
                    setTargetVec(this.target.func_70040_Z());
                    setTargetHeight(this.target.field_70131_O);
                }
                if (this.field_70173_aa >= 45) {
                    if (!this.playedExecuteSound) {
                        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.EXECUTE_EFFECT_3, SoundCategory.PLAYERS, 1.5f, 0.9f + (0.2f * this.field_70146_Z.nextFloat()));
                        this.playedExecuteSound = true;
                    }
                    if (this.field_70173_aa >= 50 && !this.didKill) {
                        IMaxAttack.dealTrueDamage(this.creator, this.target, this.target.func_110138_aP() * 2.0f);
                        if (this.target instanceof EntityMultipleLives) {
                            this.target.takeawayNumLives(5);
                        }
                        this.didKill = true;
                        Vec3d vecPos = getTargetPos();
                        CustomParticleConfig config1 = new CustomParticleConfig();
                        config1.createInstance().setParticle(ParticleInit.PURPLE_SKULL).setSpread(4.0d, 1.0d, 4.0d).setCount(12).setIgnoreRange(true);
                        IParticleSpawner.spawnParticle(this.field_70170_p, config1, vecPos.field_72450_a, vecPos.field_72448_b + (getTargetHeight() / 2.0d), vecPos.field_72449_c);
                    }
                }
            }
            if (this.field_70173_aa >= 140) {
                func_70106_y();
                return;
            }
            return;
        }
        this.rotation += this.rotationIncreaser;
        if (this.field_70173_aa > 30) {
            this.rotationIncreaser += 0.015f;
            if (this.rotation >= 3.141592653589793d) {
                this.alpha -= 0.07f;
            }
        }
    }

    public void setTarget(EntityLivingBase target) {
        this.target = target;
    }

    public void setCreator(EntityPlayer creator) {
        this.creator = creator;
    }

    protected void func_70037_a(NBTTagCompound compound) {
    }

    protected void func_70014_b(NBTTagCompound compound) {
    }

    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(TARGET_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_Z, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_LOOK_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_LOOK_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_LOOK_Z, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_HEIGHT, Float.valueOf(0.0f));
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getAlpha() {
        return this.alpha;
    }
}
