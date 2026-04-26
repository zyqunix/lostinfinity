package xol.lostinfinity.item.activate;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.mob.entity.misc.EntityUnstableMerchant;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemGeoCorrelator.class */
public class ItemGeoCorrelator extends ItemCooldown {
    private double lastDistance;

    public ItemGeoCorrelator(String regName) {
        super(regName);
        this.lastDistance = 9.9999997952E10d;
        func_77625_d(1);
        func_77637_a(TabsInit.TAB_AUXMATS);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            ItemStack stack = playerIn.func_184586_b(handIn);
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
                stack.func_77978_p().func_74768_a("GameState", 0);
            }
            if (!worldIn.field_72995_K && worldIn.field_73011_w.func_186058_p() == DimensionType.OVERWORLD) {
                int state = stack.func_77978_p().func_74762_e("GameState");
                if (state == 0) {
                    EntityUnstableMerchant newMerchant = new EntityUnstableMerchant(worldIn);
                    newMerchant.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v);
                    worldIn.func_72838_d(newMerchant);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187791_eX, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Gold + "Unstable Merchant: " + TextFmt.Reset + TextFmt.Italic + "Greetings fellow traveller! Pass me your new geocorrelator and I'll set your next coordinates."));
                } else {
                    double needX = stack.func_77978_p().func_74769_h("FindX");
                    double needY = stack.func_77978_p().func_74769_h("FindY");
                    double needZ = stack.func_77978_p().func_74769_h("FindZ");
                    double newDist = playerIn.func_70011_f(needX, needY, needZ);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187750_dc, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                    if (newDist < 7.0d) {
                        EntityUnstableMerchant newMerchant2 = new EntityUnstableMerchant(worldIn);
                        newMerchant2.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v);
                        newMerchant2.setMode(1);
                        worldIn.func_72838_d(newMerchant2);
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187791_eX, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Gold + "Unstable Merchant: " + TextFmt.Reset + TextFmt.Italic + "You found me! Pass me the correlator."));
                    } else if (newDist >= this.lastDistance) {
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Aqua + "Colder"));
                    } else if (newDist < 50.0d) {
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "Very Warm!"));
                    } else {
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "Warmer"));
                    }
                    this.lastDistance = newDist;
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 700;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        boolean flag = false;
        if (!stack.func_77942_o() || stack.func_77978_p().func_74762_e("GameState") == 0) {
            flag = true;
        }
        if (flag) {
            tooltip.add(TextFmt.Gold + "Activate to call an interdimensional traveller to your location.");
            return;
        }
        tooltip.add(TextFmt.Gold + "Activate to track the Unstable Merchant's location.");
        if (stack.func_77978_p().func_74762_e("GameState") > 1) {
            tooltip.add(TextFmt.Italic + "Traveller, I apologize for what happened back there.");
        }
    }
}
