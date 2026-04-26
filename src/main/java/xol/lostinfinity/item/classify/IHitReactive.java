package xol.lostinfinity.item.classify;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
public interface IHitReactive {
    void hitReaction(EntityPlayer entityPlayer, Entity entity, float f, ItemStack itemStack);
}
