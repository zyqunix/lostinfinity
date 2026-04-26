package xol.lostinfinity.item.weapon.droid;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.projectile.entity.EntityDroidBall;
import xol.lostinfinity.projectile.entity.EntityDroidSucker;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/droid/ItemDroidRelocatorStorage.class */
public class ItemDroidRelocatorStorage extends ItemCooldown {
    public ItemDroidRelocatorStorage(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            ItemStack stack = playerIn.func_184586_b(handIn);
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
                stack.func_77978_p().func_74768_a("Mode", 0);
                stack.func_77978_p().func_74768_a("Stored", 0);
            }
            if (stack.func_77978_p().func_74762_e("Mode") == 0) {
                if (!worldIn.field_72995_K) {
                    EntityDroidSucker shot = new EntityDroidSucker(worldIn, playerIn);
                    shot.setThrower(playerIn);
                    shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                    worldIn.func_72838_d(shot);
                }
                stack.func_77978_p().func_74768_a("Mode", 1);
            } else {
                if (!worldIn.field_72995_K) {
                    EntityDroidBall shot2 = new EntityDroidBall(worldIn, playerIn, stack.func_77978_p().func_74762_e("Stored"));
                    shot2.setThrower(playerIn);
                    shot2.setGrade(getSummonGrade());
                    shot2.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                    if (playerIn.func_70093_af()) {
                        shot2.setAttacking(false);
                    }
                    worldIn.func_72838_d(shot2);
                }
                stack.func_77978_p().func_74768_a("Stored", 0);
                stack.func_77978_p().func_74768_a("Mode", 0);
            }
            playerIn.func_184185_a(SoundInit.MAGIC_WEAPON_1, 1.0f, 1.0f);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    protected int getSummonGrade() {
        return 0;
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 3000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Red + "Shoots a projectile that stores all nearby droids.");
        tooltip.add(TextFmt.Gray + "Can then shoot a projectile to deploy all stored droids.");
        if (stack.func_77942_o()) {
            if (stack.func_77978_p().func_74762_e("Mode") == 1) {
                tooltip.add(TextFmt.Red + "Stored: " + stack.func_77978_p().func_74762_e("Stored"));
            } else {
                tooltip.add(TextFmt.Red + "Ready to fire storage projectile!");
            }
        }
        tooltip.add(TextFmt.Gray + "Normal Fire: Relocated Droids are Aggressive");
        tooltip.add(TextFmt.Gray + "Shift Fire: Relocated Droids are Reactive");
        tooltip.add(TextFmt.Aqua + "Can be upgraded.");
    }
}
