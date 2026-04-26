package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.misc.EntityDroid;
public class EntityDroidZapper extends EntityBaseThrowable {
    private boolean make_aggro;
    public EntityDroidZapper(World par1World) {
        super(par1World);
        this.make_aggro = true;
    }
    public EntityDroidZapper(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.make_aggro = true;
    }
    public void setAttacking(boolean aggro) {
        this.make_aggro = aggro;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            for (EntityDroid droid : this.field_70170_p.func_72872_a(EntityDroid.class, func_174813_aQ().func_72314_b(30.0d, 30.0d, 30.0d))) {
                droid.func_70634_a((this.field_70165_t - 1.0d) + (this.field_70146_Z.nextDouble() * 2.0d), this.field_70163_u + 0.5d, (this.field_70161_v - 1.0d) + (this.field_70146_Z.nextDouble() * 2.0d));
                droid.setAggressive(this.make_aggro);
            }
            func_70106_y();
        }
        func_184185_a(SoundInit.LARGE_TELEPORT, 1.0f, 1.0f);
    }
    protected float func_70185_h() {
        return 0.05f;
    }
}
