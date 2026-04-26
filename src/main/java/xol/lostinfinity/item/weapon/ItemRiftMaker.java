package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IMaxNullable;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemRiftMaker extends ItemCooldown implements IMaxNullable, IMaxAttack {
    public ItemRiftMaker(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 3000;
    }
    @Override // xol.lostinfinity.item.classify.IMaxNullable
    public float nullableReaction(EntityPlayer player, boolean isMainHand, float originalDamage, float newDamage, ItemStack stack) {
        if (!showDurabilityBar(stack)) {
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0f, 0.8f + (player.field_70170_p.field_73012_v.nextFloat() * 0.4f));
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            return 0.0f;
        }
        for (EntityLivingBase near_creature : player.field_70170_p.func_72872_a(EntityLivingBase.class, player.func_174813_aQ().func_72314_b(10.0d, 7.0d, 10.0d))) {
            if (!near_creature.func_110124_au().equals(player.func_110124_au())) {
                IMaxAttack.dealMaxHealth(player, near_creature, 10);
                player.func_70691_i(near_creature.func_110138_aP() * 0.1f);
            }
        }
        return newDamage;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "When held, blocks the next max health damage hit.");
        tooltip.add(TextFmt.Gold + "Upon blocking, this goes on a short cooldown.");
        tooltip.add(TextFmt.Red + "While on cooldown, taking a hit causes you to lifesteal 10% max health from everything nearby.");
    }
}
