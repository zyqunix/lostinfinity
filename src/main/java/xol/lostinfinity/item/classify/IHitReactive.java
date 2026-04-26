package xol.lostinfinity.item.classify;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/classify/IHitReactive.class */
public interface IHitReactive {
    void hitReaction(EntityPlayer entityPlayer, Entity entity, float f, ItemStack itemStack);
}
