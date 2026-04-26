package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.misc.EntityDroid;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityDroidBall.class */
public class EntityDroidBall extends EntityBaseThrowable {
    private int spawnNum;
    private boolean make_aggro;
    private int grade;

    public EntityDroidBall(World par1World) {
        super(par1World);
        this.spawnNum = 1;
        this.make_aggro = true;
        this.grade = 0;
    }

    public EntityDroidBall(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.spawnNum = 1;
        this.make_aggro = true;
        this.grade = 0;
    }

    public EntityDroidBall(World par1World, EntityLivingBase par2EntityLiving, int extra) {
        this(par1World, par2EntityLiving);
        this.spawnNum = extra;
    }

    public void setAttacking(boolean aggro) {
        this.make_aggro = aggro;
    }

    public void setGrade(int g) {
        this.grade = g;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            for (int dr = 0; dr < this.spawnNum; dr++) {
                EntityDroid droid = new EntityDroid(this.field_70170_p);
                if (func_85052_h() != null && (func_85052_h() instanceof EntityPlayer)) {
                    droid.func_193101_c((EntityPlayer) func_85052_h());
                }
                if (dr == 0) {
                    droid.func_70107_b(this.field_70165_t, this.field_70163_u + 0.2d, this.field_70161_v);
                } else {
                    droid.func_70107_b((this.field_70165_t - 0.5d) + this.field_70146_Z.nextDouble(), this.field_70163_u + 0.2d, (this.field_70161_v - 0.5d) + this.field_70146_Z.nextDouble());
                }
                droid.setAggressive(this.make_aggro);
                droid.setGrade(this.grade);
                this.field_70170_p.func_72838_d(droid);
            }
            func_70106_y();
        }
        func_184185_a(SoundInit.DROID_SUMMON, 1.0f, 1.0f);
    }

    protected float func_70185_h() {
        return 0.05f;
    }
}
