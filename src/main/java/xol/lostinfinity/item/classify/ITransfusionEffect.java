package xol.lostinfinity.item.classify;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/classify/ITransfusionEffect.class */
public interface ITransfusionEffect {
    void transfuse(EntityPlayer entityPlayer, EntityLivingBase entityLivingBase, EnumHand enumHand, ItemStack itemStack, List<Potion> list);
}
