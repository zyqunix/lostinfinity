package xol.lostinfinity.item.classify;

import java.util.function.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.CustomRayTraceResult;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/classify/ICustomRaytrace.class */
public interface ICustomRaytrace {
    public static final Predicate<Entity> ANTI_IMMATERIAL = entity -> {
        return !(entity instanceof EntityImmaterial);
    };

    default CustomRayTraceResult simpleBlockTrace(World world, EntityLivingBase entityFrom, int distance) {
        return doRayTrace(world, entityFrom, distance, null, 0, 1, false, null, 1, 1, 0.3f, null);
    }

    default CustomRayTraceResult forwardTrace(World world, Entity entityFrom, int distance) {
        return doRayTrace(world, entityFrom, distance, null, 0, 0, true, null, 1, 1, 0.3f, null);
    }

    default CustomRayTraceResult immaterialTrace(World world, EntityLivingBase entityFrom, int distance) {
        return doRayTrace(world, entityFrom, distance, null, 0, 2, false, null, 1, 1, 0.3f, null);
    }

    default CustomRayTraceResult entityTrace(World world, EntityLivingBase entityFrom, int distance, Class<? extends Entity> entityclass) {
        return doRayTrace(world, entityFrom, distance, null, 0, 1, false, entityclass, 1, 1, 0.3f, ANTI_IMMATERIAL);
    }

    default CustomRayTraceResult entitiesTrace(World world, EntityLivingBase entityFrom, int distance, Class<? extends Entity> entityclass, int count) {
        return doRayTrace(world, entityFrom, distance, null, 0, 1, false, entityclass, count, 1, 0.3f, ANTI_IMMATERIAL);
    }

    default CustomRayTraceResult standardFXTrace(World world, EntityLivingBase entityFrom, int distance, EnumParticleTypes trailFX, Class<? extends Entity> stopEntityType) {
        return doRayTrace(world, entityFrom, distance, trailFX, 3, 1, false, stopEntityType, 1, 1, 0.3f, ANTI_IMMATERIAL);
    }

    default CustomRayTraceResult forcedDistanceTrace(World world, Entity entityFrom, int distance) {
        return doRayTrace(world, entityFrom, distance, null, 0, 1, true, null, 1, 1, 0.3f, null);
    }

    default CustomRayTraceResult complexTrace(World world, EntityLivingBase entityFrom, int distance, EnumParticleTypes trailFX, int trailDelay, int blockType, boolean forceResult, Class<? extends Entity> stopEntityType, int lookYOffset, float expand) {
        return doRayTrace(world, entityFrom, distance, trailFX, trailDelay, blockType, forceResult, stopEntityType, 1, lookYOffset, expand, ANTI_IMMATERIAL);
    }

    default CustomRayTraceResult predicateTrace(World world, EntityLivingBase entityFrom, int distance, EnumParticleTypes trailFX, int trailDelay, int blockType, boolean forceResult, Class<? extends Entity> stopEntityType, int lookYOffset, float expand, Predicate<Entity> entityPredicate) {
        return doRayTrace(world, entityFrom, distance, trailFX, trailDelay, blockType, forceResult, stopEntityType, 1, lookYOffset, expand, entityPredicate);
    }

    default CustomRayTraceResult doRayTrace(World world, Entity entityFrom, int distance, EnumParticleTypes trailFX, int trailDelay, int stopBlock, boolean forceResult, Class<? extends Entity> stopEntityType, int maxEntity, int lookYOffset, float inaccuracy, Predicate<Entity> entityPredicate) {
        Vec3d modifiedLookPosition;
        Vec3d vec = entityFrom.func_70040_Z();
        if (lookYOffset != 0) {
            modifiedLookPosition = new Vec3d(entityFrom.field_70165_t, entityFrom.field_70163_u + 1.5d, entityFrom.field_70161_v);
        } else {
            modifiedLookPosition = new Vec3d(entityFrom.field_70165_t, entityFrom.field_70163_u, entityFrom.field_70161_v);
        }
        CustomRayTraceResult result = new CustomRayTraceResult();
        for (int i = 0; i <= distance; i++) {
            Vec3d nextPos = modifiedLookPosition.func_72441_c(vec.field_72450_a * ((double) i), vec.field_72448_b * ((double) i), vec.field_72449_c * ((double) i));
            AxisAlignedBB blockBox = new AxisAlignedBB(nextPos.field_72450_a - 0.1d, nextPos.field_72448_b - 0.1d, nextPos.field_72449_c - 0.1d, nextPos.field_72450_a + 0.1d, nextPos.field_72448_b + 0.1d, nextPos.field_72449_c + 0.1d).func_186662_g(inaccuracy);
            if (trailFX != null && i >= trailDelay && !world.field_72995_K) {
                Vec3d center_vec = new Vec3d(blockBox.field_72340_a + ((blockBox.field_72336_d - blockBox.field_72340_a) * 0.5d), blockBox.field_72338_b + ((blockBox.field_72337_e - blockBox.field_72338_b) * 0.5d), blockBox.field_72339_c + ((blockBox.field_72334_f - blockBox.field_72339_c) * 0.5d));
                ((WorldServer) world).func_180505_a(trailFX, true, center_vec.field_72450_a, center_vec.field_72448_b, center_vec.field_72449_c, 3, 0.0d, 0.0d, 0.0d, 0.0d, new int[0]);
            }
            if (stopEntityType != null) {
                for (Entity near_entity : world.func_72872_a(stopEntityType, blockBox)) {
                    if (!near_entity.func_110124_au().equals(entityFrom.func_110124_au()) && (entityPredicate == null || entityPredicate.test(near_entity))) {
                        result.addResultEntity(near_entity);
                        if (maxEntity > 0 && result.getResultEntities().size() >= maxEntity) {
                            result.setResultPos(nextPos);
                            return result;
                        }
                    }
                }
            }
            if (stopBlock > 0) {
                IBlockState stateResult = world.func_180495_p(new BlockPos(nextPos));
                if (!world.func_175623_d(new BlockPos(nextPos)) && (stateResult.func_185904_a().func_76230_c() || stopBlock == 2)) {
                    Vec3d referencePos = modifiedLookPosition.func_72441_c(vec.field_72450_a * ((double) (i - 1)), vec.field_72448_b * ((double) (i - 1)), vec.field_72449_c * ((double) (i - 1)));
                    result.setResultPos(referencePos);
                    result.setGrabbedVector(nextPos);
                    return result;
                }
            }
        }
        if (forceResult) {
            Vec3d forcedVec = modifiedLookPosition.func_72441_c(vec.field_72450_a * ((double) distance), vec.field_72448_b * ((double) distance), vec.field_72449_c * ((double) distance));
            result.setResultPos(forcedVec);
            return result;
        }
        if (result.isModified()) {
            return result;
        }
        return null;
    }
}
