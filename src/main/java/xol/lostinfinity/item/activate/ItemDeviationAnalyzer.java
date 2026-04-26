package xol.lostinfinity.item.activate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantAmalgam;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantCreeper;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantEnderman;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantShulker;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSkeleton;
public class ItemDeviationAnalyzer extends Item {
    public ItemDeviationAnalyzer(String regName) {
        func_77637_a(TabsInit.TAB_AUXMATS);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        boolean found_creeper = false;
        boolean found_skeleton = false;
        boolean found_shulker = false;
        boolean found_enderman = false;
        for (EntityLiving li : worldIn.func_72872_a(EntityLiving.class, playerIn.func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
            if (li instanceof EntityDeviantCreeper) {
                found_creeper = true;
            } else if (li instanceof EntityDeviantSkeleton) {
                found_skeleton = true;
            } else if (li instanceof EntityDeviantShulker) {
                found_shulker = true;
            } else if (li instanceof EntityDeviantEnderman) {
                found_enderman = true;
            }
        }
        if (found_creeper && found_skeleton && found_shulker && found_enderman) {
            playerIn.func_184185_a(SoundInit.SCANNER, 1.0f, 1.0f);
            playerIn.func_184611_a(handIn, new ItemStack(ItemInit.deviationAnalyzerCharged, 1));
            if (!worldIn.field_72995_K) {
                for (EntityLiving li2 : worldIn.func_72872_a(EntityLiving.class, playerIn.func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
                    if ((li2 instanceof EntityDeviantCreeper) || (li2 instanceof EntityDeviantSkeleton) || (li2 instanceof EntityDeviantShulker) || (li2 instanceof EntityDeviantEnderman)) {
                        li2.func_70106_y();
                    }
                }
                EntityDeviantAmalgam dev = new EntityDeviantAmalgam(worldIn);
                dev.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u + 2.0d, playerIn.field_70161_v);
                worldIn.func_72838_d(dev);
            }
        } else if (worldIn.field_72995_K) {
            playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "DETECTION: Creature missing!"));
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Use near the following all at once to charge it:");
        tooltip.add(TextFmt.Green + "Deviant Creeper");
        tooltip.add(TextFmt.Dark_Gray + "Deviant Skeleton");
        tooltip.add(TextFmt.White + "Deviant Shulker");
        tooltip.add(TextFmt.Light_Purple + "Deviant Enderman");
    }
}
