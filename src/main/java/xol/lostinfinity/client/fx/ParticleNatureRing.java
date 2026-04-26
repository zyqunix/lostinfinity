package xol.lostinfinity.client.fx;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;
public class ParticleNatureRing extends LostParticle {
    private static final Random RANDOM = new Random();
    protected ParticleNatureRing(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeed, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.5d - RANDOM.nextDouble(), ySpeed, 0.5d - RANDOM.nextDouble());
        this.field_187130_j *= 0.20000000298023224d;
        if (xSpeedIn == 0.0d && zSpeedIn == 0.0d) {
            this.field_187129_i *= 0.0d;
            this.field_187131_k *= 0.10000000149011612d;
        }
        this.scale = 1.0f;
        this.field_70547_e = ((int) (7.0d / ((((double) this.field_187136_p.nextFloat()) * 0.8d) + 0.2d))) + 7;
        this.growSpeed = (0.3f + (this.field_187136_p.nextFloat() / 2.0f)) * 9.0f;
        func_187117_a(ParticleInit.NATURE_RING_SPRITE);
        this.field_190017_n = false;
    }
    public ParticleNatureRing setParticleGravity(double motionY) {
        this.field_187130_j = motionY;
        return this;
    }
    public boolean func_187111_c() {
        return true;
    }
    @Override // xol.lostinfinity.client.fx.LostParticle
    public void func_189213_a() {
        super.func_189213_a();
        if (this.scale >= 40.0f) {
            this.field_70546_d = this.field_70547_e;
        }
        int i = this.field_70546_d;
        this.field_70546_d = i + 1;
        if (i >= this.field_70547_e) {
            func_187112_i();
        }
        this.field_187130_j += 0.002d;
        func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
    }
    public int func_70537_b() {
        return 1;
    }
    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        @Nullable
        public Particle func_178902_a(int particleID, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... p_178902_15_) {
            return new ParticleNatureRing(world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
