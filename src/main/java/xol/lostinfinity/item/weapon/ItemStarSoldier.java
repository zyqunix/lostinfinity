package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.projectile.entity.EntityStarBlast;
import xol.lostinfinity.util.data.CustomRayTraceResult;
public class ItemStarSoldier extends ItemCooldown implements ICustomHoldPose, ICustomRaytrace {
    public ItemStarSoldier(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                EntityStarBlast shot = new EntityStarBlast(worldIn, playerIn);
                shot.setThrower(playerIn);
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 3.0f, 5.0f);
                worldIn.func_72838_d(shot);
                CustomRayTraceResult trace_result = simpleBlockTrace(worldIn, playerIn, 5);
                if (trace_result != null) {
                    Vec3d resultVector = trace_result.getGrabbedVector();
                    if (playerIn.func_70011_f(resultVector.field_72450_a, resultVector.field_72448_b, resultVector.field_72449_c) < 5.0d) {
                        playerIn.func_70024_g(Math.signum(resultVector.field_72450_a - playerIn.field_70165_t) * (-2.5d), 1.0d, Math.signum(resultVector.field_72449_c - playerIn.field_70161_v) * (-2.5d));
                        playerIn.field_70133_I = true;
                        playerIn.func_70690_d(new PotionEffect(PotionInit.ADRENALINE, 120, 2));
                    }
                }
            }
            playerIn.func_184185_a(SoundEvents.field_187578_au, 1.0f, 1.0f);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 300;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Shoots a projectile that creates a large explosion on impact.");
        tooltip.add(TextFmt.Italic + "Explosion deals 75% max health damage.");
        tooltip.add(TextFmt.Green + "Can rocket jump using the explosion, gaining Adrenaline III.");
    }
}
