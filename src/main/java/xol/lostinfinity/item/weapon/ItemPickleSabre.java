package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IHotbarDeath;
import xol.lostinfinity.mob.entity.misc.EntityPickleMan;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemPickleSabre.class */
public class ItemPickleSabre extends ItemCooldownSword implements IMaxAttack, IHotbarDeath, ICustomRaytrace {
    public ItemPickleSabre(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        int pstacks;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (!worldIn.field_72995_K && (pstacks = stack.func_77978_p().func_74762_e("PickleStacks")) > 0) {
            CustomRayTraceResult trace_result = entityTrace(worldIn, playerIn, 45, EntityPickleMan.class);
            if (trace_result != null && trace_result.getResultEntity() != null) {
                EntityPickleMan hitPickle = trace_result.getResultEntity();
                hitPickle.addTrueDamageToAttacks(0.025f * pstacks);
                hitPickle.setMyScale(1.0f + (0.2f * pstacks));
                hitPickle.addExtraLives(pstacks);
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_1, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
        }
        playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        IMaxAttack.dealMaxHealth((Entity) attacker, target, 1, 2.0f);
        return true;
    }

    @Override // xol.lostinfinity.item.weapon.ItemCooldownSword
    protected int getCooldown() {
        return 20000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Deals 200% max health damage per hit.");
        tooltip.add(TextFmt.Red + "When taking max health damage that would kill you, block it and:");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Green) + "Heal to full.");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Gold) + "Store the stacks of pickle power in your sword.");
        tooltip.add(TextFmt.Underline + "Gain a stack for each 5% of your max life blocked.");
        tooltip.add(TextFmt.Green + "You can consume stacks by buffing a Pickle Man:");
        tooltip.add(TextFmt.Bold + "Per Stack:" + TextFmt.Reset + TextFmt.Aqua + " Adds 2.5% Health As True Damage on hit.");
        tooltip.add(TextFmt.Bold + "Per Stack:" + TextFmt.Reset + TextFmt.Aqua + " Gain an extra life.");
        tooltip.add(TextFmt.Bold + "Per Stack:" + TextFmt.Reset + TextFmt.Aqua + " Increase in size.");
    }

    @Override // xol.lostinfinity.item.classify.IHotbarDeath
    public boolean playedKilled(ItemStack stack, EntityPlayer player, Entity attacker, float damageDealt) {
        World world = player.field_70170_p;
        if (!showDurabilityBar(stack)) {
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            if (!world.field_72995_K) {
                player.func_70691_i(player.func_110138_aP());
                world.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.MAGIC_WEAPON_14, SoundCategory.MASTER, 1.0f, 1.0f);
                float cappedDamage = Math.min(damageDealt, player.func_110138_aP());
                int stacksToAdd = MathHelper.func_76141_d(cappedDamage / (player.func_110138_aP() / 20.0f));
                stack.func_77978_p().func_74768_a("PickleStacks", Math.min(stack.func_77978_p().func_74762_e("PickleStacks") + stacksToAdd, 20));
                player.func_145747_a(new TextComponentString(TextFmt.Green + "You have " + stack.func_77978_p().func_74762_e("PickleStacks") + " stacks."));
                return false;
            }
            return false;
        }
        return true;
    }
}
