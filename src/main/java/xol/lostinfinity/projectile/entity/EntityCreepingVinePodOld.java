package xol.lostinfinity.projectile.entity;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.client.fx.ClientParticleRenderer;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.BezierCurve;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityCreepingVinePodOld.class */
public class EntityCreepingVinePodOld extends EntityBaseThrowable {
    private static final CustomParticleConfig config = new CustomParticleConfig();
    private Vec3d initDirection;
    private final LinkedList<BezierCurve.Node> path;
    private final Set<EntityLivingBase> pierced;

    static {
        config.createInstance().setIgnoreRange(true).setParticle(ParticleInit.GALAXY_GREEN);
    }

    public EntityCreepingVinePodOld(World worldIn) {
        super(worldIn);
        this.path = new LinkedList<>();
        this.pierced = new HashSet();
        func_70105_a(0.75f, 0.75f);
    }

    public void setInitDirection(Vec3d initDirection) {
        this.initDirection = initDirection;
        Vec3d node = func_174791_d();
        Vec3d startHandle = node.func_178788_d(initDirection.func_186678_a(2.0d));
        Vec3d endHandle = node.func_178787_e(initDirection.func_186678_a(2.0d));
        this.path.add(new BezierCurve.Node(node, startHandle, endHandle));
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void setThrower(EntityLivingBase throwset) {
        super.setThrower(throwset);
        this.field_184539_c = throwset;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (this.field_70170_p.field_72995_K || result.field_72313_a != RayTraceResult.Type.ENTITY || !(result.field_72308_g instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase target = result.field_72308_g;
        if (this.pierced.contains(target)) {
            return;
        }
        IMaxAttack.dealTrueDamage(this.field_70192_c, target, 100.0f);
        this.pierced.add(target);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        if (this.field_70128_L) {
            this.path.clear();
            this.pierced.clear();
            return;
        }
        if (this.field_70170_p.field_72995_K) {
            this.field_70142_S = this.field_70165_t;
            this.field_70137_T = this.field_70163_u;
            this.field_70136_U = this.field_70161_v;
            config.setOrigin(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            ClientParticleRenderer.renderComplex(config);
            return;
        }
        super.func_70071_h_();
        if (this.initDirection == null) {
            func_70106_y();
            return;
        }
        int tick = this.field_70173_aa % 60;
        if (tick == 0) {
            this.path.pop();
            createRandomNode();
        }
        while (this.path.size() <= 1) {
            createRandomNode();
        }
        Vec3d c = BezierCurve.lerpNodes(this.path.get(0), this.path.get(1), ((double) tick) / 60.0d);
        Vec3d motion = c.func_178786_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70159_w = motion.field_72450_a;
        this.field_70181_x = motion.field_72448_b;
        this.field_70179_y = motion.field_72449_c;
        func_70012_b(c.field_72450_a, c.field_72448_b, c.field_72449_c, this.field_70177_z, this.field_70125_A);
    }

    public void func_180426_a(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        super.func_180426_a(x, y, z, yaw, pitch, posRotationIncrements, teleport);
    }

    protected float func_70185_h() {
        return 0.0f;
    }

    private void createRandomNode() {
        double pX = this.field_70165_t;
        double pY = this.field_70163_u;
        double pZ = this.field_70161_v;
        if (this.path.size() >= 1) {
            BezierCurve.Node node = this.path.get(0);
            pX = node.node.field_72450_a;
            pY = node.node.field_72448_b;
            pZ = node.node.field_72449_c;
        }
        Vec3d handleStart = LMath.fastNormalize(new Vec3d((((double) this.field_70146_Z.nextFloat()) - 0.5d) + (this.initDirection.field_72450_a * 0.25d), (((double) this.field_70146_Z.nextFloat()) - 0.5d) + (this.initDirection.field_72448_b * 0.25d), (((double) this.field_70146_Z.nextFloat()) - 0.5d) + (this.initDirection.field_72449_c * 0.25d))).func_186678_a(4.0d).func_72441_c(pX, pY, pZ);
        Vec3d randDir = LMath.fastNormalize(new Vec3d(this.initDirection.field_72450_a * ((double) this.field_70146_Z.nextFloat()), this.initDirection.field_72448_b * ((double) this.field_70146_Z.nextFloat()), this.initDirection.field_72449_c * ((double) this.field_70146_Z.nextFloat()))).func_186678_a(8.0d);
        Vec3d node2 = handleStart.func_178787_e(randDir);
        Vec3d handleEnd = node2.func_178787_e(randDir);
        this.path.add(new BezierCurve.Node(node2, handleStart, handleEnd));
    }
}
