package xol.lostinfinity.mob.entity.classify;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.util.DamageSource;
public interface ILostMultiPart extends IEntityMultiPart {
    boolean attackEntityFromPart(EntityLivingBase entityLivingBase, DamageSource damageSource, float f);
    default boolean func_70965_a(MultiPartEntityPart dragonPart, DamageSource source, float damage) {
        return false;
    }
}
