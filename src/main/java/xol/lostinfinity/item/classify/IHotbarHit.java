package xol.lostinfinity.item.classify;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.util.data.CustomDamageResult;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/classify/IHotbarHit.class */
public interface IHotbarHit {
    void hitReaction(EntityPlayer entityPlayer, Entity entity, CustomDamageResult customDamageResult, ItemStack itemStack);
}
