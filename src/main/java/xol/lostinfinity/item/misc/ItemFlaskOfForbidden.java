package xol.lostinfinity.item.misc;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IHotbarDeath;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemFlaskOfForbidden.class */
public class ItemFlaskOfForbidden extends Item implements IHotbarDeath {
    public ItemFlaskOfForbidden(String regName) {
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "When taking max health damage that would kill you, activate this flask instead.");
        tooltip.add(TextFmt.Italic + "The flask does the following:");
        tooltip.add(TextFmt.Green + "Heal for 20% Maximum Life");
        tooltip.add(TextFmt.Light_Purple + "Gives you Last Breath I and everything else nearby Blighted I.");
        tooltip.add(TextFmt.Gold + "If you already have Last Breath, increase the strength of both effects by 1.");
        tooltip.add(TextFmt.Red + "You die if you go beyond Last Breath V.");
    }

    @Override // xol.lostinfinity.item.classify.IHotbarDeath
    public boolean playedKilled(ItemStack stack, EntityPlayer player, Entity attacker, float damageDealt) {
        World world = player.field_70170_p;
        if (player.func_70644_a(PotionInit.LAST_BREATH)) {
            int level = player.func_70660_b(PotionInit.LAST_BREATH).func_76458_c();
            if (level >= 5) {
                return true;
            }
            if (!world.field_72995_K) {
                potionApplication(world, player, level + 1);
            }
        } else if (!world.field_72995_K) {
            potionApplication(world, player, 0);
        }
        player.func_70691_i(player.func_110138_aP() * 0.2f);
        return false;
    }

    private void potionApplication(World world, EntityPlayer player, int amp) {
        player.func_70690_d(new PotionEffect(PotionInit.LAST_BREATH, 200, amp));
        for (EntityLivingBase near_creature : player.field_70170_p.func_72872_a(EntityLivingBase.class, player.func_174813_aQ().func_72314_b(20.0d, 10.0d, 20.0d))) {
            if (!near_creature.func_110124_au().equals(player.func_110124_au())) {
                player.func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 200, amp));
            }
        }
        if (!world.field_72995_K) {
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.BLIGHT_SPELL_GREEN).setSpread(4.0d, 1.0d, 4.0d).setCount(4).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(world, config1, player.field_70165_t, player.field_70163_u + 1.0d, player.field_70161_v);
            CustomParticleConfig config2 = new CustomParticleConfig();
            config2.createInstance().setParticle(ParticleInit.CORRUPTION_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(world, config2, player.field_70165_t, player.field_70163_u + 1.0d, player.field_70161_v);
            world.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundEvents.field_187664_bz, SoundCategory.MASTER, 1.0f, 1.0f);
        }
    }
}
