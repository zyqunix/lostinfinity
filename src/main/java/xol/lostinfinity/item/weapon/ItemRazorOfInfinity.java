package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.projectile.entity.EntityRecursor;
public class ItemRazorOfInfinity extends Item implements ISwitchModels {
    public ItemRazorOfInfinity(String regName) {
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        func_77625_d(1);
        setModelSwitch("empty", this, 2);
        ItemInit.ITEMS.add(this);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int mode = stack.func_77978_p().func_74762_e("empty_data");
        if (mode == 0) {
            if (!worldIn.field_72995_K) {
                EntityRecursor shot = new EntityRecursor(worldIn, playerIn);
                shot.setThrower(playerIn);
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 2.0f, 0.0f);
                worldIn.func_72838_d(shot);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187737_v, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            stack.func_77978_p().func_74768_a("empty_data", 1);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Throws out a returning disc.");
        tooltip.add(TextFmt.Red + "Deals 100% Max Health True Damage");
        tooltip.add(TextFmt.Dark_Aqua + "Darkborn");
    }
}
