package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemBladeOfConqueror extends ItemCooldownSword implements IMaxAttack, ISwitchModels, IModeSelect, ICustomRaytrace {
    public ItemBladeOfConqueror(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_GALAXY);
        setModelSwitch("attack", this, 3);
    }
    @Override // xol.lostinfinity.item.weapon.ItemCooldownSword
    protected int getCooldown() {
        return 1500;
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                if (!playerIn.func_70093_af()) {
                    CustomRayTraceResult trace = forwardTrace(worldIn, playerIn, 10);
                    BlockPos start_pos = playerIn.func_180425_c();
                    BlockPos result_pos = trace.getResultPos();
                    int divisor = 3;
                    if (playerIn.field_71075_bZ.field_75100_b) {
                        divisor = 6;
                    }
                    worldIn.func_184133_a((EntityPlayer) null, start_pos, SoundInit.DASH, SoundCategory.MASTER, 1.0f, 1.0f);
                    worldIn.func_184133_a((EntityPlayer) null, result_pos, SoundInit.DASH, SoundCategory.MASTER, 1.0f, 1.0f);
                    playerIn.func_70024_g((start_pos.func_177958_n() - result_pos.func_177958_n()) / (-divisor), 0.3d, (start_pos.func_177952_p() - result_pos.func_177952_p()) / (-divisor));
                    playerIn.field_70133_I = true;
                } else {
                    CustomRayTraceResult trace2 = forwardTrace(worldIn, playerIn, 2);
                    BlockPos result_pos2 = trace2.getResultPos();
                    for (EntityLivingBase near_pl : worldIn.func_72872_a(EntityLivingBase.class, playerIn.func_174813_aQ().func_72314_b(25.0d, 3.0d, 25.0d))) {
                        if (!near_pl.func_110124_au().equals(playerIn.func_110124_au())) {
                            near_pl.func_70634_a(result_pos2.func_177958_n(), result_pos2.func_177956_o(), result_pos2.func_177952_p());
                        }
                    }
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.WARP).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(worldIn, config1, result_pos2.func_177958_n(), ((double) result_pos2.func_177956_o()) + 0.5d, result_pos2.func_177952_p());
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        World world = attacker.field_70170_p;
        int attack_style = stack.func_77978_p().func_74762_e("attack_data");
        if (attack_style == 0) {
            if (!world.field_72995_K) {
                if (!attacker.field_70170_p.func_175623_d(attacker.func_180425_c().func_177977_b())) {
                    for (EntityLivingBase near_pl : attacker.field_70170_p.func_72872_a(EntityLivingBase.class, target.func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
                        if (!near_pl.func_110124_au().equals(attacker.func_110124_au())) {
                            IMaxAttack.dealTrueDamage(attacker, near_pl, near_pl.func_110138_aP() * 0.6f);
                        }
                    }
                    float f = 0.0f;
                    while (true) {
                        float angle = f;
                        if (angle <= 6.283185307179586d) {
                            double velocity_x = 6.0d * Math.cos(angle);
                            double velocity_z = 6.0d * Math.sin(angle);
                            CustomParticleConfig config1 = new CustomParticleConfig();
                            config1.createInstance().setParticle(ParticleInit.SLAM).setSpread(1.0d, 1.0d, 1.0d).setCount(2).setIgnoreRange(true);
                            IParticleSpawner.spawnParticle(world, config1, attacker.field_70165_t + velocity_x, attacker.field_70163_u + 3.0d, attacker.field_70161_v + velocity_z);
                            f = (float) (((double) angle) + 0.3141592653589793d);
                        } else {
                            world.func_184133_a((EntityPlayer) null, target.func_180425_c(), SoundInit.GROUND_SLAM, SoundCategory.MASTER, 2.0f, 1.0f);
                            return true;
                        }
                    }
                } else {
                    IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP() * 0.6f);
                    return true;
                }
            } else {
                return true;
            }
        } else {
            if (attack_style == 1) {
                if (!world.field_72995_K) {
                    attacker.func_70024_g(0.0d, 4.0d, 0.0d);
                    attacker.field_70133_I = true;
                    for (EntityLivingBase near_pl2 : attacker.field_70170_p.func_72872_a(EntityLivingBase.class, target.func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
                        near_pl2.func_70024_g(0.0d, 4.0d, 0.0d);
                        near_pl2.field_70133_I = true;
                    }
                }
                stack.func_77978_p().func_74768_a("attack_data", 2);
                return true;
            }
            stack.func_77978_p().func_74768_a("attack_data", 1);
            if (!world.field_72995_K) {
                for (EntityLivingBase near_pl3 : attacker.field_70170_p.func_72872_a(EntityLivingBase.class, target.func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
                    if (!near_pl3.func_110124_au().equals(attacker.func_110124_au()) && near_pl3.field_70163_u >= 100.0d) {
                        IMaxAttack.dealTrueDamage(attacker, near_pl3, near_pl3.func_110138_aP() * 3.0f);
                    }
                }
                return true;
            }
            return true;
        }
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Green + "Deals 60% of target's health as True Damage.");
        tooltip.add(TextFmt.Red + "Uses AOE ground slam attack.");
        tooltip.add(TextFmt.Aqua + "Can toggle duality gravity combo instead, dealing 300% health as True Damage.");
        tooltip.add(TextFmt.Gold + "Special activatable abilities:");
        tooltip.add(TextFmt.Gold + "Right Click: Dash");
        tooltip.add(TextFmt.Gold + "Shift-Right Click: Warp nearby enemies in front of you.");
        tooltip.add(TextFmt.Italic + "True damage cannot be blocked or reflected.");
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundEvents.field_187750_dc, SoundCategory.MASTER, 2.0f, 1.0f);
        int attack_style = stack.func_77978_p().func_74762_e("attack_data");
        if (attack_style == 0) {
            stack.func_77978_p().func_74768_a("attack_data", 1);
        } else {
            stack.func_77978_p().func_74768_a("attack_data", 0);
        }
        if (!player.field_70170_p.field_72995_K) {
            player.func_145747_a(new TextComponentString(TextFmt.Aqua + "Gravity combo " + (attack_style == 0 ? "enabled" : "disabled")));
        }
    }
}
