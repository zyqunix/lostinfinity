package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.projectile.entity.EntityWhirlpool;
import xol.lostinfinity.util.data.CustomRayTraceResult;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemWhirlpool.class */
public class ItemWhirlpool extends ItemCooldown implements ICustomRaytrace {
    private static final int RANGE = 5;

    public ItemWhirlpool(String regName, CreativeTabs tab) {
        super(regName);
        func_77637_a(tab);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult crtResult;
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K && (crtResult = forwardTrace(worldIn, playerIn, RANGE)) != null) {
                BlockPos resultPos = crtResult.getResultPos();
                EntityWhirlpool entityWhirlpool = new EntityWhirlpool(worldIn);
                entityWhirlpool.setOwner(playerIn);
                entityWhirlpool.func_70107_b(resultPos.func_177958_n(), resultPos.func_177956_o() + 0.1f, resultPos.func_177952_p());
                worldIn.func_72838_d(entityWhirlpool);
                worldIn.func_184133_a((EntityPlayer) null, crtResult.getResultPos(), SoundInit.MAGIC_WEAPON_7, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 2000;
    }

    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Right-Click to spawn a whirlpool at your targeted location.");
        tooltip.add(TextFmt.Gold + "The whirlpool will slowly grow and pull enemies towards it's center.");
        tooltip.add(TextFmt.Gold + "It deals massive damage to enemies that get too close.");
        tooltip.add(TextFmt.Aqua + "Aquatic");
    }
}
