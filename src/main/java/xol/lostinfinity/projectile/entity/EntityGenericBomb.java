package xol.lostinfinity.projectile.entity;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityGenericBomb extends EntityBaseThrowable {
    public EntityGenericBomb(World par1World) {
        super(par1World);
    }
    public EntityGenericBomb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityGenericBomb(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            UUID thrower_uuid = null;
            if (func_85052_h() != null) {
                thrower_uuid = func_85052_h().func_110124_au();
            }
            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(2.0d, 2.0d, 2.0d))) {
                if (!target.func_110124_au().equals(thrower_uuid)) {
                    IMaxAttack.dealMaxHealth(this, target, 3);
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
        return 0.05f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), 0.0d, 0.0d, 0.0d, new int[0]);
        }
    }
}
