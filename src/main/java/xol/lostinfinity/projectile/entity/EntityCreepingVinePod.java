package xol.lostinfinity.projectile.entity;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityCreepingVinePod.class */
public class EntityCreepingVinePod extends EntityBaseThrowable {
    private final Set<EntityLivingBase> pierced;
    private Vec3d initDirection;
    private Rotations currentDir;
    private Rotations randomDir;
    private int turnTick;

    public EntityCreepingVinePod(World worldIn) {
        super(worldIn);
        this.pierced = new HashSet();
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void setThrower(EntityLivingBase throwset) {
        super.setThrower(throwset);
        this.field_184539_c = throwset;
    }

    public void setInitDirection(Vec3d initDirection) {
        this.initDirection = initDirection;
        this.currentDir = LMath.toPitchYaw(initDirection);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        EntityMultipleLives entityMultipleLives;
        if (this.field_70170_p.field_72995_K || result.field_72313_a != RayTraceResult.Type.ENTITY || !(result.field_72308_g instanceof EntityLivingBase) || (entityMultipleLives = (EntityLivingBase) result.field_72308_g) == this.field_70192_c || this.pierced.contains(entityMultipleLives)) {
            return;
        }
        if (!IMaxAttack.dealTrueDamage(this.field_70192_c, entityMultipleLives, entityMultipleLives.func_110138_aP() * 0.75f).wasTargetKilled() && (entityMultipleLives instanceof EntityMultipleLives)) {
            entityMultipleLives.takeawayNumLives(5);
        }
        this.pierced.add(entityMultipleLives);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175682_a(ParticleInit.GALAXY_GREEN, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
            return;
        }
        if (this.initDirection == null) {
            setInitDirection(LMath.fastNormalize(new Vec3d(this.field_70159_w, this.field_70181_x, this.field_70179_y)));
        }
        int i = this.turnTick - 1;
        this.turnTick = i;
        if (i <= 0) {
            this.turnTick = 30;
            this.randomDir = LMath.toPitchYaw(LMath.fastNormalize(new Vec3d((((double) this.field_70146_Z.nextFloat()) - 0.5d) + (this.initDirection.field_72450_a * 0.2d), (((double) this.field_70146_Z.nextFloat()) - 0.5d) + (this.initDirection.field_72448_b * 0.2d), (((double) this.field_70146_Z.nextFloat()) - 0.5d) + (this.initDirection.field_72449_c * 0.2d))));
        }
        this.currentDir = LMath.lerp(this.currentDir, this.randomDir, 1.0f / this.turnTick);
        Vec3d motion = LMath.toLookVec(this.currentDir.func_179415_b(), this.currentDir.func_179416_c());
        this.field_70159_w = motion.field_72450_a * 0.25d;
        this.field_70181_x = motion.field_72448_b * 0.25d;
        this.field_70179_y = motion.field_72449_c * 0.25d;
        this.field_70133_I = true;
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
