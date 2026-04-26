package xol.lostinfinity.mob.entity.minion.old_andromeda;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.mob.entity.classify.IKnockbackImmunity;
import xol.lostinfinity.mob.entity.classify.ILostMultiPart;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
import xol.lostinfinity.mob.entity.minion.andromeda.EntityAndromedaChaser;
import xol.lostinfinity.mob.entity.minion.old_andromeda.OldAndromedaSegment;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.BezierCurve;
import xol.lostinfinity.util.math.LMath;
public class OldAndromedaController extends EntityMinion implements ILostMultiPart, IKnockbackImmunity {
    private static final List<String> DAMAGE_TYPE = Collections.singletonList("Aquatic");
    private static final int CYCLE_TICK = 100;
    private static final int PHASE_CHANGE_TICK = 20;
    private static final int CHARGE_COOLDOWN = 16;
    private final OldAndromedaSegment[] segments;
    private EntityLivingBase target;
    private Attack attack;
    private Attack nextAttack;
    private int attackCycleTick;
    private int attackTransitionTick;
    private int chargeCooldown;
    private BezierCurve.Node chargeStartNode;
    private BezierCurve.Node chargeEndNode;
    private BezierCurve.Node chargeRandomNode;
    public OldAndromedaController(World worldIn) {
        super(worldIn);
        this.segments = new OldAndromedaSegment[10];
        this.attack = Attack.IDLE;
        this.nextAttack = Attack.IDLE;
        this.attackCycleTick = 100;
        func_70105_a(0.25f, 0.25f);
        this.field_70145_X = true;
        OldAndromedaSegment lastSegment = new OldAndromedaSegment.Head(worldIn, this);
        lastSegment.updatePosition();
        this.segments[0] = lastSegment;
        for (int i = 1; i < this.segments.length - 1; i++) {
            OldAndromedaSegment segment = new OldAndromedaSegment(worldIn, this, lastSegment);
            segment.updatePosition();
            lastSegment = segment;
            this.segments[i] = segment;
        }
        OldAndromedaSegment.Tail tail = new OldAndromedaSegment.Tail(worldIn, this, lastSegment);
        tail.updatePosition();
        this.segments[this.segments.length - 1] = tail;
        for (OldAndromedaSegment oldAndromedaSegment : this.segments) {
            oldAndromedaSegment.setSize(15);
        }
    }
    public void func_70071_h_() {
        super.func_70071_h_();
        for (Entity entity : this.segments) {
            this.field_70170_p.func_72866_a(entity, true);
            entity.updatePosition();
        }
    }
    protected void func_82167_n(Entity entityIn) {
        if (!this.field_70170_p.field_72995_K && isActive() && validateTarget(entityIn)) {
            double dX = entityIn.field_70159_w;
            double dY = entityIn.field_70181_x;
            double dZ = entityIn.field_70179_y;
            CustomDamageResult damageResult = IMaxAttack.dealTrueDamage(func_70902_q(), (EntityLivingBase) entityIn, 100.0f, DAMAGE_TYPE);
            if (damageResult.didSuccessfulHit() && damageResult.targetHealthChanged()) {
                Vec3d dir = LMath.fastNormalize(new Vec3d(this.field_70165_t - this.segments[0].field_70165_t, this.field_70163_u - this.segments[0].field_70163_u, this.field_70161_v - this.segments[0].field_70161_v));
                entityIn.field_70159_w = dX + (dir.field_72450_a * 0.5d);
                entityIn.field_70181_x = dY + (dir.field_72448_b * 0.5d);
                entityIn.field_70179_y = dZ + (dir.field_72449_c * 0.5d);
                entityIn.field_70133_I = true;
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    public void func_70106_y() {
        super.func_70106_y();
        for (Entity entity : this.segments) {
            this.field_70170_p.func_72973_f(entity);
        }
    }
    @Nullable
    public Entity[] func_70021_al() {
        return this.segments;
    }
    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    protected void livingUpdate() {
        EntityPlayer owner = func_70902_q();
        if (owner == null) {
            return;
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.attack != this.nextAttack) {
                if (this.attackTransitionTick >= PHASE_CHANGE_TICK) {
                    this.attackTransitionTick = 0;
                    this.attack = this.nextAttack;
                    func_70902_q().func_145747_a(new TextComponentString("State: " + this.attack.name()));
                }
                if (this.attack != this.nextAttack) {
                    transitionTick();
                    this.attackTransitionTick++;
                }
            }
            if (this.attack == this.nextAttack) {
                if (this.attack == Attack.IDLE || this.attackCycleTick >= 100) {
                    this.attackCycleTick = 0;
                    this.nextAttack = Attack.nextAttack(this.attack);
                }
                if (this.target == null || this.target.field_70128_L) {
                    this.target = null;
                    findClosestTarget();
                    if (this.target == null) {
                        this.nextAttack = Attack.IDLE;
                    }
                }
                if (this.target != null) {
                    setActive(true);
                    attackTick();
                } else if (this.attack == Attack.IDLE) {
                    setActive(false);
                    resetCharge();
                }
                this.attackCycleTick++;
            }
        }
        if (!isActive()) {
            updatePosition();
        }
    }
    static  class AnonymousClass1 {
        static final  int[] $SwitchMap$xol$lostinfinity$mob$entity$minion$old_andromeda$OldAndromedaController$Attack = new int[Attack.values().length];
        static {
            try {
                $SwitchMap$xol$lostinfinity$mob$entity$minion$old_andromeda$OldAndromedaController$Attack[Attack.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$xol$lostinfinity$mob$entity$minion$old_andromeda$OldAndromedaController$Attack[Attack.CHARGE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$xol$lostinfinity$mob$entity$minion$old_andromeda$OldAndromedaController$Attack[Attack.TRACER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$xol$lostinfinity$mob$entity$minion$old_andromeda$OldAndromedaController$Attack[Attack.BEAM.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
    protected void transitionTick() {
        switch (AnonymousClass1.$SwitchMap$xol$lostinfinity$mob$entity$minion$old_andromeda$OldAndromedaController$Attack[this.nextAttack.ordinal()]) {
            case 1:
                float x = MathHelper.func_76126_a(this.field_70173_aa * 0.05f) * 5.0f;
                float y = MathHelper.func_76126_a(this.field_70173_aa * 0.05f) * 0.5f;
                float z = MathHelper.func_76134_b(this.field_70173_aa * 0.05f) * 5.0f;
                Vec3d targetPosition = new Vec3d(this.owner.field_70165_t + ((double) x), this.owner.field_70163_u + 2.0d + ((double) y), this.owner.field_70161_v + ((double) z));
                Vec3d location = LMath.lerp(func_174791_d(), targetPosition, 1.0f / (PHASE_CHANGE_TICK - this.attackTransitionTick));
                func_70107_b(location.field_72450_a, location.field_72448_b, location.field_72449_c);
                break;
            case 2:
                resetCharge();
                this.attackTransitionTick = 0;
                this.attack = this.nextAttack;
                break;
            case 3:
                float x2 = MathHelper.func_76126_a(this.field_70173_aa * 0.15f) * (this.target.field_70130_N + 5.0f);
                float y2 = MathHelper.func_76126_a(this.field_70173_aa * 0.15f) * 0.5f;
                float z2 = MathHelper.func_76134_b(this.field_70173_aa * 0.15f) * (this.target.field_70130_N + 5.0f);
                Vec3d targetPosition2 = LMath.getEntityMiddle(this.target).func_72441_c(x2, y2, z2);
                Vec3d location2 = LMath.lerp(func_174791_d(), targetPosition2, 1.0f / (PHASE_CHANGE_TICK - this.attackTransitionTick));
                func_70107_b(location2.field_72450_a, location2.field_72448_b, location2.field_72449_c);
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                float x3 = MathHelper.func_76126_a(this.field_70173_aa * 0.15f) * (this.target.field_70130_N + 5.0f);
                float y3 = MathHelper.func_76126_a(this.field_70173_aa * 0.5f);
                float z3 = MathHelper.func_76134_b(this.field_70173_aa * 0.15f) * (this.target.field_70130_N + 5.0f);
                Vec3d targetPosition3 = LMath.getEntityMiddle(this.target).func_72441_c(x3, y3, z3);
                Vec3d location3 = LMath.lerp(func_174791_d(), targetPosition3, 1.0f / (PHASE_CHANGE_TICK - this.attackTransitionTick));
                func_70107_b(location3.field_72450_a, location3.field_72448_b, location3.field_72449_c);
                break;
        }
    }
    protected void attackTick() {
        switch (AnonymousClass1.$SwitchMap$xol$lostinfinity$mob$entity$minion$old_andromeda$OldAndromedaController$Attack[this.attack.ordinal()]) {
            case 2:
                if (this.chargeCooldown == 0) {
                    this.chargeCooldown = CHARGE_COOLDOWN;
                    this.chargeStartNode = this.chargeRandomNode;
                    if (this.chargeStartNode == null) {
                        Vec3d randomNode = func_174791_d();
                        Vec3d randomDirection = LMath.fastNormalize(new Vec3d(getRandomDouble(1.0d), getRandomDouble(1.0d), getRandomDouble(1.0d)));
                        Vec3d randomStartHandle = randomNode.func_178787_e(randomDirection.func_186678_a(-20.0d));
                        Vec3d randomEndHandle = randomNode.func_178787_e(randomDirection.func_186678_a(20.0d));
                        this.chargeStartNode = new BezierCurve.Node(randomNode, randomStartHandle, randomEndHandle);
                    }
                    Vec3d targetNode = LMath.getEntityMiddle(this.target);
                    Vec3d slideDirection = LMath.fastNormalize(new Vec3d(getRandomDouble(1.0d) + ((targetNode.field_72450_a - this.chargeStartNode.node.field_72450_a) * 0.1d), getRandomDouble(1.0d) + ((targetNode.field_72448_b - this.chargeStartNode.node.field_72448_b) * 0.1d), getRandomDouble(1.0d) + ((targetNode.field_72449_c - this.chargeStartNode.node.field_72449_c) * 0.1d)));
                    Vec3d targetStartHandle = targetNode.func_178787_e(slideDirection.func_186678_a(-10.0d));
                    Vec3d targetEndHandle = targetNode.func_178787_e(slideDirection.func_186678_a(10.0d));
                    this.chargeEndNode = new BezierCurve.Node(targetNode, targetStartHandle, targetEndHandle);
                    Vec3d randomNode2 = targetNode.func_178787_e(LMath.fastNormalize(new Vec3d(targetNode.field_72450_a - this.chargeStartNode.node.field_72450_a, 0.0d, targetNode.field_72449_c - this.chargeStartNode.node.field_72449_c)).func_186678_a(20.0d)).func_178787_e(new Vec3d(getRandomDouble(10.0d), getRandomDouble(10.0d), getRandomDouble(10.0d)));
                    Vec3d randomDirection2 = LMath.fastNormalize(new Vec3d(getRandomDouble(1.0d), getRandomDouble(1.0d), getRandomDouble(1.0d)));
                    Vec3d randomStartHandle2 = randomNode2.func_178787_e(randomDirection2.func_186678_a(-20.0d));
                    Vec3d randomEndHandle2 = randomNode2.func_178787_e(randomDirection2.func_186678_a(20.0d));
                    this.chargeRandomNode = new BezierCurve.Node(randomNode2, randomStartHandle2, randomEndHandle2);
                }
                if (this.chargeCooldown >= 8.0d) {
                    double ratio = 1.0d - ((((double) this.chargeCooldown) - 8.0d) / 8.0d);
                    Vec3d pos = BezierCurve.lerpNodes(this.chargeStartNode, this.chargeEndNode, ratio);
                    func_70107_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
                } else {
                    double ratio2 = 1.0d - (((double) this.chargeCooldown) / 8.0d);
                    Vec3d pos2 = BezierCurve.lerpNodes(this.chargeEndNode, this.chargeRandomNode, ratio2);
                    func_70107_b(pos2.field_72450_a, pos2.field_72448_b, pos2.field_72449_c);
                }
                if (this.chargeCooldown == 8.0d) {
                    func_82167_n(this.target);
                }
                this.chargeCooldown--;
                break;
            case 3:
                float x = MathHelper.func_76126_a(this.field_70173_aa * 0.15f) * (this.target.field_70130_N + 5.0f);
                float y = MathHelper.func_76126_a(this.field_70173_aa * 0.15f) * 0.5f;
                float z = MathHelper.func_76134_b(this.field_70173_aa * 0.15f) * (this.target.field_70130_N + 5.0f);
                Vec3d targetPosition = LMath.getEntityMiddle(this.target).func_72441_c(x, y, z);
                func_70107_b(targetPosition.field_72450_a, targetPosition.field_72448_b, targetPosition.field_72449_c);
                int segId = this.attackCycleTick % this.segments.length;
                Vec3d pos3 = this.segments[segId].func_174791_d();
                Vec3d dir = LMath.fastNormalize(LMath.getEntityMiddle(this.target).func_178788_d(pos3));
                EntityAndromedaChaser chaser = new EntityAndromedaChaser(this.field_70170_p);
                chaser.setTarget(this.target);
                chaser.setThrower(this);
                chaser.setSecondaryThrower(this.owner);
                chaser.func_70107_b(pos3.field_72450_a, pos3.field_72448_b, pos3.field_72449_c);
                chaser.field_70159_w = dir.field_72450_a;
                chaser.field_70181_x = dir.field_72448_b;
                chaser.field_70179_y = dir.field_72449_c;
                this.field_70170_p.func_72838_d(chaser);
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                float x2 = MathHelper.func_76126_a(this.field_70173_aa * 0.15f) * (this.target.field_70130_N + 5.0f);
                float y2 = MathHelper.func_76126_a(this.field_70173_aa * 0.5f);
                float z2 = MathHelper.func_76134_b(this.field_70173_aa * 0.15f) * (this.target.field_70130_N + 5.0f);
                Vec3d targetPosition2 = LMath.getEntityMiddle(this.target).func_72441_c(x2, y2, z2);
                func_70107_b(targetPosition2.field_72450_a, targetPosition2.field_72448_b, targetPosition2.field_72449_c);
                break;
        }
    }
    protected void resetCharge() {
        this.chargeStartNode = null;
        this.chargeEndNode = null;
        this.chargeRandomNode = null;
        this.chargeCooldown = 0;
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
    private void updatePosition() {
        float x = MathHelper.func_76126_a(this.field_70173_aa * 0.05f) * 5.0f;
        float y = MathHelper.func_76126_a(this.field_70173_aa * 0.05f) * 0.5f;
        float z = MathHelper.func_76134_b(this.field_70173_aa * 0.05f) * 5.0f;
        func_70080_a(this.owner.field_70165_t + ((double) x), this.owner.field_70163_u + 2.0d + ((double) y), this.owner.field_70161_v + ((double) z), 0.0f, 0.0f);
        this.field_70759_as = 0.0f;
        this.field_70761_aq = 0.0f;
    }
    private void findClosestTarget() {
        List<EntityLivingBase> targets = this.field_70170_p.func_175647_a(EntityLivingBase.class, this.owner.func_174813_aQ().func_186662_g(24.0d), (v1) -> {
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
    public enum Attack {
        IDLE,
        CHARGE,
        TRACER,
        BEAM;
        public static Attack nextAttack(Attack attack) {
            int i = attack.ordinal();
            Attack a = values()[(i + 1) % values().length];
            return a == IDLE ? CHARGE : a;
        }
    }
}
