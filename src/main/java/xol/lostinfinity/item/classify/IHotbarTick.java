package xol.lostinfinity.item.classify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
public interface IHotbarTick {
    void hotbarTick(EntityPlayer entityPlayer, int i, ItemStack itemStack);
}
