package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.Reference;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemBladesOfConvergence.class */
public class ItemBladesOfConvergence extends ItemSword implements IMaxAttack {
    private static final String MODE = "mode";

    public ItemBladesOfConvergence(String regName) {
        super(Item.ToolMaterial.WOOD);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
        func_185043_a(new ResourceLocation(Reference.MODID, MODE), (stack, worldIn, entityIn) -> {
            return isMode(stack) ? 1.0f : 0.0f;
        });
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (isMode(stack)) {
            for (EntityLivingBase nearPl : attacker.field_70170_p.func_72872_a(EntityLivingBase.class, attacker.func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                if (nearPl.func_110124_au() != attacker.func_110124_au() && nearPl.field_70163_u > 100.0d) {
                    boolean killed = IMaxAttack.dealMaxHealth(attacker, nearPl, 1).wasTargetKilled();
                    if (killed && (nearPl instanceof EntityPlayer) && !attacker.field_70170_p.field_72995_K) {
                        attacker.func_145747_a(new TextComponentString(TextFmt.Green + "Your blade has consumed " + nearPl.func_70005_c_()));
                    }
                }
            }
        } else if (attacker instanceof EntityPlayerMP) {
            attacker.field_70159_w = 0.0d;
            attacker.field_70181_x = 4.0d;
            attacker.field_70179_y = 0.0d;
            attacker.field_70133_I = true;
            ((EntityPlayerMP) attacker).field_71135_a.func_147359_a(new SPacketEntityVelocity(attacker));
            for (EntityPlayerMP entityPlayerMP : attacker.field_70170_p.func_72872_a(EntityLivingBase.class, attacker.func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                if (entityPlayerMP.func_70005_c_() != attacker.func_70005_c_()) {
                    ((EntityLivingBase) entityPlayerMP).field_70159_w = 0.0d;
                    ((EntityLivingBase) entityPlayerMP).field_70181_x = 4.0d;
                    ((EntityLivingBase) entityPlayerMP).field_70179_y = 0.0d;
                    ((EntityLivingBase) entityPlayerMP).field_70133_I = true;
                    if (entityPlayerMP instanceof EntityPlayer) {
                        ((EntityPlayerMP) attacker).field_71135_a.func_147359_a(new SPacketEntityVelocity(entityPlayerMP));
                        entityPlayerMP.field_71135_a.func_147359_a(new SPacketEntityVelocity(attacker));
                        entityPlayerMP.field_71135_a.func_147359_a(new SPacketEntityVelocity(entityPlayerMP));
                    }
                }
            }
        }
        toggleMode(stack);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "A weapon that switches sides when you attack.");
        tooltip.add(TextFmt.Green + "Blade of Destiny: All nearby players are pulled into the air with you.");
        tooltip.add(TextFmt.Red + "Blade of Ultimatum: Kills all nearby targets over 100 height.");
    }

    private void toggleMode(ItemStack stack) {
        setMode(stack, !isMode(stack));
    }

    private void setMode(ItemStack stack, boolean flag) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74757_a(MODE, flag);
    }

    private boolean isMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74757_a(MODE, false);
            return false;
        }
        return stack.func_77978_p().func_74767_n(MODE);
    }
}
