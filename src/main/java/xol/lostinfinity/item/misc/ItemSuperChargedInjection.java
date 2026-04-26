package xol.lostinfinity.item.misc;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemSuperChargedInjection.class */
public class ItemSuperChargedInjection extends ItemBasic {
    public ItemSuperChargedInjection(String regName) {
        super(regName, TabsInit.TAB_AUXMATS);
    }

    public boolean func_111207_a(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target instanceof EntityPlayer) {
            if (!playerIn.field_70170_p.field_72995_K) {
                playerIn.field_70170_p.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.SYRINGE_USE, SoundCategory.MASTER, 1.5f, 1.0f);
            }
            target.func_70690_d(new PotionEffect(PotionInit.SUPERCHARGED, 72000, 9));
        }
        return super.func_111207_a(stack, playerIn, target, hand);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Inject another player to grant them Super-Charged.");
        tooltip.add(TextFmt.Light_Purple + "Grants the player 10 lives, 50% dodge change, rapid healing, and a damage aura that deals 25% max health twice a second.");
    }
}
