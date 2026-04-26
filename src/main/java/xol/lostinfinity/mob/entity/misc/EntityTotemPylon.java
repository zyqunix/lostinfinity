package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityTotemPylon.class */
public class EntityTotemPylon extends EntityLiving {
    private EntityPlayer owner;
    private int timer;

    public EntityTotemPylon(World worldIn) {
        super(worldIn);
        this.owner = null;
        this.timer = 200;
        func_70105_a(1.0f, 1.0f);
        func_184224_h(true);
    }

    public void setOwner(EntityPlayer play) {
        this.owner = play;
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.timer % 5 == 0) {
                for (EntityThrowable entityThrowable : this.field_70170_p.func_72872_a(Entity.class, new AxisAlignedBB(func_180425_c()).func_186662_g(20.0d))) {
                    boolean flag = true;
                    if (entityThrowable instanceof EntityThrowable) {
                        if (entityThrowable.func_85052_h() != null && this.owner != null && entityThrowable.func_85052_h().func_110124_au().equals(this.owner.func_110124_au())) {
                            flag = false;
                        }
                    } else if (entityThrowable instanceof EntityArrow) {
                        if (((EntityArrow) entityThrowable).field_70250_c != null && this.owner != null && ((EntityArrow) entityThrowable).field_70250_c.func_110124_au().equals(this.owner.func_110124_au())) {
                            flag = false;
                        }
                    } else if (entityThrowable instanceof EntityFireball) {
                        if (((EntityFireball) entityThrowable).field_70235_a != null && this.owner != null && ((EntityFireball) entityThrowable).field_70235_a.func_110124_au().equals(this.owner.func_110124_au())) {
                            flag = false;
                        }
                    } else {
                        flag = false;
                    }
                    if (flag) {
                        CustomParticleConfig config1 = new CustomParticleConfig();
                        config1.createInstance().setParticle(ParticleInit.FLAME_LARGE).setSpread(1.0d, 0.0d, 1.0d).setSpeed(0.3d, 0.0d, 0.3d).setVelSpread(1.0d, 0.0d, 1.0d).setCount(5).setIgnoreRange(true);
                        IParticleSpawner.spawnParticle(this.field_70170_p, config1, ((Entity) entityThrowable).field_70165_t, ((Entity) entityThrowable).field_70163_u, ((Entity) entityThrowable).field_70161_v);
                        func_184185_a(SoundInit.ITEM_AXIOMAVORUM, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
                        entityThrowable.func_70106_y();
                    }
                }
            }
            if (this.timer <= 0) {
                func_70106_y();
            }
            this.timer--;
        }
    }
}
