package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.misc.EntityTNTZombie;
public class EntityTNTStrapper extends EntityBaseThrowable {
    public EntityTNTStrapper(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityTNTStrapper(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityTNTStrapper(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (func_85052_h() != null) {
                EntityTNTZombie zombie = new EntityTNTZombie(this.field_70170_p);
                zombie.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                zombie.func_193101_c((EntityPlayer) func_85052_h());
                this.field_70170_p.func_72838_d(zombie);
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.05f;
    }
}
