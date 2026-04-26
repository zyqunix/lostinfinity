package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemGhostHunter extends ItemCooldownSword implements IMaxAttack {
    public ItemGhostHunter(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_DEVIANTWEP);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        IMaxAttack.dealMaxHealth(attacker, target, 10);
        return true;
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            RayTraceResult mop = func_77621_a(worldIn, playerIn, true);
            if (mop == null || mop.field_72313_a != RayTraceResult.Type.BLOCK) {
                return ActionResult.newResult(EnumActionResult.PASS, playerIn.func_184586_b(handIn));
            }
            BlockPos start = playerIn.func_180425_c();
            BlockPos result = mop.func_178782_a();
            boolean flag = worldIn.func_175623_d(result.func_177982_a(0, 1, 0)) && worldIn.func_175623_d(result.func_177982_a(0, 2, 0));
            double dist = playerIn.func_70011_f(result.func_177958_n(), result.func_177956_o(), result.func_177952_p());
            if (flag) {
                worldIn.func_184133_a((EntityPlayer) null, result, SoundInit.ITEM_GHOSTHUNTER, SoundCategory.MASTER, 2.0f, 1.0f);
                if (!worldIn.field_72995_K) {
                    playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
                    playerIn.func_70634_a(result.func_177958_n(), result.func_177956_o() + 1, result.func_177952_p());
                    for (EntityLivingBase near_entity : worldIn.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(result.func_177982_a(-3, -3, -3), result.func_177982_a(3, 3, 3)))) {
                        if (near_entity.func_110124_au() != playerIn.func_110124_au()) {
                            int denomDamage = Math.round(10 - Math.round(8.0d * (dist / 20.0d)));
                            if (denomDamage < 2) {
                                denomDamage = 2;
                            }
                            IMaxAttack.dealMaxHealth(playerIn, near_entity, denomDamage);
                        }
                    }
                } else {
                    int repeats = 10 + Math.abs(Math.round((float) dist));
                    double xdiff = start.func_177958_n() - result.func_177958_n();
                    double ydiff = start.func_177956_o() - result.func_177956_o();
                    double zdiff = start.func_177952_p() - result.func_177952_p();
                    for (int part = 0; part < repeats; part++) {
                        for (int k = 0; k < 3; k++) {
                            worldIn.func_175688_a(EnumParticleTypes.SMOKE_LARGE, ((double) start.func_177958_n()) + (((-xdiff) / ((double) repeats)) * ((double) part)), ((double) start.func_177956_o()) + (((-ydiff) / ((double) repeats)) * ((double) part)), ((double) start.func_177952_p()) + (((-zdiff) / ((double) repeats)) * ((double) part)), (worldIn.field_73012_v.nextDouble() - 0.5d) * 0.1d, 0.0d, (worldIn.field_73012_v.nextDouble() - 0.5d) * 0.1d, new int[0]);
                        }
                    }
                }
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    protected RayTraceResult func_77621_a(World worldIn, EntityPlayer playerIn, boolean useLiquids) {
        float f = playerIn.field_70125_A;
        float f1 = playerIn.field_70177_z;
        double d0 = playerIn.field_70165_t;
        double d1 = playerIn.field_70163_u + ((double) playerIn.func_70047_e());
        double d2 = playerIn.field_70161_v;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.func_76134_b(((-f1) * 0.017453292f) - 3.1415927f);
        float f3 = MathHelper.func_76126_a(((-f1) * 0.017453292f) - 3.1415927f);
        float f4 = -MathHelper.func_76134_b((-f) * 0.017453292f);
        float f5 = MathHelper.func_76126_a((-f) * 0.017453292f);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3d vec3d1 = vec3d.func_72441_c(((double) f6) * 40.0d, ((double) f5) * 40.0d, ((double) f7) * 40.0d);
        return worldIn.func_147447_a(vec3d, vec3d1, useLiquids, !useLiquids, false);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Deals 10% Max Health Damage");
        tooltip.add(TextFmt.Dark_Purple + "Right click to teleport up to 30 blocks.");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Light_Purple) + "On Arrival:");
        tooltip.add(TextFmt.Red + "Deal up to 50% Max Health Damage to Very Close Enemies");
        tooltip.add(TextFmt.Italic + "Damage based on distance travelled.");
    }
}
