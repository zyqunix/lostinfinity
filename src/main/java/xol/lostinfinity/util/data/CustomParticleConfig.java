package xol.lostinfinity.util.data;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
public class CustomParticleConfig {
    public int count = 1;
    public Vec3d origin = Vec3d.field_186680_a;
    public final List<Instance> particles = new ArrayList();
    public Instance createInstance() {
        Instance instance = new Instance();
        this.particles.add(instance);
        return instance;
    }
    public void setCount(int count) {
        this.count = Math.max(1, count);
    }
    public void setOrigin(double x, double y, double z) {
        this.origin = new Vec3d(x, y, z);
    }
    public void setOrigin(Vec3d origin) {
        this.origin = origin;
    }
    public static class Instance {
        public EnumParticleTypes particleType;
        public boolean ignoreRange;
        public int weight = 1;
        public Vec3d offset = Vec3d.field_186680_a;
        public Vec3d speed = Vec3d.field_186680_a;
        public Vec3d spread = Vec3d.field_186680_a;
        public Vec3d velSpread = Vec3d.field_186680_a;
        public int count = 1;
        public int[] args = new int[0];
        public Instance setWeight(int weight) {
            this.weight = Math.max(1, weight);
            return this;
        }
        public Instance setParticle(EnumParticleTypes type) {
            this.particleType = type;
            return this;
        }
        public Instance setIgnoreRange(boolean flag) {
            this.ignoreRange = flag;
            return this;
        }
        public Instance setOffset(Vec3d offset) {
            this.offset = offset;
            return this;
        }
        public Instance setOffset(double x, double y, double z) {
            this.offset = new Vec3d(x, y, z);
            return this;
        }
        public Instance setSpeed(Vec3d speed) {
            this.speed = speed;
            return this;
        }
        public Instance setSpeed(double x, double y, double z) {
            this.speed = new Vec3d(x, y, z);
            return this;
        }
        public Instance setSpread(Vec3d spread) {
            this.spread = spread;
            return this;
        }
        public Instance setSpread(double x, double y, double z) {
            this.spread = new Vec3d(x, y, z);
            return this;
        }
        public Instance setVelSpread(Vec3d velSpread) {
            this.velSpread = velSpread;
            return this;
        }
        public Instance setVelSpread(double x, double y, double z) {
            this.velSpread = new Vec3d(x, y, z);
            return this;
        }
        public Instance setCount(int count) {
            this.count = Math.max(1, count);
            return this;
        }
        public Instance setArgs(int... args) {
            this.args = args;
            return this;
        }
    }
    public static CustomParticleConfig read(PacketBuffer buf) {
        CustomParticleConfig config = new CustomParticleConfig();
        config.setCount(buf.func_150792_a());
        config.setOrigin(new Vec3d(buf.readFloat(), buf.readFloat(), buf.readFloat()));
        int size = buf.func_150792_a();
        for (int i = 0; i < size; i++) {
            Instance instance = new Instance().setWeight(buf.func_150792_a()).setParticle(EnumParticleTypes.valueOf(buf.func_150789_c(40))).setIgnoreRange(buf.readBoolean()).setOffset(new Vec3d(buf.readFloat(), buf.readFloat(), buf.readFloat())).setSpeed(new Vec3d(buf.readFloat(), buf.readFloat(), buf.readFloat())).setSpread(new Vec3d(buf.readFloat(), buf.readFloat(), buf.readFloat())).setVelSpread(new Vec3d(buf.readFloat(), buf.readFloat(), buf.readFloat())).setCount(buf.func_150792_a()).setArgs(buf.func_186863_b());
            config.particles.add(instance);
        }
        return config;
    }
    public static void write(CustomParticleConfig config, PacketBuffer buf) {
        buf.func_150787_b(config.count);
        buf.writeFloat((float) config.origin.field_72450_a);
        buf.writeFloat((float) config.origin.field_72448_b);
        buf.writeFloat((float) config.origin.field_72449_c);
        buf.func_150787_b(config.particles.size());
        for (Instance instance : config.particles) {
            buf.func_150787_b(instance.weight);
            buf.func_180714_a(instance.particleType.name());
            buf.writeBoolean(instance.ignoreRange);
            buf.writeFloat((float) instance.offset.field_72450_a);
            buf.writeFloat((float) instance.offset.field_72448_b);
            buf.writeFloat((float) instance.offset.field_72449_c);
            buf.writeFloat((float) instance.speed.field_72450_a);
            buf.writeFloat((float) instance.speed.field_72448_b);
            buf.writeFloat((float) instance.speed.field_72449_c);
            buf.writeFloat((float) instance.spread.field_72450_a);
            buf.writeFloat((float) instance.spread.field_72448_b);
            buf.writeFloat((float) instance.spread.field_72449_c);
            buf.writeFloat((float) instance.velSpread.field_72450_a);
            buf.writeFloat((float) instance.velSpread.field_72448_b);
            buf.writeFloat((float) instance.velSpread.field_72449_c);
            buf.func_150787_b(instance.count);
            buf.func_186875_a(instance.args);
        }
    }
}
