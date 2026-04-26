package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemSuperShocker.class */
public class ItemSuperShocker extends ItemCooldown implements IMaxAttack, ICustomRaytrace, ICustomHoldPose, ISwitchModels, IModeSelect {
    public ItemSuperShocker(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setModelSwitch("zaptype", this, 2);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                int attack_style = stack.func_77978_p().func_74762_e("zaptype_data");
                CustomRayTraceResult trace_result = entityTrace(worldIn, playerIn, 45, EntityLivingBase.class);
                if (trace_result != null && trace_result.getResultEntity() != null) {
                    EntityLivingBase hit_entity = (EntityLivingBase) trace_result.getResultEntity();
                    int radius = shockCreature(playerIn, hit_entity, 5 + (5 * attack_style));
                    if (attack_style == 1) {
                        if (radius > 10) {
                            radius = 10;
                        }
                        for (EntityLivingBase near_pl : worldIn.func_72872_a(EntityLivingBase.class, playerIn.func_174813_aQ().func_186662_g(3 + (2 * radius)))) {
                            if (!near_pl.func_110124_au().equals(playerIn.func_110124_au()) && !near_pl.func_110124_au().equals(hit_entity.func_110124_au())) {
                                shockCreature(playerIn, near_pl, 10);
                            }
                        }
                    }
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.LASER_WEAPON_3, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    private int shockCreature(EntityPlayer attacker, EntityLivingBase entity, int denominator) {
        int level = 1;
        if (entity.func_70644_a(PotionInit.SHOCKED)) {
            level = entity.func_70660_b(PotionInit.SHOCKED).func_76458_c() + 1;
        }
        if (IMaxAttack.dealMaxHealth((Entity) attacker, entity, denominator, level).didSuccessfulHit()) {
            entity.func_70690_d(new PotionEffect(PotionInit.SHOCKED, 80, level));
        }
        CustomParticleConfig config1 = new CustomParticleConfig();
        config1.createInstance().setParticle(ParticleInit.ZAP).setSpread(3.0d, 1.0d, 3.0d).setCount(5).setIgnoreRange(true);
        IParticleSpawner.spawnParticle(attacker.field_70170_p, config1, entity.field_70165_t, entity.field_70163_u + ((double) (entity.field_70131_O / 2.0f)), entity.field_70161_v);
        return level;
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 100;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Shocks a target that you are looking at.");
        tooltip.add(TextFmt.Gold + "Increases level of shock if target is already shocked.");
        tooltip.add(TextFmt.Underline + "While AOE mode is enabled, spread shock in an area around target.");
        tooltip.add(TextFmt.Italic + "Radius of shock spread depends on target's number of shock stacks.");
        tooltip.add(TextFmt.Gold + "Deals (Single: 20%, AOE: 10%) Max Health Damage per stack of shock.");
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int attack_style = stack.func_77978_p().func_74762_e("zaptype_data");
        if (attack_style == 0) {
            stack.func_77978_p().func_74768_a("zaptype_data", 1);
        } else {
            stack.func_77978_p().func_74768_a("zaptype_data", 0);
        }
    }
}
