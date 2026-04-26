package xol.lostinfinity.mob.entity.minion.andromeda;

import java.util.Collections;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.projectile.entity.EntityBaseThrowable;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/minion/andromeda/EntityAndromedaChaser.class */
public class EntityAndromedaChaser extends EntityBaseThrowable {
    private static final List<String> DAMAGE_TYPE = Collections.singletonList("Aquatic");
    private EntityLivingBase target;

    public EntityAndromedaChaser(World worldIn) {
        super(worldIn);
        func_70105_a(2.5f, 2.5f);
    }

    public void setTarget(EntityLivingBase target) {
        this.target = target;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void setThrower(EntityLivingBase throwset) {
        super.setThrower(throwset);
        this.field_184539_c = throwset;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        Entity entity;
        if (this.field_70170_p.field_72995_K || result.field_72313_a == RayTraceResult.Type.BLOCK || !(result.field_72308_g instanceof EntityLivingBase) || (entity = (EntityLivingBase) result.field_72308_g) != this.target || entity == this.field_70192_c || entity == getSecondaryThrower()) {
            return;
        }
        if ((this.field_70192_c instanceof EntityAndromedaController) && !this.field_70192_c.validateTarget(entity)) {
            return;
        }
        double dX = ((EntityLivingBase) entity).field_70159_w;
        double dY = ((EntityLivingBase) entity).field_70181_x;
        double dZ = ((EntityLivingBase) entity).field_70179_y;
        IMaxAttack.dealTrueDamage(getSecondaryThrower(), entity, entity.func_110138_aP() * 1.25f, DAMAGE_TYPE);
        ((EntityLivingBase) entity).field_70159_w = dX;
        ((EntityLivingBase) entity).field_70181_x = dY;
        ((EntityLivingBase) entity).field_70179_y = dZ;
        func_70106_y();
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa > 140) {
            func_70106_y();
            return;
        }
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (this.target == null) {
            func_70106_y();
            return;
        }
        if (this.target.field_70128_L) {
            return;
        }
        this.field_70159_w *= 0.95d;
        this.field_70181_x *= 0.95d;
        this.field_70179_y *= 0.95d;
        Vec3d dir = LMath.fastNormalize(LMath.getEntityMiddle(this.target).func_178788_d(func_174791_d())).func_186678_a(0.2d);
        func_70024_g(dir.field_72450_a, dir.field_72448_b, dir.field_72449_c);
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
