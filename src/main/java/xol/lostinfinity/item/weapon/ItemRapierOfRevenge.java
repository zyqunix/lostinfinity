package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IHotbarDeath;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemRapierOfRevenge.class */
public class ItemRapierOfRevenge extends ItemCooldownSword implements IMaxAttack, IHotbarDeath {
    public ItemRapierOfRevenge(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!isRevengeMode(attacker.func_184614_ca())) {
            IMaxAttack.dealMaxHealth((Entity) attacker, target, 5, 4.0f);
            return true;
        }
        IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP() * 0.8f);
        return true;
    }

    public boolean isRevengeMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74772_a("lastUse", 0L);
        }
        long lastUse = stack.func_77978_p().func_74763_f("lastUse");
        long maxDelay = getCooldown() / 3;
        return System.currentTimeMillis() - lastUse <= maxDelay;
    }

    @Override // xol.lostinfinity.item.weapon.ItemCooldownSword
    protected int getCooldown() {
        return 33000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Deals 80% max health damage per hit.");
        tooltip.add(TextFmt.Aqua + "When taking max health damage that would kill you:");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Green) + "Heal to full.");
        tooltip.add(TextFmt.Green + "Grant the Racing Heart buff.");
        tooltip.add(TextFmt.Red + "Deal true damage while the cooldown bar is below 33%.");
    }

    @Override // xol.lostinfinity.item.classify.IHotbarDeath
    public boolean playedKilled(ItemStack stack, EntityPlayer player, Entity attacker, float damageDealt) {
        World world = player.field_70170_p;
        if (!showDurabilityBar(stack)) {
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            if (!world.field_72995_K) {
                player.func_70691_i(player.func_110138_aP());
                world.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.MAGIC_WEAPON_14, SoundCategory.MASTER, 1.0f, 1.0f);
                player.func_70690_d(new PotionEffect(PotionInit.RACING_HEART, 400));
                return false;
            }
            return false;
        }
        return true;
    }
}
