package xol.lostinfinity.item.weapon;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.projectile.entity.EntityForbiddenBrand;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemBrandOfTheForbidden.class */
public class ItemBrandOfTheForbidden extends ItemCooldown implements IMaxAttack, ICustomRaytrace, ICustomHoldPose {
    public ItemBrandOfTheForbidden(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        EntityForbiddenBrand existing;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                CustomRayTraceResult trace_result = standardFXTrace(worldIn, playerIn, 45, EnumParticleTypes.TOTEM, EntityLivingBase.class);
                if (trace_result != null && trace_result.getResultEntity() != null) {
                    UUID brandUUID = stack.func_77978_p().func_186857_a("brandID");
                    if (isExistingBrand(brandUUID) && (existing = (EntityForbiddenBrand) worldIn.func_73046_m().func_175576_a(brandUUID)) != null) {
                        existing.func_70106_y();
                    }
                    EntityLivingBase hit_entity = trace_result.getResultEntity();
                    EntityForbiddenBrand brand = new EntityForbiddenBrand(worldIn);
                    brand.func_70107_b(hit_entity.field_70165_t, hit_entity.field_70163_u, hit_entity.field_70161_v);
                    brand.setCreator(playerIn);
                    brand.setTarget(hit_entity);
                    worldIn.func_72838_d(brand);
                    stack.func_77978_p().func_186854_a("brandID", brand.func_110124_au());
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_15, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    private boolean isExistingBrand(UUID idToCheck) {
        return (idToCheck == null || idToCheck.equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))) ? false : true;
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 1000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Puts a forbidden brand on a target that you are looking at.");
        tooltip.add(TextFmt.Gold + "Forbidden Brand Deals Max Health Damage Rapidly");
        tooltip.add(TextFmt.Red + "Can only have one brand active at a time.");
    }
}
