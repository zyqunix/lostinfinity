package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemKingslayer.class */
public class ItemKingslayer extends ItemSword implements IMaxAttack {
    public ItemKingslayer(String regName) {
        super(Item.ToolMaterial.WOOD);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        float attackerPercentage = attacker.func_110143_aJ() / attacker.func_110138_aP();
        if (target.func_110143_aJ() / target.func_110138_aP() < attackerPercentage) {
            IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP() * 2.0f);
            target.field_70170_p.func_184133_a((EntityPlayer) null, target.func_180425_c(), SoundInit.GALAXYFIRE, SoundCategory.MASTER, 1.0f, 1.0f);
            return true;
        }
        if (attackerPercentage > 0.5f) {
            IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP() * 0.5f);
            return true;
        }
        IMaxAttack.dealMaxHealth((Entity) attacker, target, 1, 2.0f);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Deals 200% Health True Damage if you currently have a higher health % than your target.");
        tooltip.add(TextFmt.Gold + "Otherwise, if you are above 50% life deal 50% Health True Damage");
        tooltip.add(TextFmt.Gold + "Otherwise, deal 200% Max Health Damage");
    }
}
