package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntitySkycrabAttack extends EntityBaseThrowable {
    private EntityPlayer crabOwner;
    public EntitySkycrabAttack(World par1World) {
        super(par1World);
        this.crabOwner = null;
        func_70105_a(1.0f, 1.0f);
    }
    public EntitySkycrabAttack(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.crabOwner = null;
        func_70105_a(1.0f, 1.0f);
    }
    public EntitySkycrabAttack(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.crabOwner = null;
        func_70105_a(1.0f, 1.0f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void setThrower(EntityLivingBase throwset) {
        this.field_70192_c = throwset;
    }
    public void setCrabOwner(EntityPlayer player) {
        this.crabOwner = player;
    }
    private EntityPlayer getCrabOwner() {
        return this.crabOwner;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (getCrabOwner() != null && func_85052_h() != null) {
                for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(4.0d, 4.0d, 4.0d))) {
                    if (!target.func_110124_au().equals(this.crabOwner.func_110124_au()) && !target.func_110124_au().equals(func_85052_h().func_110124_au())) {
                        IMaxAttack.dealMaxHealth((Entity) func_85052_h(), target, 4, 2.0f);
                    }
                }
            }
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.EXPLOSION_RING).setSpread(2.0d, 1.0d, 2.0d).setCount(3).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            func_184185_a(SoundInit.GENERIC_WEAPON_6, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        if (func_189652_ae()) {
            return 0.0f;
        }
        return 0.0366f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(ParticleInit.LASER_FIZZLE_LARGE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
}
