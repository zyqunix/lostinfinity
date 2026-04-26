package xol.lostinfinity.item.misc;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IModeSelect;
public class ItemBrewOfForbiddenArts extends ItemCooldown implements IModeSelect {
    public ItemBrewOfForbiddenArts(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (playerIn.func_70644_a(PotionInit.PLANESPLIT)) {
                playerIn.func_184589_d(PotionInit.PLANESPLIT);
            } else {
                if (!worldIn.field_72995_K) {
                    int mode = stack.func_77978_p().func_74762_e("drink_mode");
                    playerIn.func_70690_d(new PotionEffect(PotionInit.PLANESPLIT, 72000, mode));
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187664_bz, SoundCategory.MASTER, 1.0f, 1.0f);
                }
                stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 5000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "When drunk, grants you Plane-Split.");
        tooltip.add(TextFmt.Red + "Your health pool is limited while Plane-Split.");
        tooltip.add(TextFmt.Aqua + "Plane-Split grants a chance to dodge ANY damage.");
        tooltip.add(TextFmt.Italic + "Can select level of Plane-Split to grant.");
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("drink_mode", 0);
        }
        int new_mode = stack.func_77978_p().func_74762_e("drink_mode") + 1;
        if (new_mode >= 9) {
            new_mode = 0;
        }
        stack.func_77978_p().func_74768_a("drink_mode", new_mode);
        player.func_145747_a(new TextComponentString(TextFmt.Italic + "Life Cap: " + (90 - (new_mode * 10)) + "%, Dodge: " + (10 + (new_mode * 10)) + "%"));
        player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundEvents.field_187556_aj, SoundCategory.MASTER, 1.0f, 1.0f);
    }
}
