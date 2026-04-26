package xol.lostinfinity.item.misc;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
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
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IHotbarDeath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemGuardianElixir.class */
public class ItemGuardianElixir extends ItemCooldown implements IHotbarDeath {
    public ItemGuardianElixir(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187664_bz, SoundCategory.MASTER, 1.0f, 1.0f);
            }
            playerIn.func_70690_d(new PotionEffect(PotionInit.IRONHEART, 300));
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 45000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "When drunk, grants the Ironheart buff.");
        tooltip.add(TextFmt.Aqua + "When taking max health damage that would kill you, activate this elixir instead.");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Dark_Red) + "Ironheart grants immunity to max health damage.");
    }

    @Override // xol.lostinfinity.item.classify.IHotbarDeath
    public boolean playedKilled(ItemStack stack, EntityPlayer player, Entity attacker, float damageDealt) {
        World world = player.field_70170_p;
        if (!showDurabilityBar(stack)) {
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            if (!world.field_72995_K) {
                world.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundEvents.field_187664_bz, SoundCategory.MASTER, 1.0f, 1.0f);
                player.func_70690_d(new PotionEffect(PotionInit.IRONHEART, 300));
                return false;
            }
            return false;
        }
        return true;
    }
}
