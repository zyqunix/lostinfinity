package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.mob.entity.misc.EntityWormholePortal;
public class ItemWormholeRifle extends ItemChanneling implements IModeSelect {
    public ItemWormholeRifle(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        int newType;
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (!showDurabilityBar(stack) && !ItemChanneling.isChanneling(player, stack)) {
            int type = stack.func_77978_p().func_74762_e("usetype_data");
            if (type < 4) {
                newType = type + 1;
            } else {
                newType = 0;
            }
            stack.func_77978_p().func_74768_a("usetype_data", newType);
            if (!player.field_70170_p.field_72995_K) {
                player.func_145747_a(new TextComponentString("Portal Range: " + ((newType + 1) * 10)));
            }
        }
    }
    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public ActionResult<ItemStack> chargeStart(World worldIn, EntityPlayer player, EnumHand handIn, ItemStack stack) {
        if (!worldIn.field_72995_K) {
            EntityWormholePortal firstPortal = new EntityWormholePortal(worldIn);
            Vec3d look = player.func_70040_Z().func_72432_b();
            Vec3d sideVec = player.func_70040_Z().func_178785_b(1.5707964f);
            int range = (stack.func_77978_p().func_74762_e("usetype_data") + 1) * 10;
            Vec3d firstPortalPos = player.func_174791_d().func_72441_c((-sideVec.field_72450_a) / 3.0d, ((double) player.field_70131_O) / 1.1d, (-sideVec.field_72449_c) / 3.0d).func_72441_c(look.field_72450_a, 0.0d, look.field_72449_c);
            Vec3d secondPortalPos = firstPortalPos.func_178787_e(look.func_186678_a(range));
            firstPortal.func_70634_a(firstPortalPos.field_72450_a, firstPortalPos.field_72448_b, firstPortalPos.field_72449_c);
            firstPortal.setCaster(player);
            firstPortal.setPlayerLook(player.func_70040_Z());
            worldIn.func_72838_d(firstPortal);
            EntityWormholePortal secondPortal = new EntityWormholePortal(worldIn);
            secondPortal.func_70634_a(secondPortalPos.field_72450_a, secondPortalPos.field_72448_b, secondPortalPos.field_72449_c);
            secondPortal.setCaster(player);
            secondPortal.setPlayerLook(player.func_70040_Z());
            secondPortal.setShoot();
            worldIn.func_72838_d(secondPortal);
        }
        return super.chargeStart(worldIn, player, handIn, stack);
    }
    @Override // xol.lostinfinity.item.basics.ItemChanneling, xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 500;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Shoots rapid fire wormhole travelling bullets.");
        tooltip.add(TextFmt.Italic + "Wormhole distance can be set by adjusting item mode.");
        tooltip.add(TextFmt.Red + "Each bullet does 50% Max Health Damage");
        tooltip.add(TextFmt.Italic + "Deals True Damage to targets below 25% Life.");
        tooltip.add(TextFmt.Dark_Aqua + "Darkborn");
    }
}
