package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntitySkyStrike extends EntityBaseThrowable {
    private int multiplier;
    private EntityLivingBase followTarget;
    public EntitySkyStrike(World par1World) {
        super(par1World);
        this.multiplier = 1;
        this.followTarget = null;
        func_70105_a(1.0f, 1.0f);
    }
    public EntitySkyStrike(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.multiplier = 1;
        this.followTarget = null;
        func_70105_a(1.0f, 1.0f);
    }
    public void setDamageMultiplierAndTarget(int mult, EntityLivingBase target) {
        this.multiplier = mult;
        this.followTarget = target;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(4.0d, 4.0d, 4.0d))) {
                IMaxAttack.dealMaxHealth((Entity) this, target, 5, this.multiplier);
            }
            this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0f, false);
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.05f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < 3; i++) {
                this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_INSTANT, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), 0.0d, 0.0d, 0.0d, new int[0]);
            }
            return;
        }
        if (this.followTarget != null) {
            if (this.followTarget.field_70165_t > this.field_70165_t) {
                this.field_70159_w = 1.0d;
            } else if (this.followTarget.field_70165_t < this.field_70165_t) {
                this.field_70159_w = -1.0d;
            }
            if (this.followTarget.field_70161_v > this.field_70161_v) {
                this.field_70179_y = 1.0d;
            } else if (this.followTarget.field_70161_v < this.field_70161_v) {
                this.field_70179_y = -1.0d;
            }
        }
    }
}
