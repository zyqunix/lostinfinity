package xol.lostinfinity.projectile.entity;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.LMath;
public class EntityLaserGunBeam extends Entity implements IMaxAttack {
    private EntityPlayer owner;
    private static final DataParameter<Float> TARGET_X = EntityDataManager.func_187226_a(EntityLaserGunBeam.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_Y = EntityDataManager.func_187226_a(EntityLaserGunBeam.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_Z = EntityDataManager.func_187226_a(EntityLaserGunBeam.class, DataSerializers.field_187193_c);
    private static final DataParameter<ItemStack> STACK = EntityDataManager.func_187226_a(EntityLaserGunBeam.class, DataSerializers.field_187196_f);
    private static final DataParameter<Boolean> CHARGING = EntityDataManager.func_187226_a(EntityLaserGunBeam.class, DataSerializers.field_187198_h);
    private static final int duration = 40;
    private final double dist = 90.0d;
    public int counter;
    private boolean wasCharging;
    public EntityLaserGunBeam(World worldIn) {
        super(worldIn);
        this.owner = null;
        this.dist = 90.0d;
        this.wasCharging = false;
    }
    public ItemStack getStack() {
        return (ItemStack) this.field_70180_af.func_187225_a(STACK);
    }
    public void setStack(ItemStack stack) {
        this.field_70180_af.func_187227_b(STACK, stack);
    }
    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(TARGET_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_Z, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(STACK, ItemStack.field_190927_a);
        this.field_70180_af.func_187214_a(CHARGING, false);
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
    public EntityPlayer getOwner() {
        return this.owner;
    }
    public void setOwner(EntityPlayer owner) {
        this.owner = owner;
    }
    public void func_70030_z() {
        super.func_70030_z();
        ItemStack stack = getStack();
        if (stack.func_77973_b() != ItemInit.laserGun) {
            return;
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.owner == null || this.owner.func_184614_ca().func_77973_b() != ItemInit.laserGun) {
                func_70106_y();
                return;
            }
            updatePosition();
            boolean charging = ItemChanneling.isChanneling(this.owner, stack);
            setCharging(charging);
            if (charging) {
                if (this.counter < 34) {
                    this.counter++;
                }
            } else if (this.counter < 34) {
                func_70106_y();
                return;
            } else {
                this.counter++;
                if (this.counter == 36) {
                    damageTarget();
                }
            }
            if (this.counter >= duration) {
                func_70106_y();
                return;
            }
            return;
        }
        if (!((Boolean) this.field_70180_af.func_187225_a(CHARGING)).booleanValue() || this.counter < 34) {
            this.counter++;
        }
        spawnParticles();
    }
    private void damageTarget() {
        EntityMultipleLives multiLifer;
        int lives;
        Vec3d dir = getTargetPos().func_178788_d(func_174791_d()).func_72432_b();
        ArrayList<EntityLivingBase> hitEntities = new ArrayList<>();
        double d = 0.0d;
        while (true) {
            double i = d;
            if (i <= 90.0d) {
                Vec3d pos = func_174791_d().func_72441_c(dir.field_72450_a * i, dir.field_72448_b * i, dir.field_72449_c * i);
                AxisAlignedBB checkBox = new AxisAlignedBB(pos.field_72450_a - 1.8d, pos.field_72448_b - 1.8d, pos.field_72449_c - 1.8d, pos.field_72450_a + 1.8d, pos.field_72448_b + 1.8d, pos.field_72449_c + 1.8d);
                for (EntityMultipleLives entityMultipleLives : this.field_70170_p.func_72872_a(EntityLivingBase.class, checkBox)) {
                    if (!(entityMultipleLives instanceof EntityImmaterial) && !entityMultipleLives.func_110124_au().equals(this.owner.func_110124_au()) && !hitEntities.contains(entityMultipleLives)) {
                        if (!IMaxAttack.dealTrueDamage(this.owner, entityMultipleLives, entityMultipleLives.func_110138_aP() * 0.5f).wasTargetKilled() && (entityMultipleLives instanceof EntityMultipleLives) && (lives = (multiLifer = entityMultipleLives).remainingLives()) >= 2) {
                            multiLifer.takeawayNumLives(MathHelper.func_76141_d(Math.min(lives / 2, 10)));
                        }
                        hitEntities.add(entityMultipleLives);
                    }
                }
                d = i + 0.4d;
            } else {
                this.field_70170_p.func_184133_a((EntityPlayer) null, this.owner.func_180425_c(), SoundInit.LASER_WEAPON_9, SoundCategory.MASTER, 1.0f, 1.0f);
                return;
            }
        }
    }
    private void updatePosition() {
        if (this.owner == null) {
            return;
        }
        if (this.counter % 5 == 0 && this.counter < 36) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, this.owner.func_180425_c(), SoundInit.LASER_WEAPON_8, SoundCategory.MASTER, 1.0f, 0.7f + (this.counter / 10.0f));
        }
        Vec3d dir = this.owner.func_70040_Z();
        Vec3d offset = dir.func_178785_b(1.5707964f);
        func_70634_a(this.owner.field_70165_t - (offset.field_72450_a / 2.0d), this.owner.field_70163_u + (((double) this.owner.field_70131_O) / 1.8d), this.owner.field_70161_v - (offset.field_72449_c / 2.0d));
        Vec3d targetPos = dir.func_186678_a(90.0d).func_178787_e(this.owner.func_174791_d());
        setTargetPos(targetPos);
    }
    private void spawnParticles() {
        if (this.counter >= duration) {
            return;
        }
        Vec3d dir = LMath.fastNormalize(getTargetPos().func_178788_d(func_174791_d()));
        double d = 0.0d;
        while (true) {
            double i = d;
            if (i <= 90.0d) {
                if (this.field_70146_Z.nextInt(4) == 0) {
                    Vec3d pos = func_174791_d().func_178787_e(dir.func_186678_a(i));
                    this.field_70170_p.func_175688_a(this.counter < 35 ? ParticleInit.FLAME_SMALL : ParticleInit.SPECTRAL, pos.field_72450_a, pos.field_72448_b + 0.4d, pos.field_72449_c, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, new int[0]);
                }
                d = i + 1.0d;
            } else {
                return;
            }
        }
    }
    public double getDist() {
        return 90.0d;
    }
    protected void func_70037_a(NBTTagCompound compound) {
    }
    protected void func_70014_b(NBTTagCompound compound) {
    }
    private void setCharging(boolean charging) {
        if (this.wasCharging == charging) {
            return;
        }
        this.wasCharging = charging;
        this.field_70180_af.func_187227_b(CHARGING, Boolean.valueOf(this.wasCharging));
    }
}
