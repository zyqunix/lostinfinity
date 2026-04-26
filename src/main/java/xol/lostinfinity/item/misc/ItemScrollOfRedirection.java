package xol.lostinfinity.item.misc;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.item.classify.IDimensionSwitch;
import xol.lostinfinity.mob.entity.murk.EntityScreamer;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemScrollOfRedirection.class */
public class ItemScrollOfRedirection extends ItemBasic implements IDimensionSwitch {
    public ItemScrollOfRedirection(String regName) {
        super(regName, TabsInit.TAB_AUXMATS);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K && worldIn.field_73011_w.func_186058_p() == DimensionType.OVERWORLD) {
            DimensionActivator.transferEntityWithCoords(playerIn, DimensionInit.shadowSea, randomCoordinate(playerIn.field_70170_p.field_73012_v), 160.0d, randomCoordinate(playerIn.field_70170_p.field_73012_v));
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    private static double randomCoordinate(Random rand) {
        return ((-0.5d) + rand.nextDouble()) * 10000.0d;
    }

    @Override // xol.lostinfinity.item.classify.IDimensionSwitch
    public void onDimensionSwitch(EntityPlayer player, ItemStack stack) {
        if (!player.field_70170_p.field_72995_K) {
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.RAPID_TELEPORT, SoundCategory.HOSTILE, 1.0f, 1.0f);
            player.func_70634_a(player.field_70165_t, ((double) player.field_70170_p.func_189649_b((int) player.field_70165_t, (int) player.field_70161_v)) + 0.5d, player.field_70161_v);
            EntityScreamer screamer = new EntityScreamer(player.field_70170_p);
            screamer.func_70107_b(player.field_70165_t, player.field_70163_u + 15.0d, player.field_70161_v);
            screamer.setInOcean(true);
            screamer.setLivesCount(50);
            player.field_70170_p.func_72838_d(screamer);
        }
        stack.func_190918_g(1);
    }
}
