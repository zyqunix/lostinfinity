package xol.lostinfinity.item.misc;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.mob.entity.murk.EntitySkyre;
import xol.lostinfinity.mob.entity.murk.EntityWhisper;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemBeaconOfDarkness extends ItemCooldown implements ICustomRaytrace {
    public ItemBeaconOfDarkness(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K && (trace_result = entityTrace(worldIn, playerIn, 45, EntityLivingBase.class)) != null && trace_result.getResultEntity() != null) {
                EntityLivingBase hit_entity = trace_result.getResultEntity();
                for (EntitySkyre skyre : worldIn.func_72872_a(EntitySkyre.class, playerIn.func_174813_aQ().func_186662_g(20.0d))) {
                    skyre.setMyDarkTarget(hit_entity);
                }
                for (EntityWhisper whisper : worldIn.func_72872_a(EntityWhisper.class, playerIn.func_174813_aQ().func_186662_g(45.0d))) {
                    whisper.setFollowingTarget(playerIn);
                }
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.CORRUPTION_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(worldIn, config1, hit_entity.field_70165_t, hit_entity.field_70163_u + ((double) (hit_entity.field_70131_O / 2.0f)), hit_entity.field_70161_v);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.DARKBIND, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 100;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Dark_Purple + "Attracts certain dark creatures.");
        tooltip.add(TextFmt.Light_Purple + "Can be used to send dark creatures to a target.");
    }
}
