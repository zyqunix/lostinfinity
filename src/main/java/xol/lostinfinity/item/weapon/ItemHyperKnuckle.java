package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
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
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemHyperKnuckle.class */
public class ItemHyperKnuckle extends ItemCooldownSword implements IMaxAttack, ICustomRaytrace {
    public ItemHyperKnuckle(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        float damageDealt = IMaxAttack.dealMaxHealth((Entity) attacker, target, 4, 3.0f).getDamageDealt();
        attacker.func_70691_i(damageDealt);
        return true;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        if (handIn == EnumHand.MAIN_HAND) {
            ItemStack stack = playerIn.func_184586_b(handIn);
            if (!showDurabilityBar(stack)) {
                if (!worldIn.field_72995_K) {
                    boolean hasOffhand = (playerIn.func_184592_cb().func_77973_b() instanceof ItemHyperKnuckle) || (playerIn.func_184592_cb().func_77973_b() instanceof ItemHypercron);
                    if (hasOffhand && (trace_result = entityTrace(worldIn, playerIn, 10, EntityLivingBase.class)) != null && trace_result.getResultEntity() != null) {
                        EntityLivingBase tracedEntity = trace_result.getResultEntity();
                        if (tracedEntity.func_110143_aJ() < tracedEntity.func_110138_aP() / 3.0f) {
                            playerIn.func_70634_a(tracedEntity.field_70165_t, tracedEntity.field_70163_u, tracedEntity.field_70161_v);
                            playerIn.func_70691_i(playerIn.func_110138_aP());
                            IMaxAttack.dealTrueDamage(playerIn, tracedEntity, tracedEntity.func_110143_aJ() * 2.0f);
                            worldIn.func_184133_a((EntityPlayer) null, tracedEntity.func_180425_c(), SoundInit.EXECUTE_EFFECT, SoundCategory.PLAYERS, 1.0f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                            CustomParticleConfig config1 = new CustomParticleConfig();
                            config1.createInstance().setParticle(ParticleInit.CLAW_MARKS).setSpread(4.0d, 1.0d, 4.0d).setCount(8).setIgnoreRange(true);
                            IParticleSpawner.spawnParticle(worldIn, config1, tracedEntity.field_70165_t, tracedEntity.field_70163_u + ((double) (tracedEntity.field_70131_O / 2.0f)), tracedEntity.field_70161_v);
                        }
                    }
                }
                playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.weapon.ItemCooldownSword
    protected int getCooldown() {
        return 500;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Deals 75% Max Health Damage");
        tooltip.add(TextFmt.Green + "Lifesteals for 100% of Damage Dealt");
        tooltip.add(TextFmt.Gold + "If also wielding a Hyper Knuckle in the offhand:");
        tooltip.add(TextFmt.Italic + "Right Click to execute a nearby target below 33% health, dashing to them.");
        tooltip.add(TextFmt.Italic + "Executing a target heals you to full.");
    }
}
