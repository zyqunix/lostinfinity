package xol.lostinfinity.item.weapon.droid;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.projectile.entity.EntityDroidBall;
import xol.lostinfinity.projectile.entity.EntityDroidSucker;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/droid/ItemDroidConstructorMK3.class */
public class ItemDroidConstructorMK3 extends ItemDroidRelocatorMK2 implements ISwitchModels {
    public ItemDroidConstructorMK3(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setModelSwitch("primarytype", this, 2);
    }

    @Override // xol.lostinfinity.item.weapon.droid.ItemDroidRelocatorStorage
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("primarytype_data", 0);
            stack.func_77978_p().func_74768_a("Mode", 0);
        }
        if (playerIn.func_70093_af()) {
            int mode = stack.func_77978_p().func_74762_e("Mode");
            int primary = stack.func_77978_p().func_74762_e("primarytype_data");
            if (atMaxMode(primary, mode)) {
                int newPrimary = stack.func_77978_p().func_74762_e("primarytype_data") + 1;
                if (newPrimary == 2) {
                    newPrimary = 0;
                }
                stack.func_77978_p().func_74768_a("primarytype_data", newPrimary);
                stack.func_77978_p().func_74768_a("Mode", 0);
            } else {
                stack.func_77978_p().func_74768_a("Mode", mode + 1);
            }
            if (!worldIn.field_72995_K) {
                playerIn.func_145747_a(new TextComponentString(getModeDescription(stack.func_77978_p().func_74762_e("primarytype_data"), stack.func_77978_p().func_74762_e("Mode"))));
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187556_aj, SoundCategory.MASTER, 2.0f, 1.0f);
            }
        } else if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (stack.func_77978_p().func_74762_e("primarytype_data") == 0) {
                if (!worldIn.field_72995_K) {
                    EntityDroidBall shot = new EntityDroidBall(worldIn, playerIn);
                    shot.setThrower(playerIn);
                    shot.setGrade(2);
                    shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                    if (stack.func_77978_p().func_74762_e("Mode") == 1) {
                        shot.setAttacking(false);
                    }
                    worldIn.func_72838_d(shot);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_2, SoundCategory.MASTER, 2.0f, 1.0f);
                }
            } else {
                int mode2 = stack.func_77978_p().func_74762_e("Mode");
                if (!worldIn.field_72995_K) {
                    if (mode2 == 0) {
                        EntityDroidSucker shot2 = new EntityDroidSucker(worldIn, playerIn);
                        shot2.setThrower(playerIn);
                        shot2.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                        worldIn.func_72838_d(shot2);
                    } else {
                        EntityDroidBall shot3 = new EntityDroidBall(worldIn, playerIn, stack.func_77978_p().func_74762_e("Stored"));
                        shot3.setThrower(playerIn);
                        shot3.setGrade(2);
                        shot3.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                        if (mode2 == 2) {
                            shot3.setAttacking(false);
                        }
                        worldIn.func_72838_d(shot3);
                        stack.func_77978_p().func_74768_a("Stored", 0);
                    }
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_1, SoundCategory.MASTER, 2.0f, 1.0f);
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return new ActionResult<>(EnumActionResult.PASS, playerIn.func_184586_b(handIn));
    }

    @Override // xol.lostinfinity.item.weapon.droid.ItemDroidRelocatorStorage, xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 300;
    }

    private boolean atMaxMode(int primary, int mode) {
        return mode == primary + 1;
    }

    private String getModeDescription(int primary, int mode) {
        String msg;
        if (primary == 0) {
            if (mode == 0) {
                msg = TextFmt.Red + "Constructing: Aggressive Droids";
            } else {
                msg = TextFmt.Blue + "Constructing: Reactive Droids";
            }
        } else if (mode == 0) {
            msg = TextFmt.Gold + "Relocator Enabled";
        } else if (mode == 1) {
            msg = TextFmt.Red + "Deploying Stored Droids: Aggressive";
        } else {
            msg = TextFmt.Blue + "Deploying Stored Droids: Reactive";
        }
        return msg;
    }

    @Override // xol.lostinfinity.item.weapon.droid.ItemDroidRelocatorStorage
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Summoned droids are extremely powerful.");
        tooltip.add(TextFmt.Italic + "Shift-Activate To Cycle Modes");
        if (stack.func_77942_o()) {
            tooltip.add(TextFmt.Gray + "Stored Droids: " + stack.func_77978_p().func_74762_e("Stored"));
        }
    }
}
