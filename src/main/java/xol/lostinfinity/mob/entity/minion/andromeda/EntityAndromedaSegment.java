package xol.lostinfinity.mob.entity.minion.andromeda;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.common.events.EventsClientRender;
import xol.lostinfinity.common.packets.LostInfinityPacketHandler;
import xol.lostinfinity.common.packets.clientbound.PacketSyncParts;
import xol.lostinfinity.mob.entity.classify.IKnockbackImmunity;
import xol.lostinfinity.mob.entity.classify.IRelay;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/minion/andromeda/EntityAndromedaSegment.class */
public class EntityAndromedaSegment extends EntityMinion implements IKnockbackImmunity, IRelay<EntityAndromedaController> {
    protected EntityAndromedaController controller;
    protected EntityAndromedaSegment parentSegment;
    protected int id;
    protected float dWidth;
    protected float dHeight;
    protected float dSocketOffset;
    protected float dOriginDistance;
    protected int size;
    protected float socketOffset;
    protected float originDistance;
    protected float segmentMaxPitch;
    protected float segmentMaxYaw;
    protected boolean hasSyncedOnce;
    protected boolean awaitSync;
    protected double syncX;
    protected double syncY;
    protected double syncZ;
    protected float syncYaw;
    protected float syncPitch;

    public EntityAndromedaSegment(World worldIn, EntityAndromedaController controller, EntityAndromedaSegment parentSegment) {
        super(worldIn);
        this.size = 1;
        this.hasSyncedOnce = false;
        this.controller = controller;
        this.parentSegment = parentSegment;
        func_70105_a(1.0f, 1.0f);
        this.dWidth = this.field_70130_N;
        this.dHeight = this.field_70131_O;
        this.dSocketOffset = 0.5f;
        this.socketOffset = this.dSocketOffset;
        this.dOriginDistance = 0.5f;
        this.originDistance = this.dOriginDistance;
        this.segmentMaxPitch = 60.0f;
        this.segmentMaxYaw = 60.0f;
        this.field_70158_ak = true;
        this.field_70145_X = true;
        if (worldIn.field_72995_K) {
            EventsClientRender.renderForce.put(Integer.valueOf(func_145782_y()), this);
        }
    }

    @Override // xol.lostinfinity.mob.entity.classify.IKnockbackImmunity
    public float getKnockbackResistance(CustomDamageResult damageResult) {
        return 1.0f;
    }

    protected void updatePosition() {
        if (this.id > 0 && this.parentSegment == null) {
            return;
        }
        if (this.awaitSync) {
            this.awaitSync = false;
            func_70080_a(this.syncX, this.syncY, this.syncZ, this.syncYaw, this.syncPitch);
            this.hasSyncedOnce = true;
        } else {
            move(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 3 == 0) {
            PacketSyncParts syncParts = new PacketSyncParts(this);
            LostInfinityPacketHandler.INSTANCE.sendToAllTracking(syncParts, this.controller);
        }
    }

    protected void move(double x, double y, double z) {
        Vec3d parentPos = this.parentSegment.getSocketPosition();
        Vec3d dir = LMath.fastNormalize(parentPos.func_178786_a(x, y, z));
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

    protected void cleanUp() {
        this.controller = null;
        this.parentSegment = null;
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
        return this.field_70131_O;
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

    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    public boolean func_70112_a(double distance) {
        return distance < 65536.0d;
    }

    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
    }

    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    public boolean shouldRender() {
        return this.hasSyncedOnce;
    }

    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    /* JADX INFO: renamed from: getRelay, reason: merged with bridge method [inline-methods] */
    public EntityAndromedaController mo307getRelay() {
        return this.controller;
    }

    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public void setId(int id) {
        this.id = id;
    }

    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public int getId() {
        return this.id;
    }

    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public void setPos(double x, double y, double z, float yaw, float pitch) {
        this.syncX = x;
        this.syncY = y;
        this.syncZ = z;
        this.syncYaw = yaw;
        this.syncPitch = pitch;
        this.awaitSync = true;
    }

    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public double getX() {
        return this.field_70165_t;
    }

    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public double getY() {
        return this.field_70163_u;
    }

    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public double getZ() {
        return this.field_70161_v;
    }

    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public float getYaw() {
        return this.field_70177_z;
    }

    @Override // xol.lostinfinity.mob.entity.classify.IRelay
    public float getPitch() {
        return this.field_70125_A;
    }

    public EntityAndromedaController getController() {
        return this.controller;
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/minion/andromeda/EntityAndromedaSegment$Head.class */
    public static class Head extends EntityAndromedaSegment {
        @Override // xol.lostinfinity.mob.entity.minion.andromeda.EntityAndromedaSegment, xol.lostinfinity.mob.entity.classify.IRelay
        /* JADX INFO: renamed from: getRelay */
        public /* bridge */ /* synthetic */ Entity mo307getRelay() {
            return super.mo307getRelay();
        }

        public Head(World worldIn, EntityAndromedaController controller) {
            super(worldIn, controller, null);
        }

        @Override // xol.lostinfinity.mob.entity.minion.andromeda.EntityAndromedaSegment
        protected void move(double x, double y, double z) {
            Vec3d parentPos = this.controller.func_174791_d();
            Vec3d dir = LMath.fastNormalize(parentPos.func_178786_a(x, y, z));
            double hMagnitude = LMath.fastSqrt((dir.field_72450_a * dir.field_72450_a) + (dir.field_72449_c * dir.field_72449_c));
            float pitch = ((float) MathHelper.func_181159_b(-dir.field_72448_b, hMagnitude)) * 57.29578f;
            float yaw = ((float) MathHelper.func_181159_b(-dir.field_72450_a, dir.field_72449_c)) * 57.29578f;
            Vec3d dir2 = dir.func_186678_a(-this.originDistance);
            func_70080_a(parentPos.field_72450_a + dir2.field_72450_a, parentPos.field_72448_b + dir2.field_72448_b, parentPos.field_72449_c + dir2.field_72449_c, yaw, pitch);
        }
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/minion/andromeda/EntityAndromedaSegment$Tail.class */
    public static class Tail extends EntityAndromedaSegment {
        @Override // xol.lostinfinity.mob.entity.minion.andromeda.EntityAndromedaSegment, xol.lostinfinity.mob.entity.classify.IRelay
        /* JADX INFO: renamed from: getRelay */
        public /* bridge */ /* synthetic */ Entity mo307getRelay() {
            return super.mo307getRelay();
        }

        public Tail(World worldIn, EntityAndromedaController controller, EntityAndromedaSegment parentSegment) {
            super(worldIn, controller, parentSegment);
        }
    }
}
