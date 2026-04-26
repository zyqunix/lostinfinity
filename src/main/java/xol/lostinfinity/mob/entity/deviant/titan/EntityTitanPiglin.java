package xol.lostinfinity.mob.entity.deviant.titan;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityDeviantTitan;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityTitanPiglin extends EntityDeviantTitan implements IMaxAttack {
    public EntityTitanPiglin(World worldIn) {
        super(worldIn);
        func_70105_a(3.5f, 7.0f);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70122_E && this.field_70173_aa % 60 == 10) {
                this.field_70181_x = 1.2d;
                return;
            }
            if (this.field_70173_aa % 60 == 30) {
                EntityPlayer dashto = null;
                if (func_70638_az() == null || !(func_70638_az() instanceof EntityPlayer)) {
                    Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d)).iterator();
                    if (it.hasNext()) {
                        EntityPlayer near_pl = (EntityPlayer) it.next();
                        dashto = near_pl;
                        func_70624_b(near_pl);
                    }
                } else {
                    dashto = (EntityPlayer) func_70638_az();
                }
                if (dashto != null) {
                    func_70024_g((dashto.field_70165_t - this.field_70165_t) * 0.245d, (dashto.field_70163_u - this.field_70163_u) * 0.145d, (dashto.field_70161_v - this.field_70161_v) * 0.245d);
                }
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187937_hk;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187938_hl;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187935_hi;
    }
    protected ResourceLocation func_184647_J() {
        if (onFinalLife()) {
            return LootTableRegistry.ENTITIES_TITAN_PIGMAN;
        }
        return null;
    }
}
