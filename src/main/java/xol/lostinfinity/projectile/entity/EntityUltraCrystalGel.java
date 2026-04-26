package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.starforge.EntityClyster;
import xol.lostinfinity.mob.entity.starforge.EntityReflectal;
import xol.lostinfinity.mob.entity.starforge.EntitySpyker;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityUltraCrystalGel.class */
public class EntityUltraCrystalGel extends EntityBaseThrowable {
    public EntityUltraCrystalGel(World par1World) {
        super(par1World);
    }

    public EntityUltraCrystalGel(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityUltraCrystalGel(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null) {
                boolean flag = false;
                if (result.field_72308_g instanceof EntitySpyker) {
                    EntitySpyker hitEntity = result.field_72308_g;
                    if (!hitEntity.isEnlarged()) {
                        hitEntity.setEnlarged(true);
                        hitEntity.func_70606_j(hitEntity.func_110138_aP());
                        flag = true;
                    }
                } else if (result.field_72308_g instanceof EntityReflectal) {
                    EntityReflectal hitEntity2 = result.field_72308_g;
                    if (!hitEntity2.isEnlarged()) {
                        hitEntity2.setEnlarged(true);
                        hitEntity2.func_70606_j(hitEntity2.func_110138_aP());
                        hitEntity2.setLivesCount(0);
                        flag = true;
                    }
                } else if (result.field_72308_g instanceof EntityClyster) {
                    EntityClyster hitEntity3 = result.field_72308_g;
                    if (!hitEntity3.isEnlarged()) {
                        hitEntity3.setEnlarged(true);
                        hitEntity3.func_70606_j(hitEntity3.func_110138_aP());
                        flag = true;
                    }
                }
                if (flag) {
                    func_184185_a(SoundInit.GENERIC_WEAPON_2, 2.0f, 1.0f);
                    func_184185_a(SoundInit.MAGIC_WEAPON_3, 2.0f, 1.0f);
                }
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.05f;
    }
}
