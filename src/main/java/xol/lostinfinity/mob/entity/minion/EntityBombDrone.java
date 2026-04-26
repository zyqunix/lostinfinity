package xol.lostinfinity.mob.entity.minion;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityBombDrone extends EntityMultipleLivesMount implements IConditionalDamage {
    private static final DataParameter<Float> OWNER_POS_X = EntityDataManager.func_187226_a(EntityBombDrone.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> OWNER_POS_Y = EntityDataManager.func_187226_a(EntityBombDrone.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> OWNER_POS_Z = EntityDataManager.func_187226_a(EntityBombDrone.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> OWNER_PITCH = EntityDataManager.func_187226_a(EntityBombDrone.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> OWNER_YAW = EntityDataManager.func_187226_a(EntityBombDrone.class, DataSerializers.field_187193_c);
    public EntityBombDrone(World worldIn) {
        super(worldIn);
        func_70105_a(0.6f, 0.6f);
        this.field_70138_W = 5.0f;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(OWNER_POS_X, Float.valueOf((float) this.field_70165_t));
        this.field_70180_af.func_187214_a(OWNER_POS_Y, Float.valueOf((float) this.field_70163_u));
        this.field_70180_af.func_187214_a(OWNER_POS_Z, Float.valueOf((float) this.field_70161_v));
        this.field_70180_af.func_187214_a(OWNER_PITCH, Float.valueOf(this.field_70125_A));
        this.field_70180_af.func_187214_a(OWNER_YAW, Float.valueOf(this.field_70177_z));
    }
    public void func_70106_y() {
        super.func_70106_y();
        if (!this.field_70170_p.field_72995_K || this.owner == Minecraft.func_71410_x().field_71439_g) {
            resetOwnerPosition();
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount
    public void setOwner(EntityPlayer player) {
        super.setOwner(player);
        this.field_70180_af.func_187227_b(OWNER_POS_X, Float.valueOf((float) player.field_70165_t));
        this.field_70180_af.func_187227_b(OWNER_POS_Y, Float.valueOf((float) player.field_70163_u));
        this.field_70180_af.func_187227_b(OWNER_POS_Z, Float.valueOf((float) player.field_70161_v));
        this.field_70180_af.func_187227_b(OWNER_PITCH, Float.valueOf(player.field_70125_A));
        this.field_70180_af.func_187227_b(OWNER_YAW, Float.valueOf(player.field_70759_as));
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.owner != null && this.field_70173_aa % 5 == 0) {
                this.owner.func_70690_d(new PotionEffect(PotionInit.PROTECTED, 8, 0));
            }
            if (this.field_70173_aa % 20 == 0) {
                func_184185_a(SoundInit.BOMB_TICK, 1.0f, 1.0f);
            }
        }
        if (!this.field_70170_p.field_72995_K && func_184179_bs() == null) {
            func_70106_y();
            CustomParticleConfig config = new CustomParticleConfig();
            config.createInstance().setIgnoreRange(true).setParticle(ParticleInit.EXPLOSION_RED).setSpread(5.0d, 5.0d, 5.0d).setCount(5);
            IParticleSpawner.spawnParticle(this.field_70170_p, config, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            CustomParticleConfig config2 = new CustomParticleConfig();
            config2.createInstance().setIgnoreRange(true).setParticle(ParticleInit.EXPLOSION_YELLOW).setSpread(5.0d, 5.0d, 5.0d).setCount(5);
            IParticleSpawner.spawnParticle(this.field_70170_p, config2, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            CustomParticleConfig config3 = new CustomParticleConfig();
            config3.createInstance().setIgnoreRange(true).setParticle(ParticleInit.EXPLOSION_ORANGE).setSpread(5.0d, 5.0d, 5.0d).setCount(5);
            IParticleSpawner.spawnParticle(this.field_70170_p, config3, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            func_184185_a(SoundInit.DEEP_EXPLOSION, 1.0f, 1.0f);
            for (EntityLivingBase target : this.field_70170_p.func_175647_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(5.0d, 5.0d, 5.0d), (v1) -> {
                return validateTarget(v1);
            })) {
                IMaxAttack.dealTrueDamage(this.owner, target, target.func_110138_aP() * 1.1f);
            }
        }
    }
    public boolean func_70097_a(DamageSource source, float amount) {
        return source.func_76346_g() != this.owner && super.func_70097_a(source, amount);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount
    public double func_70042_X() {
        return -0.85d;
    }
    public boolean func_184215_y(Entity entityIn) {
        return entityIn != this.owner && super.func_184215_y(entityIn);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 5;
    }
    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return attacker != this.owner;
    }
    protected void resetOwnerPosition() {
        float posX = ((Float) this.field_70180_af.func_187225_a(OWNER_POS_X)).floatValue();
        float posY = ((Float) this.field_70180_af.func_187225_a(OWNER_POS_Y)).floatValue();
        float posZ = ((Float) this.field_70180_af.func_187225_a(OWNER_POS_Z)).floatValue();
        float pitch = ((Float) this.field_70180_af.func_187225_a(OWNER_PITCH)).floatValue();
        float yaw = ((Float) this.field_70180_af.func_187225_a(OWNER_YAW)).floatValue();
        this.owner.field_70759_as = yaw;
        this.owner.field_70177_z = yaw;
        this.owner.field_70125_A = pitch;
        this.owner.func_70634_a(posX, posY, posZ);
    }
    protected boolean validateTarget(Entity input) {
        if (!(input instanceof EntityLivingBase) || (input instanceof EntityImmaterial) || input == func_70902_q() || input.field_70128_L || ((EntityLivingBase) input).func_110143_aJ() <= 0.0f) {
            return false;
        }
        return input instanceof EntityPlayer ? (((EntityPlayer) input).func_184812_l_() || ((EntityPlayer) input).func_175149_v()) ? false : true : ((input instanceof IEntityOwnable) && ((IEntityOwnable) input).func_70902_q() == func_70902_q()) ? false : true;
    }
}
