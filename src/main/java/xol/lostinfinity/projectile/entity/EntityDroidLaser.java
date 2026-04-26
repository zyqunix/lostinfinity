package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDroidLaser extends EntityBaseThrowable {
    private EntityLivingBase owner;
    public EntityDroidLaser(World par1World) {
        super(par1World);
        this.owner = null;
        func_70105_a(0.75f, 0.75f);
    }
    public EntityDroidLaser(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.owner = null;
        func_70105_a(0.75f, 0.75f);
    }
    public EntityDroidLaser(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.owner = null;
        func_70105_a(0.75f, 0.75f);
    }
    public void setOwner(EntityLivingBase play) {
        this.owner = play;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && !result.field_72308_g.equals(this.owner) && !result.field_72308_g.equals(func_85052_h()) && !result.field_72308_g.equals(func_85052_h()) && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, 3);
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
