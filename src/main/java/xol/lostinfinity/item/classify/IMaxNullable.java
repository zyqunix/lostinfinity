package xol.lostinfinity.item.classify;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.util.data.CustomDamageResult;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/classify/IMaxNullable.class */
public interface IMaxNullable {
    float nullableReaction(EntityPlayer entityPlayer, boolean z, float f, float f2, ItemStack itemStack);

    default float trueNullableReaction(EntityPlayer player, boolean isMainHand, float originalDamage, float newDamage, ItemStack stack, CustomDamageResult result) {
        return newDamage;
    }
}
