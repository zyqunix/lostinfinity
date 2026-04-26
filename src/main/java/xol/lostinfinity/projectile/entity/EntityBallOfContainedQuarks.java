package xol.lostinfinity.projectile.entity;
import java.util.Iterator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityBallOfContainedQuarks extends EntityBaseThrowable {
    public EntityBallOfContainedQuarks(World par1World) {
        super(par1World);
    }
    public EntityBallOfContainedQuarks(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityBallOfContainedQuarks(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            AxisAlignedBB bb = new AxisAlignedBB(func_180425_c()).func_72314_b(0.5d, 0.5d, 0.5d);
            Iterator it = this.field_70170_p.func_72872_a(EntityBallOfContainedGluons.class, bb).iterator();
            if (it.hasNext()) {
                EntityBallOfContainedGluons gluons = (EntityBallOfContainedGluons) it.next();
                for (BlockPos pos : BlockPos.func_177980_a(func_180425_c().func_177982_a(-2, -2, -2), func_180425_c().func_177982_a(2, 2, 2))) {
                    if (this.field_70170_p.func_180495_p(pos).equals(BlockInit.atomiteOre.func_176203_a(0))) {
                        this.field_70170_p.func_175656_a(pos, BlockInit.atomiteOre.func_176203_a(1));
                    }
                }
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.setCount(3);
                config1.createInstance().setParticle(ParticleInit.EXPLOSION_BLUE).setSpread(5.0d, 1.0d, 5.0d).setIgnoreRange(true);
                config1.createInstance().setParticle(ParticleInit.EXPLOSION_TEAL).setSpread(5.0d, 1.0d, 5.0d).setIgnoreRange(true);
                CustomParticleConfig config2 = new CustomParticleConfig();
                config2.createInstance().setParticle(ParticleInit.MURK).setSpread(10.0d, 2.0d, 10.0d).setCount(7).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                IParticleSpawner.spawnParticle(this.field_70170_p, config2, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                func_184185_a(SoundInit.GENERIC_WEAPON_6, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
                gluons.func_70106_y();
                func_70106_y();
            }
        }
    }
    protected float func_70185_h() {
        return 0.05f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K && result.field_72313_a == RayTraceResult.Type.BLOCK) {
            func_70106_y();
        }
    }
}
