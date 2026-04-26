package xol.lostinfinity.item.classify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
public interface IMoveTick {
    void moveTick(EntityPlayer entityPlayer, EnumHand enumHand, ItemStack itemStack);
}
