package xol.lostinfinity.mob.entity.cthulhu;

import com.google.common.base.Optional;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.common.events.EventsClientRender;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.util.animation.client.AnimationHandler;
import xol.lostinfinity.util.animation.entity.IXolAnimated;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/cthulhu/EntityCelestialStatue.class */
public class EntityCelestialStatue extends AbstractCthulhuMinion implements IXolAnimated, IConditionalDamage {
    private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.func_187226_a(EntityCelestialStatue.class, DataSerializers.field_187203_m);
    private static final DataParameter<Integer> TYPE = EntityDataManager.func_187226_a(EntityCelestialStatue.class, DataSerializers.field_187192_b);
    private static final Vec3d OFFSET_1 = new Vec3d(-8.0d, 25.5d, 0.0d);
    private static final Vec3d OFFSET_2 = new Vec3d(8.0d, 25.5d, 0.0d);
    private final AnimationHandler handler;
    private EntityCthulhu owner;
    private float rot;

    public EntityCelestialStatue(World worldIn) {
        super(worldIn);
        this.handler = new AnimationHandler();
        func_70105_a(10.0f, 30.0f);
    }

    public void func_70636_d() {
        if (getOwner() != null && this.field_70173_aa % 100 == 0) {
            this.rot = LMath.toPitchYaw(getOwner().func_174791_d().func_178786_a(this.field_70165_t, this.field_70163_u, this.field_70161_v)).func_179416_c();
        }
        this.field_70177_z = this.rot;
        this.field_70759_as = this.rot;
        this.field_70761_aq = this.rot;
        super.func_70636_d();
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(OWNER, Optional.absent());
        this.field_70180_af.func_187214_a(TYPE, 0);
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (getOwner() != null) {
            this.rot = LMath.toPitchYaw(getOwner().func_174791_d().func_178786_a(this.field_70165_t, this.field_70163_u, this.field_70161_v)).func_179416_c();
        }
        if (this.field_70170_p.field_72995_K) {
            playAnimation("pose_" + (getType() + 1), 1.0f);
            EventsClientRender.renderForce.put(Integer.valueOf(func_145782_y()), this);
        }
    }

    public Vec3d getBeamOriginOffset() {
        switch (getType()) {
            case 1:
            case 3:
                return OFFSET_2;
            default:
                return OFFSET_1;
        }
    }

    @Override // xol.lostinfinity.mob.entity.cthulhu.AbstractCthulhuMinion, xol.lostinfinity.mob.entity.cthulhu.ICthulhuMinion
    public void setOwner(EntityCthulhu owner) {
        this.owner = owner;
        this.field_70180_af.func_187227_b(OWNER, Optional.of(owner.func_110124_au()));
    }

    @Override // xol.lostinfinity.mob.entity.cthulhu.AbstractCthulhuMinion, xol.lostinfinity.mob.entity.cthulhu.ICthulhuMinion
    public EntityCthulhu getOwner() {
        if (this.owner == null) {
            Optional<UUID> uuidOptional = (Optional) this.field_70180_af.func_187225_a(OWNER);
            if (uuidOptional.isPresent()) {
                UUID uuid = (UUID) uuidOptional.get();
                this.owner = (EntityCthulhu) this.field_70170_p.field_72996_f.stream().filter(entity -> {
                    return entity.func_110124_au().equals(uuid) && (entity instanceof EntityCthulhu);
                }).map(entity2 -> {
                    return (EntityCthulhu) entity2;
                }).findFirst().orElse(null);
            }
        }
        return this.owner;
    }

    public void setType(int i) {
        this.field_70180_af.func_187227_b(TYPE, Integer.valueOf(i));
    }

    public int getType() {
        return ((Integer) this.field_70180_af.func_187225_a(TYPE)).intValue();
    }

    @Override // xol.lostinfinity.util.animation.entity.IXolAnimated
    public AnimationHandler getAnimationHandler() {
        return this.handler;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 10000;
    }

    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return false;
    }
}
