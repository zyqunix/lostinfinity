package xol.lostinfinity.projectile.entity;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanController;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanSegment;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.LMath;
public class EntityLeviathanTracer extends EntityBaseThrowable {
    private static final List<String> DAMAGE_TYPE = Collections.singletonList("Aquatic");
    private final Entity target;
    private EntityLeviathanController leviathanController;
    public EntityLeviathanTracer(World worldIn) {
        super(worldIn);
        func_70105_a(0.5f, 0.5f);
        this.target = null;
    }
    public void setLeviathanThrower(EntityLeviathanController throwset) {
        super.setThrower(throwset);
        this.leviathanController = throwset;
    }
    public EntityLeviathanTracer(World worldIn, double x, double y, double z, Entity target) {
        super(worldIn, x, y, z);
        func_70105_a(0.5f, 0.5f);
        this.target = target;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        EntityLeviathanSegment entityLeviathanSegment;
        if (this.field_70170_p.field_72995_K || result.field_72313_a == RayTraceResult.Type.BLOCK || !(result.field_72308_g instanceof EntityLivingBase) || (entityLeviathanSegment = (EntityLivingBase) result.field_72308_g) == this.leviathanController) {
            return;
        }
        if (entityLeviathanSegment instanceof EntityLeviathanSegment) {
            int id = entityLeviathanSegment.getId();
            if (this.leviathanController.segments[id] == entityLeviathanSegment) {
                return;
            }
        }
        IMaxAttack.dealMaxHealth((Entity) (this.leviathanController != null ? this.leviathanController : this), (EntityLivingBase) entityLeviathanSegment, 1, DAMAGE_TYPE);
        func_70106_y();
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175682_a(ParticleInit.ION_FUEL, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
        } else if (this.field_70173_aa > 140 || this.leviathanController == null) {
            func_70106_y();
            return;
        }
        if (this.target == null || this.target.field_70128_L || this.field_70173_aa < 40) {
            return;
        }
        this.field_70159_w *= 0.95d;
        this.field_70181_x *= 0.95d;
        this.field_70179_y *= 0.95d;
        Vec3d dir = LMath.fastNormalize(LMath.getEntityMiddle(this.target).func_178788_d(func_174791_d())).func_186678_a(0.2d);
        func_70024_g(dir.field_72450_a, dir.field_72448_b, dir.field_72449_c);
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
