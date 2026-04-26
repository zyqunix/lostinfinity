package xol.lostinfinity.item.weapon;

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
import xol.lostinfinity.projectile.entity.EntityCarrierProjectile;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemCometShower.class */
public class ItemCometShower extends ItemCooldown {
    public ItemCometShower(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                EntityCarrierProjectile shot = new EntityCarrierProjectile(worldIn, playerIn);
                shot.setThrower(playerIn);
                shot.setForm((byte) 1);
                if (!playerIn.func_70093_af()) {
                    shot.setRemainingLife((byte) 40);
                } else {
                    shot.setRemainingLife((byte) 15);
                }
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                worldIn.func_72838_d(shot);
            }
            playerIn.func_184185_a(SoundInit.ITEM_STARSTORM, 1.0f, 1.0f);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Yellow + "Fires a projectile that creates a bunch of large comets.");
        tooltip.add(TextFmt.Yellow + "Shift Fire for fast detonation.");
        tooltip.add(TextFmt.Aqua + "Comets deal:");
        tooltip.add(TextFmt.Red + "50% Max Health to creatures");
        tooltip.add(TextFmt.Red + "75% Max Health to players");
    }
}
