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

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemHoloPoint.class */
public class ItemHoloPoint extends ItemCooldown implements IMaxAttack, ICustomRaytrace, ICustomHoldPose {
    public ItemHoloPoint(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (handIn == EnumHand.MAIN_HAND) {
            ItemStack stack = playerIn.func_184586_b(handIn);
            if (!showDurabilityBar(stack)) {
                if (!worldIn.field_72995_K) {
                    CustomRayTraceResult trace_result = standardFXTrace(worldIn, playerIn, 45, EnumParticleTypes.SMOKE_NORMAL, EntityLivingBase.class);
                    if (trace_result != null && trace_result.getResultEntity() != null) {
                        EntityLivingBase hit_entity = trace_result.getResultEntity();
                        boolean flag = false;
                        if (hit_entity.func_70644_a(PotionInit.SHATTERED)) {
                            flag = true;
                        }
                        IMaxAttack.dealMaxHealth((Entity) playerIn, hit_entity, 4, 3.0f);
                        if (!flag) {
                            hit_entity.func_70690_d(new PotionEffect(PotionInit.SHATTERED, 100));
                        }
                    }
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_15, SoundCategory.PLAYERS, 1.0f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_17, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }
                boolean hasOffhand = playerIn.func_184592_cb().func_77973_b() instanceof ItemArcOfTheForbidden;
                if (hasOffhand) {
                    stack.func_77978_p().func_74768_a("ComplexCooldown", 500);
                } else {
                    stack.func_77978_p().func_74768_a("ComplexCooldown", 1200);
                }
                playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected boolean hasSimpleCooldown() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Deals 75% Max Health Damage");
        tooltip.add(TextFmt.Gold + "Applies Shattered if not already on the target.");
        tooltip.add(TextFmt.Italic + "Has a greatly reduced cooldown when your offhand is Arc of the Forbidden.");
    }
}
