package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemBladeOfTheForbidden extends ItemSword implements IMaxAttack, ISwitchModels, IModeSelect {
    public ItemBladeOfTheForbidden(String regName) {
        super(Item.ToolMaterial.WOOD);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
        setModelSwitch("swordtype", this, 2);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        World world = attacker.field_70170_p;
        int attack_style = stack.func_77978_p().func_74762_e("swordtype_data");
        if (attack_style == 0) {
            float lowestHP = attacker.func_110143_aJ();
            for (EntityLivingBase near_pl : world.func_72872_a(EntityLivingBase.class, attacker.func_174813_aQ().func_72314_b(25.0d, 15.0d, 25.0d))) {
                if (!near_pl.field_70128_L && near_pl.func_110143_aJ() < lowestHP) {
                    lowestHP = near_pl.func_110143_aJ();
                }
            }
            for (EntityLivingBase near_pl2 : world.func_72872_a(EntityLivingBase.class, attacker.func_174813_aQ().func_72314_b(25.0d, 15.0d, 25.0d))) {
                if (!near_pl2.func_110124_au().equals(attacker.func_110124_au())) {
                    near_pl2.func_70606_j(lowestHP);
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.QUANTUM_MARK).setSpread(2.0d, 1.0d, 2.0d).setCount(10).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(world, config1, near_pl2.field_70165_t, near_pl2.field_70163_u + ((double) (near_pl2.field_70131_O / 2.0f)), near_pl2.field_70161_v);
                }
            }
            if (!world.field_72995_K) {
                world.func_184133_a((EntityPlayer) null, attacker.func_180425_c(), SoundInit.GENERIC_POP, SoundCategory.PLAYERS, 0.7f, 0.7f + (attacker.field_70170_p.field_73012_v.nextFloat() * 0.6f));
                return true;
            }
            return true;
        }
        for (EntityLivingBase near_pl3 : world.func_72872_a(EntityLivingBase.class, attacker.func_174813_aQ().func_72314_b(25.0d, 15.0d, 25.0d))) {
            if (!near_pl3.func_110124_au().equals(attacker.func_110124_au())) {
                int healthLossMulti = MathHelper.func_76141_d(100.0f - ((100.0f * (near_pl3.func_110143_aJ() / near_pl3.func_110138_aP())) / 5.0f));
                IMaxAttack.dealMaxHealth((Entity) attacker, near_pl3, 10, healthLossMulti);
            }
        }
        if (!world.field_72995_K) {
            world.func_184133_a((EntityPlayer) null, attacker.func_180425_c(), SoundInit.GENERIC_WEAPON_7, SoundCategory.PLAYERS, 0.7f, 0.7f + (attacker.field_70170_p.field_73012_v.nextFloat() * 0.6f));
            return true;
        }
        return true;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Purple Side: Sets All Nearby Creatures to the Health of the LOWEST Nearby Creatures");
        tooltip.add(TextFmt.Red + "Red Side: Deals Max Health Damage Based on Missing Health To ALL Nearby Creatures");
        tooltip.add(TextFmt.Red + "Red Side: Max 200% Health Damage");
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundEvents.field_187750_dc, SoundCategory.MASTER, 2.0f, 1.0f);
        int attack_style = stack.func_77978_p().func_74762_e("swordtype_data");
        if (attack_style == 0) {
            stack.func_77978_p().func_74768_a("swordtype_data", 1);
        } else {
            stack.func_77978_p().func_74768_a("swordtype_data", 0);
        }
    }
}
