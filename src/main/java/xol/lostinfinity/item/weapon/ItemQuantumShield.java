package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IMaxNullable;
import xol.lostinfinity.item.classify.IMaxReducible;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemQuantumShield extends Item implements IMaxAttack, IMaxReducible, IMaxNullable {
    public ItemQuantumShield(String regName) {
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    @Override // xol.lostinfinity.item.classify.IMaxReducible
    public float reduceMaxDamage(EntityPlayer player, boolean isMainHand, float damage, float reductionMultiplier, ItemStack stack) {
        float newMulti;
        if (player.func_110143_aJ() > player.func_110138_aP() / 2.0f) {
            newMulti = reductionMultiplier - 0.2f;
        } else {
            newMulti = reductionMultiplier - 0.5f;
        }
        return newMulti;
    }
    @Override // xol.lostinfinity.item.classify.IMaxNullable
    public float nullableReaction(EntityPlayer player, boolean isMainHand, float originalDamage, float newDamage, ItemStack stack) {
        float mitigated_damage = originalDamage - newDamage;
        World world = player.field_70170_p;
        if (!world.field_72995_K) {
            world.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.ITEM_AXIOMAVORUM, SoundCategory.MASTER, 1.0f, 0.5f + world.field_73012_v.nextFloat());
            for (EntityLivingBase near_creature : player.field_70170_p.func_72872_a(EntityLivingBase.class, player.func_174813_aQ().func_72314_b(30.0d, 30.0d, 30.0d))) {
                if (!near_creature.func_110124_au().equals(player.func_110124_au())) {
                    IMaxAttack.dealTrueDamage(player, near_creature, mitigated_damage);
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.QUANTUM_MARK).setSpread(2.0d, 1.0d, 2.0d).setCount(10).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(world, config1, near_creature.field_70165_t, near_creature.field_70163_u + ((double) (near_creature.field_70131_O / 2.0f)), near_creature.field_70161_v);
                }
            }
        }
        return newDamage;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "When held, reduces max health damage taken by 20%.");
        tooltip.add(TextFmt.Gold + "When below 50% health, reduce max health damage by 50% instead.");
        tooltip.add(TextFmt.Aqua + "Mitigated damage is dealt to all creatures in a 30 block radius.");
    }
}
