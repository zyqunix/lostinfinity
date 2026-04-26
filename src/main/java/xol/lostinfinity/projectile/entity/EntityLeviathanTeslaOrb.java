package xol.lostinfinity.projectile.entity;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanController;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanSegment;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.math.LMath;
public class EntityLeviathanTeslaOrb extends EntityBaseThrowable {
    private static final List<String> DAMAGE_TYPE = Collections.singletonList("Aquatic");
    private final Map<Entity, Long> hitCooldown;
    private EntityLeviathanController leviathanController;
    public EntityLeviathanTeslaOrb(World worldIn) {
        super(worldIn);
        this.hitCooldown = new ConcurrentHashMap();
        func_70105_a(0.5f, 0.5f);
    }
    public EntityLeviathanTeslaOrb(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.hitCooldown = new ConcurrentHashMap();
        func_70105_a(0.5f, 0.5f);
    }
    public void setLeviathanThrower(EntityLeviathanController throwset) {
        super.setThrower(throwset);
        this.leviathanController = throwset;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        double mX = this.field_70159_w;
        double mY = this.field_70181_x;
        double mZ = this.field_70179_y;
        super.func_70071_h_();
        this.field_70159_w = mX;
        this.field_70181_x = mY;
        this.field_70179_y = mZ;
        if (this.field_70170_p.field_72995_K || this.field_70173_aa % 20 != 0 || this.leviathanController == null) {
            return;
        }
        List<Entity> list = this.field_70170_p.func_175674_a(this, func_174813_aQ().func_186662_g(10.0d), input -> {
            if (input == this.leviathanController) {
                return false;
            }
            if (input instanceof EntityLeviathanSegment) {
                int id = ((EntityLeviathanSegment) input).getId();
                return this.leviathanController.segments[id] != input;
            }
            return true;
        });
        CustomParticleConfig config = new CustomParticleConfig();
        config.createInstance().setParticle(ParticleInit.GOLDEN_MAGIC).setIgnoreRange(true);
        for (Entity entity : list) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase hit = (EntityLivingBase) entity;
                IMaxAttack.dealTrueDamage(this.leviathanController, hit, hit.func_110138_aP() * 0.2f, DAMAGE_TYPE);
                for (int i = 1; i <= 10; i++) {
                    Vec3d vec = LMath.lerp(LMath.getEntityMiddle(this), LMath.getEntityMiddle(hit), i / 10.0f);
                    IParticleSpawner.spawnParticle(this.field_70170_p, config, vec.field_72450_a, vec.field_72448_b, vec.field_72449_c);
                }
            }
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
