package xol.lostinfinity.item.classify;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/classify/IHotbarTick.class */
public interface IHotbarTick {
    void hotbarTick(EntityPlayer entityPlayer, int i, ItemStack itemStack);
}
