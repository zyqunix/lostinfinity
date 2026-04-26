package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityBlightedComet.class */
public class EntityBlightedComet extends EntityBaseThrowable {
    public EntityBlightedComet(World par1World) {
        super(par1World);
        func_70105_a(1.0f, 1.0f);
    }

    public EntityBlightedComet(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(1.0f, 1.0f);
    }

    public EntityBlightedComet(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(1.0f, 1.0f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void setThrower(EntityLivingBase throwset) {
        this.field_70192_c = throwset;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(4.0d, 4.0d, 4.0d))) {
                if (IMaxAttack.dealMaxHealth((Entity) this, target, 4, 3.0f).didSuccessfulHit()) {
                    target.func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 200));
                }
            }
            this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0f, false);
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.0366f;
    }
}
