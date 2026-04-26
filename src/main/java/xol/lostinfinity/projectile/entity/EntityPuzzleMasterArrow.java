package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.boss.EntityPuzzleMaster;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityPuzzleMasterArrow.class */
public class EntityPuzzleMasterArrow extends EntityBaseThrowable {
    public EntityPuzzleMasterArrow(World par1World) {
        super(par1World);
    }

    public EntityPuzzleMasterArrow(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase) && !(result.field_72308_g instanceof EntityPuzzleMaster)) {
                IMaxAttack.dealTrueDamage(this, result.field_72308_g, result.field_72308_g.func_110138_aP() * 0.95f);
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
