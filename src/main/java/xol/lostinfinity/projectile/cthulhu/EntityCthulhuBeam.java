package xol.lostinfinity.projectile.cthulhu;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.data.RayTraceBuilder;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/cthulhu/EntityCthulhuBeam.class */
public class EntityCthulhuBeam extends EntityImmaterial {
    private static final DataParameter<Float> DIR_X = EntityDataManager.func_187226_a(EntityCthulhuBeam.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> DIR_Y = EntityDataManager.func_187226_a(EntityCthulhuBeam.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> DIR_Z = EntityDataManager.func_187226_a(EntityCthulhuBeam.class, DataSerializers.field_187193_c);
    public float pitch;
    public float yaw;

    public EntityCthulhuBeam(World worldIn) {
        super(worldIn);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(DIR_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(DIR_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(DIR_Z, Float.valueOf(0.0f));
    }

    public Vec3d getDirection() {
        return new Vec3d(((Float) this.field_70180_af.func_187225_a(DIR_X)).floatValue(), ((Float) this.field_70180_af.func_187225_a(DIR_Y)).floatValue(), ((Float) this.field_70180_af.func_187225_a(DIR_Z)).floatValue());
    }

    public void setDirection(float x, float y, float z) {
        float a = (float) MathHelper.func_181161_i((x * x) + (y * y) + (z * z));
        this.field_70180_af.func_187227_b(DIR_X, Float.valueOf(x * a));
        this.field_70180_af.func_187227_b(DIR_Y, Float.valueOf(y * a));
        this.field_70180_af.func_187227_b(DIR_Z, Float.valueOf(z * a));
    }

    public void func_70071_h_() {
        if (this.field_70173_aa > 45) {
            func_70106_y();
        } else if (!this.field_70170_p.field_72995_K && this.field_70173_aa >= 15 && this.field_70173_aa < 30 && this.field_70173_aa % 3 == 0) {
            damagePlayer();
        }
    }

    public void func_184206_a(DataParameter<?> key) {
        super.func_184206_a(key);
        if (this.field_70170_p.field_72995_K) {
            Rotations rot = LMath.toPitchYaw(getDirection());
            this.pitch = rot.func_179415_b();
            this.yaw = rot.func_179416_c();
        }
    }

    private void damagePlayer() {
        CustomRayTraceResult result = RayTraceBuilder.entity(EntityPlayer.class, 64).maxEntity(0).trace(this.field_70170_p, this, func_174791_d(), getDirection());
        if (result == null || result.getResultEntities().isEmpty()) {
            return;
        }
        Iterator<Entity> it = result.getResultEntities().iterator();
        while (it.hasNext()) {
            EntityPlayer player = (Entity) it.next();
            IMaxAttack.dealTrueDamage(this, player, player.func_110138_aP() * 0.08f);
        }
    }
}
