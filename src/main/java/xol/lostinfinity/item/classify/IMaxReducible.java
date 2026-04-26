package xol.lostinfinity.item.classify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
public interface IMaxReducible {
    float reduceMaxDamage(EntityPlayer entityPlayer, boolean z, float f, float f2, ItemStack itemStack);
    default float trueReduceMaxDamage(EntityPlayer player, boolean isMainHand, float damage, float reductionMultiplier, ItemStack stack) {
        return damage;
    }
}
