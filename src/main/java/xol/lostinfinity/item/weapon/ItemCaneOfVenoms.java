package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.util.Reference;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemCaneOfVenoms.class */
public class ItemCaneOfVenoms extends ItemCooldownSword implements IMaxAttack, ICustomRaytrace, IModeSelect {
    private static final String MODE = "bane_mode";

    public ItemCaneOfVenoms(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
        func_185043_a(new ResourceLocation(Reference.MODID, MODE), (stack, worldIn, entityIn) -> {
            return isBaneMode(stack) ? 1.0f : 0.0f;
        });
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        int potion_count;
        if (isBaneMode(stack) && (potion_count = target.func_70651_bq().size()) > 0) {
            IMaxAttack.dealMaxHealth((Entity) attacker, target, 5, potion_count);
            return true;
        }
        return true;
    }

    public String func_77667_c(ItemStack stack) {
        return isBaneMode(stack) ? "item.bane_of_venoms" : "item.cane_of_venoms";
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack) && !isBaneMode(stack)) {
            if (!worldIn.field_72995_K && (trace_result = standardFXTrace(worldIn, playerIn, 45, EnumParticleTypes.SMOKE_NORMAL, EntityLivingBase.class)) != null && trace_result.getResultEntity() != null) {
                EntityLivingBase target = trace_result.getResultEntity();
                int potion_count = target.func_70651_bq().size();
                if (potion_count > 0) {
                    IMaxAttack.dealMaxHealth((Entity) playerIn, target, 10, potion_count);
                }
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.VENOM).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
                CustomParticleConfig config2 = new CustomParticleConfig();
                config2.createInstance().setParticle(ParticleInit.VENOM_RING).setSpread(1.0d, 1.0d, 1.0d).setCount(5).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(worldIn, config1, target.field_70165_t, target.field_70163_u + ((double) (target.field_70131_O / 2.0f)), target.field_70161_v);
                IParticleSpawner.spawnParticle(worldIn, config2, target.field_70165_t, target.field_70163_u + ((double) (target.field_70131_O / 2.0f)), target.field_70161_v);
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_5, SoundCategory.PLAYERS, 1.0f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.weapon.ItemCooldownSword
    protected int getCooldown() {
        return 100;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (isBaneMode(stack)) {
            tooltip.add(TextFmt.Light_Purple + "Per Potion Effect on Target:");
            tooltip.add(TextFmt.Gold + "Deals 20% Max Health Damage");
        } else {
            tooltip.add(TextFmt.Gold + "Fires a projectile that deals damage based on potion effects.");
            tooltip.add(TextFmt.Light_Purple + "Per Potion Effect on Target:");
            tooltip.add(TextFmt.Gold + "Deals 10% Max Health Damage");
        }
        tooltip.add(TextFmt.Green + "Shift Right-Click: Swap between Cane & Bane");
    }

    private void toggleBaneMode(ItemStack stack) {
        setBaneMode(stack, !isBaneMode(stack));
    }

    private void setBaneMode(ItemStack stack, boolean flag) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74757_a(MODE, flag);
    }

    private boolean isBaneMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74757_a(MODE, false);
            return false;
        }
        return stack.func_77978_p().func_74767_n(MODE);
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        toggleBaneMode(stack);
    }
}
