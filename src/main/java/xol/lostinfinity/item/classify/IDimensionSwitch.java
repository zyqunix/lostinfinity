package xol.lostinfinity.item.classify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
public interface IDimensionSwitch {
    void onDimensionSwitch(EntityPlayer entityPlayer, ItemStack itemStack);
}
