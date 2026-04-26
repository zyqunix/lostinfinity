package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.projectile.entity.EntityCreepingVineArrow;
public class ItemCreepingVine extends ItemCooldown implements ICustomHoldPose {
    public ItemCreepingVine(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                EntityCreepingVineArrow shot = new EntityCreepingVineArrow(worldIn);
                Vec3d pos = playerIn.func_174824_e(1.0f).func_178787_e(playerIn.func_70040_Z());
                shot.func_70107_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
                shot.setThrower(playerIn);
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.7f, 0.0f);
                worldIn.func_72838_d(shot);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187578_au, SoundCategory.PLAYERS, 1.5f, 1.0f);
            }
            startCooldown(stack);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 1000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Shoots a projectile that explodes into pods on impact.");
        tooltip.add(TextFmt.Italic + "The pods travel curve randomly and are able to pass to terrain.");
        tooltip.add(TextFmt.Green + "Each Pod Deals 75% Health True Damage");
        tooltip.add(TextFmt.Bold + "Pods take 5 lives off of Multi-Life Creatures");
    }
}
