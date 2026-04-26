package xol.lostinfinity.client.fx;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;
public class ParticleBloodDrop extends LostParticle {
    private static final Random RANDOM = new Random();
    protected ParticleBloodDrop(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeed, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.5d - RANDOM.nextDouble(), ySpeed, 0.5d - RANDOM.nextDouble());
        this.field_187130_j = 0.2d;
        this.scale = RANDOM.nextFloat() * 0.5f;
        this.field_70547_e = 30 + this.field_187136_p.nextInt(20);
        this.growSpeed = 0.05f;
        func_187117_a(ParticleInit.BLOOD_DROP_SPRITE);
    }
    public ParticleBloodDrop setParticleGravity(double motionY) {
        this.field_187130_j = motionY;
        return this;
    }
    public boolean func_187111_c() {
        return true;
    }
    @Override // xol.lostinfinity.client.fx.LostParticle
    public void func_189213_a() {
        updatePos();
        updateAngle();
        if (this.scale < 10.0f) {
            updateScale();
        } else {
            this.prevScale = this.scale;
        }
        int i = this.field_70546_d;
        this.field_70546_d = i + 1;
        if (i >= this.field_70547_e) {
            this.field_82339_as -= 0.05f;
            if (this.field_82339_as <= 0.0f) {
                func_187112_i();
            }
            this.field_187130_j -= 0.019999999552965164d;
        } else {
            this.field_187130_j -= 0.009999999776482582d;
        }
        func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
        if (this.field_187127_g == this.field_187124_d) {
            this.field_187129_i *= 1.1d;
            this.field_187131_k *= 1.1d;
        }
        this.field_187129_i *= 0.9599999785423279d;
        this.field_187131_k *= 0.9599999785423279d;
        if (this.field_187132_l) {
            this.field_187129_i *= 0.699999988079071d;
            this.field_187131_k *= 0.699999988079071d;
        }
    }
    public int func_70537_b() {
        return 1;
    }
    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        @Nullable
        public Particle func_178902_a(int particleID, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... p_178902_15_) {
            return new ParticleBloodDrop(world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
