package xol.lostinfinity.client.fx;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
public abstract class LostParticle extends Particle {
    protected float growSpeed;
    protected float rotateSpeed;
    protected float scale;
    protected float prevScale;
    public LostParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
    }
    public void func_189213_a() {
        updatePos();
        updateScale();
        updateAngle();
    }
    protected void updatePos() {
        this.field_187123_c = this.field_187126_f;
        this.field_187124_d = this.field_187127_g;
        this.field_187125_e = this.field_187128_h;
    }
    protected void updateScale() {
        this.prevScale = this.scale;
        this.scale += this.growSpeed;
    }
    protected void updateAngle() {
        this.field_190015_G = this.field_190014_F;
        this.field_190014_F += this.rotateSpeed;
    }
    public void func_180434_a(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        this.field_70544_f = (float) MathHelper.func_151238_b(this.prevScale, this.scale, partialTicks);
        super.func_180434_a(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }
}
