package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.IHotbarTick;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.entity.misc.EntityStormBomb;
public class ItemStormBauble extends Item implements IHotbarTick, ICustomHoldPose {
    public ItemStormBauble(String regName) {
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    @Override // xol.lostinfinity.item.classify.IHotbarTick
    public void hotbarTick(EntityPlayer player, int slot, ItemStack stack) {
        World world = player.field_70170_p;
        if (!world.field_72995_K && player.field_70173_aa - player.func_142013_aG() < 120 && player.func_142013_aG() != 0) {
            AxisAlignedBB checkBox = new AxisAlignedBB(player.func_180425_c().func_177963_a(-5.0d, -5.0d, -5.0d), player.func_180425_c().func_177963_a(5.0d, 5.0d, 5.0d));
            for (IEntityOwnable iEntityOwnable : world.func_72872_a(EntityLivingBase.class, checkBox)) {
                if (!iEntityOwnable.func_110124_au().equals(player.func_110124_au()) && (!(iEntityOwnable instanceof IEntityOwnable) || !iEntityOwnable.func_184753_b().equals(player.func_110124_au()))) {
                    if (!(iEntityOwnable instanceof EntityImmaterial)) {
                        Vec3d dir = iEntityOwnable.func_174791_d().func_178788_d(player.func_174791_d());
                        dir.func_72432_b();
                        iEntityOwnable.func_70024_g(dir.field_72450_a / 10.0d, (dir.field_72448_b / 10.0d) + 0.1d, dir.field_72449_c / 10.0d);
                        ((EntityLivingBase) iEntityOwnable).field_70133_I = true;
                    }
                }
            }
            if (player.field_70173_aa % 5 == 0) {
                double angle = world.field_73012_v.nextDouble() * 3.141592653589793d * 2.0d;
                double x = (Math.cos(angle) * 8.0d) + player.field_70165_t;
                double z = (Math.sin(angle) * 8.0d) + player.field_70161_v;
                double y = player.field_70163_u + ((double) (player.field_70131_O / 2.0f)) + 1.0d;
                EntityStormBomb bomb = new EntityStormBomb(world);
                bomb.setCreator(player.func_110124_au());
                bomb.func_70107_b(x, y, z);
                world.func_72838_d(bomb);
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "While in the hotbar, if you've taken damage recently:");
        tooltip.add(TextFmt.Gold + "Create storm bombs that deal 50% health true damage around you.");
        tooltip.add(TextFmt.Gold + "Push living entities away.");
    }
}
