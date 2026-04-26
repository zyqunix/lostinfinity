package xol.lostinfinity.item.classify;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/classify/IMoveTick.class */
public interface IMoveTick {
    void moveTick(EntityPlayer entityPlayer, EnumHand enumHand, ItemStack itemStack);
}
