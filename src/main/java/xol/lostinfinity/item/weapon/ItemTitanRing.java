package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.projectile.entity.EntityTitanRing;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemTitanRing.class */
public class ItemTitanRing extends ItemCooldown implements IModeSelect {
    public ItemTitanRing(String regName) {
        super(regName);
        func_77625_d(5);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                EntityTitanRing shot = new EntityTitanRing(worldIn, playerIn);
                shot.setThrower(playerIn);
                int throwMode = stack.func_77978_p().func_74762_e("throw_mode");
                if (throwMode == 1) {
                    shot.setHighVelo();
                }
                shot.shootNoVel(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 2.0f + (throwMode * 3), 0.0f);
                worldIn.func_72838_d(shot);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_19, SoundCategory.PLAYERS, 0.2f + (0.5f * throwMode), 0.6f + (worldIn.field_73012_v.nextFloat() * 0.4f) + (0.5f * throwMode));
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_20, SoundCategory.PLAYERS, 0.2f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("throw_mode", 0);
        }
        int throwMode = stack.func_77978_p().func_74762_e("throw_mode");
        if (throwMode == 0) {
            player.func_145747_a(new TextComponentString(TextFmt.Italic + "High velocity throws enabled."));
            stack.func_77978_p().func_74768_a("throw_mode", 1);
        } else {
            player.func_145747_a(new TextComponentString(TextFmt.Italic + "High velocity throws disabled."));
            stack.func_77978_p().func_74768_a("throw_mode", 0);
        }
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 50;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Throws out an unfolding, returning ring.");
        tooltip.add(TextFmt.Gold + "Can deploy as many rings as you can keep in the air.");
        tooltip.add(TextFmt.Red + "Deals 50% Health True Damage");
        tooltip.add(TextFmt.Yellow + "Hits increase shock level on target, adding 25% Health True Damage per stack");
        tooltip.add(TextFmt.Italic + "Can enable high velocity throws, throwing faster rings but dealing 30% less damage.");
        tooltip.add(TextFmt.Dark_Aqua + "Aquatic, Darkborn");
    }
}
