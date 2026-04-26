package xol.lostinfinity.item.weapon;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.projectile.entity.EntityWandAttack;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemGalaxyWand extends ItemSword implements IMaxAttack {
    private String wep;
    public ItemGalaxyWand(String regName) {
        super(Item.ToolMaterial.WOOD);
        this.wep = "";
        func_77637_a(TabsInit.TAB_GALAXY);
        setRegistryName(regName);
        func_77655_b(regName);
        this.wep = regName;
        ItemInit.ITEMS.add(this);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            RayTraceResult mop = func_77621_a(worldIn, playerIn, true);
            if (mop == null || mop.field_72313_a != RayTraceResult.Type.BLOCK) {
                return ActionResult.newResult(EnumActionResult.PASS, playerIn.func_184586_b(handIn));
            }
            BlockPos result = mop.func_178782_a();
            AxisAlignedBB aabb = new AxisAlignedBB(result.func_177982_a(-5, -5, -5), result.func_177982_a(5, 5, 5));
            for (EntityLivingBase li : worldIn.func_72872_a(EntityLivingBase.class, aabb)) {
                IMaxAttack.dealMaxHealth(playerIn, li, 8);
            }
            if (!worldIn.field_72995_K) {
                playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
                EntityWandAttack attack = new EntityWandAttack(worldIn);
                attack.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u + 1.0d, playerIn.field_70161_v);
                attack.setAimBlock(result);
                attack.setCaster(playerIn);
                worldIn.func_72838_d(attack);
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
    public boolean showDurabilityBar(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74772_a("lastUse", 0L);
        }
        long lastUse = stack.func_77978_p().func_74763_f("lastUse");
        long maxDelay = TimeUnit.SECONDS.toMillis(3L);
        return System.currentTimeMillis() - lastUse <= maxDelay;
    }
    public double getDurabilityForDisplay(ItemStack stack) {
        if (showDurabilityBar(stack)) {
            double result = System.currentTimeMillis() - stack.func_77978_p().func_74763_f("lastUse");
            double fin = result / TimeUnit.SECONDS.toMillis(3L);
            return 1.0d - Math.pow(fin, 1.0d);
        }
        return 1.0d;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Red + "In several directions fire projectiles.");
        switch (this.wep) {
            case "moonglow_wand":
                tooltip.add(TextFmt.Gold + "While Above 80% Life:");
                tooltip.add(TextFmt.Aqua + "Deal 15% Max Health Damage");
                break;
            case "novacron_wand":
                tooltip.add(TextFmt.Gold + "To Targets Below 50% Health:");
                tooltip.add(TextFmt.Light_Purple + "Deal 10% Max Health Damage");
                break;
            case "aurorus_wand":
                tooltip.add(TextFmt.Gold + "To Targets Above 50% Health:");
                tooltip.add(TextFmt.Green + "Deal 10% Max Health Damage");
                break;
            case "starfire_wand":
                tooltip.add(TextFmt.Gold + "While Below 20% Life:");
                tooltip.add(TextFmt.Yellow + "Deal 20% Max Health Damage");
                break;
        }
    }
}
