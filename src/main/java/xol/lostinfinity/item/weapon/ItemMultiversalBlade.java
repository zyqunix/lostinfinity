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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.mob.entity.misc.EntityMultiverseGhost;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemMultiversalBlade.class */
public class ItemMultiversalBlade extends ItemCooldown implements IMaxAttack, ICustomRaytrace {
    public ItemMultiversalBlade(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 600;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                CustomRayTraceResult result = complexTrace(worldIn, playerIn, 20, null, 0, 0, true, EntityLivingBase.class, 1, 0.3f);
                Vec3d target = result.getResultVector();
                float rYaw = playerIn.field_70177_z * 0.017453292f;
                float x = -MathHelper.func_76126_a(rYaw);
                float z = MathHelper.func_76134_b(rYaw);
                float yawOffset = (-1.0f) + (2.0f * worldIn.field_73012_v.nextFloat());
                Vec3d spawn = playerIn.func_174791_d().func_178788_d(new Vec3d(((double) x) * 2.5d, -0.5d, ((double) z) * 2.5d).func_178785_b((float) (((double) yawOffset) + (Math.signum(yawOffset) < 0.0f ? -1.14d : 1.14d))));
                Vec3d dir = target.func_178786_a(0.0d, 1.5d, 0.0d).func_178788_d(spawn).func_72432_b();
                EntityMultiverseGhost ghost = new EntityMultiverseGhost(worldIn, dir.func_186678_a(0.10000000149011612d));
                ghost.setCopiedPlay(playerIn);
                ghost.func_70080_a(spawn.field_72450_a, spawn.field_72448_b, spawn.field_72449_c, ((float) MathHelper.func_181159_b(-dir.field_72450_a, dir.field_72449_c)) * 57.29578f, 0.0f);
                worldIn.func_72838_d(ghost);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MULTIVERSAL_BLADE, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
            }
            playerIn.func_184609_a(handIn);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Call upon yourself from another universe to attack for you.");
        tooltip.add(TextFmt.Red + "Your alternate self deals 75% Health True Damage");
        tooltip.add(TextFmt.Dark_Aqua + "Darkborn");
    }
}
