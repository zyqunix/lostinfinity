package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
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
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemEMP extends ItemCooldown {
    public ItemEMP(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                for (EntityLivingBase near_pl : worldIn.func_72872_a(EntityLivingBase.class, playerIn.func_174813_aQ().func_72314_b(25.0d, 25.0d, 25.0d))) {
                    if (!near_pl.func_110124_au().equals(playerIn.func_110124_au())) {
                        if (near_pl instanceof EntityPlayer) {
                            near_pl.func_70690_d(new PotionEffect(PotionInit.NULLIFIED, 400, 0));
                        } else {
                            near_pl.func_70690_d(new PotionEffect(PotionInit.VULNERABILITY, 400, 0));
                        }
                    }
                }
                float f = 0.0f;
                while (true) {
                    float angle = f;
                    if (angle > 6.283185307179586d) {
                        break;
                    }
                    double velocity_x = 3.0d * Math.cos(angle);
                    double velocity_z = 3.0d * Math.sin(angle);
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.POWER_LOSS).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(worldIn, config1, playerIn.field_70165_t + velocity_x, playerIn.field_70163_u + 0.5d, playerIn.field_70161_v + velocity_z);
                    f = (float) (((double) angle) + 0.3141592653589793d);
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_11, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 15000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Applies the nullified debuff to creatures in a large radius.");
        tooltip.add(TextFmt.Green + "Non-players receive vulnerability instead.");
    }
}
