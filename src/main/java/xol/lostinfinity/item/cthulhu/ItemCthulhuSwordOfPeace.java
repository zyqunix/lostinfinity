package xol.lostinfinity.item.cthulhu;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhu;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/cthulhu/ItemCthulhuSwordOfPeace.class */
public class ItemCthulhuSwordOfPeace extends AbstractCthulhuSword {
    public ItemCthulhuSwordOfPeace(String regName) {
        super(regName);
    }

    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            checkHittingBarrier(player);
        }
        return super.onEntitySwing(entityLiving, stack);
    }

    private static void checkHittingBarrier(EntityPlayer entityPlayer) {
        World world = entityPlayer.field_70170_p;
        if (entityPlayer.func_184586_b(EnumHand.MAIN_HAND).func_77973_b().equals(ItemInit.cthulhuSwordPeace) && world.field_72995_K && isHittingBarrier(entityPlayer)) {
            world.func_184133_a((EntityPlayer) null, entityPlayer.func_180425_c(), SoundInit.FORCEFIELD_HIT, SoundCategory.PLAYERS, 1.0f, 0.8f + (entityPlayer.field_70170_p.field_73012_v.nextFloat() * 0.4f));
            entityPlayer.field_70170_p.field_72996_f.stream().filter(entity -> {
                return entity instanceof EntityCthulhu;
            }).map(entity2 -> {
                return (EntityCthulhu) entity2;
            }).findFirst().ifPresent(cthulhu -> {
                sendCthulhuPacket(3, 20, cthulhu.func_145782_y());
            });
        }
    }
}
