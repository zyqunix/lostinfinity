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
public class ItemSonicShield extends Item implements IMaxReducible {
    public ItemSonicShield(String regName) {
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "When held in your mainhand, reduces max health damage taken by 10%.");
        tooltip.add(TextFmt.Gold + "When below 33% health, reduce max health damage by 50% instead.");
        tooltip.add(TextFmt.Aqua + "Provides immunity to sonic attacks.");
    }
    @Override // xol.lostinfinity.item.classify.IMaxReducible
    public float reduceMaxDamage(EntityPlayer player, boolean isMainHand, float damage, float reductionMultiplier, ItemStack stack) {
        float newMulti;
        if (player.func_110143_aJ() > player.func_110138_aP() / 3.0f) {
            newMulti = reductionMultiplier - 0.1f;
        } else {
            newMulti = reductionMultiplier - 0.5f;
        }
        return newMulti;
    }
}
