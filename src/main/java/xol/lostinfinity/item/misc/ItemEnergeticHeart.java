package xol.lostinfinity.item.misc;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IHeldTick;
public class ItemEnergeticHeart extends ItemCooldown implements IHeldTick {
    public ItemEnergeticHeart(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_20, SoundCategory.MASTER, 1.5f, 1.0f);
            }
            playerIn.func_70690_d(new PotionEffect(PotionInit.UNLEASHING, 400, 3));
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 30000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "When drunk, grants Unleashing IV.");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Yellow) + "Unleashing releases bursts of energy from your body.");
        tooltip.add(TextFmt.Red + "Bursts deal 33% Max Health. Deals True damage to targets below 33% health.");
    }
    @Override // xol.lostinfinity.item.classify.IHeldTick
    public void heldTick(EntityPlayer player, EnumHand hand, ItemStack stack) {
        if (!player.field_70170_p.field_72995_K && player.field_70173_aa % 60 == 0) {
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.HEART_BEAT, SoundCategory.PLAYERS, 1.5f, 1.0f);
        }
    }
}
