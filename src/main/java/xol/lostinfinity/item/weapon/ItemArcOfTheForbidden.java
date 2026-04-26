package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IMaxNullable;
import xol.lostinfinity.item.classify.IMaxReducible;
import xol.lostinfinity.projectile.entity.EntityArcBlast;
public class ItemArcOfTheForbidden extends ItemCooldown implements IMaxReducible, IMaxNullable {
    public ItemArcOfTheForbidden(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 2000;
    }
    @Override // xol.lostinfinity.item.classify.IMaxReducible
    public float reduceMaxDamage(EntityPlayer player, boolean isMainHand, float damage, float reductionMultiplier, ItemStack stack) {
        float newMulti = reductionMultiplier;
        if (showDurabilityBar(stack)) {
            newMulti -= 0.25f;
        }
        return newMulti;
    }
    @Override // xol.lostinfinity.item.classify.IMaxNullable
    public float nullableReaction(EntityPlayer player, boolean isMainHand, float originalDamage, float newDamage, ItemStack stack) {
        if (!showDurabilityBar(stack)) {
            World world = player.field_70170_p;
            if (!world.field_72995_K) {
                double x0 = player.field_70165_t;
                double y0 = player.field_70163_u + 1.0d;
                double z0 = player.field_70161_v;
                float f = 0.0f;
                while (true) {
                    float angle = f;
                    if (angle > 6.283185307179586d) {
                        break;
                    }
                    EntityArcBlast shot = new EntityArcBlast(world, player);
                    shot.func_70107_b(x0, y0, z0);
                    double velocity_x = 5.0d * Math.cos(angle);
                    double velocity_z = 5.0d * Math.sin(angle);
                    shot.setThrower(player);
                    shot.calculateVelocity(velocity_x * 0.25d, 0.10000000149011612d, velocity_z * 0.25d);
                    world.func_72838_d(shot);
                    f = (float) (((double) angle) + 0.39269908169872414d);
                }
            }
            player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0f, 0.8f + (player.field_70170_p.field_73012_v.nextFloat() * 0.4f));
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            return 0.0f;
        }
        return newDamage;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Bold + "Reflects 70% of Max Health Damage Taken");
        tooltip.add(TextFmt.Italic + "Ocasionally Blocks a Full Max Health Hit");
        tooltip.add(TextFmt.Aqua + "Fires a Ring of Arc Blasts When Blocking a Hit");
        tooltip.add(TextFmt.Gold + "Counts as an Arc Blaster");
        tooltip.add(TextFmt.Red + "Grants 30% Max Health Damage Reduction While on Cooldown");
    }
}
