package xol.lostinfinity.item.weapon;

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
import net.minecraft.util.math.BlockPos;
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
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemEnderJumper.class */
public class ItemEnderJumper extends ItemCooldown implements ICustomRaytrace, IMaxAttack {
    public ItemEnderJumper(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            playerIn.func_184586_b(handIn);
            if (!worldIn.field_72995_K && (trace_result = simpleBlockTrace(worldIn, playerIn, 60)) != null) {
                BlockPos resultPos = trace_result.getResultPos();
                playerIn.func_70634_a(resultPos.func_177958_n(), resultPos.func_177956_o(), resultPos.func_177952_p());
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.WARP).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(worldIn, config1, resultPos.func_177958_n(), ((double) resultPos.func_177956_o()) + 0.5d, resultPos.func_177952_p());
                worldIn.func_184133_a((EntityPlayer) null, resultPos, SoundInit.BIG_WARP, SoundCategory.PLAYERS, 1.5f, 0.5f + worldIn.field_73012_v.nextFloat());
                for (EntityLivingBase near_pl : worldIn.func_72872_a(EntityLivingBase.class, playerIn.func_174813_aQ().func_72314_b(6.0d, 6.0d, 6.0d))) {
                    if (!near_pl.func_110124_au().equals(playerIn.func_110124_au())) {
                        CustomDamageResult result = IMaxAttack.dealMaxHealth((Entity) playerIn, near_pl, 4, 3.0f);
                        boolean wasKilled = result.wasTargetKilled();
                        if (!wasKilled && result.didSuccessfulHit()) {
                            near_pl.func_70690_d(new PotionEffect(PotionInit.VULNERABILITY, 200, 1));
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
        return 1000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Can be used to quickly warp to locations.");
        tooltip.add(TextFmt.Gold + "Warping deals 75% max health damage to nearby enemies upon arrival.");
        tooltip.add(TextFmt.Italic + "Warping also curses enemies with vulnerability II.");
    }
}
