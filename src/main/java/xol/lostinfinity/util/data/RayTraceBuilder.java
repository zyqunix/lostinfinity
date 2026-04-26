package xol.lostinfinity.util.data;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/data/RayTraceBuilder.class */
public class RayTraceBuilder {
    private int distance;
    private float raySize;
    private boolean force;
    private Predicate<IBlockState> blockFilter;
    private Class<? extends Entity> entityClass;
    private Predicate<Entity> entityFilter;
    private int maxEntity;
    private EnumParticleTypes trailFX;
    private int trailDelay;
    private CustomTrace customTrace;

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/data/RayTraceBuilder$CustomTrace.class */
    @FunctionalInterface
    public interface CustomTrace {
        void trace(Vec3d vec3d, Vec3d vec3d2, float f);
    }

    public static RayTraceBuilder block(int distance) {
        return new RayTraceBuilder().distance(distance).raySize(0.4f).blockFilter(RayTraceBuilder::checkSolid);
    }

    public static RayTraceBuilder forward(int distance) {
        return new RayTraceBuilder().distance(distance).raySize(0.4f).force(true);
    }

    public static RayTraceBuilder immaterial(int distance) {
        return new RayTraceBuilder().distance(distance).raySize(0.4f).blockFilter(RayTraceBuilder::checkNotAir);
    }

    public static RayTraceBuilder entity(Class<? extends Entity> entityClass, int distance) {
        return new RayTraceBuilder().distance(distance).raySize(0.4f).blockFilter(RayTraceBuilder::checkSolid).entityClass(entityClass).entityFilter(RayTraceBuilder::checkEntityImmaterial);
    }

    public static RayTraceBuilder fx(Class<? extends Entity> entityClass, int distance, EnumParticleTypes trailFX) {
        return new RayTraceBuilder().distance(distance).raySize(0.4f).blockFilter(RayTraceBuilder::checkSolid).entityClass(entityClass).entityFilter(RayTraceBuilder::checkEntityImmaterial).trailFX(trailFX).trailDelay(3);
    }

    public static RayTraceBuilder force(int distance) {
        return new RayTraceBuilder().distance(distance).raySize(0.4f).force(true).blockFilter(RayTraceBuilder::checkSolid);
    }

    public RayTraceBuilder distance(int distance) {
        this.distance = distance;
        return this;
    }

    public RayTraceBuilder raySize(float raySize) {
        this.raySize = Math.max(0.1f, raySize);
        return this;
    }

    public RayTraceBuilder force(boolean force) {
        this.force = force;
        return this;
    }

    public RayTraceBuilder blockFilter(Predicate<IBlockState> filter) {
        this.blockFilter = filter;
        return this;
    }

    public RayTraceBuilder entityClass(Class<? extends Entity> entityClass) {
        this.entityClass = entityClass;
        return this;
    }

    public RayTraceBuilder entityFilter(Predicate<Entity> filter) {
        this.entityFilter = filter;
        return this;
    }

    public RayTraceBuilder maxEntity(int maxEntity) {
        this.maxEntity = maxEntity;
        return this;
    }

    public RayTraceBuilder trailFX(EnumParticleTypes trailFX) {
        this.trailFX = trailFX;
        return this;
    }

    public RayTraceBuilder trailDelay(int trailDelay) {
        this.trailDelay = trailDelay;
        return this;
    }

    public RayTraceBuilder custom(CustomTrace customTrace) {
        this.customTrace = customTrace;
        return this;
    }

    public CustomRayTraceResult trace(Entity entity, boolean useEyeHeight) {
        return trace(entity.field_70170_p, entity, useEyeHeight ? entity.func_174824_e(1.0f) : entity.func_174791_d(), entity.func_70040_Z());
    }

    public CustomRayTraceResult trace(World world, @Nullable Entity entity, Vec3d pos, Vec3d dir) {
        CustomRayTraceResult result = new CustomRayTraceResult();
        Vec3d lastPos = pos;
        for (int i = 0; i <= this.distance; i++) {
            Vec3d nextPos = pos.func_178787_e(dir.func_186678_a(i));
            AxisAlignedBB boundingBox = new AxisAlignedBB(nextPos.field_72450_a, nextPos.field_72448_b, nextPos.field_72449_c, nextPos.field_72450_a, nextPos.field_72448_b, nextPos.field_72449_c).func_186662_g(this.raySize);
            if (this.trailFX != null && i >= this.trailDelay && !world.field_72995_K) {
                ((WorldServer) world).func_180505_a(this.trailFX, true, nextPos.field_72450_a, nextPos.field_72448_b, nextPos.field_72449_c, 3, 0.0d, 0.0d, 0.0d, 0.0d, new int[0]);
            }
            if (this.entityClass != null) {
                for (Entity nearEntity : world.func_175647_a(this.entityClass, boundingBox, this.entityFilter)) {
                    if (nearEntity != entity) {
                        result.addResultEntity(nearEntity);
                        if (this.maxEntity > 0 && result.getResultEntities().size() >= this.maxEntity) {
                            result.setResultPos(nextPos);
                            return result;
                        }
                    }
                }
            }
            if (this.blockFilter != null) {
                IBlockState blockState = world.func_180495_p(new BlockPos(nextPos));
                if (this.blockFilter.apply(blockState)) {
                    result.setResultPos(lastPos);
                    result.setGrabbedVector(nextPos);
                    return result;
                }
            }
            if (this.customTrace != null) {
                this.customTrace.trace(lastPos, nextPos, i);
            }
            lastPos = nextPos;
        }
        if (this.force) {
            result.setResultPos(lastPos);
            return result;
        }
        if (result.isModified()) {
            return result;
        }
        return null;
    }

    public static boolean checkSolid(IBlockState block) {
        return block.func_185904_a() != Material.field_151579_a && block.func_185904_a().func_76230_c();
    }

    public static boolean checkImmaterial(IBlockState block) {
        return (block.func_185904_a() == Material.field_151579_a || block.func_185904_a().func_76230_c()) ? false : true;
    }

    public static boolean checkNotAir(IBlockState block) {
        return block.func_185904_a() != Material.field_151579_a;
    }

    public static boolean checkEntityImmaterial(Entity entity) {
        return !(entity instanceof EntityImmaterial);
    }
}
