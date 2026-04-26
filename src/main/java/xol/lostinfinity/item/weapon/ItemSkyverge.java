package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemSkyverge.class */
public class ItemSkyverge extends ItemCooldown implements IMaxAttack {
    public ItemSkyverge(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack) && !worldIn.field_72995_K) {
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_191244_bn, SoundCategory.MASTER, 2.0f, 1.0f);
            for (EntityPlayer e : worldIn.func_72872_a(EntityPlayer.class, playerIn.func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                if (e.func_110124_au() != playerIn.func_110124_au()) {
                    e.func_70024_g(Math.signum(playerIn.field_70165_t - e.field_70165_t) * 0.7d, 0.5d + (Math.signum(playerIn.field_70163_u - e.field_70163_u) * 0.5d), Math.signum(playerIn.field_70161_v - e.field_70161_v) * 0.7d);
                    e.field_70133_I = true;
                    IMaxAttack.dealMaxHealth(playerIn, e, 8);
                }
            }
            for (EntityLiving li : worldIn.func_72872_a(EntityLiving.class, playerIn.func_174813_aQ().func_72314_b(30.0d, 30.0d, 30.0d))) {
                if (li.func_110124_au() != playerIn.func_110124_au()) {
                    li.func_70024_g(Math.signum(playerIn.field_70165_t - li.field_70165_t) * (-2.5d), 0.5d, Math.signum(playerIn.field_70161_v - li.field_70161_v) * (-2.5d));
                    li.field_70133_I = true;
                }
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 1000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "When activated, creates 2 gusts of wind:");
        tooltip.add(TextFmt.Red + "A gust that forces creatures away.");
        tooltip.add(TextFmt.Blue + "A gust that damages and carries nearby players towards you.");
    }
}
