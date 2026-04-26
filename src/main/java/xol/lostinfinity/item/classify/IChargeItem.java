package xol.lostinfinity.item.classify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
public interface IChargeItem {
    void endChargeEffect(ItemStack itemStack, EntityPlayer entityPlayer);
}
