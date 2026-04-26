package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityRockworm.class */
public class EntityRockworm extends EntityMultipleLives implements IMaxAttack, IConditionalDamage {
    private static final DataParameter<Boolean> AWAKE = EntityDataManager.func_187226_a(EntityGrubber.class, DataSerializers.field_187198_h);
    private int sleepTimer;

    public EntityRockworm(World worldIn) {
        super(worldIn);
        this.sleepTimer = 0;
        func_70105_a(1.5f, 1.5f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(AWAKE, false);
    }

    public boolean isAwake() {
        return ((Boolean) this.field_70180_af.func_187225_a(AWAKE)).booleanValue();
    }

    public void setAwake(boolean awoke) {
        this.field_70180_af.func_187227_b(AWAKE, Boolean.valueOf(awoke));
        if (!awoke) {
            this.field_70714_bg.field_75782_a.clear();
            this.field_70715_bh.field_75782_a.clear();
        } else {
            initBasicTasks(this);
        }
        this.sleepTimer = 0;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && isAwake() && func_70638_az() == null) {
            if (this.sleepTimer == 200) {
                setAwake(false);
            }
            this.sleepTimer++;
        }
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            if (IMaxAttack.dealTrueDamage(this, func_70638_az(), func_70638_az().func_110138_aP() * 0.5f).didSuccessfulHit()) {
                func_70638_az().func_70690_d(new PotionEffect(PotionInit.PHASED, 200));
                return true;
            }
            return true;
        }
        return false;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.42d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.ROCKWORM_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.ROCKWORM_HURT;
    }

    protected SoundEvent func_184639_G() {
        return isAwake() ? SoundInit.ROCKWORM_AMBIENT : SoundInit.ROCKWORM_SLEEPING;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.func_175623_d(func_180425_c()) && isAwake()) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.ROCK_TUMBLE, SoundCategory.HOSTILE, 1.5f, 1.0f);
            this.field_70170_p.func_175656_a(func_180425_c(), BlockInit.ioniteOre.func_176223_P());
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return isAwake();
    }
}
