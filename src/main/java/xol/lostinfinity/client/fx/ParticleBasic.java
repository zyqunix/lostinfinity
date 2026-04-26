package xol.lostinfinity.client.fx;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/client/fx/ParticleBasic.class */
public class ParticleBasic extends LostParticle {
    protected ParticleBasic(World worldIn, double xPos, double yPos, double zPos, double xSpeed, double ySpeed, double zSpeed) {
        super(worldIn, xPos, yPos, zPos, xSpeed, ySpeed, zSpeed);
        this.field_70547_e = (int) (8.0d / ((Math.random() * 0.8d) + 0.2d));
    }

    public ParticleBasic setTexture(TextureAtlasSprite sprite) {
        func_187117_a(sprite);
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
        func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
    }

    public int func_70537_b() {
        return 1;
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/client/fx/ParticleBasic$BaseParticleFactory.class */
    @SideOnly(Side.CLIENT)
    public static class BaseParticleFactory implements IParticleFactory {
        public Particle func_178902_a(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
            Particle particle = new ParticleBasic(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            particle.func_187114_a(20);
            return particle;
        }
    }
}
