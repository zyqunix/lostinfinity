package xol.lostinfinity.mob.entity.minion.andromeda;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.classify.IKnockbackImmunity;
import xol.lostinfinity.mob.entity.classify.ILostMultiPart;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
import xol.lostinfinity.mob.entity.minion.andromeda.EntityAndromedaSegment;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/minion/andromeda/EntityAndromedaController.class */
public class EntityAndromedaController extends EntityMinion implements ILostMultiPart, IKnockbackImmunity {
    private static final List<String> DAMAGE_TYPE = Collections.singletonList("Aquatic");
    private static final int CHARGE_COOLDOWN = 30;
    private static final int TURN_TIME = 30;
    private final EntityAndromedaSegment[] segments;
    private final AndromedaMoveHelper andromedaMoveHelper;
    private EntityLivingBase target;
    private int syncTick;
    private Vec3d syncPos;
    private Attack attack;
    private int chargeCooldown;
    private int turnTick;
    private Vec3d chargeDir;

    public EntityAndromedaController(World worldIn) {
        super(worldIn);
        this.segments = new EntityAndromedaSegment[60];
        this.attack = Attack.CHARGE;
        func_70105_a(0.25f, 0.25f);
        this.field_70145_X = true;
        this.andromedaMoveHelper = new AndromedaMoveHelper(this);
        this.field_70765_h = this.andromedaMoveHelper;
        func_110163_bv();
        populateSegments();
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    protected boolean func_70692_ba() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    protected void onDeath() {
        for (int i = 0; i < this.segments.length; i++) {
            EntityAndromedaSegment segment = this.segments[i];
            segment.func_70106_y();
            this.field_70170_p.func_72973_f(segment);
            segment.cleanUp();
            this.segments[i] = null;
        }
    }

    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    protected void livingUpdate() {
        if (this.field_70170_p.field_72995_K || this.field_70128_L) {
            return;
        }
        if (this.field_70173_aa % 400 == 0) {
            this.attack = Attack.cycle(this.attack);
        }
        if (this.target == null || this.target.field_70128_L || this.target.func_70068_e(this.owner) > 9216.0d || !isInRangeOfOwner()) {
            findClosestTarget();
        }
        if (this.target != null) {
            switch (this.attack) {
                case CHARGE:
                    if (this.chargeCooldown <= 0) {
                        Vec3d targetPos = LMath.getEntityMiddle(this.target);
                        Vec3d delta = LMath.fastNormalize(targetPos.func_178788_d(func_174791_d()));
                        Vec3d motion = LMath.fastNormalize(new Vec3d(this.field_70159_w, this.field_70181_x, this.field_70179_y));
                        Rotations rotations = LMath.lerp(LMath.toPitchYaw(motion), LMath.toPitchYaw(delta), 1.0f / this.turnTick);
                        Vec3d finalMotion = LMath.toLookVec(rotations.func_179415_b(), rotations.func_179416_c());
                        double speed = Math.max(LMath.fastLength(this.target.field_70159_w, this.target.field_70181_x, this.target.field_70179_y) * 1.5d, 2.0d);
                        this.field_70159_w = finalMotion.field_72450_a * speed;
                        this.field_70181_x = finalMotion.field_72448_b * speed;
                        this.field_70179_y = finalMotion.field_72449_c * speed;
                        this.field_70133_I = true;
                        if (func_70092_e(targetPos.field_72450_a, targetPos.field_72448_b, targetPos.field_72449_c) < 9.0d) {
                            this.chargeCooldown = 30;
                        }
                        int i = this.turnTick - 1;
                        this.turnTick = i;
                        if (i <= 0) {
                            this.turnTick = 30;
                        }
                    } else {
                        this.turnTick = 30;
                        if (this.chargeDir == null) {
                            this.chargeDir = new Vec3d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
                        }
                        this.field_70159_w = this.chargeDir.field_72450_a;
                        this.field_70181_x = this.chargeDir.field_72448_b;
                        this.field_70179_y = this.chargeDir.field_72449_c;
                        this.field_70133_I = true;
                        int i2 = this.chargeCooldown - 1;
                        this.chargeCooldown = i2;
                        if (i2 == 0) {
                            this.chargeDir = null;
                        }
                    }
                    break;
                case TRACER:
                    int tick = this.field_70173_aa / 4;
                    if (this.field_70173_aa % 4 == 0) {
                        int segId = tick % this.segments.length;
                        Vec3d pos = this.segments[segId].func_174791_d();
                        Vec3d dir = LMath.fastNormalize(LMath.getEntityMiddle(this.target).func_178788_d(pos));
                        EntityAndromedaChaser chaser = new EntityAndromedaChaser(this.field_70170_p);
                        chaser.setTarget(this.target);
                        chaser.setThrower(this);
                        chaser.setSecondaryThrower(this.owner);
                        chaser.func_70107_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
                        chaser.field_70159_w = dir.field_72450_a;
                        chaser.field_70181_x = dir.field_72448_b;
                        chaser.field_70179_y = dir.field_72449_c;
                        this.field_70170_p.func_72838_d(chaser);
                    }
                    break;
            }
        }
        if ((this.target == null || this.attack != Attack.CHARGE) && (!isInRangeOfOwner() || this.andromedaMoveHelper.hasArrived())) {
            double dX = this.owner.field_70165_t + getRandomDouble(128.0d);
            double dY = this.owner.field_70163_u + 60.0d + getRandomDouble(16.0d);
            double dZ = this.owner.field_70161_v + getRandomDouble(128.0d);
            this.andromedaMoveHelper.func_75642_a(dX, dY, dZ, 1.0d);
        }
        if (!isInRangeOfOwner(96.0d)) {
            this.syncTick = 10;
            this.syncPos = new Vec3d(getRandomDouble(32.0d), 0.0d, getRandomDouble(32.0d));
        }
        if (this.syncTick > 0) {
            float f = 1.0f / this.syncTick;
            func_70634_a(MathHelper.func_151238_b(this.field_70165_t, this.owner.field_70165_t + this.syncPos.field_72450_a, f), this.field_70163_u, MathHelper.func_151238_b(this.field_70161_v, this.owner.field_70161_v + this.syncPos.field_72449_c, f));
            this.syncTick--;
        }
        if (this.field_70173_aa % 10 == 0) {
            Set<Entity> targets = new HashSet<>();
            for (Entity entity : this.segments) {
                List<Entity> segList = this.field_70170_p.func_175674_a(entity, entity.func_174813_aQ(), this::validateTarget);
                targets.addAll(segList);
            }
            if (targets.isEmpty()) {
                return;
            }
            targets.forEach(entity2 -> {
                EntityLivingBase livingBase = (EntityLivingBase) entity2;
                IMaxAttack.dealTrueDamage(this.owner, livingBase, livingBase.func_110138_aP() * 2.0f, DAMAGE_TYPE);
            });
        }
    }

    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    public boolean shouldRender() {
        return true;
    }

    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    protected boolean validateTarget(Entity input) {
        if ((input instanceof EntityAndromedaController) || (input instanceof EntityAndromedaSegment)) {
            return false;
        }
        return super.validateTarget(input);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70128_L) {
            return;
        }
        for (Entity entity : this.segments) {
            this.field_70170_p.func_72866_a(entity, true);
            entity.updatePosition();
        }
    }

