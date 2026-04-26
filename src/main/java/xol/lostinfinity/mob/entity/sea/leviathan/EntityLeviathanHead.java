package xol.lostinfinity.mob.entity.sea.leviathan;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.util.math.LMath;
public class EntityLeviathanHead extends EntityLeviathanSegment {
    public EntityLeviathanHead(EntityLeviathanController parent) {
        super(parent, null, 1.0f, 1.0f);
        this.dSocketOffset = 0.4375f;
        this.dOriginDistance = 0.4375f;
    }
    @Override // xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanSegment
    protected void updatePosition() {
        if (this.awaitSync) {
            this.awaitSync = false;
            func_70080_a(this.syncX, this.syncY, this.syncZ, this.syncYaw, this.syncPitch);
            return;
        }
        Vec3d parentPos = ((EntityLeviathanController) this.relay).func_174791_d();
        Vec3d dir = LMath.fastNormalize(parentPos.func_178786_a(this.field_70165_t, this.field_70163_u, this.field_70161_v));
        double hMagnitude = LMath.fastSqrt((dir.field_72450_a * dir.field_72450_a) + (dir.field_72449_c * dir.field_72449_c));
        float pitch = ((float) MathHelper.func_181159_b(-dir.field_72448_b, hMagnitude)) * 57.29578f;
        float yaw = ((float) MathHelper.func_181159_b(-dir.field_72450_a, dir.field_72449_c)) * 57.29578f;
        Vec3d dir2 = dir.func_186678_a(-this.originDistance);
        func_70080_a(parentPos.field_72450_a + dir2.field_72450_a, parentPos.field_72448_b + dir2.field_72448_b, parentPos.field_72449_c + dir2.field_72449_c, yaw, pitch);
    }
}
