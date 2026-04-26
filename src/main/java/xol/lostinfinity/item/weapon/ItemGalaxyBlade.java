package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
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
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemGalaxyBlade extends ItemSword implements IMaxAttack {
    private String wep;
    public ItemGalaxyBlade(String regName) {
        super(Item.ToolMaterial.WOOD);
        this.wep = "";
        func_77637_a(TabsInit.TAB_GALAXY);
        setRegistryName(regName);
        func_77655_b(regName);
        this.wep = regName;
        ItemInit.ITEMS.add(this);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        switch (this.wep) {
            case "moonglow_blade":
                if (attacker.func_110143_aJ() >= 4.0f * (attacker.func_110138_aP() / 5.0f)) {
                    IMaxAttack.dealMaxHealth(attacker, target, 3);
                    break;
                }
                break;
            case "novacron_blade":
                if (target.func_110143_aJ() <= target.func_110138_aP() / 2.0f) {
                    IMaxAttack.dealMaxHealth(attacker, target, 4);
                    break;
                }
                break;
            case "aurorus_blade":
                if (target.func_110143_aJ() >= target.func_110138_aP() / 2.0f) {
                    IMaxAttack.dealMaxHealth(attacker, target, 4);
                    break;
                }
                break;
            case "starfire_blade":
                if (attacker.func_110143_aJ() <= attacker.func_110138_aP() / 5.0f) {
                    IMaxAttack.dealMaxHealth(attacker, target, 3);
                    break;
                }
                break;
        }
        return true;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        switch (this.wep) {
            case "moonglow_blade":
                tooltip.add(TextFmt.Gold + "While Above 80% Life:");
                tooltip.add(TextFmt.Aqua + "Deal 33% Max Health Damage");
                break;
            case "novacron_blade":
                tooltip.add(TextFmt.Gold + "To Targets Below 50% Health:");
                tooltip.add(TextFmt.Light_Purple + "Deal 25% Max Health Damage");
                break;
            case "aurorus_blade":
                tooltip.add(TextFmt.Gold + "To Targets Above 50% Health:");
                tooltip.add(TextFmt.Green + "Deal 25% Max Health Damage");
                break;
            case "starfire_blade":
                tooltip.add(TextFmt.Gold + "While Below 20% Life:");
                tooltip.add(TextFmt.Yellow + "Deal 33% Max Health Damage");
                break;
        }
    }
}