    public boolean func_70072_I() {
        return false;
    }

    public boolean func_180799_ab() {
        return false;
    }

    @Nullable
    public Entity[] func_70021_al() {
        if (this.field_70128_L) {
            return null;
        }
        return this.segments;
    }

    @Override // xol.lostinfinity.mob.entity.classify.IKnockbackImmunity
    public float getKnockbackResistance(CustomDamageResult damageResult) {
        return 1.0f;
    }

    @Override // xol.lostinfinity.mob.entity.classify.ILostMultiPart
    public boolean attackEntityFromPart(EntityLivingBase part, DamageSource source, float damage) {
        return false;
    }

    public World func_82194_d() {
        return this.field_70170_p;
    }

    protected boolean isInRangeOfOwner() {
        return isInRangeOfOwner(96.0d);
    }

    protected boolean isInRangeOfOwner(double dist) {
        return func_70092_e(this.owner.field_70165_t, this.field_70163_u, this.owner.field_70161_v) <= dist * dist;
    }

    private void populateSegments() {
        EntityAndromedaSegment lastSegment = new EntityAndromedaSegment.Head(this.field_70170_p, this);
        lastSegment.updatePosition();
        lastSegment.setId(0);
        this.segments[0] = lastSegment;
        for (int i = 1; i < this.segments.length - 1; i++) {
            EntityAndromedaSegment segment = new EntityAndromedaSegment(this.field_70170_p, this, lastSegment);
            segment.updatePosition();
            lastSegment = segment;
            segment.setId(i);
            this.segments[i] = segment;
        }
        EntityAndromedaSegment.Tail tail = new EntityAndromedaSegment.Tail(this.field_70170_p, this, lastSegment);
        tail.updatePosition();
        tail.setId(this.segments.length - 1);
        this.segments[this.segments.length - 1] = tail;
        for (EntityAndromedaSegment entityAndromedaSegment : this.segments) {
            entityAndromedaSegment.setSize(15);
        }
    }

