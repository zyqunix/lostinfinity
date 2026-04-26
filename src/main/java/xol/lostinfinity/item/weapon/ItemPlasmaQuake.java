package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
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
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.mob.entity.misc.EntityPlasmaBomb;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemPlasmaQuake.class */
public class ItemPlasmaQuake extends ItemCooldown implements IMaxAttack, ICustomRaytrace {
    public ItemPlasmaQuake(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                CustomRayTraceResult trace_result = forwardTrace(worldIn, playerIn, 2);
                Vec3d dir = trace_result.getResultVector().func_178788_d(playerIn.func_174791_d());
                Vec3d dir2 = dir.func_178788_d(new Vec3d(0.0d, dir.field_72448_b, 0.0d)).func_72432_b();
                EntityPlasmaBomb bomb = new EntityPlasmaBomb(worldIn);
                bomb.setCreator(playerIn.func_110124_au());
                int height = worldIn.func_189649_b(playerIn.func_180425_c().func_177958_n() + (((int) dir2.field_72450_a) * 2), playerIn.func_180425_c().func_177952_p() + (((int) dir2.field_72449_c) * 2));
                bomb.func_70107_b(((double) playerIn.func_180425_c().func_177958_n()) + (dir2.field_72450_a * 2.0d), height + 1, ((double) playerIn.func_180425_c().func_177952_p()) + (dir2.field_72449_c * 2.0d));
                bomb.setTimer(0);
                bomb.setDir(dir2);
                bomb.setRepeats(30);
                worldIn.func_72838_d(bomb);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 1000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Creates a Plasma Quake along the ground.");
        tooltip.add(TextFmt.Aqua + "Direction of quake is the direction you are facing.");
        tooltip.add(TextFmt.Gold + "Deals 100% Health As True Damage");
    }
}
