package xol.lostinfinity.client.fx;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/client/fx/ParticleLightningIndicator.class */
public class ParticleLightningIndicator extends LostParticle {
    private static final Random RANDOM = new Random();

    protected ParticleLightningIndicator(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeed, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.5d - RANDOM.nextDouble(), ySpeed, 0.5d - RANDOM.nextDouble());
        this.field_187130_j *= 0.0d;
        this.field_187129_i *= 0.10000000149011612d;
        this.field_187131_k *= 0.10000000149011612d;
        this.scale = 4.5f + (2.0f * this.field_187136_p.nextFloat());
        this.field_82339_as = 0.0f;
        this.field_70547_e = (int) (14.0d / ((Math.random() * 0.8d) + 0.2d));
        func_187117_a(ParticleInit.LIGHTNING_INDICATOR_SPRITE);
    }

    public ParticleLightningIndicator setParticleGravity(double motionY) {
        this.field_187130_j = motionY;
        return this;
    }

    public boolean func_187111_c() {
        return true;
    }

    @Override // xol.lostinfinity.client.fx.LostParticle
    public void func_189213_a() {
        super.func_189213_a();
        if (this.field_70546_d < 20) {
            if (this.field_82339_as < 1.0f) {
                this.field_82339_as += 0.2f;
            }
        } else if (this.field_82339_as > 0.0f) {
            this.field_82339_as -= 0.1f;
        } else {
            func_187112_i();
        }
        this.field_70546_d++;
        func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
        this.field_187129_i *= 0.9599999785423279d;
        this.field_187130_j *= 0.9599999785423279d;
        this.field_187131_k *= 0.9599999785423279d;
        if (this.field_187132_l) {
            this.field_187129_i *= 0.699999988079071d;
            this.field_187131_k *= 0.699999988079071d;
        }
    }

    public int func_70537_b() {
        return 1;
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/client/fx/ParticleLightningIndicator$Factory.class */
    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        @Nullable
        public Particle func_178902_a(int particleID, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... p_178902_15_) {
            return new ParticleLightningIndicator(world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
