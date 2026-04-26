package xol.lostinfinity.mob.entity.sea.seaserpent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.mob.entity.base.EntityMultipleLivesRelay;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.LMath;
public class EntitySeaSerpentSegment extends EntityMultipleLivesRelay<EntitySeaSerpentController> implements IMaxAttack {
    protected final EntitySeaSerpentSegment parentSegment;
    protected float dWidth;
    protected float dHeight;
    protected float dSocketOffset;
    protected float dOriginDistance;
    protected int size;
    protected float socketOffset;
    protected float originDistance;
    protected float segmentMaxPitch;
    protected float segmentMaxYaw;
    public EntitySeaSerpentSegment(EntitySeaSerpentController parent, EntitySeaSerpentSegment parentSegment) {
        this(parent, parentSegment, 1.0f, 1.0f);
    }
    public EntitySeaSerpentSegment(EntitySeaSerpentController parent, EntitySeaSerpentSegment parentSegment, float width, float height) {
        super(parent, width, height);
        this.size = 1;
        this.parentSegment = parentSegment;
        this.dWidth = width;
        this.dHeight = height;
        this.dSocketOffset = 0.5f;
        this.socketOffset = this.dSocketOffset;
        this.dOriginDistance = 0.5f;
        this.originDistance = this.dOriginDistance;
        this.segmentMaxPitch = 60.0f;
        this.segmentMaxYaw = 60.0f;
    }
    protected void updatePosition() {
        if (this.parentSegment == null || this.parentSegment.field_70128_L) {
            return;
        }
        if (this.awaitSync) {
            this.awaitSync = false;
            func_70080_a(this.syncX, this.syncY, this.syncZ, this.syncYaw, this.syncPitch);
            return;
        }
        Vec3d parentPos = this.parentSegment.getSocketPosition();
        Vec3d dir = LMath.fastNormalize(parentPos.func_178786_a(this.field_70165_t, this.field_70163_u, this.field_70161_v));
        if (dir.func_189985_c() == 0.0d) {
            return;
        }
        double hMagnitude = LMath.fastSqrt((dir.field_72450_a * dir.field_72450_a) + (dir.field_72449_c * dir.field_72449_c));
        float pitch = ((float) MathHelper.func_181159_b(-dir.field_72448_b, hMagnitude)) * 57.29578f;
        float parentPitch = this.parentSegment.field_70125_A;
        float pitch2 = (float) MathHelper.func_151238_b(parentPitch + MathHelper.func_76131_a(LMath.degreeDifference(parentPitch, pitch), -this.segmentMaxPitch, this.segmentMaxPitch), parentPitch, 0.02d);
        float yaw = ((float) MathHelper.func_181159_b(-dir.field_72450_a, dir.field_72449_c)) * 57.29578f;
        float parentYaw = this.parentSegment.field_70177_z;
        float yaw2 = (float) MathHelper.func_151238_b(parentYaw + MathHelper.func_76131_a(LMath.degreeDifference(parentYaw, yaw), -this.segmentMaxYaw, this.segmentMaxYaw), parentYaw, 0.02d);
        Vec3d dir2 = Vec3d.func_189986_a(pitch2, yaw2).func_186678_a(-this.originDistance);
        func_70080_a(parentPos.field_72450_a + dir2.field_72450_a, parentPos.field_72448_b + dir2.field_72448_b, parentPos.field_72449_c + dir2.field_72449_c, yaw2, pitch2);
    }
    protected Vec3d getSocketPosition() {
        return func_174791_d().func_178787_e(func_70040_Z().func_186678_a(-this.socketOffset));
    }
    public boolean func_70072_I() {
        return false;
    }
    public boolean func_180799_ab() {
        return false;
    }
    public void func_70080_a(double x, double y, double z, float yaw, float pitch) {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.field_70165_t = x;
        this.field_70163_u = y;
        this.field_70161_v = z;
        this.field_70126_B = this.field_70177_z;
        this.field_70127_C = this.field_70125_A;
        this.field_70758_at = this.field_70759_as;
        this.field_70760_ar = this.field_70761_aq;
        this.field_70177_z = yaw;
        this.field_70759_as = yaw;
        this.field_70761_aq = yaw;
        this.field_70125_A = pitch;
        if (!this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72964_e(((int) Math.floor(this.field_70165_t)) >> 4, ((int) Math.floor(this.field_70161_v)) >> 4);
        }
        func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        func_70101_b(yaw, pitch);
    }
    public float func_70047_e() {
        return this.field_70131_O / 2.0f;
    }
    public void setSize(int size) {
        this.size = size;
        func_70105_a(this.dWidth * size, this.dHeight * size);
        this.socketOffset = this.dSocketOffset * size;
        this.originDistance = this.dOriginDistance * size;
    }
    public float func_70603_bj() {
        return this.size;
    }
}
