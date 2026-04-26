package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemVitalitus.class */
public class ItemVitalitus extends ItemSword implements IMaxAttack {
    public ItemVitalitus(String regName) {
        super(Item.ToolMaterial.WOOD);
        func_77637_a(TabsInit.TAB_DEVIANTWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if ((target instanceof EntityPlayer) && !attacker.field_70170_p.field_72995_K) {
            int target_percentage = Math.round((target.func_110143_aJ() / target.func_110138_aP()) * 100.0f);
            int attacker_percentage = Math.round((attacker.func_110143_aJ() / attacker.func_110138_aP()) * 100.0f);
            target.func_70606_j((target.func_110138_aP() * attacker_percentage) / 100.0f);
            attacker.func_70606_j((attacker.func_110138_aP() * target_percentage) / 100.0f);
            if (attacker.func_110143_aJ() < 0.0f && (attacker instanceof EntityPlayer)) {
                ((EntityPlayer) attacker).field_71071_by.func_70436_m();
            }
            if (target.func_110143_aJ() < 0.0f && (target instanceof EntityPlayer)) {
                ((EntityPlayer) target).field_71071_by.func_70436_m();
                return true;
            }
            return true;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "On player hit:");
        tooltip.add(TextFmt.Gold + "Swaps your health percentage with the enemy.");
    }
}
