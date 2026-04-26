package xol.lostinfinity.item.activate;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.projectile.entity.EntityStrappedFirework;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemExplosiveStrappedFirework.class */
public class ItemExplosiveStrappedFirework extends ItemCooldown {
    public ItemExplosiveStrappedFirework(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        func_77625_d(16);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (playerIn.field_70163_u <= worldIn.func_175645_m(playerIn.func_180425_c()).func_177956_o() + 3) {
                if (!worldIn.field_72995_K) {
                    EntityStrappedFirework shot = new EntityStrappedFirework(worldIn, playerIn);
                    shot.setThrower(playerIn);
                    worldIn.func_72838_d(shot);
                }
                playerIn.func_184586_b(handIn).func_190918_g(1);
                playerIn.func_184185_a(SoundEvents.field_187626_cN, 1.0f, 1.0f);
            } else if (!worldIn.field_72995_K) {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Fireworks must be launched close to the ground."));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 400;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Explosive vials attached to a firework.");
        tooltip.add(TextFmt.Gold + "Good for killing airborne creatures.");
    }
}
