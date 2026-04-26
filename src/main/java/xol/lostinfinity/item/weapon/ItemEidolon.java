package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.misc.EntityEidolonMist;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemEidolon extends ItemSword implements IMaxAttack {
    public ItemEidolon(String regName) {
        super(Item.ToolMaterial.DIAMOND);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        World world = attacker.field_70170_p;
        if (!world.field_72995_K) {
            IMaxAttack.dealMaxHealth((Entity) attacker, target, 2, 1.0f);
            EntityEidolonMist mist = new EntityEidolonMist(world, attacker);
            mist.func_70634_a(target.field_70165_t, target.field_70163_u + ((double) (target.field_70131_O / 2.0f)), target.field_70161_v);
            mist.addInitialTarget(target);
            mist.func_184538_a(attacker, attacker.field_70125_A, attacker.field_70177_z, 0.0f, 0.1f, 0.0f);
            world.func_72838_d(mist);
            return true;
        }
        return true;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Melee hits create a mist.");
        tooltip.add(TextFmt.Red + "The Mist Deals 300% Health True Damage");
        tooltip.add(TextFmt.Italic + "The mist seeks out more targets beyond the first.");
        tooltip.add(TextFmt.Gold + "The Mist Unleashes Undead Phantoms");
        tooltip.add(TextFmt.Dark_Aqua + "Darkborn");
    }
}