    private void findClosestTarget() {
        this.target = null;
        List<EntityLivingBase> targets = this.field_70170_p.func_175647_a(EntityLivingBase.class, this.owner.func_174813_aQ().func_186662_g(96.0d), (v1) -> {
            return validateTarget(v1);
        });
        Vec3d pos = this.owner.func_174791_d();
        double targetDist = Double.MAX_VALUE;
        for (EntityLivingBase entity : targets) {
            double dist = LMath.getDistanceSquaredToAABB(pos, entity.func_174813_aQ());
            if (dist < targetDist) {
                targetDist = dist;
                this.target = entity;
            }
        }
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/minion/andromeda/EntityAndromedaController$AndromedaMoveHelper.class */
    private static class AndromedaMoveHelper extends EntityMoveHelper {
        private final EntityAndromedaController parentEntity;
        private int turnTick;

        public AndromedaMoveHelper(EntityAndromedaController andromeda) {
            super(andromeda);
            this.turnTick = 20;
            this.parentEntity = andromeda;
        }

        protected boolean hasArrived() {
            double d0 = func_179917_d() - this.parentEntity.field_70165_t;
            double d1 = func_179919_e() - this.parentEntity.field_70163_u;
            double d2 = func_179918_f() - this.parentEntity.field_70161_v;
            double d3 = (d0 * d0) + (d1 * d1) + (d2 * d2);
            return d3 < 36.0d || d3 > 9216.0d;
        }

        public void func_75641_c() {
            if ((this.parentEntity.target == null || this.parentEntity.attack != Attack.CHARGE) && this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                Vec3d delta = getMoveDir(new Vec3d(func_179917_d(), func_179919_e(), func_179918_f()));
                this.parentEntity.field_70159_w = delta.field_72450_a * this.field_75645_e;
                this.parentEntity.field_70181_x = delta.field_72448_b * this.field_75645_e;
                this.parentEntity.field_70179_y = delta.field_72449_c * this.field_75645_e;
            }
        }

        private Vec3d getMoveDir(Vec3d targetPos) {
            Vec3d delta = LMath.fastNormalize(targetPos.func_178788_d(this.parentEntity.func_174791_d()));
            Vec3d motion = LMath.fastNormalize(new Vec3d(this.parentEntity.field_70159_w, this.parentEntity.field_70181_x, this.parentEntity.field_70179_y));
            Rotations rotations = LMath.lerp(LMath.toPitchYaw(motion), LMath.toPitchYaw(delta), 1.0f / this.turnTick);
            int i = this.turnTick - 1;
            this.turnTick = i;
            if (i <= 0) {
                this.turnTick = 20;
            }
            return LMath.toLookVec(rotations.func_179415_b(), rotations.func_179416_c());
        }
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/minion/andromeda/EntityAndromedaController$Attack.class */
    private enum Attack {
        CHARGE,
        TRACER;

        /* JADX INFO: Access modifiers changed from: private */
        public static Attack cycle(Attack attack) {
            return values()[(attack.ordinal() + 1) % values().length];
        }
    }
}
