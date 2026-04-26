package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.projectile.entity.EntityAcidicConcoction;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemAcidicConcoction.class */
public class ItemAcidicConcoction extends ItemCooldown {
    public ItemAcidicConcoction(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                EntityAcidicConcoction shot = new EntityAcidicConcoction(worldIn, playerIn);
                shot.setThrower(playerIn);
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.0f, 0.0f);
                worldIn.func_72838_d(shot);
            }
            playerIn.func_184185_a(SoundEvents.field_187578_au, 1.0f, 1.0f);
            playerIn.func_70690_d(new PotionEffect(PotionInit.ACIDIC, 1000));
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 4000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Throws flasks that release acidic gel.");
        tooltip.add(TextFmt.Green + "Acidic Gel deals damage and releases poisonous bubbles.");
        tooltip.add(TextFmt.Yellow + "Flask explosion gives nearby enemies Ultraheavy III.");
    }
}
