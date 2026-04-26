package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemAbyssalCurse extends ItemSword implements IMaxAttack {
    public ItemAbyssalCurse(String regName) {
        super(Item.ToolMaterial.WOOD);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (IMaxAttack.dealMaxHealth(attacker, target, 1).didSuccessfulHit()) {
            if (target.func_70644_a(PotionInit.BLOOD_TOXIN)) {
                int level = target.func_70660_b(PotionInit.BLOOD_TOXIN).func_76458_c() + 1;
                target.func_70690_d(new PotionEffect(PotionInit.BLOOD_TOXIN, 200, Math.min(9, level)));
                return true;
            }
            target.func_70690_d(new PotionEffect(PotionInit.BLOOD_TOXIN, 200));
            return true;
        }
        return true;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Red + "Deals 100% Max Health Damage");
        tooltip.add(TextFmt.Dark_Green + "Applies Blood Toxin.");
        tooltip.add(TextFmt.Gold + "If Blood Toxin is already applied, up the strength (max 10).");
    }
}
