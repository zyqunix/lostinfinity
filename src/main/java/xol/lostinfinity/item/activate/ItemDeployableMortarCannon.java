package xol.lostinfinity.item.activate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.mob.entity.misc.EntityMortarCannon;
public class ItemDeployableMortarCannon extends ItemCooldown {
    public ItemDeployableMortarCannon(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                EntityMortarCannon cannon = new EntityMortarCannon(worldIn);
                cannon.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v);
                cannon.setOwner(playerIn);
                cannon.setHomePos(new Vec3d(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v));
                worldIn.func_72838_d(cannon);
            }
            playerIn.func_184185_a(SoundEvents.field_187692_g, 1.0f, 1.0f);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 10000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "A deployable cannon.");
        tooltip.add(TextFmt.Red + "Mortar deals 100% Max Health Damage");
        tooltip.add(TextFmt.Green + "Mortar Applies Shattered III");
    }
}
