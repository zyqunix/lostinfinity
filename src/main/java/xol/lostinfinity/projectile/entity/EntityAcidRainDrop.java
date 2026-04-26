package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityAcidRainDrop extends EntityBaseThrowable {
    public EntityAcidRainDrop(World par1World) {
        super(par1World);
        func_70105_a(0.95f, 0.95f);
    }
    public EntityAcidRainDrop(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.95f, 0.95f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealTrueDamage(this, result.field_72308_g, result.field_72308_g.func_110138_aP() * 0.5f);
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.02f;
    }
}
