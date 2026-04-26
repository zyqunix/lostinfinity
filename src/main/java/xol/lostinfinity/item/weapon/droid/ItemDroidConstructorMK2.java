package xol.lostinfinity.item.weapon.droid;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.projectile.entity.EntityDroidBall;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/droid/ItemDroidConstructorMK2.class */
public class ItemDroidConstructorMK2 extends ItemCooldown {
    public ItemDroidConstructorMK2(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                EntityDroidBall shot = new EntityDroidBall(worldIn, playerIn);
                shot.setThrower(playerIn);
                shot.setGrade(1);
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                if (playerIn.func_70093_af()) {
                    shot.setAttacking(false);
                }
                worldIn.func_72838_d(shot);
            }
            playerIn.func_184185_a(SoundInit.MAGIC_WEAPON_2, 1.0f, 1.0f);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 300;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Summoned droids have the following:");
        tooltip.add(TextFmt.Red + "Increased Damage");
        tooltip.add(TextFmt.Aqua + "Increased Speed");
        tooltip.add(TextFmt.Green + "% Health Regeneration");
        tooltip.add(TextFmt.Light_Purple + "Dashing Ability");
    }
}
