package xol.lostinfinity.item.weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.util.PotionBasic;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemEverlastingWarlord extends ItemCooldownSword implements IMaxAttack, ICustomRaytrace {
    public ItemEverlastingWarlord(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        CustomDamageResult dr = IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP() * 0.5f);
        float damageDealt = dr.getDamageDealt();
        if (dr.wasTargetKilled()) {
            killReward(attacker);
        } else {
            attacker.func_70691_i(damageDealt);
        }
        attacker.field_70170_p.func_184133_a((EntityPlayer) null, attacker.func_180425_c(), SoundInit.SWING_HIT, SoundCategory.PLAYERS, 1.5f, 0.6f + (attacker.field_70170_p.field_73012_v.nextFloat() * 0.4f));
        return true;
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        if (handIn == EnumHand.MAIN_HAND) {
            ItemStack stack = playerIn.func_184586_b(handIn);
            if (!showDurabilityBar(stack)) {
                if (!worldIn.field_72995_K && (trace_result = entityTrace(worldIn, playerIn, 30, EntityLivingBase.class)) != null && trace_result.getResultEntity() != null) {
                    EntityLivingBase tracedEntity = trace_result.getResultEntity();
                    playerIn.func_70634_a(tracedEntity.field_70165_t, tracedEntity.field_70163_u, tracedEntity.field_70161_v);
                    IMaxAttack.dealTrueDamage(playerIn, tracedEntity, tracedEntity.func_110138_aP() * 0.5f);
                    worldIn.func_184133_a((EntityPlayer) null, tracedEntity.func_180425_c(), SoundInit.RAPID_TELEPORT, SoundCategory.PLAYERS, 1.5f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                    if (tracedEntity.func_110143_aJ() <= 0.0f) {
                        killReward(playerIn);
                    }
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.CLAW_MARKS).setSpread(4.0d, 1.0d, 4.0d).setCount(8).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(worldIn, config1, tracedEntity.field_70165_t, tracedEntity.field_70163_u + ((double) (tracedEntity.field_70131_O / 2.0f)), tracedEntity.field_70161_v);
                }
                playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.weapon.ItemCooldownSword
    protected int getCooldown() {
        return 2000;
    }
    private void killReward(EntityLivingBase player) {
        List<PotionEffect> potionsToAdd = new ArrayList<>();
        List<Potion> potionList = (List) player.func_70651_bq().stream().map((v0) -> {
            return v0.func_188419_a();
        }).collect(Collectors.toList());
        for (Potion potion : potionList) {
            if (potion instanceof PotionBasic) {
                PotionBasic lost_potion = (PotionBasic) potion;
                PotionEffect scaledEffect = scalePotionEffect(player.func_70660_b(potion), lost_potion.negativeLostEffect());
                if (scaledEffect != null) {
                    potionsToAdd.add(scaledEffect);
                }
            } else {
                PotionEffect scaledEffect2 = scalePotionEffect(player.func_70660_b(potion), potion.func_76398_f());
                if (scaledEffect2 != null) {
                    potionsToAdd.add(scaledEffect2);
                }
            }
        }
        player.func_70674_bp();
        for (PotionEffect pe : potionsToAdd) {
            player.func_70690_d(pe);
        }
        player.func_70691_i(player.func_110138_aP());
    }
    private PotionEffect scalePotionEffect(PotionEffect effect, boolean bad) {
        if (!bad) {
            return effect;
        }
        int level = effect.func_76458_c() - 1;
        int duration = effect.func_76459_b();
        if (level >= 0) {
            return new PotionEffect(effect.func_188419_a(), duration, level);
        }
        return null;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Deals 50% Health True Damage");
        tooltip.add(TextFmt.Green + "Lifesteals for 100% of (melee) Damage Dealt");
        tooltip.add(TextFmt.Italic + "Right Click to dash to a target, dealing an instant ranged hit.");
        tooltip.add(TextFmt.Gold + "Killing a target heals you to full and reduces the level of negative effects.");
    }
}
