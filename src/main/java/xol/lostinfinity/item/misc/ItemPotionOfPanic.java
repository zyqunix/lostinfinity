package xol.lostinfinity.item.misc;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemPotionOfPanic extends ItemCooldown {
    public ItemPotionOfPanic(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.EVIL_LAUGH, SoundCategory.MASTER, 1.5f, 1.0f);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.DEEP_EXPLOSION, SoundCategory.MASTER, 0.5f, 1.0f);
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.EXPLOSION_FEAR).setSpread(30.0d, 4.0d, 30.0d).setCount(5).setIgnoreRange(true);
                CustomParticleConfig config2 = new CustomParticleConfig();
                config2.createInstance().setParticle(ParticleInit.EXPLOSION_YELLOW).setSpread(30.0d, 4.0d, 30.0d).setCount(15).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(worldIn, config1, playerIn.field_70165_t, playerIn.field_70163_u + 1.0d, playerIn.field_70161_v);
                IParticleSpawner.spawnParticle(worldIn, config2, playerIn.field_70165_t, playerIn.field_70163_u + 1.0d, playerIn.field_70161_v);
                playerIn.func_70690_d(new PotionEffect(PotionInit.FEARED, 400));
                for (EntityTameable entityTameable : worldIn.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(playerIn.func_180425_c()).func_186662_g(15.0d))) {
                    if (!entityTameable.func_110124_au().equals(playerIn.func_110124_au())) {
                        if (entityTameable instanceof EntityTameable) {
                            if (entityTameable.func_70902_q().func_110124_au().equals(playerIn.func_110124_au())) {
                                entityTameable.func_70690_d(new PotionEffect(PotionInit.FEARED, 400));
                            } else {
                                entityTameable.func_70690_d(new PotionEffect(PotionInit.TERRIFIED, 400));
                            }
                        } else {
                            entityTameable.func_70690_d(new PotionEffect(PotionInit.TERRIFIED, 400));
                        }
                    }
                }
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
        tooltip.add(TextFmt.Gold + "When drunk, creates a cloud of fear around you.");
        tooltip.add(TextFmt.Light_Purple + "The cloud causes enemies to fear you and your tames.");
        tooltip.add(TextFmt.Italic + "Entities who are afraid of another entity cannot go near them.");
        tooltip.add(TextFmt.Italic + "Entities who are afraid of another entity have a 50% chance to miss attacks on them.");
    }
}
