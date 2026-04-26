package xol.lostinfinity.item.misc;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IHotbarDeath;
import xol.lostinfinity.projectile.entity.EntityDebtCollectorEffect;
public class ItemDebtCollector extends ItemCooldown implements IHotbarDeath {
    public ItemDebtCollector(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 15000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "A very magical scythe.");
        tooltip.add(TextFmt.Gold + "When taking lethal damage, if there is a close enemy make them pay your debt instead.");
        tooltip.add(TextFmt.Red + "Paying a debt deals 200% life as true damage.");
        tooltip.add(TextFmt.Red + "Paying a debt takes 5 lives off creatures with multiple lives.");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Green) + "When a debt is paid, you heal to full health.");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Gold) + "Gain 3 seconds of immunity while a debt is paid.");
    }
    @Override // xol.lostinfinity.item.classify.IHotbarDeath
    public boolean playedKilled(ItemStack stack, EntityPlayer player, Entity attacker, float damageDealt) {
        World world = player.field_70170_p;
        if (!showDurabilityBar(stack)) {
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            EntityLivingBase collected = null;
            for (EntityLivingBase nearCreature : world.func_72872_a(EntityLivingBase.class, player.func_174813_aQ().func_72314_b(15.0d, 5.0d, 15.0d))) {
                if (collected == null || (player.func_70032_d(nearCreature) < player.func_70032_d(collected) && collected.func_110124_au() != player.func_110124_au())) {
                    if (!nearCreature.func_110124_au().equals(player.func_110124_au())) {
                        collected = nearCreature;
                    }
                }
            }
            if (collected != null) {
                if (!world.field_72995_K) {
                    EntityDebtCollectorEffect effect = new EntityDebtCollectorEffect(world);
                    effect.setCreator(player);
                    effect.setTargetPos(collected.func_174791_d());
                    effect.setTargetVec(collected.func_70040_Z());
                    effect.setTarget(collected);
                    effect.func_70107_b(collected.field_70165_t, collected.field_70163_u + ((double) collected.field_70131_O) + 0.6d, collected.field_70161_v);
                    world.func_72838_d(effect);
                    world.func_184133_a((EntityPlayer) null, collected.func_180425_c(), SoundInit.EXECUTE_EFFECT_2, SoundCategory.PLAYERS, 1.0f, 0.9f + (0.2f * world.field_73012_v.nextFloat()));
                    player.func_70691_i(player.func_110138_aP());
                    player.func_70690_d(new PotionEffect(PotionInit.IRONHEART, 120));
                    return false;
                }
                return false;
            }
            return true;
        }
        return true;
    }
}
