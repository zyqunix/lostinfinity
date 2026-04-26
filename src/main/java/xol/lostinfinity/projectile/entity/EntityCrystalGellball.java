package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.misc.EntityMirrorZombie;
public class EntityCrystalGellball extends EntityBaseThrowable {
    public EntityCrystalGellball(World par1World) {
        super(par1World);
    }
    public EntityCrystalGellball(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityCrystalGellball(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityZombie)) {
                result.field_72308_g.func_70106_y();
                result.field_72308_g.func_82142_c(true);
                EntityMirrorZombie mz = new EntityMirrorZombie(this.field_70170_p);
                mz.func_70107_b(this.field_70165_t, this.field_70163_u + 0.2d, this.field_70161_v);
                this.field_70170_p.func_72838_d(mz);
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.05f;
    }
}
