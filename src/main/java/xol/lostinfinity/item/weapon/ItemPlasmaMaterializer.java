package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.mob.entity.misc.EntityPlasmaExplosion;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.data.RayTraceBuilder;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemPlasmaMaterializer extends ItemChanneling implements IMaxAttack {
    public ItemPlasmaMaterializer(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }
    private ActionResult<ItemStack> onItemRightClick0(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack) && !stack.func_77951_h()) {
            if (!worldIn.field_72995_K && (trace_result = RayTraceBuilder.entity(EntityPlasmaExplosion.class, 45).entityFilter(null).trace(playerIn, true)) != null) {
                if (trace_result.getResultEntity() != null) {
                    EntityPlasmaExplosion explosEntity = trace_result.getResultEntity();
                    if (!playerIn.func_70093_af()) {
                        explosEntity.upExplScale();
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_2, SoundCategory.MASTER, 2.0f, 1.0f + (worldIn.field_73012_v.nextFloat() * 0.2f));
                    } else {
                        explosEntity.setBoom();
                    }
                } else {
                    BlockPos result_pos = trace_result.getResultPos();
                    EntityPlasmaExplosion charging_entity = new EntityPlasmaExplosion(worldIn);
                    charging_entity.setCreator(playerIn.func_110124_au());
                    charging_entity.func_70107_b(result_pos.func_177958_n(), result_pos.func_177956_o() + 1, result_pos.func_177952_p());
                    worldIn.func_72838_d(charging_entity);
                    IParticleSpawner.spawnParticle(worldIn, 2, 0, result_pos.func_177958_n(), result_pos.func_177956_o() + 1, result_pos.func_177952_p());
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_WEAPON_1, SoundCategory.MASTER, 2.0f, 1.0f);
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public ActionResult<ItemStack> chargeStart(World worldIn, EntityPlayer player, EnumHand handIn, ItemStack stack) {
        if (showDurabilityBar(stack) || worldIn.field_72995_K) {
            return new ActionResult<>(EnumActionResult.FAIL, stack);
        }
        CustomRayTraceResult trace = RayTraceBuilder.block(45).trace(player, true);
        if (trace == null) {
            return new ActionResult<>(EnumActionResult.FAIL, stack);
        }
        BlockPos resultPos = trace.getResultPos();
        EntityPlasmaExplosion chargeEntity = new EntityPlasmaExplosion(worldIn);
        chargeEntity.setCreator(player.func_110124_au());
        chargeEntity.func_70107_b(resultPos.func_177958_n(), resultPos.func_177956_o() + 1, resultPos.func_177952_p());
        worldIn.func_72838_d(chargeEntity);
        IParticleSpawner.spawnParticle(worldIn, 2, 0, resultPos.func_177958_n(), resultPos.func_177956_o() + 1, resultPos.func_177952_p());
        worldIn.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.GENERIC_WEAPON_1, SoundCategory.MASTER, 2.0f, 1.0f);
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public void chargeTick(World worldIn, EntityPlayer player, EnumHand hand, ItemStack stack, int chargeTime) {
        CustomRayTraceResult trace;
        if (chargeTime == 0 || chargeTime % 10 != 0 || showDurabilityBar(stack) || worldIn.field_72995_K || (trace = RayTraceBuilder.entity(EntityPlasmaExplosion.class, 45).entityFilter(null).trace(player, true)) == null || trace.getResultEntity() == null) {
            return;
        }
        EntityPlasmaExplosion explodeEntity = trace.getResultEntity();
        if (explodeEntity.getExplScale() < explodeEntity.maxExpSize()) {
            worldIn.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.GENERIC_WEAPON_2, SoundCategory.MASTER, 2.0f, 1.0f + (worldIn.field_73012_v.nextFloat() * 0.2f));
        }
        explodeEntity.upExplScale();
    }
    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public void chargeStop(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int chargeTime) {
        CustomRayTraceResult trace;
        if (showDurabilityBar(stack) || worldIn.field_72995_K || (trace = RayTraceBuilder.entity(EntityPlasmaExplosion.class, 45).entityFilter(null).trace(entityLiving, true)) == null || trace.getResultEntity() == null) {
            return;
        }
        EntityPlasmaExplosion explodeEntity = trace.getResultEntity();
        explodeEntity.setBoom();
        startCooldown(stack);
    }
    @Override // xol.lostinfinity.item.basics.ItemChanneling, xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 100;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Places a plasma charge.");
        tooltip.add(TextFmt.Green + "If looking at an existing plasma charge, power it up.");
        tooltip.add(TextFmt.Red + "Plasma Charges can be charged to increase in size in damage before exploding.");
        tooltip.add(TextFmt.Gold + "Shift Click: Detonate Early");
    }
}
