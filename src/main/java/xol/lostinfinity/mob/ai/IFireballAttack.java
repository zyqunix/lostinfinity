package xol.lostinfinity.mob.ai;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
@FunctionalInterface
public interface IFireballAttack {
    @Nonnull
    Entity createFireball(World world, EntityLivingBase entityLivingBase, double d, double d2, double d3, int i);
    default Entity createFireball(EntityLivingBase parent, Entity target) {
        Vec3d vec3d = parent.func_70676_i(1.0f);
        double d2 = target.field_70165_t - (parent.field_70165_t + (vec3d.field_72450_a * 4.0d));
        double d3 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 2.0f))) - ((0.5d + parent.field_70163_u) + ((double) (parent.field_70131_O / 2.0f)));
        double d4 = target.field_70161_v - (parent.field_70161_v + (vec3d.field_72449_c * 4.0d));
        Entity fireball = createFireball(parent.field_70170_p, parent, d2, d3, d4, parent.func_70681_au().nextInt(6));
        fireball.field_70165_t = parent.field_70165_t + (vec3d.field_72450_a * 4.0d);
        fireball.field_70163_u = parent.field_70163_u + ((double) (parent.field_70131_O / 2.0f)) + 0.5d;
        fireball.field_70161_v = parent.field_70161_v + (vec3d.field_72449_c * 4.0d);
        return fireball;
    }
}
