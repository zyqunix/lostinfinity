package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IMaxReducible;
public class ItemDualDefender extends Item implements IMaxReducible {
    public ItemDualDefender(String regName) {
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    @Override // xol.lostinfinity.item.classify.IMaxReducible
    public float reduceMaxDamage(EntityPlayer player, boolean isMainHand, float damage, float reductionMultiplier, ItemStack stack) {
        if (player.func_110143_aJ() == player.func_110138_aP()) {
            float f = reductionMultiplier - 0.5f;
        } else {
            float f2 = reductionMultiplier - 0.1f;
        }
        return reductionMultiplier;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Italic + "Grants effects while held in offhand.");
        tooltip.add(TextFmt.Gold + "While on full life:");
        tooltip.add(TextFmt.Green + "Take 50% Reduced Max Health Damage");
        tooltip.add(TextFmt.Red + "Reflect 25% of Max Health Damage");
        tooltip.add(TextFmt.Gold + "While not on full life:");
        tooltip.add(TextFmt.Green + "Take 10% Reduced Max Health Damage");
        tooltip.add(TextFmt.Red + "Reflect 125% of Max Health Damage");
    }
}
