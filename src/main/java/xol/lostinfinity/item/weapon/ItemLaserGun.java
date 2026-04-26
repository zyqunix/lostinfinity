package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.projectile.entity.EntityLaserGunBeam;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemLaserGun.class */
public class ItemLaserGun extends ItemChanneling {
    public ItemLaserGun(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setMaxChargeTime(34);
    }

    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public ActionResult<ItemStack> chargeStart(World worldIn, EntityPlayer player, EnumHand handIn, ItemStack stack) {
        if (!worldIn.field_72995_K) {
            EntityLaserGunBeam laserBeam = new EntityLaserGunBeam(worldIn);
            double dist = laserBeam.getDist();
            Vec3d targetPos = player.func_70040_Z().func_186678_a(dist).func_178787_e(player.func_174791_d());
            laserBeam.setOwner(player);
            laserBeam.setTargetPos(targetPos);
            laserBeam.setStack(stack);
            Vec3d lookVec = player.func_70040_Z().func_178785_b(1.5707964f);
            laserBeam.func_70107_b(player.field_70165_t - (lookVec.field_72450_a / 2.0d), player.field_70163_u + (((double) player.field_70131_O) / 1.8d), player.field_70161_v - (lookVec.field_72449_c / 2.0d));
            worldIn.func_72838_d(laserBeam);
            worldIn.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.MAGIC_WEAPON_20, SoundCategory.PLAYERS, 0.7f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
        }
        return super.chargeStart(worldIn, player, handIn, stack);
    }

    @Override // xol.lostinfinity.item.basics.ItemChanneling, xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 2000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Italic + "The Super-Mega-Ultimate Laser Gun");
        tooltip.add(TextFmt.Gold + "Charges up a high damage, terrain-piercing laser.");
        tooltip.add(TextFmt.Red + "Also Deals 50% Health True Damage");
        tooltip.add(TextFmt.Underline + "Also takes off 50% of remaining lives from multi-life creatures (max 10)");
    }
}
