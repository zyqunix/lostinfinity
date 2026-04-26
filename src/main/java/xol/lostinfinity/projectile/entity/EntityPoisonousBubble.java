package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityPoisonousBubble.class */
public class EntityPoisonousBubble extends EntityBaseThrowable {
    public EntityPoisonousBubble(World par1World) {
        super(par1World);
        func_70105_a(0.9f, 0.9f);
    }

    public EntityPoisonousBubble(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.9f, 0.9f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa > 30) {
                func_70106_y();
                return;
            }
            return;
        }
        this.field_70170_p.func_175682_a(this.field_70146_Z.nextBoolean() ? ParticleInit.ACID : ParticleInit.ACID_YELLOW, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityLivingBase)) {
                EntityPlayer entityPlayer = (EntityLivingBase) result.field_72308_g;
                if (!(entityPlayer instanceof EntityPlayer) || !entityPlayer.func_70644_a(PotionInit.ACIDIC)) {
                    entityPlayer.func_70690_d(new PotionEffect(PotionInit.PLAGUE, 100, 2));
                }
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
