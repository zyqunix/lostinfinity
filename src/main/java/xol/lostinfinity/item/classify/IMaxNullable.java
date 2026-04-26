package xol.lostinfinity.item.classify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.util.data.CustomDamageResult;
public interface IMaxNullable {
    float nullableReaction(EntityPlayer entityPlayer, boolean z, float f, float f2, ItemStack itemStack);
    default float trueNullableReaction(EntityPlayer player, boolean isMainHand, float originalDamage, float newDamage, ItemStack stack, CustomDamageResult result) {
        return newDamage;
    }
}
