package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityElementiumPrime extends EntityBaseThrowable {
    public EntityElementiumPrime(World par1World) {
        super(par1World);
    }
    public EntityElementiumPrime(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase)) {
                EntityLivingBase hit = result.field_72308_g;
                hit.func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 300, 1));
                hit.func_70690_d(new PotionEffect(PotionInit.PLAGUE, 300, 1));
                hit.func_70690_d(new PotionEffect(PotionInit.VULNERABILITY, 300, 2));
                func_85052_h().func_70691_i(func_85052_h().func_110138_aP() / 5.0f);
            }
            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(5.0d, 5.0d, 5.0d))) {
                IMaxAttack.dealMaxHealth(this, target, 3);
            }
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.EXPLOSION_RING_DARK).setSpread(1.0d, 1.0d, 1.0d).setCount(5).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_6, SoundCategory.PLAYERS, 1.5f, 0.6f + (0.4f * this.field_70146_Z.nextFloat()));
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.02f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_INSTANT, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
}
