package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
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
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IHeldTick;
import xol.lostinfinity.projectile.entity.EntityCelestialFire;
import xol.lostinfinity.util.player.HoldItemUtil;
public class ItemAxiomCelestarium extends Item implements IHeldTick {
    public ItemAxiomCelestarium(String regName) {
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("Charge", 0);
        }
        if (!showDurabilityBar(player.func_184586_b(handIn))) {
            stack.func_77978_p().func_74768_a("Charge", 0);
            if (!worldIn.field_72995_K) {
                worldIn.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.MAGIC_WEAPON_3, SoundCategory.MASTER, 2.0f, 1.0f);
                for (int yVelo = 1; yVelo < 3; yVelo++) {
                    float startPos = (float) ((((double) (yVelo - 1)) * 3.141592653589793d) / 8.0d);
                    double x0 = player.field_70165_t;
                    double y0 = player.field_70163_u;
                    double z0 = player.field_70161_v;
                    float f = startPos;
                    while (true) {
                        float angle = f;
                        if (angle <= 6.283185307179586d + ((double) startPos)) {
                            EntityCelestialFire shot = new EntityCelestialFire(worldIn, player);
                            shot.func_70107_b(x0, y0, z0);
                            double velocity_x = 5.0d * Math.cos(angle);
                            double velocity_z = 5.0d * Math.sin(angle);
                            shot.setThrower(player);
                            shot.calculateVelocity(velocity_x, (-yVelo) * 1.5f, velocity_z);
                            shot.func_184538_a(player, shot.field_70125_A, shot.field_70177_z, 0.0f, 1.3f, 0.0f);
                            worldIn.func_72838_d(shot);
                            f = (float) (((double) angle) + 0.3141592653589793d);
                        }
                    }
                }
            }
        }
        return super.func_77659_a(worldIn, player, handIn);
    }
    public boolean showDurabilityBar(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("Charge", 0);
        }
        int chrg = stack.func_77978_p().func_74762_e("Charge");
        return chrg != 25;
    }
    public double getDurabilityForDisplay(ItemStack stack) {
        if (showDurabilityBar(stack)) {
            double result = stack.func_77978_p().func_74762_e("Charge");
            double fin = result / 25.0d;
            return 1.0d - Math.pow(fin, 1.0d);
        }
        return 1.0d;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "When held destroys incoming projectiles.");
        tooltip.add(TextFmt.Gold + "At maximum charge, activate to fire a ring of 50% max health damage attacks.");
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("Charge")) {
            int charges = stack.func_77978_p().func_74762_e("Charge");
            tooltip.add(TextFmt.Aqua + "Charges: " + charges + "/25");
        }
    }
    @Override // xol.lostinfinity.item.classify.IHeldTick
    public void heldTick(EntityPlayer player, EnumHand hand, ItemStack stack) {
        HoldItemUtil.axiomProtection(player);
    }
}
