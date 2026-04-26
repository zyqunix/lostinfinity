package xol.lostinfinity.item.weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
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
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.util.PotionBasic;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemPhantomBlade.class */
public class ItemPhantomBlade extends ItemCooldownSword implements IMaxAttack, ICustomRaytrace {
    public ItemPhantomBlade(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        IMaxAttack.dealMaxHealth((Entity) attacker, target, 4, 3.0f);
        return true;
    }

    @Override // xol.lostinfinity.item.weapon.ItemCooldownSword
    protected int getCooldown() {
        return 500;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K && (trace_result = complexTrace(worldIn, playerIn, 25, null, 0, 0, false, EntityLivingBase.class, 1, 0.3f)) != null && trace_result.getResultEntity() != null) {
                EntityLivingBase tracedEntity = trace_result.getResultEntity();
                playerIn.func_70634_a(tracedEntity.field_70165_t, tracedEntity.field_70163_u, tracedEntity.field_70161_v);
                worldIn.func_184133_a((EntityPlayer) null, tracedEntity.func_180425_c(), SoundInit.ITEM_GHOSTHUNTER, SoundCategory.MASTER, 2.0f, 1.0f);
                List<PotionEffect> effectsToAdd = new ArrayList<>();
                for (PotionEffect effect : playerIn.func_70651_bq()) {
                    Potion effPot = effect.func_188419_a();
                    if (effPot.func_76398_f()) {
                        effectsToAdd.add(effect);
                    } else if (effPot instanceof PotionBasic) {
                        PotionBasic basicPot = (PotionBasic) effPot;
                        if (basicPot.negativeLostEffect()) {
                            effectsToAdd.add(effect);
                        }
                    }
                }
                if (effectsToAdd.size() > 0 && IMaxAttack.dealMaxHealth((Entity) playerIn, tracedEntity, 10, effectsToAdd.size()).didSuccessfulHit()) {
                    Iterator<PotionEffect> it = effectsToAdd.iterator();
                    while (it.hasNext()) {
                        tracedEntity.func_70690_d(it.next());
                    }
                }
                playerIn.func_70674_bp();
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Deals 75% Max Health Damage");
        tooltip.add(TextFmt.Dark_Purple + "Right click to teleport up to 20 blocks to an entity through walls.");
        tooltip.add(TextFmt.Aqua + "Teleporting to an entity transfers ALL negative potion effects to them.");
        tooltip.add(TextFmt.Dark_Purple + "Teleporting deals 10% Max Health Damage per effect transferred.");
    }
}
