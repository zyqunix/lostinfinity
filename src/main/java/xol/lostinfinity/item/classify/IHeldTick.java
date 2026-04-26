package xol.lostinfinity.item.classify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
public interface IHeldTick {
    default void heldTick(EntityPlayer player, EnumHand hand, ItemStack stack) {
    }
    default void startHolding(EntityPlayer player, EnumHand hand, ItemStack stack) {
    }
    default void stopHolding(EntityPlayer player, EnumHand hand, ItemStack stack) {
    }
}
