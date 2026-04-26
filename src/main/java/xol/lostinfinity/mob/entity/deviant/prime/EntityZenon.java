package xol.lostinfinity.mob.entity.deviant.prime;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantPrime;
import xol.lostinfinity.mob.entity.classify.IEntityReactive;
import xol.lostinfinity.projectile.entity.EntityZenonShot;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/prime/EntityZenon.class */
public class EntityZenon extends EntityDeviantPrime implements IMaxAttack, IEntityReactive {
    private static final DataParameter<Boolean> REFLECT = EntityDataManager.func_187226_a(EntityZenon.class, DataSerializers.field_187198_h);

    public EntityZenon(World worldIn) {
        super(worldIn);
        func_70105_a(1.75f, 3.2f);
    }

    public boolean getReflect() {
        return ((Boolean) this.field_70180_af.func_187225_a(REFLECT)).booleanValue();
    }

    public void setReflect(boolean reflect) {
        this.field_70180_af.func_187227_b(REFLECT, Boolean.valueOf(reflect));
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4000.0d);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(REFLECT, false);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealTrueDamage(this, func_70638_az(), func_70638_az().func_110138_aP() * 0.6f);
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            EntityLivingBase target = func_70638_az();
            if (this.field_70173_aa % 80 == 0) {
                for (EntityZenonShot shot : this.field_70170_p.func_72872_a(EntityZenonShot.class, new AxisAlignedBB(func_180425_c()).func_186662_g(15.0d))) {
                    Vec3d returnPos = new Vec3d(this.field_70165_t, this.field_70163_u + (((double) this.field_70131_O) / 2.0d), this.field_70161_v);
                    Vec3d dir = returnPos.func_178788_d(shot.func_174791_d()).func_72432_b();
                    shot.field_70159_w = dir.field_72450_a * 1.0d;
                    shot.field_70181_x = dir.field_72448_b * 1.0d;
                    shot.field_70179_y = dir.field_72449_c * 1.0d;
                    shot.clearHits();
                    shot.field_70133_I = true;
                    this.field_70170_p.func_184133_a((EntityPlayer) null, shot.func_180425_c(), SoundInit.LASER_WEAPON_8, SoundCategory.HOSTILE, 1.1f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
                }
            }
            if (this.field_70173_aa % 10 == 0 && target != null) {
                this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.LASER_WEAPON_5, SoundCategory.HOSTILE, 1.25f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
                EntityZenonShot shot2 = new EntityZenonShot(this.field_70170_p, this);
                Vec3d start = new Vec3d(this.field_70165_t, this.field_70163_u + (((double) this.field_70131_O) / 2.0d), this.field_70161_v);
                Vec3d targetVec = new Vec3d(target.field_70165_t, target.field_70163_u + ((double) (target.field_70131_O / 2.0f)), target.field_70161_v);
                Vec3d dir2 = targetVec.func_178788_d(start).func_72432_b();
                shot2.func_70634_a(start.field_72450_a, start.field_72448_b, start.field_72449_c);
                shot2.func_70186_c(dir2.field_72450_a, dir2.field_72448_b, dir2.field_72449_c, 1.0f, 2.0f);
                this.field_70170_p.func_72838_d(shot2);
            }
            if (this.field_70173_aa % 100 == 0) {
                setReflect(!getReflect());
                this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.LASER_WEAPON_7, SoundCategory.HOSTILE, 2.0f, 0.6f + (this.field_70146_Z.nextFloat() * 0.4f));
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantPrime, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 75;
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.GENERIC_STYLE1_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.GENERIC_STYLE1_HURT;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantPrime
    protected String primeName() {
        return "Zenon";
    }

    @Override // xol.lostinfinity.mob.entity.classify.IEntityReactive
    public float hitEffect(EntityLivingBase attacker, float damage) {
        if (getReflect()) {
            IMaxAttack.dealTrueDamage(this, attacker, damage * 0.1f);
            return 0.0f;
        }
        return damage;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantPrime
    protected Item primeDrop() {
        return ItemInit.deviantFragmentBL;
    }
}
