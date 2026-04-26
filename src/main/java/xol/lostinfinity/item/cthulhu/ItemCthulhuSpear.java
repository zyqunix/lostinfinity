package xol.lostinfinity.item.cthulhu;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuSpear;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/cthulhu/ItemCthulhuSpear.class */
public class ItemCthulhuSpear extends ItemChanneling {
    public ItemCthulhuSpear(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public ActionResult<ItemStack> chargeStart(World worldIn, EntityPlayer player, EnumHand handIn, ItemStack stack) {
        if (!worldIn.field_72995_K) {
            EntityCthulhuSpear spear = new EntityCthulhuSpear(worldIn);
            spear.setOwner(player);
            spear.updatePosition();
            worldIn.func_72838_d(spear);
        }
        return super.chargeStart(worldIn, player, handIn, stack);
    }

    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public void chargeTick(World worldIn, EntityPlayer player, EnumHand hand, ItemStack stack, int chargeTime) {
        if (chargeTime % 10 == 0) {
            worldIn.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.MAGIC_WEAPON_15, SoundCategory.PLAYERS, 1.0f, 0.4f + (0.15f * MathHelper.func_76141_d(chargeTime / 10)));
        }
    }
}
