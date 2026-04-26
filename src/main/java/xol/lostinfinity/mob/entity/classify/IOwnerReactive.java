package xol.lostinfinity.mob.entity.classify;
import net.minecraft.entity.Entity;
import xol.lostinfinity.util.data.CustomDamageResult;
public interface IOwnerReactive {
    default void maxHealthDamageEffect(Entity attacker, CustomDamageResult result) {
    }
    default void trueDamageEffect(Entity attacker, CustomDamageResult result) {
    }
}
