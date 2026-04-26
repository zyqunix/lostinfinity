package xol.lostinfinity.mob.entity.deviant.titan;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityDeviantTitan;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/titan/EntityTitanMagmacube.class */
public class EntityTitanMagmacube extends EntityDeviantTitan implements IMaxAttack {
    public EntityTitanMagmacube(World worldIn) {
        super(worldIn);
        func_70105_a(4.0f, 4.0f);
        func_189654_d(true);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.5d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70173_aa % 30 == 0) {
            func_70024_g(0.0d, 2.0d, 0.0d);
            func_184185_a(SoundEvents.field_187762_di, 1.0f, 0.5f + this.field_70146_Z.nextFloat());
        }
        this.field_70181_x -= 0.25d;
        float scl = 4.5f + MathHelper.func_76126_a(this.field_70173_aa * 0.1f);
        func_70105_a(scl, scl);
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        if (!this.field_70170_p.field_72995_K) {
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(20.0d, 20.0d, 20.0d))) {
                if (!near_pl.func_184812_l_()) {
                    near_pl.func_70024_g(Math.signum(this.field_70165_t - near_pl.field_70165_t) * 0.5d, Math.signum(this.field_70163_u - near_pl.field_70163_u) * 0.5d, Math.signum(this.field_70161_v - near_pl.field_70161_v) * 0.5d);
                    near_pl.field_70133_I = true;
                }
            }
            this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5, this.field_70146_Z.nextDouble() * 3.0d, 0.3d, this.field_70146_Z.nextDouble() * 3.0d, 0.15000000596046448d, new int[0]);
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187758_dg;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187760_dh;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187764_dj;
    }

    protected ResourceLocation func_184647_J() {
        if (onFinalLife()) {
            return LootTableRegistry.ENTITIES_TITAN_MAGMACUBE;
        }
        return null;
    }
}
