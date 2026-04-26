package xol.lostinfinity.item.classify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
public interface IPotionReactive {
    default void potionAddReaction(EntityPlayer player, ItemStack stack, EnumHand hand, PotionEffect newEffect, PotionEffect prevEffect) {
    }
    default void potionRemoveReaction(EntityPlayer player, ItemStack stack, EnumHand hand, Potion potion, PotionEffect effect) {
    }
}
