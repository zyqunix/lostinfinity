package xol.lostinfinity.client.fx;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;
public class ParticlePlasmaExplosion extends LostParticle {
    private static final Random RANDOM = new Random();
    private int tickOffset;
    protected ParticlePlasmaExplosion(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeed, double ySpeed, double zSpeed, int multi_scl) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.5d - RANDOM.nextDouble(), ySpeed, 0.5d - RANDOM.nextDouble());
        this.tickOffset = 0;
        this.field_190014_F = (0.5f * this.field_187136_p.nextFloat()) - 0.25f;
        this.scale = 10.0f + (RANDOM.nextFloat() * 3.0f) + multi_scl;
        this.field_70547_e = 16;
        this.tickOffset = RANDOM.nextInt(3);
        func_82338_g(0.3f + (this.field_187136_p.nextFloat() / 3.0f));
        this.field_70551_j = 0.75f + (((float) this.field_187136_p.nextGaussian()) / 4.0f);
        this.field_70552_h = ((float) this.field_187136_p.nextGaussian()) / 2.0f;
        func_187117_a(ParticleInit.PLASMA_EXPLOSION_SPRITE);
    }
    public ParticlePlasmaExplosion setParticleGravity(double motionY) {
        this.field_187130_j = motionY;
        return this;
    }
    public boolean func_187111_c() {
        return true;
    }
    @Override // xol.lostinfinity.client.fx.LostParticle
    public void func_189213_a() {
        super.func_189213_a();
        int i = this.field_70546_d;
        this.field_70546_d = i + 1;
        if (i >= this.field_70547_e) {
            func_187112_i();
        }
    }
    @Override // xol.lostinfinity.client.fx.LostParticle
    public void func_180434_a(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        if ((entityIn.field_70173_aa + this.tickOffset) % 2 == 0) {
            super.func_180434_a(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        }
    }
    public int func_70537_b() {
        return 1;
    }
    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        @Nullable
        public Particle func_178902_a(int particleID, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... args) {
            return new ParticlePlasmaExplosion(world, x, y, z, xSpeed, ySpeed, zSpeed, args[0]);
        }
    }
}
