package xol.lostinfinity.item.misc;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.mob.entity.misc.EntityPlasmaSlicer;
public class ItemPlasmaEmitter extends ItemCooldown {
    public ItemPlasmaEmitter(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K) {
            for (EntityPlasmaSlicer slicer : worldIn.func_72872_a(EntityPlasmaSlicer.class, new AxisAlignedBB(playerIn.func_180425_c()).func_186662_g(3.0d))) {
                if (slicer.getEmitter().func_110124_au().equals(playerIn) || slicer.getReceiver().func_110124_au().equals(playerIn)) {
                    slicer.func_70106_y();
                    break;
                }
            }
        }
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                Iterator it = worldIn.func_72872_a(EntityPlayer.class, new AxisAlignedBB(playerIn.func_180425_c()).func_186662_g(15.0d)).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    EntityPlayer player = (EntityPlayer) it.next();
                    if (!player.func_110124_au().equals(playerIn.func_110124_au()) && player.func_184614_ca().func_77973_b() == ItemInit.plasmaReceiver) {
                        EntityPlasmaSlicer slicer2 = new EntityPlasmaSlicer(worldIn);
                        slicer2.setEmitter(playerIn);
                        slicer2.setReceiver(player);
                        slicer2.setStableDist(playerIn.func_70032_d(player));
                        slicer2.func_70634_a(playerIn.field_70165_t, playerIn.field_70163_u + (((double) playerIn.field_70131_O) / 2.0d), playerIn.field_70161_v);
                        worldIn.func_72838_d(slicer2);
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.ELECTRIC_WOOSH, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                        break;
                    }
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 10000;
    }
}
