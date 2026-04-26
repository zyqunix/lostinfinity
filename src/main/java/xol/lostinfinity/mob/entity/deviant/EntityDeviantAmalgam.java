package xol.lostinfinity.mob.entity.deviant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.projectile.entity.EntitySkullShot;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDeviantAmalgam extends EntityDeviantMob implements IMaxAttack {
    private boolean animationState;
    private boolean cageVisible;
    public EntityDeviantAmalgam(World worldIn) {
        super(worldIn);
        this.animationState = false;
        this.cageVisible = false;
        func_70105_a(3.0f, 5.5f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    public boolean func_180427_aV() {
        return true;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.54d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2000.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 6 - getMutation());
            return true;
        }
        return false;
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187530_aT;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187864_fh;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187773_eO;
    }
    public void func_70636_d() {
        super.func_70636_d();
        EntityLivingBase target = func_70638_az();
        if (this.field_70173_aa % (12 - (2 * getMutation())) == 0 && target != null) {
            if (!this.field_70170_p.field_72995_K) {
                EntitySkullShot shot = new EntitySkullShot(this.field_70170_p, this);
                double d0 = target.field_70165_t - this.field_70165_t;
                double d1 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 6.0f))) - shot.field_70163_u;
                double d2 = target.field_70161_v - this.field_70161_v;
                double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 2.0f, 0.0f);
                this.field_70170_p.func_72838_d(shot);
            }
            func_184185_a(SoundEvents.field_193784_dd, 1.0f, 1.0f);
        }
        if (this.animationState) {
            if (this.field_70173_aa % 3 == 0) {
                this.cageVisible = !this.cageVisible;
            }
            if (this.field_70173_aa % (50 - (getMutation() * 7)) == 0) {
                this.cageVisible = false;
                this.animationState = false;
                if (!this.field_70170_p.field_72995_K) {
                    this.field_70170_p.func_72876_a((Entity) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, 9.0f, false);
                    double range = 4.0d + ((double) (5 * getMutation()));
                    for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(range, range, range))) {
                        if (!near_pl.func_184812_l_()) {
                            IMaxAttack.dealMaxHealth(this, near_pl, 5 - getMutation());
                        }
                    }
                }
            }
        } else if (this.field_70173_aa % (100 - (getMutation() * 14)) == 0) {
            this.animationState = true;
            func_184185_a(SoundEvents.field_187572_ar, 2.0f, 1.0f);
        }
        if (this.field_70173_aa % 120 == 0) {
            if (this.field_70170_p.field_72995_K) {
                for (int i = 0; i < 14; i++) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_WITCH, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
                }
            }
            this.field_70170_p.func_184134_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundEvents.field_187534_aX, SoundCategory.HOSTILE, 1.5f, 0.5f + this.field_70170_p.field_73012_v.nextFloat(), true);
            func_70690_d(new PotionEffect(MobEffects.field_76441_p, 40));
        }
        if (func_82150_aj() && this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N) * 3.0d), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N) * 3.0d), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
    public boolean getCageVisible() {
        return this.cageVisible;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return getMutation() == 0 ? 8 : 4;
    }
}
