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
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemContagionater extends ItemCooldown implements IMaxAttack, ICustomRaytrace {
    public ItemContagionater(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                CustomRayTraceResult trace_result = entityTrace(worldIn, playerIn, 45, EntityLivingBase.class);
                if (trace_result != null && trace_result.getResultEntity() != null) {
                    EntityLivingBase hit_entity = trace_result.getResultEntity();
                    hit_entity.func_70690_d(new PotionEffect(PotionInit.PLAGUE, 500, 5));
                    hit_entity.func_70690_d(new PotionEffect(PotionInit.CONTAGIOUS, 500));
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.PLAGUE).setCount(8).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(worldIn, config1, hit_entity.field_70165_t, hit_entity.field_70163_u + ((double) (hit_entity.field_70131_O / 2.0f)), hit_entity.field_70161_v);
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_9, SoundCategory.PLAYERS, 1.0f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
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
        tooltip.add(TextFmt.Light_Purple + "Grants Contagious and Other Selected Effect To Target");
        tooltip.add(TextFmt.Green + "Currently granting Plague.");
        tooltip.add(TextFmt.Gold + "Can have more effects added.");
    }
}
