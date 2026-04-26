package xol.lostinfinity.client.fx;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/client/fx/ParticleStationaryBubble.class */
public class ParticleStationaryBubble extends LostParticle {
    private static final Random RANDOM = new Random();
    private float maxScale;

    protected ParticleStationaryBubble(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeed, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.5d - RANDOM.nextDouble(), ySpeed, 0.5d - RANDOM.nextDouble());
        this.maxScale = 1.0f;
        this.field_187129_i = 0.0d;
        this.field_187130_j = 0.05000000074505806d;
        this.field_187131_k = 0.0d;
        this.scale = RANDOM.nextFloat();
        this.field_70547_e = (int) (14.0d / ((Math.random() * 0.8d) + 0.2d));
        this.maxScale = 0.1f + (this.field_187136_p.nextFloat() * 4.0f);
        func_187117_a(ParticleInit.STATIONARY_BUBBLE_SPRITE);
    }

    public ParticleStationaryBubble setParticleGravity(double motionY) {
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
        if (this.field_70547_e - this.field_70546_d < 8) {
            if (this.scale > 0.5d) {
                this.prevScale = this.scale;
                this.scale = (float) (((double) this.scale) - 0.25d);
            }
        } else if (this.scale < this.maxScale) {
            this.prevScale = this.scale;
            this.scale = (float) (((double) this.scale) + 0.5d);
        } else {
            this.prevScale = this.scale;
        }
        int i = this.field_70546_d;
        this.field_70546_d = i + 1;
        if (i >= this.field_70547_e) {
            func_187112_i();
        }
        this.field_187130_j += 0.002d;
        func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
        if (this.field_187127_g == this.field_187124_d) {
            this.field_187129_i *= 1.1d;
            this.field_187131_k *= 1.1d;
        }
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

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/client/fx/ParticleStationaryBubble$Factory.class */
    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        @Nullable
        public Particle func_178902_a(int particleID, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... p_178902_15_) {
            return new ParticleStationaryBubble(world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
