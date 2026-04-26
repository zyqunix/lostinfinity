package xol.lostinfinity.item.misc;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.mob.entity.misc.EntityDimensionalMerchant;
import xol.lostinfinity.mob.entity.misc.EntityRocketStrappedExplosive;
public class ItemRocketStrappedExplosive extends ItemBasic {
    public ItemRocketStrappedExplosive(String regName) {
        super(regName, TabsInit.TAB_AUXMATS);
    }
    public boolean func_111207_a(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target instanceof EntityPlayer) {
            if (!playerIn.field_70170_p.field_72995_K) {
                EntityPlayer targetPlayer = (EntityPlayer) target;
                targetPlayer.func_70024_g(0.0d, 0.05d, 0.0d);
                targetPlayer.field_70133_I = true;
                EntityRocketStrappedExplosive boost = new EntityRocketStrappedExplosive(playerIn.field_70170_p);
                boost.func_70107_b(targetPlayer.field_70165_t, targetPlayer.field_70163_u, targetPlayer.field_70161_v);
                boost.setOwner(targetPlayer);
                playerIn.field_70170_p.func_72838_d(boost);
            }
            stack.func_190918_g(1);
        }
        if (target instanceof EntityDimensionalMerchant) {
            if (!playerIn.field_70170_p.field_72995_K) {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "The " + target.func_70005_c_() + " decided they would rather not, and traded you an item instead."));
                EntityItem drop = new EntityItem(playerIn.field_70170_p);
                drop.func_92058_a(new ItemStack(ItemInit.velocitizedFemur));
                drop.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v);
                playerIn.field_70170_p.func_72838_d(drop);
            }
            stack.func_190918_g(1);
            return true;
        }
        return true;
    }
}
