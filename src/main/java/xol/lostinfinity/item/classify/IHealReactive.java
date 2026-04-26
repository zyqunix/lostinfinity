package xol.lostinfinity.item.classify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
public interface IHealReactive {
    float itemHealReaction(EntityPlayer entityPlayer, float f, ItemStack itemStack);
}
