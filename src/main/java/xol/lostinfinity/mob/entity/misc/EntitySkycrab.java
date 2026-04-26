package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.dimension.data.FlightCurve;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable;
import xol.lostinfinity.projectile.entity.EntitySkycrabAttack;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntitySkycrab.class */
public class EntitySkycrab extends EntityMultiLivesTameable implements IMaxAttack {
    private EntityLivingBase target;
    private Double moveSpeed;
    private static final double radius = 50.0d;
    private static BlockPos homePos;
    private BlockPos goalPosition;
    private FlightCurve flightCurve;
    public static float radians = 0.0f;
    private boolean targeting;
    private int time;
    private boolean aggressive;
    private static final double closeChaseSpeed = 0.20000000298023224d;
    private static final double mediumChaseSpeed = 0.07699999958276749d;
    private static final double swoopSpeed = 2.0d;

    public EntitySkycrab(World worldIn) {
        super(worldIn);
        this.target = null;
        this.moveSpeed = Double.valueOf(5.0d);
        this.goalPosition = null;
        this.flightCurve = null;
        this.targeting = false;
        this.time = 0;
        this.aggressive = false;
        func_70105_a(12.0f, 12.0f);
        func_189654_d(true);
    }

    protected void func_184651_r() {
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(35000.0d);
    }

