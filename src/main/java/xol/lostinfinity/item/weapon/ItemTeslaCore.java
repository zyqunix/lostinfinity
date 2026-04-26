package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.projectile.entity.EntityVoltaicIonizerAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemTeslaCore.class */
public class ItemTeslaCore extends ItemCooldown {
    public ItemTeslaCore(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                EntityVoltaicIonizerAttack attack = new EntityVoltaicIonizerAttack(worldIn);
                attack.setCaster(playerIn);
                attack.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u + ((double) (playerIn.field_70131_O / 2.0f)), playerIn.field_70161_v);
                worldIn.func_72838_d(attack);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.ELECTRIC_SHOCK, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 1000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Sends out branching lightning from your position.");
        tooltip.add(TextFmt.Aqua + "Lightning can branch from nearby entities to further entities.");
        tooltip.add(TextFmt.Italic + "This effect can occur several times, to a very far distance.");
        tooltip.add(TextFmt.Red + "Deals 50% Max Health Damage + 20% Per Bounce");
    }
}
