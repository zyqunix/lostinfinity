package xol.lostinfinity.item.weapon;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IChargeItem;
import xol.lostinfinity.item.classify.IMaxNullable;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemBlackHole extends ItemCooldown implements ISwitchModels, IModeSelect, IChargeItem, IMaxAttack, IMaxNullable {
    public ItemBlackHole(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setModelSwitch("usetype", this, 2);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            ItemStack stack = playerIn.func_184586_b(handIn);
            int type = stack.func_77978_p().func_74762_e("usetype_data");
            if (!worldIn.field_72995_K) {
                if (type == 0) {
                    playerIn.func_70690_d(new PotionEffect(PotionInit.CHARGING, 300));
                } else {
                    playerIn.func_70690_d(new PotionEffect(PotionInit.CHARGING, 100));
                }
                playerIn.field_70170_p.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_10, SoundCategory.PLAYERS, 1.5f, 1.0f);
            }
            int cdVal = type == 0 ? 25000 : 9000;
            stack.func_77978_p().func_74768_a("ComplexCooldown", cdVal);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected boolean hasSimpleCooldown() {
        return false;
    }
    @Override // xol.lostinfinity.item.classify.IMaxNullable
    public float nullableReaction(EntityPlayer player, boolean isMainHand, float originalDamage, float newDamage, ItemStack stack) {
        float returnDamage = newDamage;
        if (player.func_70644_a(PotionInit.CHARGING)) {
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            int mitigated_damage = Math.round(newDamage);
            returnDamage = 0.0f;
            int alreadyStored = stack.func_77978_p().func_74762_e("StoredDamage");
            stack.func_77978_p().func_74768_a("StoredDamage", alreadyStored + mitigated_damage);
        }
        return returnDamage;
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (!showDurabilityBar(stack)) {
            int attack_style = stack.func_77978_p().func_74762_e("usetype_data");
            if (attack_style == 0) {
                stack.func_77978_p().func_74768_a("usetype_data", 1);
            } else {
                stack.func_77978_p().func_74768_a("usetype_data", 0);
            }
        }
    }
    @Override // xol.lostinfinity.item.classify.IChargeItem
    public void endChargeEffect(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int damageStored = stack.func_77978_p().func_74762_e("StoredDamage");
        if (damageStored > 0) {
            int attack_style = stack.func_77978_p().func_74762_e("usetype_data");
            int damageDeal = attack_style == 0 ? Math.floorDiv(damageStored, 4) : damageStored;
            List<EntityLivingBase> near_creatures = new ArrayList<>();
            for (EntityLivingBase near_pl : player.field_70170_p.func_72872_a(EntityLivingBase.class, player.func_174813_aQ().func_72314_b(20.0d, 8.0d, 20.0d))) {
                if (!near_pl.func_110124_au().equals(player.func_110124_au())) {
                    near_creatures.add(near_pl);
                }
            }
            if (near_creatures.size() > 0) {
                int finalDeal = Math.floorDiv(damageDeal, near_creatures.size());
                for (EntityLivingBase near_pl2 : near_creatures) {
                    IMaxAttack.dealTrueDamage(player, near_pl2, finalDeal);
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.SPACE_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(8).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(player.field_70170_p, config1, near_pl2.field_70165_t, near_pl2.field_70163_u + ((double) (near_pl2.field_70131_O / 2.0f)), near_pl2.field_70161_v);
                }
                player.func_145747_a(new TextComponentString(TextFmt.Gold + "You dealt " + damageDeal + " split amongst " + near_creatures.size() + " creatures."));
            }
            CustomParticleConfig config12 = new CustomParticleConfig();
            config12.createInstance().setParticle(ParticleInit.POWER_FIELD).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(player.field_70170_p, config12, player.field_70165_t, player.field_70163_u + ((double) (player.field_70131_O / 2.0f)), player.field_70161_v);
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.ENERGY_PULSE, SoundCategory.PLAYERS, 1.5f, 1.0f);
        }
        stack.func_77978_p().func_74768_a("StoredDamage", 0);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "When held and activated, grants a charging buff.");
        tooltip.add(TextFmt.Gold + "While charging, take no max health damage and store damage prevented.");
        tooltip.add(TextFmt.Gray + "At the end of the charging duration, stored damage is dealt as true damage to all nearby creatures.");
        tooltip.add(TextFmt.Italic + "Damage dealt is split between the creatures.");
        tooltip.add(TextFmt.Light_Purple + "Purple: Long Duration, 25% Of Damage Stored");
        tooltip.add(TextFmt.Red + "Red: Short Duration, 100% Of Damage Stored");
    }
}