    public boolean func_70652_k(Entity entity) {
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        EntityLivingBase owner = func_70902_q();
        if (!this.field_70170_p.field_72995_K) {
            if (owner == null) {
                func_70106_y();
                return;
            }
            if (isAggressive()) {
                BlockPos goalPosition = getGoalPosition();
                EntityLivingBase targetEntity = getTarget();
                if (goalPosition == null || targetEntity == null) {
                    for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(radius, radius, radius))) {
                        if (!target.func_110124_au().equals(owner.func_110124_au()) && !target.func_110124_au().equals(func_110124_au()) && this.field_70173_aa % 20 == 0 && !isTargeting()) {
                            setTarget(target);
                            updateGoalPosition(target);
                        }
                    }
                    if (getGoalPosition() == null) {
                        double centreX = getHomePos().func_177958_n();
                        double centreZ = getHomePos().func_177952_p();
                        double X = func_180425_c().func_177958_n();
                        func_180425_c().func_177956_o();
                        double Z = func_180425_c().func_177952_p();
                        double distToCircle = Math.sqrt(Math.pow(X - centreX, swoopSpeed) + Math.pow(Z - centreZ, swoopSpeed)) - radius;
                        if (distToCircle > radius) {
                            moveToOrbit(func_180425_c().func_177958_n(), func_180425_c().func_177952_p(), getHomePos().func_177958_n(), getHomePos().func_177952_p());
                            radians = 1.5707964f;
                        } else {
                            circle();
                        }
                    }
                } else {
                    updateGoalPosition(targetEntity);
                    BlockPos pos = func_180425_c();
                    BlockPos goalPos = getGoalPosition();
                    double x = pos.func_177958_n();
                    double y = pos.func_177956_o();
                    double z = pos.func_177952_p();
                    double goalX = goalPos.func_177958_n();
                    double goalY = goalPos.func_177956_o();
                    double goalZ = goalPos.func_177952_p();
                    double xDiff = Math.abs(goalX - x);
                    double yDiff = Math.abs(goalY - y);
                    double zDiff = Math.abs(goalZ - z);
                    if (xDiff + yDiff + zDiff > 10.0d) {
                        swoopToPosition();
                    } else if (xDiff + yDiff + zDiff < 5.0d) {
                        if (this.field_70173_aa % 3 == 0) {
                            fire();
                        }
                        if (targetEntity.field_70128_L || this.time > 1000) {
                            this.time = 0;
                            setTarget(null);
                            setGoalPosition(null);
                        }
                    } else {
                        Vec3d toTarget = new Vec3d(goalX - x, goalY - y, goalZ - z);
                        toTarget.func_72432_b();
                        if (goalX - x < 5.0d && goalZ - z < 5.0d) {
                            calculateVelocity(toTarget.field_72450_a * closeChaseSpeed, toTarget.field_72448_b * mediumChaseSpeed, toTarget.field_72449_c * closeChaseSpeed);
                        } else {
                            calculateVelocity(toTarget.field_72450_a * mediumChaseSpeed, toTarget.field_72448_b * mediumChaseSpeed, toTarget.field_72449_c * mediumChaseSpeed);
                        }
                        this.field_70133_I = true;
                    }
                }
            } else {
                circle();
            }
            if (this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL) {
                func_70106_y();
            }
            this.time++;
        }
    }

    private boolean isTargeting() {
        return this.targeting;
    }

    public void setTargeting(boolean targeting) {
        this.targeting = targeting;
    }

    private void updateGoalPosition(EntityLivingBase target) {
        BlockPos targetPos = target.func_180425_c().func_177963_a(0.0d, target.field_70131_O + 10.0f, 0.0d);
        this.goalPosition = targetPos;
    }

    private EntityLivingBase getTarget() {
        return this.target;
    }

    public void setTarget(EntityLivingBase target) {
        this.target = target;
    }

    public BlockPos getGoalPosition() {
        return this.goalPosition;
    }

    public void circle() {
        if (getHomePos() != null) {
            double centreX = getHomePos().func_177958_n();
            double centreY = getHomePos().func_177956_o();
            double centreZ = getHomePos().func_177952_p();
            double X = func_180425_c().func_177958_n();
            double Y = func_180425_c().func_177956_o();
            double Z = func_180425_c().func_177952_p();
            double xDiff = X - centreX;
            double yDiff = Y - centreY;
            double zDiff = Z - centreZ;
            if (Math.abs(yDiff) > 1.0d) {
                Vec3d YVec = new Vec3d(0.0d, -yDiff, 0.0d).func_72432_b();
                calculateVelocity(YVec.field_72450_a, YVec.field_72448_b, YVec.field_72449_c);
                this.field_70133_I = true;
                return;
            }
            double distToCircle = Math.sqrt(Math.pow(xDiff, swoopSpeed) + Math.pow(zDiff, swoopSpeed)) - radius;
            if (Math.abs(distToCircle) > radius) {
                moveToOrbit(X, Z, centreX, centreZ);
                return;
            }
            radians += 0.06283186f;
            if (radians >= 6.283185307179586d) {
                radians = 0.0f;
            }
            double x1 = (radius * ((double) MathHelper.func_76126_a(radians))) + centreX;
            double z1 = (radius * ((double) MathHelper.func_76134_b(radians))) + centreZ;
            Vec3d dir = new Vec3d(x1 - X, 0.0d, z1 - Z).func_72432_b();
            calculateVelocity(dir.field_72450_a * swoopSpeed, dir.field_72448_b * swoopSpeed, dir.field_72449_c * swoopSpeed);
            this.field_70133_I = true;
        }
    }

    public void calculateVelocity(double x, double y, double z) {
        this.field_70159_w = x;
        this.field_70181_x = y;
        this.field_70179_y = z;
    }

    public void moveToOrbit(double X, double Z, double centreX, double centreZ) {
        double startX = centreX + radius;
        Vec3d toCentre = new Vec3d(startX - X, 0.0d, centreZ - Z).func_72432_b();
        calculateVelocity(toCentre.field_72450_a, toCentre.field_72448_b, toCentre.field_72449_c);
        this.field_70133_I = true;
        radians = 1.5707964f;
    }

    public void swoopToPosition() {
        int remainder = this.field_70173_aa % 20;
        if (remainder == 0) {
            this.flightCurve = new FlightCurve(func_180425_c(), getGoalPosition(), this.moveSpeed.doubleValue());
            return;
        }
        if (this.flightCurve != null) {
            Vec3d toTarget = new Vec3d(getGoalPosition().func_177958_n() - func_180425_c().func_177958_n(), getGoalPosition().func_177956_o() - func_180425_c().func_177956_o(), getGoalPosition().func_177952_p() - func_180425_c().func_177952_p()).func_72432_b();
            System.out.println(String.format("%f", Double.valueOf((this.flightCurve.getYVelocity(((double) remainder) / 100.0d) * this.moveSpeed.doubleValue()) / 200.0d)));
            double xVel = toTarget.field_72450_a / swoopSpeed;
            double yVel = (toTarget.field_72448_b / swoopSpeed) + ((this.flightCurve.getYVelocity(((double) remainder) / 20.0d) * this.moveSpeed.doubleValue()) / 200.0d);
            double zVel = toTarget.field_72449_c / swoopSpeed;
            System.out.println(String.format("X: %f Y: %f Z: %f", Double.valueOf(xVel), Double.valueOf(yVel), Double.valueOf(zVel)));
            calculateVelocity(xVel * 5.0d, yVel * 5.0d, zVel * 5.0d);
            this.field_70133_I = true;
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable
    protected int numberOfLives() {
        return 10;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable
    protected void updateLifeAction() {
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
        }
    }

    public void fire() {
        if (func_70902_q() != null) {
            EntitySkycrabAttack attack = new EntitySkycrabAttack(this.field_70170_p);
            attack.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            attack.func_70186_c((this.field_70146_Z.nextDouble() - 0.5d) * 0.10000000149011612d, -0.1d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.10000000149011612d, 0.4f, 0.0f);
            attack.setThrower(this);
            attack.setCrabOwner((EntityPlayer) func_70902_q());
            this.field_70170_p.func_175739_a(EnumParticleTypes.LAVA, this.field_70165_t, this.field_70163_u, this.field_70161_v, 2, this.field_70146_Z.nextDouble() * 3.0d, 0.3d, this.field_70146_Z.nextDouble() * 3.0d, 0.15000000596046448d, new int[0]);
            this.field_70170_p.func_72838_d(attack);
            func_184185_a(SoundInit.LASER_WEAPON_1, 1.5f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f));
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.SKYCRAB_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.SKYCRAB_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.SKYCRAB_AMBIENT;
    }

    public void playTargetSound() {
        func_184185_a(SoundInit.SKYCRAB_TARGET, 2.0f, 1.0f);
    }

    protected boolean func_70692_ba() {
        return func_70902_q() == null;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable
    public EntityAgeable func_90011_a(EntityAgeable ageable) {
        return null;
    }

    public void setHomePos(BlockPos hpos) {
        homePos = hpos;
    }

    public BlockPos getHomePos() {
        return homePos;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("HomePositionX", getHomePos().func_177958_n());
        tag.func_74768_a("HomePositionZ", getHomePos().func_177952_p());
        tag.func_74768_a("HomePositionY", getHomePos().func_177956_o());
        tag.func_74757_a("Aggressive", isAggressive());
    }

    public boolean isAggressive() {
        return this.aggressive;
    }

    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultiLivesTameable
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        BlockPos homePosition = new BlockPos(tag.func_74762_e("HomePositionX"), tag.func_74762_e("HomePositionY"), tag.func_74762_e("HomePositionZ"));
        setHomePos(homePosition);
        setAggressive(tag.func_74767_n("Aggressive"));
    }

    public void setGoalPosition(BlockPos targetPos) {
        this.goalPosition = targetPos;
    }
}
