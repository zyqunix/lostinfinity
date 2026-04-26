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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemTimekeeper extends ItemCooldown implements IMaxAttack, ICustomRaytrace, ICustomHoldPose {
    public ItemTimekeeper(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                CustomRayTraceResult trace_result = standardFXTrace(worldIn, playerIn, 45, EnumParticleTypes.SMOKE_NORMAL, EntityLivingBase.class);
                if (trace_result != null && trace_result.getResultEntity() != null) {
                    EntityLivingBase hit_entity = trace_result.getResultEntity();
                    int level = 0;
                    if (hit_entity.func_70644_a(PotionInit.SPONTANEOUS_COMBUSTION)) {
                        level = hit_entity.func_70660_b(PotionInit.SPONTANEOUS_COMBUSTION).func_76458_c() + 1;
                    }
                    hit_entity.func_70690_d(new PotionEffect(PotionInit.SPONTANEOUS_COMBUSTION, 80, level));
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_15, SoundCategory.PLAYERS, 1.0f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_17, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 750;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Red + "Fires a round that explodes after some time.");
        tooltip.add(TextFmt.Italic + "(Applies Spontaneous Combustion)");
        tooltip.add(TextFmt.Gold + "Shooting a target with a round in them increases the damage of the round.");
    }
}
