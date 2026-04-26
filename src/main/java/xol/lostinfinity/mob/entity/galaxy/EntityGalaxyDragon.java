package xol.lostinfinity.mob.entity.galaxy;
import java.util.Collections;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.projectile.entity.EntityGalaxyDragonFireball;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.data.RayTraceBuilder;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityGalaxyDragon extends EntityMultipleLivesMount implements IConditionalDamage {
    private static final Vec3d GROUND_OFFSET = new Vec3d(0.0d, 1.3d, 1.0d);
    private static final Vec3d AIR_OFFSET = new Vec3d(0.0d, 0.765d, 1.0d);
    private long nextFireballAttack;
    public EntityGalaxyDragon(World worldIn) {
        super(worldIn);
        func_70105_a(2.0f, 1.53f);
        this.field_70138_W = 5.0f;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }
    public float func_70047_e() {
        return this.field_70131_O;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 15;
    }
    public void fireBreathAttack(EntityPlayer driver) {
        CustomRayTraceResult result = RayTraceBuilder.entity(EntityLivingBase.class, 30).maxEntity(0).raySize(2.625f).custom((lastPos, currentPos, distance) -> {
            float spread = ((distance * distance) / 180.0f) + 0.25f;
            CustomParticleConfig config = new CustomParticleConfig();
            config.createInstance().setCount(((int) distance) / 3).setParticle(ParticleInit.DRAGON_FIRE).setSpread(spread, spread, spread).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config, currentPos);
        }).trace(this.field_70170_p, this, getHeadOffset(), driver.func_70040_Z());
        if (result == null) {
            return;
        }
        Iterator<Entity> it = result.getResultEntities().iterator();
        while (it.hasNext()) {
            EntityLivingBase entityLivingBase = (Entity) it.next();
            if (entityLivingBase != this && entityLivingBase != driver) {
                EntityLivingBase livingBase = entityLivingBase;
                IMaxAttack.dealTrueDamage(driver, livingBase, livingBase.func_110138_aP() * 0.05f, Collections.singletonList("Aquatic"));
            }
        }
        func_184185_a(SoundInit.DRAGON_FIRE_BREATH, 0.7f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
    }
    public void fireballAttack(EntityPlayer driver) {
        if (System.currentTimeMillis() < this.nextFireballAttack) {
            return;
        }
        Vec3d driverVec = driver.func_70040_Z();
        Vec3d location = getHeadOffset();
        EntityGalaxyDragonFireball fireball = new EntityGalaxyDragonFireball(this.field_70170_p, location.field_72450_a, location.field_72448_b, location.field_72449_c);
        fireball.setThrower(this);
        fireball.setSecondaryThrower(driver);
        Vec3d driverVec2 = driverVec.func_186678_a(3.0d);
        fireball.func_70024_g(driverVec2.field_72450_a, driverVec2.field_72448_b, driverVec2.field_72449_c);
        this.field_70170_p.func_72838_d(fireball);
        func_184185_a(SoundInit.DRAGON_FIRE_BALL, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
        this.nextFireballAttack = System.currentTimeMillis() + 500;
    }
    public Vec3d getHeadOffset() {
        return func_174791_d().func_178787_e((this.isActuallyOnGround ? GROUND_OFFSET : AIR_OFFSET).func_178785_b((-this.field_70177_z) * 0.017453292f));
    }
    protected float func_70599_aP() {
        return 0.3f;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount
    public double func_70042_X() {
        return super.func_70042_X() - 0.25d;
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.DRAGON_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.DRAGON_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.DRAGON_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return attacker != this.owner;
    }
}
