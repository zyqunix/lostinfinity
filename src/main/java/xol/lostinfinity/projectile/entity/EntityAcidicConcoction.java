package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityAcidicConcoction.class */
public class EntityAcidicConcoction extends EntityBaseThrowable {
    public EntityAcidicConcoction(World par1World) {
        super(par1World);
    }

    public EntityAcidicConcoction(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityAcidicConcoction(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    protected float func_70185_h() {
        return 0.05f;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && result.field_72308_g.equals(func_85052_h())) {
                return;
            }
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.POISON_EXPLOSION).setSpread(15.0d, 2.0d, 15.0d).setCount(15).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            func_184185_a(SoundInit.FLASK_EXPLODE, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
            for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(10))) {
                if (!target.equals(func_85052_h())) {
                    target.func_70690_d(new PotionEffect(PotionInit.ULTRAHEAVY, 200, 2));
                }
            }
            for (int i = -10; i <= 10; i++) {
                for (int j = -10; j <= 10; j++) {
                    if ((i * i) + (j * j) <= 10 * 10) {
                        BlockPos pos = func_180425_c().func_177982_a(i, 0, j);
                        if (this.field_70170_p.func_175623_d(pos) && this.field_70170_p.func_175665_u(pos.func_177977_b())) {
                            this.field_70170_p.func_175656_a(pos, BlockInit.acidicConcoctionGel.func_176223_P());
                        } else {
                            boolean found = false;
                            int dist = 0;
                            while (!found && dist < 5) {
                                dist++;
                                if (this.field_70170_p.func_175623_d(pos) && this.field_70170_p.func_175665_u(pos.func_177977_b())) {
                                    this.field_70170_p.func_175656_a(pos, BlockInit.acidicConcoctionGel.func_176223_P());
                                    found = true;
                                }
                                if (this.field_70170_p.func_175623_d(pos) && this.field_70170_p.func_175623_d(pos.func_177977_b())) {
                                    pos = pos.func_177977_b();
                                } else if (!this.field_70170_p.func_175623_d(pos)) {
                                    pos = pos.func_177984_a();
                                }
                            }
                        }
                    }
                }
            }
            func_70106_y();
        }
    }
}
