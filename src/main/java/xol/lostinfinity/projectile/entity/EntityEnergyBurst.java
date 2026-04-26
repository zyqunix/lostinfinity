package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityEnergyBurst.class */
public class EntityEnergyBurst extends EntityBaseThrowable {
    public EntityEnergyBurst(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }

    public EntityEnergyBurst(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }

    public EntityEnergyBurst(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityLivingBase) && func_85052_h() != null) {
                EntityLivingBase hit = result.field_72308_g;
                if (!hit.equals(func_85052_h())) {
                    if (hit.func_110143_aJ() <= hit.func_110138_aP() / 3.0f) {
                        IMaxAttack.dealTrueDamage(this, hit, hit.func_110138_aP() * 0.33f);
                    } else {
                        IMaxAttack.dealMaxHealth(this, hit, 3);
                    }
                }
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
