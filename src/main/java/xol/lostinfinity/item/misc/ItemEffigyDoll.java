package xol.lostinfinity.item.misc;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.projectile.entity.EntityEffigyShot;
public class ItemEffigyDoll extends ItemBasic {
    public ItemEffigyDoll(String regName) {
        super(regName, TabsInit.TAB_AUXMATS);
        func_77625_d(1);
    }
    public boolean func_111207_a(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target instanceof EntityPlayer) {
            ItemStack realStack = playerIn.func_184586_b(hand);
            EntityPlayer targetPlayer = (EntityPlayer) target;
            if (!realStack.func_77942_o()) {
                realStack.func_77982_d(new NBTTagCompound());
            }
            realStack.func_77978_p().func_186854_a("targetID", targetPlayer.func_110124_au());
            if (!playerIn.field_70170_p.field_72995_K) {
                playerIn.func_145747_a(new TextComponentString("Stored " + targetPlayer.func_70005_c_() + " in the effigy doll"));
                return true;
            }
            return true;
        }
        return true;
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (stack.func_77942_o() && stack.func_77978_p().func_186855_b("targetID")) {
            if (!worldIn.field_72995_K) {
                EntityEffigyShot shot = new EntityEffigyShot(worldIn, playerIn);
                shot.setStack(stack);
                shot.setThrower(playerIn);
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.0f, 0.0f);
                worldIn.func_72838_d(shot);
            }
            stack.func_190918_g(1);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Red + "A creepy doll. Use on a player to store their likeness.");
        if (stack.func_77942_o() && stack.func_77978_p().func_186855_b("targetID")) {
            UUID targetID = stack.func_77978_p().func_186857_a("targetID");
            EntityPlayer target = worldIn.func_152378_a(targetID);
            if (target != null) {
                tooltip.add(TextFmt.Red + "Stored: " + target.func_70005_c_());
            }
        }
    }
}
