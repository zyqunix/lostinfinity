package xol.lostinfinity.item.misc;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IHotbarDeath;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemReactionChamber.class */
public class ItemReactionChamber extends ItemCooldown implements IHotbarDeath {
    public ItemReactionChamber(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                effect(worldIn, playerIn);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 45000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "When used or taking max health damage that would kill you, activate this instead.");
        tooltip.add(TextFmt.Green + "Grants you Racing Heart");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Dark_Red) + "Grants Nearby Enemies: Nullified");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Light_Purple) + "Grants Nearby Enemies: Blighted");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Gold) + "Grants Nearby Enemies: Vulnerability");
    }

    @Override // xol.lostinfinity.item.classify.IHotbarDeath
    public boolean playedKilled(ItemStack stack, EntityPlayer player, Entity attacker, float damageDealt) {
        World world = player.field_70170_p;
        if (!showDurabilityBar(stack)) {
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            if (!world.field_72995_K) {
                effect(world, player);
                return false;
            }
            return false;
        }
        return true;
    }

    private void effect(World world, EntityPlayer player) {
        world.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.SCANNER, SoundCategory.MASTER, 1.0f, 1.0f);
        player.func_70690_d(new PotionEffect(PotionInit.RACING_HEART, 400));
        for (EntityLivingBase near_creature : player.field_70170_p.func_72872_a(EntityLivingBase.class, player.func_174813_aQ().func_186662_g(15.0d))) {
            if (!near_creature.func_110124_au().equals(player.func_110124_au())) {
                near_creature.func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 200));
                near_creature.func_70690_d(new PotionEffect(PotionInit.VULNERABILITY, 200));
                near_creature.func_70690_d(new PotionEffect(PotionInit.NULLIFIED, 200));
            }
        }
        CustomParticleConfig config1 = new CustomParticleConfig();
        config1.createInstance().setParticle(ParticleInit.FLAME_LARGE).setSpread(1.0d, 0.0d, 1.0d).setSpeed(0.3d, 0.0d, 0.3d).setVelSpread(1.0d, 0.0d, 1.0d).setCount(21).setIgnoreRange(true);
        CustomParticleConfig config2 = new CustomParticleConfig();
        config2.createInstance().setParticle(ParticleInit.REPEL_FIELD).setSpread(1.0d, 1.0d, 1.0d).setCount(3).setIgnoreRange(true);
        IParticleSpawner.spawnParticle(player.field_70170_p, config1, player.field_70165_t, player.field_70163_u + 1.0d, player.field_70161_v);
        IParticleSpawner.spawnParticle(player.field_70170_p, config2, player.field_70165_t, player.field_70163_u + 1.0d, player.field_70161_v);
    }
}
