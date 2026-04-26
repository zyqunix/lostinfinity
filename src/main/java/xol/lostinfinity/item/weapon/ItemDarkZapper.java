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
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemDarkZapper extends ItemCooldownSword implements IMaxAttack, ICustomRaytrace, ICustomHoldPose, ISwitchModels, IModeSelect {
    public ItemDarkZapper(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setModelSwitch("zaptype", this, 2);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        int attack_style = stack.func_77978_p().func_74762_e("zaptype_data");
        if (attack_style == 0 && !showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                CustomRayTraceResult trace_result = entityTrace(worldIn, playerIn, 45, EntityLivingBase.class);
                if (trace_result != null && trace_result.getResultEntity() != null) {
                    EntityLivingBase hit_entity = trace_result.getResultEntity();
                    int level = 1;
                    if (hit_entity.func_70644_a(PotionInit.SHOCKED)) {
                        level = hit_entity.func_70660_b(PotionInit.SHOCKED).func_76458_c() + 1;
                    }
                    CustomDamageResult result = IMaxAttack.dealMaxHealth((Entity) playerIn, hit_entity, 10, level);
                    if (result.didSuccessfulHit()) {
                        hit_entity.func_70690_d(new PotionEffect(PotionInit.SHOCKED, 80, level));
                    }
                    if (!result.wasTargetKilled()) {
                        IMaxAttack.dealTrueDamage(playerIn, hit_entity, hit_entity.func_110138_aP() * 0.033f * level);
                    }
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.ZAP).setSpread(3.0d, 1.0d, 3.0d).setCount(5).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(worldIn, config1, hit_entity.field_70165_t, hit_entity.field_70163_u + ((double) (hit_entity.field_70131_O / 2.0f)), hit_entity.field_70161_v);
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.LASER_WEAPON_3, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        int attack_style = stack.func_77978_p().func_74762_e("zaptype_data");
        if (attack_style == 1 && target.func_70644_a(PotionInit.SHOCKED)) {
            int level = target.func_70660_b(PotionInit.SHOCKED).func_76458_c();
            for (EntityLivingBase near_pl : attacker.field_70170_p.func_72872_a(EntityLivingBase.class, target.func_174813_aQ().func_186662_g(8.0d))) {
                if (!near_pl.func_110124_au().equals(target.func_110124_au()) && !near_pl.func_110124_au().equals(attacker.func_110124_au())) {
                    near_pl.func_70690_d(new PotionEffect(PotionInit.SHOCKED, 100, level));
                }
            }
            return true;
        }
        return true;
    }
    @Override // xol.lostinfinity.item.weapon.ItemCooldownSword
    protected int getCooldown() {
        return 100;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Shocks a target that you are looking at.");
        tooltip.add(TextFmt.Gold + "Increases level of shock if target is already shocked.");
        tooltip.add(TextFmt.Gold + "Deals 10% Max Health Damage per stack of shock.");
        tooltip.add(TextFmt.Gold + "Deals 3% Health True Damage per stack of shock.");
        tooltip.add(TextFmt.Aqua + "Alternate melee form spreads stacks of shock to nearby enemies on hit.");
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
