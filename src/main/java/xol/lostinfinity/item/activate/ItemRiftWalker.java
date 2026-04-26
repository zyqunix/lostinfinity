package xol.lostinfinity.item.activate;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
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
import xol.lostinfinity.mob.entity.misc.EntityRift;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemRiftWalker extends ItemCooldown implements IMaxAttack {
    public ItemRiftWalker(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (playerIn.func_70644_a(PotionInit.SPECTRAL) && playerIn.func_70644_a(Potion.func_188412_a(14))) {
            playerIn.func_184596_c(PotionInit.SPECTRAL);
            playerIn.func_184596_c(Potion.func_188412_a(14));
            playerIn.func_184596_c(PotionInit.PROTECTED);
            endEffect(playerIn);
        } else if (!showDurabilityBar(stack) && !playerIn.func_70093_af()) {
            if (!worldIn.field_72995_K) {
                playerIn.func_70690_d(new PotionEffect(Potion.func_188412_a(14), 300, 0, false, false));
                playerIn.func_70690_d(new PotionEffect(PotionInit.SPECTRAL, 300, 0, false, false));
                playerIn.func_70690_d(new PotionEffect(PotionInit.PROTECTED, 300, 0, false, false));
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_VANISH, SoundCategory.PLAYERS, 1.0f, 0.9f + (0.2f * worldIn.field_73012_v.nextFloat()));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    public static void endEffect(EntityPlayer player) {
        if (!player.field_70170_p.field_72995_K) {
            player.func_82142_c(false);
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.MURK).setSpread(5.0d, 2.0d, 5.0d).setCount(20).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(player.field_70170_p, config1, player.field_70165_t, player.field_70163_u, player.field_70161_v);
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.RIFT_CREATE, SoundCategory.PLAYERS, 1.5f, 1.0f);
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.GENERIC_WEAPON_10, SoundCategory.PLAYERS, 1.0f, 1.0f);
            for (EntityLivingBase target : player.field_70170_p.func_72872_a(EntityLivingBase.class, player.func_174813_aQ().func_186662_g(15.0d))) {
                if (!target.func_110124_au().equals(player.func_110124_au())) {
                    IMaxAttack.dealTrueDamage(player, target, target.func_110138_aP() * 1.5f, Arrays.asList("Darkborn"));
                }
            }
            EntityRift rift = new EntityRift(player.field_70170_p);
            rift.func_70107_b(player.field_70165_t, player.field_70163_u + 0.5d, player.field_70161_v);
            player.field_70170_p.func_72838_d(rift);
        }
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 8000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Enter a spectral form, separated from your body, becoming immune and invisible.");
        tooltip.add(TextFmt.Gold + "After 15 seconds, your body snaps to your spectral form.");
        tooltip.add(TextFmt.Red + "Exiting spectral form creates dangerous rifts and deals damage.");
        tooltip.add(TextFmt.Dark_Aqua + "Darkborn");
    }
}
