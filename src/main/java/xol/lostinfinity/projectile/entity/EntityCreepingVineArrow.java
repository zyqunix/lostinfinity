package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityCreepingVineArrow.class */
public class EntityCreepingVineArrow extends EntityBaseThrowable {
    private static final int POD_COUNT = 20;

    public EntityCreepingVineArrow(World worldIn) {
        super(worldIn);
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (this.field_70170_p.field_72995_K) {
        }
        switch (AnonymousClass1.$SwitchMap$net$minecraft$util$math$RayTraceResult$Type[result.field_72313_a.ordinal()]) {
            case 1:
                spawnPods();
                break;
            case 2:
                if (result.field_72308_g != this.field_70192_c) {
                    spawnPods();
                    break;
                }
                break;
        }
    }

    /* JADX INFO: renamed from: xol.lostinfinity.projectile.entity.EntityCreepingVineArrow$1, reason: invalid class name */
    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityCreepingVineArrow$1.class */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$minecraft$util$math$RayTraceResult$Type = new int[RayTraceResult.Type.values().length];

        static {
            try {
                $SwitchMap$net$minecraft$util$math$RayTraceResult$Type[RayTraceResult.Type.BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$net$minecraft$util$math$RayTraceResult$Type[RayTraceResult.Type.ENTITY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private void spawnPods() {
        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.SPLAT_EXPLODE, SoundCategory.PLAYERS, 1.5f, 1.0f);
        for (int i = 0; i < POD_COUNT; i++) {
            EntityCreepingVinePod pod = new EntityCreepingVinePod(this.field_70170_p);
            pod.setThrower(this.field_70192_c);
            pod.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            Vec3d randDir = LMath.fastNormalize(new Vec3d(((double) this.field_70146_Z.nextFloat()) - 0.5d, ((double) this.field_70146_Z.nextFloat()) - 0.5d, ((double) this.field_70146_Z.nextFloat()) - 0.5d));
            pod.setInitDirection(randDir);
            pod.func_70186_c(randDir.field_72450_a, randDir.field_72448_b, randDir.field_72449_c, 0.25f, 0.0f);
            this.field_70170_p.func_72838_d(pod);
        }
        func_70106_y();
    }
}
