package xol.lostinfinity.item.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemBladeOfDead.class */
public class ItemBladeOfDead extends ItemCooldownSword implements IMaxAttack, ICustomRaytrace {
    public ItemBladeOfDead(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        IMaxAttack.dealMaxHealth(attacker, target, 5);
        return true;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                CustomRayTraceResult trace_result = entityTrace(worldIn, playerIn, 45, EntityLivingBase.class);
                BlockPos startPos = playerIn.func_180425_c();
                if (trace_result != null && trace_result.getResultEntity() != null) {
                    EntityLivingBase targeted = trace_result.getResultEntity();
                    BlockPos resultPos = trace_result.getResultPos();
                    float dist = targeted.func_70032_d(playerIn);
                    playerIn.func_70634_a(targeted.field_70165_t, targeted.field_70163_u, targeted.field_70161_v);
                    worldIn.func_184133_a((EntityPlayer) null, resultPos, SoundInit.ITEM_GHOSTHUNTER, SoundCategory.MASTER, 2.0f, 1.0f);
                    int multiplier = Math.round(dist / 12.0f);
                    IMaxAttack.dealMaxHealth((Entity) playerIn, targeted, 4, Math.max(1, Math.min(3, multiplier)));
                    List<UUID> found_living = new ArrayList<>();
                    found_living.add(playerIn.func_110124_au());
                    found_living.add(targeted.func_110124_au());
                    int repeats = 18 + Math.abs(Math.round(dist));
                    double xdiff = startPos.func_177958_n() - resultPos.func_177958_n();
                    double ydiff = startPos.func_177956_o() - resultPos.func_177956_o();
                    double zdiff = startPos.func_177952_p() - resultPos.func_177952_p();
                    for (int part = 0; part < repeats; part++) {
                        BlockPos curPos = new BlockPos(((double) startPos.func_177958_n()) + (((-xdiff) / ((double) repeats)) * ((double) part)), ((double) startPos.func_177956_o()) + (((-ydiff) / ((double) repeats)) * ((double) part)), ((double) startPos.func_177952_p()) + (((-zdiff) / ((double) repeats)) * ((double) part)));
                        if (worldIn.field_72995_K) {
                            worldIn.func_175688_a(EnumParticleTypes.SMOKE_LARGE, curPos.func_177958_n(), curPos.func_177956_o(), curPos.func_177952_p(), (worldIn.field_73012_v.nextDouble() - 0.5d) * 0.1d, 0.0d, (worldIn.field_73012_v.nextDouble() - 0.5d) * 0.1d, new int[0]);
                        } else if (part != repeats - 1) {
                            for (EntityLivingBase near_entity : worldIn.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(curPos.func_177982_a(-4, -4, -4), curPos.func_177982_a(4, 4, 4)))) {
                                UUID entity_id = near_entity.func_110124_au();
                                if (!found_living.contains(entity_id)) {
                                    found_living.add(entity_id);
                                    IMaxAttack.dealMaxHealth(playerIn, near_entity, 3);
                                }
                            }
                        }
                    }
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.weapon.ItemCooldownSword
    protected int getCooldown() {
        return 1000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Deals 20% Max Health Damage");
        tooltip.add(TextFmt.Dark_Purple + "Right click to teleport up to 45 blocks to an entity.");
        tooltip.add(TextFmt.Dark_Purple + "Deal 33% Max Health Damage To Enemies Near The Trail");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Light_Purple) + "On Arrival:");
        tooltip.add(TextFmt.Red + "Deal up to 75% Max Health Damage to Targeted Entity");
        tooltip.add(TextFmt.Italic + "Damage based on distance travelled.");
    }
}
