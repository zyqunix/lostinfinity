package xol.lostinfinity.item.misc;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IHotbarTick;
import xol.lostinfinity.projectile.entity.EntityExothermite;
import xol.lostinfinity.util.damagesource.DeathMessage;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemExothermite.class */
public class ItemExothermite extends Item implements IHotbarTick {
    public ItemExothermite(String regName) {
        func_77637_a(TabsInit.TAB_AUXMATS);
        setRegistryName(regName);
        func_77655_b(regName);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!worldIn.field_72995_K) {
            EntityExothermite shot = new EntityExothermite(worldIn, playerIn);
            shot.setThrower(playerIn);
            shot.setStack(stack.func_77946_l());
            shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 2.0f, 0.0f);
            worldIn.func_72838_d(shot);
        }
        stack.func_190918_g(1);
        playerIn.func_184185_a(SoundEvents.field_187578_au, 1.0f, 1.0f);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.classify.IHotbarTick
    public void hotbarTick(EntityPlayer player, int itemSlot, ItemStack stack) {
        if (showDurabilityBar(stack)) {
            long progress = stack.func_77978_p().func_74763_f("progress");
            UUID holderID = player.func_110124_au();
            if (stack.func_77978_p().func_74764_b("lastholder")) {
                if (!holderID.equals(stack.func_77978_p().func_186857_a("lastholder"))) {
                    stack.func_77978_p().func_186854_a("lastholder", holderID);
                    stack.func_77978_p().func_74768_a("damageramp", 0);
                }
            } else {
                stack.func_77978_p().func_186854_a("lastholder", holderID);
            }
            if (progress < getDuration()) {
                stack.func_77978_p().func_74772_a("progress", progress + 1);
                if (player.field_70173_aa % 40 == 0 && !player.field_70170_p.field_72995_K) {
                    int ramp = stack.func_77978_p().func_74762_e("damageramp");
                    float hpMulti = 0.05f * ramp;
                    for (EntityPlayer nearPlayer : player.field_70170_p.func_72872_a(EntityPlayer.class, player.func_174813_aQ().func_186662_g(6.0d))) {
                        float newHp = nearPlayer.func_110143_aJ() - (nearPlayer.func_110138_aP() * hpMulti);
                        if (newHp > 0.0f) {
                            nearPlayer.func_70606_j(newHp);
                        } else {
                            nearPlayer.func_70606_j(0.0f);
                            DeathMessage.broadcastDeathMessage(player.func_184102_h(), TextFmt.Red + nearPlayer.func_70005_c_() + " melted.");
                        }
                    }
                    stack.func_77978_p().func_74768_a("damageramp", Math.min(15, ramp + 1));
                    return;
                }
                return;
            }
            if (!player.field_70170_p.field_72995_K) {
                player.func_145747_a(new TextComponentString("This exothermite has cooled."));
                player.field_71071_by.func_70299_a(itemSlot, new ItemStack(ItemInit.exothermite, 1));
                player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundEvents.field_187659_cY, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
        }
    }

    public boolean showDurabilityBar(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74772_a("progress", 0L);
        }
        long progress = stack.func_77978_p().func_74763_f("progress");
        return progress <= ((long) getDuration());
    }

    protected static int getDuration() {
        return 1000;
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        if (showDurabilityBar(stack)) {
            double progress = stack.func_77978_p().func_74763_f("progress");
            double fin = progress / ((double) getDuration());
            return 1.0d - Math.pow(fin, 1.0d);
        }
        return 1.0d;
    }
}
