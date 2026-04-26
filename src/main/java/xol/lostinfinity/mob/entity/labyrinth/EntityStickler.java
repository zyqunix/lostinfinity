package xol.lostinfinity.mob.entity.labyrinth;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityStickler extends EntityMultipleLives implements IMaxAttack {
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.func_187226_a(EntityStickler.class, DataSerializers.field_187198_h);
    public EntityStickler(World worldIn) {
        super(worldIn);
        func_70105_a(0.75f, 0.75f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(ATTACKING, false);
    }
    public boolean getAngry() {
        return ((Boolean) this.field_70180_af.func_187225_a(ATTACKING)).booleanValue();
    }
    public void setAngry(boolean f) {
        this.field_70180_af.func_187227_b(ATTACKING, Boolean.valueOf(f));
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public boolean func_70652_k(Entity entity) {
        this.field_70143_R = -1.0f;
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4);
            return true;
        }
        return false;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            setAngry(func_70638_az() != null);
            if (func_70638_az() != null && this.field_70173_aa % 60 == 0) {
                explode(false);
            }
        }
        if (func_70638_az() == null) {
            this.field_70181_x += 0.10000000149011612d;
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            explode(true);
        }
    }
    private void explode(boolean big) {
        func_184185_a(SoundInit.STICKLER_ABILITY, big ? 2.0f : 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
        for (EntityLivingBase near_pl : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(big ? 8.0d : 3.0d))) {
            if (!(near_pl instanceof EntityStickler) && !near_pl.field_70128_L) {
                IMaxAttack.dealMaxHealth(this, near_pl, 2);
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.GOO_RING).setSpread(2.0d, 1.0d, 2.0d).setCount(5).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u + ((double) this.field_70131_O), this.field_70161_v);
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STICKLER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STICKLER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.STICKLER_AMBIENT;
    }
    protected boolean func_70692_ba() {
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }
    protected ResourceLocation func_184647_J() {
        if (this.field_70146_Z.nextInt(7) == 0) {
            return LootTableRegistry.LABYRINTH_MIDDLE;
        }
        return null;
    }
}
