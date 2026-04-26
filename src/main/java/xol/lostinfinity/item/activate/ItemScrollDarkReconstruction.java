package xol.lostinfinity.item.activate;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.mob.entity.murk.EntityWhisper;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemScrollDarkReconstruction.class */
public class ItemScrollDarkReconstruction extends Item implements ICustomRaytrace {
    public ItemScrollDarkReconstruction(String regName) {
        setRegistryName(regName).func_77655_b(regName).func_77637_a(TabsInit.TAB_AUXMATS).func_77625_d(1);
        ItemInit.ITEMS.add(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K) {
            if (worldIn.field_73011_w.func_186058_p() == DimensionInit.infiniteMurk) {
                List<EntityWhisper> sacrifices = playerIn.field_70170_p.func_72872_a(EntityWhisper.class, new AxisAlignedBB(playerIn.func_180425_c()).func_186662_g(25.0d));
                if (sacrifices.size() >= 6) {
                    EntityWhisper leader = sacrifices.get(0);
                    leader.setLeader(true);
                    for (int i = 1; i < sacrifices.size(); i++) {
                        EntityWhisper sacrfice = sacrifices.get(i);
                        sacrfice.setSacrificed(true);
                    }
                } else {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "You need 6 Whispers to begin the ritual."));
                    playerIn.func_184185_a(SoundEvents.field_187646_bt, 1.0f, 1.0f);
                }
            } else {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "You must be in the Infinite Murk to cast this ritual."));
                playerIn.func_184185_a(SoundEvents.field_187646_bt, 1.0f, 1.0f);
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "This scroll can be used within the Infinite Murk");
        tooltip.add(TextFmt.Gold + "to summon a horrific, ancient terror.");
        tooltip.add("");
        tooltip.add(TextFmt.Aqua + "Reagents: " + TextFmt.Red + "6 Whispers");
    }
}
