package xol.lostinfinity.mob.ai;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
@FunctionalInterface
public interface IThrowableAttack {
    EntityThrowable createThowable(World world, EntityLivingBase entityLivingBase, double d, double d2, double d3);
    default float getVelocity() {
        return 1.6f;
    }
    default float getInaccuracy(World world) {
        return 3 - world.func_175659_aa().func_151525_a();
    }
    default Entity createFireball(EntityLivingBase parent, Entity target) {
        EntityThrowable fireball = createThowable(parent.field_70170_p, parent, parent.field_70165_t, parent.field_70163_u, parent.field_70161_v);
        double d0 = (target.field_70163_u + ((double) target.func_70047_e())) - 1.100000023841858d;
        double d1 = target.field_70165_t - parent.field_70165_t;
        double d2 = d0 - fireball.field_70163_u;
        double d3 = target.field_70161_v - parent.field_70161_v;
        float f = MathHelper.func_76133_a((d1 * d1) + (d3 * d3)) * 0.2f;
        fireball.func_70186_c(d1, d2 + ((double) f), d3, getVelocity(), getInaccuracy(parent.field_70170_p));
        return fireball;
    }
}
