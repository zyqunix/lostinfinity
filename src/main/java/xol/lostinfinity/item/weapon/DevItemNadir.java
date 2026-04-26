package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.data.RayTraceBuilder;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class DevItemNadir extends ItemCooldown implements IMaxAttack, IModeSelect, ICustomRaytrace {
    public enum BladeMode {
        INSTANT,
        ONE_LIFE
    }
    public DevItemNadir(String regName) {
        super(regName);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K && (trace_result = RayTraceBuilder.fx(EntityLivingBase.class, 75, EnumParticleTypes.SMOKE_NORMAL).entityFilter(input -> {
                return ((input instanceof EntityImmaterial) || input.field_70128_L) ? false : true;
            }).trace(playerIn, true)) != null && trace_result.getResultEntity() != null) {
                EntityMultipleLives entityMultipleLives = (EntityLivingBase) trace_result.getResultEntity();
                if (entityMultipleLives instanceof EntityMultipleLives) {
                    EntityMultipleLives ml = entityMultipleLives;
                    switch (getMode(stack)) {
                        case INSTANT:
                            ml.takeawayNumLives(ml.remainingLives() + 1);
                            break;
                        case ONE_LIFE:
                            ml.takeawayNumLives(100);
                            break;
                    }
                } else {
                    entityMultipleLives.func_70606_j(0.0f);
                }
                double size = entityMultipleLives.func_174813_aQ().func_72320_b();
                Vec3d loc = new Vec3d(((EntityLivingBase) entityMultipleLives).field_70165_t, ((EntityLivingBase) entityMultipleLives).field_70163_u + ((double) (((EntityLivingBase) entityMultipleLives).field_70131_O / 2.0f)), ((EntityLivingBase) entityMultipleLives).field_70161_v);
                CustomParticleConfig config = new CustomParticleConfig();
                config.setCount(10);
                config.createInstance().setParticle(ParticleInit.PRISMATIC_EXPLOSION_TYPE1).setSpread(size / 2.0d, size / 2.0d, size / 2.0d).setIgnoreRange(true);
                config.createInstance().setParticle(ParticleInit.PRISMATIC_EXPLOSION_TYPE2).setSpread(size / 2.0d, size / 2.0d, size / 2.0d).setIgnoreRange(true);
                config.createInstance().setParticle(ParticleInit.PRISMATIC_EXPLOSION_TYPE3).setSpread(size / 2.0d, size / 2.0d, size / 2.0d).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(worldIn, config, loc);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 50;
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        cycleMode(stack);
    }
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.getFormatting(TextFmt.Dark_Red, TextFmt.Bold) + "Developer Item");
    }
    private void cycleMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74768_a("blade_mode", (stack.func_77978_p().func_74762_e("blade_mode") + 1) % BladeMode.values().length);
    }
    private BladeMode getMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            return BladeMode.INSTANT;
        }
        return BladeMode.values()[stack.func_77978_p().func_74762_e("blade_mode")];
    }
    public String getHighlightTip(ItemStack item, String displayName) {
        switch (getMode(item)) {
            case INSTANT:
                return displayName + " - Instant Kill";
            case ONE_LIFE:
                return displayName + " - Remove Life";
            default:
                return displayName;
        }
    }
    public IRarity getForgeRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }
}
