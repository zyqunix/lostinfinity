package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
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
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemSolarSpear extends ItemSword implements IMaxAttack, ICustomHoldPose {
    public ItemSolarSpear(String regName) {
        super(Item.ToolMaterial.WOOD);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (target.func_70644_a(PotionInit.SHOCKED)) {
            int level = target.func_70660_b(PotionInit.SHOCKED).func_76458_c() + 1;
            target.func_184589_d(PotionInit.SHOCKED);
            IMaxAttack.dealTrueDamage(attacker, target, (level * target.func_110138_aP()) / 10.0f);
            attacker.field_70170_p.func_184133_a((EntityPlayer) null, attacker.func_180425_c(), SoundInit.GENERIC_POP, SoundCategory.PLAYERS, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Deals 10% True Damage Per Stack of Shock on an Enemy");
        tooltip.add(TextFmt.Gold + "Consumes shock on hit.");
    }
}
